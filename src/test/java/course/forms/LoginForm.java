package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

public class LoginForm extends BaseForm {

    private final TextBox username = new TextBox(By.tagName("input"), "Username");
    private final TextBox password = new TextBox(By.name("password"), "Password");
    private final Button login = new Button(By.cssSelector("input[type='image']"), "Login");

    public LoginForm() {
        super(By.id("username"), "Onliner Login Page");
    }

    /**
     * This method is used for correct login with specified credentials
     * @param userLogin Username
     * @param userPassword Password
     */
    public HomeForm correctLogin(final String userLogin, final String userPassword) {
        username.type(userLogin);
        password.type(userPassword);
        login.clickAndWait();
        return new HomeForm();
    }
}
