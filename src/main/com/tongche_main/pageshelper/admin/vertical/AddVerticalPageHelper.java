package tongche_main.pageshelper.admin.vertical;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pages.admin.vertical.AddVerticalPage;
import utils.SeleniumUtil;
import utils.TestUrl;

/**
 * 车辆页面帮助类：提供在这个页面上做的操作的方法封装
 * @author shuailiu
 *
 */
public class AddVerticalPageHelper extends BaseParpareTestAdmin {

	public static Logger logger = Logger.getLogger(AddVerticalPageHelper.class);

	/**
	 * 等待车辆页面元素加载
	 */
	public static void waitAddVerticalPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.pause(2000);
		seleniumUtil.click(AddVerticalPage.AVP_BUTTON_VERTICAL);
		seleniumUtil.pause(1000);
		seleniumUtil.click(AddVerticalPage.AVP_BUTTON_ADD);
		logger.info("开始检查车辆页面元素");
		seleniumUtil.pause(1000);
		seleniumUtil.waitForElementToLoad(timeOut, AddVerticalPage.AVP_INPUT_MAX_CAPACITY);
		seleniumUtil.waitForElementToLoad(timeOut, AddVerticalPage.AVP_INPUT_CARLINCESE);
		seleniumUtil.waitForElementToLoad(timeOut, AddVerticalPage.AVP_INPUT_CARNUMBER);
//		seleniumUtil.waitForElementToLoad(timeOut, AddVerticalPage.AVP_SELECT_CARTYPE);
		seleniumUtil.waitForElementToLoad(timeOut, AddVerticalPage.AVP_BUTTON_SUBMIT);
		logger.info("检查车辆元素完毕");
	}
	
	/**
     * @description 车辆操作封装
     * @param seleniumUtil selenium api封装引用对象
     **/
	public static void typeAddVerticalPageInfo(SeleniumUtil seleniumUtil,String maxCapacity,String carLincese,String carNumber){
		logger.info("开始输入车辆信息");
		seleniumUtil.clear(AddVerticalPage.AVP_INPUT_MAX_CAPACITY);
		seleniumUtil.type(AddVerticalPage.AVP_INPUT_MAX_CAPACITY,maxCapacity);
		seleniumUtil.clear(AddVerticalPage.AVP_INPUT_CARLINCESE);
		seleniumUtil.type(AddVerticalPage.AVP_INPUT_CARLINCESE,carLincese);
		seleniumUtil.clear(AddVerticalPage.AVP_INPUT_CARNUMBER);
		seleniumUtil.type(AddVerticalPage.AVP_INPUT_CARNUMBER,carNumber);
//		seleniumUtil.click(AddVerticalPage.AVP_SELECT_CARTYPE);
//		seleniumUtil.selectByValue(AddVerticalPage.AVP_SELECT_CARTYPE,carType);
		logger.info("输入车辆信息完毕");
		seleniumUtil.click(AddVerticalPage.AVP_BUTTON_SUBMIT);
		seleniumUtil.pause(1000);
//		errorAlert(seleniumUtil,errorMessage);
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
			seleniumUtil.click(AddVerticalPage.AVP_BUTTON_CLOSE_WINDOW);
			seleniumUtil.pause(1000);
		}catch (NoAlertPresentException e){
			logger.info("新建车辆成功");
		}
	}
}
























