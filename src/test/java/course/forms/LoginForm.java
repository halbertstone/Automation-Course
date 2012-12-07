package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Link;
import webdriver.elements.TextBox;

public class LoginForm extends BaseForm {

    public TextBox username = new TextBox(By.id("username"), "Логин");
    public TextBox password = new TextBox(By.id("password"), "Пароль");
    public Link login = new Link(By.xpath("//input[@alt = 'Войти и быть как дома']"), "Войти и быть как дома");

    public LoginForm() {
        super(By.id("username"), "Onliner Login Page");
    }

    /**
     * This method is used for login with specified credentials
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        this.username.type(username);
        this.password.type(password);
        login.clickAndWait();
    }
}
