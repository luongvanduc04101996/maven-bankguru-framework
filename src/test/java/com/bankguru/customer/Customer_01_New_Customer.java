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
	WebDriver driver;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	ManagerHomePageObject managerHomePage;
	CustomerPageObject customerPage;
	String userID, password, email;
	FakerConfig fakeData;


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
		loginPage.openListPageByName(driver, "Manager");
		managerHomePage = PageGeneratorManager.getManagerHomePage(driver);
		
	}

	@Test
	public void NewCustomer_01_Name_Cannot_Be_Empty() {
		log.info("NewCustomer_01 - Step 01: Click to New Customer page ");
		managerHomePage.openListPageByName(driver, "New Customer");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
		log.info("NewCustomer_01 - Step 02: Input empty to name textbox");
		customerPage.inputTextBoxByName(driver, "name", "");
		
		log.info("NewCustomer_01 - Step 03: Press to Tab key");
		customerPage.pressTabKey();
		
		log.info("NewCustomer_01 - Step 04: Verify message displayed");
		verifyEquals(customerPage.getMessageEmptyName(), "Customer name must not be blank");
		
	}

	@AfterClass (alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
