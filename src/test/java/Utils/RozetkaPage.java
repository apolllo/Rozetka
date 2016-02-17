package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RozetkaPage {
    private WebDriverUtils webdriver;
    public WebElement loginField;
    public WebElement passwordField;

    public RozetkaPage() {
        this("http://rozetka.com.ua");
    }
    public RozetkaPage(String url) {
        this("firefox", url);
    }
    public RozetkaPage(String browser, String url) {
        if (webdriver == null || browser != webdriver.browser) {
            webdriver = new WebDriverUtils(browser);
        }
        getRequest(url);
    }

    public void getRequest(String url) {
        webdriver.openPage(url);
    }

    public WebElement getLoginField() {
        this.loginField = webdriver.findElement(By.xpath("//input[@name='login']"));
        return this.loginField;
    }

    public void setLoginField(String login) {
        getLoginField().sendKeys(login);
    }

    public WebElement getPasswordField() {
        this.passwordField = webdriver.findElement(By.xpath("//input[@name='password']"));
        return this.passwordField;
    }

    public void setPasswordField(String password) {
        getPasswordField().sendKeys(password);
    }

    public void search(String searchText) {
        WebElement searchElement = webdriver.findElement(By.xpath("//div[@name='header-search-input-text-wrap']/input"));
        searchElement.sendKeys(searchText);
        searchElement.submit();
    }

    public String findLink(String searchText) {
        return webdriver.findResultWithText(searchText);
    }

    public String getTitle() {
        return webdriver.getPageTitle();
    }

    public String getFirstSearchResult() {
        String selector = "#block_with_search > div > div:nth-child(1) > div.g-i-list-middle-part > div.g-i-list-title > a";
        WebElement cssElement = webdriver.findElement(By.cssSelector(selector));
        return cssElement.getText();
    }

    public void login(String login, String password) {
        setLoginField(login);
        setPasswordField(password);
        this.passwordField.submit();
    }

    public void sleep(long seconds) {
        webdriver.sleep(seconds);
    }

    public void screen() {
        webdriver.saveScreenshot();
    }

    public void close() {
        webdriver.cleanup();
    }
}
