package iqidaoTest.adminPageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateSeasonPage extends BasePage{
	public CreateSeasonPage(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
	}
	By activitySubmitButtonLocator = By.xpath("html/body/div[1]/div/section[2]/section[1]/form/footer/button");
	By descriptionLocator = By.xpath("html/body/div[1]/div/section[2]/section[1]/form/div/div[5]/div/div/div[2]/div");
	
	By addSeasonButtonLocator = By.xpath("html/body/div[1]/div/section[2]/section[2]/header/a");
	By seasonNameLocator = By.xpath(".//*[@id='add-match']/div[2]/div[1]/input");
	By seasonPriceLocator = By.xpath(".//*[@id=\"add-match\"]/div[2]/div[2]/input");
	By seasonStartTimeLocator = By.xpath(".//*[@id=\"add-match\"]/div[2]/div[3]/input");
	By seasonEndTimeLocator = By.xpath(".//*[@id=\"add-match\"]/div[2]/div[4]/input");
	By seasonSubmitButtonLocator = By.xpath(".//*[@id=\"add-match\"]/div[3]/button[1]");
	
	By addItemButtonLocator = By.linkText("添加条目");
	By itemNameLocator = By.xpath(".//*[@id='add-course']/div[2]/div/div[1]/div/input");
	By itemStartTimeLocator = By.xpath(".//*[@id=\"add-course\"]/div[2]/div/div[2]/div/input");
	By itemTypeLocator = By.id("type");
	By courseSyllabusLocator = By.id("upload_doc");
	By itemSubmitButtonLocator = By.xpath(".//*[@id=\"add-course\"]/div[3]/button[2]");
	
	public WebElement getAddSeasonButton() {
		return this.dr.findElement(addSeasonButtonLocator);
	}
	
	public WebElement getSeasonNameTextField() {
		return this.dr.findElement(seasonNameLocator);
	}
	
	public WebElement getSeasonPriceTextField() {
		return this.dr.findElement(seasonPriceLocator);
	}
	
	public WebElement getSeasonStartTimeTextField() {
		return this.dr.findElement(seasonStartTimeLocator);
	}
	
	public WebElement getSeasonEndTimeTextField() {
		return this.dr.findElement(seasonEndTimeLocator);
	}
	
	public WebElement getSeasonSubmitButton() {
		return this.dr.findElement(seasonSubmitButtonLocator);
	}
	
	public WebElement getAddItemButton() {
		JavascriptExecutor js = (JavascriptExecutor) this.dr;
	    js.executeScript("arguments[0].scrollIntoView(true);", this.dr.findElement(addItemButtonLocator));
		return this.dr.findElement(addItemButtonLocator);
	}
	
	public WebElement getItemNameTextField() {
		return this.dr.findElement(itemNameLocator);
	}
	
	public WebElement getItemStartTimeTextField() {
		return this.dr.findElement(itemStartTimeLocator);
	}
	
	public WebElement getItemTypeSelect() {
		return this.dr.findElement(itemTypeLocator);
	}
	
	public WebElement getcourseSyllabusTextField() {
		return this.dr.findElement(courseSyllabusLocator);
	}
	
	public WebElement getItemSubmitButton() {
		return this.dr.findElement(itemSubmitButtonLocator);
	}
	
	public boolean findItemByName(String itemName) {
		boolean flag = false;
		WebElement table = this.dr.findElement(By.id("special-list"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for(WebElement col:cols) {
				if(col.getText().contains(itemName)) {
					flag = true;
					break;
				}
			}
			
		}
		return flag; 
	}
	
	public boolean getFirstItem(String itemName) {
		boolean flag = false;
		WebElement item = this.dr.findElement(By.xpath("html/body/div[1]/div/section[2]/section[3]/div/div[2]/table/tbody/tr/td[3]/a"));
		if(item.getText().contains(itemName)) {
			flag = true;
		}
		return flag;
	}
	
	public void addActivitySeason(String seasonName, String seasonPrice, String seasonStartTime, String seasonEndTime) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//页面滚动定位到添加赛季的按钮
		JavascriptExecutor js = (JavascriptExecutor) this.dr;
//	    js.executeScript("arguments[0].scrollIntoView(true);", this.dr.findElement(activitySubmitButtonLocator));
	    js.executeScript("document.getElementsByTagName('html')[0].scrollTop = 700");
	    try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.getAddSeasonButton().click();
		this.getSeasonNameTextField().sendKeys(seasonName);
		this.getSeasonPriceTextField().sendKeys(seasonPrice);
		this.getSeasonStartTimeTextField().clear();
		this.getSeasonStartTimeTextField().sendKeys(seasonStartTime);
		this.getSeasonEndTimeTextField().clear();
		this.getSeasonEndTimeTextField().sendKeys(seasonEndTime);
		this.getSeasonSubmitButton().click();
	}
	
	
	public void addCourceItem(String itemName, String itemStartTime, String courseSyllabus) {
		//页面滚动定位到添加条目的按钮
		JavascriptExecutor js = (JavascriptExecutor) this.dr;
	    js.executeScript("arguments[0].scrollIntoView(true);", this.dr.findElement(By.linkText("添加条目")));
	    this.getAddItemButton().click();
	    this.getItemNameTextField().sendKeys(itemName);
	    this.getItemStartTimeTextField().clear();
	    this.getItemStartTimeTextField().sendKeys(itemStartTime);
	    Select select = new Select(this.getItemTypeSelect());
	    select.selectByValue("0");
	    this.getcourseSyllabusTextField().sendKeys(courseSyllabus);
	    this.getItemSubmitButton().click();
	}
	
	public void addExamItem(String itemName, String itemStartTime) {
		//页面滚动定位到添加条目的按钮
		JavascriptExecutor js = (JavascriptExecutor) this.dr;
	    js.executeScript("arguments[0].scrollIntoView(true);", this.dr.findElement(By.linkText("添加条目")));
	    this.getItemNameTextField().sendKeys(itemName);
	    this.getItemStartTimeTextField().clear();
	    this.getItemStartTimeTextField().sendKeys(itemStartTime);
		Select select = new Select(this.getItemTypeSelect());
	    select.selectByValue("1");
	    this.getItemSubmitButton().click();
	}
	
	
}
