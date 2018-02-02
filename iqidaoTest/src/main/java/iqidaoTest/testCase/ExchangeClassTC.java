package iqidaoTest.testCase;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.ExchangeClass.ExClassList;
import iqidaoTest.ExchangeClass.ExClassPage;
import iqidaoTest.Utils.SetLog;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;

public class ExchangeClassTC {
private WebDriver driver;
//定义变量值
//页面URL
	String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
	String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
	String ExClassUrl=xmlData.getParamFromXml("ExClassUrl");
	// 登录
	String ChormeURL=xmlData.getParamFromXml("ChormeURL");
	String userName = xmlData.getParamFromXml("userName");
	String passWord = xmlData.getParamFromXml("passWord");
	//换班数据
	String ExUserName=xmlData.getParamFromXml("activityUserName");
	String OriginActivityName=xmlData.getParamFromXml("activityName");
	String OriginSeason=xmlData.getParamFromXml("seasonName");
	String ToActivityName=xmlData.getParamFromXml("ToActivityName");
	String ToSeason=xmlData.getParamFromXml("ToSeason");
	@BeforeTest
	public void BeforeTest() {
		SetLog setlog=new SetLog();
		setlog.SetLogInfo();
		System.setProperty("webdriver.chrome.driver", ChormeURL);
		this.driver = new ChromeDriver();
		//this.driver.manage().window().maximize();
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
	}
	@Test(groups = { "ExClass" })
	//添加换班信息
	public void ExClass(){
		ExClassPage exclass=new ExClassPage(driver,ExClassUrl);
		exclass.AddExClass(ExUserName, OriginActivityName, OriginSeason, ToActivityName, ToSeason);
		//判断是否添加成功
		ExClassList searsh=new ExClassList(driver,ExClassUrl);
		searsh.ElementExist(ExUserName, OriginActivityName, ToActivityName);
	}
	@AfterTest
	public void afterTest() {
		this.driver.close();
	}
}
