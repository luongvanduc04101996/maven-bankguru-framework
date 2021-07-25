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


public class Customer_05_New_Account extends BaseTest {
	
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
		
		customerID = customerPage.getRegisteredCustomerID();
	}
	
	@Test
	public void New_Account_01_CustomerID_Cannot_Be_Empty() {
		log.info("New_Account_01: Step 01 - Open New Account page");
		customerPage.openListLinkPageByText(driver, "New Account");
		accountPage = PageGeneratorManager.getAccountPage(driver);
		
		
		log.info("New_Account_01: Step 02 - Input empty to customer id");
		accountPage.inputTextBoxByNameAttribute(driver, "cusid", "");
		
		log.info("New_Account_01: Step 03 - Press tab key");
		accountPage.pressTabKeyAtCustomerIDTextbox();
		
		log.info("New_Account_01: Step 04 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageCustomerID(), "Customer ID is required");
	}
	
	@Test
	public void New_Account_02_CustomerID_Must_Be_Numberic() {
		log.info("New_Account_02: Step 01 - Input not numberic to customer id with value: 123abc");
		accountPage.inputTextBoxByNameAttribute(driver, "cusid", "123abc");
		
		log.info("New_Account_02: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageCustomerID(), "Characters are not allowed");
	}
	
	@Test
	public void New_Account_03_CustomerID_Cannot_Have_Special_Character() {
		log.info("New_Account_03: Step 01 - Input special character to customer id with value: 123abc@");
		accountPage.inputTextBoxByNameAttribute(driver, "cusid", "123abc@");
		
		log.info("New_Account_03: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageCustomerID(), "Special characters are not allowed");
	}
	
	@Test
	public void New_Account_04_CustomerID_First_Character_Cannot_Have_Space() {
		log.info("New_Account_04: Step 01 - Input first character is space to customer id with value:  123");
		accountPage.inputTextBoxByNameAttribute(driver, "cusid", " 123");
		
		log.info("New_Account_04: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageCustomerID(), "First character can not have space");
	}
	
	@Test
	public void New_Account_05_CustomerID_Cannot_Have_Space() {
		log.info("New_Account_05: Step 01 - Input space to customer id with value: 1 2 3");
		accountPage.inputTextBoxByNameAttribute(driver, "cusid", "1 2 3");
		
		log.info("New_Account_05: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageCustomerID(), "Characters are not allowed");
	}
	
	@Test
	public void New_Account_06_Initial_Deposit_Cannot_Be_Empty() {
		log.info("New_Account_06: Step 01 - Input empty to initial deposit text box");
		accountPage.inputTextBoxByNameAttribute(driver, "inideposit", "");
		
		log.info("New_Account_06: Step 02 - Press tab key");
		accountPage.pressTabKeyAtInitialDepositTextbox();
		
		log.info("New_Account_06: Step 03 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageInitialDeposit(), "Initial Deposit must not be blank");
	}
	
	@Test
	public void New_Account_07_Initial_Deposit_Must_Be_Numberic() {
		log.info("New_Account_07: Step 01 - Input not numberic to initial deposit with value: 123abc");
		accountPage.inputTextBoxByNameAttribute(driver, "inideposit", "123abc");
		
		log.info("New_Account_07: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageInitialDeposit(), "Characters are not allowed");
	}
	
	@Test
	public void New_Account_08_Initial_Deposit_Cannot_Have_Special_Character() {
		log.info("New_Account_08: Step 01 - Input special character to initial deposit with value: 123abc@");
		accountPage.inputTextBoxByNameAttribute(driver, "inideposit", "123abc@");
		
		log.info("New_Account_08: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageInitialDeposit(), "Special characters are not allowed");
	}
	
	@Test
	public void New_Account_09_Initial_Deposit_First_Character_Cannot_Have_Space() {
		log.info("New_Account_09: Step 01 - Input first character is space to initial deposit with value:  123");
		accountPage.inputTextBoxByNameAttribute(driver, "inideposit", " 123");
		
		log.info("New_Account_09: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageInitialDeposit(), "First character can not have space");
	}
	
	@Test
	public void New_Account_10_Initial_Deposit_Cannot_Have_Space() {
		log.info("New_Account_10: Step 01 - Input space to initial deposit with value: 1 2 3");
		accountPage.inputTextBoxByNameAttribute(driver, "inideposit", "1 2 3");
		
		log.info("New_Account_10: Step 02 - Verify error message displayed");
		verifyEquals(accountPage.getErrorMessageInitialDeposit(), "Characters are not allowed");
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
	String dayBirth, monthBirth, yearBirth;
	FakerConfig fakeData;
}
