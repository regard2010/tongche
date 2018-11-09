package tongche_main.pageshelper.admin.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pages.admin.driver.AddDriverPage;
import tongche_main.pages.admin.vertical.AddVerticalPage;
import utils.SeleniumUtil;
import utils.TestUrl;

/**
 * 驾驶员页面帮助类：提供在这个页面上做的操作的方法封装
 * @author liuweiyi
 *
 */
public class AddDriverPageHelper extends BaseParpareTestAdmin {

	public static Logger logger = Logger.getLogger(AddDriverPageHelper.class);

	/**
	 * 等待驾驶员页面元素加载
	 */
	public static void waitAddDriverPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.get(TestUrl.getTongCheAdminUrl() + "/#/shipDriver");
		seleniumUtil.pause(1000);
		seleniumUtil.click(AddDriverPage.ADP_BUTTON_ADD);
		logger.info("开始检查驾驶员页面元素");
		seleniumUtil.pause(1000);
		seleniumUtil.waitForElementToLoad(timeOut, AddDriverPage.ADP_INPUT_DRIVER_NAME);
		seleniumUtil.waitForElementToLoad(timeOut, AddDriverPage.ADP_INPUT_DRIVER_TEL);
		seleniumUtil.waitForElementToLoad(timeOut, AddDriverPage.ADP_BUTTON_SUBMIT);
		logger.info("检查登录驾驶员元素完毕");
	}
	
	/**
     * @description 驾驶员操作封装
     * @param seleniumUtil selenium api封装引用对象
     **/
	public static void typeAddDriverPageInfo(SeleniumUtil seleniumUtil,String driverName,String driverTel,String errorMessage){
		logger.info("开始输入驾驶员信息");
		seleniumUtil.clear(AddDriverPage.ADP_INPUT_DRIVER_NAME);
		seleniumUtil.type(AddDriverPage.ADP_INPUT_DRIVER_NAME,driverName);
		seleniumUtil.clear(AddDriverPage.ADP_INPUT_DRIVER_TEL);
		seleniumUtil.type(AddDriverPage.ADP_INPUT_DRIVER_TEL,driverTel);
		logger.info("输入驾驶员信息完毕");
		seleniumUtil.click(AddDriverPage.ADP_BUTTON_SUBMIT);
		seleniumUtil.pause(1000);
		errorAlert(seleniumUtil,errorMessage);
	}

	/**
	 * 出现重复数据弹出框处理
	 * @param seleniumUtil
	 * @param errorMessage
	 */
	public static void errorAlert(SeleniumUtil seleniumUtil,String errorMessage){
		try {
			Alert alert = seleniumUtil.driver.switchTo().alert();
			String errorText = alert.getText();
			Assert.assertEquals(errorText,errorMessage);
			alert.dismiss();
			seleniumUtil.click(AddDriverPage.ADP_BUTTON_CLOSE_WINDOW);
			seleniumUtil.pause(1000);
		}catch (NoAlertPresentException e){
			logger.info("==========================新建搅拌车驾驶员成功==========================");
		}
	}
}
























