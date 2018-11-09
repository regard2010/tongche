package tongche_main.pageshelper.admin.task;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pages.admin.task.AddTaskPage;
import utils.SeleniumUtil;
import utils.TestUrl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 任务页面帮助类：提供在这个页面上做的操作的方法封装
 * @author liuweiyi
 *
 */
public class AddTaskPageHelper extends BaseParpareTestAdmin {

	public static Logger logger = Logger.getLogger(AddTaskPageHelper.class);

	/**
	 * 等待任务页面元素加载
	 */
	public static void waitAddTaskPageLoad(SeleniumUtil seleniumUtil, int timeOut){
		seleniumUtil.get(TestUrl.getTongCheAdminUrl() + "/#/shipTask");
		seleniumUtil.pause(1000);
		seleniumUtil.click(AddTaskPage.ATP_BUTTON_ADD);
		logger.info("开始检查任务页面元素");
		seleniumUtil.pause(1000);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_CONTACT_NUMBER);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_DELIVER_COMPANY);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_PROJECT);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_PROJECT_ADDRESS);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_EXPECTED_FREIGHT);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_LINK_MAN);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_LINK_MAN_TEL);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_PLAN_QUANTITY);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_PLAN_START_TIME);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_POURING_METHOD);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_POURING_POSITION);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_SLUMP);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_TASK_VARIETY);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_TRANSPORT_DISTANCE);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_INPUT_REMARKS);
		seleniumUtil.waitForElementToLoad(timeOut, AddTaskPage.ATP_BUTTON_SUBMIT);
		logger.info("检查任务元素完毕");
	}
	
	/**
     * @description 任务操作封装
     * @param seleniumUtil selenium api封装引用对象
     **/
	public static void typeAddTaskPageInfo(SeleniumUtil seleniumUtil,String taskNumber,String fhQuantity,String errorMessage){
		logger.info("开始输入小票信息");
		seleniumUtil.clear(AddTaskPage.ATP_INPUT_CONTACT_NUMBER);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_CONTACT_NUMBER,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_DELIVER_COMPANY);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_DELIVER_COMPANY,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_PROJECT);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_PROJECT,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_PROJECT_ADDRESS);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_PROJECT_ADDRESS,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_EXPECTED_FREIGHT);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_EXPECTED_FREIGHT,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_LINK_MAN);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_LINK_MAN,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_LINK_MAN_TEL);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_LINK_MAN_TEL,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_PLAN_QUANTITY);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_PLAN_QUANTITY,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_PLAN_START_TIME);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_PLAN_START_TIME,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_POURING_METHOD);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_POURING_METHOD,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_SLUMP);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_SLUMP,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_TASK_VARIETY);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_TASK_VARIETY,taskNumber);
		seleniumUtil.click(AddTaskPage.ATP_INPUT_TRANSPORT_DISTANCE);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_TRANSPORT_DISTANCE,taskNumber);
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		seleniumUtil.clear(AddTaskPage.ATP_INPUT_REMARKS);
		seleniumUtil.type(AddTaskPage.ATP_INPUT_REMARKS,df.format(day));
		logger.info("输入小票信息完毕");
		seleniumUtil.click(AddTaskPage.ATP_BUTTON_SUBMIT);
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
			seleniumUtil.click(AddTaskPage.ATP_BUTTON_CLOSE_WINDOW);
			seleniumUtil.pause(1000);
		}catch (NoAlertPresentException e){
			logger.info("==========================新建任务成功==========================");
		}
	}
}
























