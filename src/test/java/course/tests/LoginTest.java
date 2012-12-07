package course.tests;

import course.base.CredentialsHelper;
import course.forms.HomeForm;
import course.forms.LoginForm;
import webdriver.BaseTest;

public class LoginTest extends BaseTest {

    public void runTest() {
        logger.step(1);
        HomeForm home = new HomeForm();
        home.login.clickAndWait();

        logger.step(2);
        LoginForm loginForm = new LoginForm();
        loginForm.login(CredentialsHelper.getUsername(), CredentialsHelper.getPassword());

        logger.step(3);
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
