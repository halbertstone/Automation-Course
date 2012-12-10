package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Link;

public class HomeForm extends BaseForm {

    public Link login = new Link(By.linkText("Войти"), "Login");
    public Link logout = new Link(By.linkText("Выйти"), "Logout");

    public HomeForm() {
        super(By.id("g-search-input"), "Onliner Home Page");
    }
}
