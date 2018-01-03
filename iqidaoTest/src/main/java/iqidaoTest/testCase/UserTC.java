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
import iqidaoTest.adminPageObject.UserCouponsPage;
import iqidaoTest.adminPageObject.UsersListPage;

public class UserTC {
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
	String itemName = "第一课";
	String itemStartTime = CommonUtils.setDays(2017, 12, 01, 17, 00);
	String courseSyllabus = "C:\\工作目录\\K级官子第1课时——研发完成.doc";
	//添加或删除活动用户
	String activityUserName = "zl棋手80";
	//发放用户优惠券
	String couponUserName = "zl棋手80";
	String couponPrice = "1";
	String couponStartTime = CommonUtils.setDays(2017, 12, 01, 00, 00);
	String couponEndTime = CommonUtils.setDays(2017, 12, 31, 23, 59);
	//添加用户
	String userRealName = "zltest";
	String mobilePhone = "11111111111";	//专属测试手机号
	String userGroup = "100"; 	//100为客服身份
	String userPassword = "111111";
	
	
	
	@BeforeTest
	public void beforeTest() {
//		this.driver = new FirefoxDriver();
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
	
	
	//发放用户优惠券
	@Test(dependsOnMethods = {"createSeasonAndCourse"})
	public void sendUserCoupon() {
		UserCouponsPage userCouponsPage = new UserCouponsPage(this.driver, userCouponListUrl);
		userCouponsPage.addUserCoupon(couponUserName, couponPrice, couponStartTime, couponEndTime);
		boolean result = userCouponsPage.checkFirstUserCoupon(couponUserName);
		AssertJUnit.assertTrue(result);
	}
	
	
	//添加用户
	@Test(dependsOnMethods = {"adminLogin"})
	public void addUser() {
		UsersListPage usersListPage = new UsersListPage(this.driver, usersListUrl);
		usersListPage.addUser(userRealName, mobilePhone, userGroup, userPassword);
		
	}
	
	@AfterTest
	public void afterTest() {
		this.driver.close();
	}

}
