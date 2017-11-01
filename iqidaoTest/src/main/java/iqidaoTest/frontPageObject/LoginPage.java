package iqidaoTest.frontPageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import iqidaoTest.adminPageObject.BasePage;

public class LoginPage extends BasePage{
	
	
	public LoginPage(WebDriver driver, String url){
		super(driver);
		this.url = url;
		this.goTo();
	
	}
	
	By loginbuttonLocator = By.id("header-signin");
	By usernameLocator = By.name("key");
	By passwordLocator = By.name("password");
	By captchaLocator = By.name("captcha");
	By loginbtnLocator = By.id("signin-btn");
		
	public WebElement getLoginTable(){
		return this.dr.findElement(loginbuttonLocator);
	}
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
		return this.dr.findElement(loginbtnLocator);
	}

	public HomePage login(String userName, String passWord, String redirectPageUrl){
		this.getLoginTable().click();
		new WebDriverWait(this.dr, 5).until(ExpectedConditions.presenceOfElementLocated(usernameLocator));
		this.getUsernameTextField().sendKeys(userName);
		this.dr.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		this.getPasswordTextField().sendKeys(passWord);		
		this.getCaptchaTextField().sendKeys(CAPTCHA);
		this.getSubmitButton().click();
		return new HomePage(this.dr, redirectPageUrl);
		
	}
}
