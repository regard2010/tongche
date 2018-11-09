package ixijian_main.pages.login;

import org.openqa.selenium.By;

/**
 * 登录页面元素定位声明
 */
public class CrmLoginPage {
	
	/* 用户名输入框 */
	public static final By LP_INPUT_USERNAME = By.name("username");
	
	/* 密码输入框 */
	public static final By LP_INPUT_PASSWORD = By.name("password");
	
	/* 登录按钮 */
	public static final By LP_BUTTON_LOGIN = By.xpath("//*[@id=\"fm1\"]/div[3]/button");
	
	/* 登录错误信息 */
	public static final By LP_TEXT_ERROR = By.xpath("//*[@id=\"fm1\"]/div[2]/div");

}
