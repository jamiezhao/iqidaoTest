package iqidaoTest.frontPageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import iqidaoTest.adminPageObject.BasePage;

public class ActivitysPage extends BasePage{
	
	public ActivitysPage (WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
	}
	
	By nextPageLocator = By.linkText("下一页");
	
	public WebElement getNextPageButton() {
		return this.dr.findElement(nextPageLocator);
	}
	
	public boolean gotoActivityDetailPageByName(String activityName) {
		boolean flag = false;
		WebElement ul = this.dr.findElement(By.className("item-list"));
		List<WebElement> lis = ul.findElements(By.className("training-item")); 
		for(WebElement li:lis) {
			if(li.findElement(By.className("title")).getText().contains(activityName)) {
				flag = true;
				JavascriptExecutor js = (JavascriptExecutor) this.dr;
			    js.executeScript("arguments[0].scrollIntoView(true);", li.findElement(By.className("title")));
				li.findElement(By.className("thumb")).click();
				return flag;
			}
		}
		return flag;
	}
	
	public String goNextPage() {
		String className = this.getNextPageButton().getAttribute("class");
		System.out.println("this is className " + className);
		if("disabled".equals(className)) {
			return "disabled";
		}else {
			this.getNextPageButton().click();
			String currentUrl = this.dr.getCurrentUrl();
			return currentUrl;
		}
		
	}
	
	
	
	
}
