package tongche_main.pages.admin.login;

import org.openqa.selenium.By;

/**
 * 登录页面元素定位声明
 */
public class AdminLoginPage {
	
	/* 用户名输入框 */
	public static final By LP_INPUT_USERNAME = By.id("mobile");
	
	/* 密码输入框 */
	public static final By LP_INPUT_PASSWORD = By.id("secret");

	/* 登录按钮 */
	public static final By LP_BUTTON_LOGIN = By.xpath("//*[@id=\"app\"]/div/div/div/button");

	/************************** 已登录 **************************/
	/* 用户已登录 */
	public static final By LP_TEXT_LOGIN_NOW = By.xpath("//*[@id=\"head-nav\"]/div/div[2]/ul[2]/li/a/span");
}
