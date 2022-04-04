package com.steps;

import com.entities.UserManager;
import com.pages.LoginPage;
import net.thucydides.core.annotations.Step;

import static com.entities.UserFactory.chooseCurrentUser;
import static org.junit.Assert.assertTrue;

public class LoginPageSteps {

    private final LoginPage loginPage = new LoginPage();

    @Step
    public void login() {
        loginPage.get();
    }

    @Step
    public void loginIsSuccessful() {
        assertTrue(LoginPage.SUCCESSFUL_LOGIN_MESSAGE.isDisplayed());
    }

    @Step
    public void chooseUser(UserManager user) {
        chooseCurrentUser(user);
    }
}
