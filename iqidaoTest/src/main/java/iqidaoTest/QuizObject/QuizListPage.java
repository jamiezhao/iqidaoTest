package iqidaoTest.QuizObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import iqidaoTest.adminPageObject.BasePage;

public class QuizListPage extends BasePage {
	private int TableRows = 20;

	public QuizListPage(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
		// TODO Auto-generated constructor stub
	}

	// 试题列表查询界面元素定位
	By QuizNameLoactor = By.xpath("/html/body/div/div/section[2]/div/form/div/div[1]/div[1]/input");
	By TypeLoactor = By.xpath("/html/body/div/div/section[2]/div/form/div/div[2]/div[1]/select");
	By SearchButtonLoactor = By.xpath("/html/body/div/div/section[2]/div/form/div/div[4]/div[3]/div/button");
	// 修改界面元素定位
	By SgfEditLoactor = By.xpath("//*[@id='edit-quiz']/div[2]/div/div[8]/div/div/div[1]/input");
	By nameEditLoactor = By.xpath("//*[@id='edit-quiz']/div[2]/div/div[1]/div[1]/input");
	By SaveLoactor = By.xpath("//*[@id='edit-quiz']/div[3]/button[2]");
	//删除界面元素定位
	By DelLoactor = By.xpath("//*[@id='quiz-list']/tbody/tr[1]/td[11]/a[1]");
	By ConfirmLoactor = By.id("delete-confirm");

	
	// 返回属性
	public WebElement getQuizNameLoactor() {
		return this.dr.findElement(QuizNameLoactor);
	}

	public Select getTypeLoactor() {
		Select type = new Select(this.dr.findElement(TypeLoactor));
		return type;
	}

	public WebElement getSearchButtonLoactor() {
		return this.dr.findElement(SearchButtonLoactor);
	}

	public WebElement getSgfEditLoactor() {
		return this.dr.findElement(SgfEditLoactor);
	}

	public WebElement getnameEditLoactor() {
		return this.dr.findElement(nameEditLoactor);
	}

	public WebElement getSaveLoactor() {
		return this.dr.findElement(SaveLoactor);
	}
	public WebElement getDelLoactor() {
		return this.dr.findElement(DelLoactor);
	}
	public WebElement getConfirmLoactor() {
		return this.dr.findElement(ConfirmLoactor);
	}
	// 查询
	public void SearchQuiz(String quizname, String fenlei) {
		this.getQuizNameLoactor().sendKeys(quizname);
		Select classchoose = this.getTypeLoactor();
		// 试题分类1-死活2-布局3-定式4-中盘5-管子
		classchoose.selectByValue(fenlei);
		this.getSearchButtonLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取当前查询数据-遍历列表
	public boolean getQuizSearchList(String quizname) {
		boolean flag = false;
		try {
			for (int row = 1; row < TableRows + 1; row++) {
				WebElement actualquizName = this.dr
						.findElement(By.xpath("//*[@id='quiz-list']/tbody/tr[" + row + "]/td[3]/a"));
				if (actualquizName.getText().contains(quizname)) {
					flag = true;
					actualquizName.click();
					break;
				}
			}
			if (flag) {
				return flag;
			} else {
				return flag;
			}
		} catch (Exception e) {
			return flag;
		}
	}

	// 查询后判断是否添加成功
	public void Exist(String quizname, String fenlei) {
		SearchQuiz(quizname, fenlei);
		// 遍历数据表，检查返回值
		if (getQuizSearchList(quizname)) {
			System.out.println("数据存在，添加成功");
		} else {
			System.out.println("数据不存在，添加失败");
		}
	}

	// 修改数据
	public QuizListPage ModifyQuiz(String quizname, String fenlei, String newfile, String newname, String PageUrl) {
		SearchQuiz(quizname, fenlei);
		getQuizSearchList(quizname);
		this.getnameEditLoactor().clear();
		this.getnameEditLoactor().sendKeys(newname);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getSgfEditLoactor().sendKeys(newfile);
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
		return new QuizListPage(this.dr, PageUrl);
	}
	//删除数据
	public QuizListPage DelQuiz(String quizname, String fenlei, String newfile, String newname, String PageUrl) {
		this.getDelLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getConfirmLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new QuizListPage(this.dr, PageUrl);
	
	}
}
