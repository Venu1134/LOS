package com.trisysLOS.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.CreateLoanPage;
import com.trisysLOS.pageObjects.DashboardPage;
import com.trisysLOS.pageObjects.LoansDeatilsPage;
import com.trisysLOS.pageObjects.LoansListingPage;
import com.trisysLOS.utilities.UtilityClass;

public class LoansListingPageTest extends BaseClass {

	public LoansListingPageTest() {
		super();
	}

	public WebDriver driver;
	public AdminLoginPage adminLoginPage;
	public DashboardPage dashboardPage;
	public LoansListingPage loansListingPage;
	public LoansDeatilsPage loansDetailsPage;
	public CreateLoanPage createLoanPage;
	

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
	@Test
	public void LOS_TC_LoansListing_003() {
		loansListingPage = dashboardPage.clickOnLoansLodule();
		loansListingPage.enterSearchByNameOrMobileNumber(testDataProp.getProperty("SearchName"));
		loansDetailsPage = loansListingPage.clickOnCreatedLoan(testDataProp.getProperty("SearchName"));
		loansDetailsPage.clickOnDeleteButton();
	}
	
	//Validate whether the loan created is displayed in Loan listing page
	@Test
	public void LOS_TC_LoansListing_004() {
		loansListingPage = dashboardPage.clickOnLoansLodule();
		createLoanPage = loansListingPage.clickOnNewLoanButton();
		String name = UtilityClass.invaldName();
		System.out.println("Invalid name : "+name);
		loansListingPage = createLoanPage.enterAllDetails(name, testDataProp.getProperty("MobileNumber"), testDataProp.getProperty("Email"), testDataProp.getProperty("Product"), testDataProp.getProperty("Individual"), testDataProp.getProperty("IndividualType"), testDataProp.getProperty("Amount"), testDataProp.getProperty("Date"), testDataProp.getProperty("Priority"), testDataProp.getProperty("Description"), testDataProp.getProperty("Owner"), testDataProp.getProperty("Branch"));
	    loansListingPage.nameValidateInLoansListing(name);
	    loansListingPage.clickOnCreatedLoan(name);
	}
	
	// Validate that user is unable to create duplicate loans
	
	
}
