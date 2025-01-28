package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    @FindBy(id = "nav-hamburger-menu")
    WebElement hamburger_menu;

    @FindBy(xpath = "//div[text()='See all']")
    WebElement SeeAll;

    @FindBy(xpath = "//div[text()='Video Games']")
    WebElement videoGames;

    @FindBy(xpath = "(//ul//a[text()='All Video Games'])[1]")
    WebElement AllVideoGames;
    public HomePage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public void openAllMenu() {
        waitForPageToLoad();
        ClickOn(hamburger_menu);
    }

    public void navigateToVideoGames() {

        ClickOn(SeeAll);
        ClickOn(videoGames);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        jsClick(AllVideoGames);
    }
}