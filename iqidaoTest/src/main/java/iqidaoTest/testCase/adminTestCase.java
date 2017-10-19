package iqidaoTest.testCase;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.Utils.CommonUtils;
import iqidaoTest.pageObject.ActivitysListPage;
import iqidaoTest.pageObject.AdminHomePage;
import iqidaoTest.pageObject.AdminLoginPage;
import iqidaoTest.pageObject.CreateActivityPage;
import iqidaoTest.pageObject.CreateSeasonPage;

public class adminTestCase {
	private WebDriver driver;
	//页面URL
	String adminLoginUrl = "http://101.132.45.64/admin001";
	String adminHomeUrl = "http://101.132.45.64/admin001/home";
	String createActivityUrl = "http://101.132.45.64/admin001/activity/post";
	String activitysListUrl = "http://101.132.45.64/admin001/activities";
	//登录
	String userName = "186186";
	String passWord = "111111";
	//创建活动
	String activityName = "zltest";
	String teacherName = "zl老师00";
	String signupCount = "0";
	String lowduan = "-4";
	String price = "1000";
	String signupStartTime = CommonUtils.setDays(2017, 10, 1, 00, 00);
	String signupEndTime = CommonUtils.setDays(2017, 10, 15, 23, 59);
	String activityStartTime = CommonUtils.setDays(2017, 10, 16, 00, 00);
	String activityEndTime = CommonUtils.setDays(2017, 10, 30, 23, 59);
	//创建赛季
	String seasonName = "第一赛季";
	String seasonPrice = "0";
	String seasonStartTime = activityStartTime;
	String seasonEndTime = activityEndTime;
	//创建课程
	String itemName = "第一课";
	String itemStartTime = CommonUtils.setDays(2017, 10, 17, 17, 00);
	String courseSyllabus = "C:\\工作目录\\K级官子第1课时——研发完成.doc";
	
	@BeforeTest
	public void beforeTest() {
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\工作目录\\autoTest\\chromedriver.exe");
		this.driver = new ChromeDriver();
	}
	
	//后台登录
	@Test
	public void adminLogin(){
		String expectedResult = "首页";
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		this.driver.manage().window().maximize();
		String actualResult = adminHomePage.getTitleText();
		assertTrue(actualResult.contains(expectedResult));
	}
	
	//创建活动
	@Test(dependsOnMethods = {"adminLogin"})
	public void createActivity(){
		String expectedResult = activityName;
		CreateActivityPage createActivityPage = new CreateActivityPage(this.driver, createActivityUrl);
		ActivitysListPage activitysListPage = createActivityPage.createActivity(activityName, teacherName, signupCount, lowduan, price, signupStartTime, signupEndTime, activityStartTime, activityEndTime, activitysListUrl);
		String actualResult = activitysListPage.getFirstActivityName().getText();
		assertTrue(actualResult.contains(expectedResult));
	}
	
	//创建赛季和课程
	@Test(dependsOnMethods = {"createActivity"})
	public void createSeasonAndCourse() {
		ActivitysListPage activityListPage = new ActivitysListPage(this.driver, activitysListUrl);
		String editActivityUrl = activityListPage.getActivityUrlByName(activityName);
		System.out.println(editActivityUrl);
		this.driver.get(editActivityUrl);
		CreateSeasonPage createSeasonPage = new CreateSeasonPage(this.driver, editActivityUrl);
		createSeasonPage.addActivitySeason(seasonName, seasonPrice, seasonStartTime, seasonEndTime);
		createSeasonPage.addCourceItem(itemName, itemStartTime, courseSyllabus);
		boolean flag = createSeasonPage.findItemByName(itemName);
		assertTrue(flag);
	}
	
	@AfterTest
	public void afterTest() {
		this.driver.close();
	}

}
