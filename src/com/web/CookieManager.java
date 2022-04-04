package com.web;

import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.Cookie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CookieManager {

    public static List<Cookie> cookieList;
    private static final List<String> cookieNames = Arrays.asList("Selenium", "_gid", "_ga");

    public void readCookies() {
        if (cookieList == null) {
            cookieList = ThucydidesWebDriverSupport.getDriver().manage().getCookies().stream()
                    .filter(cookie -> cookieNames.contains(cookie.getName()))
                    .collect(Collectors.toList());
        }
    }

    public void writeCookies() {
        cookieList.forEach(cookie -> ThucydidesWebDriverSupport.getDriver().manage().addCookie(cookie));
    }
}
