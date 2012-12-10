package course.tests;

import course.forms.HomeForm;
import course.forms.LoginForm;
import webdriver.BaseTest;
import webdriver.controller.UserType;
import webdriver.controller.UsersController;

public class LoginTest extends BaseTest {

    public UsersController.User user = UsersController.getInstance().getUserByType(UserType.USER);

    public void runTest() {
        LogStep();
        HomeForm home = new HomeForm();
        home.login.clickAndWait();

        LogStep();
        LoginForm loginForm = new LoginForm();
        loginForm.correctLogin(user.getLogin(), user.getPassword());

        LogStep();
        assertEquals("Incorrect user was logged in", user.getLogin(), home.getLoggedUsername());
    }

    @Override
    public boolean shouldAnalys() {
        return false;
    }

    @Override
    public void invokeAnalys(Throwable exc, String bodyText) {
    }
}
