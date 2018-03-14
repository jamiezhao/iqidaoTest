package iqidaoTest.ExchangeClass;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import iqidaoTest.adminPageObject.BasePage;

public class ExClassList extends BasePage {

	public ExClassList(WebDriver driver,String url) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.url=url;
		this.goTo();
	}
	private int TableRows=20;
	 Logger log = Logger.getLogger(ExClassList.class);  
	//查询
		By UserSearchLoactor = By.xpath("/html/body/div/div/section[2]/div/div[1]/form/div[1]/div/span/span[1]/span");
		By UserInputLoactor = By.xpath("/html/body/span/span/span[1]/input");

		By OriginActivityNameLoactor = By.xpath("/html/body/div/div/section[2]/div/div[1]/form/div[3]/div/span/span[1]/span");
		By OriginActivityInputLoactor = By.xpath("/html/body/span/span/span[1]/input");

		By ToActivityNameLoactor = By.xpath("/html/body/div/div/section[2]/div/div[1]/form/div[4]/div/span/span[1]/span");
		By ToActivityInputLoactor = By.xpath("/html/body/span/span/span[1]/input");
		By SearchButtonLoactor = By.xpath("/html/body/div/div/section[2]/div/div[1]/form/div[5]/div/div/button");
		//属性
		public WebElement getUserInputLoactor() {
			return this.dr.findElement(UserInputLoactor);
		}
		public WebElement getToActivityInputLoactor() {
			return this.dr.findElement(ToActivityInputLoactor);
		}
		public WebElement getOriginActivityInputLoactor() {
			return this.dr.findElement(OriginActivityInputLoactor);
		}
		public WebElement getUserSearchLoactor() {
			return this.dr.findElement(UserSearchLoactor);
		}
		public WebElement getOriginActivityNameLoactor() {
			return this.dr.findElement(OriginActivityNameLoactor);
		}
		public WebElement getSearchButtonLoactor() {
			return this.dr.findElement(SearchButtonLoactor);
		}
		public WebElement getToActivityNameLoactor() {
			return this.dr.findElement(ToActivityNameLoactor);
		}
		public void SearchList(String username,String originactivityname,String toactivityname){
			this.getUserSearchLoactor().click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getUserInputLoactor().sendKeys(username);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getUserInputLoactor().sendKeys(Keys.ENTER);
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
			this.getSearchButtonLoactor().click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//判断是否添加成功
		public boolean ElementExist(String username,String originactivityname,String toactivityname){
			//先执行查询功能
			SearchList(username,originactivityname,toactivityname);
			//检查数据是否存在
			boolean flag = false; 
			try{
				 this.dr.findElement(By.xpath("//*[@class='x-scroll']/table/tbody/tr[1]/td[1]"));
			     log.info("找到记录，换班记录添加成功");  
				 return true;
				}catch(org.openqa.selenium.NoSuchElementException ex){
			     log.warn("找不到记录，换班记录添加失败");  
				 return false;
			 }
			}
		}
