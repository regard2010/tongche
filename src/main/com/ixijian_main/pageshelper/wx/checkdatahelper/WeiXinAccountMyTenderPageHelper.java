package ixijian_main.pageshelper.wx.checkdatahelper;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.checkdata.WeiXinAccountMyTenderPage;
import org.openqa.selenium.By;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;
import utils.TestUrl;

public class WeiXinAccountMyTenderPageHelper extends BaseParpareTestWeiXin {

    public static Logger logger = Logger.getLogger(WeiXinAccountMyTenderPageHelper.class);

    /**
     * 等待[我的--我的投资列表页]页面元素加载
     */
    public static void waitWeiXinAccountMyTenderListPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--我的投资列表页]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_FBT);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_INTEREST);
        seleniumUtil.refresh();
        seleniumUtil.pause(5000);
        if(seleniumUtil.findElementBy(WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_STATUS_ONE).getText().equals("未起息")){
            seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_STATUS_ONE);
        }else{
            seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_STATUS_TWO);
        }
        logger.info("检查[我的--我的投资列表页]页面元素完毕");
    }

    /**
     * 等待[我的--我的投资详情页]页面元素加载
     */
    public static void waitWeiXinAccountMyTenderDetailPageLoad(SeleniumUtil seleniumUtil, int timeOut,String borrowId){
        seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/borrowrecover.htm?borrowId=" + borrowId + "");
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--我的投资详情页]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_INTEREST);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_COUPON);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_BUXI);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_TOTAL);
        if(seleniumUtil.findElementBy(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_TOTAL_RECOVER_ONE).getText().equals("暂未起息")){
            seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_TOTAL_RECOVER_ONE);
        }else {
            seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_TOTAL_RECOVER_TWO);
        }
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_AMOUNT_RECOVER);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_INTEREST_COUPON_BUXI);

        logger.info("检查[我的--我的投资详情页]页面元素完毕");
    }

    /**
     * 检查[我的--我的投资列表页]数据
     */
    public static void typeWeiXinAccountMyTenderListPageInfo(SeleniumUtil seleniumUtil,String fuTitle,String amount,String interest,String status){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始校验[我的--我的投资列表页]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_FBT),fuTitle);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_AMOUNT),"投资金额：" + amount);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_INTEREST),"预期总收益：" + interest);
        if(seleniumUtil.findElementBy(WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_STATUS_ONE).getText().equals("未起息")){
            seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_STATUS_ONE),status);
        }else{
            seleniumUtil.isContains(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_LIST_STATUS_TWO),status);
        }
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>校验[我的--我的投资列表页]完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * 检查[我的--我的投资详情页]数据
     */
    public static void typeWeiXinAccountMyTenderDetailPageInfo(SeleniumUtil seleniumUtil,String amount,String interest,String coupon,String buxi,String total,String totalRecover,String amountRecover,String interest_coupon_buxi){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始校验[我的--我的投资详情页]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_AMOUNT),"投入本金 (元)：" + amount);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_INTEREST),"应收利息 (元)：" + interest);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_COUPON),"卡券收益 (元)：" + coupon);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_BUXI),"补息收益 (元)：" + buxi);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_TOTAL),"应收总额 (元)：" + total);
        if(seleniumUtil.findElementBy(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_TOTAL_RECOVER_ONE).getText().equals("暂未起息")){
            seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_TOTAL_RECOVER_ONE),totalRecover);
        }else {
            seleniumUtil.isContains(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_TOTAL_RECOVER_TWO),totalRecover);
        }
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_AMOUNT_RECOVER),amountRecover);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTenderPage.WXAMTP_TEXT_DETAIL_INTEREST_COUPON_BUXI),interest_coupon_buxi);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>校验[我的--我的投资详情页]完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
