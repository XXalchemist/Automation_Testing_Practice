package com.Nagarro.RestApi.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.restassured.RestAssured;;

public class BaseTest {
	
	// Initializing the data.properties and Log4j
	public static Properties prop = new Properties();
	public static File file = new File("./Resources/props/data.properties");
	public static FileInputStream fis = null;
	public final static Logger logger = Logger.getLogger(BaseTest.class);
	
	
	static { 
		
		// Exception Handling for FIS
		try {
			
			fis = new FileInputStream(file);
		}catch(FileNotFoundException e){
			
			System.out.println(e.getMessage());
		}
		
		// Exception Handling for Prop
		try {
			
			prop.load(fis);
		}catch(IOException e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
	@BeforeMethod
	public void setUp() {
		
		RestAssured.baseURI = prop.getProperty("baseURI");
		Reporter.log("SetUp Completed");
		logger.info("--------------------------------------- Test Started ---------------------------------------");
		
		System.out.println("--------------------------------------- Test Started -----------------------------------------");
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		logger.info("--------------------------------------- Test Completed ---------------------------------------");
		
		System.out.println("--------------------------------------- Test Completed ---------------------------------------");
	}
		
}
