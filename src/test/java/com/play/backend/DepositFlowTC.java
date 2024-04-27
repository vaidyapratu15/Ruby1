package com.play.backend;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.play.library.BackendUtility;
import com.play.testlibrary.BaseTest;

public class DepositFlowTC extends BaseTest{
	
	@Test(priority = 1)
	public void loginToBackend() throws IOException, ParseException, InterruptedException {	
	driver.get(BackendUtility.getFrontendData("url"));
	test.info("Username : " +mBackendPage.setUserName(BackendUtility.getFrontendData("username")));
	test.info("Password : " +mBackendPage.setPassword(BackendUtility.getFrontendData("password")));
	mBackendPage.clickSignInBtn();
	test.info("URL : " + driver.getCurrentUrl());
	}
	
	@Test(priority = 2)
	public void searchUserName() throws InterruptedException, IOException, ParseException {
		test.info("User : "+mBackendPage.setSearchUser(BackendUtility.getFrontendData("user")));
		mBackendPage.clickOnSearchBtn();
	}

	@Test(priority = 3)
	public void getBalanceInfo() {
		depositWithdrawPage.getAvailableBalance();
	}
	
	@Test(priority = 4)
	public void clickOnDepositBtn() {
		depositWithdrawPage.clickOnDeposit();
		test.info("Clicked on deposite button");
	}
	
	@Test(priority =5)
	public void enterAmountForDeposit() {
		depositWithdrawPage.setDepositeAmount();
	}
	
	@Test(priority = 6)
	public void clickOnSaveButton() {
		depositWithdrawPage.clickOnSaveBtn();
	}
	
	@Test (priority = 7)
	public void verifyWalletAmount() throws InterruptedException, IOException, ParseException {
		Thread.sleep(3000);
		String onBackend =depositWithdrawPage.getAvailableBalance();
		setFrontend();
		frontendLogin();
		setNewUserLimitRule();
		String onFpUI =swLoginpage.getAvailableWithdrawalOnUI();
		if (onFpUI.equals(onBackend)) {
		    test.pass("Available Balance on Backend : " + onBackend +"  |  " + "Available Balance on UI : " + onFpUI);
		} else {
		    test.fail("Available Balance value not matched as on backend");
		}
	}
	
	@Test(priority = 8)
	public void logOutToWeb() {
		swLoginpage.setLoggedOut();
	}

	
	public void setFrontend() throws IOException, ParseException, InterruptedException {
		driver.get(BackendUtility.getFrontendData("mUrl")); 
		test.info("Checking Available Withdrawal On UI  ");
		test.info("Url : " + driver.getCurrentUrl());			
	}
	
	public void frontendLogin() throws IOException, ParseException, InterruptedException {
		test.info("usename : " +swLoginpage.setUserName(BackendUtility.getFrontendData("mUsername")));
		swLoginpage.setPassword(BackendUtility.getFrontendData("mPassword"));			
        swLoginpage.clickOnlogin();
		swLoginpage.setUIAppDwldPopup();
	}
	public void setNewUserLimitRule() {
		try {
			swLoginpage.clickBetLimitRuleAcceptance();
		}catch(Exception e) { }
	}
	public void logOutFromBackend() {
		createMember.setLogOut();
	}
	
	
	
}
