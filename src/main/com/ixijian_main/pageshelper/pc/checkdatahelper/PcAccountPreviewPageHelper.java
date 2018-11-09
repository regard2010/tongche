package ixijian_main.pageshelper.pc.checkdatahelper;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.checkdata.PcAccountPreviewPage;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;

public class PcAccountPreviewPageHelper  extends BaseParpareTestPc {

    public static Logger logger = Logger.getLogger(PcAccountPreviewPageHelper.class);

    /**
     * 等待[我的--账户总览]页面元素加载
     */
    public static void waitPcAccountPreviewPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--账户总览]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountPreviewPage.PAP_TEXT_BALANCE);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountPreviewPage.PAP_TEXT_TOTAL);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountPreviewPage.PAP_TEXT_RECEIVE);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountPreviewPage.PAP_TEXT_USER_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountPreviewPage.PAP_TEXT_USER_RECEIVE);
        logger.info("检查[我的--账户总览]页面元素完毕");
    }

    /**
     * 检查[我的--账户总览]数据
     * @param seleniumUtil
     * @param balance
     * @param total
     * @param receive
     */
    public static void typePcAccountPreviewPageInfo(SeleniumUtil seleniumUtil,String balance,String total,String receive,String userAmount,String userReceive){
        logger.info("开始校验[我的--账户总览]");
        seleniumUtil.isTextCorrect(PcAccountPreviewPage.PAP_TEXT_BALANCE.toString(),balance);
        seleniumUtil.isTextCorrect(PcAccountPreviewPage.PAP_TEXT_TOTAL.toString(),total);
        seleniumUtil.isTextCorrect(PcAccountPreviewPage.PAP_TEXT_RECEIVE.toString(),receive);
        seleniumUtil.isTextCorrect(PcAccountPreviewPage.PAP_TEXT_USER_AMOUNT.toString(),userAmount);
        seleniumUtil.isTextCorrect(PcAccountPreviewPage.PAP_TEXT_USER_RECEIVE.toString(),userReceive);
        logger.info("校验[我的--账户总览]完毕");
    }
}
