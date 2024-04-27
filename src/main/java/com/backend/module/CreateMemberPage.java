package com.backend.module;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.play.action.ActionClass;
import com.play.action.RandomGenerator;
import com.play.library.BaseClass;


public class CreateMemberPage extends BaseClass {
	ActionClass action = new ActionClass();
    RandomGenerator random = new RandomGenerator();
    String name;
    String mobileNumber;
    String password = "Password@123";
    String confirmPassword = password;

    @FindBy(xpath = "//div//button[@ui-sref='createmember']")
    private WebElement createMember;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "mobileno")
    private WebElement mobileNo;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "cpassword")
    private WebElement confirmPasswordInput;
    
    @FindBy(id = "playerDeposit")
    private WebElement deposit;
    
    @FindBy(xpath = "//div//button[@type='submit']")
    private WebElement saveBtn;
    
    @FindBy (xpath="(//div[@class='logo col-sm-2']//span)[2]")
	private WebElement menuBtn;
	
	@FindBy (id="logout")
	private WebElement logOutBtn;
	
	@FindBy(xpath = ("//div[@id='toast-container']//div[contains(text(),'User  Created Successfully')]"))
	private WebElement userCreatedPopup;

	@FindBy (xpath="//div[@id='toast-container']//div[contains(text(),'Unable to Create User')]")
	private WebElement tapToastText;  
	
	@FindBy (xpath="///div[contains(@class,'toast-message')]")
	private WebElement tapToastClass; ////div[contains(text(),'Unable to Create User')]
	
	@FindBy (xpath="//div[contains(text(),'User Name already exist')]")
	private WebElement userFiled;
	
    @FindBy (xpath="//div[contains(text(),' Username must be 8 Character')]")
	private WebElement lessUserFiled;

	@FindBy (xpath="//div[contains(text(),'Please enter 10 digit mobile no')]")
	private WebElement mobField;
	
	@FindBy (xpath="//div[contains(text(),'Password should be atleast 8 characters long.')]")
	private WebElement passwordField;
	
	@FindBy (xpath="(//div[contains(text(),'Confirm Password')])[2]")
	private WebElement confirmPasswordField;
	
    
    public CreateMemberPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
   
    public void clickonCreateMember() {
        createMember.click();
        test.info("Clicked on create user button");
    }

    public void enterUserData() {
        name = random.generateRandomUsername();
        test.info("Username : " + name);
        username.sendKeys(name);
        mobileNumber = random.generateRandomNumber(10);
        mobileNo.sendKeys(mobileNumber);
        test.info("Mobile No : " + mobileNumber);
        passwordInput.sendKeys(password);
        test.info("Password : " + password);
        confirmPasswordInput.sendKeys(confirmPassword);
  //      deposit.sendKeys("10");
    }

    public JSONObject generateUserData() {
        JSONObject userData = new JSONObject();
        userData.put("username", name);
        userData.put("password", password);
        userData.put("confirmPassword", confirmPassword);
        userData.put("mobileNumber", mobileNumber);
        return userData;
    }

    public void storeUserData() {
        JSONObject userData = generateUserData();

        try (FileWriter file = new FileWriter("Json/UIJsonData/userData.json")) {
            file.write(userData.toString());
            test.info("User data stored successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void verifySaveBtn() { // for negative flow [ save button should not visible ]
    	action.jSForScroll(driver, saveBtn);
    	 test.info("Save btn  isEnabled : " +saveBtn.isEnabled());
		    boolean isEnabled = saveBtn.isEnabled();
		    if (isEnabled) {
                test.fail("Save button is visible on the page");
		    } else {
		       test.pass("Save button is Not visible on the page");
		    } 	
    }

    public void saveBtn() {  //for positive flow
    	action.jSForScroll(driver, saveBtn);
    	 test.info("Save btn  isEnabled : " +saveBtn.isEnabled());
		    boolean isEnabled = saveBtn.isEnabled();
		    if (isEnabled) {
                test.pass("Save button is visible on the page");
		    } else {
		       test.fail("Save button is Not visible on the page");
		    } 	
    }

    
    public void clickOnSaveBtn() throws InterruptedException {
    	    Thread.sleep(4000);
    	   // saveBtn.click();
		    action.jSForClick(driver, saveBtn);
		    test.info("Save btn  isEnabled : " +saveBtn.isEnabled());
    }
    
    public void setLogOut() {
    	action.jSForScroll(driver, menuBtn);
		action.jSForClick(driver, menuBtn);
		test.info("clicked on menu btn");
		logOutBtn.click();
		test.info("Successfully logged out");
	}
    
    public void getPopup() {
    	action.explicitVisiblility(driver, userCreatedPopup, 10);
		test.info(userCreatedPopup.getText());
    }
    
    //negative test cases
    
    public void setUserName(String user) throws InterruptedException {
     username.sendKeys(user);
    // test.info("Username :  "+ user);
   	 mobileNo.sendKeys(Keys.ENTER);
   	 Thread.sleep(2000);
   
    String actualText = userFiled.getText();
    test.info( "Validation Text : " +actualText);
	String expectedText = "User Name already exist";

    if (actualText.equalsIgnoreCase(expectedText)) {
   test.pass("User Name already exist");
    } else { 
    test.fail("Unabled to featch text");
    }
   }
    
    public void setLessCharMobNo() throws InterruptedException {
    	 String number=random.generateRandomNumber(6);
         test.info("Number : "+ number);
         mobileNo.sendKeys(number); 
         passwordInput.sendKeys(Keys.ENTER); Thread.sleep(300);
         test.info(mobField.getText());
    }
    public void setLessCharUsers() throws InterruptedException {
    	// action.clearTextField(driver, username);
    	 String name = random.generateRandomName(5);
    	 test.info("Username : "+ name);
         username.sendKeys(name); 
         mobileNo.sendKeys(Keys.ENTER); Thread.sleep(300);
         test.info(lessUserFiled.getText());
    }
    public void setWeakPassword() throws InterruptedException {
    	 String pass = random.generateRandomWeakPassword();
         test.info("Password : "+ pass);
         passwordInput.sendKeys(pass);
         confirmPasswordInput.sendKeys(Keys.ENTER); Thread.sleep(300);
         test.info(passwordField.getText());
    }
    public void setWeakConfirmPassword() throws InterruptedException {
    	 String pass = random.generateRandomWeakPassword();
         test.info("Confirm Password : "+ pass);
         confirmPasswordInput.sendKeys(pass);
         deposit.sendKeys(Keys.ENTER); Thread.sleep(300);
         test.info(confirmPasswordField.getText());
    }
    public void setuserData() { 
         String number=random.generateRandomNumber(10);
         test.info("Number : "+ number);
         mobileNo.sendKeys(number);
         
         String pass = random.generateRandomStrongPassword();
         test.info("Password : "+ pass);
         passwordInput.sendKeys(pass);
         confirmPasswordInput.sendKeys(pass);
    }
    
    public void gettapToastText() throws InterruptedException {
   	test.info(tapToastText.getText() );
    }
    
}



	
	


