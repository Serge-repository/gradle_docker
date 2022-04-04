package com.web;

import com.pages.LoginPage;
import com.web.properties.Environment;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static com.entities.UserFactory.isUserTheSame;

public abstract class BasePage {

    public static WebDriver driver = ThucydidesWebDriverSupport.getDriver();
    public WebDriverWait wait = new WebDriverWait(driver, 5);
    private final CookieManager cookieManager = new CookieManager();

    private static String DOMAIN;
    private static final Map<String, String> urls = UrlManager.initUrlMaps();

    public void get() {
        LoginPage loginPage = new LoginPage();
        if (!isLoggedIn() || !isUserTheSame) {    //проверка по необходимым кукам есть ли они
            loginPage.getPageUrl();
            loginPage.performLoginFlow();
            cookieManager.readCookies();
            if (!getClass().getSimpleName().equals("LoginPage")){
                getPageUrl();
            }
        } else {
            cookieManager.writeCookies();
            getPageUrl();
            if (getClass().getSimpleName().equals("LoginPage")){
                driver.navigate().refresh();
            }
        }
        waitForPageTobeReady();
    }

    protected void getPageUrl() {
        driver.get(getDomain() + urls.get(getClass().getSimpleName()));
        waitForPageTobeReady();
    }

    private String getDomain() {
        if (DOMAIN == null) {
            DOMAIN = urls.get(Environment.getCurrentEnvironment().getUrlMapValue());
        }
        return DOMAIN;
    }

    private boolean isLoggedIn() {
        return driver.manage().getCookies().stream().anyMatch(cookie -> cookie.getName().equals("Selenium"));
    }

    public void waitForPageTobeReady() {
        try {
            new WebDriverWait(driver, 30).until((WebDriver driver) -> {
                String documentState = (String) ((JavascriptExecutor) this.driver).executeScript("return document.readyState");
                return documentState.equals("complete");
            });
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
