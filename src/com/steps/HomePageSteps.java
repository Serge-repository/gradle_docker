package com.steps;

import com.pages.HomePage;
import net.thucydides.core.annotations.Step;

public class HomePageSteps {

    private final HomePage homePage = new HomePage();

    @Step
    public void openHomePage(){ // так вызываем каждую страницу
        homePage.get();
    }

    @Step
    public void chooseOptionFromSeleniumDropdown(String option) {
        homePage.selectOptionFromSelenium(option);
    }
}
