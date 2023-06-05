package com.trisysLOS.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.DashboardPage;
import com.trisysLOS.utilities.JiraCreateIssue;

import static com.trisysLOS.utilities.ExtentTestManager.startTest;

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
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Sanity","Positive"}, priority=1, description = "To verify valid URL is display sign in page.")
	public void LOS_TC_Login_001(Method method) {
		startTest(method.getName(),"To verify valid URL is display sign in page.");
		Assert.assertTrue(adminLoginPage.getSignInPageURL(testDataProp.getProperty("actualURL")));
	}

	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Sanity","Positive"},priority=1,description = "To verify user is able to login with valid username and password.")
	public void LOS_TC_Login_002(Method method) {
		startTest(method.getName(),"To verify user is able to login with valid username and password.");
		dashboardPage = adminLoginPage.EnterValidLoginCredentials(prop.getProperty("UserName"), prop.getProperty("Password"));
		Assert.assertTrue(dashboardPage.isDashboardHeadingExists());
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=2,description = "To verify user is able to login with invalid username and password.")
	public void LOS_TC_Login_003(Method method) {
		startTest(method.getName(),"To verify user is able to login with invalid username and password.");
		adminLoginPage.enterInvalidLoginCredentials(testDataProp.getProperty("InvalidUserName"), testDataProp.getProperty("InvalidPassword"));
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=2,description = "To verify user is able to login with valid username and invalid password.")
	public void LOS_TC_Login_004(Method method) {
		startTest(method.getName(),"To verify user is able to login with valid username and invalid password.");
		adminLoginPage.enterInvalidLoginCredentials(prop.getProperty("UserName"), testDataProp.getProperty("InvalidPassword"));
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"}, priority=2,description = "To verify user is able to login with invalid username and valid password.")
	public void LOS_TC_Login_005(Method method) {
		startTest(method.getName(),"To verify user is able to login with invalid username and valid password.");
		adminLoginPage.enterInvalidLoginCredentials(testDataProp.getProperty("InvalidUserName"), prop.getProperty("Password"));
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"}, priority=3,description = "To verify user is able to login with valid username and without password.")
	public void LOS_TC_Login_006(Method method) {
		startTest(method.getName(),"To verify user is able to login with valid username and without password.");
		adminLoginPage.enterInvalidLoginCredentials(prop.getProperty("UserName"), " ");
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"}, priority=3, description = "To verify user is able to login without username and valid password.")
	public void LOS_TC_Login_007(Method method) {
		startTest(method.getName(),"To verify user is able to login without username and valid password.");
		adminLoginPage.enterInvalidLoginCredentials(" ", prop.getProperty("Password"));
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"}, priority=3,description = "To verify user is able to login without entering username and password.")
	public void LOS_TC_Login_008(Method method) {
		startTest(method.getName(),"To verify user is able to login without entering username and password.");
		adminLoginPage.clickOnLoginButton();
		Assert.assertTrue(adminLoginPage.validateErrorMessage(testDataProp.getProperty("actualErrorMessage")));
	}
}
