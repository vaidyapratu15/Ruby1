package com.play.backend;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.play.library.BackendUtility;
import com.play.testlibrary.BaseTest;

public class CreateMemeberNegativeTC extends BaseTest{
	
	@Test(priority = 1)
	public void loginToBackend() throws IOException, ParseException, InterruptedException {	
	driver.get(BackendUtility.getFrontendData("url"));
	test.info("Username : " +mBackendPage.setUserName(BackendUtility.getFrontendData("username")));
	test.info("Password : " +mBackendPage.setPassword(BackendUtility.getFrontendData("password")));
	mBackendPage.clickSignInBtn();
	test.info("URL : " + driver.getCurrentUrl());
	}
	
	@Test(priority = 2)
	public void setCreateMember() throws InterruptedException {
		createMember.clickonCreateMember();
		Thread.sleep(4000);
	}
	
	@Test(priority = 3)
	public void verifySameUser() throws InterruptedException, IOException, ParseException {
		createMember.setUserName(BackendUtility.getFrontendData("mUsername"));  
	}
	
	@Test(priority = 4)
	public void verifyLessDigitMobNo() throws InterruptedException {
		createMember.setLessCharMobNo();
	}
	
	@Test(priority=5)
	public void verifyWeakPassword() throws InterruptedException {
		createMember.setWeakPassword();
	}
	
	@Test(priority=6)
	public void verifyUnmatchedPassword() throws InterruptedException {
		createMember.setWeakConfirmPassword();
	}
	
	@Test(priority = 7)
	public void verifySaveBtn() throws InterruptedException {
		createMember.clickOnSaveBtn();
		createMember.gettapToastText();
		driver.navigate().refresh();
	}
	
	@Test(priority = 8)
	public void verifyLessCharUsername() throws InterruptedException {
		createMember.setLessCharUsers();
	}
	
	@Test(priority = 9)
	public void setUserData() {
		createMember.setuserData();
	}
	@Test(priority = 10)
	public void verifySaveBtnVisiblility() {
		createMember.verifySaveBtn();
	}
	//@Test(priority = 11)
	public void clickonSaveBtn() throws InterruptedException {
		createMember.clickOnSaveBtn();
	}
	
	//@Test(priority = 12)
	public void verifyUserCreated() throws InterruptedException {
		createMember.gettapToastText();
	}
	
	@Test(priority=13)
	public void logOutFromBackend() {
		createMember.setLogOut();
	}

}
