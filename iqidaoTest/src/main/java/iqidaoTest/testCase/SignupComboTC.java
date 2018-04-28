package iqidaoTest.testCase;

import java.awt.Desktop.Action;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iqidaoTest.CoursePaper.SwitchTo;
import iqidaoTest.SignupCombo.CreateComboPage;
import iqidaoTest.Utils.xmlData;
import iqidaoTest.adminPageObject.ActivitysListPage;
import iqidaoTest.adminPageObject.AdminHomePage;
import iqidaoTest.adminPageObject.AdminLoginPage;
import iqidaoTest.adminPageObject.CreateActivityPage;
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
			this.driver = new ChromeDriver();
			this.driver.manage().window().maximize();
		}
		
		//后台登录
		@Test(groups = { "adminLogin" })
		public void adminLogin(){
			String expectedResult = "首页";
			AdminLoginPage adminLoginPage = new AdminLoginPage(this.driver, adminLoginUrl);
			AdminHomePage adminHomePage = adminLoginPage.adminLogin(userName, passWord, adminHomeUrl);
			String actualResult = adminHomePage.getTitleText();
			AssertJUnit.assertTrue(actualResult.contains(expectedResult));
		}
		//创建续报
		@Test(dependsOnMethods = {"adminLogin"},groups = { "CreateCombo" })
		public void CreateCombo(){
			CreateComboPage createComboPage = new CreateComboPage(this.driver, CmoboListUrl);
			createComboPage.CreatCmobo(Comboname, Originalprice, Nowprice, Registnum, fileurl,note);
			createComboPage.ElementExist(Comboname);
		}
		//编辑续报信息以及添加活动
		@Test(dependsOnMethods = {"CreateCombo"},groups = { "EditCombo" })
		public void EditCombo(){
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
			createComboPage.EditCombo(notemodmod, Combonamemod, nowpricemod, activityName,seasonName, CmoboListUrl);
			//检查是否修改成功
			/*Actions action = new Actions(driver) ;
			action.sendKeys(Keys.CONTROL+"w").perform();
			createComboPage.ElementExist(Combonamemod);*/	
		}
		@AfterTest
		public void afterTest() {
			this.driver.quit();
		}
}
