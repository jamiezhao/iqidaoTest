package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.Utils.CommonUtils;
import iqidaoTest.adminPageObject.ActivityUsersPage;
import iqidaoTest.adminPageObject.ActivitysListPage;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;
import iqidaoTest.adminPageObject.CreateActivityPage;
import iqidaoTest.adminPageObject.CreateSeasonPage;

public class ActivityTC {
	private WebDriver driver;
	//页面URL
	String adminLoginUrl = "http://testing.iqidao.com/admin001";
	String adminHomeUrl = "http://testing.iqidao.com/admin001/home";
	String createActivityUrl = "http://testing.iqidao.com/admin001/activity/post";
	String activitysListUrl = "http://testing.iqidao.com/admin001/activities";
	String userCouponListUrl = "http://testing.iqidao.com/admin001/coupon/user";
	String usersListUrl = "http://testing.iqidao.com/admin001/users";
	//登录
	String userName = "186186";
	String passWord = "111111";
	//创建活动
	String activityName = "zlautoTest";
	String teacherName = "zl老师00";
	String signupCount = "0";
	String lowduan = "-4";
	String price = "1";
	String signupStartTime = CommonUtils.setDays(2017, 11, 1, 00, 00);
	String signupEndTime = CommonUtils.setDays(2017, 11, 30, 23, 59);
	String activityStartTime = CommonUtils.setDays(2017, 12, 01, 00, 00);
	String activityEndTime = CommonUtils.setDays(2017, 12, 31, 23, 59);
	//创建赛季
	String seasonName = "第一赛季";
	String seasonPrice = "1";
	String seasonStartTime = activityStartTime;
	String seasonEndTime = activityEndTime;
	//创建课程
<<<<<<< HEAD
	String itemName = "第一课";
	String itemStartTime = CommonUtils.setDays(2017, 12, 01, 17, 00);
	String courseSyllabus = "C:\\工作目录\\K级官子第1课时——研发完成.doc";
=======
	String courseItemName = xmlData.getParamFromXml("courseItemName");
	String examItemName = xmlData.getParamFromXml("examItemName");
	String itemStartTime = xmlData.getParamFromXml("itemStartTime");
	String courseSyllabus = xmlData.getParamFromXml("courseSyllabus");
>>>>>>> upstream/master

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
