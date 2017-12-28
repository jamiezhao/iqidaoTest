package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.CoursePaper.CourseQuizAdd;
import iqidaoTest.CoursePaper.PaperListPage;
import iqidaoTest.CoursePaper.SwitchTo;
import iqidaoTest.Utils.CommonUtils;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;

public class CouserPaperQuizTC {
	private WebDriver driver;
	// 页面URL
	String adminLoginUrl = "http://testing.iqidao.com/admin001";
	String adminHomeUrl = "http://testing.iqidao.com/admin001/home";
	String paperSearchUrl = "http://testing.iqidao.com/admin001/papers";
	// 登录
	String userName = "186186";
	String passWord = "111111";
	// 查询试卷
	String CourseName = "分支-2课";
	String exameName = "分支9考试";
	String papername = CourseName+"练习题";
	// 0-系统试卷；1专项考试；2-专项预习；3-专项课后；4-错题本；5推荐试卷
	String orginselect = "3";
	String papername1 = CourseName+"预习题";
	// 0-系统试卷；1专项考试；2-专项预习；3-专项课后；4-错题本；5推荐试卷
	String orginselect1 = "2";
	String papername2 = exameName;
	// 0-系统试卷；1专项考试；2-专项预习；3-专项课后；4-错题本；5推荐试卷
	String orginselect2 = "1";
	@BeforeTest
	public void beforeTest() {
		// this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\231\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	// 添加试卷-课后题
	@Test
	public void addcoursequiz() {
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		// 查询试卷
		PaperListPage paperserach = new PaperListPage(this.driver, paperSearchUrl);
		paperserach.Search(papername, orginselect);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//进入试卷详细界面
		paperserach.getPaperSearchList(papername);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 切换窗口
		SwitchTo switch1 = new SwitchTo();
		switch1.changewindow(this.driver);
		String cu2=this.driver.getCurrentUrl();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//添加试题
		CourseQuizAdd addquiz = new CourseQuizAdd(this.driver);
		addquiz.AddCourseQuiz();
		paperserach.ElementExist();
		//删除功能检查
		PaperListPage del = new PaperListPage(this.driver, cu2);
		del.delquiz();
		driver.quit();
	}
	@Test
	public void addcoursequiz1() {
		beforeTest();
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		// 查询试卷
		PaperListPage paperserach = new PaperListPage(this.driver, paperSearchUrl);
		paperserach.Search(papername1, orginselect1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//进入试卷详细界面
		paperserach.getPaperSearchList(papername1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 切换窗口
		SwitchTo switch1 = new SwitchTo();
		switch1.changewindow(this.driver);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//添加试题
		CourseQuizAdd addquiz = new CourseQuizAdd(this.driver);
		addquiz.AddCourseQuiz();
		paperserach.ElementExist();
		driver.quit();
	}
	@Test
	public void addcoursequiz2() {
		beforeTest();
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		// 查询试卷
		PaperListPage paperserach = new PaperListPage(this.driver, paperSearchUrl);
		paperserach.Search(papername2, orginselect2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//进入试卷详细界面
		paperserach.getPaperSearchList(papername2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 切换窗口
		SwitchTo switch1 = new SwitchTo();
		switch1.changewindow(this.driver);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//添加试题
		CourseQuizAdd addquiz = new CourseQuizAdd(this.driver);
		addquiz.AddCourseQuiz();
		paperserach.ElementExist();
		driver.quit();
	}
	
	@AfterTest
	public void afterTest() {
		this.driver.quit();
	}
}
