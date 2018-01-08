package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.QuizObject.QuizClassList;
import iqidaoTest.QuizObject.QuizClassficationPage;
import iqidaoTest.QuizObject.QuizListPage;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;

public class QuizTC {
	private WebDriver driver;
	// 定义页面url
	String adminLoginUrl = "http://testing.iqidao.com/admin001";
	String adminHomeUrl = "http://testing.iqidao.com/admin001/home";
	String QUIZCLASSurl = "http://testing.iqidao.com/admin001/quiz/classes";
	String QuizListUrl = "http://testing.iqidao.com/admin001/quizzes";
	// 登录参数值
	String userName = "186186";
	String passWord = "111111";
	// 试题分类添加参数值
	String quizclassname = "分班测评";// 分类名称
	String code = "13";
	String newclassname = "试题分类新增";
	// 试题添加参数值
	String duanwei = "1";// 段位
	String fenlei = "1";// 试题分类1-死活2-布局3-定式4-中盘5-管子
	String goal = "1";// 计算目标1-计算2-判断3-攻防4-定式5-棋型6-价值7-大局
	String tixing = "4";// 试题类型1-摆图2-选择4-自动应答
	String first1 = "1";// 黑先白先1-黑先2-白先
	String filename = "C:\\官子sgf\\1K黑先自动应答题-如何破入白空.sgf";// sgf文件路径，请与填写信息匹配
	String result = "1";// 结果1-不需选择2-净劫2-打劫
	// 试题查询参数
	String quizname = "如何破入白空";
	// 试题修改
	String newfile = "C:\\官子sgf\\6D黑先自动应答，如何先手收取一路官子.sgf";
	String newname = "试题名称修改";

	@BeforeTest
	public void beforeTest() {
		// this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\231\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test(groups = {"classadd"}) 
	public void QuizClassadd() {
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
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

	@Test(groups = {"quizadd"})
	//(dependsOnMethods = { "QuizClassadd" })
	// 前提分类添加成功，执行试题添加
	public void Quizadd() {
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		// 新增试题
		QuizClassList add = new QuizClassList(this.driver, QUIZCLASSurl);
		add.QuizAdd(code, duanwei, fenlei, goal, tixing, first1, filename, result, QuizListUrl);
		// 新增试题完毕，点击查询，检查是否添加成功
		QuizListPage search = new QuizListPage(driver, QuizListUrl);
		search.Exist(quizname, fenlei);
	}

	@Test(groups = {"quizmod"})
	//(dependsOnMethods = { "Quizadd" })
	// 前提试题添加成功，执行试题修改
	public void Quizmod() {
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		// 修改试题
		QuizListPage mod = new QuizListPage(driver, QuizListUrl);
		mod.ModifyQuiz(quizname, fenlei, newfile, newname, QuizListUrl);
		this.driver.navigate().refresh();
		// 修改完成检查是否修改
		mod.Exist(newname, fenlei);
	}
	@Test(groups = {"quizdel"})
	//(dependsOnMethods = { "Quizmod" })
	// 前提试题添加成功，执行试题修改
	public void Quizdel() {
		// 登录
		AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
		AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
		// 查询试题
		QuizListPage del = new QuizListPage(driver, QuizListUrl);
		del.SearchQuiz(newname, fenlei);
		// 删除
		del.DelQuiz(newname, fenlei, newfile, newname, QuizListUrl);
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
