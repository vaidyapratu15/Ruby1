package com.play.testlibrary;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.commons.mail.EmailException;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.backend.module.CreateMemberPage;
import com.backend.module.DepositWithdrawPage;
import com.backend.module.PlayMahadevBackendPage;
import com.fairplay.module.SuperwinLoginPage;
import com.play.library.BaseClass;
import com.play.library.Utilities;

public class BaseTest extends BaseClass {
	
		
	@BeforeSuite //(alwaysRun = true)
	public void initializeBrowser() throws IOException, ParseException {	
		setUpBrowser();
	//	driver.get(Utilities.getJsonData("url"));
	}	
	@AfterSuite
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
	@BeforeTest
	public void beforeTest() {
		setExtentReport();
	}
	@AfterTest
	public void afterTest() throws IOException {
		extent.flush();
		Desktop.getDesktop().browse(new File("Extent Report.html").toURI());
	}
	@BeforeMethod
	public void createExtentTest(ITestResult result,Method m ){
//		 test = extent.createTest(m.getName());
		String testName = result.getMethod().getRealClass().getSimpleName() + " - " + result.getMethod().getMethodName();
		test = extent.createTest(testName, result.getMethod().getDescription());
	}
	@AfterMethod
	public void captureSceenshot(ITestResult result, Method m) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			Utilities.CaptureScreenshots(result.getMethod().getMethodName(), driver);
			test.log(Status.FAIL, m.getName()); // test.addScreenCaptureFromPath(ss);
		} else {
			test.log(Status.PASS, m.getName());
		} 
	}
	@AfterSuite
	public void setEmail() throws EmailException {
//		MailTestClass.setAttachEmailReport();
	}
	
	@BeforeClass
	public void pageObjects() {
		soft = new SoftAssert(); 
		mBackendPage = new PlayMahadevBackendPage(driver);
		createMember = new CreateMemberPage(driver);
		swLoginpage = new SuperwinLoginPage(driver);
		depositWithdrawPage = new DepositWithdrawPage(driver);
	}
	
	protected SoftAssert soft;
	protected PlayMahadevBackendPage mBackendPage;
	protected CreateMemberPage createMember;
	protected SuperwinLoginPage swLoginpage;
	protected DepositWithdrawPage  depositWithdrawPage;
	
	
}

























