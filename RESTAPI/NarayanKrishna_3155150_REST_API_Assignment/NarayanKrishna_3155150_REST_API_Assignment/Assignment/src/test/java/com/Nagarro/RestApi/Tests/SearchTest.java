package com.Nagarro.RestApi.Tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Nagarro.RestApi.Utils.Utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SearchTest extends BaseTest {

	// GET - Valid Search
	@Test(priority = 1)
	public void ValidBoardSearch() {

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("query", prop.getProperty("expectedSearchCustomTestBoard"));

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().get(prop.getProperty("searchResource"));

		JsonPath responseJson = Utils.rawToJson(response);

		ArrayList actualSearchedItems = responseJson.get("boards");

		// Fetching name of first searched board
		Map<String, String> actualBoardNames = (Map<String, String>) actualSearchedItems.get(0);

		String actualBoardName = actualBoardNames.get("name");

		// Assertion for Board Name
		Assert.assertEquals(actualBoardName, prop.getProperty("expectedSearchCustomTestBoard"),
				"Verify actual SearchBoardName == expectedSearchCustomTestBoard");

		logger.info("Valid Search Test Completed");
	}

	// GET - When there is nothing to search
	@Test(priority = 2)
	public void InvalidBoardSearch() {

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("query", prop.getProperty("invalidSearchBoardName"));

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().get(prop.getProperty("searchResource"));

		JsonPath responseJson = Utils.rawToJson(response);

		ArrayList actualSearchedItems = responseJson.get("boards");

		// Fetching name of first searched board

		try {

			Map<String, String> actualBoardNames = (Map<String, String>) actualSearchedItems.get(0);
			String actualBoardName = actualBoardNames.get("name");
		}

		catch (Exception e) {

			String actualBoardName = "";

			// Assertion for Board Name

			Assert.assertEquals(actualBoardName, prop.getProperty("expectedSearchBoardData"),
					"Verify actual SearchBoardData == expectedSearchData");

			logger.info("Invalid Search Test Completed");
		}
	}
}
