package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputEmailID(String email) {
		waitToElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public void clickToSubmitButton() {
		waitToElementClickAble(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserID() {
		waitToElementVisible(driver, RegisterPageUI.USER_ID_VALUE);
		return getElementText(driver, RegisterPageUI.USER_ID_VALUE);
	}

	public String getPassword() {
		waitToElementVisible(driver, RegisterPageUI.PASSWORD_VALUE);
		return getElementText(driver, RegisterPageUI.PASSWORD_VALUE);
	}

	public LoginPageObject openUrlLoginPage() {
		openPageURL(driver, GlobalConstants.LOGIN_PAGE);
		return PageGeneratorManager.getLoginPage(driver);
	}

}
