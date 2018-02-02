package iqidaoTest.ExchangeClass;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import iqidaoTest.adminPageObject.BasePage;

public class ExClassPage extends BasePage {

	public ExClassPage(WebDriver driver,String url) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.url=url;
		this.goTo();	
	}
	 Logger log = Logger.getLogger(ExClassList.class);  
	//添加
	By AddLoactor = By.xpath("/html/body/div/div/section[2]/div/header/h3/a");
	By UserNameLoactor = By.xpath("//*[@id='changeClass']/form/div[2]/div[1]/div/span/span[1]/span");
	By UserInputLoactor = By.xpath("/html/body/span/span/span[1]/input");
	By OriginActivityNameLoactor = By.xpath("//*[@id='changeClass']/form/div[2]/div[2]/div/span[1]/span[1]/span");
	By OriginActivityInputLoactor = By.xpath("/html/body/span/span/span[1]/input");
	By OriginSeasonNameLoactor = By.xpath("//*[@id='changeClass']/form/div[2]/div[3]/div/span[1]/span[1]/span");
	By OriginSeasonInputLoactor = By.xpath("/html/body/span/span/span[1]/input");
	By ToActivityNameLoactor = By.xpath("//*[@id='changeClass']/form/div[2]/div[4]/div/span[1]/span[1]/span");
	By ToActivityInputLoactor = By.xpath("/html/body/span/span/span[1]/input");
	By ToSeasonNameLoactor = By.xpath("//*[@id='changeClass']/form/div[2]/div[5]/div/span[1]");
	By ToSeasonInputLoactor = By.xpath("/html/body/span/span/span[1]/input");
	By SaveLoactor = By.id("submit");
	//属性
	public WebElement getAddLoactor() {
		return this.dr.findElement(AddLoactor);
	}
	public WebElement getUserNameLoactor() {
		return this.dr.findElement(UserNameLoactor);
	}
	public WebElement getUserInputLoactor() {
		return this.dr.findElement(UserInputLoactor);
	}
	public WebElement getOriginActivityNameLoactor() {
		return this.dr.findElement(OriginActivityNameLoactor);
	}
	public WebElement getOriginSeasonNameLoactor() {
		return this.dr.findElement(OriginSeasonNameLoactor);
	}
	public WebElement getToActivityNameLoactor() {
		return this.dr.findElement(ToActivityNameLoactor);
	}
	public WebElement getToSeasonNameLoactor() {
		return this.dr.findElement(ToSeasonNameLoactor);
	}
	public WebElement getSaveLoactor() {
		return this.dr.findElement(SaveLoactor);
	}
	public WebElement getOriginActivityInputLoactor() {
		return this.dr.findElement(OriginActivityInputLoactor);
	}
	public WebElement getOriginSeasonInputLoactor() {
		return this.dr.findElement(OriginSeasonInputLoactor);
	}
	public WebElement getToActivityInputLoactor() {
		return this.dr.findElement(ToActivityInputLoactor);
	}
	public WebElement getToSeasonInputLoactor() {
		return this.dr.findElement(ToSeasonInputLoactor);
	}
	//添加换班数据
	public WebDriver AddExClass(String username,String originactivityname,String originseason,String toactivityname,String toseason ){
		this.getAddLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getUserNameLoactor().click();
		this.getUserInputLoactor().sendKeys(username);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getUserInputLoactor().sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getOriginActivityNameLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getOriginActivityInputLoactor().sendKeys(originactivityname);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getOriginActivityInputLoactor().sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getOriginSeasonNameLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getOriginSeasonInputLoactor().sendKeys(originseason);
		this.getOriginSeasonInputLoactor().sendKeys(Keys.ENTER);
		this.getToActivityNameLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getToActivityInputLoactor().sendKeys(toactivityname);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getToActivityInputLoactor().sendKeys(Keys.ENTER);
		this.getToSeasonNameLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getToSeasonInputLoactor().sendKeys(toseason);
		this.getToSeasonInputLoactor().sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getSaveLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.dr;

	}
	
}
