package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.CoursePaper.SwitchTo;
import iqidaoTest.PublicColumnObject.PublicColumnAddPage;
import iqidaoTest.PublicColumnObject.PublicColumnListPage;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;

public class PublicNewsTC {
	private WebDriver driver;
	// 定义页面url
	String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
	String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
	String publicurl = xmlData.getParamFromXml("publicurl");
	// 登录参数
	String ChormeURL=xmlData.getParamFromXml("ChormeURL");
	String userName = xmlData.getParamFromXml("userName");
	String passWord = xmlData.getParamFromXml("passWord");
	// 专栏信息添加参数值
	String newname = xmlData.getParamFromXml("newname");
	String modnewname = xmlData.getParamFromXml("modnewname");
	String newsclass = xmlData.getParamFromXml("newsclass");
	String fileurl = xmlData.getParamFromXml("fileurl");

	@BeforeTest
	public void BeforeTest() {
		System.setProperty("webdriver.chrome.driver", ChormeURL);
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		String expectedResult = "首页";
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		String actualResult = adminHomePage.getTitleText();
		AssertJUnit.assertTrue(actualResult.contains(expectedResult));
	}
	@Test
	//添加新信息
	public void PublicclumnAdd() {
		PublicColumnAddPage add = new PublicColumnAddPage(this.driver, publicurl);
		add.AddColoumn(newname, newsclass,fileurl);
		PublicColumnListPage list = new PublicColumnListPage(this.driver, publicurl);
		list.Judgeif(newname);
	}

	@Test(dependsOnMethods = { "PublicclumnAdd" })
	public void publicclumnMod() {
		// 查询到已添加信息
		PublicColumnListPage list = new PublicColumnListPage(this.driver, publicurl);
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
		list.Judgeif(modnewname);
	}

	// 删除案例
	@Test(dependsOnMethods = { "publicclumnMod" })
	public void publicclumnDel() throws InterruptedException {
		PublicColumnListPage list = new PublicColumnListPage(this.driver, publicurl);
		list.DEL(modnewname);
		// 判断是否删除
		list.ElementExist(modnewname);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
