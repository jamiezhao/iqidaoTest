package iqidaoTest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminLoginPage extends BasePage{

	public AdminLoginPage(WebDriver driver, String url){
		super(driver);
		this.url = url;
		this.goTo();
	}
	
	By usernameLocator = By.name("phone");
	By passwordLocator = By.name("password");
	By captchaLocator = By.name("captcha");
	By submitbuttonLocator = By.id("submit");
	
	public WebElement getUsernameTextField(){
		return this.dr.findElement(usernameLocator);
	}
	
	public WebElement getPasswordTextField(){
		return this.dr.findElement(passwordLocator);
	}
	
	public WebElement getCaptchaTextField(){
		return this.dr.findElement(captchaLocator);
	}
	
	public WebElement getSubmitButton(){
		return this.dr.findElement(submitbuttonLocator);
	}
	
	public AdminHomePage adminLogin(String userName, String passWord, String redirectPageUrl){
		this.getUsernameTextField().sendKeys(userName);
		this.getPasswordTextField().sendKeys(passWord);
		this.getCaptchaTextField().sendKeys(CAPTCHA);
		this.getSubmitButton().click();
		return new AdminHomePage(this.dr, redirectPageUrl);
		
	}
}
