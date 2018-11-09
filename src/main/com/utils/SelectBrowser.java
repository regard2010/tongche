package utils;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestContext;

public class SelectBrowser {

	static Logger logger = Logger.getLogger(SelectBrowser.class.getName());
	
	
	public WebDriver selectExplorerByName(String browser, ITestContext context){
		Properties props = System.getProperties(); //获得系统属性集
		String currentPlatform = props.getProperty("os.name"); //操作系统名称
		logger.info("当前操作系统是：[" + currentPlatform + "]");
		logger.info("启动测试浏览器：[" + browser + "]");
		//从testNG的配置文件读取参数driverConfgFilePath的值
		String driverConfgFilePath = context.getCurrentXmlTest().getParameter("driverConfgFilePath");
		/** 声明好驱动的路径 */
		String chromedriver_win = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_win");
//		String chromedriver_linux = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_linux");
        String chromedriver_mac = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_mac");
        String ghostdriver_win = PropertiesDataProvider.getTestData(driverConfgFilePath, "ghostdriver_win");
        if(currentPlatform.toLowerCase().contains("win")){ //如果是windows平台
        	if(browser.equalsIgnoreCase("chrome")){
        		System.setProperty("webdriver.chrome.driver", chromedriver_win);
        		//返回谷歌浏览器对象
        		return new ChromeDriver();
        	}else if(browser.equalsIgnoreCase("firefox")){
        		//返回火狐浏览器对象
        		return new FirefoxDriver();
        	}else if(browser.equalsIgnoreCase("ghost")){
        		DesiredCapabilities ghostCapabilities = new DesiredCapabilities();
        		ghostCapabilities.setJavascriptEnabled(true);
        		ghostCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, ghostdriver_win);
        		//返回ghost对象
        		return new PhantomJSDriver(ghostCapabilities);
        	}else {
        		logger.error("浏览器 [" + browser + "]" + " 无法在  [" + currentPlatform + "] 上启动");
                Assert.fail("浏览器 [" + browser + "]" + " 无法在  [" + currentPlatform + "] 上启动");
        	}
        }else if(currentPlatform.toLowerCase().contains("mac")){ //如果是mac平台
        	if(browser.equalsIgnoreCase("chrome")){
        		System.setProperty("webdriver.chrome.driver", chromedriver_mac);
        		//返回谷歌浏览器对象
        		return new ChromeDriver();
        	}else if(browser.equalsIgnoreCase("firefox")){
        		//返回谷歌浏览器对象
        		return new FirefoxDriver();
        	}else if(browser.equalsIgnoreCase("safari")) {
				System.setProperty("webdriver.safari.driver", "/Users/shuailiu/Library/Safari/Extensions");
				System.setProperty("webdriver.safari.noinstall", "true");
				//返回苹果浏览器对象
				return new SafariDriver();
			}else {
        		logger.error("浏览器 [" + browser + "]" + " 无法在  [" + currentPlatform + "] 上启动");
                Assert.fail("浏览器 [" + browser + "]" + " 无法在  [" + currentPlatform + "] 上启动");
        	}
        }else{
        	logger.error("操作系统:" + currentPlatform + "无法启动浏览器,当前只支持windows和mac系统");
        	Assert.fail("操作系统:" + currentPlatform + "无法启动浏览器,当前只支持windows和mac系统");
        }
        return null;
	}
}

























