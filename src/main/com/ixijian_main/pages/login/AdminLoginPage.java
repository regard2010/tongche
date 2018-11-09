package ixijian_main.pages.login;

import org.openqa.selenium.By;

/**
 * 登录页面元素定位声明
 */
public class AdminLoginPage {
	
	/* 用户名输入框 */
	public static final By LP_INPUT_USERNAME = By.name("username");
	
	/* 密码输入框 */
	public static final By LP_INPUT_PASSWORD = By.name("password");
	
	/* 登录按钮 */
	public static final By LP_BUTTON_LOGIN = By.xpath("//*[@id=\"fm1\"]/div[3]/button");
	
	/* 登录错误信息 */
	public static final By LP_TEXT_ERROR = By.xpath("//*[@id=\"fm1\"]/div[2]/div");

	/************************** 增加验证码登录 **************************/

	/* 获取验证码 */
	public static final By LP_BUTTON_CODE = By.id("getCode");

	/* 写入验证码 */
	public static final By LP_INPUT_CODE = By.id("code");

	/************************** 已登录 **************************/
	/* 用户已登录 */
	public static final By LP_TEXT_LOGIN_NOW = By.xpath("//*[@id=\"head-nav\"]/div/div[2]/ul[2]/li/a/span");
}
