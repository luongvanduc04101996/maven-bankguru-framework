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
	public void pressTabKeyAtNameTextbox() {
		waitToElementVisible(driver, CustomerPageUI.NAME_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.NAME_TEXTBOX, Keys.TAB);
	}

	public void pressTabKeyAtAddressArea() {
		waitToElementVisible(driver, CustomerPageUI.ADDRESS_TEXT_AREA);
		sendKeyBoardToElement(driver, CustomerPageUI.ADDRESS_TEXT_AREA, Keys.TAB);
	}

	public void pressTabKeyAtCityTextbox() {
		waitToElementVisible(driver, CustomerPageUI.CITY_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.CITY_TEXTBOX, Keys.TAB);		
	}
	
	public void pressTabKeyAtStateTextbox() {
		waitToElementVisible(driver, CustomerPageUI.STATE_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.STATE_TEXTBOX, Keys.TAB);	
	}
	
	public void pressTabKeyAtPINTextbox() {
		waitToElementVisible(driver, CustomerPageUI.PIN_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.PIN_TEXTBOX, Keys.TAB);	
	}
	
	public void pressTabKeyAtMobileNumberTextbox() {
		waitToElementVisible(driver, CustomerPageUI.MOBILE_NUMBER_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.MOBILE_NUMBER_TEXTBOX, Keys.TAB);	
	}
	
	public void pressTabKeyAtEmailTextbox() {
		waitToElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.EMAIL_TEXTBOX, Keys.TAB);	
	}
	
	public void pressTabKeyAtPasswordTextbox() {
		waitToElementVisible(driver, CustomerPageUI.PASSWORD_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.PASSWORD_TEXTBOX, Keys.TAB);	
	}
	
	public void pressTabKeyAtCustomerIDTextbox() {
		waitToElementVisible(driver, CustomerPageUI.CUSTOMER_ID_TEXTBOX);
		sendKeyBoardToElement(driver, CustomerPageUI.CUSTOMER_ID_TEXTBOX, Keys.TAB);	
	}
	
	public void inputToAddressArea(String value) {
		waitToElementVisible(driver, CustomerPageUI.ADDRESS_TEXT_AREA);
		sendkeyToElement(driver, CustomerPageUI.ADDRESS_TEXT_AREA, value);
	}
	
	public String getErrorMessageName() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_NAME_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_NAME_TEXTBOX);
	}

	public String getErrorMessageAddress() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_ADDRESS_TEXT_AREA);
		return getElementText(driver, CustomerPageUI.MESSAGE_ADDRESS_TEXT_AREA);
	}

	public String getErrorMessageCity() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_CITY_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_CITY_TEXTBOX);
	}
	
	public String getErrorMessageState() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_STATE_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_STATE_TEXTBOX);
	}
	
	public String getErrorMessagePin() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_PIN_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_PIN_TEXTBOX);
	}
	
	public String getErrorMessageMobileNumber() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_MOBILE_NUMBER_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_MOBILE_NUMBER_TEXTBOX);
	}
	
	public String getErrorMessageEmail() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_EMAIL_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_EMAIL_TEXTBOX);
	}
	
	public String getErrorMessagePassword() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_PASSWORD_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_PASSWORD_TEXTBOX);
	}

	public String getErrorMessageCustomerID() {
		waitToElementVisible(driver, CustomerPageUI.MESSAGE_CUSTOMER_ID_TEXTBOX);
		return getElementText(driver, CustomerPageUI.MESSAGE_CUSTOMER_ID_TEXTBOX);
	}
	
	public void selectGenderMaleRadio() {
		waitToElementVisible(driver, CustomerPageUI.GENDER_MALE_RADIO);
		checkToCheckBoxOrRadio(driver, CustomerPageUI.GENDER_MALE_RADIO);
	}

	public void clickToSubmitButton() {
		waitToElementClickAble(driver, CustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, CustomerPageUI.SUBMIT_BUTTON);
	}
	public String getCustomerRegisterSuccessfulMessage() {
		waitToElementVisible(driver, CustomerPageUI.CUSTOMER_REGISTERED_SUCCESSFUL_MESSAGE);
		return getElementText(driver, CustomerPageUI.CUSTOMER_REGISTERED_SUCCESSFUL_MESSAGE);
	}
	public String getRegisteredCustomerID() {
		waitToElementVisible(driver, CustomerPageUI.CUSTOMER_ID_VALUE);
		return getElementText(driver, CustomerPageUI.CUSTOMER_ID_VALUE);
	}
	public void clickToSubmitButtonAtEditAndDeleteCustomer() {
		waitToElementClickAble(driver, CustomerPageUI.SUBMIT_BUTTON_AT_EDIT_CUSTOMER);
		clickToElement(driver, CustomerPageUI.SUBMIT_BUTTON_AT_EDIT_CUSTOMER);
	}
	public String getMessageUpdatedSuccessful() {
		waitToElementVisible(driver, CustomerPageUI.CUSTOMER_UPDATED_SUCCESSFUL_MESSAGE);
		return getElementText(driver, CustomerPageUI.CUSTOMER_UPDATED_SUCCESSFUL_MESSAGE);
	}

}
