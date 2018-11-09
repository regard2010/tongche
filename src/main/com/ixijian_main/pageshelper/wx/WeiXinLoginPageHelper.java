package ixijian_main.pageshelper.wx;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.WeiXinLoginPage;
import org.testng.log4testng.Logger;
import utils.LoginUtil;
import utils.SeleniumUtil;
import utils.TestUrl;

/**
 * 登录页面帮助类：提供在这个页面上做的操作的方法封装
 * @author shuailiu
 *
 */
public class WeiXinLoginPageHelper extends BaseParpareTestWeiXin {

	public static Logger logger = Logger.getLogger(WeiXinLoginPageHelper.class);

	/**
	 * 等待登录页面元素加载
	 */
	public static void waitLoginPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.pause(1000);
		logger.info("开始检查登录页面元素");
		seleniumUtil.waitForElementToLoad(timeOut, WeiXinLoginPage.WXLP_INPUT_USERNAME);
		seleniumUtil.waitForElementToLoad(timeOut, WeiXinLoginPage.WXLP_INPUT_PASSWORD);
		seleniumUtil.waitForElementToLoad(timeOut,WeiXinLoginPage.WXLP_INPUT_CODE);
		seleniumUtil.waitForElementToLoad(timeOut, WeiXinLoginPage.WXLP_BUTTON_LOGIN);
		logger.info("检查登录页面元素完毕");
	}
	
	/**
     * @description 登录操作封装
     * @param seleniumUtil selenium api封装引用对象
     * @param username 用户名值
     * @param password 用户密码值
     * */
	public static void typeLoginInfo(SeleniumUtil seleniumUtil,String username,String password){
		logger.info(">>>>>>>>>>开始获取验证码");
		seleniumUtil.clear(WeiXinLoginPage.WXLP_INPUT_USERNAME);
		seleniumUtil.type(WeiXinLoginPage.WXLP_INPUT_USERNAME,username);
		String code = LoginUtil.getInstance().WXLoginCode(username,seleniumUtil);
		logger.info(">>>>>>>>>>验证码获取完毕");
		seleniumUtil.back();
		logger.info(">>>>>>>>>>开始输入登录信息");
		seleniumUtil.clear(WeiXinLoginPage.WXLP_INPUT_USERNAME);
		seleniumUtil.type(WeiXinLoginPage.WXLP_INPUT_USERNAME,username);
		seleniumUtil.clear(WeiXinLoginPage.WXLP_INPUT_PASSWORD);
		seleniumUtil.type(WeiXinLoginPage.WXLP_INPUT_PASSWORD,password);
		seleniumUtil.clear(WeiXinLoginPage.WXLP_INPUT_CODE);
		seleniumUtil.type(WeiXinLoginPage.WXLP_INPUT_CODE,code);
		logger.info(">>>>>>>>>>输入登录信息完毕");
		seleniumUtil.click(WeiXinLoginPage.WXLP_BUTTON_LOGIN);
	}

	/**
     * @description 验证登录错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
	public static void checkLoginErrorInfo(SeleniumUtil seleniumUtil,String error){
		seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinLoginPage.WXLP_TEXT_ERROR), error);
	}

	/**
	 * 登出
	 */
	public static void logOutWeiXin(SeleniumUtil seleniumUtil){
		seleniumUtil.pause(2000);
		seleniumUtil.get(TestUrl.getWXOldURL() + "/user/logout.htm");
	}
}
























