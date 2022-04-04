package com.web;

import com.web.properties.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MyDriver implements DriverSource {

    private static final Browser browser = Browser.getCurrentBrowser();
    WebDriver driver;

    // Uncomment for SeleniumGrid run
    String host = "http://localhost:4444";
    // Uncomment for local run
//    String host = null;

    @Override
    public WebDriver newDriver() {
        MutableCapabilities browserOptions;
        if (host != null) {
            if (System.getProperty("browser") != null && System.getProperty("browser").equals("firefox")) {
                browserOptions = getFirefoxCapabilities();
            } else {
                browserOptions = getChromeCapabilities();
            }
            if (System.getProperty("HUB_HOST") != null) {
                host = System.getProperty("HUB_HOST");
            }

            String completeUrl = host + "/wd/hub";
            try {
                this.driver = new RemoteWebDriver(new URL(completeUrl), browserOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            browserOptions = initCaps();
            switch (browser) {
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver((FirefoxOptions) browserOptions);
                    driver.manage().window().maximize();
                    break;
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver((ChromeOptions) browserOptions);
                    driver.manage().window().maximize();
                    break;
                default:
                    throw new RuntimeException(browser.name() + " initialization is not implemented. Try Chrome or Firefox");
            }
        }
        return driver;
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

    private MutableCapabilities initCaps() {
        switch (browser) {
            case CHROME:
                return getChromeCapabilities();
            case FIREFOX:
                return getFirefoxCapabilities();
            default:
                throw new RuntimeException(browser + " browser is not expected");
        }
    }

    private MutableCapabilities getChromeCapabilities() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("safebrowsing.enabled", "true");
        prefs.put("profile.default_content_settings.multiple-automatic-downloads", 1);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments(String.format("--lang=%s", "en"));
        return chromeOptions;
    }

    private MutableCapabilities getFirefoxCapabilities() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("intl.accept_languages", "en");
        firefoxOptions.setProfile(firefoxProfile);
        return firefoxOptions;
    }
}
