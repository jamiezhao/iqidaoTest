package iqidaoTest.SignupCombo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import iqidaoTest.adminPageObject.BasePage;

public class CreateComboPage extends BasePage{
private WebDriver driver;
private static Log log = LogFactory.getLog(CreateComboPage.class); 

	public CreateComboPage(WebDriver driver, String url) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.url = url;
		this.goTo();
	}
	//新增续报
	By CreateButton = By.xpath("/html/body/div/div/section[2]/div/header/h3/a");
	By ComboNameInput = By.xpath("//*[@id='add_combo']/div[2]/div/div[1]/div[1]/input");
	By OriginalpriceInput = By.name("rawprice");
	By RegistNumInput = By.name("registenum");
	By NowPriceInput = By.name("nowprice");
	By EnableSiginButton = By.xpath("//*[@id='add_combo']/div[2]/div/div[3]/div[1]/div/div/div/div/label/span");
	By QruploadButton = By.name("wxGroupQr");
	By NoteInput = By.name("note");
	By SubmitButton = By.xpath("//*[@id='add_combo']/div[3]/button[2]");
	//查询续报
	By SearchComboNameInput = By.xpath("//*[@id='items-query']/div[1]/div/input");
	By SearchButton = By.xpath("//*[@id='items-query']/div[3]/div/div/button");
	//编辑续报
	By ComboNameEditInput = By.name("comboname");
	By EnablemodSiginButton = By.xpath("/html/body/div/div/section[2]/div/form[1]/div/div[3]/div[1]/div/div/div/div/label/span");
	By SaveButton = By.xpath("/html/body/div/div/section[2]/div/form[1]/div/div[4]/div/button");
	By Activity = By.xpath("/html/body/div/div/section[2]/div/form[2]/div[1]/div[1]/div/span[1]/span[1]/span");
	By ActivityInput = By.xpath("/html/body/span/span/span[1]/input");
	By ActivitySelect = By.xpath("/html/body/div/div/section[2]/div/form[2]/div[1]/div[1]/div/span[1]");
	By Season = By.xpath("/html/body/div/div/section[2]/div/form[2]/div[1]/div[2]/div/span[1]");
	By SeasonInput = By.xpath("/html/body/span/span/span[1]/input");
	By SaveactivityButton = By.xpath("/html/body/div/div/section[2]/div/form[2]/div[1]/div[3]/div/div/input");


	public WebElement getCreateButton() {
		return this.dr.findElement(CreateButton);
	}
	public WebElement getComboNameInput() {
		return this.dr.findElement(ComboNameInput);
	}
	public WebElement getOriginalpriceInput() {
		return this.dr.findElement(OriginalpriceInput);
	}
	public WebElement getRegistNumInput() {
		return this.dr.findElement(RegistNumInput);
	}
	public WebElement getNowPriceInput() {
		return this.dr.findElement(NowPriceInput);
	}
	public WebElement getEnableSiginButton() {
		return this.dr.findElement(EnableSiginButton);
	}
	public WebElement getQruploadButton() {
		return this.dr.findElement(QruploadButton);
	}
	public WebElement getNoteInput() {
		return this.dr.findElement(NoteInput);
	}
	public WebElement getSubmitButton() {
		return this.dr.findElement(SubmitButton);
	}
	public WebElement getSearchComboNameInput() {
		return this.dr.findElement(SearchComboNameInput);
	}
	public WebElement getSearchButton() {
		return this.dr.findElement(SearchButton);
	}
	public WebElement getComboNameEditInput() {
		return this.dr.findElement(ComboNameEditInput);
	}
	public WebElement getSaveButton() {
		return this.dr.findElement(SaveButton);
	}
	public WebElement getActivity() {
		return this.dr.findElement(Activity);
	}
	public WebElement getActivityInput() {
		return this.dr.findElement(ActivityInput);
	}
	public WebElement getActivitySelect() {
		return this.dr.findElement(ActivitySelect);
	}
	public WebElement getSeason() {
		return this.dr.findElement(Season);
	}
	public WebElement getSeasonInput() {
		return this.dr.findElement(SeasonInput);
	}
	public WebElement getSaveactivityButton() {
		return this.dr.findElement(SaveactivityButton);
	}
	public WebElement getEnablemodSiginButton() {
		return this.dr.findElement(EnablemodSiginButton);
	}
	//创建续报
	public WebDriver   CreatCmobo(String Comboname,String Originalprice,String Nowprice,String Registnum,String fileurl,String note) {
		this.getCreateButton().click();
		this.getComboNameInput().sendKeys(Comboname);
		this.getOriginalpriceInput().sendKeys(Originalprice);
		this.getNowPriceInput().sendKeys(Nowprice);
		this.getRegistNumInput().sendKeys(Registnum);
		this.getQruploadButton().sendKeys(fileurl);
		this.getNoteInput().sendKeys(note);
		this.getEnableSiginButton().click();//默认开启报名
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.getSubmitButton().click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.dr;
	}
	//查询功能
		public String  SearchCombo(String Comboname) {
			this.getSearchComboNameInput().sendKeys(Comboname);
			this.getSearchButton().click();
			return this.url;
		}
	//判断是否添加成功
	public WebDriver  ElementExist(String Comboname) {
		SearchCombo(Comboname);
		System.out.println("查询成功");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement comboname1 = this.dr.findElement(By.xpath("/html/body/div/div/section[2]/div/div[3]/div/table/tbody/tr[1]/td[3]/a"));
		System.out.println("找到了"+comboname1.getText());
		if(comboname1.getText().contains(Comboname))
		{
			System.out.println("续报添加成功");
		}
		else
		{
			log.error("续报添加失败");
		}
		return this.dr;
	}
	public WebDriver FindCombo(String Comboname){
		SearchCombo(Comboname);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement comboname1 = this.dr.findElement(By.xpath("/html/body/div/div/section[2]/div/div[3]/div/table/tbody/tr[1]/td[3]/a"));
		System.out.println("找到了"+comboname1.getText());
		if(comboname1.getText().contains(Comboname))
		{
			comboname1.click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
		
		}
		else
		{
			log.error("无法找到数据，无法执行编辑案例");
		}
		return this.dr;
		
	}
	public CreateComboPage EditCombo(String notemod,String Combonamemod,String nowpricemod,String activityName,String seasonName,String CmoboListUrl){
		
		/*//修改基本信息
		this.getComboNameEditInput().clear();
		this.getComboNameEditInput().sendKeys(Combonamemod);
		this.getNoteInput().clear();
		this.getNoteInput().sendKeys(notemod);
		this.getNowPriceInput().clear();
		this.getNowPriceInput().sendKeys(nowpricemod);
		this.getEnablemodSiginButton().click();
		this.getSaveButton().click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		//添加活动
		this.getActivity().click();
		this.getActivityInput().sendKeys(activityName);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.getActivityInput().sendKeys(Keys.ENTER);
		//this.getActivitySelect().click();
		this.getSeason().click();
		this.getSeasonInput().sendKeys(seasonName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getSeasonInput().sendKeys(Keys.ENTER);
		this.getSaveactivityButton().click();
		return new CreateComboPage(driver,CmoboListUrl);
	}
}
