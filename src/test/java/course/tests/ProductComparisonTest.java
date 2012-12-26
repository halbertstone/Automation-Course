package course.tests;

import course.forms.*;
import webdriver.BaseTest;
import webdriver.controller.UserType;
import webdriver.controller.UsersController;
import java.util.Map;

public class ProductComparisonTest extends BaseTest {

    public UsersController.User user = UsersController.getInstance().getUserByType(UserType.USER);

    public void runTest() {
        //1
        LogStep();
        HomeForm homeForm = new HomeForm();
        homeForm.login.clickAndWait();

        //2
        LogStep();
        LoginForm loginForm = new LoginForm();
        homeForm = loginForm.correctLogin(user.getLogin(), user.getPassword());

        //3
        LogStep();
        CatalogHomeForm catalogForm = homeForm.openCatalog();

        //4
        LogStep();
        PhonesSectionForm sectionForm = catalogForm.openPhonesSection();

        //5
        LogStep();
        Map<String,Integer> foundProducts = sectionForm.getRecordsWithDifferentNames();
        for(Integer id : foundProducts.values()) {
            sectionForm.checkComparisonCheckbox(id);
        }

        //6
        LogStep();
        ComparisonForm comparisonForm = sectionForm.openComparisonPage();

        //7
        LogStep();
        comparisonForm.printBestProductInformation(comparisonForm.findBestProduct());
    }

    @Override
    public boolean shouldAnalys() {
        return false;
    }

    @Override
    public void invokeAnalys(Throwable exc, String bodyText) {
    }
}
