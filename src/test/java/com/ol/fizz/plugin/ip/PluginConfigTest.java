package com.ol.fizz.plugin.ip;

import com.ol.fizz.plugin.ip.PluginConfig.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PluginConfigTest {

    @Test
    void testPluginConfig() {
        Assertions.assertDoesNotThrow(() -> {
            PluginConfig pluginConfig = new PluginConfig();
            Item item = new Item();
            item.setBlackIp("");
            item.setWhiteIp("");
            item.setGwGroup("");
            pluginConfig.getConfigs().add(item);
            Assertions.assertNotNull(pluginConfig);
        });
    }
}
