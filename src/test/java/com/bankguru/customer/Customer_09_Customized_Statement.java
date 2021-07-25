package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.FakerConfig;
import commons.PageGeneratorManager;
import pageObjects.AccountPageObject;
import pageObjects.CustomerPageObject;
import pageObjects.CustomizedStatementPageObject;
import pageObjects.LoginPageObject;
import pageObjects.ManagerHomePageObject;
import pageObjects.RegisterPageObject;


public class Customer_09_Customized_Statement extends BaseTest {
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriverAndOpenURLByWebDriverManager(browserName, url);
		
		fakeData = FakerConfig.getFakeData();
		emailRegister = fakeData.getEmailAddress();
		name = fakeData.getFirstname() + " " + fakeData.getLastname();
		address = fakeData.getAddress();
		city = fakeData.getCityName();
		state = "Wasington";
		pin = "041096";
		phone = "0367986845";
		dayBirth = "22";	monthBirth = "12";	yearBirth = "1996";
		birthDay = monthBirth + "/" + dayBirth + "/" + yearBirth;
		gender = "male";	
		email = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		initialDeposit = "10000000";
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		log.info("Precondition: Click to here link to register");
		registerPage = loginPage.clickToHereLink();
		
		log.info("Precondition: Input email ID textbox");
		registerPage.inputEmailID(emailRegister);
		
		log.info("Precondition: Click to submit button");
		registerPage.clickToSubmitButton();
		
		log.info("Precondition: Get user id to login");
		userIDLogin = registerPage.getUserID();
		
		log.info("Precondition: Get password to login");
		passwordLogin =registerPage.getPassword();
		
		log.info("Precondition: Open url login page");
		loginPage = registerPage.openUrlLoginPage();
		
		log.info("Precondition: Input user ID");
		loginPage.inputUserID(userIDLogin);
		
		log.info("Precondition: Input password");
		loginPage.inputPassword(passwordLogin);
		
		log.info("Precondition: Click to login button");
		loginPage.clickToLoginButton();
		managerHomePage = PageGeneratorManager.getManagerHomePage(driver);
		
		log.info("Precondition: Click to New Customer page");
		managerHomePage.openListLinkPageByText(driver, "New Customer");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
		log.info("Precondition: Create new customer successful");
		customerPage.inputTextBoxByNameAttribute(driver, "name", name);
		customerPage.selectGenderMaleRadio();
		customerPage.inputTextBoxByNameAttribute(driver, "dob", birthDay);
		customerPage.inputToAddressArea(address);
		customerPage.inputTextBoxByNameAttribute(driver, "city", city);
		customerPage.inputTextBoxByNameAttribute(driver, "state", state);
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", pin);
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", phone);
		customerPage.inputTextBoxByNameAttribute(driver, "emailid", email);
		customerPage.inputTextBoxByNameAttribute(driver, "password", password);
		customerPage.clickToSubmitButton();
		verifyEquals(customerPage.getCustomerRegisterSuccessfulMessage(),"Customer Registered Successfully!!!");
		
		log.info("Precondition: Get customer ID");
		customerID = customerPage.getRegisteredCustomerID();
		
		log.info("Precondition: Go to New Account page");
		customerPage.openListLinkPageByText(driver, "New Account");
		accountPage = PageGeneratorManager.getAccountPage(driver);
		
		log.info("Precondition: Create new account successful with customerID = " + customerID);
		accountPage.inputTextBoxByNameAttribute(driver, "cusid", customerID);
		accountPage.inputTextBoxByNameAttribute(driver, "inideposit", initialDeposit);
		accountPage.clickToSubmitButtonAtNewAccount();
		verifyEquals(accountPage.getTextAccountGeneratedSuccessfully(), "Account Generated Successfully!!!");
		
		log.info("Precondition: Get account ID");
		accountID = accountPage.getTextAtUpdatedCustomer(driver, "Account ID");
		
		log.info("Precondition: Open Edit Account page");
		accountPage.openListLinkPageByText(driver, "Customised Statement");
		customizedStatementPage = PageGeneratorManager.getCustomizedStatementPage(driver);
	}
	
	@Test
	public void CustomizedState_01_AccountNo_Cannot_Be_Empty() {
		log.info("CustomizedState_01: Step 01 - Input empty to AccountNo");
		customizedStatementPage.inputTextBoxByNameAttribute(driver, "accountno", "");
		
		log.info("CustomizedState_01: Step 02 - Press tab key");
		customizedStatementPage.pressTabToTextboxByName(driver, "accountno");
		
		log.info("CustomizedState_01: Step 03 - Verify error message displayed");
		verifyEquals(customizedStatementPage.getTextErrorMessageAtTextboxByID(driver, "message2"), "Account Number must not be blank");
	}
	
	@Test
	public void CustomizedState_02_AccountNo_Must_Be_Numberic() {
		log.info("CustomizedState_02: Step 01 - Input not numberic to AccountNo with value: 123abc");
		customizedStatementPage.inputTextBoxByNameAttribute(driver, "accountno", "123abc");
		
		log.info("CustomizedState_02: Step 02 - Verify error message displayed");
		verifyEquals(customizedStatementPage.getTextErrorMessageAtTextboxByID(driver, "message2"), "Characters are not allowed");
	}
	
	@Test
	public void CustomizedState_03_AccountNo_Cannot_Have_Special_Character() {
		log.info("CustomizedState_03: Step 01 - Input special character to AccountNo with value: 123abc@");
		customizedStatementPage.inputTextBoxByNameAttribute(driver, "accountno", "123abc@");
		
		log.info("CustomizedState_03: Step 02 - Verify error message displayed");
		verifyEquals(customizedStatementPage.getTextErrorMessageAtTextboxByID(driver, "message2"), "Special characters are not allowed");
	}
	
	@Test
	public void CustomizedState_04_AccountNo_Cannot_Have_Space() {
		log.info("CustomizedState_04: Step 01 - Input space to AccountNo with value: 1 2 3");
		customizedStatementPage.inputTextBoxByNameAttribute(driver, "accountno", "1 2 3");
		
		log.info("CustomizedState_04: Step 02 - Verify error message displayed");
		verifyEquals(customizedStatementPage.getTextErrorMessageAtTextboxByID(driver, "message2"), "Characters are not allowed");
	}
	
	@Test
	public void CustomizedState_05_Minimum_Transaction_Value_Cannot_Be_Empty() {
		log.info("CustomizedState_05: Step 01 - Input empty to Minimum Transaction Value");
		customizedStatementPage.inputTextBoxByNameAttribute(driver, "amountlowerlimit", "");
		
		log.info("CustomizedState_05: Step 02 - Press tab key");
		customizedStatementPage.pressTabToTextboxByName(driver, "amountlowerlimit");
		
		log.info("CustomizedState_05: Step 03 - Verify error message displayed");
		verifyEquals(customizedStatementPage.getTextErrorMessageAtTextboxByID(driver, "message12"), "Account Number must not be blank");
	}
	
	@Test
	public void CustomizedState_06_Minimum_Transaction_Value_Must_Be_Numberic() {
		log.info("CustomizedState_06: Step 01 - Input not numberic to Minimum Transaction Value with value: 123abc");
		customizedStatementPage.inputTextBoxByNameAttribute(driver, "amountlowerlimit", "123abc");
		
		log.info("CustomizedState_06: Step 02 - Verify error message displayed");
		verifyEquals(customizedStatementPage.getTextErrorMessageAtTextboxByID(driver, "message12"), "Characters are not allowed");
	}
	
	@Test
	public void CustomizedState_07_Minimum_Transaction_Value_Cannot_Have_Special_Character() {
		log.info("CustomizedState_07: Step 01 - Input special character to Minimum Transaction Value with value: 123abc@");
		customizedStatementPage.inputTextBoxByNameAttribute(driver, "amountlowerlimit", "123abc@");
		
		log.info("CustomizedState_07: Step 02 - Verify error message displayed");
		verifyEquals(customizedStatementPage.getTextErrorMessageAtTextboxByID(driver, "message12"), "Special characters are not allowed");
	}
	
	@Test
	public void CustomizedState_08_Minimum_Transaction_Value_Cannot_Have_Space() {
		log.info("CustomizedState_08: Step 01 - Input space to Minimum Transaction Value with value: 1 2 3");
		customizedStatementPage.inputTextBoxByNameAttribute(driver, "amountlowerlimit", "1 2 3");
		
		log.info("CustomizedState_08: Step 02 - Verify error message displayed");
		verifyEquals(customizedStatementPage.getTextErrorMessageAtTextboxByID(driver, "message12"), "Characters are not allowed");
	}
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
	
	WebDriver driver;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	ManagerHomePageObject managerHomePage;
	CustomerPageObject customerPage;
	AccountPageObject accountPage;
	CustomizedStatementPageObject customizedStatementPage;
	String userIDLogin, passwordLogin, emailRegister, name, address, city, state, pin, phone, email, password, birthDay, customerID, gender;
	String dayBirth, monthBirth, yearBirth,initialDeposit, accountID;
	FakerConfig fakeData;
}
