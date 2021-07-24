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


public class Customer_03_Edit_Customer extends BaseTest {
	WebDriver driver;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	ManagerHomePageObject managerHomePage;
	CustomerPageObject customerPage;
	String userIDLogin, passwordLogin, emailRegister, name, address, city, state, pin, phone, email, password, birthDay, customerID, gender;
	String dayBirth, monthBirth, yearBirth;
	FakerConfig fakeData;


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
	public void Edit_01_CustomerID_Cannot_Be_Empty() {
		log.info("Edit_01: Step 01 - Open Edit Customer page");
		customerPage.openListLinkPageByText(driver, "Edit Customer");
		
		log.info("Edit_01: Step 02 - Input empty to customer id");
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", "");
		
		log.info("Edit_01: Step 03 - Press tab key");
		customerPage.pressTabKeyAtCustomerIDTextbox();
		
		log.info("Edit_01: Step 04 - Verify error message displayed");
		verifyEquals(customerPage.getErrorMessageCustomerID(), "Customer ID is required");
	}
	
	@Test
	public void Edit_02_CustomerID_Must_Be_Numberic() {
		log.info("Edit_02: Step 01 - Input not numberic to customer id with value: 123abc");
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", "123abc");
		
		log.info("Edit_02: Step 02 - Verify error message displayed");
		verifyEquals(customerPage.getErrorMessageCustomerID(), "Characters are not allowed");
	}
	
	@Test
	public void Edit_03_CustomerID_Cannot_Have_Special_Character() {
		log.info("Edit_03: Step 01 - Input special character to customer id with value: 123abc@");
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", "123abc@");
		
		log.info("Edit_03: Step 02 - Verify error message displayed");
		verifyEquals(customerPage.getErrorMessageCustomerID(), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_04_Enter_Valid_Customer_ID() {
		log.info("Edit_04: Step 01 - Input valid customer id with value: " + customerID);
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", customerID);
		
		log.info("Edit_04: Step 02 - Click submit button");
		customerPage.clickToSubmitButtonAtEditCustomer();
		
		log.info("Edit_04: Step 03 - Verify submit successful with customer name displayed");
		verifyEquals(customerPage.getAttributeValueAtEditCustomer(driver, "Customer Name", "value"), name);
	}
	
	@Test
	public void Edit_05_Verify_values_are_corrected() {
		log.info("Edit_05: Step 01 - Verify customer name");
		verifyEquals(customerPage.getAttributeValueAtEditCustomer(driver, "Customer Name", "value"), name);
		
		log.info("Edit_05: Step 02 - Verify gender");
		verifyEquals(customerPage.getAttributeValueAtEditCustomer(driver, "Gender", "value"), gender);
	
		log.info("Edit_05: Step 03 - Verify date of birth");
		verifyEquals(customerPage.getAttributeValueAtEditCustomer(driver, "Date of Birth", "value"), yearBirth + "-" + monthBirth + "-" + dayBirth);
	}
	
	@Test
	public void Edit_06_City_Cannot_Be_Blank() {
		log.info("Edit_06 - Step 01: Input blank to city text box");
		customerPage.inputTextBoxByNameAttribute(driver, "city", "");
		
		log.info("Edit_06 - Step 02: Press tab key");
		customerPage.pressTabKeyAtCityTextbox();
		
		log.info("Edit_06 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageCity(), "City Field must not be blank");
	}
	
	@Test
	public void Edit_07_State_Cannot_Be_Blank() {
		log.info("Edit_07 - Step 01: Input blank to state text box");
		customerPage.inputTextBoxByNameAttribute(driver, "state", "");
		
		log.info("Edit_07 - Step 02: Press tab key");
		customerPage.pressTabKeyAtStateTextbox();
		
		log.info("Edit_07 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageState(), "State must not be blank");
	}
	
	@Test
	public void Edit_08_Pin_Cannot_Be_Blank() {
		log.info("Edit_08 - Step 01: Input blank to pin text box");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "");
		
		log.info("Edit_08 - Step 02: Press tab key");
		customerPage.pressTabKeyAtPINTextbox();
		
		log.info("Edit_08 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "PIN Code must not be blank");
	}
	
	@Test
	public void Edit_09_Mobile_Number_Cannot_Be_Blank() {
		log.info("Edit_09 - Step 01: Input blank to mobile number text box");
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", "");
		
		log.info("Edit_09 - Step 02: Press tab key");
		customerPage.pressTabKeyAtMobileNumberTextbox();
		
		log.info("Edit_09 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageMobileNumber(), "Mobile no must not be blank");
	}
	
	@Test
	public void Edit_10_Email_Cannot_Be_Blank() {
		log.info("Edit_10 - Step 01: Input blank to email text box");
		customerPage.inputTextBoxByNameAttribute(driver, "emailid", "");
		
		log.info("Edit_10 - Step 02: Press tab key");
		customerPage.pressTabKeyAtEmailTextbox();
		
		log.info("Edit_10 - Step 03: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageEmail(), "Email-ID must not be blank");
	}
	
	@Test
	public void Edit_11_City_Cannot_Be_Numeric() {
		log.info("Edit_11 - Step 01: Input numeric to city textbox with value : duc123");
		customerPage.inputTextBoxByNameAttribute(driver, "city", "duc123");
		
		log.info("Edit_11 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageCity(), "Numbers are not allowed");	
	}
	
	@Test
	public void Edit_12_City_Cannot_Have_Special_Characters() {
		log.info("Edit_12 - Step 01: Input special characters to city textbox with value : duc123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "city", "duc123@!# ");
		
		log.info("Edit_12 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageCity(), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_13_City_Cannot_Have_Special_Characters() {
		log.info("Edit_13 - Step 01: Input first charater is space to city textbox with value :  duc");
		customerPage.inputTextBoxByNameAttribute(driver, "city", " duc ");
		
		log.info("Edit_13 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageCity(), "First character can not have space");
	}
	
	@Test
	public void Edit_14_State_Cannot_Be_Numeric() {
		log.info("Edit_14 - Step 01: Input numeric to state textbox with value : duc123");
		customerPage.inputTextBoxByNameAttribute(driver, "state", "duc123");
		
		log.info("Edit_14 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageState(), "Numbers are not allowed");	
	}
	
	@Test
	public void Edit_15_State_Cannot_Have_Special_Characters() {
		log.info("Edit_15 - Step 01: Input special characters to state textbox with value : duc123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "state", "duc123@!# ");
		
		log.info("Edit_15 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageState(), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_16_State_Cannot_Have_Special_Characters() {
		log.info("Edit_16 - Step 01: Input first charater is space to state textbox with value :  duc");
		customerPage.inputTextBoxByNameAttribute(driver, "state", " duc ");
		
		log.info("Edit_16 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageState(), "First character can not have space");
	}
	
	@Test
	public void Edit_17_Pin_Must_Be_Numberic() {
		log.info("Edit_17 - Step 01: Input not numberic to pin textbox with value: 123duc");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "123duc");
			
		log.info("Edit_17 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "Characters are not allowed");
	}
	
	@Test
	public void Edit_18_Pin_Must_Have_6_digits() {
		log.info("Edit_18 - Step 01: Input numeric to pin textbox with value : 123");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "123");
		
		log.info("Edit_18 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "PIN Code must have 6 Digits");	
	}
	
	@Test
	public void Edit_19_Pin_Cannot_Have_Special_Characters() {
		log.info("Edit_19 - Step 01: Input special characters to pin textbox with value : 123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "123@!#");
		
		log.info("Edit_19 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_20_Pin_Cannot_Have_First_Character_As_Blank_Space() {
		log.info("Edit_20 - Step 01: Input space at first character to pin textbox with value :  123");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", " 123");
		
		log.info("Edit_20 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "First character can not have space");
	}
	
	@Test
	public void Edit_21_Pin_Cannot_Have_Space() {
		log.info("Edit_21 - Step 01: Input space to pin textbox with value: 12 45");
		customerPage.inputTextBoxByNameAttribute(driver, "pinno", "12 45");	
		
		log.info("Edit_21 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessagePin(), "Characters are not allowed");
	}
	
	@Test
	public void Edit_22_Mobile_Number_Cannot_Have_First_Character_As_Blank_Space() {
		log.info("Edit_22 - Step 01: Input space at first character to mobile number textbox with value :  123");
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", " 123");
		
		log.info("Edit_22 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageMobileNumber(), "First character can not have space");
	}
	
	@Test
	public void Edit_23_Mobile_Number_Cannot_Have_Space() {
		log.info("Edit_23 - Step 01: Input space to mobile number textbox with value: 12 45");
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", "12 45");	
		
		log.info("Edit_23 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageMobileNumber(), "Characters are not allowed");
	}
	
	@Test
	public void Edit_24_Mobile_Number_Cannot_Have_Special_Characters() {
		log.info("Edit_24 - Step 01: Input special characters to mobile number textbox with value : 123@!#");
		customerPage.inputTextBoxByNameAttribute(driver, "telephoneno", "123@!#");
		
		log.info("Edit_24 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageMobileNumber(), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_25_Email_Must_Be_In_Correct_Format() {
		log.info("Edit_25 - Step 01: Input invalid format to email textbox with value : duc@gmail");
		customerPage.inputTextBoxByNameAttribute(driver, "emailid", "duc@gmail");
		
		log.info("Edit_25 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageEmail(), "Email-ID is not valid");
	}
	
	@Test
	public void Edit_26_Email_Not_Contain_Space() {
		log.info("Edit_26 - Step 01: Input space to email textbox with value : du c@gmail");
		customerPage.inputTextBoxByNameAttribute(driver, "emailid", "du c@gmail");
		
		log.info("Edit_26 - Step 02: Verify message displayed");
		verifyEquals(customerPage.getErrorMessageEmail(), "Email-ID is not valid");
	}
	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
