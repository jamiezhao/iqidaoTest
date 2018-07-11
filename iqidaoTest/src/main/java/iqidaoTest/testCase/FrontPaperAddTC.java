package iqidaoTest.testCase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.CoursePaper.SwitchTo;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.WebFrontObject.CreatePaperPage;
import iqidaoTest.WebFrontObject.LoginObject;
import iqidaoTest.adminPageObject.ActivitysListPage;
import iqidaoTest.adminPageObject.CreateActivityPage;

public class FrontPaperAddTC {

		private WebDriver driver;
		// TODO Auto-generated constructor stub
		// 页面URL
		String WebFrontUrl = xmlData.getParamFromXml("WebFrontUrl");
		String CreatePapertUrl = xmlData.getParamFromXml("CreatePapertUrl");
		// 登录
		String ChormeURL = xmlData.getParamFromXml("ChormeURL");
		String frontName = xmlData.getParamFromXml("frontName");
		String passWord = xmlData.getParamFromXml("passWord");
		//创建试卷
		String Papername[] = xmlData.getParamArrayFromXml("Papername");
		String PaperStarttime = xmlData.getParamFromXml("PaperStarttime");
		//导入试题
		String frontquizurl = xmlData.getParamFromXml("frontquizurl");
		String dayuselect = xmlData.getParamFromXml("dayuselect");	
	@BeforeTest
	public void beforeTest() throws IOException {
		System.setProperty("webdriver.chrome.driver", ChormeURL);
		/*//多个案例连续跑，只打开1个浏览器时用这个
		TestProperties prop =new TestProperties();
		String  driverserver = prop.GetValueByKey("Test.Properties", "Driver");
		String  caseSession = prop.GetValueByKey("Test.Properties", "Sessionid");
		this.driver = new MyChormeDriver(driverserver,caseSession);*/
		// 单个测试案例执行时使用
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	// 创建作业试卷
	@Test(priority = 0)
	public void CreatPaper() {
		LoginObject login = new LoginObject(this.driver, WebFrontUrl);
		login.Login(frontName, passWord);	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 1; i++) {
			CreatePaperPage createpaper=new CreatePaperPage(this.driver, CreatePapertUrl);
			createpaper.CreatePaper(Papername[i], PaperStarttime);
		}
	}
	//试卷导入试题以及随机添加已有试题
	// 切换窗口
	@Test(priority = 1)
	public void InsertQuiz() {
		LoginObject login = new LoginObject(this.driver, WebFrontUrl);
		login.Login(frontName, passWord);	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			CreatePaperPage insertquiz=new CreatePaperPage(this.driver, CreatePapertUrl);
			//查詢試卷
			insertquiz.SearchPaper(Papername[0]);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//切換當前窗口界面
			SwitchTo switch1 = new SwitchTo();
			switch1.changewindow(this.driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//導入試題且添加隨機選擇试题
			insertquiz.InsertQuiz(frontquizurl,dayuselect,CreatePapertUrl);
	}
	
	  @AfterTest 
	  public void afterTest() { 
		  this.driver.close();
		  }
	 
}
