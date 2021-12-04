package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_09_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
	

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "abc" + generateFakeNumber() + "@email.com";
		password = "123456";
		
		
	}

	@Test
	public void User_01_Register_Login() {
		registerPage = homePage.openRegisterPage();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
		homePage = registerPage.clickToLogoutLink();
		
		loginPage = homePage.openLoginPage();
		loginPage.clickToLoginButton();
		
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		customerInforPage = homePage.openMyAccountPage();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}
	
	@Test
	public void User_02_Switch_Page() {
		addressPage = customerInforPage.openAddressPage(driver);
		
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		
		addressPage = rewardPointPage.openAddressPage(driver);
		
		rewardPointPage = addressPage.openRewardPointPage(driver);
		
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
	}
	
	@Test
	public void User_03_Dynamic_Page_01() {
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		
		customerInforPage = (UserCustomerInforPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");
	}
	
	@Test
	public void User_03_Dynamic_Page_02() {
		customerInforPage.openPagesAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		myProductReviewPage.openPagesAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
		rewardPointPage.openPagesAtMyAccountByPageName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		
		addressPage.openPagesAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
	}
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}