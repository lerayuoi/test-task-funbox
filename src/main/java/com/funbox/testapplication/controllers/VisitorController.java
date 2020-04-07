package com.funbox.testapplication.controllers;

import com.funbox.testapplication.model.LinksDto;
import com.funbox.testapplication.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.funbox.testapplication.model.DomainsDto;
import com.funbox.testapplication.service.VisitorServiceBean;

@RestController
@RequestMapping("/api")
public class VisitorController {
    @Autowired
    private VisitorServiceBean visitorServiceBean;

    @PostMapping("/visited_links")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseDto createVisitedLinks(@RequestBody final LinksDto linksDto) {
        Long receivedTime = System.currentTimeMillis();
        return visitorServiceBean.createVisitedLinks(linksDto, receivedTime);
    }

    @GetMapping("/visited_domains")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DomainsDto getVisitedDomains(@RequestParam(value = "from", required = false) final Long from,
                                        @RequestParam(value = "to", required = false) final Long to) {
        return visitorServiceBean.getVisitedDomains(from, to);
    }
}
