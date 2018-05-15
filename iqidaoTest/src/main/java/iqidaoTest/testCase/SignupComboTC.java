package iqidaoTest.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.CoursePaper.SwitchTo;
import iqidaoTest.SignupCombo.CreateComboPage;
import iqidaoTest.Utils.MyChormeDriver;
import iqidaoTest.Utils.TestProperties;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;
public class SignupComboTC {
	private WebDriver driver;
	//页面URL
		String adminLoginUrl = xmlData.getParamFromXml("adminLoginUrl");
		String adminHomeUrl = xmlData.getParamFromXml("adminHomeUrl");
		String CmoboListUrl = xmlData.getParamFromXml("CmoboListUrl");
		//登录
		String ChormeURL=xmlData.getParamFromXml("ChormeURL");
		String userName = xmlData.getParamFromXml("userName");
		String passWord = xmlData.getParamFromXml("passWord");
		//创建续报
		String Comboname = xmlData.getParamFromXml("Comboname");
		String Originalprice = xmlData.getParamFromXml("Originalprice");
		String Nowprice = xmlData.getParamFromXml("Nowprice");
		String Registnum = xmlData.getParamFromXml("Registnum");
		String fileurl = xmlData.getParamFromXml("fileurl");
		String note = xmlData.getParamFromXml("note");
		//编辑续报
		String notemodmod = xmlData.getParamFromXml("notemodmod");
		String Combonamemod = xmlData.getParamFromXml("Combonamemod");
		String nowpricemod = xmlData.getParamFromXml("nowpricemod");
		String activityName = xmlData.getParamFromXml("activityName");
		String seasonName = xmlData.getParamFromXml("seasonName");
		@BeforeTest
		public void beforeTest() {
			System.setProperty("webdriver.chrome.driver", ChormeURL);
			//多个案例连续跑，只打开1个浏览器时用这个
			TestProperties prop =new TestProperties();
			String  driverserver = prop.GetValueByKey("Test.Properties", "Driver");
			String  caseSession = prop.GetValueByKey("Test.Properties", "Sessionid");
			this.driver = new MyChormeDriver(driverserver,caseSession);
			/*//单个测试案例执行时使用
			this.driver = new ChromeDriver();
			String expectedResult = "首页";
			AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
			AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
			String actualResult = adminHomePage.getTitleText();
			AssertJUnit.assertTrue(actualResult.contains(expectedResult));*/
			this.driver.manage().window().maximize();
		}
		//创建续报
		@Test(groups = { "EditCombo" },priority = 0)
		public void CreateCombo(){
			CreateComboPage createComboPage = new CreateComboPage(this.driver, CmoboListUrl);
			createComboPage.CreatCmobo(Comboname, Originalprice, Nowprice, Registnum, fileurl,note);
			createComboPage.ElementExist(Comboname);
		}
		//编辑续报信息以及添加活动
		@Test(groups = { "EditCombo" },priority = 1)
		public void EditCombo() throws Exception{
			CreateComboPage createComboPage = new CreateComboPage(this.driver, CmoboListUrl);
			createComboPage.FindCombo(Comboname);
			SwitchTo switch1 = new SwitchTo();
			switch1.changewindow(this.driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createComboPage.EditCombo(notemodmod, Combonamemod, nowpricemod, activityName,seasonName);
		}
		/*@AfterTest
		public void afterTest() {
			this.driver.quit();
		}*/
}
