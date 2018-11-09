package ixijian_main.pageshelper.login;


import ixijian_main.base.BaseParpareTestCrm;
import ixijian_main.pages.login.AdminLoginPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import utils.MySqlUtil;
import utils.SeleniumUtil;

/**
 * 登录页面帮助类：提供在这个页面上做的操作的方法封装
 * @author shuailiu
 *
 */
public class CrmLoginPageHelper extends BaseParpareTestCrm {

	public static Logger logger = Logger.getLogger(AdminLoginPageHelper.class);
	
	/**
	 * 等待登录页面元素加载
	 */
	public static void waitLoginPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.pause(1000);
	
		logger.info("开始检查登录页面元素");
		seleniumUtil.waitForElementToLoad(timeOut, AdminLoginPage.LP_INPUT_USERNAME);
		seleniumUtil.waitForElementToLoad(timeOut, AdminLoginPage.LP_INPUT_PASSWORD);
		seleniumUtil.waitForElementToLoad(timeOut, AdminLoginPage.LP_BUTTON_LOGIN);
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
//		seleniumUtil.clear(By.id("username"));
		seleniumUtil.type(By.id("username"),username);
//		seleniumUtil.clear(By.id("password"));
		seleniumUtil.type(By.id("password"),password);
		seleniumUtil.pause(1000);
		/**
		seleniumUtil.click(AdminLoginPage.LP_BUTTON_CODE);
		seleniumUtil.pause(8000);
		String codeSql = "SELECT `content` FROM `ms_send` WHERE `template_id`='85' ORDER BY `send_id` DESC LIMIT 1;";
		String code = MySqlUtil.getInstance().mySqlCURD_URI("ixijian_dev",codeSql);
		seleniumUtil.pause(3000);
		code = code.substring(9,15);
		logger.info("验证码是：" + code);
		seleniumUtil.clear(AdminLoginPage.LP_INPUT_CODE);
		seleniumUtil.type(AdminLoginPage.LP_INPUT_CODE,code);
		seleniumUtil.pause(1000);
		 */
		logger.info("输入登录信息完毕");
		seleniumUtil.click(AdminLoginPage.LP_BUTTON_LOGIN);
	}


	/**
     * @description 验证登录错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
	public static void checkLoginErrorInfo(SeleniumUtil seleniumUtil,String error){
		seleniumUtil.isTextCorrect(seleniumUtil.getText(AdminLoginPage.LP_TEXT_ERROR), error);
	}

	/**
	 * 登出
	 */
	public static void logOutPc(SeleniumUtil seleniumUtil){
		seleniumUtil.pause(2000);
		seleniumUtil.get(testCrmUrl + "/user/logout.htm");
	}
}
























