package tongche_main.pageshelper.admin.login;

import org.apache.log4j.Logger;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pages.admin.login.AdminLoginPage;
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
     **/
	public static void typeAdminInfo(SeleniumUtil seleniumUtil,String username,String password){
		logger.info("开始输入登录信息");
		seleniumUtil.clear(AdminLoginPage.LP_INPUT_USERNAME);
		seleniumUtil.type(AdminLoginPage.LP_INPUT_USERNAME,username);
		seleniumUtil.clear(AdminLoginPage.LP_INPUT_PASSWORD);
		seleniumUtil.type(AdminLoginPage.LP_INPUT_PASSWORD,password);
		logger.info("输入登录信息完毕");
		seleniumUtil.click(AdminLoginPage.LP_BUTTON_LOGIN);
		seleniumUtil.pause(1000);
	}

	/**
	 * 登出
	 */
	public static void logOutAdmin(SeleniumUtil seleniumUtil,String username){
		seleniumUtil.pause(2000);
		seleniumUtil.get(testAdminUrl + "/user/logout?tel=" + username + "");
	}
}
























