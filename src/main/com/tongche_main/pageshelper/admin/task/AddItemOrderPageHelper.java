package tongche_main.pageshelper.admin.task;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pages.admin.task.AddItemOrderPage;
import utils.SeleniumUtil;
import utils.TestUrl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 小票页面帮助类：提供在这个页面上做的操作的方法封装
 * @author liuweiyi
 *
 */
public class AddItemOrderPageHelper extends BaseParpareTestAdmin {

	public static Logger logger = Logger.getLogger(AddItemOrderPageHelper.class);

	/**
	 * 等待小票页面元素加载
	 */
	public static void waitItemOrderPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.get(TestUrl.getTongCheAdminUrl() + "/#/shipItemOrder");
		seleniumUtil.pause(1000);
		seleniumUtil.click(AddItemOrderPage.AIOP_BUTTON_ADD);
		logger.info("开始检查小票页面元素");
		seleniumUtil.pause(1000);
		seleniumUtil.waitForElementToLoad(timeOut, AddItemOrderPage.AIOP_INPUT_TASK_NUMBER);
		seleniumUtil.waitForElementToLoad(timeOut, AddItemOrderPage.AIOP_INPUT_CAR_NUMBER);
		seleniumUtil.waitForElementToLoad(timeOut, AddItemOrderPage.AIOP_INPUT_FH_Quantity);
		seleniumUtil.waitForElementToLoad(timeOut, AddItemOrderPage.AIOP_INPUT_REMARKS);
		logger.info("检查小票元素完毕");
	}
	
	/**
     * @description 驾驶员操作封装
     * @param seleniumUtil selenium api封装引用对象
     **/
	public static void typeItemOrderPageInfo(SeleniumUtil seleniumUtil,String taskNumber,String fhQuantity,String errorMessage){
		logger.info("开始输入小票信息");
		seleniumUtil.clear(AddItemOrderPage.AIOP_INPUT_TASK_NUMBER);
		seleniumUtil.type(AddItemOrderPage.AIOP_INPUT_TASK_NUMBER,taskNumber);
		seleniumUtil.click(AddItemOrderPage.AIOP_INPUT_CAR_NUMBER);
		seleniumUtil.clear(AddItemOrderPage.AIOP_INPUT_FH_Quantity);
		seleniumUtil.type(AddItemOrderPage.AIOP_INPUT_FH_Quantity,fhQuantity);
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		seleniumUtil.clear(AddItemOrderPage.AIOP_INPUT_REMARKS);
		seleniumUtil.type(AddItemOrderPage.AIOP_INPUT_REMARKS,df.format(day));
		logger.info("输入小票信息完毕");
		seleniumUtil.click(AddItemOrderPage.AIOP_BUTTON_SUBMIT);
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
			seleniumUtil.click(AddItemOrderPage.AIOP_BUTTON_CLOSE_WINDOW);
			seleniumUtil.pause(1000);
		}catch (NoAlertPresentException e){
			logger.info("==========================新建小票成功==========================");
		}
	}
}
























