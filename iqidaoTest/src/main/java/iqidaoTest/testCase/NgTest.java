package iqidaoTest.testCase;

import org.testng.annotations.Test;

import iqidaoTest.pageObject.HomePage;
import iqidaoTest.pageObject.LoginPage;

import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class NgTest {
	private WebDriver driver;
  @Test
  public void f() {
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
  @BeforeTest
  public void beforeTest() {
	  this.driver = new FirefoxDriver();
  }

  @AfterTest
  public void afterTest() {
	  this.driver.close();
  }

}
