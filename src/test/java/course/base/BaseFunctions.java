package course.base;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;

public class BaseFunctions {

    /**
     * Maximizing the Browser window
     */
    public static void windowMaximise(RemoteWebDriver driver) {
        driver.manage().window().setPosition(new org.openqa.selenium.Point(0, 0));

        String JS_GET_MAX_WIDTH =
                "return (window.screen ? window.screen.availWidth : arguments[0]);";
        String JS_GET_MAX_HEIGHT =
                "return (window.screen ? window.screen.availHeight : arguments[0]);";

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = ((Long) driver.executeScript(
                JS_GET_MAX_WIDTH,
                (int) toolkit.getScreenSize().getWidth())).intValue();
        int height = ((Long) driver.executeScript(
                JS_GET_MAX_HEIGHT,
                (int) toolkit.getScreenSize().getHeight())).intValue();
        org.openqa.selenium.Dimension screenResolution =
                new org.openqa.selenium.Dimension(width, height);
        driver.manage().window().setSize(screenResolution);
    }
}
