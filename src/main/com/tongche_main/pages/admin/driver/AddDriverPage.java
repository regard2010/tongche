package tongche_main.pages.admin.driver;

import org.openqa.selenium.By;

/**
 * 驾驶员页面元素定位声明(只填必填项)
 */
public class AddDriverPage {
	
	/* 新建驾驶员 */
	public static final By ADP_BUTTON_ADD = By.xpath("//*[@id=\"main\"]/div[1]/div[1]/button");

	/* 姓名 */
	public static final By ADP_INPUT_DRIVER_NAME = By.id("userName");

	/* 电话 */
	public static final By ADP_INPUT_DRIVER_TEL = By.xpath("//*[@id=\"driverDetail\"]/div[2]/div/input");

	/* 表单提交 */
	public static final By ADP_BUTTON_SUBMIT = By.id("addDriver");

	/* 关闭新建车辆窗口 */
	public static final By ADP_BUTTON_CLOSE_WINDOW = By.xpath("//*[@id=\"driver1\"]/div/div/div[1]/button");


}
