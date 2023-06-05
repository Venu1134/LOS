package com.trisysLOS.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoansDeatilsPage {

	WebDriver driver;
	
	public LoansDeatilsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(text(),'Loan Details')]")
	private WebElement loansDetailsHeading;
	
	@FindBy(xpath="//*[contains(@title,'Edit Loan')]//i")
	private WebElement editButton;
	
	@FindBy(xpath="//*[contains(@title,'Delete Loan')]//i")
	private WebElement deleteButton;
	
	public EditLoanPage clickOnEditButton() {
		editButton.click();
		return new EditLoanPage(driver);
	}
	
	public LoanDeletePage clickOnDeleteButton() {
		deleteButton.click();
		return new LoanDeletePage(driver);
	}
	
	public boolean validateLoansDeatilsPage() {
		return loansDetailsHeading.isDisplayed();
	}
}
