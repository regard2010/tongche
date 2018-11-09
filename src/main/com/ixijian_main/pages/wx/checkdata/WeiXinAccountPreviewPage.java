package ixijian_main.pages.wx.checkdata;

import org.openqa.selenium.By;

public class WeiXinAccountPreviewPage {

    /* 可用余额 */
    public static final By PAP_TEXT_BALANCE = By.xpath("/html/body/div/div[1]/div[3]/div[1]");

    /* 总资产 */
    public static final By PAP_TEXT_TOTAL = By.xpath("/html/body/div/div[1]/div[2]/div[1]");

    /* 待收资金(元) */
    public static final By PAP_TEXT_RECEIVE = By.xpath("/html/body/div/div[1]/div[4]/div[1]");

    /* 我的积分 */
    public static final By PAP_TEXT_POINTS = By.xpath("/html/body/div/div[3]/div[3]/div/a/span/font");

    /* 我的卡券 */
//    public static final By PAP_TEXT_COUPONS = By.xpath("/html/body/div/div[3]/div[4]/div/a/span/font");
    public static final By PAP_TEXT_COUPONS = By.xpath("/html/body/div/div[3]/div[2]/div/a/span/font");

}
