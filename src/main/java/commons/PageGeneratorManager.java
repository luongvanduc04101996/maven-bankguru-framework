package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.AccountPageObject;
import pageObjects.CustomerPageObject;
import pageObjects.LoginPageObject;
import pageObjects.ManagerHomePageObject;
import pageObjects.MiniStatementPageObject;
import pageObjects.RegisterPageObject;

public class PageGeneratorManager {
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static ManagerHomePageObject getManagerHomePage(WebDriver driver) {
		return new ManagerHomePageObject(driver);
	}
	
	public static CustomerPageObject getCustomerPage(WebDriver driver) {
		return new CustomerPageObject(driver);
	}
	
	public static AccountPageObject getAccountPage(WebDriver driver) {
		return new AccountPageObject(driver);
	}
	
	public static MiniStatementPageObject getMiniStatement(WebDriver driver) {
		return new MiniStatementPageObject(driver);
	}

}
