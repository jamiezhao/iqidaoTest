package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.QuizObject.QuizClassList;
import iqidaoTest.QuizObject.QuizClassficationPage;
import iqidaoTest.QuizObject.QuizListPage;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;

public class QuizTC {
	private WebDriver driver;
	// 定义页面url
	String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
	String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
	String QUIZCLASSurl = xmlData.getParamFromXml("QUIZCLASSurl");
	String QuizListUrl = xmlData.getParamFromXml("QuizListUrl");
	// 登录参数
	String userName = xmlData.getParamFromXml("userName");
	String passWord = xmlData.getParamFromXml("passWord");
	// 试题分类添加参数值
	String quizclassname = xmlData.getParamFromXml("quizclassname");// 分类名称
	String code = xmlData.getParamFromXml("code");
	String newclassname = xmlData.getParamFromXml("newclassname");
	// 试题添加参数值
	String duanwei = xmlData.getParamFromXml("duanwei");// 段位
	String fenlei = xmlData.getParamFromXml("fenlei");// 试题分类1-死活2-布局3-定式4-中盘5-管子
	String goal = xmlData.getParamFromXml("goal");// 计算目标1-计算2-判断3-攻防4-定式5-棋型6-价值7-大局
	String tixing = xmlData.getParamFromXml("tixing");// 试题类型1-摆图2-选择4-自动应答
	String first1 = xmlData.getParamFromXml("first1");// 黑先白先1-黑先2-白先
	String filename = xmlData.getParamFromXml("filename");// sgf文件路径，请与填写信息匹配
	String result = xmlData.getParamFromXml("result");// 结果1-不需选择2-净劫2-打劫
	// 试题查询参数
	String quizname = xmlData.getParamFromXml("quizname");
	// 试题修改
	String newfile = xmlData.getParamFromXml("newfile");
	String newnamequiz = xmlData.getParamFromXml("newnamequiz");

	@BeforeTest
	public void BeforeSuite() {
		System.setProperty("webdriver.chrome.driver", "C:\\231\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
	}
	@Test
	public void QuizClassadd() {
		// 新增分类,首先判断同code是否存在
		QuizClassList list = new QuizClassList(this.driver, QUIZCLASSurl);
		if (list.getPaperSearchList1(code)) {
			// 存在则不添加
			System.out.println("已存在不需要添加分类");
		} else {
			System.out.println("不存在，需要手动添加分类");
			// 定义获取到的名称
			QuizClassficationPage add = new QuizClassficationPage(this.driver, QUIZCLASSurl);
			add.AddQuizClass(code, newclassname, QUIZCLASSurl);
			// 检查分类是否添加成功
			list.WeblementExist(newclassname);
		}
	}
	@Test(dependsOnMethods = { "QuizClassadd" })
	// 前提分类添加成功，执行试题添加
	public void Quizadd() {
		// 新增试题
		QuizClassList add = new QuizClassList(this.driver, QUIZCLASSurl);
		add.QuizAdd(code, duanwei, fenlei, goal, tixing, first1, filename, result, QuizListUrl);
		// 新增试题完毕，点击查询，检查是否添加成功
		QuizListPage search = new QuizListPage(driver, QuizListUrl);
		search.Exist(quizname, fenlei);
	}
	@Test(dependsOnMethods = { "Quizadd" })
	// 前提试题添加成功，执行试题修改
	public void Quizmod() {
		// 修改试题
		QuizListPage mod = new QuizListPage(driver, QuizListUrl);
		mod.ModifyQuiz(quizname, fenlei, newfile, newnamequiz, QuizListUrl);
		this.driver.navigate().refresh();
		// 修改完成检查是否修改
		mod.Exist(newnamequiz, fenlei);
	}
	@Test(dependsOnMethods = { "Quizmod" })
	// 前提试题添加成功，执行试题修改
	public void Quizdel() {
		// 查询试题
		QuizListPage del = new QuizListPage(driver, QuizListUrl);
		del.SearchQuiz(newnamequiz, fenlei);
		// 删除
		del.DelQuiz(newnamequiz, fenlei, newfile, newnamequiz, QuizListUrl);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
