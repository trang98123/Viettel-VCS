package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import pageUIs.admin.nopCommerce.adminBasePageUI;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import pageUIs.user.nopCommerce.UserBasePageUI;

public class BasePage {
	// che giấu sự khởi tạo của đối tượng
	public static BasePage getBasepage() {
		return new BasePage();
	}

	// mo trang bat ky
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	// openPageUrl("http://facebook.com")
	public String getPageTitle(WebDriver driver) {
		// neu ko return thi ko lay dc du lieu
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void accepAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.accept();
		sleepInSecond(2);
	}

	public void cancelAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.dismiss();
	}

	public void senkeyToAlert(WebDriver driver, String value) {
		alert = waitForAlertPresence(driver);
		alert.sendKeys(value);
	}

	public String getAlertText(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		return alert.getText();
	}

	public void swicthToWindowByID(WebDriver driver, String parentID) {
		// Lay ra all cac ID cua Window/ tab dang co
		Set<String> allWindowID = driver.getWindowHandles();

		// duyet qua tung ID
		for (String windowID : allWindowID) {
			System.out.println(windowID);

			// neu nhu ID nao ma khac vs paraentID thi nhay vao ham if
			if (!windowID.equals(parentID)) {
				// swicth vafo 1 window/ tab theo ID
				driver.switchTo().window(windowID);
				break;
			}
		}

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void swithToWindowByTitle(WebDriver driver, String expectedWindowTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windownID : allWindowIDs) {
			driver.switchTo().window(windownID);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(expectedWindowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowExceptparent(WebDriver driver, String parentID) {
		Set<String> allWindownID = driver.getWindowHandles();
		for (String windowID : allWindownID) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				driver.close();
				sleepInSecond(1);
			}
			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window(parentID);
				break;
			}
		}
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public WebElement getElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(By.xpath(getDynamicLoctor(locator, params)));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public String getDynamicLoctor(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... params) {
		getElement(driver, getDynamicLoctor(locator, params)).click();
	}

	public void senkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public void senkeyToElement(WebDriver driver, String locator, String value, String... params) {
		locator = getDynamicLoctor(locator, params);
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);

	}

	public int countElementNumber(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	public int countElementNumber(WebDriver driver, String locator, String... params) {
		locator = getDynamicLoctor(locator, params);
		return getElements(driver, locator).size();
	}

	public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	public void selectDropdownByText(WebDriver driver, String locator, String itemText, String... params) {
		locator = getDynamicLoctor(locator, params);
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	public String getSelectItemDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectItemDropdown(WebDriver driver, String locator, String... params) {
		locator = getDynamicLoctor(locator, params);
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);
		explicitWait = new WebDriverWait(driver, shortTimeout);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeAsyncScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... params) {
		return getElement(driver, getDynamicLoctor(locator, params)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText().trim();
	}

	public String getElementText(WebDriver driver, String locator, String... params) {

		return getElement(driver, getDynamicLoctor(locator, params)).getText().trim();
	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {// neu nhu checkbox or radio chua dc chon thi
																			// ms click
		if (!isElementSelect(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... params) {// neu nhu checkbox or radio
																							// chua dc chon thi ms click
		locator = getDynamicLoctor(locator, params);
		if (!isElementSelect(driver, locator)) {
			if(driver.toString().contains("internet explorer"))
			{
				clickToElementByJS(driver, locator);
			}
			
			getElement(driver, locator).click();
		}
	}

	public void uncheckToCheckBox(WebDriver driver, String locator) {
		if (isElementSelect(driver, locator)) {
			if(driver.toString().contains("internet explorer"))
			{
				clickToElementByJS(driver, locator);
			}
			
			getElement(driver, locator).click();
		}
	}

	public boolean isElmentDisplayed(WebDriver driver, String locator) {
		try {
			// Displayed: Visible on UI+ In DOM
			// Undisplayed: Invisible on UI + In DOM
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Undisplayed: Invisible on UI + Not in DOM
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time= " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM and not visible on UI");
			System.out.println("End time= " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visiableon UI");
			System.out.println("End time= " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible on UI");
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElmentDisplayed(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLoctor(locator, params)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isElementSelect(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	public WebDriver switchTodefaultContent(WebDriver driver, String locator) {
		return driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void hoverToElement(WebDriver driver, String locator, String... params) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, getDynamicLoctor(locator, params))).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClicktolement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
		;
	}

	// nhan phim
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... params) {
		action = new Actions(driver);
		locator = getDynamicLoctor(locator, params);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
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

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}
	public void scrollToElement(WebDriver driver, String locator, String... params) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, getDynamicLoctor(locator, params)));
	}

//it dung
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public Boolean jQueryAJAXLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery !=null) && (jQuery.active===0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
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

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLoctor(locator, params))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLoctor(locator, params))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLoctor(locator, params))));
	}
//	public void uploadMultipleFiles(WebDriver driver, String... fileNames)
//	{
//		String filePath= GlobalConstans.UPLOAD_FOLDER_PATH ;
//		String fullFileName="";
//		for (String file : fileNames) {
//			fullFileName= fullFileName + filePath + file +"\n";
//		}
//		fullFileName=fullFileName.trim();
//	   getElement(driver, adminBasePageUI.UPLOAD_FILE_BY_CARD_NAME).sendKeys(fullFileName);
//	}

	
	
//Admin Nopcommerce
	
//	public void uploadFileAtCardName(WebDriver driver, String cardName, String... fileNames) {
//		String filePath = GlobalConstans.UPLOAD_FOLDER_PATH ;
//
//		String fullFileName = "";
//		for (String file : fileNames) {
//			fullFileName = fullFileName + filePath + file + "\n";
//		}
//		fullFileName = fullFileName.trim();
//		getElement(driver, adminBasePageUI.UPLOAD_FILE_BY_CARD_NAME, cardName).sendKeys(fullFileName);
//
//	}
//
//	public boolean isMessageDisplyedEmptyTable(WebDriver driver,String tableName) {
//		// TODO Auto-generated method stub
//		waitForElementVisible(driver, adminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME,tableName);
//		scrollToElement(driver,adminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
//		return isElmentDisplayed(driver, adminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME,tableName);
//	}
//	// 20 page in footer
//	// User- Nopcommerce
//	public SearchPageObject openSearchPage(WebDriver driver) {
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, UserBasePageUI.SEARCH_PAGE_FOOTER);
//		clickToElement(driver, UserBasePageUI.SEARCH_PAGE_FOOTER);
//		return PageGeneratorManager.getSearchPage(driver);
//	}
//
//	public MyAccountPageObject openMyAccountPage(WebDriver driver) {
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, UserBasePageUI.MY_ACCOUNT_PAGE_FOOTER);
//		clickToElement(driver, UserBasePageUI.MY_ACCOUNT_PAGE_FOOTER);
//		return PageGeneratorManager.getMyAccontPage(driver);
//	}
//
//	public OrderPageObject openOrderPage(WebDriver driver) {
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, UserBasePageUI.ORDER_PAGE_FOOTER);
//		clickToElement(driver, UserBasePageUI.ORDER_PAGE_FOOTER);
//		return PageGeneratorManager.getOrderpage(driver);
//	}

//	// Pattern object 
//	
//	public void enterToTextboxByID(WebDriver driver, String textboxID, String value) {
//		// TODO Auto-generated method stub
//		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
//		senkeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
//	}
//	
//	
//	
//	public void clickRadioButtonByID(WebDriver driver, String radioButtonID) {
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_RADIO_BY_ID, radioButtonID);
//		clickToElement(driver, UserBasePageUI.DYNAMIC_RADIO_BY_ID, radioButtonID);
//	}    
//	
//	public void selectDropdownByName(WebDriver driver, String dropdownName, String itemText)
//	{
//		selectDropdownByText(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropdownName);
//	
//	}
//	
//	public void clickToButtonByText(WebDriver driver, String buttonText)
//	{
//		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT,buttonText);
//		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT,buttonText);
//	}
//	public void openFooterPageByName(WebDriver driver, String pageName)
//	{
//		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
//		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER,pageName);
//	}
//
//	

//	public void uploadFileAtCartName(WebDriver driver, String cartName, String... fileNames) {
//		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
//		String fullFileName = "";
//		for (String file : fileNames) {
//			fullFileName = fullFileName + filePath + file + "\n";
//		}
//		fullFileName = fullFileName.trim();
//		getElement(driver, AdminBasepageUI.UPLOAD_FILE_BY_CARD_NAME, cartName).sendKeys(fullFileName);
//	}
//
//	public void uploadImage(WebDriver driver, String filePath) {
//		getElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(filePath);
//	}
	// 1 Ham cho ca 20 page
	// case 1-Page<10
//public Basepage getFooterPageByName(WebDriver driver, String pageName)
//{
//	waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
//	clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER,pageName);
//	if(pageName.equals("Search"))
//	{
//		return PageGeneratorManager.getSearchPage(driver);
//	}
//	else if(pageName.equals("My account"))
//	{
//		return PageGeneratorManager.getMyAccontPage(driver);
//	}
//	else 
//	{
//		return PageGeneratorManager.getOrderpage(driver);
//	}
//	
//}
//Case 2- multiple page
//public void openHeaderPageByName(WebDriver driver, String pageName)
//{
//	waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
//	clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER,pageName);
//}

//Admin- Nopcommerce
//	public void openSubMenuPageByName(WebDriver driver, String menuPageName, String subMenuPageName) {
//		// TODO Auto-generated method stub
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, AdminBasepageUI.MENU_LINK_BY_NAME, menuPageName);
//		clickToElement(driver, AdminBasepageUI.MENU_LINK_BY_NAME, menuPageName);
//
//		waitForElementClickable(driver, AdminBasepageUI.SUB_MENU_LINK_BY_NAME, subMenuPageName);
//		clickToElement(driver, AdminBasepageUI.SUB_MENU_LINK_BY_NAME, subMenuPageName);
//	}

//Demo
//	public LoginPO clickToLoginLink(WebDriver driver, String buttonText) {
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_MENU_HEADER_BY_TEXT, buttonText);
//		clickToElement(driver, UserBasePageUI.DYNAMIC_MENU_HEADER_BY_TEXT, buttonText);
//		return PageGeneratorManager.getLogin(driver);
//	}

//	public RegisterPO clickToRegiterLink(WebDriver driver, String buttonText) {
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_MENU_HEADER_BY_TEXT, buttonText);
//		clickToElement(driver, UserBasePageUI.DYNAMIC_MENU_HEADER_BY_TEXT, buttonText);
//		return PageGeneratorManager.getRegisterPage(driver);
//	}
//
//	public boolean isMessageDisplayedInEnptyTable(WebDriver driver, String tableName) {
//		// TODO Auto-generated method stub
//		waitForElementVisible(driver, AdminBasepageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
//		return isElmentDisplayed(driver, AdminBasepageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
//	}

//	public MyAccountPO clickToMyAccountLink(WebDriver driver, String buttonText) {
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, MyAccountPageUI.MY_ACCOUNT);
//		clickToElement(driver, MyAccountPageUI.MY_ACCOUNT, buttonText);
//		return PageGeneratorManager.getMyAccountPage(driver);
//	}

//HRM- Menu/ Sub menu/ ChildSubMenu
//	public void openMenuPage(WebDriver driver, String menuPageName) {
//		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
//		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
//	}
//
//	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
//		// step 1: click to menu
//		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
//		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
//		// step 2: click to submenu
//		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
//		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
//
//	}
//
//	public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName,
//			String childsubMenuPageName) {
//		// step 1: click to menu
//		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
//		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
//
//		// step 2: hover to submenu
//		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
//		hoverToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
//
//		// step 3: click to childsubmenu
//		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, childsubMenuPageName);
//		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childsubMenuPageName);
//	}
//
//	// button component
//	public void clickToButtonByID(WebDriver driver, String buttonIDName) {
//		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
//		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
//	}
//
//	// enter textbox component
//	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String value) {
//		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
//		senkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxIDName);
//	}
//	//get textbox value component
//	public String getTextboxValueByID(WebDriver driver, String textboxIDName)
//	{
//		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID,textboxIDName);
//		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxIDName);
//	}
//	
//	//Dropdown
//	public void selectItemInDropdowmnByID(WebDriver driver, String dropdownID, String valueItem) {
//		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID,dropdownID);
//		selectDropdownByText(driver, BasePageUI.DROPDOWN_BY_ID, valueItem, dropdownID);
//	}
//	public DashboadPO loginToSystem(WebDriver driver,String userName, String passWord)
//	{
//		waitForElementVisible(driver, BasePageUI.USER_LOGIN_TEXTBOX);
//		senkeyToElement(driver, BasePageUI.USER_LOGIN_TEXTBOX, userName);
//		senkeyToElement(driver, BasePageUI.PASSWORD_LOGIN_TEXTBOX, passWord);
//		clickToElement(driver, BasePageUI.LOGIN_BUTTON);
//		return PageGeneratorManager.getDashboadPage(driver);
//	}
//	
//	public LoginPO logoutToSystem(WebDriver driver)
//	{
//		waitForElementClickable(driver, BasePageUI.WELCOME_USER_LINK);
//		clickToElement(driver, BasePageUI.WELCOME_USER_LINK);
//		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
//		clickToElement(driver, BasePageUI.LOGOUT_LINK);
//		
//		return PageGeneratorManager.getLoginPage(driver);
//	}
//	/**
//	 * Get selectec text in item dropdown
//	 * @author trang siu nhin
//	 * @param driver
//	 * @param dropdownID
//	 * @return selected text in dropdown
//	 */
//	public String getSelectItemInDropdownByID(WebDriver driver, String dropdownID)
//	{
//		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID,dropdownID);
//	return	getSelectItemDropdown(driver, BasePageUI.DROPDOWN_BY_ID,dropdownID);
//	}
//
//	/**
//	 * @param driver
//	 * @param checkboxLableName
//	 */
//	public void checkToCheckboxByLable(WebDriver driver, String checkboxLableName)
//	{
//		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABLE,checkboxLableName);
//		checkToCheckboxOrRadio(driver,BasePageUI.CHECKBOX_BY_LABLE,checkboxLableName);
//		
//	}
//	
//	public void checkToRadioCheckboxByLable(WebDriver driver, String radioLableName)
//	{
//		waitForElementClickable(driver, BasePageUI.RADIO_BY_LABLE,radioLableName);
//		checkToCheckboxOrRadio(driver,BasePageUI.RADIO_BY_LABLE,radioLableName);
//	}
//	public String getValueInTableIDAtColumnNameAndRowNumber(WebDriver driver,String tableID, String headName, String rowIndex) {
//		// TODO Auto-generated method stub
//		int columIndex=getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME,tableID,headName)+1;
//		//4
//		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX,tableID,rowIndex,String.valueOf(columIndex));
//		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX,tableID,rowIndex,String.valueOf(columIndex));
//	}
	// Amind- Nopcommerce
//	public void openSubMenuPageByName(WebDriver driver, String menuPageName, String subMenuPageName) {
//		// TODO Auto-generated method stub
//		waitForElementClickable(driver, adminBasePageUI.MENU_PAGE_BY_NAME, menuPageName);
//		clickToElement(driver, adminBasePageUI.MENU_PAGE_BY_NAME, menuPageName);
//		clickToElement(driver, adminBasePageUI.MENU_PAGE_BY_NAME, menuPageName);
//
//		waitForElementClickable(driver, adminBasePageUI.SUB_MENU_PAGE_BY_NAME, subMenuPageName);
//		clickToElement(driver, adminBasePageUI.SUB_MENU_PAGE_BY_NAME, subMenuPageName);
//	}

	private Alert alert;
	private WebDriverWait explicitWait;
	private long shortTimeout = GlobalConstans.SHORT_TIMEOUT;
	private long longTimeout = GlobalConstans.LONG_TIMEOUT;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
}
