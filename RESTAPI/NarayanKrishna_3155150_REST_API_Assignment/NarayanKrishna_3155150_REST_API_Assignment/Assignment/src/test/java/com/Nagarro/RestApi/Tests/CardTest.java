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

public class CardTest extends BaseTest {

	// POST - Valid create a new card on board
	@Test(priority = 6)
	public void ValidCreateCard() {

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("idList", Utils.getCurrentCardIdList());
		parameters.put("name", prop.getProperty("expectedCardName"));

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().post(prop.getProperty("cardResource"));

		JsonPath responseJson = Utils.rawToJson(response);

		String actualCardName = responseJson.get("name");

		String currentCardId = responseJson.get("id");

		Utils.setCurrentCardId(currentCardId);

		// Assertion for Card Name
		Assert.assertEquals(actualCardName, prop.getProperty("expectedCardName"),
				"Verify actual CardName == expected CardName");

		logger.info("Valid Create Card Test Completed");
	}

	// GET - Valid get a card
	@Test(priority = 7)
	public void ValidGetCard() {

		String currentCardId = Utils.getCurrentCardId();

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").queryParameters(parameters).log()
				.all().get(prop.getProperty("cardResource") + currentCardId);

		JsonPath responseJson = Utils.rawToJson(response);

		String actualCardName = responseJson.get("name");

		// Assertion for Card Name
		Assert.assertEquals(actualCardName, prop.getProperty("expectedCardName"),
				"Verify actual CardName == expected CardName");

		logger.info("Valid Get Card Test Completed");
	}

	// GET - Invalid card id
	@Test(priority = 8)
	public void InvalidGetCard() {

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").queryParameters(parameters).log()
				.all().get(prop.getProperty("cardResource") + prop.getProperty("InvalidCardId"));

		int actualStatusCode = response.getStatusCode();

		// Assertion for Status Code
		Assert.assertEquals(actualStatusCode, Integer.parseInt(prop.getProperty("expectedPNFStatusCode")),
				"Verify actual StatusCode == expected StatusCode");

		logger.info("Invalid Get Card Test Completed");
	}

	// PUT - Valid update a card
	@Test(priority = 9)
	public void ValidUpdateCard() {

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());
		parameters.put("idList", Utils.getCurrentCardIdList());
		parameters.put("name", prop.getProperty("newCardName"));

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().post(prop.getProperty("cardResource"));

		JsonPath responseJson = Utils.rawToJson(response);

		String actualCardName = responseJson.get("name");

		String currentCardId = responseJson.get("id");

		Utils.setCurrentCardId(currentCardId);

		// Assertion for Card Name
		Assert.assertEquals(actualCardName, prop.getProperty("newCardName"),
				"Verify actual CardName == expected CardName");

		logger.info("Valid Update Card Test Completed");
	}

	// DELETE - Valid delete a card
	@Test(priority = 10)
	public void ValidDeleteCard() {

		String currentCardId = Utils.getCurrentCardId();

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").queryParameters(parameters).log()
				.all().delete(prop.getProperty("cardResource") + currentCardId);

		int actualStatusCode = response.getStatusCode();

		// Assertion for Status Code
		Assert.assertEquals(actualStatusCode, Integer.parseInt(prop.getProperty("expectedSuccessStatusCode")),
				"Verify actual StatusCode == expected StatusCode");

		logger.info("Valid Delete Card Test Completed");
	}

}
