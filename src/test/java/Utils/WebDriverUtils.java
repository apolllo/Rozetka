package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    private static WebDriver driver;
    public String browser;
    private static String projectDir = System.getProperty("user.dir");

    public WebDriverUtils(String browse) {
        browser = browse;
        switch (browse) {
            case "phantom":
                //TODO: add PhantomJS dependency
                //driver = new PhantomJSDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            default:
                driver = new FirefoxDriver();
                break;
        }
    }

    void openPage(String url) {
        driver.get(url);
    }

    String getPageTitle(){
        return driver.getTitle();
    }

    String findResultWithText(String searchText) {
        WebElement element = driver.findElement(By.partialLinkText(searchText));
        System.out.println("Found by link text: " + element.getText());
        return element.getText();
    }

    WebElement findElement(By selector) {
        return driver.findElement(selector);
    }

    void saveScreenshot() {
        new File(projectDir + "/temp").mkdir();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        long date = new Date().getTime();
        try {
            FileUtils.copyFile(scrFile, new File(projectDir + "/temp/screenshot" + date + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sleep(long seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void cleanup() {
        driver.quit();
    }
}
