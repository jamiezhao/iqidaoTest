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

public class ActivityUserTC {
	private WebDriver driver;
	//页面URL
	String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
	String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
	String activitysListUrl = xmlData.getParamFromXml("activitysListUrl");

	//登录
	String userName = xmlData.getParamFromXml("userName");
	String passWord = xmlData.getParamFromXml("passWord");
	
	String activityName = xmlData.getParamFromXml("activityName");
	//添加或删除活动用户
	String activityUserName = xmlData.getParamFromXml("activityUserName");

	
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
	
	//手工添加活动用户
//	@Test(dependsOnMethods = {"createSeasonAndCourse"})
	@Test(dependsOnMethods = {"adminLogin"})
	public void addActivityUser() {
		ActivitysListPage activitysListPage = new ActivitysListPage(this.driver, activitysListUrl);
		WebElement activityUsers = activitysListPage.getActivityUsersByName(activityName);
		if(activityUsers != null) {
			System.out.println("--click---------");
			activityUsers.click();
			this.driver.navigate().refresh();
			String currentUrl = this.driver.getCurrentUrl();
			ActivityUsersPage activityUsersPage = new ActivityUsersPage(this.driver, currentUrl);
			activityUsersPage.addActivityUser(activityUserName);
			boolean result = activityUsersPage.findActivityUserName(activityUserName);
			System.out.println(result);
			AssertJUnit.assertTrue(result);
		}else {
			AssertJUnit.assertTrue(false);
		}
		
		
	}
	
	//手动删除活动用户
	@Test(dependsOnMethods = {"addActivityUser"})
	public void deleteActivityUser() {
		ActivitysListPage activityListPage = new ActivitysListPage(this.driver, activitysListUrl);
		WebElement activityUsers = activityListPage.getActivityUsersByName(activityName);
		if(activityUsers != null) {
			activityUsers.click();
			this.driver.navigate().refresh();
			String currentUrl = this.driver.getCurrentUrl();
			ActivityUsersPage activityUsersPage = new ActivityUsersPage(this.driver, currentUrl);
			activityUsersPage.deleteActivityUser(activityUserName);
			boolean result = activityUsersPage.findActivityUserName(activityUserName);
			AssertJUnit.assertTrue(!result);
		}else {
			AssertJUnit.assertTrue(false);
		}
		
	}
	
	@AfterTest
	public void afterTest() {
		this.driver.close();
	}

}
