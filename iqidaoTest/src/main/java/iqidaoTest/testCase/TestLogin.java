package iqidaoTest.testCase;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import iqidaoTest.Utils.CommonUtils;
import iqidaoTest.adminPageObject.ActivityUsersPage;
import iqidaoTest.adminPageObject.ActivitysListPage;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;
import iqidaoTest.adminPageObject.CreateActivityPage;
import iqidaoTest.adminPageObject.UserCouponsPage;
import iqidaoTest.frontPageObject.ActivitysPage;
import iqidaoTest.frontPageObject.HomePage;
import iqidaoTest.frontPageObject.LoginPage;

public class TestLogin {
	private WebDriver driver;
	String signupStartTime = CommonUtils.setDays(2017, 10, 1, 00, 00);
	String signupEndTime = CommonUtils.setDays(2017, 10, 15, 23, 59);
	String activityStartTime = CommonUtils.setDays(2017, 10, 16, 00, 00);
	String activityEndTime = CommonUtils.setDays(2017, 10, 30, 23, 59);
	String activitysListUrl = "http://testing.iqidao.com/admin001/activities";
	String activityUsersListUrl = "http://testing.iqidao.com/admin001/activity/users?activityId=234";
	String userCouponListUrl = "http://testing.iqidao.com/admin001/coupon/user";
	String url = "http://testing.iqidao.com/";
	String userName = "186186";
	String passWord = "111111";
	//发放用户优惠券
	String couponUserName = "zl棋手80";
	String couponPrice = "1";
	String couponStartTime = CommonUtils.setDays(2017, 1, 1, 00, 00);
	String couponEndTime = CommonUtils.setDays(2017, 12, 31, 23, 59);
	
	@Before
	public void setUp(){
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\工作目录\\autoTest\\chromedriver33.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}
	
	//前台登录
	@Ignore
	@Test
	public void testLogin(){
		
		String redirectPageUrl = "http://testing.iqidao.com/home";
		String expectedResult = "学习中心";
		//登录
		LoginPage login = new LoginPage(this.driver, url);
		HomePage homePage = login.login(userName, passWord, redirectPageUrl);
		
		ActivitysPage activitysPage = new ActivitysPage(this.driver, activitysListUrl);
		activitysPage.gotoActivityDetailPageByName("zlautoTest");

		
		/*
		List<WebElement> trains = this.driver.findElements(By.tagName("ul"));
		for(WebElement train : trains) {
			System.out.println(train.findElement(By.tagName("li")).getText());
		}
		//检查登录结果
		String actualResult = homePage.getTitleText();
		assertTrue(actualResult.contains(expectedResult));
		*/
		
	}
	
//	@Test
	public void buyTraining() {
		
		this.driver.findElement(By.tagName("ul"));
	}
	
	
	
	//后台登录
	@Test
	public void adminLogin() throws InterruptedException{
		String userName = "186186";
		String passWord = "111111";
		String url = "http://testing.iqidao.com/admin001";
		String redirectPageUrl = "http://testing.iqidao.com/admin001/home";
		String expectedResult = "首页";
		
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, url);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, redirectPageUrl);
		this.driver.manage().window().maximize();
//		String actualResult = adminHomePage.getTitleText();
//		System.out.println(actualResult);
//		
//		assertTrue(actualResult.contains(expectedResult));
		
		String activityName = "zl1115双师";
		String teacherName = "zl老师00";
		String signupCount = "10";
		String lowduan = "-4";
		String price = "1000";
		/*
		CreateActivityPage createActivityPage = new CreateActivityPage(this.driver, "http://testing.iqidao.com/admin001/activity/post");
		ActivitysListPage activitysListPage = createActivityPage.createActivity(activityName, teacherName, signupCount, lowduan, price, signupStartTime, signupEndTime, activityStartTime, activityEndTime, activitysListUrl);
		String actualResult = activitysListPage.getFirstActivityName().getText();
		System.out.println(actualResult);
		*/
		
	
		
		UserCouponsPage userCouponsPage = new UserCouponsPage(this.driver, userCouponListUrl);
		userCouponsPage.addUserCoupon(couponUserName, couponPrice, couponStartTime, couponEndTime);
		
		/*
		ActivitysListPage activitysListPage = new ActivitysListPage(this.driver, activitysListUrl);
		String activityUrl = activitysListPage.getActivityUrlByName("性能一千人");
		this.driver.get(activityUrl);
		WebElement table = this.driver.findElement(By.id("special-list"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for(WebElement col:cols) {
				System.out.println(col.getText());
			}
			
		}
		*/
		
//		CreateSeasonPage createSeasonPage = new CreateSeasonPage(this.driver, activityUrl);
//		createSeasonPage.addActivitySeason("zlseason", "100", "2017-10-13 00:00", "2017-10-31 23:59");
//		createSeasonPage.addCourceItem("zlcourse", "2017-10-13 00:00", "C:\\工作目录\\K级官子第1课时——研发完成.doc");
	}
	

	@Ignore
	@Test
	public void createActivity(){
		String activityName = "zltest";
		String teacherName = "zl老师00";
		String signupCount = "0";
		String lowduan = "-4";
		String price = "1000";
		String url = "http://testing.iqidao.com/admin001/activity/post";
		String expectedResult = activityName;
		
		CreateActivityPage createActivityPage = new CreateActivityPage(this.driver, url);
		ActivitysListPage activitysListPage = createActivityPage.createActivity(activityName, teacherName, signupCount, lowduan, price, signupStartTime, signupEndTime, activityStartTime, activityEndTime, activitysListUrl);
		String actualResult = activitysListPage.getFirstActivityName().getText();
		System.out.println(actualResult);
		
		assertTrue(actualResult.contains(expectedResult));
	}
	
	@After
	public void tearDown(){
//		this.driver.close();
	}

}
