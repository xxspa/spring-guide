package com.newzhxu.bandwagon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BandwagonAutoconfigration.class)
class BandWagoneTest {


    @Autowired
    BandWagone bandWagone;

    @Test
    void start() {
        String start = bandWagone.start();
        System.out.println(start);
    }
}