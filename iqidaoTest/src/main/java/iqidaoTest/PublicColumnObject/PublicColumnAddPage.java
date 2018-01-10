package iqidaoTest.PublicColumnObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import iqidaoTest.adminPageObject.BasePage;

public class PublicColumnAddPage extends BasePage {
	public PublicColumnAddPage(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
	}

	// 添加界面元素定位
	By publicaAddLoactor = By.linkText("添加新闻");
	By NewtitleLoactor = By.xpath("//*[@id='addarticle']/form/div[2]/div/div[1]/div/div[1]/div/input");
	By NewwriterLoactor = By.xpath("//*[@id='addarticle']/form/div[2]/div/div[1]/div/div[2]/div/input");
	By NewclassLoactor = By.xpath("//*[@id='addarticle']/form/div[2]/div/div[1]/div/div[3]/div/select");
	By NewtimeLoactor = By.xpath("//*[@id='addarticle']/form/div[2]/div/div[1]/div/div[4]/div/input");
	By NeworderLoactor = By.name("rank");
	By NewviewedLoactor = By.name("viewCount");
	By NewtroduLoactor = By.xpath("//*[@id='addarticle']/form/div[2]/div/div[1]/div/div[7]/div/div/div[2]/div");
	By NewcontentLoactor = By.xpath("//*[@id='addarticle']/form/div[2]/div/div[3]/div/div/div[3]/div");
	// 选择图片
	By NewImageLoactor = By.name("img");
	By NewSaveLoactor = By.xpath("//*[@id='addarticle']/form/div[3]/button[2]");
	// By NewSaveLoactor = By.className("btn-primary");
	By NewPageLoactor = By.xpath("//*[@id='addarticle']/form/div[2]/div");

	// 元素定位方法，返回值
	public WebElement getpublicaAddLoactor() {
		return this.dr.findElement(publicaAddLoactor);
	}

	public WebElement getNewtitleLoactor() {
		return this.dr.findElement(NewtitleLoactor);
	}

	public WebElement getNewwriterLoactor() {
		return this.dr.findElement(NewwriterLoactor);
	}

	public Select getNewclassLoactor() {
		Select NewClassSelect = new Select(this.dr.findElement(NewclassLoactor));
		return NewClassSelect;
	}

	public WebElement getNewtimeLoactor() {
		return this.dr.findElement(NewtimeLoactor);
	}

	public WebElement getNeworderLoactor() {
		return this.dr.findElement(NeworderLoactor);
	}

	public WebElement getNewviewedLoactor() {
		return this.dr.findElement(NewviewedLoactor);
	}

	public WebElement getNewtroduLoactor() {
		return this.dr.findElement(NewtroduLoactor);
	}

	public WebElement getNewcontentLoactor() {
		return this.dr.findElement(NewcontentLoactor);
	}

	public WebElement getNewImageLoactor() {
		return this.dr.findElement(NewImageLoactor);
	}

	public WebElement getNewSaveLoactor() {
		return this.dr.findElement(NewSaveLoactor);
	}

	public WebElement getNewPageLoactor() {
		return this.dr.findElement(NewPageLoactor);
	}

	// 添加专栏信息
	public void AddColoumn(String newname, String newsclass,String fileurl) {
		// 点击添加
		this.getpublicaAddLoactor().click();
		// 保存按钮首次点击异常，暂时解决方法点击两次
		JavascriptExecutor js1 = (JavascriptExecutor) this.dr;
		js1.executeScript("arguments[0].scrollIntoView(true)",dr.findElement(By.xpath("//*[@id='addarticle']/form/div[3]/button[2]")));
		dr.findElement(By.xpath("//*[@id='addarticle']/form/div[3]/button[2]")).click();
		// 输入信息
		this.getNewtitleLoactor().sendKeys(newname);
		this.getNewwriterLoactor().sendKeys("zjy");
		// 选择分类-官方资讯/名师指导/棋道精选/围棋百科/赛事快报
		Select NewClassSelect = this.getNewclassLoactor();
		NewClassSelect.selectByValue(newsclass);
		this.getNewtimeLoactor().click();
		this.getNeworderLoactor().sendKeys("1");
		this.getNewviewedLoactor().sendKeys("2");
		this.getNewImageLoactor().sendKeys(fileurl);
		this.getNewtroduLoactor().sendKeys("3");
		// 滚动条下拉
		JavascriptExecutor js = (JavascriptExecutor) this.dr;
		js.executeScript("scrollTo(0,10000)");
		this.getNewcontentLoactor().sendKeys("4");
		// roll down and keep the element to the center of browser
		// 移动至定位元素
		js1.executeScript("arguments[0].scrollIntoView(true)",dr.findElement(By.xpath("//*[@id='addarticle']/form/div[3]/button[2]")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dr.findElement(By.xpath("//*[@id='addarticle']/form/div[3]/button[2]")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.getNewSaveLoactor().click();
	}
}
