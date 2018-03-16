package iqidaoTest.CoursePaper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import iqidaoTest.adminPageObject.BasePage;

public class PaperListPage extends BasePage {
	private int TableRows = 20;
	public PaperListPage(WebDriver driver, String url) {
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
	//查询方法
	public  WebDriver Search(String papername,String orginselect) {
		this.getNameSearchLoactor().sendKeys(papername);
		Select OriginSearch=this.getOriginSearchLoactor();
		//0-系统试卷；1专项考试；2-专项预习；3-专项课后；4-错题本；5推荐试卷
		OriginSearch.selectByValue(orginselect);
		this.getSearchButtonLoactor().click();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.dr;
	}	
	//获取当前查询数据-遍历列表
	public String getPaperSearchList(String papername) {
		boolean flag = false; 
		for(int row = 1; row < TableRows + 1; row++) {
			WebElement actualPaperName = this.dr.findElement(By.xpath("//*[@id='quiz-list']/tbody/tr[" + row + "]/td[2]/a"));
			if(actualPaperName.getText().contains(papername)) {
				flag = true;
				actualPaperName.click();
				break;	
			}
		}
		if(flag) {
			return this.url;
		}else {
			return "can not find the activity";
		}	
	}
	//删除功能检查
	public String Delquiz() {
		this.getQuizDelLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getConfirmButtonLoactor().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.url;
	}
	//判断是否添加成功
	public boolean ElementExist() {
		try{
		 this.dr.findElement(By.xpath("//*[@id='paperQuiz']/tbody/tr[1]/td[2]"));
		 return true;
		}catch(org.openqa.selenium.NoSuchElementException ex){
		 return false;
	 }
	}
}

