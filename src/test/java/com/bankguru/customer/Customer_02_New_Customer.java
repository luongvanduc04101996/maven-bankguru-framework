package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.FakerConfig;
import commons.PageGeneratorManager;
import pageObjects.CustomerPageObject;
import pageObjects.LoginPageObject;
import pageObjects.ManagerHomePageObject;
import pageObjects.RegisterPageObject;


public class Customer_02_New_Customer extends BaseTest {

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriverAndOpenURLByWebDriverManager(browserName, url);
		
		fakeData = FakerConfig.getFakeData();
		email = fakeData.getEmailAddress();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		log.info("Precondition: Click to here link to register");
		registerPage = loginPage.clickToHereLink();
		
		log.info("Precondition: Input email ID textbox");
		registerPage.inputEmailID(email);
		
		log.info("Precondition: Click to submit button");
		registerPage.clickToSubmitButton();
		
		log.info("Precondition: Get user id to login");
		userID = registerPage.getUserID();
		
		log.info("Precondition: Get password to login");
		password =registerPage.getPassword();
		
		log.info("Precondition: Open url login page");
		loginPage = registerPage.openUrlLoginPage();
		
		log.info("Precondition: Input user ID");
		loginPage.inputUserID(userID);
		
		log.info("Precondition: Input password");
		loginPage.inputPassword(password);
		
		log.info("Precondition: Click to login button");
		loginPage.clickToLoginButton();
		managerHomePage = PageGeneratorManager.getManagerHomePage(driver);
		
	}

	@Test
	public void NewCustomer_19_Pin_Must_Be_Numberic() {
		log.info("NewCustomer_19 - Step 01: Click to New Customer page");
		managerHomePage.openListLinkPageByText(driver, "New Customer");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
		log.info("NewCustomer_19 - Step 02: Input not numberic to pin textbox with value: 123duc");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "123duc");
			
		log.info("NewCustomer_19 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "Characters are not allowed");
	}
	
	@Test
	public void NewCustomer_20_Pin_Must_Have_6_digits() {
		log.info("NewCustomer_20 - Step 01: Input numeric to pin textbox with value : 123");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "123");
		
		log.info("NewCustomer_20 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "PIN Code must have 6 Digits");	
	}
	
	@Test
	public void NewCustomer_21_Pin_Cannot_Have_Special_Characters() {
		log.info("NewCustomer_21 - Step 01: Input special characters to pin textbox with value : 123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "123@!#");
		
		log.info("NewCustomer_21 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "Special characters are not allowed");
	}
	
	@Test
	public void NewCustomer_22_Pin_Cannot_Have_First_Character_As_Blank_Space() {
		log.info("NewCustomer_22 - Step 01: Input space at first character to pin textbox with value :  123");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", " 123");
		
		log.info("NewCustomer_22 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "First character can not have space");
	}
	
	@Test
	public void NewCustomer_23_Pin_Cannot_Have_Space() {
		log.info("NewCustomer_23 - Step 01: Input space to pin textbox with value: 12 45");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "12 45");	
		
		log.info("NewCustomer_23 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "Characters are not allowed");
	}
	
	@Test
	public void NewCustomer_24_Mobile_Number_Cannot_Have_First_Character_As_Blank_Space() {
		log.info("NewCustomer_24 - Step 01: Input space at first character to mobile number textbox with value :  123");
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", " 123");
		
		log.info("NewCustomer_24 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageMobileNumber(), "First character can not have space");
	}
	
	@Test
	public void NewCustomer_25_Mobile_Number_Cannot_Have_Space() {
		log.info("NewCustomer_25 - Step 01: Input space to mobile number textbox with value: 12 45");
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", "12 45");	
		
		log.info("NewCustomer_25 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageMobileNumber(), "Characters are not allowed");
	}
	
	@Test
	public void NewCustomer_26_Mobile_Number_Cannot_Have_Special_Characters() {
		log.info("NewCustomer_26 - Step 01: Input special characters to mobile number textbox with value : 123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", "123@!#");
		
		log.info("NewCustomer_26 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageMobileNumber(), "Special characters are not allowed");
	}
	
	@Test
	public void NewCustomer_27_Email_Must_Be_In_Correct_Format() {
		log.info("NewCustomer_27 - Step 01: Input invalid format to email textbox with value : duc@gmail");
		customerPage.inputTextBoxByNameAttribute(driver, "emailid", "duc@gmail");
		
		log.info("NewCustomer_27 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageEmail(), "Email-ID is not valid");
	}
	
	@Test
	public void NewCustomer_28_Email_Not_Contain_Space() {
		log.info("NewCustomer_28 - Step 01: Input space to email textbox with value : du c@gmail");
		customerPage.inputTextBoxByNameAttribute(driver, "emailid", "du c@gmail");
		
		log.info("NewCustomer_28 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageEmail(), "Email-ID is not valid");
	}
	
	@Test
	public void NewCustomer_29_Check_All_Field_As_Requirement() {
		log.info("NewCustomer_29 - Step 01: Check all field are displayed");
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "Customer Name"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "Gender"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "Date of Birth"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "Address"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "City"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "State"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "PIN"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "Mobile Number"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "E-mail"));
		verifyTrue(customerPage.isDisplayedWithTDFieldByText(driver, "Password"));
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
	String userID, password, email;
	FakerConfig fakeData;

}
