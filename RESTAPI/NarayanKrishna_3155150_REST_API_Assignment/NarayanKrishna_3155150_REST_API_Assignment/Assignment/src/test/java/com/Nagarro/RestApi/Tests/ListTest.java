package com.Nagarro.RestApi.Tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Nagarro.RestApi.Utils.Utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ListTest extends BaseTest {

	// POST - Create a valid List on given board
	@Test(priority = 11)
	public void ValidCreateList() {

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("name", prop.getProperty("expectedListName"));
		parameters.put("idBoard", prop.getProperty("CustomTestBoard"));

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().post(prop.getProperty("listResource"));

		JsonPath responseJson = Utils.rawToJson(response);

		String actualListName = responseJson.get("name");

		String currentListId = responseJson.getString("id");

		Utils.setCurrentListId(currentListId);

		// Assertion for Board Name
		Assert.assertEquals(actualListName, prop.getProperty("expectedListName"),
				"Verify actual ListName == expected ListName");

		logger.info("Valid Create List Test Completed");
	}

	// GET - Get a valid List
	@Test(priority = 12)
	public void ValidGetList() {

		String currentListId = Utils.getCurrentListId();

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("idBoard", prop.getProperty("CustomTestBoard"));

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().get(prop.getProperty("listResource") + currentListId);

		JsonPath responseJson = Utils.rawToJson(response);

		String actualListName = responseJson.get("name");

		// Assertion for Board Name
		Assert.assertEquals(actualListName, prop.getProperty("expectedListName"),
				"Verify actual ListName == expected ListName");

		logger.info("Valid Get List Test Completed");
	}

	// GET - Negative scenario - Invalid Get List
	@Test(priority = 13)
	public void InvalidGetList() {

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("idBoard", prop.getProperty("CustomTestBoard"));

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().get(prop.getProperty("listResource") + prop.getProperty("invalidListId"));

		int actualStatusCode = response.getStatusCode();

		// Assertion for Status Code
		Assert.assertEquals(actualStatusCode, Integer.parseInt(prop.getProperty("expectedPNFStatusCode")),
				"Verify actual StatusCode == expected StatusCode");

		logger.info("Invalid Get List Test Completed");
	}

	// PUT - Update the name of the List
	@Test(priority = 14)
	public void ValidUpdateList() {

		String currentListId = Utils.getCurrentListId();

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("name", prop.getProperty("newListName"));

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().put(prop.getProperty("listResource") + currentListId);

		JsonPath responseJson = Utils.rawToJson(response);

		String actualListName = responseJson.get("name");

		Utils.setCurrentListId(currentListId);

		// Assertion for Board Name
		Assert.assertEquals(actualListName, prop.getProperty("newListName"),
				"Verify actual ListName == expected ListName");

		logger.info("Valid Update List Test Completed");
	}

	// PUT - Archive the List
	@Test(priority = 15)
	public void ValidArchiveList() {

		String currentListId = Utils.getCurrentListId();

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().put(prop.getProperty("listResource") + currentListId + "/closed");

		int actualStatusCode = response.getStatusCode();

		// Assertion for Status Code
		Assert.assertEquals(actualStatusCode, Integer.parseInt(prop.getProperty("expectedPNFStatusCode")),
				"Verify actual StatusCode == expected StatusCode");

		logger.info("Valid Archive List Test Completed");
	}

}
