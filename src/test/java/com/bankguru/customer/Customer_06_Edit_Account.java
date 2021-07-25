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
import pageObjects.LoginPageObject;
import pageObjects.ManagerHomePageObject;
import pageObjects.RegisterPageObject;


public class Customer_06_Edit_Account extends BaseTest {
	
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
		accountPage.openListLinkPageByText(driver, "Edit Account");
		
	}
	
	@Test
	public void Edit_Account_01_Account_Number_Cannot_Be_Empty() {
		log.info("Edit_Account_01: Step 01 - Input empty to Account No");
		accountPage.inputTextBoxByNameAttribute(driver, "accountno", "");
		
		log.info("Edit_Account_01: Step 02 - Press tab key");
		accountPage.pressTabKeyAtAccountNoTextbox();
		
		log.info("Edit_Account_01: Step 03 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageAccountNo(), "Account Number must not be blank");
	}
	
	@Test
	public void Edit_Account_02_Account_Number_Must_Be_Numberic() {
		log.info("Edit_Account_02: Step 01 - Input not numberic to Account No: abc");
		accountPage.inputTextBoxByNameAttribute(driver, "accountno", "abc");
		
		log.info("Edit_Account_02: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageAccountNo(), "Characters are not allowed");
	}
	
	@Test
	public void Edit_Account_03_Account_Number_Cannot_Have_Special_Character() {
		log.info("Edit_Account_03: Step 01 - Input special character to Account No: 1234@@");
		accountPage.inputTextBoxByNameAttribute(driver, "accountno", "1234@@");
		
		log.info("Edit_Account_03: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageAccountNo(), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_Account_04_Account_Number_Cannot_Have_Space() {
		log.info("Edit_Account_04: Step 01 - Input space character to Account No: 12 3 4");
		accountPage.inputTextBoxByNameAttribute(driver, "accountno", "12 3 4");
		
		log.info("Edit_Account_04: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageAccountNo(), "Characters are not allowed");
	}
	
	@Test
	public void Edit_Account_05_Account_Number_Enter_Valid_Value() {
		log.info("Edit_Account_05: Step 01 - Input valid value to Account No: " + accountID);
		accountPage.inputTextBoxByNameAttribute(driver, "accountno", accountID);
		
		log.info("Edit_Account_05: Step 02 - Click to subbmit button");
		accountPage.clickToSubmitButtonAtEditAndDeleteAccount();
		
		log.info("Edit_Account_05: Step 03 - Verify login to edit form successful");
		verifyEquals(accountPage.getTextAccountFormSuccessful(), "Edit Account Entry Form");
		
		log.info("Edit_Account_05: Step 04 - Verify customerID and Balance(initial deposit)");
		verifyEquals(accountPage.getAttributeValueByNameOfTextbox(driver, "txtcid", "value"), customerID);
		verifyEquals(accountPage.getAttributeValueByNameOfTextbox(driver, "txtinitdep", "value"), initialDeposit);
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
	String userIDLogin, passwordLogin, emailRegister, name, address, city, state, pin, phone, email, password, birthDay, customerID, gender;
	String dayBirth, monthBirth, yearBirth, initialDeposit, accountID;
	FakerConfig fakeData;
}
