package ixijian_main.pageshelper.login;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.login.IndexPcPage;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;


public class IndexPcPageHelper extends BaseParpareTestPc {

	public static Logger logger = Logger.getLogger(IndexPcPageHelper.class);

	/**
	 * 等待首页页面元素加载
	 */
	public static void waitIndexPcPageLoad(SeleniumUtil seleniumUtil, int timeOut) {
		seleniumUtil.pause(1000);

		logger.info("开始检查首页页面元素");
		seleniumUtil.waitForElementToLoad(timeOut, IndexPcPage.IP_BUTTON_LOGOUT);
		logger.info("检查首页页面元素完毕");
	}

	/**
	 * @description 验证登录错误信息
	 * @param seleniumUtil
	 *            selenium api封装引用对象
	 */
	public static void checkIndexPcErrorInfo(SeleniumUtil seleniumUtil) {
		seleniumUtil.isElementExist(IndexPcPage.IP_BUTTON_LOGOUT);
	}
}
