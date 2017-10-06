package iqidaoTest.testCase;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import iqidaoTest.pageObject.AdminHomePage;
import iqidaoTest.pageObject.AdminLoginPage;
import iqidaoTest.pageObject.HomePage;
import iqidaoTest.pageObject.LoginPage;

public class TestLogin {
	private WebDriver driver;
	
	@Before
	public void setUp(){
		this.driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", "C:\\工作目录\\autoTest\\chromedriver.exe");
//		this.driver = new ChromeDriver();
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
	public void adminLogin(){
		String userName = "186186";
		String passWord = "111111";
		String url = "http://101.132.45.64/admin001";
		String redirectPageUrl = "http://101.132.45.64/admin001/home";
		String expectedResult = "首页";
		
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, url);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, redirectPageUrl);
		this.driver.manage().window().maximize();
//
//		this.driver.findElement(By.xpath("html/body/div[1]/aside/section/ul/li[1]/a/span[1]")).click();
//		this.driver.findElement(By.xpath("html/body/div[1]/aside/section/ul/li[1]/ul/li/a/span")).click();
//		this.driver.findElement(By.linkText("创建活动")).click();
		this.driver.get("http://101.132.45.64/admin001/activity/post");
		this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		
		//设置活动教师，1。点击输入框，弹出搜索框 2。在输入框输入搜索内容 3.等待ajax加载数据，点击数据
		List<WebElement> lists = this.driver.findElements(By.className("select2-selection__placeholder"));
		lists.get(1).click();
		this.driver.findElement(By.className("select2-search__field")).sendKeys("zl老师00");
		new WebDriverWait(this.driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".clearfix.text-center.ajaxSelect")));
		this.driver.findElement(By.cssSelector(".clearfix.text-center.ajaxSelect")).click();
		
		
		Select activityPatternSelect = new Select(this.driver.findElement(By.name("activityVersion")));
		activityPatternSelect.selectByValue("2");
		
		
		
//		String actualResult = adminHomePage.getTitleText();
//		System.out.println(actualResult);
//		assertTrue(actualResult.contains(expectedResult));
	}
	

	
//	@Test
	public void createActivity(){
		String url = "http://101.132.45.64/admin001/activities";
		
		
	}
	
	@After
	public void tearDown(){
//		this.driver.close();
	}

}
