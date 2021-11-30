package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_04_Multiple_Browser extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "abc" + generateFakeNumber() + "@email.com";
		password = "123456";
		
		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);
	}

	@Test
	public void TC_01_Register_Emtpty_Data() {
		System.out.println("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		System.out.println("Register Page - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();
			
		System.out.println("Register Page - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(),"First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(),"Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(),"Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		System.out.println("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();
				
		System.out.println("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("1234@56#%*");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(),"Wrong email");
	}
	
	
	@Test
	public void TC_03_Register_Success() {
		System.out.println("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		System.out.println("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
		System.out.println("Register Page - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		System.out.println("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		System.out.println("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page - Step 04: Verify error existing email message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(),"The specified email already exists");		
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		System.out.println("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		System.out.println("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");
		
		System.out.println("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password must meet the following rules:\nmust have at least 6 characters");
	
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		System.out.println("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		System.out.println("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("654321");
		
		System.out.println("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"The password and confirmation password do not match.");
	}
  
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateFakeNumber(){
		Random rand = new Random();
		return rand.nextInt(9999);
	}	
}