package ixijian_main.plugins.arrow;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class TestResultListener extends TestListenerAdapter{

	private static Logger logger = Logger.getLogger(TestResultListener.class);
	protected ITestContext testContext = null;
	String browser = null;
	
	//获得testng.xml中配置的浏览器
	public void onStart(ITestContext testContext){
		this.testContext = testContext;
		browser = String.valueOf(testContext.getCurrentXmlTest().getParameter("browserName"));
		super.onStart(testContext);
	}
	
	//测试用例执行失败并进行截图操作
	public void onTestFailure(ITestResult tr){
		super.onTestFailure(tr);
		logger.warn(tr.getName() + "测试用例执行失败！");
		WebDriver driver = (WebDriver) testContext.getAttribute("SELENIUM_DRIVER");
		saveScreenShot(tr,driver,browser);
	}

	//截图流程
	private void saveScreenShot(ITestResult tr, WebDriver driver, String browser) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String mDateTime = formatter.format(new Date());
		String fileName = mDateTime + "_" + tr.getName();
		String filePath = "";
		try {
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			filePath = "result/screenshot/" + fileName + ".jpg";
			File destFile = new File(filePath);
			FileUtils.copyFile(screenshot, destFile);
			logger.info("[" + fileName + "]截图成功，保存在：" + "[" + filePath + "]");
		} catch (Exception e) {
			filePath = "["+fileName+"]" + " ,截图失败，原因：" + e.getMessage();
			logger.error(filePath);
		}
		if(!"".equals(filePath)){
			Reporter.setCurrentTestResult(tr);
			Reporter.log(filePath);
			//把截图写入到html报告中
			Reporter.log("<img src=\"../../" + filePath + "\"/>");
		}
		
	}
}


























