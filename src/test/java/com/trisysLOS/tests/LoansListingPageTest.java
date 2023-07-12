package com.trisysLOS.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.CreateLoanPage;
import com.trisysLOS.pageObjects.DashboardPage;
import com.trisysLOS.pageObjects.LoanDeletePage;
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
	public CreateLoanPage createLoanPage;
	public LoanDeletePage loanDeletePage;
	

	@Parameters("browser")
	@BeforeMethod(groups = {"System"})
	public void setUp(String browser) {
		driver = initilizeBrowser(browser);
		adminLoginPage = new AdminLoginPage(driver);
		dashboardPage = adminLoginPage.EnterValidLoginCredentials(prop.getProperty("UserName"), prop.getProperty("Password"));
	}

	@AfterMethod(groups = {"System"})
	public void logout() {
		adminLoginPage.applicationLogout();
	}
	/*public void tearDown() {
		driver.quit();
	}*/
	
	
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
		@Test(groups= {"System"})
		public void LOS_TC_LoansListing_004() {
			loansListingPage = dashboardPage.clickOnLoansLodule();
			createLoanPage = loansListingPage.clickOnNewLoanButton();
			loansListingPage = createLoanPage.enterAllDetails(testDataProp.getProperty("Name"), testDataProp.getProperty("MobileNumber"), testDataProp.getProperty("Email"), testDataProp.getProperty("Product"), testDataProp.getProperty("Individual"), testDataProp.getProperty("IndividualType"), testDataProp.getProperty("Amount"), testDataProp.getProperty("Date"), testDataProp.getProperty("Priority"), testDataProp.getProperty("Description"), testDataProp.getProperty("Owner"), testDataProp.getProperty("Branch"));
			loansListingPage.enterSearchByNameOrMobileNumber(testDataProp.getProperty("SearchName"));
			loansListingPage.nameValidateInLoansListing(testDataProp.getProperty("SearchName"));
		    loansListingPage.clickOnCreatedLoan(testDataProp.getProperty("SearchName"));
		    Assert.assertTrue(loansListingPage.nameValidateInLoansListing(testDataProp.getProperty("SearchName")));
		}
		
		// Validate that user is unable to create duplicate loans
		@Test(groups= {"System"})
		public void LOS_TC_LoansListing_005() {
	        loansListingPage = dashboardPage.clickOnLoansLodule();
			createLoanPage = loansListingPage.clickOnNewLoanButton();
			loansListingPage = createLoanPage.enterAllDetails(testDataProp.getProperty("Name"), testDataProp.getProperty("MobileNumber"), testDataProp.getProperty("Email"), testDataProp.getProperty("Product"), testDataProp.getProperty("Individual"), testDataProp.getProperty("IndividualType"), testDataProp.getProperty("Amount"), testDataProp.getProperty("Date"), testDataProp.getProperty("Priority"), testDataProp.getProperty("Description"), testDataProp.getProperty("Owner"), testDataProp.getProperty("Branch"));
			loansListingPage.enterSearchByNameOrMobileNumber(testDataProp.getProperty("SearchName"));
	        loansListingPage.nameValidateInLoansListing(testDataProp.getProperty("SearchName"));
			createLoanPage = loansListingPage.clickOnNewLoanButton();
			loansListingPage = createLoanPage.enterAllDetails(testDataProp.getProperty("Name"), testDataProp.getProperty("MobileNumber"), testDataProp.getProperty("Email"), testDataProp.getProperty("Product"), testDataProp.getProperty("Individual"), testDataProp.getProperty("IndividualType"), testDataProp.getProperty("Amount"), testDataProp.getProperty("Date"), testDataProp.getProperty("Priority"), testDataProp.getProperty("Description"), testDataProp.getProperty("Owner"), testDataProp.getProperty("Branch"));
	        Assert.assertTrue(createLoanPage.validateNameErrorMessage(testDataProp.getProperty("DuplicateErrorMessage")));
		}
		
		//Validate that user is able to delete the created loan and verify that loan is not displayed in loan listing page
		@Test(groups= {"System"})
		public void LOS_TC_LoansListing_006() {
	        loansListingPage = dashboardPage.clickOnLoansLodule();
			createLoanPage = loansListingPage.clickOnNewLoanButton();
			loansListingPage = createLoanPage.enterAllDetails(testDataProp.getProperty("Name"), testDataProp.getProperty("MobileNumber"), testDataProp.getProperty("Email"), testDataProp.getProperty("Product"), testDataProp.getProperty("Individual"), testDataProp.getProperty("IndividualType"), testDataProp.getProperty("Amount"), testDataProp.getProperty("Date"), testDataProp.getProperty("Priority"), testDataProp.getProperty("Description"), testDataProp.getProperty("Owner"), testDataProp.getProperty("Branch"));
		    loansListingPage.nameValidateInLoansListing(testDataProp.getProperty("Name"));
			loansListingPage.enterSearchByNameOrMobileNumber(testDataProp.getProperty("SearchName"));
		    loansDetailsPage = loansListingPage.clickOnCreatedLoan(testDataProp.getProperty("SearchName"));
		    loanDeletePage = loansDetailsPage.clickOnDeleteButton();
		    loanDeletePage.clickOnYesButton();
		    loansListingPage.nameValidateInLoansListing(testDataProp.getProperty("Name"));
	        Assert.assertFalse(loansListingPage.nameValidateInLoansListing(testDataProp.getProperty("Name")));
		    
		}
	
}
