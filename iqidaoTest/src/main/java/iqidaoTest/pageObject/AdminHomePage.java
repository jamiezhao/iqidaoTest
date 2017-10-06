package iqidaoTest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminHomePage extends BasePage{
	public AdminHomePage(WebDriver driver, String url){
		super(driver);
		this.url = url;
		this.goTo();
	}
	
	By homePageTitleLocator = By.xpath("html/body/div[1]/div/section[1]/ol/li/a");
	
	public String getTitleText(){
		return this.dr.findElement(homePageTitleLocator).getText();
	}

}
