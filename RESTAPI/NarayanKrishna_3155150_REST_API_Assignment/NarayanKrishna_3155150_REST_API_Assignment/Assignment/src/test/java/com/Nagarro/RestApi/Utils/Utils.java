package com.Nagarro.RestApi.Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utils {

	private static String currentBoardId = "";
	private final static String token = "bc58afd6314911e751d1646dbe4d1e2474018b7216f02981f76a8742ab79b6c9";
	private final static String userName = "Narayan Krishna";
	private final static String apiKey = "a39f924fe0dda168289470b335d7d2e0";
	private static String currentCardIdList = "5fe8ad9e095273438014d170";
	private static String currentCardId = "";
	private static String currentListId = "";
	

	public static String getApiKey() {
		return apiKey;
	}


	public static String getToken() {
		return token;
	}


	public static String getCurrentBoardId() {
		return currentBoardId;
	}


	public static void setCurrentBoardId(String currentBoardId) {
		Utils.currentBoardId = currentBoardId;
	}


	public static String getUserName() {
		return userName;
	}

	
	public static String getCurrentCardIdList() {
		return currentCardIdList;
	}


	public static void setCurrentCardIdList(String currentCardIdList) {
		Utils.currentCardIdList = currentCardIdList;
	}
	
	public static JsonPath rawToJson(Response response) {
		
		String responseString = (response).asString();
		JsonPath responseJson = new JsonPath(responseString);
		return responseJson;
	}


	public static String getCurrentCardId() {
		return currentCardId;
	}


	public static void setCurrentCardId(String currentCardId) {
		Utils.currentCardId = currentCardId;
	}


	public static String getCurrentListId() {
		return currentListId;
	}


	public static void setCurrentListId(String currentListId) {
		Utils.currentListId = currentListId;
	}

}
