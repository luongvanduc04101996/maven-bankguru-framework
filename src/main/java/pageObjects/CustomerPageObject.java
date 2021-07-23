package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage{
	WebDriver driver;

	public CustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getMessageEmptyName() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_NAME_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_NAME_TEXTBOX);
	}

	public void pressTabKey() {
		waitToElementVisible(driver, CustomerPageUI.NAME_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.NAME_TEXTBOX, Keys.TAB);
	}
}
