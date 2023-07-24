package com.trisysLOS.tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.pageObjects.AddSchemePage;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.DashboardPage;
import com.trisysLOS.pageObjects.SchemesListingPage;

public class AddSchemePageTest extends BaseClass {

	public AddSchemePageTest() {
		super();
	}
	
	public WebDriver driver;
	public AdminLoginPage adminLoginPage;
	public DashboardPage dashboardPage;
	public SchemesListingPage schemesListingPage;
	public AddSchemePage addSchemePage;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		driver = initilizeBrowser(prop.getProperty("BrowserName"));
		adminLoginPage = new AdminLoginPage(driver);
		dashboardPage = adminLoginPage.EnterValidLoginCredentials(prop.getProperty("UserName"),
				prop.getProperty("Password"));
		schemesListingPage= dashboardPage.clickOnSchemesModule();
		addSchemePage = schemesListingPage.clickOnAddScheme();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

	@Test
	public void LOS_TC_AddScheme_001() {
		addSchemePage.enterSchemeName(testDataProp.getProperty("SchemeName"));
		addSchemePage.selectProduct(testDataProp.getProperty("ProductName"));
		addSchemePage.selectStatus(testDataProp.getProperty("Status"));
		schemesListingPage = addSchemePage.clickOnSaveButton();
		Assert.assertTrue(schemesListingPage.validateAddSchemeConfirmatonMessage());
	}
	

	@Test
	public void LOS_TC_AddScheme_002() {
		addSchemePage.enterSchemeName(testDataProp.getProperty("SchemeName"));
		addSchemePage.selectProduct(testDataProp.getProperty("ProductName"));
		addSchemePage.selectStatus(testDataProp.getProperty("Status"));
		schemesListingPage = addSchemePage.clickOncancelButton();
		Assert.assertTrue(schemesListingPage.getSchemesPageURL(testDataProp.getProperty("SchemesListing")));
	}
	

	@Test
	public void LOS_TC_AddScheme003() {
		schemesListingPage = addSchemePage.clickOnSaveButton();
		Assert.assertFalse(schemesListingPage.getSchemesPageURL(testDataProp.getProperty("SchemesListing")));
	}
}
