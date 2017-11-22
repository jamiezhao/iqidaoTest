package iqidaoTest.testCase;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import iqidaoTest.frontPageObject.ActivitysPage;
import iqidaoTest.frontPageObject.HomePage;
import iqidaoTest.frontPageObject.LoginPage;

public class TestCase {
	private WebDriver driver;
	//页面url
	String loginUrl = "http://testing.iqidao.com/";
	String activitysListUrl = "http://testing.iqidao.com/trainings";
	
	//登录
	String userName = "186186";
	String passWord = "111111";
	
	@BeforeMethod
	public void setUp(){
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\工作目录\\autoTest\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}
	

	//前台登录
	@Test
	public void testLogin(){
		
		String redirectPageUrl = "http://testing.iqidao.com/home";
		String expectedResult = "学习中心";
		//登录
		LoginPage login = new LoginPage(this.driver, loginUrl);
		HomePage homePage = login.login(userName, passWord, redirectPageUrl);
		
		
		//检查登录结果
		String actualResult = homePage.getTitleText();
		AssertJUnit.assertTrue(actualResult.contains(expectedResult));
		
	}
	
	
	//购买活动
	@Test
	public void buyActivity() {
		ActivitysPage activitysPage = new ActivitysPage(this.driver, activitysListUrl);
		activitysPage.gotoActivityDetailPageByName("zlautoTest");
		
		
		
	}
	
}
