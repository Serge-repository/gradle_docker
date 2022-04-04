package com.web.properties;

public enum Browser {
    CHROME,
    FIREFOX;

    private static Browser browser = null;

    public static Browser getCurrentBrowser() {  //для командной строки выбор браузера\драйвера
        if (browser == null) {                   // по умолчанию хром для класа MyDriver
            browser = Browser.valueOf(System.getProperty("browser", "chrome").toUpperCase());
        }
        return browser;
    }
}
