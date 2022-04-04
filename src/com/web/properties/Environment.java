package com.web.properties;

public enum Environment {
    DEV("DEV_DOMAIN"),   //называть енумы как в json
    PROD("PROD_DOMAIN"),
    STAGING("STAGING_DOMAIN");

    private static Environment environment = null;
    private final String urlMapValue;

    Environment(String urlMapValue) {
        this.urlMapValue = urlMapValue;
    }

    public static Environment getCurrentEnvironment() {
        if (environment == null) {
            environment = Environment.valueOf(System.getProperty("environment", "staging").toUpperCase());
        }
        return environment;
    }

    public String getUrlMapValue() {
        return urlMapValue;
    }
}
