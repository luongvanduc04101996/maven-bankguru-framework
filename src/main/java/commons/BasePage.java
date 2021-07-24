package commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.BasePageUI;

public class BasePage {
	public void openPageURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getCurrentPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getCurrentPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void setTextAlert(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		element.click();
	}
	
	public void clickToElementByDynamicLocator(WebDriver driver, String dynamicLocator, String... values) {
		locator = getDynamicLocator(dynamicLocator, values);
		clickToElement(driver, locator);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String key) {
		element = getElement(driver, locator);
		element.clear();
		element.sendKeys(key);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
		element = getElement(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(itemValue);
	}

	public String getFirstSelectedTextInDropdown(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		select = new Select(element);
		return select.isMultiple();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String allItemsXpath,
			String expectedValueItem) {
		getElement(driver, parentXpath).click();

		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(allItemsXpath)));

		elements = getElements(driver, allItemsXpath);

		for (WebElement tempElement : elements) {
			if (tempElement.getText().equals(expectedValueItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", tempElement);
				sleepInSecond(1);
				tempElement.click();
				sleepInSecond(1);
				break;
			}

		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		element = getElement(driver, locator);
		return element.getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return element.getText();
	}

	public int countElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	public void checkToCheckBoxOrRadio(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckBox(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void clickAndHoldToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.clickAndHold(getElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void waitToAllElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitToElementInvisible(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitToElementClickAble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	
	public String getDynamicLocator(String dynamicLocator, String... values) {
		return dynamicLocator = String.format(dynamicLocator, (Object[]) values);
	}
	
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOutInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		
		if(elements.size()==0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDataStringSortedAscending(WebDriver driver, String locator) {
		ArrayList<WebElement> elements = (ArrayList<WebElement>) getElements(driver, locator);
		ArrayList<String> actualDatas = new ArrayList<>();
		ArrayList<String> expectedDatas = new ArrayList<>();
		for(WebElement tempElement:elements) {
			actualDatas.add(tempElement.getText());
		}
		for(String tempData:actualDatas) {
			expectedDatas.add(tempData);
		}
		Collections.sort(expectedDatas);
		return actualDatas.equals(expectedDatas);
	}
	
	public boolean isDataStringSortedDecending(WebDriver driver, String locator) {
		ArrayList<WebElement> elements = (ArrayList<WebElement>) getElements(driver, locator);
		ArrayList<String> actualDatas = new ArrayList<>();
		ArrayList<String> expectedDatas = new ArrayList<>();
		for(WebElement tempElement:elements) {
			actualDatas.add(tempElement.getText());
		}
		for(String tempData:actualDatas) {
			expectedDatas.add(tempData);
		}
		Collections.sort(expectedDatas);
		Collections.reverse(expectedDatas);
		return actualDatas.equals(expectedDatas);
	}	
	
	public boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
		ArrayList<WebElement> elements = (ArrayList<WebElement>) getElements(driver, locator);
		ArrayList<Float> actualDatas = new ArrayList<>();
		ArrayList<Float> expectedDatas = new ArrayList<>();
		for(WebElement tempElement:elements) {
			actualDatas.add(Float.parseFloat(tempElement.getText().replaceAll("$", "").trim()));
		}
		for(Float tempData:actualDatas) {
			expectedDatas.add(tempData);
		}
		Collections.sort(expectedDatas);
		return actualDatas.equals(expectedDatas);
	}
	
	public boolean isDataFloatSortedDecending(WebDriver driver, String locator) {
		ArrayList<WebElement> elements = (ArrayList<WebElement>) getElements(driver, locator);
		ArrayList<Float> actualDatas = new ArrayList<>();
		ArrayList<Float> expectedDatas = new ArrayList<>();
		for(WebElement tempElement:elements) {
			actualDatas.add(Float.parseFloat(tempElement.getText().replaceAll("$", "").trim()));
		}
		for(Float tempData:actualDatas) {
			expectedDatas.add(tempData);
		}
		Collections.sort(expectedDatas);
		Collections.reverse(expectedDatas);
		return actualDatas.equals(expectedDatas);
	}
	
	public Date convertStringToDate(String dateInString) {
		dateInString = dateInString.replace(",", "");
		SimpleDateFormat format = new SimpleDateFormat("MM dd yyyy");
		Date date = null;
		try {
			date = format.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public boolean isDataDateSortedDecending(WebDriver driver, String locator) {
		ArrayList<WebElement> elements = (ArrayList<WebElement>) getElements(driver, locator);
		ArrayList<Date> actualDatas = new ArrayList<>();
		ArrayList<Date> expectedDatas = new ArrayList<>();
		for(WebElement tempElement:elements) {
			actualDatas.add(convertStringToDate(tempElement.getText()));
		}
		for(Date tempData:actualDatas) {
			expectedDatas.add(tempData);
		}
		Collections.sort(expectedDatas);
		Collections.reverse(expectedDatas);
		return actualDatas.equals(expectedDatas);
	}
	
	public boolean isDataDateSortedAscending(WebDriver driver, String locator) {
		ArrayList<WebElement> elements = (ArrayList<WebElement>) getElements(driver, locator);
		ArrayList<Date> actualDatas = new ArrayList<>();
		ArrayList<Date> expectedDatas = new ArrayList<>();
		for(WebElement tempElement:elements) {
			actualDatas.add(convertStringToDate(tempElement.getText()));
		}
		for(Date tempData:actualDatas) {
			expectedDatas.add(tempData);
		}
		Collections.sort(expectedDatas);
		return actualDatas.equals(expectedDatas);
	}
	
	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0);
	}

	public boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}
	
	public String getDirectorySlashToUploadFilesFolder() {
		String folderName;
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/src/main/resources/uploadFiles/";
		} else if (isWindows()) {
			folderName = "\\src\\main\\resources\\uploadFiles\\";
		} else {
			folderName = null;
		}
		return folderName;
	}
	
	public void uploadFilesBySendkey(WebDriver driver, String locator, String... fullImageNames) {
		String filePath = System.getProperty("user.dir") + getDirectorySlashToUploadFilesFolder();
		String fullPath = "";
		for(String tempName:fullImageNames) {
			fullPath = fullPath + filePath + tempName + "\n";
		}
		fullPath = fullPath.trim();
		sendkeyToElement(driver, locator, fullPath);
	}
	
	public void openListLinkPageByText(WebDriver driver, String textNamePage) {
		locator = getDynamicLocator(BasePageUI.DYNAMIC_LINK_BY_TEXT, textNamePage);
		waitToAllElementsVisible(driver, locator);
		clickToElement(driver, locator);
	}
	
	public void inputTextBoxByNameAttribute(WebDriver driver, String nameAttribute, String value) {
		locator = getDynamicLocator(BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, nameAttribute);
		waitToAllElementsVisible(driver, locator);
		sendkeyToElement(driver, locator, value);
	}
	
	public boolean isDisplayedWithTDFieldByText(WebDriver driver, String textName) {
		locator = getDynamicLocator(BasePageUI.DYNAMIC_TD_BY_TEXT_AT_NEW_CUSTOMER, textName);
		waitToElementVisible(driver, locator);
		return isElementDisplayed(driver, locator);
	}
	
	public String getAttributeValueAtEditCustomer(WebDriver driver, String textName, String attribute) {
		locator = getDynamicLocator(BasePageUI.DYNAMIC_GET_ATTRIBUTE_VALUE_AT_EDIT_CUSTOMER, textName);
		waitToElementVisible(driver, locator);
		return getElementAttribute(driver, locator, attribute);
	}

	public String getTextAtUpdatedCustomer(WebDriver driver, String textName) {
		locator = getDynamicLocator(BasePageUI.DYNAMIC_GET_TEXT_AT_UPDATED_CUSTOMER, textName);
		waitToElementVisible(driver, locator);
		return getElementText(driver, locator);
	}
	
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;
	private Select select;
	private String locator;
	private String osName = System.getProperty("os.name");
}
