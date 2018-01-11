package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.CoursePaper.CourseQuizAddPage;
import iqidaoTest.CoursePaper.PaperListPage;
import iqidaoTest.CoursePaper.SwitchTo;
import iqidaoTest.Utils.CommonUtils;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;

public class CouserPaperQuizTC {
	private WebDriver driver;
	// 页面URL
	String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
	String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
	String paperSearchUrl = xmlData.getParamFromXml("paperSearchUrl");
	// 登录
	String ChormeURL=xmlData.getParamFromXml("ChormeURL");
	String userName = xmlData.getParamFromXml("userName");
	String passWord = xmlData.getParamFromXml("passWord");
	// 查询试卷
	String CourseName = xmlData.getParamFromXml("itemName");
	String exameName = xmlData.getParamFromXml("exameName");
	String papername =CourseName+"练习题";
	// 0-系统试卷；1专项考试；2-专项预习；3-专项课后；4-错题本；5推荐试卷
	String orginselect = xmlData.getParamFromXml("orginselect");
	String papername1 =CourseName+"预习题";
	// 0-系统试卷；1专项考试；2-专项预习；3-专项课后；4-错题本；5推荐试卷
	String orginselect1 = xmlData.getParamFromXml("orginselect1");
	String papername2 = exameName;
	// 0-系统试卷；1专项考试；2-专项预习；3-专项课后；4-错题本；5推荐试卷
	String orginselect2 = xmlData.getParamFromXml("orginselect2");
	String newnamequiz = xmlData.getParamFromXml("newnamequiz");
	@BeforeTest
	public void BeforeTest() {
		System.setProperty("webdriver.chrome.driver", ChormeURL);
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
	}

	// 添加试卷-课后题
	@Test(groups = { "AcitivityPaperQuiz" })
	public void ExercisesAfter() {
		// 查询试卷
		PaperListPage paperserach = new PaperListPage(this.driver, paperSearchUrl);
		paperserach.Search(papername, orginselect);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 进入试卷详细界面
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
		String cu2 = this.driver.getCurrentUrl();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 添加试题
		CourseQuizAddPage addquiz = new CourseQuizAddPage(this.driver);
		addquiz.AddCourseQuiz();
		paperserach.ElementExist();
		// 删除功能检查
		PaperListPage del = new PaperListPage(this.driver, cu2);
		del.Delquiz();
	}

	@Test(groups = { "AcitivityPaperQuiz" })
	public void PreparePaper() {
		// 查询试卷
		PaperListPage paperserach = new PaperListPage(this.driver, paperSearchUrl);
		paperserach.Search(papername1, orginselect1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 进入试卷详细界面
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
		// 添加试题
		CourseQuizAddPage addquiz = new CourseQuizAddPage(this.driver);
		addquiz.AddCourseQuiz();
		paperserach.ElementExist();
	}

	@Test(groups = { "AcitivityPaperQuiz" })
	public void TeatPaper() {
		// 查询试卷
		PaperListPage paperserach = new PaperListPage(this.driver, paperSearchUrl);
		paperserach.Search(papername2, orginselect2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 进入试卷详细界面
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
		// 添加试题
		CourseQuizAddPage addquiz = new CourseQuizAddPage(this.driver);
		addquiz.AddCourseQuiz();
		paperserach.ElementExist();
	}

	@AfterTest
	public void afterTest() {
		this.driver.quit();
	}
}
