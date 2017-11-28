package iqidaoTest.frontPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import iqidaoTest.adminPageObject.BasePage;

public class ActivityDetailPage extends BasePage{
	public ActivityDetailPage(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
	}
	
	By buyButtonLocator = By.cssSelector(".pay-btn");
	By couponLocaotr = By.className("price-box");
	By submitButtonLocator = By.xpath("html/body/div[7]/div/div/div[3]/div[1]/button");
	
	public WebElement getBuyButton() {
		return this.dr.findElement(buyButtonLocator);
	}

	public WebElement getCouponField() {
		return this.dr.findElement(couponLocaotr);
	}
	
	public WebElement getSubmitButton() {
		return this.dr.findElement(submitButtonLocator);
	}
	
	public MytrainsPage buyActivityByCoupon(String activityName, String redirectPageUrl) {
		this.getBuyButton().click();
		new WebDriverWait(this.dr, 10).until(ExpectedConditions.elementToBeClickable(couponLocaotr));
		this.getCouponField().click();
		this.getSubmitButton().click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new MytrainsPage(this.dr, redirectPageUrl);
	}
	
}
