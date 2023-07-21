package com.trisysLOS.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

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
	
	@FindBy(xpath="//*[@id='dealTable']//td[1]//a")
	private List<WebElement> NameList;
	
	@FindBy(xpath="//*[contains(@title,'Trisys CRM')]")
	private WebElement logo;
	
	public DashboardPage clickOnlogo() {
		logo.click();
		return new DashboardPage(driver);
	}
	
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
			Wait<WebDriver> wait = null;
			try {
				wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(20))
						.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
				wait.until(ExpectedConditions.visibilityOfAllElements(NameList));
				boolean actualNameResult = NameList.get(i).getText().equals(Name);
				if(actualNameResult == true) {
					NameList.get(i).click();
					break;
				}
			}catch(Exception e) {
				e.printStackTrace();
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
