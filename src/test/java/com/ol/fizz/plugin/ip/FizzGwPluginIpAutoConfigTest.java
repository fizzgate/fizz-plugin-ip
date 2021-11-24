package com.ol.fizz.plugin.ip;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Ignore
@Slf4j
@SpringBootTest(classes = Application.class)
public class FizzGwPluginIpAutoConfigTest {

    @Resource
    private FizzGwPluginIpAutoConfig fizzGwPluginIpAutoConfig;

    @Test
    void testConfig() {
        Assertions.assertNotNull(fizzGwPluginIpAutoConfig, "没有找到FizzGwPluginIpAutoConfig");
    }

}
