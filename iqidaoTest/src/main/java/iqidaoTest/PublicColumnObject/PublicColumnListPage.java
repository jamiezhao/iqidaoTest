package iqidaoTest.PublicColumnObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import iqidaoTest.adminPageObject.BasePage;

public class PublicColumnListPage extends BasePage {
	private int TableRows = 20;

	public PublicColumnListPage(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
	}

	// 查询界面元素定位
	By SerchtitleLoactor = By.xpath("/html/body/div/div/section[2]/div/div[1]/form/div/div[1]/div/input");
	By SearchclassLoactor = By.cssSelector(
			"body > div > div > section.content.clearfix > div > div.panel-body > form > div > div:nth-child(3) > div > select");
	By SearchbuttonLoactor = By.xpath("/html/body/div/div/section[2]/div/div[1]/form/div/div[4]/div/div/button");
	By DeleteButtonLoactor = By.xpath("/html/body/div/div/section[2]/div/div[1]/div/div[1]/table/tbody/tr[1]/td[11]/a");
	// 修改界面元素定位
	By ModtitleLoactor = By.name("title");
	By ModwriterLoactor = By.name("author");
	By ModtroduLoactor = By
			.xpath("/html/body/div[1]/div/section[2]/section/div/form/div/div[1]/div/div[7]/div/div/div[3]/div");
	By ModcontentLoactor = By
			.xpath("/html/body/div[1]/div/section[2]/section/div/form/div/div[3]/div[1]/div/div[3]/div");
	By ModSaveLoactor = By.xpath("/html/body/div[1]/div/section[2]/section/div/form/div/div[3]/div[2]/button[1]");
	// 删除界面确认删除
	By ConfirmButtonLoactor = By.id("delete-confirm");

	public WebElement getSerchtitleLoactor() {
		return this.dr.findElement(SerchtitleLoactor);
	}

	public Select getSearchclassLoactor() {
		Select SearchClassSelect = new Select(this.dr.findElement(SearchclassLoactor));
		return SearchClassSelect;
	}

	public WebElement getSearchbuttonLoactor() {
		return this.dr.findElement(SearchbuttonLoactor);
	}

	public WebElement getDeleteButtonLoactor() {
		return this.dr.findElement(DeleteButtonLoactor);
	}

	public WebElement getConfirmButtonLoactor() {
		return this.dr.findElement(ConfirmButtonLoactor);
	}

	public WebElement getModtitleLoactor() {
		return this.dr.findElement(ModtitleLoactor);
	}

	public WebElement getModwriterLoactor() {
		return this.dr.findElement(ModwriterLoactor);
	}

	public WebElement getModtroduLoactor() {
		return this.dr.findElement(ModtroduLoactor);
	}

	public WebElement getModcontentLoactor() {
		return this.dr.findElement(ModcontentLoactor);
	}

	public WebElement getModSaveLoactor() {
		return this.dr.findElement(ModSaveLoactor);
	}

	// 查询方法
	public String Search(String name) {
		try{
		this.getSerchtitleLoactor().sendKeys(name);
		this.getSearchbuttonLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.url;
	}catch (Exception e){
		return "not exist";
	}
	}
	// 获取当前查询数据-遍历列表
	public String getNewsSearchList(String name) {
		boolean flag = false;
		for (int row = 1; row < TableRows + 1; row++) {
			WebElement actualPaperName = this.dr.findElement(
					By.xpath("/html/body/div/div/section[2]/div/div[1]/div/div[1]/table/tbody/tr[" + row + "]/td[2]"));
			if (actualPaperName.getText().contains(name)) {
				flag = true;
				actualPaperName.click();
				break;
			}
		}
		if (flag) {
			return this.url;
		} else {
			return "can not find the activity";
		}
	}
	public boolean Judgeif(String name){
		boolean flag = false; 
		Search(name);
		try{
		WebElement actualnewsname = this.dr.findElement(By.cssSelector("body > div > div > section.content.clearfix > div > div.panel-body > div > div.x-scroll > table > tbody > tr > td:nth-child(2) > a"));
		//可获取数据，已存在
		if(actualnewsname.getText().contains(name)) {
			flag = false;
			System.out.println("专栏信息已存在");
			return flag;
	}
		//获取到名称不一致，不存在
		else{
			System.out.println("专栏信息不存在");
		return true;
		}
		}
		//获取不到元素，表示不存在
		catch(Exception e){
			return true;
		}
}
	public boolean ElementExist(String name) {
		Search(name);
		try{
		 this.dr.findElement(By.xpath("/html/body/div/div/section[2]/div/div[1]/div/div[1]/table/tbody/tr[1]/td[1]"));
		 return true;
		}catch(org.openqa.selenium.NoSuchElementException ex){
		 return false;
	 }
	}
	// 修改专栏信息
	public WebDriver ModColoumn(String modnewname) {
		this.getModtitleLoactor().clear();
		this.getModwriterLoactor().clear();
		this.getModtitleLoactor().sendKeys(modnewname);
		this.getModwriterLoactor().sendKeys("zjy_mod");
		this.getModtroduLoactor().sendKeys("介绍信息");
		this.getModcontentLoactor().sendKeys("主要内容");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getModSaveLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.dr;
	}

	// 删除同名信息
	public void DEL( String name) throws InterruptedException {
		Search(name);
		this.getDeleteButtonLoactor().click();
		Thread.sleep(2000);
		this.getConfirmButtonLoactor().click();
		System.out.println("删除成功");
	}
}
