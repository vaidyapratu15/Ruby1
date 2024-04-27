package com.play.library;

import java.io.IOException;
import java.time.Duration;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * @Author: Pratiksha Vaidya.
 * @Since : November 2022
 * @Discription : This Base class contain setUpBrowser And Extent-Report
 */
public class BaseClass {

	public static WebDriver driver;
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static void setUpBrowser() throws IOException, ParseException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		option.addArguments("--remote-allow-origins=*");
		option.addArguments("--disable-popup-blocking");
//		option.addArguments("--headless");  //		option.setHeadless(true);
//		option.addArguments("--window-size=1920x1080");
//		option.addArguments("--disable-gpu");

        WebDriverManager.chromedriver().setup();
//      WebDriverManager.chromedriver().forceDownload().setup();
 //     WebDriverManager.chromedriver().browserVersion("116.0.5845.96").setup();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//	driver.get(Utilities.getJsonData("url"));
	}

	public static void setExtentReport() {
		// String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
		// Date());
		// spark = new ExtentSparkReporter("test-output//Extent Reports" + "//" +
		// timeStamp +".html");
		spark = new ExtentSparkReporter("Extent Report.html");
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("PlayExch Report");
		spark.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}
	
	public static void launchBrowser(String browserName) throws IOException, ParseException {

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			/*
			 * } else if (browserName.equalsIgnoreCase("firefox")) {
			 * WebDriverManager.firefoxdriver().setup(); driver = new FirefoxDriver(); }
			 * else if (browserName.equalsIgnoreCase("edge")) {
			 * WebDriverManager.edgedriver().setup(); driver = new EdgeDriver();
			 */
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(Utilities.getJsonData("url"));
	}

}
