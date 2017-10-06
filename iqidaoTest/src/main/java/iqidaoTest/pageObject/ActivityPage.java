package iqidaoTest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActivityPage extends BasePage{
	public ActivityPage(WebDriver driver, String url){
		super(driver);
		this.url = url;
		this.goTo();
	}
	
	By activityMenuLocator = By.xpath("html/body/div[1]/aside/section/ul/li[1]/a/span[1]");
	By activityListMenuLocator = By.xpath("html/body/div[1]/aside/section/ul/li[1]/ul/li/a/span");
	
	By createActivityLocator = By.linkText("创建活动");
	
	By activityNameLocator = By.name("name");
	
	By activityPictureLocator = By.name("logo1");
//	this.driver.findElement(By.name("logo1")).sendKeys("C:\\工作目录\\测试图片.png");
	By activityDescriptionLocator = By.cssSelector("div.fr-element.fr-view");
	By enableSignupLocator = By.name("enableSignup");
	By enableOnlineLocator = By.name("online");
	
	

}
