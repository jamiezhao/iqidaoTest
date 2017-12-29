package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.CoursePaper.SwitchTo;
import iqidaoTest.PublicColumnObject.PublicColumn;
import iqidaoTest.PublicColumnObject.PublicColumnList;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;

public class PublicNewsTC {
	private WebDriver driver;
	// 定义页面url
	String adminLoginUrl = "http://testing.iqidao.com/admin001";
	String adminHomeUrl = "http://testing.iqidao.com/admin001/home";
	String publicurl = "http://testing.iqidao.com/admin001/resource/public/article";
	/*
	 * WebElement targetText=this.driver.findElement(By.xpath(
	 * "/html/body/div[1]/div/section[2]/section/header/h3"));
	 */ // 登录参数值
	String userName = "186186";
	String passWord = "111111";
	// 专栏信息添加参数值
	String newname = "zjy_news";
	String modnewname = "zjy_mod";
	String newsclass = "1";

	@BeforeTest
	public void beforeTest() {
		// this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\231\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test
	public void publicclumnadd() {
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		PublicColumn add = new PublicColumn(this.driver, publicurl);
		add.addColoumn(newname, newsclass);
		PublicColumnList list = new PublicColumnList(this.driver, publicurl);
		list.judgeif(newname); 	
		}

	@Test(dependsOnMethods = { "publicclumnadd" })
	public void publicclumnmod() {
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
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
		//检查是否修改成功
		this.driver.navigate().refresh();
		list.judgeif(modnewname);
	}

	// 删除案例
	@Test (dependsOnMethods = { "publicclumnmod" })
	public void publicclumndel() throws InterruptedException {
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
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
