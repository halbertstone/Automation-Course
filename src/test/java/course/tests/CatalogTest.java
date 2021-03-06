package course.tests;

import course.forms.BoardGamesSectionForm;
import course.forms.CatalogHomeForm;
import course.forms.HomeForm;
import course.forms.LoginForm;
import webdriver.BaseTest;
import webdriver.controller.UserType;
import webdriver.controller.UsersController;

public class CatalogTest extends BaseTest {

    public UsersController.User user = UsersController.getInstance().getUserByType(UserType.USER);
    public String descriptionRegex = "^активная.*";

    public void runTest() {
        LogStep();
        HomeForm homeForm = new HomeForm();
        homeForm.login.clickAndWait();

        LogStep();
        LoginForm loginForm = new LoginForm();
        loginForm.correctLogin(user.getLogin(), user.getPassword());

        LogStep();
        CatalogHomeForm catalogForm = homeForm.openCatalog();

        LogStep();
        BoardGamesSectionForm sectionForm = catalogForm.openBoardGamesSection();

        LogStep();
        info(String.format("First item name: %1$s", sectionForm.getFirstItemName()));

        LogStep();
        info(String.format("Finding products with description that matches \"%1$s\"", descriptionRegex));
        sectionForm.printRecordsWithDescriptionRegex(descriptionRegex);
    }

    @Override
    public boolean shouldAnalys() {
        return false;
    }

    @Override
    public void invokeAnalys(Throwable exc, String bodyText) {
    }
}
