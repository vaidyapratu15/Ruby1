package com.play.library;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

public class BackendUtility {
	
	public static String getFrontendData(String key) throws IOException, ParseException {
		JSONParser json = new JSONParser();
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/Json/BackendJsonData/backendCredentials.json";
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(filePath));
		String value = (String) jsonObj.get(key);
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public static void storeWithdrawData(WebElement ele,String textName,String jsonfileName) throws IOException, ParseException {
		String userDir = System.getProperty("user.dir"); 
		String filePath = userDir + jsonfileName;    //"/Json/UIJsonData/data2.json"
	    JSONObject bonus = new JSONObject();
	    bonus.put(textName, ele.getText());

	    JSONArray latestData = new JSONArray();
	    latestData.add(bonus);

	    try (FileWriter file = new FileWriter(filePath)) {
	        file.write(latestData.toJSONString());
	        file.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
 
	public static String getLatestData(String jsonfileName , String key) throws IOException, ParseException {
	    JSONParser parser = new JSONParser();
	    try (FileReader fileReader = new FileReader(jsonfileName)) {
	        Object obj = parser.parse(fileReader);
	        if (obj instanceof JSONArray) {
	            JSONArray bonusDataArray = (JSONArray) obj;
	            if (bonusDataArray.size() > 0) {
	                JSONObject latestBonusData = (JSONObject) bonusDataArray.get(bonusDataArray.size() - 1);
	                return (String) latestBonusData.get(key);  //"Bonus : "
	            }
	        }
	    } catch (FileNotFoundException e) {
	        // ignore - file does not exist yet
	    }
	    return null;
	}
	
	public static String getJsonBeforeBalanceData(String key) throws IOException, ParseException {
		String userDir = System.getProperty("user.dir"); 
		String jsonFilePath = userDir +"Json/UIJsonData/beforeBalanceInfo.json";
		JSONParser json = new JSONParser();
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(jsonFilePath));
		String value = (String) jsonObj.get(key);
		return value;
	}

	public static String getJsonAfterBalanceData(String key) throws IOException, ParseException {
		String userDir = System.getProperty("user.dir"); 
		String jsonFilePath = userDir +"Json/UIJsonData/afterBalanceInfo.json";
		JSONParser json = new JSONParser();
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(jsonFilePath));
		String value = (String) jsonObj.get(key);
		return value;
	}



	
	


	

	
	

}
