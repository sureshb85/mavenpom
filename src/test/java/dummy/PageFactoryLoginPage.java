package dummy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageFactoryLoginPage {

	WebDriver driver = null;

	@FindBy(xpath = "//li/span//a[text()='Sign in']")
	@CacheLookup
	public WebElement signInLink;

	@FindAll({ @FindBy(how = How.ID, using = "userid"), @FindBy(name = "userid"),
			@FindBy(xpath = "//input[@name='userid']") })
	@CacheLookup
	public WebElement usernameText;

	@FindBy(how = How.CSS, using = "#signin-continue-btn")
	@CacheLookup
	public WebElement continueButton;

	@FindBy(how = How.CSS, using = "#pass")
	@CacheLookup
	public WebElement passwordText;

	PageFactoryLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSignInlink() {
		signInLink.click();
	}

	public void doLogin(String user, String pass) {
		usernameText.sendKeys(user);
		continueButton.click();
		passwordText.sendKeys(pass);

	}
}
