package com.trisysLOS.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.DashboardPage;
import com.trisysLOS.pageObjects.LoansDeatilsPage;
import com.trisysLOS.pageObjects.LoansListingPage;

public class LoansListingPageTest extends BaseClass {

	public LoansListingPageTest() {
		super();
	}

	public WebDriver driver;
	public AdminLoginPage adminLoginPage;
	public DashboardPage dashboardPage;
	public LoansListingPage loansListingPage;
	public LoansDeatilsPage loansDetailsPage;

	@BeforeMethod
	public void setUp() {
		driver = initilizeBrowser(prop.getProperty("BrowserName"));
		adminLoginPage = new AdminLoginPage(driver);
		dashboardPage = adminLoginPage.EnterValidLoginCredentials(prop.getProperty("UserName"), prop.getProperty("Password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	//Validate whether the user is able to navigate loan tab
	public void LOS_TC_LoansListing_001() {
		loansListingPage = dashboardPage.clickOnLoansLodule();
		Assert.assertTrue(loansListingPage.getLoansPageURL(testDataProp.getProperty("loansURL")));
	}
	
	@Test
	public void LOS_TC_LoansListing_002() {
		loansListingPage = dashboardPage.clickOnLoansLodule();
		loansListingPage.enterSearchByNameOrMobileNumber(testDataProp.getProperty("SearchName"));
		Assert.assertTrue(loansListingPage.nameValidateInLoansListing(testDataProp.getProperty("SearchName"))); 
	}
	
	public void LOS_TC_LoansListing_003() {
		loansListingPage = dashboardPage.clickOnLoansLodule();
		loansListingPage.enterSearchByNameOrMobileNumber(testDataProp.getProperty("SearchName"));
		loansDetailsPage = loansListingPage.clickOnCreatedLoan(testDataProp.getProperty("SearchName"));
		loansDetailsPage.clickOnDeleteButton();
	}
}
