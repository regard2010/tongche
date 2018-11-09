package tongche_main.pages.admin.task;

import org.openqa.selenium.By;

/**
 * 小票页面元素定位声明(只填必填项)
 */
public class AddItemOrderPage {
	
	/* 新建小票 */
	public static final By AIOP_BUTTON_ADD = By.id("projectXpAdd");

	/* 任务单编号 */
	public static final By AIOP_INPUT_TASK_NUMBER = By.id("userName");

	/* 车号 */
	public static final By AIOP_INPUT_CAR_NUMBER = By.id("driver");

	/* 发货方量 */
	public static final By AIOP_INPUT_FH_Quantity = By.id("fhQuantity");

	/* 备注 */
	public static final By AIOP_INPUT_REMARKS = By.name("remarks");

	/* 提交 */
	public static final By AIOP_BUTTON_SUBMIT = By.id("noteAdd");

	/* 关闭新建小票窗口 */
	public static final By AIOP_BUTTON_CLOSE_WINDOW = By.xpath("//*[@id=\"addNote\"]/div/div/div[1]/button");


}
