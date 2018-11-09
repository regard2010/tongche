package ixijian_main.pages.pc;

import org.openqa.selenium.By;

public class PcLoginPage {

    /* 用户名输入框 */
    public static final By LP_INPUT_USERNAME = By.name("");

    /* 密码输入框 */
    public static final By LP_INPUT_PASSWORD = By.name("");

    /* 登录按钮 */
    public static final By LP_BUTTON_LOGIN = By.xpath("");

    /* 登录错误信息 */
    public static final By LP_TEXT_ERROR = By.xpath("");

    /* 去掉弹出框 */
    public static final By LP_TITLE_NOTICE = By.xpath("//*[@id=\"layui-layer1\"]/span/a");
}
