package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.Utils.CommonUtils;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.adminPageObject.ActivityUsersPage;
import iqidaoTest.adminPageObject.ActivitysListPage;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;
import iqidaoTest.adminPageObject.CreateActivityPage;
import iqidaoTest.adminPageObject.CreateSeasonPage;

public class ActivityTC {
	private WebDriver driver;
	//页面URL
	String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
	String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
	String createActivityUrl = xmlData.getParamFromXml("createActivityUrl");
	String activitysListUrl = xmlData.getParamFromXml("activitysListUrl");
	//登录
	String userName = xmlData.getParamFromXml("userName");
	String passWord = xmlData.getParamFromXml("passWord");
	//创建活动
	String activityName = xmlData.getParamFromXml("activityName");
	String teacherName = xmlData.getParamFromXml("teacherName");
	String signupCount = xmlData.getParamFromXml("signupCount");
	String lowduan = xmlData.getParamFromXml("lowduan");
	String price = xmlData.getParamFromXml("price");
	String signupStartTime = xmlData.getParamFromXml("signupStartTime");
	String signupEndTime = xmlData.getParamFromXml("signupEndTime");
	String activityStartTime = xmlData.getParamFromXml("activityStartTime");
	String activityEndTime = xmlData.getParamFromXml("activityEndTime");
	//创建赛季
	String seasonName = xmlData.getParamFromXml("seasonName");
	String seasonPrice = xmlData.getParamFromXml("seasonPrice");
	String seasonStartTime = activityStartTime;
	String seasonEndTime = activityEndTime;
	//创建课程
	String courseItemName = xmlData.getParamFromXml("courseItemName");
	String examItemName = xmlData.getParamFromXml("examItemName");
	String itemStartTime = xmlData.getParamFromXml("itemStartTime");
	String courseSyllabus = xmlData.getParamFromXml("courseSyllabus");

	
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\工作目录\\autoTest\\chromedriver33.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}
	
	//后台登录
	@Test
	public void adminLogin(){
		String expectedResult = "首页";
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		String actualResult = adminHomePage.getTitleText();
		AssertJUnit.assertTrue(actualResult.contains(expectedResult));
	}
	
	//创建活动
	@Test(dependsOnMethods = {"adminLogin"})
	public void createActivity(){
		String expectedResult = activityName;
		CreateActivityPage createActivityPage = new CreateActivityPage(this.driver, createActivityUrl);
		ActivitysListPage activitysListPage = createActivityPage.createActivity(activityName, teacherName, signupCount, lowduan, price, signupStartTime, signupEndTime, activityStartTime, activityEndTime, activitysListUrl);
		String actualResult = activitysListPage.getFirstActivityName().getText();
		AssertJUnit.assertTrue(actualResult.contains(expectedResult));
	}
	
	//创建赛季和课程
	@Test(dependsOnMethods = {"createActivity"})
	public void createSeasonAndCourse() throws InterruptedException {
		ActivitysListPage activityListPage = new ActivitysListPage(this.driver, activitysListUrl);
		WebElement activityDetail = activityListPage.getActivityByName(activityName);
		if(activityDetail != null) {
			activityDetail.click();
			this.driver.navigate().refresh();
			String currentUrl = this.driver.getCurrentUrl();
			CreateSeasonPage createSeasonPage = new CreateSeasonPage(this.driver, currentUrl);
			createSeasonPage = createSeasonPage.addActivitySeason(seasonName, seasonPrice, seasonStartTime, seasonEndTime);
			Thread.sleep(10000);
			createSeasonPage = createSeasonPage.addCourseItem(courseItemName, itemStartTime, courseSyllabus);
			Thread.sleep(10000);
			createSeasonPage = createSeasonPage.addExamItem(examItemName, itemStartTime);
		}else {
			AssertJUnit.assertTrue(false);
		}
		
	}
	
	
	@AfterTest
	public void afterTest() {
		this.driver.close();
	}
}
