package com.ol.fizz.plugin.ip;

import com.ol.fizz.plugin.ip.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;

import java.net.InetSocketAddress;
import java.net.SocketException;

@Slf4j
public class IpUtilsTest {

    @Test
    void testIp() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("clientip", "127.0.0.1,127.0.0.1");
        httpHeaders.add("x-forwarded-for", "127.0.0.1");
        httpHeaders.add("proxy-client-ip", "127.0.0.1");
        httpHeaders.add("wl-proxy-client-ip", "127.0.0.1");
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 80);
        MockServerHttpRequest httpRequest = MockServerHttpRequest.get("", "").headers(httpHeaders)
                .remoteAddress(inetSocketAddress).build();
        try {
            String ip = IpUtils.getServerHttpRequestIp(httpRequest);
            log.info("ip:{}", ip);
            Assertions.assertNotNull(ip);
        } catch (SocketException e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Test
    void testLocalIpNumber() throws SocketException {
        long ipNum = IpUtils.localIpNumber();
        Assertions.assertTrue(ipNum > 0);
    }

    @Test
    void testIsInnerIP() throws SocketException {
        String ipAddress = "127.0.0.1";
        boolean isInnerIp = IpUtils.isInnerIP(ipAddress);
        Assertions.assertTrue(isInnerIp);
    }
}
