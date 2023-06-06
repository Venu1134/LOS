package com.trisysLOS.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.trisysLOS.baseClass.BaseClass;
import com.trisysLOS.pageObjects.AdminLoginPage;
import com.trisysLOS.pageObjects.CreateLoanPage;
import com.trisysLOS.pageObjects.DashboardPage;
import com.trisysLOS.pageObjects.LoansListingPage;
import com.trisysLOS.utilities.JiraCreateIssue;
import com.trisysLOS.utilities.UtilityClass;

import static com.trisysLOS.utilities.ExtentTestManager.startTest;

public class CreateLoanPageTest extends BaseClass {

	public CreateLoanPageTest() {
		super();
	}

	public WebDriver driver;
	public AdminLoginPage adminLoginPage;
	public DashboardPage dashboardPage;
	public LoansListingPage loansListingPage;
	public CreateLoanPage createLoan;

	@Parameters("browser")
	@BeforeMethod(groups= {"Regression","Sanity","Negative","Positive"},alwaysRun = true)
	public void setUp(String browser) {
		driver = initilizeBrowser(browser);
		adminLoginPage = new AdminLoginPage(driver);
		dashboardPage = adminLoginPage.EnterValidLoginCredentials(prop.getProperty("UserName"),
				prop.getProperty("Password"));
		loansListingPage = dashboardPage.clickOnLoansLodule();
		createLoan = loansListingPage.clickOnNewLoanButton();
	}

	@AfterMethod(groups= {"Regression","Sanity","Negative","Positive"},alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Sanity","Positive"}, priority=1)
	public void LOS_TC_CreateLoan_002(Method method) {
		startTest(method.getName(),"Verify if admin is able to create Loan by entering data in all mandatory fields.");
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("Amount"));
		createLoan.enterDate(testDataProp.getProperty("Date"));
		createLoan.selectPriority(testDataProp.getProperty("Priority"));
		loansListingPage = createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(loansListingPage.getLoansPageURL(testDataProp.getProperty("loansURL")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"}, priority=3)
	public void LOS_TC_CreateLoan_003() {
		createLoan.enterName(null);
		createLoan.enterMobileNumber(null);
		createLoan.enterEmailID(null);
		createLoan.selectIndividual(null);
		createLoan.selectIndividualType(null);
		createLoan.enterAmount(null);
		createLoan.enterDate(null);
		createLoan.selectPriority(null);
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.nameFieldRequired());
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Sanity","Positive"},priority=1)
	public void LOS_TC_CreateLoan_004() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectProduct(testDataProp.getProperty("Product"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("Amount"));
		createLoan.enterDate(testDataProp.getProperty("Date"));
		createLoan.selectPriority(testDataProp.getProperty("Priority"));
		createLoan.enterDescription(testDataProp.getProperty("Description"));
		createLoan.selectOwner(testDataProp.getProperty("Owner"));
		createLoan.selectBranch(testDataProp.getProperty("Branch"));
		loansListingPage = createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(loansListingPage.getLoansPageURL(testDataProp.getProperty("loansURL")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Sanity","Negative"},priority=2)
	public void LOS_TC_CreateLoan_005() {
		createLoan.clickOnCreateLoanWithOutDetails();
		Assert.assertTrue(createLoan.nameFieldRequired());
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=1)
	public void LOS_Tc_CreateLoan_006() {
		createLoan.selectProduct(testDataProp.getProperty("Product"));
		Assert.assertTrue(createLoan.getProductName(testDataProp.getProperty("Product"))); 
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=1)
	public void LOS_TC_CreateLoan_007() {
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		Assert.assertTrue(createLoan.getIndividualText(testDataProp.getProperty("Individual")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"}, priority=1)
	public void LOS_TC_CreateLoan_008() {
		createLoan.selectPriority(testDataProp.getProperty("Priority"));
		Assert.assertTrue(createLoan.getPriorityText(testDataProp.getProperty("Priority")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=1)
	public void LOS_TC_CreateLoan_009(Method method) {
		createLoan.selectOwner(testDataProp.getProperty("Owner"));
		Assert.assertTrue(createLoan.getOwnerName(testDataProp.getProperty("Owner")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=1)
	public void LOS_TC_CreateLoan_010() {
		createLoan.selectBranch(testDataProp.getProperty("Branch"));
		Assert.assertTrue(createLoan.getBranchName(testDataProp.getProperty("Branch")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=1)
	public void LOS_TC_CreateLoan_012() {
		createLoan.enterDate(testDataProp.getProperty("Date"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(createLoan.getNeedByDate(testDataProp.getProperty("Date"))); 
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=1)
	public void LOS_TC_CreateLoan_015() {
		createLoan.enterDate(testDataProp.getProperty("PastDate"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validdateDateErrorMessage(testDataProp.getProperty("PastDateActualErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3) 
	public void LOS_TC_CreateLoan_016() {
		createLoan.enterName(testDataProp.getProperty("NameAlphaSpace"));
		createLoan.clickOnCreateLoanButton();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.getName(testDataProp.getProperty("NameAlphaSpace")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_017() {
		createLoan.enterName(testDataProp.getProperty("NameNumbers"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateNameErrorMessage(testDataProp.getProperty("NameErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_018() {
		createLoan.enterName(testDataProp.getProperty("SpecialCharacters"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateNameErrorMessage(testDataProp.getProperty("NameErrorMessage")));
	}

	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_019() {
		createLoan.enterName(testDataProp.getProperty("NameNumberSpecialCharacters"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateNameErrorMessage(testDataProp.getProperty("NameErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_020() {
		createLoan.enterName(testDataProp.getProperty("NameAlphaNumberSpecialCharacters"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateNameErrorMessage(testDataProp.getProperty("NameErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=3)
	public void LOS_TC_CreateLoan_021() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.clickOnCreateLoanButton();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(createLoan.validateNameErrorMessage(testDataProp.getProperty("Name")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_022() {
		createLoan.enterName(testDataProp.getProperty("NameSpaces"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateNameErrorMessage(testDataProp.getProperty("NameErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_023() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.getMobileNumber(testDataProp.getProperty("MobileNumber")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=2)
	public void LOS_TC_CreateLoan_024() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("NineDigitMobileNumber"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateMobileNumberErrorMessage(testDataProp.getProperty("NineDigitMobileNumberErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=2) 
	public void LOS_TC_CreateLoan_025() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("TwelveDigitMobileNumber"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.getMobileNumber(testDataProp.getProperty("TwelveDigitMobileNumber")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_026() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("Name"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateMobileNumberErrorMessageWithText(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_027() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("SpecialCharacters"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateMobileNumberErrorMessageWithText(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_028() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("NameAlphaNumberSpecialCharacters"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateMobileNumberErrorMessageWithText(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=2)
	public void LOS_TC_CreateLoan_29() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("Amount"));
		createLoan.enterDate(testDataProp.getProperty("Date"));
		createLoan.selectPriority(testDataProp.getProperty("Priority"));
		createLoan.selectOwner(testDataProp.getProperty("Owner"));
		createLoan.selectBranch(testDataProp.getProperty("Branch"));
		loansListingPage = createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateMobileNumberErrorMessageWithText(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_030() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Name"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateEmailErrorMessage(testDataProp.getProperty("EmailIDErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_031() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.getEmailId(testDataProp.getProperty("Email")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=3)
	public void LOS_TC_CreateLoan_036() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("Amount"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.getAmount(testDataProp.getProperty("Amount")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_037() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("AmountInWords"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateAmountField(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_038() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("SpecialCharacters"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateAmountField(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=3)
	public void LOS_TC_CreateLoan_039() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("NameAlphaNumberSpecialCharacters"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateAmountField(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=2)
	public void LOS_TC_CreateLoan_040() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("NegativeAmount"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateAmountField(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=2)
	public void LOS_TC_CreateLoan_041() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("DecimalAmount"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.validateAmountField(testDataProp.getProperty("MobileNumberTextErrorMessage")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=2)
	public void LOS_TC_CreateLoan_042() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("ZeroAmount"));
		createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(createLoan.getAmount(testDataProp.getProperty("ZeroAmount")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Negative"},priority=2)
	public void LOS_TC_CreateLoan_046() {
		createLoan.enterName(testDataProp.getProperty("Name"));
		createLoan.enterMobileNumber(testDataProp.getProperty("MobileNumber"));
		createLoan.enterEmailID(testDataProp.getProperty("Email"));
		createLoan.selectIndividual(testDataProp.getProperty("Individual"));
		createLoan.selectIndividualType(testDataProp.getProperty("IndividualType"));
		createLoan.enterAmount(testDataProp.getProperty("Amount"));
		createLoan.enterDate(testDataProp.getProperty("Date"));
		createLoan.selectPriority(testDataProp.getProperty("Priority"));
		loansListingPage = createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(loansListingPage.getLoansPageURL(testDataProp.getProperty("loansURL")));
	}
	
	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"},priority=1)
	public void LOS_TC_CreateLoan_47() {
		loansListingPage = createLoan.clickOnCancelButton();
		Assert.assertTrue(loansListingPage.getLoansPageURL(testDataProp.getProperty("loansURL")));
	}

	@JiraCreateIssue(isCreateIssue = true)
	@Test(groups= {"Regression","Positive"}, dataProvider = "LoanCreationData",priority=1)
	public void LOS_TC_CreationLoan_48(String Name, String MobileNumber, String Email, String Product,
			String Individual, String IndividualType, String Amount, String NeedByDate,
			String Priority, String Branch) {
		createLoan.enterName(Name);
		createLoan.enterMobileNumber(MobileNumber);
		createLoan.enterEmailID(Email);
		createLoan.selectIndividual(Product);
		createLoan.selectIndividual(Individual);
		createLoan.selectIndividualType(IndividualType);
		createLoan.enterAmount(Amount);
		createLoan.enterDate(NeedByDate);
		createLoan.selectPriority(Priority);
		createLoan.selectBranch(Branch);
		loansListingPage = createLoan.clickOnCreateLoanButton();
		Assert.assertTrue(loansListingPage.getLoansPageURL(testDataProp.getProperty("loansURL")));
	}

	@DataProvider(name = "LoanCreationData")
	public Object[][] getApplicationCreationData() {
		Object[][] applicationData = UtilityClass.getTestDataFromExcel("LoanCreationData");
		return applicationData;
	}
}
