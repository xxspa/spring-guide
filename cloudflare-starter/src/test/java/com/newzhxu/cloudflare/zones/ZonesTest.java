package com.newzhxu.cloudflare.zones;

import com.newzhxu.cloudflare.Cloudflare;
import com.newzhxu.cloudflare.CloudflareAutoConfiguartion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CloudflareAutoConfiguartion.class)
class ZonesTest {
    @Autowired
    private Cloudflare cloudflare;
    private Zones zones;

    @BeforeEach
    void setUp() {
        zones = cloudflare.getZones();
    }

    @Test
    void listZones() {
        var zonesResponse = zones.listZones();
        System.out.println(zonesResponse);
        Assertions.assertTrue(zonesResponse.getSuccess());
    }
}