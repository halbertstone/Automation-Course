package course.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Link;

public class CatalogHomeForm extends BaseForm {

    private Link boardGames = new Link(By.partialLinkText("Настольные игр"), "Board Games");

    public CatalogHomeForm() {
        super(By.className("b-catalogmain"), "Onliner Catalog Page");
    }

    /**
     * This method opens a Board Games catalog section
     * @return Object with opened section page
     */
    public BoardGamesSectionForm openBoardGamesSection() {
        boardGames.clickAndWait();
        return new BoardGamesSectionForm();
    }
}