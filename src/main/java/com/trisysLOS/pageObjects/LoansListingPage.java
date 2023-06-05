package com.trisysLOS.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoansListingPage {

	WebDriver driver;
	public LoansListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='alert alert-success']")
	private WebElement loanDeleteMessage;
	
	@FindBy(xpath="//*[@title='New Loan']")
	private WebElement newLoanButton;
	
	@FindBy(xpath="(//*[@placeholder='Search Name or Mobile Number'])[1]")
	private WebElement searchByNameOrMobileNumber;
	
	@FindBy(xpath="//*[@id='dealTable']//td[1]")
	private List<WebElement> NameList;
	
	public boolean nameValidateInLoansListing(String Name) {
		boolean actualNameResult = false;
		for(int i=0; i<NameList.size(); i++) {
			actualNameResult = NameList.get(i).getText().equalsIgnoreCase(Name);
			break;
		}
		return actualNameResult;
	}
	
	public LoansDeatilsPage clickOnCreatedLoan(String Name) {
		for(int i=0; i<NameList.size(); i++) {
			boolean actualNameResult = NameList.get(i).getText().equals(Name);
			if(actualNameResult == true) {
				NameList.get(i).click();
			}
		}
		return new LoansDeatilsPage(driver);
	}
	
	public void enterSearchByNameOrMobileNumber(String Name) {
		searchByNameOrMobileNumber.sendKeys(Name);
	}
	
	public boolean getLoansPageURL(String loansURL) {
		return driver.getCurrentUrl().equals(loansURL);
	}
	
	public CreateLoanPage clickOnNewLoanButton() {
		newLoanButton.click();
		return new CreateLoanPage(driver);
	}
	
	public boolean validateLoanDeleteMessage() {
		return loanDeleteMessage.isDisplayed();
	}
}
