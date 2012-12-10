package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

public class BoardGamesSectionForm extends BaseForm {

    private final Label firstCatalogItem = new Label(By.cssSelector("td[class='pdescr'] strong"), "First item in catalog");

    public BoardGamesSectionForm() {
        super(By.xpath("//h1[text()='Каталог настольных игр']"), "Onliner Catalog Section Page");
    }


    /**
     * This method returns the name of the first item in catalog
     * @return Item name
     */
    public String getFirstItemName() {
        return firstCatalogItem.getText();
    }

}
