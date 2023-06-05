package com.trisysLOS.pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditLoanPage {

	WebDriver driver;
	
	public EditLoanPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="dealName")
	private WebElement name;
	
	@FindBy(id="mobile")
	private WebElement mobileNumber;
	
	@FindBy(id="email")
	private WebElement emailID;
	
	@FindBy(xpath="//*[contains(@title,'New Car Loan')]")
	private WebElement product;
	
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
	
	@FindBy(id="closeDate")
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
	
	@FindBy(xpath="//*[contains(@title,'Update')]")
	private WebElement saveButton;
	
	@FindBy(xpath="//*[text()='Cancel']")
	private WebElement cancelButton;
	
	@FindBy(xpath="//*[contains(text(),'Edit Loan')]")
	private WebElement editLoanPageHeading;
	
	public boolean validateEditLoanPageHeading() {
		return editLoanPageHeading.isDisplayed();
	}
	
	public void enterName(String Name) {
		name.sendKeys(Name);
	}
	
	public void enterMobileNumber(String MobileNumber) {
		mobileNumber.sendKeys(MobileNumber);
	}
	
	public void enterEmailID(String Email) {
		emailID.sendKeys(Email);
	}
	
	public void selectProduct(String prodcutName) {
		//product.click();
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
	
	public void enterDate(String Date) {
		date.sendKeys(Date);
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
	
	public LoansListingPage clickOnSaveButton() {
		saveButton.click();
		return new LoansListingPage(driver);
	}
	
	public LoansListingPage clickOnCancelButton() {
		cancelButton.click();
		return new LoansListingPage(driver);
	}
}
