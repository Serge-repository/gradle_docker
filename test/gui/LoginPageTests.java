package gui;

import com.entities.UserManager;
import com.steps.FlashPageSteps;
import com.steps.LoginPageSteps;
import com.web.TestLogger;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTagValuesOf;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginPageTests extends TestLogger {

    @Steps
    LoginPageSteps loginPageSteps;
    @Steps
    FlashPageSteps flashPageSteps;

    @Test
    @WithTag("suite:docker")  //gradle выбор сьюта при запуске через командную строку
    public void aLogin() {
        loginPageSteps.chooseUser(UserManager.ADMIN);
        loginPageSteps.login();
        loginPageSteps.loginIsSuccessful();
    }
    @Test
    @WithTagValuesOf({"suite:sanity","suite:docker"})
    public void bLogin() {
        loginPageSteps.chooseUser(UserManager.ADMIN);
        loginPageSteps.login();
        loginPageSteps.loginIsSuccessful();
    }

    @Test
    @WithTagValuesOf({"suite:sanity","suite:docker"})
    public void dGetDirectPage() {
        flashPageSteps.openFlashPage();
        flashPageSteps.checkJsTextIsDisplayed();
    }
}