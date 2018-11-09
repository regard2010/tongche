package tongche_main.pages.admin.vertical;

import org.openqa.selenium.By;

/**
 * 车辆页面元素定位声明(只填必填项)
 */
public class AddVerticalPage {
	
	/* 导航到【车辆】标签 */
	public static final By AVP_BUTTON_VERTICAL = By.xpath("//*[@id=\"header-menu\"]/li[2]/a");
	
	/* 新建车辆 */
	public static final By AVP_BUTTON_ADD = By.id("add_car_btn");

	/* 最大方量 */
	public static final By AVP_INPUT_MAX_CAPACITY = By.id("car_add_capacity_input");

	/* 车牌号 */
	public static final By AVP_INPUT_CARLINCESE = By.id("car_add_license");

	/* 车号 */
	public static final By AVP_INPUT_CARNUMBER = By.id("car_add_num");

	/* 车辆类型 */
	public static final By AVP_SELECT_CARTYPE = By.name("carType");

	/* 表单提交 */
	public static final By AVP_BUTTON_SUBMIT = By.id("car_add_confirm_btn");

	/* 关闭新建车辆窗口 */
	public static final By AVP_BUTTON_CLOSE_WINDOW = By.xpath("//*[@id=\"addVertical\"]/div/div/div[1]/button");


}
