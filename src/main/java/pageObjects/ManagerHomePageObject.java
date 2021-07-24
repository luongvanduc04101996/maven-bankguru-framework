package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.ManagerHomePageUI;

public class ManagerHomePageObject extends BasePage{
	WebDriver driver;

	public ManagerHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTextHeadingWelcome() {
		waitToElementVisible(driver, ManagerHomePageUI.HEADING_WELCOME);
		return getElementText(driver, ManagerHomePageUI.HEADING_WELCOME);
	}
}
