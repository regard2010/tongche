package ixijian_main.pages.wx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WeiXinTenderConfirmPage {

    /* 投资金额 */
    public static final By TC_TEXT_AMOUNT = By.id("money");

    /* 预期收益 */
    public static final By TC_TEXT_INCOME = By.id("income");

    /* 投资卡券 */
    public static final By TC_TEXT_COUPON = By.id("coupon-count-span");

    /* 立即投资按钮 */
    public static final By TC_BUTTON_TENDER = By.name("to-conform-pay");

    /* 我同意 */
    public static final By TC_INPUT_I_AGREE = By.xpath("//*[@id=\"coform-pay-div\"]/div/div[6]/div[1]/label/input");

    /* 确认支付 */
    public static final By TC_INPUT_COMFIRM_PAY = By.id("to-tender");

    /* 流程错误提示:金额不足 */
    public static final By TC_TEXT_ERROR = By.xpath("");

    /**********************************使用卡券后，支付确认页更新********************************/

    /* 投资金额 */
    public static final By TC_TEXT_COUPON_AMOUNT = By.id("tender-money");

    /* 预期收益 */
    public static final By TC_TEXT_COUPON_INCOME = By.id("tender-income");

    /* 卡券收益 */
    public static final By TC_TEXT_COUPON_COUPON = By.id("tender-coupon-income");

    /* 预期总收益 */
    public static final By TC_TEXT_COUPON_PAY = By.id("tender-pay");
}
