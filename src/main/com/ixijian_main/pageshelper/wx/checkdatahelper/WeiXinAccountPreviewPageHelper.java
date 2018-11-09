package ixijian_main.pageshelper.wx.checkdatahelper;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.checkdata.WeiXinAccountPreviewPage;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;

public class WeiXinAccountPreviewPageHelper extends BaseParpareTestWeiXin {

    public static Logger logger = Logger.getLogger(WeiXinAccountPreviewPageHelper.class);

    /**
     * 等待[我的--账户总览]页面元素加载
     */
    public static void waitWeiXinAccountPreviewPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--账户总览]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountPreviewPage.PAP_TEXT_BALANCE);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountPreviewPage.PAP_TEXT_TOTAL);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountPreviewPage.PAP_TEXT_RECEIVE);
//        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountPreviewPage.PAP_TEXT_POINTS);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountPreviewPage.PAP_TEXT_COUPONS);
        logger.info("检查[我的--账户总览]页面元素完毕");
    }

    /**
     * 检查[我的--账户总览]数据
     * @param seleniumUtil
     * @param balance
     * @param total
     * @param receive
     */
    public static void typeWeiXinAccountPreviewPageInfo(SeleniumUtil seleniumUtil,String balance,String total,String receive,String points,String coupons){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始校验[我的--账户总览]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountPreviewPage.PAP_TEXT_BALANCE),balance);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountPreviewPage.PAP_TEXT_TOTAL),total);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountPreviewPage.PAP_TEXT_RECEIVE),receive);
//        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountPreviewPage.PAP_TEXT_POINTS),points + "分");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountPreviewPage.PAP_TEXT_COUPONS),coupons + "张");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>校验[我的--账户总览]完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
