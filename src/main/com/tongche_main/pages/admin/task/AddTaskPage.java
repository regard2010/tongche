package tongche_main.pages.admin.task;

import org.openqa.selenium.By;

/**
 * 订单页面元素定位声明(只填必填项)
 */
public class AddTaskPage {
	
	/* 新建订单 */
	public static final By ATP_BUTTON_ADD = By.id("taskAdds");

	/* 合同编号 */
	public static final By ATP_INPUT_CONTACT_NUMBER = By.id("deliverContactName");

	/* 收货单位 */
	public static final By ATP_INPUT_DELIVER_COMPANY = By.id("_unit");

	/* 工程名称 */
	public static final By ATP_INPUT_PROJECT = By.id("_project");

	/* 运输距离 */
	public static final By ATP_INPUT_TRANSPORT_DISTANCE = By.name("transportDistance");

	/* 浇筑部位 */
	public static final By ATP_INPUT_POURING_POSITION = By.name("pouringPosition");

	/* 浇筑方式 */
	public static final By ATP_INPUT_POURING_METHOD = By.id("method");

	/* 砼品种 */
	public static final By ATP_INPUT_TASK_VARIETY = By.id("taskVariety");

	/* 坍落度 */
	public static final By ATP_INPUT_SLUMP = By.id("slump");

	/* 计划方量 */
	public static final By ATP_INPUT_PLAN_QUANTITY = By.name("planQuantity");

	/* 计划时间 */
	public static final By ATP_INPUT_PLAN_START_TIME = By.id("planStartTime");

	/* 联系人 */
	public static final By ATP_INPUT_LINK_MAN = By.id("linkMan");

	/* 联系电话 */
	public static final By ATP_INPUT_LINK_MAN_TEL = By.id("linkManTel");

	/* 预计运费 */
	public static final By ATP_INPUT_EXPECTED_FREIGHT = By.name("expectedFreight");

	/* 送货地址 */
	public static final By ATP_INPUT_PROJECT_ADDRESS = By.name("projectAddress");

	/* 备注 */
	public static final By ATP_INPUT_REMARKS = By.name("specialNeed");

	/* 提交 */
	public static final By ATP_BUTTON_SUBMIT = By.id("taskAdd");

	/* 关闭新建小票窗口 */
	public static final By ATP_BUTTON_CLOSE_WINDOW = By.xpath("//*[@id=\"addNote\"]/div/div/div[1]/button");


}


























