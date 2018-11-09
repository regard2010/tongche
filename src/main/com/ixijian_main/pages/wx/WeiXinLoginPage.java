package ixijian_main.pages.wx;

import org.openqa.selenium.By;

public class WeiXinLoginPage {

    /* 用户名输入框 */
    public static final By WXLP_INPUT_USERNAME = By.name("loginName");

    /* 密码输入框 */
    public static final By WXLP_INPUT_PASSWORD = By.name("loginPassword");

    /* 登录按钮 */
    public static final By WXLP_BUTTON_LOGIN = By.xpath("/html/body/div/div[3]/div/a");

    /* 登录错误信息 */
    public static final By WXLP_TEXT_ERROR = By.xpath("/html/body/div[2]/p");

    /**********************新添加验证码登录***********************/

    /* 验证码输入框 */
    public static final By WXLP_INPUT_CODE = By.name("loginCode");


}
