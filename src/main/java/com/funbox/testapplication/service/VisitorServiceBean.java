package com.funbox.testapplication.service;

import com.funbox.testapplication.model.DomainsDto;
import com.funbox.testapplication.model.LinksDto;
import com.funbox.testapplication.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class VisitorServiceBean implements VisitorService {
    private static final String PATTERN = "^((http[s]?|www):\\/\\/)?\\/?([^\\/\\.]+\\.)*?([^\\/\\.]+\\.[^:\\/\\s\\.]{2,3}(\\.[^:\\/\\s\\.]{2,3})?(:\\d+)?)($|\\/)([^#?\\s]+)?(.*?)?(#[\\w\\-]+)?$";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public ResponseDto createVisitedLinks(LinksDto linksDto, Long receivedTime) {
        Map<String, Long> map = new HashMap<>();
        linksDto.getLinks().forEach(link -> {
            map.put(link, receivedTime);
        });
        redisTemplate.opsForHash().putAll("domains", map);
        return new ResponseDto<>();
    }

    @Override
    public DomainsDto getVisitedDomains(Long from, Long to) {
        List<String> result = new ArrayList<>();
        Map<String, Long> domains = new HashMap<>();
        Map<String, Long> finalDomains = domains;
        redisTemplate.opsForHash().entries("domains").forEach((k, v) -> {
            finalDomains.put((String) k, (Long) v);
        });

        if (from != null && to != null) {
            domains = domains.entrySet().stream()
                    .filter(v -> v.getValue() >= from && v.getValue() <= to)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        if (!CollectionUtils.isEmpty(domains)){
            domains.keySet().forEach(k -> {
                result.add(Pattern.compile(PATTERN)
                        .matcher(k)
                        .results()
                        .map(m -> m.group(4))
                        .findAny()
                        .orElse(null));
            });
        }

        return new DomainsDto(result.stream().filter(Objects::nonNull).collect(Collectors.toList()));
    }
}
