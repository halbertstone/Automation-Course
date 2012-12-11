package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

public class CatalogSectionForm extends BaseForm {

    private final Label firstCatalogItem = new Label(By.cssSelector("td[class='pdescr'] strong"), "First item in catalog");

    protected CatalogSectionForm(final By locator, final String formTitle) {
        super(locator, formTitle);
    }

    /**
     * This method returns the name of the first item in catalog
     * @return Item name
     */
    public String getFirstItemName() {
        return firstCatalogItem.getText();
    }
}
