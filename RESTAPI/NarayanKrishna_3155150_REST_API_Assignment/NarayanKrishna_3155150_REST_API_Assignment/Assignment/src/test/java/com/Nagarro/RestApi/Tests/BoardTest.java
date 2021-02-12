package com.Nagarro.RestApi.Tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Nagarro.RestApi.Utils.Utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// Test cases for Boards
public class BoardTest extends BaseTest {
	
	// POST - Valid Create Board
	@Test(priority = 1)
	public void ValidCreateBoard() {
		
		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("name", prop.getProperty("expectedBoardName"));
		
		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept","application/json")
				.body("")
				.queryParameters(parameters)
				.log()
				.all()
				.post(prop.getProperty("boardResource"));
		
		JsonPath responseJson = Utils.rawToJson(response);
		
		String actualBoardName = responseJson.get("name");
		
		String currentBoardId = responseJson.getString("id");
		
		Utils.setCurrentBoardId(currentBoardId);
		
		// Assertion for Board Name
		Assert.assertEquals(actualBoardName, prop.getProperty("expectedBoardName"),  "Verify actual BoardName == expected BoardName");
		
		logger.info("---- Valid Create Board Test Completed---- ");
	}
	
	// GET - Valid Get Board
	@Test(priority = 2)
	public void ValidGetBoard() {
	
	String currentBoardId = Utils.getCurrentBoardId();
	
	Map<String, String> parameters = new HashMap<String, String>();
	parameters.put("key", Utils.getApiKey());
	parameters.put("token", Utils.getToken());
	
	// Creating Request
	RequestSpecification httpRequest = RestAssured.given();
	
	Response response = httpRequest.when().header("Accept","application/json")
			.queryParameters(parameters)
			.log()
			.all()
			.get(prop.getProperty("boardResource")+currentBoardId);
	
	JsonPath responseJson = Utils.rawToJson(response);
	
	String actualBoardName = responseJson.get("name");
	
	// Assertion for Board Name
	Assert.assertEquals(actualBoardName, prop.getProperty("expectedBoardName"),  "Verify actual BoardName == expected BoardName");

	logger.info("---- Valid Get Board Test Complete ---- ");
	}
	
	// GET Negative scenario - Invalid Board Id
	@Test(priority = 3)
	public void InvalidGetBoard() {
	

	Map<String, String> parameters = new HashMap<String, String>();
	parameters.put("key", Utils.getApiKey());
	parameters.put("token", Utils.getToken());
	
	// Creating Request
	RequestSpecification httpRequest = RestAssured.given();
	
	Response response = httpRequest.when().header("Accept","application/json")
			.queryParameters(parameters)
			.log()
			.all()
			.get(prop.getProperty("boardResource")+prop.getProperty("invalidBoardId"));
	
	
     int actualStatusCode = response.getStatusCode();
	
	// Assertion for Status Code
	Assert.assertEquals(actualStatusCode, Integer.parseInt(prop.getProperty("expectedPNFStatusCode")),  "Verify actual StatusCode == expected StatusCode");

	logger.info("Invalid Get Board Test Completed");
	}
	
	// PUT - Valid Update the name of board
	@Test(priority = 4)
	public void ValidUpdateBoard() {
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("name", prop.getProperty("newBoardName"));
		
		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept","application/json")
				.body("")
				.queryParameters(parameters)
				.log()
				.all()
				.put(prop.getProperty("boardResource")+Utils.getCurrentBoardId());
		
		JsonPath responseJson = Utils.rawToJson(response);
		
		String actualBoardName = responseJson.get("name");
		
		String currentBoardId = responseJson.getString("id");
		
		Utils.setCurrentBoardId(currentBoardId);
		
		// Assertion for Board Name
		Assert.assertEquals(actualBoardName, prop.getProperty("newBoardName"),  "Verify actual BoardName == expected BoardName");
		
		logger.info("Valid Update Board Test Completed");
	}
	
	// DELETE - Valid Delete a board
	@Test(priority = 5)
	public void ValidDeleteBoard() {
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		
		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept","application/json")
				.body("")
				.queryParameters(parameters)
				.log()
				.all()
				.delete(prop.getProperty("boardResource")+Utils.getCurrentBoardId());
		
				
		int actualStatusCode = response.getStatusCode();
	
		
		// Assertion for StatusCode
		Assert.assertEquals(actualStatusCode, Integer.parseInt(prop.getProperty("expectedSuccessStatusCode")),  "Verify actual StatusCode == expected StatusCode");
		
		logger.info("Valid Delete Board Test Completed");
	}
}

