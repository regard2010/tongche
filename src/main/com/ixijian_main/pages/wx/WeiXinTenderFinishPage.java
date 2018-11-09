package ixijian_main.pages.wx;

import org.openqa.selenium.By;

public class WeiXinTenderFinishPage {

    /* 查看[投资成功] */
    public static final By TF_TEXT_IS_SUCCESS = By.xpath("/html/body/div/div[1]/div/div[1]");

    /* 查看[赠送积分] */
    public static final By TF_TEXT_POINTS = By.xpath("/html/body/div/div[1]/div/div[2]");

    /* [查看我的积分] */
    public static final By TF_BUTTON_POINTS = By.xpath("/html/body/div/div[2]/a[2]");

    /* 继续投资 */
    public static final By TF_BUTTON_TENDER = By.xpath("/html/body/div/div[2]/a[1]");

    /* 流程错误提示:金额不足或者系统错误 */
    public static final By TF_TEXT_ERROR = By.xpath("/html/body/div/div");

}
