package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.AddressPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_07_Switch_Page extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInforPageObject customerInforPage;
	private AddressPageObject addressPage;
	private MyProductReviewPageObject myProductReviewPage;
	private RewardPointPageObject rewardPointPage;
	

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getHomePage(driver);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "abc" + generateFakeNumber() + "@email.com";
		password = "123456";
		
		
	}

	@Test
	public void User_01_Register() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
		homePage = registerPage.clickToLogoutLink();
		
	}
	
	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.clickToLoginButton();
		
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Test
	public void User_03_Customer_Infor() {
		customerInforPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}
	
	@Test
	public void User_04_Switch_Page() {
		addressPage = customerInforPage.openAddressPage(driver);
		
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		
		addressPage = rewardPointPage.openAddressPage(driver);
		
		rewardPointPage = addressPage.openRewardPointPage(driver);
		
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
		addressPage = myProductReviewPage.openAddressPage(driver);
		
		customerInforPage = addressPage.openCustomerInforPage(driver);
		
		myProductReviewPage = customerInforPage.openMyProductReviewPage(driver);
	}
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}