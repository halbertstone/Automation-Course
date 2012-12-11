package course.forms;

import org.openqa.selenium.By;

public class BoardGamesSectionForm extends CatalogSectionForm {

    public BoardGamesSectionForm() {
        super(By.xpath("//h1[text()='Каталог настольных игр']"), "Catalog Board Games Section Page");
    }
}
