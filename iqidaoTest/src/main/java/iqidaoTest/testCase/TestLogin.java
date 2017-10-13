package iqidaoTest.testCase;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import iqidaoTest.Utils.CommonUtils;
import iqidaoTest.pageObject.ActivitysListPage;
import iqidaoTest.pageObject.AdminHomePage;
import iqidaoTest.pageObject.AdminLoginPage;
import iqidaoTest.pageObject.BasePage;
import iqidaoTest.pageObject.CreateActivityPage;
import iqidaoTest.pageObject.CreateSeasonPage;
import iqidaoTest.pageObject.HomePage;
import iqidaoTest.pageObject.LoginPage;

public class TestLogin {
	private WebDriver driver;
	
	@Before
	public void setUp(){
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\工作目录\\autoTest\\chromedriver.exe");
		this.driver = new ChromeDriver();
	}
	
	//前台登录
	@Ignore
	@Test
	public void testLogin(){
		String userName = "186186";
		String passWord = "111111";
		String url = "http://101.132.45.64/";
		String redirectPageUrl = "http://101.132.45.64/home";
		String expectedResult = "学习中心";
		//登录
		LoginPage login = new LoginPage(this.driver, url);
		HomePage homePage = login.login(userName, passWord, redirectPageUrl);
		
		//检查登录结果
		String actualResult = homePage.getTitleText();
//		System.out.println(actualResult);
		assertTrue(actualResult.contains(expectedResult));
	}
	
	//后台登录
	@Test
	public void adminLogin() throws InterruptedException{
		String userName = "186186";
		String passWord = "111111";
		String url = "http://101.132.45.64/admin001";
		String redirectPageUrl = "http://101.132.45.64/admin001/home";
		String expectedResult = "首页";
		
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, url);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, redirectPageUrl);
		this.driver.manage().window().maximize();
//		String actualResult = adminHomePage.getTitleText();
//		System.out.println(actualResult);
//		
//		assertTrue(actualResult.contains(expectedResult));
		
		
		ActivitysListPage activitysListPage = new ActivitysListPage(this.driver, "http://101.132.45.64/admin001/activities");
		String activityUrl = activitysListPage.getActivityUrlByName("性能一千人");
		CreateSeasonPage createSeasonPage = new CreateSeasonPage(this.driver, activityUrl);
		createSeasonPage.addActivitySeason("zlseason", "100", "2017-10-13 00:00", "2017-10-31 23:59");
		createSeasonPage.addCourceItem("zlcourse", "2017-10-13 00:00", "C:\\工作目录\\K级官子第1课时——研发完成.doc");
	}
	

	
//	@Test
	public void createActivity(){
		String activityName = "zltest" + CommonUtils.DatetoString(new Date());
		String teacherName = "zl老师00";
		String signupCount = "10";
		String lowduan = "-4";
		String price = "1000";
		String redirectPageUrl = "http://101.132.45.64/admin001/activities";
		String url = "http://101.132.45.64/admin001/activity/post";
		String expectedResult = activityName;
		
		CreateActivityPage createActivityPage = new CreateActivityPage(this.driver, url);
		ActivitysListPage activitysListPage = createActivityPage.createActivity(activityName, teacherName, signupCount, lowduan, price, redirectPageUrl);
		String actualResult = activitysListPage.getFirstActivityName().getText();
		System.out.println(actualResult);
		
		assertTrue(actualResult.contains(expectedResult));
	}
	
	@After
	public void tearDown(){
//		this.driver.close();
	}

}
