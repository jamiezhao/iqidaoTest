package iqidaoTest.pageObject;


import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import iqidaoTest.Utils.CommonUtils;

//http://101.132.45.64/admin001/activity/post
public class CreateActivityPage extends BasePage{
	public CreateActivityPage(WebDriver driver, String url){
		super(driver);
		this.url = url;
		this.goTo();
	}

	
	By activityNameLocator = By.name("name");
	By activityUserLimitLocator = By.id("userLimit");
	By activityVersionLocator = By.name("activityVersion");
	By activityTypeLocator = By.name("type");
	By activityPictureLocator = By.name("logo1");
	By activityDescriptionLocator = By.cssSelector("div.fr-element.fr-view");
	By activityLowduanLocator = By.name("lowerduan");
	By activitySignupCountLocator = By.name("signupCount");
	By activityPriceLocator = By.name("price");
	By activitySignupStartTimeLocator = By.name("signupStartTime");
	By activitySignupEndTimeLocator = By.name("signupEndTime");
	By activityStartTimeLocator = By.name("startTime");
	By activityEndTimeLocator = By.name("endTime");
	By enableSignupLocator = By.name("enableSignup");
	By enableOnlineLocator = By.name("online");
	
	
	public WebElement getActivityNameTextField(){
		return this.dr.findElement(activityNameLocator);
	}
	
	public WebElement getActivityUserLimitTextField(){
		return this.dr.findElement(activityUserLimitLocator);
	}
	
	//1为普通模式，2为双师模式
	public Select getActivityVersionSelect(){
		Select activityVersionSelect = new Select(this.dr.findElement(activityVersionLocator));
		return activityVersionSelect;
	}
	
	//1为专项训练，2为年级训练，101为系统训练
	public Select getActivityTypeSelect(){
		Select activityTypeSelect = new Select(this.dr.findElement(activityTypeLocator));
		return activityTypeSelect;
	}
	
	public void setActivityTeacher(String teacherName){
		//设置活动教师，1.点击输入框，弹出搜索框 2.在输入框输入搜索内容 3.等待ajax加载数据，点击数据
		List<WebElement> lists = this.dr.findElements(By.className("select2-selection__placeholder"));
		lists.get(1).click();
		this.dr.findElement(By.className("select2-search__field")).sendKeys(teacherName);
		new WebDriverWait(this.dr, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".clearfix.text-center.ajaxSelect")));
		this.dr.findElement(By.cssSelector(".clearfix.text-center.ajaxSelect")).click();
	}
	
	public WebElement getActivityPictureField(){
		return this.dr.findElement(activityPictureLocator);
	}
	
	public WebElement getDescriptionTextField(){
		return this.dr.findElement(activityDescriptionLocator);
	}
	
	public WebElement getLowduanTextField(){
		return this.dr.findElement(activityLowduanLocator);
	}
	
	public WebElement getSignupCountTextField(){
		return this.dr.findElement(activitySignupCountLocator);
	}
	
	public WebElement getActivityPriceTextField(){
		return this.dr.findElement(activityPriceLocator);
	}
	
	public WebElement getSignupStartTimeTextField(){
		return this.dr.findElement(activitySignupStartTimeLocator);
	}
	
	public WebElement getSignupEndTimeTextField(){
		return this.dr.findElement(activitySignupEndTimeLocator);
	}
	
	public WebElement getStartTimeTextField(){
		return this.dr.findElement(activityStartTimeLocator);
	}
	
	public WebElement getEndTimeTextField(){
		return this.dr.findElement(activityEndTimeLocator);
	}
	
	public WebElement getEnableSignupButton(){
		return this.dr.findElement(enableSignupLocator);
	}
	
	public WebElement getEnableOnlineButton(){
		return this.dr.findElement(enableOnlineLocator);
	}
	
	
	public ActivitysListPage createActivity(String activityName, String teacherName, String signupCount, String lowduan, String price, String redirectPageUrl){
		this.getActivityNameTextField().sendKeys(activityName);
		this.getActivityUserLimitTextField().sendKeys("10");
		Select activityVersion = this.getActivityVersionSelect();
		activityVersion.selectByValue("2"); 	//2为双师模式
		Select activityType = this.getActivityTypeSelect();
		activityType.selectByValue("2");		//2为年级训练
		this.setActivityTeacher(teacherName);
		this.getActivityPictureField().sendKeys("C:\\工作目录\\测试图片.png");
		this.getDescriptionTextField().sendKeys("this is activity's description");
		this.getLowduanTextField().sendKeys(lowduan);
		this.getSignupCountTextField().sendKeys(signupCount);
		this.getActivityPriceTextField().sendKeys(price);
		this.getSignupStartTimeTextField().sendKeys(CommonUtils.setDays(2017, 10, 9, 0, 0));
		this.getSignupEndTimeTextField().sendKeys(CommonUtils.setDays(2017, 10, 16, 23, 59));
		this.getStartTimeTextField().sendKeys(CommonUtils.setDays(2017, 10, 17, 0, 0));
		this.getEndTimeTextField().sendKeys(CommonUtils.setDays(2017, 10, 31, 23, 59));
		this.getEnableSignupButton().click();
		this.getEnableOnlineButton().click();
		return new ActivitysListPage(this.dr, redirectPageUrl);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
