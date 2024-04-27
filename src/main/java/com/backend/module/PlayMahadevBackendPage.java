package com.backend.module;

import java.io.IOException;
import java.time.Duration;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.play.action.ActionClass;
import com.play.library.BackendUtility;
import com.play.library.BaseClass;

public class PlayMahadevBackendPage extends BaseClass {
	ActionClass action = new ActionClass();
	Actions actions = new Actions(driver);
	
	@FindBy (id="username") 
	private WebElement username;
	
	@FindBy (id="password") 
	private WebElement password;
	
	@FindBy (id="sign-in") 
	private WebElement signIn;
	
	@FindBy (xpath="//div[@class = 'col-md-12 memberlist']//input[@type='text']")
	private WebElement searchUser;
	
	@FindBy (xpath="//div//button[@ng-click='searchInTable(search)']")
	private WebElement searchBtn;
	
	@FindBy (xpath="//button[contains(text(),'Withdraw')]")
	private WebElement withdrawBtn;
	
	@FindBy (xpath = "//button[contains(text(),'Save')]")
	private WebElement saveBtn;
	
	@FindBy (xpath="//input[@type='number']")
	private WebElement enterAmount;
	
	@FindBy (xpath="((//div[@class='ng-scope'])[1]//div)[7]")
	private WebElement walletAmount;
	
	@FindBy (xpath="((//div[@class='ng-scope'])[1]//div)[8]")
	private WebElement NetExposure;
	
	@FindBy (xpath="((//div[@class='ng-scope'])[1]//div)[9]")
	private WebElement bonus;
	
	@FindBy (xpath="((//div[@class='ng-scope'])[1]//div)[10]")
	private WebElement availableWithdrawal;
	
	@FindBy (xpath="//button[contains(text(),'Cancel')]")
	private WebElement cancelBtn;
	
	@FindBy (xpath="(//div[@class='logo col-sm-2']//span)[2]")
	private WebElement menuBtn;
	
	@FindBy (id="logout")
	private WebElement logOutBtn;
	
	public PlayMahadevBackendPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}
	
	
	
	public String setUserName(String UN) throws IOException, ParseException {
		username.sendKeys(UN);	
		return UN;
	}
	public String setPassword(String PWD) throws IOException, ParseException {
		password.sendKeys(PWD);
		return PWD;
	}
	public void clickSignInBtn() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.elementToBeClickable(signIn));
	    signIn.click();
	    Thread.sleep(15000);
	}
	
	public String setSearchUser(String user) {
		test.info(" search tab isEnabled: "+searchUser.isEnabled());
		searchUser.sendKeys(user);
		return user;
	}
	
	public void clickOnSearchBtn() throws InterruptedException {
		searchBtn.click();
		test.info("Clicked on search button");
		Thread.sleep(4000);
	}
	
	public void clickOnWithdrawBtn() throws InterruptedException {
		withdrawBtn.click();
		test.info("Clicked on withdraw button");
		Thread.sleep(3000); 
		actions.doubleClick(enterAmount).perform();
		String text = enterAmount.getAttribute("value");
		test.info("By default Amount For Withdraw : "+ text);
		test.info("Save button isEnabled : "+ saveBtn.isEnabled());
	}
	
	public void getWithdrawData() {
		test.info("Wallet Amount : " + walletAmount.getText());
		test.info("Net Exposure: : " + NetExposure.getText());
		test.info("Bonus : " + bonus.getText());
		test.info("Available Withdrawal : " + availableWithdrawal.getText());		
	}
	
	public void getStoreData() throws IOException, ParseException, InterruptedException {
		BackendUtility.storeWithdrawData(availableWithdrawal, "availableWithdrawal", "/Json/BackendJsonData/withdrawData.json");
		test.info("availableWithdrawal data stored in .jsonFile"); Thread.sleep(6000);
	}
	
	public void setEnterAmount() throws IOException, ParseException, InterruptedException {
		test.info("Save btn isEnabled : " +saveBtn.isEnabled());
		actions.doubleClick(enterAmount).sendKeys(Keys.DELETE).perform();
		actions.moveToElement(enterAmount).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
	
		Thread.sleep(2000);
		enterAmount.sendKeys(BackendUtility.getLatestData("Json/BackendJsonData/withdrawData.json", "availableWithdrawal"));
		test.info("Amount stored in json file : "+  BackendUtility.getLatestData("Json/BackendJsonData/withdrawData.json", "availableWithdrawal"));
//		enterAmount.sendKeys("10");
		Thread.sleep(2000);	
	}
	
	public void setSaveBtn() {
		  test.info("Save btn  isEnabled : " +saveBtn.isEnabled());
		    boolean isEnabled = saveBtn.isEnabled();
		    if (isEnabled) {
		        test.fail("Save button is visible on the page");
		    } else {
		        test.pass("Save button is not visible on the page");
		    }
	}
	
	public void setLogOut() {
		cancelBtn.click();
		test.info("clicked on cancel button");
		menuBtn.click();
		logOutBtn.click();
		test.info("Successfully logged out");
	}
	

}
