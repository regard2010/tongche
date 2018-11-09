package ixijian_main.pageshelper.login;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.login.LoginPage;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;


/**
 * 登录页面帮助类：提供在这个页面上做的操作的方法封装
 * @author shuailiu
 *
 */
public class LoginPageHelper extends BaseParpareTestPc {

	public static Logger logger = Logger.getLogger(LoginPageHelper.class);
	
	/**
	 * 等待登录页面元素加载
	 */
	public static void waitLoginPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.pause(1000);
	
		logger.info("开始检查登录页面元素");
		seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_INPUT_USERNAME);
		seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_INPUT_PASSWORD);
		seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_BUTTON_LOGIN);
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
		seleniumUtil.clear(LoginPage.LP_INPUT_USERNAME);
		seleniumUtil.type(LoginPage.LP_INPUT_USERNAME,username);
		seleniumUtil.clear(LoginPage.LP_INPUT_PASSWORD);
		seleniumUtil.type(LoginPage.LP_INPUT_PASSWORD,password);
		logger.info("输入登录信息完毕");
		seleniumUtil.click(LoginPage.LP_BUTTON_LOGIN);
	}


	/**
     * @description 验证登录错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
	public static void checkLoginErrorInfo(SeleniumUtil seleniumUtil,String error){
		seleniumUtil.isTextCorrect(seleniumUtil.getText(LoginPage.LP_TEXT_ERROR), error);
	}

	/**
	 * 登出
	 */
	public static void logOutPc(SeleniumUtil seleniumUtil){
		seleniumUtil.pause(2000);
		seleniumUtil.get(testPcUrl + "/user/logout.htm");
	}
}
























