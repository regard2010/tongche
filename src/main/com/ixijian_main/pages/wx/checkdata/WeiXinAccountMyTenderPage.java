package ixijian_main.pages.wx.checkdata;

import org.openqa.selenium.By;

public class WeiXinAccountMyTenderPage {

    /** >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>列表页<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< **/

    /* 我的投资列表页-->项目名称 */
    public static final By WXAMTP_TEXT_LIST_FBT = By.xpath("/html/body/div/div[1]/div/a[1]/div[1]");

    /* 我的投资列表页-->购买份额(元) */
    public static final By WXAMTP_TEXT_LIST_AMOUNT = By.xpath("/html/body/div/div[1]/div/a[1]/div[2]/span");

    /* 我的投资列表页-->预期总收益(元) */
    public static final By WXAMTP_TEXT_LIST_INTEREST = By.xpath("/html/body/div/div[1]/div/a[1]/div[3]/span[1]");

    /* 我的投资列表页-->状态(投资后:未起息；起息后:到期日期)/html/body/div/div/div/a/div[3]/span[2] */
    public static final By WXAMTP_TEXT_LIST_STATUS_ONE = By.xpath("/html/body/div/div[1]/div/a[1]/div[3]/span[2]/span");

    public static final By WXAMTP_TEXT_LIST_STATUS_TWO = By.xpath("/html/body/div/div/div/a/div[3]/span[2]");

    /** >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>详情页<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< **/

    /* 我的投资详情页-->投入本金 */
    public static final By WXAMTP_TEXT_DETAIL_AMOUNT = By.xpath("/html/body/div[1]/ul/li[3]");

    /* 我的投资详情页-->应收利息 */
    public static final By WXAMTP_TEXT_DETAIL_INTEREST = By.xpath("/html/body/div[1]/ul/li[4]");

    /* 我的投资详情页-->卡券收益 */
    public static final By WXAMTP_TEXT_DETAIL_COUPON = By.xpath("/html/body/div[1]/ul/li[5]");

    /* 我的投资详情页-->补息收益 */
    public static final By WXAMTP_TEXT_DETAIL_BUXI = By.xpath("/html/body/div[1]/ul/li[6]");

    /* 我的投资详情页-->应收总额 */
    public static final By WXAMTP_TEXT_DETAIL_TOTAL = By.xpath("/html/body/div[1]/ul/li[7]");

    /* 我的投资详情页-->还款金额 /html/body/div[2]/ul[2]/li/ul/li[2]  /html/body/div[2]/ul[2]/li   暂未起息 */
    public static final By WXAMTP_TEXT_DETAIL_TOTAL_RECOVER_ONE = By.xpath("/html/body/div[2]/ul[2]/li");

    public static final By WXAMTP_TEXT_DETAIL_TOTAL_RECOVER_TWO = By.xpath("/html/body/div[2]/ul[2]/li/ul/li[2]");

    /* 我的投资详情页-->投资金额 */
    public static final By WXAMTP_TEXT_DETAIL_AMOUNT_RECOVER = By.xpath("//*[@id=\"tenderList\"]/li/ul/li[2]");

    /* 我的投资详情页-->利息+额外收益 */
    public static final By WXAMTP_TEXT_DETAIL_INTEREST_COUPON_BUXI = By.xpath("//*[@id=\"tenderList\"]/li/ul/li[3]");

}
