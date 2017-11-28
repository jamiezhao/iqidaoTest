package iqidaoTest.frontPageObject;

import org.openqa.selenium.WebDriver;

import iqidaoTest.adminPageObject.BasePage;

public class MytrainsPage extends BasePage{

	public MytrainsPage(WebDriver driver, String url) {
		super(driver);
		this.url = url;
		this.goTo();
	}
	
	
}
