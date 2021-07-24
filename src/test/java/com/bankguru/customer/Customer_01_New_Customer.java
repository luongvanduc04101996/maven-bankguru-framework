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


public class Customer_01_New_Customer extends BaseTest {
	
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
	public void NewCustomer_01_Name_Cannot_Be_Empty() {
		log.info("NewCustomer_01 - Step 01: Click to New Customer page");
		managerHomePage.openListLinkPageByText(driver, "New Customer");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
		log.info("NewCustomer_01 - Step 02: Input empty to name textbox");
		customerPage.inputTextBoxByNameAttribute(driver, "name", "");
		
		log.info("NewCustomer_01 - Step 03: Press to Tab key");
		customerPage.pressTabKeyAtNameTextbox();
		
		log.info("NewCustomer_01 - Step 04: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageName(), "Customer name must not be blank");
	}
	
	@Test
	public void NewCustomer_02_Name_Cannot_Be_Numeric() {
		log.info("NewCustomer_02 - Step 01: Input numeric to name textbox with value : duc123");
		customerPage.inputTextBoxByNameAttribute(driver, "name", "duc123");
		
		log.info("NewCustomer_02 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageName(), "Numbers are not allowed");	
	}
	
	@Test
	public void NewCustomer_03_Name_Cannot_Have_Special_Characters() {
		log.info("NewCustomer_03 - Step 01: Input special characters to name textbox with value : duc123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "name", "duc123@!# ");
		
		log.info("NewCustomer_03 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageName(), "Special characters are not allowed");
	}
	
	@Test
	public void NewCustomer_04_Name_Cannot_Have_First_Character_As_Blank_Space() {
		log.info("NewCustomer_04 - Step 01: Input space at first character to name textbox with value :  duc");
		customerPage.inputTextBoxByNameAttribute(driver, "name", " duc");
		
		log.info("NewCustomer_04 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageName(), "First character can not have space");
	}
	
	@Test
	public void NewCustomer_05_Address_Cannot_Be_Blank() {
		log.info("NewCustomer_05 - Step 01: Input blank to address text area");
		customerPage.inputToAddressArea("");
		
		log.info("NewCustomer_05 - Step 02: Press tab key");
		customerPage.pressTabKeyAtAddressArea();
		
		log.info("NewCustomer_05 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageAddress(), "Address Field must not be blank");
	}
	
	@Test
	public void NewCustomer_06_Address_Cannot_Have_First_Character_As_Blank_Space() {
		log.info("NewCustomer_06 - Step 01: Input space at first character to name textbox with value :  duc");
		customerPage.inputToAddressArea(" duc");
		
		log.info("NewCustomer_06 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageAddress(), "First character can not have space");
	}
	
	@Test
	public void NewCustomer_07_City_Cannot_Be_Blank() {
		log.info("NewCustomer_07 - Step 01: Input blank to city text box");
		customerPage.inputTextBoxByNameAttribute(driver, "city", "");
		
		log.info("NewCustomer_07 - Step 02: Press tab key");
		customerPage.pressTabKeyAtCityTextbox();
		
		log.info("NewCustomer_07 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageCity(), "City Field must not be blank");
	}
	
	@Test
	public void NewCustomer_08_State_Cannot_Be_Blank() {
		log.info("NewCustomer_08 - Step 01: Input blank to state text box");
		customerPage.inputTextBoxByNameAttribute(driver, "state", "");
		
		log.info("NewCustomer_08 - Step 02: Press tab key");
		customerPage.pressTabKeyAtStateTextbox();
		
		log.info("NewCustomer_08 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageState(), "State must not be blank");
	}
	
	@Test
	public void NewCustomer_09_Pin_Cannot_Be_Blank() {
		log.info("NewCustomer_09 - Step 01: Input blank to pin text box");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "");
		
		log.info("NewCustomer_09 - Step 02: Press tab key");
		customerPage.pressTabKeyAtPINTextbox();
		
		log.info("NewCustomer_09 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "PIN Code must not be blank");
	}
	
	@Test
	public void NewCustomer_10_Mobile_Number_Cannot_Be_Blank() {
		log.info("NewCustomer_10 - Step 01: Input blank to mobile number text box");
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", "");
		
		log.info("NewCustomer_10 - Step 02: Press tab key");
		customerPage.pressTabKeyAtMobileNumberTextbox();
		
		log.info("NewCustomer_10 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageMobileNumber(), "Mobile no must not be blank");
	}
	
	@Test
	public void NewCustomer_11_Email_Cannot_Be_Blank() {
		log.info("NewCustomer_11 - Step 01: Input blank to email text box");
		customerPage.inputTextBoxByNameAttribute(driver, "emailid", "");
		
		log.info("NewCustomer_11 - Step 02: Press tab key");
		customerPage.pressTabKeyAtEmailTextbox();
		
		log.info("NewCustomer_11 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageEmail(), "Email-ID must not be blank");
	}
	
	@Test
	public void NewCustomer_12_Password_Cannot_Be_Blank() {
		log.info("NewCustomer_12 - Step 01: Input blank to password text box");
		customerPage.inputTextBoxByNameAttribute(driver, "password", "");
		
		log.info("NewCustomer_12 - Step 02: Press tab key");
		customerPage.pressTabKeyAtPasswordTextbox();
		
		log.info("NewCustomer_12 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePassword(), "Password must not be blank");
	}
	
	@Test
	public void NewCustomer_13_City_Cannot_Be_Numeric() {
		log.info("NewCustomer_13 - Step 01: Input numeric to city textbox with value : duc123");
		customerPage.inputTextBoxByNameAttribute(driver, "city", "duc123");
		
		log.info("NewCustomer_13 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageCity(), "Numbers are not allowed");	
	}
	
	@Test
	public void NewCustomer_14_City_Cannot_Have_Special_Characters() {
		log.info("NewCustomer_14 - Step 01: Input special characters to city textbox with value : duc123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "city", "duc123@!# ");
		
		log.info("NewCustomer_14 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageCity(), "Special characters are not allowed");
	}
	
	@Test
	public void NewCustomer_15_City_Cannot_Have_Special_Characters() {
		log.info("NewCustomer_15 - Step 01: Input first charater is space to city textbox with value :  duc");
		customerPage.inputTextBoxByNameAttribute(driver, "city", " duc ");
		
		log.info("NewCustomer_15 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageCity(), "First character can not have space");
	}
	
	@Test
	public void NewCustomer_16_State_Cannot_Be_Numeric() {
		log.info("NewCustomer_16 - Step 01: Input numeric to state textbox with value : duc123");
		customerPage.inputTextBoxByNameAttribute(driver, "state", "duc123");
		
		log.info("NewCustomer_16 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageState(), "Numbers are not allowed");	
	}
	
	@Test
	public void NewCustomer_17_State_Cannot_Have_Special_Characters() {
		log.info("NewCustomer_17 - Step 01: Input special characters to state textbox with value : duc123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "state", "duc123@!# ");
		
		log.info("NewCustomer_17 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageState(), "Special characters are not allowed");
	}
	
	@Test
	public void NewCustomer_18_State_Cannot_Have_Special_Characters() {
		log.info("NewCustomer_18 - Step 01: Input first charater is space to state textbox with value :  duc");
		customerPage.inputTextBoxByNameAttribute(driver, "state", " duc ");
		
		log.info("NewCustomer_18 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageState(), "First character can not have space");
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
