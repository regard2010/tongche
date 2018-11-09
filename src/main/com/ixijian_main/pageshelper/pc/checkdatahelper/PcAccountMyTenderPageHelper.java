package ixijian_main.pageshelper.pc.checkdatahelper;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.checkdata.PcAccountMyTenderPage;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;

public class PcAccountMyTenderPageHelper extends BaseParpareTestPc {

    public static Logger logger = Logger.getLogger(PcAccountMyTenderPageHelper.class);

    /**
     * 等待[我的--我的投资]页面元素加载
     */
    public static void waitPcAccountMyTenderPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--我的投资]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTenderPage.PAMTP_TEXT_FBT);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTenderPage.PAMTP_TEXT_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTenderPage.PAMTP_TEXT_INTEREST);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTenderPage.PAMTP_TEXT_END_DATE);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTenderPage.PAMTP_TEXT_STATUS);
        logger.info("检查[我的--我的投资]页面元素完毕");
    }

    /**
     * 检查[我的--我的投资]数据
     */
    public static void typePcAccountMyTenderPageInfo(SeleniumUtil seleniumUtil,String fuTitle,String amount,String interest,String endDate,String status){
        logger.info("开始校验[我的--我的投资]");
        seleniumUtil.isTextCorrect(PcAccountMyTenderPage.PAMTP_TEXT_FBT.toString(),fuTitle);
        seleniumUtil.isTextCorrect(PcAccountMyTenderPage.PAMTP_TEXT_AMOUNT.toString(),amount);
        seleniumUtil.isTextCorrect(PcAccountMyTenderPage.PAMTP_TEXT_INTEREST.toString(),interest);
        seleniumUtil.isTextCorrect(PcAccountMyTenderPage.PAMTP_TEXT_END_DATE.toString(),endDate);
        seleniumUtil.isTextCorrect(PcAccountMyTenderPage.PAMTP_TEXT_STATUS.toString(),status);
        logger.info("校验[我的--我的投资]完毕");
    }
}
