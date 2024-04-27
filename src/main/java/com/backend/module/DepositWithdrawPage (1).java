package com.backend.module;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.play.action.ActionClass;
import com.play.library.BaseClass;

public class DepositWithdrawPage  extends BaseClass {
	ActionClass action = new ActionClass();
	Actions actions = new Actions(driver);
	
	@FindBy(xpath ="//button[contains(text(),'Deposit')]")
	private WebElement depositBtn;
	
	@FindBy(xpath ="(//tr//td[@class='ng-binding ng-scope'])[2]")
	private WebElement availableBalance;
	
	@FindBy(xpath ="//input[@name='amount']")
	private WebElement enterDepositAmount;
	
	@FindBy (xpath="//button[contains(text(),'Save')]")
	private WebElement saveBtn;
	
	@FindBy (xpath="//button[contains(text(),'Withdraw')]")
	private WebElement withdrawBtn;
	
	@FindBy (xpath="//input[@type='number']")
	private WebElement enterAmount;
	
	
	
	public DepositWithdrawPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public String getAvailableBalance1() {  // not using here value in decimal
		//String text =availableBalance.getText().replaceAll("[^0-9]", "");  // remove all non numeric 
		String text = availableBalance.getText().replaceAll("[^0-9.]", ""); 
		test.info("Available Balance On Backend : " + text);
		return text;
	}

	public String getAvailableBalance() {
        String text = availableBalance.getText().replaceAll("[^0-9.]", ""); // Extract numeric and decimal values
        test.info("Available Balance: " + text);  
	    double balance = Double.parseDouble(text); 
     //  double roundedBalance = Math.ceil(balance); 
	    double roundedBalance = Math.floor(balance);
        long roundedValue = (long) roundedBalance; 
        String roundedText = Long.toString(roundedValue); 
        test.info("Available Balance On Backend: " + roundedText);
        return roundedText;
    }


	
	public void clickOnDeposit() {
		test.info("Deposite button isEnabled : " + depositBtn.isEnabled() );
		depositBtn.click();
	}
	
	public String setDepositeAmount() {
		enterDepositAmount.sendKeys(Keys.ENTER + "10");
		actions.doubleClick(enterDepositAmount).perform();
		String text = enterDepositAmount.getAttribute("value");
		test.info(" Deposite Amount : "+ text);
		return text;	
	}
	
	public void clickOnSaveBtn() {
		test.info("Save button isEnabled : " + saveBtn.isEnabled());
		saveBtn.click();
		test.info("Clicked on save button");
	}
	
	public void clickOnWithdrawBtn() throws InterruptedException {
		withdrawBtn.click();
		test.info("Clicked on withdraw button");
		Thread.sleep(2000); 
		actions.doubleClick(enterAmount).perform();
		String text = enterAmount.getAttribute("value");
		test.info("By default Amount For Withdraw : "+ text);
		test.info("Save button isEnabled : "+ saveBtn.isEnabled());
	}
	
	public void setEnterAmount() throws IOException, ParseException, InterruptedException {
		test.info("Save btn isEnabled : " +saveBtn.isEnabled());
		actions.doubleClick(enterAmount).sendKeys(Keys.DELETE).perform();
		actions.moveToElement(enterAmount).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
	
		Thread.sleep(2000);
		enterAmount.sendKeys("10");
		actions.doubleClick(enterAmount).perform();
		String text = enterAmount.getAttribute("value");
		test.info("Amount For Withdraw : "+ text);
		Thread.sleep(2000);	
	}
	
	public void setSaveBtn() {
		  test.info("Save btn  isEnabled : " +saveBtn.isEnabled());
		    boolean isEnabled = saveBtn.isEnabled();
		    if (isEnabled) {
		    	saveBtn.click();
		        test.pass("Clicked on Save button");     
		    } else {
		        test.fail("Save button is not visible on the page");
		    }
	}
	

}
