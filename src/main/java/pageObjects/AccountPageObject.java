package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class AccountPageObject extends BasePage{
	WebDriver driver;

	public AccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
