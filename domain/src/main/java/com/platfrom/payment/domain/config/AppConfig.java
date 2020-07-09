package com.platfrom.payment.domain.config;


import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppConfig {


    public final static Map<String, String> propertis = new ConcurrentHashMap();

    {
        propertis.put("eft.mid", "852202004200002");
        propertis.put("eft.app-version", "2.9");
        propertis.put("eft.active-time", "1800");
        propertis.put("EOPG_KEY", "ea5e44684b520dde016f9c44fde47259f5640a39");

    }


    public static String get(String key) {
        return propertis.get(key);
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return Objects.isNull(value) ? defaultValue : value;
    }
}
