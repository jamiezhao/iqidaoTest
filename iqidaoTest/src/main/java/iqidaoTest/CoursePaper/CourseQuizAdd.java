package iqidaoTest.CoursePaper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import iqidaoTest.adminPageObject.BasePage;

public class CourseQuizAdd extends BasePage {

	public CourseQuizAdd(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// 新增界面定位元素
	By QuizAddLoactor = By.xpath("//*[@id='quiz-query']/div/div[9]/a[1]");
	// By QuizSearchLoactor = By.linkText("查询");
	By NameSearchLoactor = By.xpath("//*[@id='query_paperQuiz']/div[1]/div/div/input");	
	By DegreeSearchLoactor = By.name("duanLower");
	By DegreeSearch1Loactor = By.name("duanUpper");
	By ClassSearchLoactor = By.xpath("//*[@id='query_paperQuiz']/div[6]/div/div/select");
	By QuizSearchLoactor = By.xpath("//*[@id='query_paperQuiz']/div[8]/a");
	By AllSelectLoactor = By.xpath("//*[@id='paperQuiz-list']/thead/tr/th[1]/div/label/span/span");
	By QuizSaveLoactor = By.id("paperQuiz-submit");
	By PassScoreLoactor = By.name("passScore");
	By timelimmitLoactor = By.name("duration");
	By ConfigSaveLoactor = By.xpath("//*[@id='quiz-query']/div/div[9]/button");		
	// 返回属性
	public WebElement getQuizAddLoactor() {
		return this.dr.findElement(QuizAddLoactor);
	}

	public WebElement getQuizSearchLoactor() {
		return this.dr.findElement(QuizSearchLoactor);

	}

	public WebElement getAllSelectLoactor() {
		return this.dr.findElement(AllSelectLoactor);

	}

	public WebElement getQuizSaveLoactor() {
		return this.dr.findElement(QuizSaveLoactor);
	}
	public WebElement getPassScoreLoactor() {
		return this.dr.findElement(PassScoreLoactor);
	}
	public WebElement gettimelimmitLoactor() {
		return this.dr.findElement(timelimmitLoactor);
	}
	public WebElement getConfigSaveLoactor() {
		return this.dr.findElement(ConfigSaveLoactor);
	}
	public WebElement getNameSearchLoactor() {
		return this.dr.findElement(NameSearchLoactor);
	}
	public WebElement getDegreeSearchLoactor() {
		return this.dr.findElement(DegreeSearchLoactor);
	}
	public WebElement getDegreeSearch1Loactor() {
		return this.dr.findElement(DegreeSearch1Loactor);
	}
	public Select getClassSearchLoactor() {
		Select Class=new Select(this.dr.findElement(ClassSearchLoactor));
		return Class;
	}
	// 添加试题
	public WebDriver AddCourseQuiz() {
		this.getQuizAddLoactor().click();
		//试题选择条件
		/*this.getNameSearchLoactor().sendKeys();
		this.getDegreeSearchLoactor().sendKeys();
		this.getDegreeSearch1Loactor().sendKeys();
		Select type=this.getClassSearchLoactor();
		//1-摆图提2-选择题3-自动应答提
		type.selectByValue("1");*/
		this.getQuizSearchLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getAllSelectLoactor().click();
		// 滚动条下拉
		JavascriptExecutor js = (JavascriptExecutor) this.dr;
		//移动至定位元素
        js.executeScript("arguments[0].scrollIntoView(true)", dr.findElement(QuizSaveLoactor));
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //点击保存试题
		this.getQuizSaveLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//试卷分数时间限制设置
		this.getPassScoreLoactor().clear();;
		this.getPassScoreLoactor().sendKeys("60");
		this.gettimelimmitLoactor().clear();
		this.gettimelimmitLoactor().sendKeys("60");
		//点击保存设置
		this.getConfigSaveLoactor().click();
		return this.dr;
	}
}
