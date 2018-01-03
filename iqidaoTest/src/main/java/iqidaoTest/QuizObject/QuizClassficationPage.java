package iqidaoTest.QuizObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import iqidaoTest.adminPageObject.BasePage;

public class QuizClassficationPage extends BasePage {

	public QuizClassficationPage(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
		// TODO Auto-generated constructor stub
	}

	// 新增界面元素定位
	By AddButtonLoactor = By.xpath("/html/body/div/div/section[2]/div/form/div/div[2]/div/div/a");
	By ClassficationNameLoactor = By.xpath("//*[@id='add-classes']/div[2]/div[1]/div/input");
	By CodeLoactor = By.name("code");
	By SaveButtonLoactor = By.xpath("//*[@id='add-classes']/div[3]/button[2]");

	// 返回属性
	public WebElement getAddButtonLoactor() {
		return this.dr.findElement(AddButtonLoactor);
	}

	public WebElement getClassficationNameLoactor() {
		return this.dr.findElement(ClassficationNameLoactor);
	}

	public WebElement getCodeLoactor() {
		return this.dr.findElement(CodeLoactor);
	}

	public WebElement getSaveButtonLoactor() {
		return this.dr.findElement(SaveButtonLoactor);
	}

	public QuizClassList AddQuizClass(String code, String quizclassname, String PageUrl) {
		// 添加试题分类
		this.getAddButtonLoactor().click();
		this.getClassficationNameLoactor().sendKeys(quizclassname);
		this.getCodeLoactor().sendKeys(code);
		this.getSaveButtonLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new QuizClassList(this.dr, PageUrl);
	}
}
