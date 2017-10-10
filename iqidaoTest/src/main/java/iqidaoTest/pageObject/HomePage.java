package iqidaoTest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//http://101.132.45.64/home
public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver, String url){
		super(driver);
		this.url = url;
		this.goTo();
	}

	By pagetitleLocator = By.className("page-title");
	
	public String getTitleText(){
		return this.dr.findElement(pagetitleLocator).getText();
	}
	
}
