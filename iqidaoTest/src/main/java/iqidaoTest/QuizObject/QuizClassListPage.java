package iqidaoTest.QuizObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import iqidaoTest.adminPageObject.BasePage;

public class QuizClassListPage extends BasePage {
	private int TableRows = 20;

	public QuizClassListPage(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
		// TODO Auto-generated constructor stub
	}

	// 新增分类界面元素定位
	By NameSEARCHLoactor = By.xpath("/html/body/div/div/section[2]/div/form/div/div[1]/div/input");
	By SearchButtonLoactor = By.xpath("/html/body/div/div/section[2]/div/form/div/div[2]/div/div/input");
	// 新增试题界面元素定位
	By QuizAddLoactor = By.xpath("/html/body/div/div/section[2]/div/header/h3/a");
	By QuizTypeLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[1]/div[1]/select");
	By DergeeLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[2]/div[1]/input");
	By DaYuLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[1]/div[2]/select");
	By QuizClassLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[1]/div[2]/select");
	By QuizGoallLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[2]/div[3]/select");
	By TypeLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[1]/div[3]/select");
	By ResultLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[2]/div[3]/select");
	By WhiteBlackLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[2]/div[4]/select");
	By ChooseFileLoactor = By.id("SWFUpload_1");
	By SgfLoactor = By.xpath("//*[@id='addquiz']/form/div[2]/div/div[3]/div/div/div[1]/input");
	By SaveButtonLoactor = By.xpath("//*[@id='addquiz']/form/div[3]/button[2]");
	// 试题列表菜单
	By QuizMenuLoactor = By.xpath("/html/body/div/aside/section/ul/li[5]/ul/li[3]/a/span");

	// 返回属性
	public WebElement getNameSEARCHLoactor() {
		return this.dr.findElement(NameSEARCHLoactor);
	}

	public WebElement getSearchButtonLoactor() {
		return this.dr.findElement(SearchButtonLoactor);
	}
	public Select getDaYuLoactor() {
		Select dayu = new Select(this.dr.findElement(DaYuLoactor));
		return dayu;
	}
	public WebElement getQuizAddLoactor() {
		return this.dr.findElement(QuizAddLoactor);
	}

	public Select getQuizTypeLoactor() {
		Select classes = new Select(this.dr.findElement(QuizTypeLoactor));
		return classes;
	}

	public WebElement getDergeeLoactor() {
		return this.dr.findElement(DergeeLoactor);
	}

	public Select getQuizClassLoactor() {
		Select ClassQuiz = new Select(this.dr.findElement(QuizClassLoactor));
		return ClassQuiz;
	}

	public Select getQuizGoallLoactor() {
		Select Quizgoal = new Select(this.dr.findElement(QuizGoallLoactor));
		return Quizgoal;
	}

	public Select getTypeLoactor() {
		Select Type = new Select(this.dr.findElement(TypeLoactor));
		return Type;
	}

	public Select getResultLoactor() {
		Select Result = new Select(this.dr.findElement(ResultLoactor));
		return Result;
	}

	public Select getWhiteBlackLoactor() {
		Select First = new Select(this.dr.findElement(WhiteBlackLoactor));
		return First;
	}

	public WebElement getChooseFileLoactor() {
		return this.dr.findElement(ChooseFileLoactor);
	}

	public WebElement getSgfLoactor() {
		return this.dr.findElement(SgfLoactor);
	}

	public WebElement getSaveButtonLoactor() {
		return this.dr.findElement(SaveButtonLoactor);
	}

	public WebElement getQuizMenuLoactor() {
		return this.dr.findElement(QuizMenuLoactor);
	}

	public void SearchQuizClass(String quizclassname) {
		// 查询试题分类
		this.getNameSEARCHLoactor().sendKeys(quizclassname);
		this.getSearchButtonLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void WeblementExist(String quizclassname) {
		// 判断是否存在
		// 首先执行查询方法
		SearchQuizClass(quizclassname);
		// 遍历数据表，检查返回值
		if (getQuizClass(quizclassname)) {
			System.out.println("数据存在，添加成功");
		} else {
			System.out.println("数据不存在，添加失败");
		}

	}

	// 获取当前查询数据-遍历列表
	public boolean getQuizClass(String quizclassname) {
		boolean flag = false;
		for (int row = 1; row < TableRows + 1; row++) {
			//WebElement actualPaperName = this.dr.findElement(By.xpath("//*[@id='classes-list']/tbody/tr[" + row + "]/td[2]/a"));
			WebElement actualPaperName = this.dr.findElement(By.xpath("/html/body/div/div/section[2]/div/div/div[1]/div[1]/table/tbody/tr[" + row + "]/td[2]/a"));
			if (actualPaperName.getText().contains(quizclassname)) {
				flag = true;
				actualPaperName.click();
				break;
			}
		}
		if (flag) {
			return flag;
		} else {
			return flag;
		}
	}

	// 获取当前查询数据-遍历列表
	public boolean getQuizClass1(String code) {
		boolean flag = false;
		try {
			for (int row = 1; row < TableRows + 1; row++) {
				WebElement actualcodeName = this.dr
						.findElement(By.xpath("/html/body/div/div/section[2]/div/div/div[1]/div[1]/table/tbody/tr[" + row + "]/td[3]"));
				if (actualcodeName.getText().contains(code)) {
					flag = true;
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

	// 添加试题
	public QuizListPage QuizAdd(String code, String duanwei, String fenlei, String goal, String tixing, String first1,
			String filename, String result, String PageUrl) {
		for (int row = 1; row < TableRows + 1; row++) {
			WebElement actualcodeName = this.dr
					.findElement(By.xpath("/html/body/div/div/section[2]/div/div/div[1]/div[1]/table/tbody/tr[" + row + "]/td[3]"));
			if (actualcodeName.getText().contains(code)) {
				String actualclassName = this.dr
						.findElement(By.xpath("/html/body/div/div/section[2]/div/div/div[1]/div[1]/table/tbody/tr[" + row + "]/td[2]")).getText();
				// 通过查询出试题分类名称，进入新增试题菜单，新增试题
				// 切换菜单
				this.getQuizMenuLoactor().click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 点击新增按钮，填写信息
				this.getQuizAddLoactor().click();
				// 将查询到的数据，作为选择项选择
				Select classes = this.getQuizTypeLoactor();
				classes.selectByVisibleText(actualclassName);
				this.getDergeeLoactor().sendKeys(duanwei);
				Select dayu = this.getDaYuLoactor();
				// 大域1-死活2-布局3-定式4-中盘5-管子
				dayu.selectByValue(fenlei);
				Select classchoose = this.getQuizClassLoactor();
				// 试题分类1-死活2-布局3-定式4-中盘5-管子
				classchoose.selectByValue(fenlei);
				Select Quizgoal = this.getQuizGoallLoactor();
				// 计算目标1-计算2-判断3-攻防4-定式5-棋型6-价值7-大局
				Quizgoal.selectByValue(goal);
				Select Quiztype = this.getTypeLoactor();
				// 试题类型1-摆图2-自动应答4-选择题
				Quiztype.selectByValue(tixing);
				Select quizresult = this.getResultLoactor();
				// 结果1-不需选择2-净劫2-打劫
				quizresult.selectByValue(result);
				Select first = this.getWhiteBlackLoactor();
				// 黑先白先1-黑先2-白先
				first.selectByValue(first1);
				this.getSgfLoactor().sendKeys(filename);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.getSaveButtonLoactor().click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		return new QuizListPage(this.dr, PageUrl);
	}

}
