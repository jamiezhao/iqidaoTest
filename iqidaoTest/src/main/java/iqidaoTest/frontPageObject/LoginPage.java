package iqidaoTest.frontPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		new WebDriverWait(this.dr, 15).until(ExpectedConditions.presenceOfElementLocated(usernameLocator));
//		this.getUsernameTextField().sendKeys(userName);
		//解决sendkey方法由于字符太长，只输入一部分就转到下一个选项，直接用js设置值属性
		JavascriptExecutor js = (JavascriptExecutor) this.dr;
		js.executeScript("document.getElementsByName('key')[0].setAttribute('value', '" + userName + "')", this.getUsernameTextField());
//		this.getPasswordTextField().sendKeys(passWord);	
		JavascriptExecutor js2 = (JavascriptExecutor) this.dr;
		js2.executeScript("document.getElementsByName('password')[0].setAttribute('value', '" + passWord + "')", this.getUsernameTextField());
//		this.getCaptchaTextField().sendKeys(CAPTCHA);
		JavascriptExecutor js3 = (JavascriptExecutor) this.dr;
		js3.executeScript("document.getElementsByName('captcha')[0].setAttribute('value', '666666')", this.getUsernameTextField());
		this.getSubmitButton().click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new HomePage(this.dr, redirectPageUrl);
		
	}
}
