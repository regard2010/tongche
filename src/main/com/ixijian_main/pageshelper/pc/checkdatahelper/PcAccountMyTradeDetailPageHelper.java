package ixijian_main.pageshelper.pc.checkdatahelper;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.checkdata.PcAccountMyTradeDetailPage;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;

public class PcAccountMyTradeDetailPageHelper extends BaseParpareTestPc {

    public static Logger logger = Logger.getLogger(PcAccountMyTradeDetailPageHelper.class);

    /**
     * 等待[我的--交易记录]页面元素加载
     */
    public static void waitPcAccountMyTradeDetailPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--交易记录]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTradeDetailPage.PAMTDP_TEXT_TENDER_TYPE);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTradeDetailPage.PAMTDP_TEXT_CHANGE);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTradeDetailPage.PAMTDP_TEXT_BALANCE);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTradeDetailPage.PAMTDP_TEXT_REMARKS);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyTradeDetailPage.PAMTDP_TEXT_PAGE_NUM);
        logger.info("检查[我的--交易记录]页面元素完毕");
    }

    /**
     * 检查[我的--交易记录]数据
     */
    public static void typePcAccountMyTradeDetailPageInfo(SeleniumUtil seleniumUtil,String tenderType,String change,String balance,String remarks,String pageNum){
        logger.info("开始校验[我的--交易记录]");
        seleniumUtil.isTextCorrect(PcAccountMyTradeDetailPage.PAMTDP_TEXT_TENDER_TYPE.toString(),tenderType);
        seleniumUtil.isTextCorrect(PcAccountMyTradeDetailPage.PAMTDP_TEXT_CHANGE.toString(),change);
        seleniumUtil.isTextCorrect(PcAccountMyTradeDetailPage.PAMTDP_TEXT_BALANCE.toString(),balance);
        seleniumUtil.isTextCorrect(PcAccountMyTradeDetailPage.PAMTDP_TEXT_REMARKS.toString(),remarks);
        seleniumUtil.isTextCorrect(PcAccountMyTradeDetailPage.PAMTDP_TEXT_PAGE_NUM.toString(),pageNum);
        logger.info("校验[我的--交易记录]完毕");
    }
}
