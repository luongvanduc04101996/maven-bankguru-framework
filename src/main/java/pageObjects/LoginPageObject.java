package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToHereLink() {
		waitToElementClickAble(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputUserID(String userID) {
		waitToElementVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, userID);
	}

	public void inputPassword(String password) {
		waitToElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public void clickToLoginButton() {
		waitToElementClickAble(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

}
