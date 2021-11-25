package com.fizz.plugin.ip;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Disabled
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
