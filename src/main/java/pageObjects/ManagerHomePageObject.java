package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class ManagerHomePageObject extends BasePage{
	WebDriver driver;

	public ManagerHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
}
