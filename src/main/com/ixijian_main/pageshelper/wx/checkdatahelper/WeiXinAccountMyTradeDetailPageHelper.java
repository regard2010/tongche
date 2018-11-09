package ixijian_main.pageshelper.wx.checkdatahelper;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.checkdata.WeiXinAccountMyTradeDetailPage;
import org.openqa.selenium.By;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;

public class WeiXinAccountMyTradeDetailPageHelper extends BaseParpareTestWeiXin {

    public static Logger logger = Logger.getLogger(WeiXinAccountMyTradeDetailPageHelper.class);

    /**
     * 等待[我的--交易记录]页面元素加载【投资】后
     */
    public static void waitWeiXinAccountMyTradeDetailPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--交易记录]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTradeDetailPage.PAMTDP_TEXT_TENDER_TYPE);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyTradeDetailPage.PAMTDP_TEXT_CHANGE);
        logger.info("检查[我的--交易记录]页面元素完毕");
    }

    /**
     * 检查[我的--交易记录]数据【投资】后
     */
    public static void typeWeiXinAccountMyTradeDetailPageInfo(SeleniumUtil seleniumUtil,String tenderType,String change){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始校验[我的--交易记录]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTradeDetailPage.PAMTDP_TEXT_TENDER_TYPE),tenderType);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyTradeDetailPage.PAMTDP_TEXT_CHANGE),change);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>校验[我的--交易记录]完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * 等待[我的--交易记录]页面【起息】后
     */
    public static void waitWeiXinAccountMyTradeDetailPageStepTwoLoad(SeleniumUtil seleniumUtil, int timeOut,String num){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--交易记录]页面元素:第" + num + "条");
        seleniumUtil.waitForElementToLoad(timeOut, By.xpath("/html/body/div/ul[2]/li[" + num + "]/ul/li[2]"));
        seleniumUtil.waitForElementToLoad(timeOut, By.xpath("/html/body/div/ul[2]/li[" + num + "]/ul/li[3]"));
        logger.info("检查[我的--交易记录]页面元素完毕");
    }

    /**
     * 检查[我的--交易记录]数据【起息】后
     */
    public static void typeWeiXinAccountMyTradeDetailPageStepTwoInfo(SeleniumUtil seleniumUtil,String tenderType,String change,String num){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始校验[我的--交易记录]:第" + num + "条<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(By.xpath("/html/body/div/ul[2]/li[" + num + "]/ul/li[2]")),tenderType);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(By.xpath("/html/body/div/ul[2]/li[" + num + "]/ul/li[3]")),change);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>校验[我的--交易记录]完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * 等待[我的--交易记录]页面【还款】后
     */
    public static void waitWeiXinAccountMyTradeDetailPageStepThreeLoad(SeleniumUtil seleniumUtil, int timeOut,String num){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--交易记录]页面元素:第" + num + "条");
        seleniumUtil.waitForElementToLoad(timeOut, By.xpath("/html/body/div/ul[2]/li[" + num + "]/ul/li[2]"));
        seleniumUtil.waitForElementToLoad(timeOut, By.xpath("/html/body/div/ul[2]/li[" + num + "]/ul/li[3]"));
        logger.info("检查[我的--交易记录]页面元素完毕");
    }

    /**
     * 检查[我的--交易记录]数据【还款】后
     */
    public static void typeWeiXinAccountMyTradeDetailPageStepThreeInfo(SeleniumUtil seleniumUtil,String tenderType,String change,String num){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始校验[我的--交易记录]:第" + num + "条<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(By.xpath("/html/body/div/ul[2]/li[" + num + "]/ul/li[2]")),tenderType);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(By.xpath("/html/body/div/ul[2]/li[" + num + "]/ul/li[3]")),change);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>校验[我的--交易记录]完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
