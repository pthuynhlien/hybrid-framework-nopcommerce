package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static CustomerInforPageObject getCustomerInforPage(WebDriver driver) {
		return new CustomerInforPageObject(driver);
	}
	
	public static AddressPageObject getAddressPage(WebDriver driver) {
		return new AddressPageObject(driver);
	}
	
	public static MyProductReviewPageObject getMyProductReviewPageObject(WebDriver driver) {
		return new MyProductReviewPageObject(driver);
	}
	
	public static RewardPointPageObject getRewardPointPageObject(WebDriver driver) {
		return new RewardPointPageObject(driver);
	}
}
