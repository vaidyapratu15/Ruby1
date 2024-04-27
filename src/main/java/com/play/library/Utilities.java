package com.play.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utilities {

	/*
	 * @Author: Pratiksha Vaidya.
	 * @Since : November 2022
	 * @Discription : getPropertyData this method use to getData from Property file
	 * by using parameters keys. : getTestData this method use to getData from Excel
	 * by using parameters int row & cell . : CaptureScreenshots this method use to
	 * capture failureScreenshots by using parameters testCaseName.
	 */

	public static String getJsonData(String key) throws IOException, ParseException {
		JSONParser json = new JSONParser();
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/Json/UIJsonData/userData.json"; 
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(filePath));
		String value = (String) jsonObj.get(key);
		return value;
	}
	
	public static String CaptureScreenshots(String testCaseName, WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String datestamp = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		String timestamp = new SimpleDateFormat("hh_mm_ss").format(new Date());
		File distination = new File(System.getProperty("user.dir") + "//ScreenShots//" + datestamp + "//" + testCaseName
				+ timestamp + ".png");
		FileUtils.copyFile(source, distination);
		return System.getProperty("user.dir") + "//Screenshots//" + datestamp + "//" + testCaseName + timestamp
				+ ".png";
	}

	public static String getBalanceData(String key) throws IOException {
//		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//data.properties");
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//configuration//balanceinfo.properties");
		Properties p = new Properties();
		p.load(file);
		String value = p.getProperty(key);
		return value;
	}

	public static String getTestData(int rowIndex, int cellIndex) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "//ExcelData//ExcelCredential.xlsx");
		Sheet sheet = WorkbookFactory.create(file).getSheet("Sheet1");
		String value = sheet.getRow(rowIndex).getCell(cellIndex).getStringCellValue();
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public static void replaceJsonData() throws IOException, ParseException {
		String userDir = System.getProperty("user.dir"); 
		String jsonFilePath = userDir +"Json/UIJsonData/data2.json";
		String jsonData = null; // Read the JSON file contents into a string
		try {
			jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Gson gson = new GsonBuilder().create(); // Parse the JSON data
		Map<String, Object> data = gson.fromJson(jsonData, Map.class);

		String oldPwd = Utilities.getJsonData("password"); // Get the old and new password values

		Faker faker = new Faker();
		String reEnterPwd = faker.bothify("P???@###");
		data.put("reEnterPwd", reEnterPwd);

		String newPwd = Utilities.getJsonData("reEnterPwd");

		data.remove("password", oldPwd); // Remove the old password key-value pair
		data.put("password", newPwd); // Add the new password key-value pair

		String updatedJsonData = gson.toJson(data); // Write the updated JSON data back to the file
		try {
			Path path = Paths.get(jsonFilePath);
			Files.write(path, updatedJsonData.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
