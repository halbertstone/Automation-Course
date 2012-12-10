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
        loginForm.login(user.getLogin(), user.getPassword());

        LogStep();
        home.assertIsOpen();
        home.logout.assertIsPresent();
    }

    @Override
    public boolean shouldAnalys() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void invokeAnalys(Throwable exc, String bodyText) {
        // TODO Auto-generated method stub
    }
}
