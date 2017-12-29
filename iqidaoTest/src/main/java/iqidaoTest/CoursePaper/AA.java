package iqidaoTest.CoursePaper;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class SwitchTo {
	public void changewindow(WebDriver driver) {
		String currentWindow = driver.getWindowHandle();// 获取当前窗口句柄
		Set<String> handles = driver.getWindowHandles();// 获取所有窗口句柄
		Iterator<String> it = handles.iterator(); 
		while (it.hasNext()) {  
			//取得迭代器当前句柄
			String currentHandle= it.next();
			if(currentWindow.equals(currentHandle)){
				continue;
			}else{
				driver.switchTo().window(currentHandle);
			}
	}
	}
}
