package com.jquery.datatable;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_10_DataTable_DataGrid extends BaseTest {
	HomePageObject homePage;
	List<String> expectedAllCountryValues;
	List<String> actualAllCountryValues;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("20"));
		
		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("7"));
		
		homePage.openPagingByPageNumber("18");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("18"));
		
	}
	
	@Test
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Argentina");
		homePage.enterToHeaderTextboxByLabel("Females", "338282");
		homePage.enterToHeaderTextboxByLabel("Males", "349238");
		homePage.enterToHeaderTextboxByLabel("Total", "687522");
		homePage.sleepInSecond(1);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Angola");
		homePage.enterToHeaderTextboxByLabel("Females", "276880");
		homePage.enterToHeaderTextboxByLabel("Males", "276472");
		homePage.enterToHeaderTextboxByLabel("Total", "553353");
		homePage.sleepInSecond(1);
	}
	
	@Test
	public void Table_03_Enter_To_Header() {
		//Doc du lieu cua file country.txt ra
		//Luu vao 1 List<String> = Expected value = expectedAllCountryValues
		
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();
		
//		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}
	
	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		homePage.clickToLoadButton();
		homePage.sleepInSecond(1);
		
		homePage.enterToTextboxAtRowNumberByColumnName("Album", "2", "Michael 97");
		homePage.sleepInSecond(1);
		
		homePage.enterToTextboxAtRowNumberByColumnName("Artist", "4", "Michael Jackson");
		homePage.sleepInSecond(1);
		
		homePage.enterToTextboxAtRowNumberByColumnName("Year", "3", "1997");
		homePage.sleepInSecond(1);
		
		homePage.enterToTextboxAtRowNumberByColumnName("Price", "1", "15");
		homePage.sleepInSecond(1);
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "5", "Japan");
		homePage.sleepInSecond(1);
		
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
		
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "2");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "4");
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("3", "Move Up");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("5", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("4", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(1);
	}

	
	@AfterClass
	public void afterClass() { 
		driver.quit();
	}
	
	private WebDriver driver;
}