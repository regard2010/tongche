package ixijian_main.pages.login;

import org.openqa.selenium.By;

/**
 * 登录页面元素定位声明
 */
public class LoginPage {
	
	/* 用户名输入框 */
	public static final By LP_INPUT_USERNAME = By.name("loginName");
	
	/* 密码输入框 */
	public static final By LP_INPUT_PASSWORD = By.name("loginPassword");
	
	/* 登录按钮 */
	public static final By LP_BUTTON_LOGIN = By.xpath("/html/body/div[2]/div/div[2]/div/div/input");
	
	/* 登录错误信息 */
	public static final By LP_TEXT_ERROR = By.xpath("/html/body/div[2]/div/div[2]/div/div/span");
}
