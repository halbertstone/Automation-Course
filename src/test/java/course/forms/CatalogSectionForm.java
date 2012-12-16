package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.Link;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogSectionForm extends BaseForm {

    private final Label firstCatalogItem = new Label(By.cssSelector("td[class='pdescr'] strong"),
                                                     "First item in catalog");

    // Products locators
    private final String productRowLocator = "form[name='product_list'] table tr";
    private final String productNameLocatorTemplate = "//tr[%1$s]//strong//a";
    private final String productDescriptionLocatorTemplate = "//tr[%1$s]//td[@class='pdescr']/div";

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
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        int i = 1;
        while (i <= recordsCount) {
            Label productDescription = new Label(By.xpath(String.format(productDescriptionLocatorTemplate, i*2)),
                                                 String.format("Description of %1$s product", i));

            Matcher matcher = pattern.matcher(productDescription.getText());
            if (matcher.find()) {
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
}
