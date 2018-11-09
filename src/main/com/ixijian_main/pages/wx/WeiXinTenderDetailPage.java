package ixijian_main.pages.wx;

import org.openqa.selenium.By;

public class WeiXinTenderDetailPage {

    /* 投资金额 */
    public static final By TD_INPUT_AMOUNT = By.id("payAmount");

    /* 立即投资按钮 */
    public static final By TD_BUTTON_TENDER = By.name("tender-show ");

    /* 有卡券时，直接点击【去支付】 */
    public static final By TD_BUTTON_TENDER_COUPON = By.id("to-conform-pay");

    /* 流程错误提示:未登录 */
    public static final By TD_TEXT_ERROR = By.xpath("");

}
