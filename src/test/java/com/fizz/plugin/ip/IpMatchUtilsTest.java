package com.fizz.plugin.ip;

import com.fizz.plugin.ip.util.IpMatchUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IpMatchUtilsTest {

    @Test
    void testBase() {
        assertTrue("192.168.0".matches("192.*"));
    }

    @Test
    void match() {
        String ipWhite = "1.168.1.1" // 设置单个IP的白名单 //
                + ",52.*," // 设置ip通配符,对一个ip段进行匹配
                + ",192.168.3.17-192.168.3.38" // 设置一个IP范围
                + ",192.168.4.0/24" // 設置一个网段
                + ",234.168.32.0/19" // 設置一个网段
                + ",10.237.208.0/24"; // 設置一个网段
        Assertions.assertTrue(IpMatchUtils.match("1.168.1.1", ipWhite)); // true
        assertTrue(IpMatchUtils.match("52.168.1.1", ipWhite)); // true
        Assertions.assertFalse(IpMatchUtils.match("192.168.1.2", ipWhite)); // false
        Assertions.assertFalse(IpMatchUtils.match("192.168.3.16", ipWhite)); // false
        assertTrue(IpMatchUtils.match("192.168.3.37", ipWhite)); // true
        assertTrue(IpMatchUtils.match("192.168.4.1", ipWhite));// true
        assertTrue(IpMatchUtils.match("234.168.32.16", ipWhite));// true
        assertTrue(IpMatchUtils.match("234.168.47.37", ipWhite));// true
        assertTrue(IpMatchUtils.match("234.168.63.231", ipWhite));// true
        assertTrue(IpMatchUtils.match("10.237.208.51", ipWhite));// true
        assertFalse(IpMatchUtils.match("", ipWhite));// false
        assertFalse(IpMatchUtils.match("1", ipWhite));// false
        assertTrue(IpMatchUtils.match("10.237.208.51", "*"));// true
        assertFalse(IpMatchUtils.match("10.237.208.51", "0"));// false
    }

    @Test
    void testMatch() {
        assertTrue(IpMatchUtils.match("192.168.0.1", "192.*"));
    }

    @Test
    void testMatchIp() {
        String ipWhite = "1.168.1.1" // 设置单个IP的白名单 //
                + ",5**.1*," // 设置ip通配符,对一个ip段进行匹配
                + ",192.168.3.17-192.168.3.38" // 设置一个IP范围
                + ",192.168.4.0/24" // 設置一个网段
                + ",234.168.32.0/19" // 設置一个网段
                + ",10.237.208.0/24"; // 設置一个网段
        assertTrue(IpMatchUtils.match("10.237.208.126", ipWhite));// true
    }

}