package com.funbox.testapplication.service;

import com.funbox.testapplication.model.DomainsDto;
import com.funbox.testapplication.model.LinksDto;
import com.funbox.testapplication.model.ResponseDto;

public interface VisitorService {
    ResponseDto createVisitedLinks(LinksDto linksDto, Long receivedTime);

    DomainsDto getVisitedDomains(Long from, Long to);
}
