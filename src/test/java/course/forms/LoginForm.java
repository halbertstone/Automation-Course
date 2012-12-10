package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

public class LoginForm extends BaseForm {

    private TextBox username = new TextBox(By.id("username"), "Username");
    private TextBox password = new TextBox(By.id("password"), "Password");
    private Button login = new Button(By.xpath("//input[@alt = 'Войти и быть как дома']"), "Login");

    public LoginForm() {
        super(By.id("username"), "Onliner Login Page");
    }

    /**
     * This method is used for login with specified credentials
     * @param userLogin Username
     * @param userPassword Password
     */
    public void login(final String userLogin, final String userPassword) {
        username.type(userLogin);
        password.type(userPassword);
        login.clickAndWait();
    }
}
