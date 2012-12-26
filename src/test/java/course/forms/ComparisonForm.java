package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

public class ComparisonForm extends BaseForm {

    protected final String comparisonRatingRowCell = "//td[contains(text(), 'Рейтинг сравнения')]/..//td";
    protected final String comparisonValueCell = "//table[@id='rgMasterTable2']//tr[%1$s]//td[%2$s]";
    protected final String comparisonCriteriaCell = "//table[@id='rgMasterTable2']//tr[%1$s]//td[1]";
    protected final String specificTableRowLocator = "//table[@id='rgMasterTable2']//tr[%1$s]";
    protected final String genericTableRowLocator = "//table[@id='rgMasterTable2']//tr";
    protected final String productNameCell = "//table[@id='rgMasterTable2']//td[%1$s]//strong";


    public ComparisonForm() {
        super(By.xpath("//span[contains(text(), 'Сравнение моделей')]"), "Product Comparison Page");
    }

    /**
     * This method is used for printing out best product information
     * @param productIndex Index of the product
     */
    public void printBestProductInformation(final int productIndex) {
        printProductName(productIndex);
        int rowsCount = getNumberOfRows();
        for (int i = 1; i <= rowsCount; i++) {
            Label tableRow = new Label(By.xpath(String.format(specificTableRowLocator, i)),
                    String.format("%1$s row", i));
            if (tableRow.getAttribute("class").contains("pdsection")) {
                continue;
            }
            Label valueCell = new Label(By.xpath(String.format(comparisonValueCell, i, productIndex)),
                    String.format("Comparison value for %1$s product in %2$s row", productIndex, i));
            if (valueCell.getAttribute("class").contains("pdadv")) {
                Label criteria = new Label(By.xpath(String.format(comparisonCriteriaCell, i)), "Criteria Name");
                String value = valueCell.getText().trim();
                if (value.isEmpty()) {
                    value = "+";
                }
                info(String.format("%1$s - %2$s", criteria.getText().trim(), value));
            }
        }
    }

    /**
     * This method returns the number of rows in comparison table
     * @return Number of rows
     */
    private int getNumberOfRows() {
        Integer count = browser.getDriver().findElements(By.xpath(String.format(genericTableRowLocator))).size();
        // Removing 1 for the row with a button
        return count - 1;
    }

    /**
     * This method is used for printing product name
     * @param index Index of the product
     */
    private void printProductName(final int index) {
        Label productName = new Label(By.xpath(String.format(productNameCell, index)),
                String.format("Label for %1$s product name", index));
        info(String.format("Best product is: %1$s", productName.getText()));
    }

    /**
     * This method returns index of the best product in comparison
     * @return Index of the best product in comparison
     */
    public int findBestProduct() {
        int maxRating = 0;
        int bestIndex = 0;
        int count = getProductsCount();
        // Starting from 2 to omit the row title
        int i = 2;
        while (i <= count) {
            Label ratingLabel = new Label(By.xpath(String.format("%1$s[%2$s]", comparisonRatingRowCell, i)),
                    String.format("Label for %1$s product rating", i));

            int rating = Integer.parseInt(ratingLabel.getText());
            if (rating > maxRating) {
                bestIndex = i;
                maxRating = rating;
            }
            i++;
        }
        return bestIndex;
    }

    /**
     * This method is used for getting number of products in comparison
     * @return Number of products
     */
    private Integer getProductsCount() {
        Integer count = browser.getDriver().findElements(By.xpath(comparisonRatingRowCell)).size();
        // Dividing by 2 because Onliner displays copy of each field with "visibility: hidden"
        return count / 2;
    }
}
