package ixijian_main.pageshelper.pc;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.PcLoginPage;
import org.openqa.selenium.By;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;

/**
 * 登录页面帮助类：提供在这个页面上做的操作的方法封装
 * @author shuailiu
 *
 */
public class PcLoginPageHelper extends BaseParpareTestPc {

	public static Logger logger = Logger.getLogger(PcLoginPageHelper.class);

	/**
	 * 等待登录页面元素加载
	 */
	public static void waitLoginPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.pause(1000);
	
		logger.info("开始检查登录页面元素");
		seleniumUtil.waitForElementToLoad(timeOut, PcLoginPage.LP_INPUT_USERNAME);
		seleniumUtil.waitForElementToLoad(timeOut, PcLoginPage.LP_INPUT_PASSWORD);
		seleniumUtil.waitForElementToLoad(timeOut, PcLoginPage.LP_BUTTON_LOGIN);
		logger.info("检查登录页面元素完毕");
	}
	
	/**
     * @description 登录操作封装
     * @param seleniumUtil selenium api封装引用对象
     * @param username 用户名值
     * @param password 用户密码值
     * */
	public static void typeLoginInfo(SeleniumUtil seleniumUtil,String username,String password){
		logger.info("开始输入登录信息");
		seleniumUtil.clear(PcLoginPage.LP_INPUT_USERNAME);
		seleniumUtil.type(PcLoginPage.LP_INPUT_USERNAME,username);
		seleniumUtil.clear(PcLoginPage.LP_INPUT_PASSWORD);
		seleniumUtil.type(PcLoginPage.LP_INPUT_PASSWORD,password);
		logger.info("输入登录信息完毕");
		seleniumUtil.click(PcLoginPage.LP_BUTTON_LOGIN);
	}


	/**
	 * 等待弹出框
	 * @param seleniumUtil
	 * @param timeOut
	 */
	public static  void waitLoginLayerTitleLoad(SeleniumUtil seleniumUtil,int timeOut){
		seleniumUtil.pause(1000);
		logger.info("开始检查用户告知弹出框元素");
		seleniumUtil.waitForElementToLoad(timeOut,PcLoginPage.LP_TITLE_NOTICE);
		logger.info("检查用户告知弹出框元素完毕");
	}

	/**
	 * 关闭弹出框
	 * @param seleniumUtil
	 */
	public static void typeLoginLayerTitleInfo(SeleniumUtil seleniumUtil){
		if(seleniumUtil.isElementExist(By.id("layui-layer1"))){
			seleniumUtil.click(PcLoginPage.LP_TITLE_NOTICE);
			seleniumUtil.pause(1000);
			logger.info("用户告知框[已关闭]，可以继续登录");
		}else{
			logger.info("用户告知框[未弹出]，可以继续登录");
		}

	}


	/**
     * @description 验证登录错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
	public static void checkLoginErrorInfo(SeleniumUtil seleniumUtil,String error){
		seleniumUtil.isTextCorrect(seleniumUtil.getText(PcLoginPage.LP_TEXT_ERROR), error);
	}

	/**
	 * 登出
	 */
	public static void logOutPc(SeleniumUtil seleniumUtil){
		seleniumUtil.pause(2000);
		seleniumUtil.get(testPcUrl + "/user/logout.htm");
	}
}
























