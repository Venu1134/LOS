package com.trisysLOS.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class CreateLoanPage {

	WebDriver driver;
	
	public CreateLoanPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="dealName")
	private WebElement name;
	
	@FindBy(id="mobile")
	private WebElement mobileNumber;
	
	@FindBy(id="email")
	private WebElement emailID;
	
	@FindBy(xpath="(//*[contains(text(),'New Car Loan')])[3]")
	private WebElement product;
	
	@FindBy(xpath="(//*[@class='selection']/span/span)[1]")
	private WebElement productName;
	
	@FindBy(xpath="//*[@id='select2-product-o1-results']//li")
	private List<WebElement> productList;
	
	@FindBy(id="individual")
	private WebElement individual;
	
	@FindBy(xpath="//*[@id='individual']//option")
	private List<WebElement> individualList;
	
	@FindBy(id="individualType")
	private WebElement individualType;
	
	@FindBy(xpath="//*[@id='individualType']//option")
	private List<WebElement> individualTypeList;
	
	@FindBy(id="dealAmount")
	private WebElement amount;
	
	@FindBy(xpath="//*[@id='closeDate']")
	private WebElement date;
	
	@FindBy(id="dealPriority")
	private WebElement priority;
	
	@FindBy(xpath="//*[@id='dealPriority']//option")
	private List<WebElement> priorityList;
	
	@FindBy(id="dealDescription")
	private WebElement description;
	
	@FindBy(name="owner")
	private WebElement owner;
	
	@FindBy(xpath="//*[@name='owner']//option")
	private List<WebElement> ownerList;
	
	@FindBy(xpath="(//*[@name='owner'])[2]")
	private WebElement branch;
	
	@FindBy(xpath="(//*[@name='owner'])[2]//option")
	private List<WebElement> branchList;
	
	@FindBy(xpath="//*[@title='Add']")
	private WebElement createLoanButton;
	
	@FindBy(xpath="//*[text()='Cancel']")
	private WebElement cancelButton;
	
	public void enterName(String Name) {
		name.sendKeys(Name);
	}
	
	public boolean getName(String Name) {
		System.out.println("Name : "+name.getText());
		return name.getText().equalsIgnoreCase(Name);
	}
	
	public boolean validateNameErrorMessage(String expectedErrorMessage) {
		System.out.println("error Message : "+name.getAttribute("title"));
		return name.getAttribute("title").equals(expectedErrorMessage);
	}
	
	public void enterMobileNumber(String MobileNumber) {
		mobileNumber.sendKeys(MobileNumber);
	}
	
	public boolean validateMobileNumberErrorMessage(String expectedErrorMessage) {
		System.out.println("error Message : "+mobileNumber.getAttribute("validationMessage"));
		return mobileNumber.getAttribute("validationMessage").equals(expectedErrorMessage);
	}
	
	public boolean validateMobileNumberErrorMessageWithText(String expectedErrorMessage) {
		System.out.println("error Message : "+mobileNumber.getAttribute("title"));
		return mobileNumber.getAttribute("title").equals(expectedErrorMessage);
	}
	
	public boolean getMobileNumber(String MobileNumber) {
		//mobileNumber.click();
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", mobileNumber);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Mobile Number : "+mobileNumber.getText());
		return mobileNumber.getText().equals(MobileNumber);
	}
	
	public void enterEmailID(String Email) {
		emailID.sendKeys(Email);
	}
	
	public boolean getEmailId(String Email) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", emailID);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("email ID : "+emailID.getText());
		return emailID.getText().equals(Email);
	}
	
	public boolean validateEmailErrorMessage(String expectedErrorMessage) {
		System.out.println("error Message : "+emailID.getAttribute("validationMessage"));
		return emailID.getAttribute("validationMessage").equals(expectedErrorMessage);
	}
	
	public void selectProduct(String prodcutName) {
		//product.click();
		Wait<WebDriver> wait = null;
		wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
		wait.until(ExpectedConditions.visibilityOfAllElements(product));
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", product);
		
		for(int i=0; i<productList.size(); i++) {
			boolean actualProductName = productList.get(i).getText().equalsIgnoreCase(prodcutName);
			if(actualProductName == true) {
				productList.get(i).click();
				break;
			}
		}	
	}
	
	public void selectIndividual(String Individual) {
		//individual.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", individual);
		
		for(int i=0; i<individualList.size();i++) {
			boolean actualIndividual = individualList.get(i).getText().equalsIgnoreCase(Individual);
			if(actualIndividual == true) {
				individualList.get(i).click();
				break;
			}
		}
	}
	
	public boolean getIndividualText(String Individual) {
		System.out.println("Individual : "+individual.getText());
		return individual.getText().contains(Individual);
	}
	
	public void selectIndividualType(String IndividualType) {
		//individualType.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", individualType);
		
		for(int i=0; i<individualTypeList.size(); i++) {
			boolean actualIndividualType = individualTypeList.get(i).getText().equalsIgnoreCase(IndividualType);
			if(actualIndividualType == true) {
				individualTypeList.get(i).click();
				break;
			}
		}
	}
	
	public void enterAmount(String Amount) {
		amount.sendKeys(Amount);
	}
	
	public boolean getAmount(String Amount) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", amount);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Amount : "+amount.getText());
		return amount.getText().equals(Amount);
	}
	
	public boolean validateAmountField(String expectedErrorMessage) {
		System.out.println("error Message : "+amount.getAttribute("title"));
		return amount.getAttribute("title").equals(expectedErrorMessage);
	}
	
	public void enterDate(String Date) {
		date.sendKeys(Date);
	}
	
	public boolean validdateDateErrorMessage(String expectedErrorMessage) {
		return date.getAttribute("validationMessage").equals(expectedErrorMessage);
	}
	
	public boolean getNeedByDate(String Date) {
		System.out.println("Date : "+date.getText());
		return date.getText().equals(Date);
	}
	
	public void selectPriority(String Priority) {
		//priority.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", priority);
		
		for(int i=0;i<priorityList.size(); i++) {
			boolean actualPriority = priorityList.get(i).getText().equalsIgnoreCase(Priority);
			if(actualPriority == true) {
				priorityList.get(i).click();
				break;
			}
		}
	}
	
	public boolean getPriorityText(String Priority) {
		return priority.getText().contains(Priority);
	}
	
	public void enterDescription(String Description) {
		description.sendKeys(Description);
	}
	
	public void selectOwner(String Owner) {
		//owner.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", owner);
		
		for(int i=0; i<ownerList.size(); i++) {
			boolean actualOwner = ownerList.get(i).getText().equalsIgnoreCase(Owner);
			if(actualOwner == true) {
				ownerList.get(i).click();
				break;
			}
		}
	}
	
	public boolean getOwnerName(String Owner) {
		System.out.println("Owner "+owner.getText());
		return owner.getText().contains(Owner);
	}
	
	public void selectBranch(String Branch) {
		//branch.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", branch);
		
		for(int i=0; i<branchList.size(); i++) {
			boolean actualBranch = branchList.get(i).getText().equalsIgnoreCase(Branch);
			if(actualBranch == true) {
				branchList.get(i).click();
				break;
			}
		}
	}
	
	public boolean getBranchName(String Branch) {
		System.out.println("Branch : "+branch.getText());
		return branch.getText().contains(Branch);
	}
	
	public LoansListingPage clickOnCreateLoanButton() {
		createLoanButton.click();
		return new LoansListingPage(driver);
	}
	
	public LoansListingPage clickOnCancelButton() {
		cancelButton.click();
		return new LoansListingPage(driver);
	}
	
	public CreateLoanPage clickOnCreateLoanWithOutDetails() {
		createLoanButton.click();
		return new CreateLoanPage(driver);
	}
	
	public boolean nameFieldRequired() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",name);
		return isRequired;
	}
	
	public boolean getProductName(String ProductName) {
		System.out.println("Product Name : "+productName.getText());
		return productName.getText().contains(ProductName);
	}
	
	public LoansListingPage enterAllDetails(String Name, String MobNumber, String Email,String Product, String Individual,String IndividualTypes , String Amounts ,String Date , String Priority , String Description , String Owner , String Branch) {
		CreateLoanPage clp = new CreateLoanPage(driver);
		clp.enterName(Name);
		clp.enterMobileNumber(MobNumber);
		clp.enterEmailID(Email);
		clp.selectProduct(Product);
		clp.selectIndividual(Individual);
		clp.selectIndividualType(IndividualTypes);
		clp.enterAmount(Amounts);
		clp.enterDate(Date);
		clp.selectPriority(Priority);
		clp.enterDescription(Description);
		clp.selectOwner(Owner);
		clp.selectBranch(Branch);	
		clp.clickOnCreateLoanButton(); 
		return new LoansListingPage(driver);
	}
	
}
