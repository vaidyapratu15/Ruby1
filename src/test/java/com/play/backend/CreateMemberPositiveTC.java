package com.play.backend;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.play.library.BackendUtility;
import com.play.library.Utilities;
import com.play.testlibrary.BaseTest;

public class CreateMemberPositiveTC extends BaseTest{
	
	@Test(priority = 1)
	public void loginToBackend() throws IOException, ParseException, InterruptedException {	
	driver.get(BackendUtility.getFrontendData("url"));
	test.info("Username : " +mBackendPage.setUserName(BackendUtility.getFrontendData("username")));
	test.info("Password : " +mBackendPage.setPassword(BackendUtility.getFrontendData("password")));
	mBackendPage.clickSignInBtn();
	test.info("URL : " + driver.getCurrentUrl());
	}
	
	
	@Test(priority = 2)
	public void setUserData() throws InterruptedException, IOException, ParseException {
		createMember.clickonCreateMember();
		Thread.sleep(4000);	
		createMember.enterUserData();
	}
	
	@Test (priority = 3)
	public void storeUserData() throws IOException, ParseException, InterruptedException {
		createMember.storeUserData();
	}
	
	@Test (priority = 4)
	public void verifySaveButton() throws InterruptedException {
		createMember.saveBtn();
		createMember.clickOnSaveBtn();
		Thread.sleep(5000);	
	}
	
	@Test (priority = 5)
	public void getUserCreatedPopup() {
		createMember.getPopup();
	}
	
	//@Test(priority=6)
	public void logOutFromBackend() {
		createMember.setLogOut();
	}
	
	@Test(priority = 7)
	public void setUpfairplay() throws IOException, ParseException, InterruptedException {
		driver.get(BackendUtility.getFrontendData("mUrl"));
		Thread.sleep(5000);
		test.info("Url : " + driver.getCurrentUrl());
		test.info("Title : " + driver.getTitle());	
		
	}
	
	@Test(priority = 8)
	public void loginToFrontend() throws IOException, ParseException, InterruptedException {  //user stored data from userData.json
		Thread.sleep(5000);
		test.info("usename : " +swLoginpage.setUserName(Utilities.getJsonData("username")));
		test.info("password : " +swLoginpage.setPassword(Utilities.getJsonData("password")));			
		swLoginpage.clickOnlogin();	
	}
	
	@Test(priority = 9)
	public void setNewUserLimitRule() {
		swLoginpage.setUIAppDwldPopup();
		swLoginpage.clickBetLimitRuleAcceptance();
		swLoginpage.setUIAppDwldPopup();
	}
	
	@Test(priority = 10)
	public void verifyDepositAmount() {
		String expected = "1";
		String actualText =swLoginpage.getAvailableWithdrawalOnUI();
		if (actualText.equals(expected)) {
		    test.pass("By default deposit : " + actualText);
		} else {
		    test.fail("deposit value not 1");
		}
	}
	@Test(priority = 11)
	public void logOutToWeb() {
		swLoginpage.setLoggedOut();
	}
	
	
	

}

