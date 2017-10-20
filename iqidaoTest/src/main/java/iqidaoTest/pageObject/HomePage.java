package iqidaoTest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//http://101.132.45.64/home
public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver, String url){
		super(driver);
		this.url = url;
		this.goTo();
	}

	By pagetitleLocator = By.className("page-title");
	By studyCenterLocator = By.partialLinkText("进入学习中心");
	By trainingMenuLocator = By.xpath("html/body/div[1]/div/nav[1]/ul/li[2]/a");
	
	public String getTitleText(){
		return this.dr.findElement(pagetitleLocator).getText();
	}
	
	public WebElement getTrainingMenuLink() {
		return this.dr.findElement(trainingMenuLocator);
	}
	
	
	
}
