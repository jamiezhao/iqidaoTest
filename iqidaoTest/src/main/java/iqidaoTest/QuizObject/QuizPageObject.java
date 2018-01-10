package iqidaoTest.QuizObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import iqidaoTest.adminPageObject.BasePage;

public class QuizPageObject extends BasePage{
	public QuizPageObject(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
		// TODO Auto-generated constructor stub
	}
	// 查询界面元素定位
		By NameSearchLoactor = By.xpath("/html/body/div/div/section[2]/div/form/div/div[1]/div/input");
		By OriginSearchLoactor = By.name("paperSrcId");
		By SearchButtonLoactor = By.xpath("/html/body/div/div/section[2]/div/form/div/div[6]/div/div/input");
		By QuizDelLoactor = By.xpath("//*[@id='paperQuiz']/tbody/tr[1]/td[14]");
		By ConfirmButtonLoactor = By.id("delete-confirm");
		// 返回属性
		public WebElement getNameSearchLoactor() {
			return this.dr.findElement(NameSearchLoactor);
		}

		public Select getOriginSearchLoactor() {
			Select OriginSearch = new Select(this.dr.findElement(OriginSearchLoactor));
			return OriginSearch;
		}
		public WebElement getSearchButtonLoactor() {
			return this.dr.findElement(SearchButtonLoactor);
			}
		public WebElement getQuizDelLoactor() {
			return this.dr.findElement(QuizDelLoactor);
			}
		public WebElement getConfirmButtonLoactor() {
			return this.dr.findElement(ConfirmButtonLoactor);
			}
}
