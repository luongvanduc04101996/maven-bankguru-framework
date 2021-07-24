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


public class Customer_04_Delete_Customer extends BaseTest {
	
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
	public void Delete_01_CustomerID_Cannot_Be_Empty() {
		log.info("Delete_01: Step 01 - Open Edit Customer page");
		customerPage.openListLinkPageByText(driver, "Delete Customer");
		
		log.info("Delete_01: Step 02 - Input empty to customer id");
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", "");
		
		log.info("Delete_01: Step 03 - Press tab key");
		customerPage.pressTabKeyAtCustomerIDTextbox();
		
		log.info("Delete_01: Step 04 - Verify error message displayed");
		verifyEquals(customerPage.getErrorMessageCustomerID(), "Customer ID is required");
	}
	
	@Test
	public void Delete_02_CustomerID_Must_Be_Numberic() {
		log.info("Delete_02: Step 01 - Input not numberic to customer id with value: 123abc");
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", "123abc");
		
		log.info("Delete_02: Step 02 - Verify error message displayed");
		verifyEquals(customerPage.getErrorMessageCustomerID(), "Characters are not allowed");
	}
	
	@Test
	public void Delete_03_CustomerID_Cannot_Have_Special_Character() {
		log.info("Delete_03: Step 01 - Input special character to customer id with value: 123abc@");
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", "123abc@");
		
		log.info("Delete_03: Step 02 - Verify error message displayed");
		verifyEquals(customerPage.getErrorMessageCustomerID(), "Special characters are not allowed");
	}
	
	@Test
	public void Delete_04_CustomerID_First_Character_Cannot_Have_Space() {
		log.info("Delete_04: Step 01 - Input first character is space to customer id with value:  123");
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", " 123");
		
		log.info("Delete_04: Step 02 - Verify error message displayed");
		verifyEquals(customerPage.getErrorMessageCustomerID(), "First character can not have space");
	}
	
	@Test
	public void Delete_05_CustomerID_Cannot_Have_Space() {
		log.info("Delete_05: Step 01 - Input space to customer id with value: 1 2 3");
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", "1 2 3");
		
		log.info("Delete_05: Step 02 - Verify error message displayed");
		verifyEquals(customerPage.getErrorMessageCustomerID(), "Characters are not allowed");
	}
	
	@Test
	public void Delete_06_Enter_Valid_Customer_ID_To_Delete() {
		log.info("Delete_06: Step 01 - Input valid customer id with value: " + customerID);
		customerPage.inputTextBoxByNameAttribute(driver, "cusid", customerID);
		
		log.info("Delete_06: Step 02 - Click submit button");
		customerPage.clickToSubmitButtonAtEditAndDeleteCustomer();
		
		log.info("Delete_06: Step 03 - Select OK alert");
		customerPage.acceptAlert(driver);
		
		log.info("Delete_06: Step 04 - Verify deleted successful");
		verifyEquals(customerPage.getTextAlert(driver), "Customer deleted Successfully");
		customerPage.acceptAlert(driver);
		
		log.info("Delete_06: Step 05 - Verify go to manager home page");
		managerHomePage = PageGeneratorManager.getManagerHomePage(driver);
		verifyEquals(managerHomePage.getTextHeadingWelcome(), "Welcome To Manager's Page of Guru99 Bank");
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
	String userIDLogin, passwordLogin, emailRegister, name, address, city, state, pin, phone, email, password, birthDay, customerID, gender;
	String dayBirth, monthBirth, yearBirth;
	FakerConfig fakeData;
}
