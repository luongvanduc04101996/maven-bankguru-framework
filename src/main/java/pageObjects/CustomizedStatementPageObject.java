package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class CustomizedStatementPageObject extends BasePage{
	WebDriver driver;

	public CustomizedStatementPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
