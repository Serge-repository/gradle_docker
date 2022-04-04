package com.steps;

import com.pages.FlashPage;
import net.thucydides.core.annotations.Step;

public class FlashPageSteps {

    private final FlashPage flashPage = new FlashPage();

    @Step
    public void openFlashPage(){ // такой метод должен быть у каждого пейджа где можем сделать прямой переход с куками
        flashPage.get();
    }

    @Step
    public void checkJsTextIsDisplayed(){
        flashPage.verifyJsTextDisplayed();
    }
}
