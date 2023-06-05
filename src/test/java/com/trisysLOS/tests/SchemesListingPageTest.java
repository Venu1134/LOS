package com.trisysLOS.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.DashboardPage;
import com.trisysLOS.pageObjects.SchemesListingPage;

public class SchemesListingPageTest extends BaseClass{

	public WebDriver driver;
	public AdminLoginPage adminLoginPage;
	public DashboardPage dashboardPage;
	public SchemesListingPage schemesListingPage;
	
	public SchemesListingPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver = initilizeBrowser(prop.getProperty("BrowserName"));
		adminLoginPage = new AdminLoginPage(driver);
		dashboardPage = adminLoginPage.EnterValidLoginCredentials(prop.getProperty("UserName"),
				prop.getProperty("Password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void LOS_TC_Schemes_001() {
		schemesListingPage = dashboardPage.clickOnSchemesModule();
		Assert.assertTrue(schemesListingPage.getSchemesPageURL(testDataProp.getProperty("SchemesListing")));
	}
}
