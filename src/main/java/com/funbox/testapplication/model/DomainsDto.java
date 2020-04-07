package com.funbox.testapplication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DomainsDto extends ResponseDto<Object> implements Serializable {
    private List<String> domains;

    public DomainsDto(List<String> domains) {
        this.domains = domains;
    }

    public DomainsDto(List<String> domains, ExceptionCodes statusMessage) {
        super(statusMessage);
        this.domains = domains;
    }
}
