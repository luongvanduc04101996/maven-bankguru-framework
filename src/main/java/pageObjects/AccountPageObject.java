package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.AccountPageUI;

public class AccountPageObject extends BasePage{
	WebDriver driver;

	public AccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void pressTabKeyAtCustomerIDTextbox() {
		waitToElementVisible(driver, AccountPageUI.CUSTOMER_ID_TEXTBOX);
		sendKeyBoardToElement(driver, AccountPageUI.CUSTOMER_ID_TEXTBOX, Keys.TAB);
	}
	
	public String getErrorMessageCustomerID() {
		waitToElementVisible(driver, AccountPageUI.MESSAGE_AT_CUSTOMER_ID);
		return getElementText(driver, AccountPageUI.MESSAGE_AT_CUSTOMER_ID);
	}
	
	public void pressTabKeyAtInitialDepositTextbox() {
		waitToElementVisible(driver, AccountPageUI.INITIAL_DEPOSIT_TEXTBOX);
		sendKeyBoardToElement(driver, AccountPageUI.INITIAL_DEPOSIT_TEXTBOX, Keys.TAB);
	}

	public void pressTabKeyAtAccountNoTextbox() {
		waitToElementVisible(driver, AccountPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyBoardToElement(driver, AccountPageUI.ACCOUNT_NO_TEXTBOX, Keys.TAB);
	}
	
	public String getErrorMessageInitialDeposit() {
		waitToElementVisible(driver, AccountPageUI.MESSAGE_AT_INITIAL_DEPOSIT);
		return getElementText(driver, AccountPageUI.MESSAGE_AT_INITIAL_DEPOSIT);
	}

	public String getErrorMessageAccountNo() {
		waitToElementVisible(driver, AccountPageUI.MESSAGE_AT_ACOUNT_NO);
		return getElementText(driver, AccountPageUI.MESSAGE_AT_ACOUNT_NO);
	}
	
	public void clickToSubmitButtonAtNewAccount() {
		waitToElementVisible(driver, AccountPageUI.SUBMIT_BUTTON);
		clickToElement(driver, AccountPageUI.SUBMIT_BUTTON);
	}

	public void clickToSubmitButtonAtEditAndDeleteAccount() {
		waitToElementVisible(driver, AccountPageUI.SUBMIT_BUTTON_AT_EDIT_AND_DELETE_ACCOUNT);
		clickToElement(driver, AccountPageUI.SUBMIT_BUTTON_AT_EDIT_AND_DELETE_ACCOUNT);
	}

	public String getTextAccountGeneratedSuccessfully() {
		waitToElementVisible(driver, AccountPageUI.CREATED_ACCOUNT_SUCCESSFUL_TEXT);
		return getElementText(driver, AccountPageUI.CREATED_ACCOUNT_SUCCESSFUL_TEXT);
	}

	public String getTextAccountFormSuccessful() {
		waitToElementVisible(driver, AccountPageUI.EDIT_ACCOUNT_SUCCESSFUL_TEXT);
		return getElementText(driver, AccountPageUI.EDIT_ACCOUNT_SUCCESSFUL_TEXT);
	}

}
