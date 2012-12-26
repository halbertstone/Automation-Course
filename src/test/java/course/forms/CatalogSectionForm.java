package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.CommonFunctions;
import webdriver.elements.Button;
import webdriver.elements.CheckBox;
import webdriver.elements.Label;
import webdriver.elements.Link;

import java.util.ArrayList;

public class CatalogSectionForm extends BaseForm {

    private final Label firstCatalogItem = new Label(By.cssSelector("td[class='pdescr'] strong"),
                                                     "First item in catalog");
    private final Button compareModels = new Button(By.cssSelector("div[class='pcompbtn'] a"), "Compare models");

    // Products locators
    protected final String productRowLocator = "form[name='product_list'] table tr";
    protected final String productNameLocatorTemplate = "//tr[%1$s]//strong//a";
    protected final String productDescriptionLocatorTemplate = "//tr[%1$s]//td[@class='pdescr']/div";
    protected final String productCheckboxLocatorTemplate = "//tr[%1$s]//td[@class='pcheck']/input";

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

    /**
     * This method returns the number of displayed products on page
     * @return Number of displayed products
     */
    public Integer getNumberOfProductsOnPage() {
        Integer count = browser.getDriver().findElements(By.cssSelector(productRowLocator)).size();
        // Dividing by 2 because Onliner displays extra 'tr' with line picture for every product
        return count / 2;
    }

    /**
     * This method returns products with specific description
     * @param regex Regular Expression used for checking product description
     * @return List of product names
     */
    public ArrayList<String> getRecordsWithDescriptionRegex(final String regex) {
        ArrayList<String> foundProducts = new ArrayList<String>();
        int recordsCount = getNumberOfProductsOnPage();
        int i = 1;
        while (i <= recordsCount) {
            Label productDescription = new Label(By.xpath(String.format(productDescriptionLocatorTemplate, i*2)),
                                                 String.format("Description of %1$s product", i));

            if (CommonFunctions.regexIsMatch(productDescription.getText(), regex)) {
                Link productName = new Link(By.xpath(String.format(productNameLocatorTemplate, i*2)),
                                            String.format("Link for %1$s product name", i));
                foundProducts.add(productName.getText());
            }
            i++;
        }
        return foundProducts;
    }

    /**
     * This method is used for logging records with specific description
     * @param regex Regular Expression used for checking product description
     */
    public void printRecordsWithDescriptionRegex(final String regex) {
        for (String productName : getRecordsWithDescriptionRegex(regex)) {
            info(String.format("Product with specific description: %1$s", productName));
        }
    }

    /**
     * This method is used for clicking comparison checkbox for product
     * @param index Index of a product to click
     */
    public void checkComparisonCheckbox(final Integer index) {
        CheckBox comparisonCheckbox = new CheckBox(By.xpath(String.format(productCheckboxLocatorTemplate, index * 2)),
                String.format("Checkbox for %1$s product", index));

        comparisonCheckbox.check();
    }

    /**
     * This method is used for opening Product Comparison page for selected models
     * @return Object with opened Comparison page
     */
    public ComparisonForm openComparisonPage() {
        compareModels.clickAndWait();
        return new ComparisonForm();
    }
}
