package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.Link;

public class HomeForm extends BaseForm {

    public Link login = new Link(By.linkText("Войти"), "Login");
    private Label username = new Label(By.className("user-name"), "Username");
    private Link catalog = new Link(By.xpath("//nav//a[contains(text(), 'Каталог и цены')]"), "Catalog");

    public HomeForm() {
        super(By.id("g-search-input"), "Onliner Home Page");
    }

    /**
     * This method returns the username of the logged user
     * @return Username
     */
    public String getLoggedUsername() {
        return username.getText();
    }

    /**
     * This method opens up a page with Onliner catalog
     * @return Opened form object
     */
    public CatalogHomeForm openCatalog() {
        catalog.clickAndWait();
        return new CatalogHomeForm();
    }
}
