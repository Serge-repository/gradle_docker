package com.pages;

import com.entities.UserFactory;
import com.web.BasePage;
import com.web.CustomElement;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final CustomElement EMAIL_INPUT = new CustomElement("EMAIL_INPUT", By.xpath("(//input)[1]"));
    private final CustomElement PASSWORD_INPUT = new CustomElement("REMEMBER_ME_CHECKBOX", By.xpath("(//input)[2]"));
    private final CustomElement LOGIN_BUTTON = new CustomElement("LOGIN_BUTTON", By.xpath("//button"));
    public static final CustomElement SUCCESSFUL_LOGIN_MESSAGE = new CustomElement("SUCCESSFUL_LOGIN_MESSAGE", By.xpath("(//center)[1]"));

    public void performLoginFlow() {
        waitForPageTobeReady();
        EMAIL_INPUT.sendKeys(UserFactory.currentlySelectedUser.getLogin());
        PASSWORD_INPUT.sendKeys(UserFactory.currentlySelectedUser.getPassword());
        LOGIN_BUTTON.click();
        waitForPageTobeReady();
    }

}
