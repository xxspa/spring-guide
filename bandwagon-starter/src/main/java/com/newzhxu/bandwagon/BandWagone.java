package com.newzhxu.bandwagon;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface BandWagone {
    @GetExchange("/start")
    String start();
}
