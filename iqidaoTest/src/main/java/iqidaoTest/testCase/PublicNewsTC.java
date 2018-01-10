package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.CoursePaper.SwitchTo;
import iqidaoTest.PublicColumnObject.PublicColumn;
import iqidaoTest.PublicColumnObject.PublicColumnList;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;
import iqidaoTest.Utils.CommonUtils;
import iqidaoTest.Utils.xmlData;

public class PublicNewsTC {
	private WebDriver driver;
	// 定义页面url
	String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
	String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
	String publicurl = xmlData.getParamFromXml("publicurl");
	// 登录参数
	String userName = xmlData.getParamFromXml("userName");
	String passWord = xmlData.getParamFromXml("passWord");
	// 专栏信息添加参数值
	String newname = xmlData.getParamFromXml("newname");
	String modnewname = xmlData.getParamFromXml("modnewname");
	String newsclass = xmlData.getParamFromXml("newsclass");

	@BeforeTest
	public void BeforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\231\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
	}
	@Test
	//添加新信息
	public void publicclumnadd() {
		PublicColumn add = new PublicColumn(this.driver, publicurl);
		add.addColoumn(newname, newsclass);
		PublicColumnList list = new PublicColumnList(this.driver, publicurl);
		list.judgeif(newname);
	}

	@Test(dependsOnMethods = { "publicclumnadd" })
	public void publicclumnmod() {
		// 查询到已添加信息
		PublicColumnList list = new PublicColumnList(this.driver, publicurl);
		list.Search(newname);
		list.getNewsSearchList(newname);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 修改新闻
		// 切换窗口
		SwitchTo switch1 = new SwitchTo();
		switch1.changewindow(this.driver);
		list.ModColoumn(modnewname);
		// 检查是否修改成功
		this.driver.navigate().refresh();
		list.judgeif(modnewname);
	}

	// 删除案例
	@Test(dependsOnMethods = { "publicclumnmod" })
	public void publicclumndel() throws InterruptedException {
		PublicColumnList list = new PublicColumnList(this.driver, publicurl);
		list.DEL(modnewname);
		// 判断是否删除
		list.ElementExist(modnewname);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
