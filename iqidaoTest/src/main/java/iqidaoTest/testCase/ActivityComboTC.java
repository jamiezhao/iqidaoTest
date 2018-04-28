package iqidaoTest.testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.SignupCombo.CreateComboPage;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.adminPageObject.ActivityComboPage;
import iqidaoTest.adminPageObject.ActivitysListPage;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;

public class ActivityComboTC {

	private WebDriver driver;
	// 页面URL
	String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
	String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
	String CmoboListUrl = xmlData.getParamFromXml("CmoboListUrl");
	String activitysListUrl = xmlData.getParamFromXml("activitysListUrl");
	// 登录
	String ChormeURL = xmlData.getParamFromXml("ChormeURL");
	String userName = xmlData.getParamFromXml("userName");
	String passWord = xmlData.getParamFromXml("passWord");
	// 创建续报关联
	String Combonamemod = xmlData.getParamFromXml("Combonamemod");
	String combostartime = xmlData.getParamFromXml("combostartime");
	String comboendtime = xmlData.getParamFromXml("comboendtime");
	String activityName = xmlData.getParamFromXml("activityName");

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ChormeURL);
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	// 后台登录
	@Test(groups = { "adminLogin" })
	public void adminLogin() {
		String expectedResult = "首页";
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		String actualResult = adminHomePage.getTitleText();
		AssertJUnit.assertTrue(actualResult.contains(expectedResult));
	}

	// 创建活动-续报关联
	@Test(dependsOnMethods = { "adminLogin" }, groups = { "CreateComboLink" })
	public void ComboLink() {
		ActivityComboPage activityComboPage = new ActivityComboPage(this.driver, activitysListUrl);
		activityComboPage.SearchActivity(activityName);
		activityComboPage.CreatActivityCombo(Combonamemod, combostartime, comboendtime);
	}
}
