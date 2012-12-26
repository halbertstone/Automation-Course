package course.forms;

import org.openqa.selenium.By;
import webdriver.elements.Link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhonesSectionForm extends CatalogSectionForm {

    public PhonesSectionForm() {
        super(By.xpath("//h1[text()='Каталог мобильных телефонов и коммуникаторов']"), "Catalog Phones Section Page");
    }

    /**
     * This method returns ids of products with different names
     * @return List of product ids
     */
    public Map<String,Integer> getRecordsWithDifferentNames(final int... params) {
        int limit = params.length > 0 ? params[0] : 2;
        ArrayList<String> companies = new ArrayList<String>();
        Map<String,Integer> foundProducts = new HashMap<String, Integer>();
        int recordsCount = getNumberOfProductsOnPage();
        int i = 1;
        while (i <= recordsCount) {
            Link productNameLink = new Link(By.xpath(String.format(productNameLocatorTemplate, i*2)),
                    String.format("Link for %1$s product name", i));
            String productName = productNameLink.getText();
            String company = productName.split(" ")[0];
            if (!companies.contains(company)) {
                companies.add(company);
                foundProducts.put(productName, i);
                if (foundProducts.values().size() == limit) {
                    break;
                }
            }
            i++;
        }
        return foundProducts;
    }
}
