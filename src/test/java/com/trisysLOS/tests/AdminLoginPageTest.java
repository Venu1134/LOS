package com.trisysLOS.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.jiraIntegration.JiraCreateIssue;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.DashboardPage;

public class AdminLoginPageTest extends BaseClass {

	public AdminLoginPageTest() {
		super();
	}

	public WebDriver driver;
	public AdminLoginPage adminLoginPage;
	public DashboardPage dashboardPage;

	
	@Parameters("browser")
	@BeforeMethod(groups= {"Regression","Sanity","Negative","Positive"}, alwaysRun = true)
	public void setUp(String browser) {
		driver = initilizeBrowser(browser);
		adminLoginPage = new AdminLoginPage(driver);
	}

	
	@AfterMethod(groups= {"Regression","Sanity","Negative","Positive"}, alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(groups= {"Regression","Sanity","Positive"}, priority=1)
	public void LOS_TC_Login_001() {
		Assert.assertTrue(adminLoginPage.getSignInPageURL(testDataProp.getProperty("actualURL")));
	}

	@JiraCreateIssue(isCreateIssue=true)
	@Test(groups= {"Regression","Sanity","Positive"},priority=1)
	public void LOS_TC_Login_002() {
		dashboardPage = adminLoginPage.EnterValidLoginCredentials(prop.getProperty("UserName"), prop.getProperty("Password"));
		Assert.assertTrue(dashboardPage.isDashboardHeadingExists());
	}
	
	@JiraCreateIssue(isCreateIssue=true)
	@Test(groups= {"Regression","Negative"},priority=2)
	public void LOS_TC_Login_003() {
		adminLoginPage.enterInvalidLoginCredentials(testDataProp.getProperty("InvalidUserName"), testDataProp.getProperty("InvalidPassword"));
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue=true)
	@Test(groups= {"Regression","Negative"},priority=2)
	public void LOS_TC_Login_004() {
		adminLoginPage.enterInvalidLoginCredentials(prop.getProperty("UserName"), testDataProp.getProperty("InvalidPassword"));
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue=true)
	@Test(groups= {"Regression","Negative"}, priority=2)
	public void LOS_TC_Login_005() {
		adminLoginPage.enterInvalidLoginCredentials(testDataProp.getProperty("InvalidUserName"), prop.getProperty("Password"));
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue=true)
	@Test(groups= {"Regression","Negative"}, priority=3)
	public void LOS_TC_Login_006() {
		adminLoginPage.enterInvalidLoginCredentials(prop.getProperty("UserName"), " ");
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue=true)
	@Test(groups= {"Regression","Negative"}, priority=3)
	public void LOS_TC_Login_007() {
		adminLoginPage.enterInvalidLoginCredentials(" ", prop.getProperty("Password"));
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue=true)
	@Test(groups= {"Regression","Negative"}, priority=3)
	public void LOS_TC_Login_008() {
		adminLoginPage.clickOnLoginButton();
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
}
