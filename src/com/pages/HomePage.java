package com.pages;

import com.fragments.FilterDropdownFragment;
import com.web.BasePage;
import com.web.CustomElement;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final CustomElement SELENIUM_DROPDOWN = new CustomElement("SELENIUM_DROPDOWN", By.xpath("(//li[@class='dropdown'])[1]"));
    private final CustomElement AD_BLOCK = new CustomElement("AD_BLOCK", By.xpath("//div[contains(@id,'google_ads')]"));
    private final CustomElement FRAME_CLOSE_BUTTON = new CustomElement("FRAME_CLOSE_BUTTON", By.xpath("//span[text()='Close']"));

    FilterDropdownFragment filterDropdownFragment = new FilterDropdownFragment(SELENIUM_DROPDOWN, false);

    public void selectOptionFromSelenium(String optionName) {
        filterDropdownFragment.selectFilterOption(optionName);
        if (AD_BLOCK.isDisplayed()){
            driver.switchTo().frame("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0");
            driver.switchTo().frame("ad_iframe");
            FRAME_CLOSE_BUTTON.click();
        }
    }
}
