package com.fizz.plugin.ip;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

@Disabled
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
class IpPluginTest {

    @Resource
    private IpPlugin ipPlugin;

    @Test
    void testBean() {
        Assertions.assertNotNull(ipPlugin);
    }

    @Test
    void testFilter() {
        Assertions.assertDoesNotThrow(() -> {
            Map<String, Object> config = Maps.newHashMap();
            config.put(RouterConfig.FieldName.WHITE_IP, "");
            config.put(RouterConfig.FieldName.BLACK_IP, "180.169.124.157");
            config.put(com.fizzgate.plugin.PluginConfig.CUSTOM_CONFIG, "{\"configs\":[{\"gwGroup\":\"fizz-gateway-plugin-example\",\"whiteIp\":\"\",\"blackIp\":\"10.237.209.56,180.169.124.158\"}]}");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("clientip", "180.169.124.157");
            httpHeaders.add("x-forwarded-for", "180.169.124.158");
            httpHeaders.add("proxy-client-ip", "180.169.124.159");
            httpHeaders.add("wl-proxy-client-ip", "180.169.124.160");
            MockServerHttpRequest.BodyBuilder request = MockServerHttpRequest.method(HttpMethod.POST, "http://127.0.0.1:8600/admin/health").
                    headers(httpHeaders);
            MockServerWebExchange exchange = MockServerWebExchange.builder(request).build();
            Mono<Void> filter = ipPlugin.filter(exchange, config);
            Assertions.assertNotNull(filter, "filter不能为null");
        }, "不可抛异常");
    }

    @Test
    void testExcepiton() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Map<String, Object> config = Maps.newHashMap();
            config.put(RouterConfig.FieldName.WHITE_IP, "");
            config.put(RouterConfig.FieldName.BLACK_IP, "180.169.124.157");
            config.put(com.fizzgate.plugin.PluginConfig.CUSTOM_CONFIG, "{\"configs\":[1{\"gwGroup\":\"fizz-gateway-plugin-example\",\"whiteIp\":\"\",\"blackIp\":\"10.237.209.56,180.169.124.158\"}]}");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("clientip", "180.169.124.157");
            httpHeaders.add("x-forwarded-for", "180.169.124.158");
            httpHeaders.add("proxy-client-ip", "180.169.124.159");
            httpHeaders.add("wl-proxy-client-ip", "180.169.124.160");
            MockServerHttpRequest.BodyBuilder request = MockServerHttpRequest.method(HttpMethod.POST, "http://127.0.0.1:8600/admin/health").
                    headers(httpHeaders);
            MockServerWebExchange exchange = MockServerWebExchange.builder(request).build();
            ipPlugin.filter(exchange, config);
        });
    }

    @Test
    void testNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Map<String, Object> config = Maps.newHashMap();
            config.put(RouterConfig.FieldName.WHITE_IP, "");
            config.put(RouterConfig.FieldName.BLACK_IP, "");
            config.put(com.fizzgate.plugin.PluginConfig.CUSTOM_CONFIG, "{\"configs\":[{\"gwGroup\":\"fizz-gateway-plugin-example\",\"whiteIp\":\"\",\"blackIp\":\"10.237.209.56,180.169.124.158\"}]}");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("clientip", "180.169.124.157");
            httpHeaders.add("x-forwarded-for", "180.169.124.158");
            httpHeaders.add("proxy-client-ip", "180.169.124.159");
            httpHeaders.add("wl-proxy-client-ip", "180.169.124.160");
            MockServerHttpRequest.BodyBuilder request = MockServerHttpRequest.method(HttpMethod.POST, "http://127.0.0.1:8600/admin/health").
                    headers(httpHeaders);
            MockServerWebExchange exchange = MockServerWebExchange.builder(request).build();
            ipPlugin.filter(exchange, config);
        });
    }

    @Test
    void testIP() {
        Assertions.assertDoesNotThrow(() -> {
            Map<String, Object> config = Maps.newHashMap();
            config.put(RouterConfig.FieldName.WHITE_IP, "");
            config.put(RouterConfig.FieldName.BLACK_IP, "");
            config.put(com.fizzgate.plugin.PluginConfig.CUSTOM_CONFIG, "{\"configs\":[{\"gwGroup\":\"fizz-gateway-plugin-example\",\"whiteIp\":\"\",\"blackIp\":\"10.237.209.56,180.169.124.158\"}]}");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("clientip", "");
            httpHeaders.add("x-forwarded-for", "");
            httpHeaders.add("proxy-client-ip", "");
            httpHeaders.add("wl-proxy-client-ip", "");
            MockServerHttpRequest.BodyBuilder request = MockServerHttpRequest.method(HttpMethod.POST, "http://127.0.0.1:8600/admin/health").
                    headers(httpHeaders);
            MockServerWebExchange exchange = MockServerWebExchange.builder(request).build();
            ipPlugin.filter(exchange, config);
        }, "不可抛异常");
    }


}