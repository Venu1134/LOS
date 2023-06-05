package com.trisysLOS.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.DashboardPage;
import com.trisysLOS.pageObjects.LoanDeletePage;
import com.trisysLOS.pageObjects.LoansDeatilsPage;
import com.trisysLOS.pageObjects.LoansListingPage;

public class LoanDeletePageTest extends BaseClass{

	public LoanDeletePageTest() {
		super();
	}
	
	public WebDriver driver;
	public AdminLoginPage adminLoginPage;
	public DashboardPage dashboardPage;
	public LoansListingPage loansListingPage;
	public LoansDeatilsPage loansDeatilsPage;
	public LoanDeletePage loanDeletePage;
	
	@BeforeMethod
	public void setUp() {
		driver = initilizeBrowser(prop.getProperty("BrowserName"));
		adminLoginPage = new AdminLoginPage(driver);
		dashboardPage = adminLoginPage.EnterValidLoginCredentials(prop.getProperty("UserName"), prop.getProperty("Password"));
		loansListingPage = dashboardPage.clickOnLoansLodule();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void LOS_TC_DeleteLoan_001() {
		loansListingPage.enterSearchByNameOrMobileNumber(testDataProp.getProperty("SearchName"));
		loansDeatilsPage =loansListingPage.clickOnCreatedLoan(testDataProp.getProperty("SearchName"));
		loanDeletePage = loansDeatilsPage.clickOnDeleteButton();
		loansListingPage = loanDeletePage.clickOnYesButton();
		Assert.assertTrue(loansListingPage.validateLoanDeleteMessage());
	}
}
