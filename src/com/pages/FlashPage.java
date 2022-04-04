package com.pages;

import com.web.BasePage;
import com.web.CustomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

public class FlashPage extends BasePage {

    private final CustomElement JS_CONTROLLER_TEXT = new CustomElement("JS_CONTROLLER_TEXT", By.cssSelector("center>b>font"));

    public void verifyJsTextDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(JS_CONTROLLER_TEXT));
        assertTrue("Check if js text is displayed", JS_CONTROLLER_TEXT.isDisplayed());
    }
}
