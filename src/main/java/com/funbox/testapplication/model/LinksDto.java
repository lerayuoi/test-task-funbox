package com.funbox.testapplication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@RedisHash("Link")
public class LinksDto implements Serializable {
    private List<String> links;

    @JsonCreator
    public LinksDto(@JsonProperty("links") List<String> links) {
        this.links = links;
    }


}
