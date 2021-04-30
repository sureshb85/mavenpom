package com.selenium.pom;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import jdk.internal.org.jline.utils.Log;

public class BasePage {

	protected WebDriver driver = null;
	protected WebDriverWait wait = null;
	protected JavascriptExecutor jsExecutor = null;
	private static String screenshotName;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15);
		jsExecutor = ((JavascriptExecutor) driver);
	}

	public WebElement getElement(By by) {
		WebElement element = null;
		try {
			element = driver.findElement(by);
		} catch (NoSuchElementException e) {
			System.out.println("Element not found" + e.getMessage());
			Log.info("Element not found" + e.getMessage());
			Assert.fail("Element not found " + "<" + element.toString() + ">");
		}
		return element;
	}

	public void waitAndClickElement(WebElement element) throws InterruptedException, IOException {
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			System.out.println("clicked on element: " + "<" + element.toString() + ">");
			Log.info("clicked on element: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable click on WebElement: " + e.getMessage());
			Log.info("Unable click on WebElement: " + e.getMessage());
			Assert.fail("Unable to wait and click on the WebElement " + "<" + element.toString() + ">");
		}
	}

	public void waitAndClickElement(By by) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(by)).click();
			System.out.println("clicked on element: " + "<" + by.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to click on the element using the By locator: " + e.getMessage());
			Assert.fail("Unable to click on the element using the By locator: " + "<" + by.toString() + ">");
		}
	}

	public void actionMoveAndClick(WebElement element) {
		Actions actions = new Actions(driver);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			actions.moveToElement(element).click().build().perform();
			System.out.println("Clicked on the WebElement: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
			if (elementPresent == true) {
				actions.moveToElement(elementToClick).click().build().perform();
			}
		} catch (Exception e) {
			System.out.println("Unable to wait and click on the WebElement " + "<" + element.toString() + ">");
			Assert.fail("Unable to wait and click on the WebElement " + e.getMessage());
		}
	}

	public void actionMoveAndClick(By by) {
		Actions actions = new Actions(driver);
		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(by)).isEnabled();
			if (elementPresent == true) {
				WebElement elementToClick = driver.findElement(by);
				actions.moveToElement(elementToClick).click().build().perform();
				System.out.println("Clicked on the WebElement: " + "<" + by.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = driver.findElement(by);
			actions.moveToElement(elementToClick).click().build().perform();
		} catch (Exception e) {
			System.out.println("Unable to wait and click on the WebElement " + "<" + by.toString() + ">");
			Assert.fail("Unable to wait and click on the WebElement " + e.getMessage());
		}
	}

	public void sendKeysToWebElement(WebElement element, String textToSend) {
		try {
			this.WaitUntilWebElementIsVisible(element);
			element.clear();
			element.sendKeys(textToSend);
			System.out.println("entered text: '" + textToSend + "' to element: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println(
					"Unable to locate WebElement: " + "<" + element.toString() + "> and enter text: " + textToSend);
			Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
		}
	}

	public void sendKeysToWebElement(By by, String textToSend) {
		WebElement element = getElement(by);
		try {
			this.WaitUntilWebElementIsVisible(element);
			element.clear();
			element.sendKeys(textToSend);
			System.out.println("entered text: '" + textToSend + "' to element: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println(
					"Unable to locate WebElement: " + "<" + element.toString() + "> and enter text: " + textToSend);
			Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
		}
	}

	public boolean WaitUntilWebElementIsVisible(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("WebElement is visible using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean WaitUntilWebElementIsVisible(By element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			System.out.println("Element is visible using By locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementClickable(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("WebElement is clickable using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
			return false;
		}
	}

	public static String returnDateStamp(String fileExtension) {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
		return date;
	}

	public void captureScreenshot() throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenshotName = returnDateStamp(".jpg");
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
	}

	public String getCurrentURL() {
		try {
			String url = driver.getCurrentUrl();
			System.out.println("Current URL: " + url);
			return url;
		} catch (Exception e) {
			System.out.println("navigaton went wrongL, Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	public void jsPageScroll(int numb1, int numb2) {
		try {
			jsExecutor.executeScript("scroll(" + numb1 + "," + numb2 + ")");
			System.out.println("scrolled using locators to position: " + numb1 + ", " + numb2);
		} catch (Exception e) {
			System.out
					.println("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
			Assert.fail("Unable to scroll to WebElement: " + e.getMessage());
		}
	}

	public void waitAndclickElementUsingJS(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			jsExecutor.executeScript("arguments[0].click();", element);
			System.out.println("JS clicked on the WebElement: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement staleElement = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
			if (elementPresent == true) {
				jsExecutor.executeScript("arguments[0].click();", elementPresent);
			}
		} catch (NoSuchElementException e) {
			System.out.println("Unable to JS click on the WebElement: " + "<" + element.toString() + ">");
			Assert.fail("Unable to JS click on the WebElement: " + e.getMessage());
		}
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void loadUrl(String url) {
		driver.get(url);
	}

	public void selectTextFromDropdownList(By by, String textToSelect) {
		wait = new WebDriverWait(driver, 30);
		WebElement element = getElement(by);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			Select selectValue = new Select(element);
			selectValue.selectByVisibleText(textToSelect);
			System.out.println("selected text: " + textToSelect + "from dropdown: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println(
					"Unable to select text: " + textToSelect + "from dropdwon: " + "<" + element.toString() + ">");
			Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
		}
	}
}
