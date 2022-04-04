package gui;

import com.steps.FlashPageSteps;
import com.steps.HomePageSteps;
import com.web.TestLogger;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTagValuesOf;
import org.junit.BeforeClass;
import org.junit.Test;

public class SameDropdownFilterTests extends TestLogger {

    @Steps
    HomePageSteps homePageSteps;
    @Steps
    FlashPageSteps flashPageSteps;

    @BeforeClass
    public static void beforeClass(){
        new HomePageSteps().openHomePage();
    }

    @Test
    @WithTagValuesOf({"suite:sanity","suite:docker"})
    public void checkElementsWithinDropdownAndChooseOne() {
        homePageSteps.chooseOptionFromSeleniumDropdown("Flash Movie Demo");
        flashPageSteps.checkJsTextIsDisplayed();
    }
}