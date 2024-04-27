package com.fairplay.module;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.play.action.ActionClass;
import com.play.library.BaseClass;

public class SuperwinLoginPage extends BaseClass{
	ActionClass action = new ActionClass();
    Actions actions = new Actions(driver);

   @FindBy (xpath=("//div[@class='v-text-field__slot']//label"))
   private WebElement username;
	
	@FindBy(xpath = ("(//div[@class='v-text-field__slot'])[2]//input"))
	private WebElement password;
	
	@FindBy(xpath = ("//button[@type='submit']//span[text()='Login']"))
	private WebElement loginBtn;
	
	@FindBy(xpath = ("//div//div[@role='status']"))
	private WebElement popup;
	
	@FindBy(xpath = "//div[contains(text(),'Logged in successfully.')]")
	private WebElement loginPopup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])")
	private WebElement close;
	
	@FindBy(xpath = "(//div[contains(@class,'v-card__actions')])//div[@class = 'v-input--selection-controls__ripple']")
	private WebElement checkBoxBtn;

	@FindBy(xpath = "(//div[contains(@class,'v-card__actions')])[2]")
	private WebElement acceptNContinue1;
	
	@FindBy(xpath="(//div[contains(@class,'v-card__actions')])//button")
	private WebElement acceptNContinue2;
	
	@FindBy(xpath="//button[contains(@class,'mr-1 v-btn')] ")
	private WebElement profileBtn;
	
	@FindBy(xpath="//button//span[contains(text(),'Logout')] ")
	private WebElement logoutBtn;
	
	@FindBy (xpath="//span[@class='user-balance white--text']")
	private WebElement availableWithdrawal;
	
	@FindBy (xpath="//div[@class='download-app-popup']//i")
	private WebElement appDwnldPopup;
	
	public SuperwinLoginPage(WebDriver driver){
     PageFactory.initElements(driver, this);
	}
	
		public void clickOnlogin() throws InterruptedException {
			Boolean btn = loginBtn.isEnabled();
			test.info("Login button isEnabled :" + btn);
			loginBtn.click();
				action.visibilityOf(driver, loginPopup, 10);
				String msg = loginPopup.getText();
				Thread.sleep(1000);
				test.info("Popup :  " + msg);
				close.click();	
		}
	 
	public void clickBetLimitRuleAcceptance() {
			try {
				acceptNContinue1.click();
				Thread.sleep(1000);
				checkBoxBtn.click();
				acceptNContinue2.click();
			} catch (Exception e) {
			}
		}
	 
	public String setUserName(String UN) throws IOException, ParseException {

		actions.moveToElement(username).click().build().perform();
		actions.sendKeys(UN).build().perform();
		return UN;
	}
	
	public String setPassword(String PWD) throws IOException, ParseException {
		actions.moveToElement(password).click().sendKeys("Password@123").build().perform();
		return PWD;
	}
	
	public void setLoggedOut() {
		profileBtn.click();
		action.jSForScroll(driver, logoutBtn);
		logoutBtn.click();
		test.info("Successfully Logged out");
	}
	
	public String getAvailableWithdrawalOnUI() {
			String text =availableWithdrawal.getText().replaceAll("[^0-9]", "");  // remove all non numeric
			test.info("Wallet Balance On Frountend UI : " + text);
			return text;
    }
	
	public void setUIAppDwldPopup() {
		try {
			appDwnldPopup.click();
			test.info("Closed download the app popup");
			//action.jSForClick(driver, appDwnldPopup);
		}catch(Exception e) {
			
		}
	}

     
}
