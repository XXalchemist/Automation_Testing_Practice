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

public class EmojiTest extends BaseTest {

	@Test(priority = 1)
	public void ValidGetEmojis() {

		// Passing token and key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", Utils.getApiKey());
		parameters.put("token", Utils.getToken());

		// Creating Request
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("Accept", "application/json").body("").queryParameters(parameters)
				.log().all().get(prop.getProperty("emojisResource"));

		int actualStatusCode = response.getStatusCode();

		// Assertion for Status Code
		Assert.assertEquals(actualStatusCode, Integer.parseInt(prop.getProperty("expectedSuccessStatusCode")),
				"Verify actual StatusCode == expected StatusCode");

		logger.info("Valid Get emojis Test Completed");

	}
}
