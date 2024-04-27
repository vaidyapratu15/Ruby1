package com.play.backend;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.play.library.BackendUtility;
import com.play.testlibrary.BaseTest;

public class BonusFlowTC extends BaseTest{
	
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
	public void clickWithdrawButton() throws InterruptedException {
		mBackendPage.clickOnWithdrawBtn();
	}
	
	@Test(priority =4)
	public void getWithdrawData() {
		mBackendPage.getWithdrawData();
	}
	
	@Test(priority=5)
	public void storeAvailableWithdrawData() throws IOException, ParseException, InterruptedException {
		mBackendPage.getStoreData();
	}
	@Test(priority=6)
	public void enterAmountForWithdraw() throws IOException, ParseException, InterruptedException {
		mBackendPage.setEnterAmount();
	}
	
	@Test(priority=7)
	public void verifySaveButtonVisibility() {
		mBackendPage.setSaveBtn();	
	}
	
	@Test(priority=8)
	public void logoutFromBackend() {
		mBackendPage.setLogOut();
	}
	
//	test.info("Username : " +mBackendPage.setUserName(EnvConfigReader.getUsername()));
}
