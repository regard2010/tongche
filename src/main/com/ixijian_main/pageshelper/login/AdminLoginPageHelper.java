package ixijian_main.pageshelper.login;


import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.pages.login.AdminLoginPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.MySqlUtil;
import utils.SeleniumUtil;

/**
 * 登录页面帮助类：提供在这个页面上做的操作的方法封装
 * @author shuailiu
 *
 */
public class AdminLoginPageHelper extends BaseParpareTestAdmin {

	public static Logger logger = Logger.getLogger(AdminLoginPageHelper.class);

	/**
	 * 等待登录页面元素加载
	 */
	public static void waitAdminPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.pause(1000);
		if(seleniumUtil.isElementExist(AdminLoginPage.LP_TEXT_LOGIN_NOW)){
			seleniumUtil.setBrowserSize(1600,900);
			seleniumUtil.pause(1000);
			logger.info("已检测到用户已登录");
		}else {
			logger.info("开始检查登录页面元素");
			seleniumUtil.waitForElementToLoad(timeOut, AdminLoginPage.LP_INPUT_USERNAME);
			seleniumUtil.waitForElementToLoad(timeOut, AdminLoginPage.LP_INPUT_PASSWORD);
			seleniumUtil.waitForElementToLoad(timeOut, AdminLoginPage.LP_BUTTON_LOGIN);
//			seleniumUtil.waitForElementToLoad(timeOut, AdminLoginPage.LP_BUTTON_CODE);
//			seleniumUtil.waitForElementToLoad(timeOut, AdminLoginPage.LP_INPUT_CODE);
			logger.info("检查登录页面元素完毕");
		}
	}
	
	/**
     * @description 登录操作封装
     * @param seleniumUtil selenium api封装引用对象
     * @param username 用户名值
     * @param password 用户密码值
     * */
	public static void typeAdminInfo(SeleniumUtil seleniumUtil,String username,String password){
		if(seleniumUtil.isElementExist(AdminLoginPage.LP_TEXT_LOGIN_NOW)){
			logger.info("用户已登录，请继续操作");
			seleniumUtil.pause(1000);
			seleniumUtil.setBrowserSize(400,800);
		}else{
			logger.info("开始输入登录信息");
			seleniumUtil.clear(AdminLoginPage.LP_INPUT_USERNAME);
			seleniumUtil.type(AdminLoginPage.LP_INPUT_USERNAME,username);
			seleniumUtil.clear(AdminLoginPage.LP_INPUT_PASSWORD);
			seleniumUtil.type(AdminLoginPage.LP_INPUT_PASSWORD,password);
//			seleniumUtil.pause(1000);
//			seleniumUtil.click(AdminLoginPage.LP_BUTTON_CODE);
//			seleniumUtil.pause(8000);
//			String codeSql = "SELECT `content` FROM `ms_send` ORDER BY `send_id` DESC LIMIT 1";
//			String code = MySqlUtil.getInstance().mySqlCURD_URI("ixijian_dev",codeSql);
//			seleniumUtil.pause(3000);
//			code = code.substring(9,15);
//			logger.info("验证码是：" + code);
//			seleniumUtil.clear(AdminLoginPage.LP_INPUT_CODE);
//			seleniumUtil.type(AdminLoginPage.LP_INPUT_CODE,code);
//			seleniumUtil.pause(1000);
			logger.info("输入登录信息完毕");
			seleniumUtil.click(AdminLoginPage.LP_BUTTON_LOGIN);
			seleniumUtil.pause(1000);
//			checkAdminErrorInfo(seleniumUtil,username,password,code);
		}

	}


	/**
     * @description 验证登录错误信息
     * @param seleniumUtil selenium api封装引用对象
     * */
	private static void checkAdminErrorInfo(SeleniumUtil seleniumUtil,String username,String password,String code){
		if(seleniumUtil.isElementExist(AdminLoginPage.LP_TEXT_ERROR)){
			resetAdminInfo(seleniumUtil,username,password,code);
		}
	}

	/**
	 * 重新登录
	 * @param seleniumUtil
	 * @param username
	 * @param password
	 */
	private static void resetAdminInfo(SeleniumUtil seleniumUtil,String username,String password,String code){
		seleniumUtil.pause(5000);
		String codeSql = "SELECT `content` FROM `ms_send` ORDER BY `send_id` DESC LIMIT 1";
		code = MySqlUtil.getInstance().mySqlCURD_URI("ixijian_dev",codeSql);
		seleniumUtil.pause(3000);
		code = code.substring(9,15);
		logger.info("验证码是：" + code);
		logger.info("开始重新输入登录信息");
		seleniumUtil.clear(AdminLoginPage.LP_INPUT_USERNAME);
		seleniumUtil.type(AdminLoginPage.LP_INPUT_USERNAME,username);
		seleniumUtil.clear(AdminLoginPage.LP_INPUT_PASSWORD);
		seleniumUtil.type(AdminLoginPage.LP_INPUT_PASSWORD,password);
		seleniumUtil.clear(AdminLoginPage.LP_INPUT_CODE);
		seleniumUtil.type(AdminLoginPage.LP_INPUT_CODE,code);
		seleniumUtil.pause(1000);
		logger.info("重新输入登录信息完毕");
		seleniumUtil.click(AdminLoginPage.LP_BUTTON_LOGIN);
	}


	/**
	 * 登出
	 */
	public static void logOutAdmin(SeleniumUtil seleniumUtil){
		seleniumUtil.pause(2000);
		seleniumUtil.get(testAdminUrl + "/logout");
	}
}
























