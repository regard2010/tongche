package ixijian_main.pageshelper.pc;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.TenderConfirmPage;
import org.apache.log4j.Logger;
import utils.SeleniumUtil;

public class TenderConfirmPageHelper extends BaseParpareTestPc {

    public static Logger logger = Logger.getLogger(TenderConfirmPageHelper.class);

    /**
     * 等待投资确认页面元素加载
     */
    public static void waitPcTenderConfirmPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);

        logger.info("开始检查投资详情页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, TenderConfirmPage.TC_INPUT_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, TenderConfirmPage.TC_INPUT_COUPON);
        seleniumUtil.waitForElementToLoad(timeOut, TenderConfirmPage.TC_BUTTON_TENDER);
        logger.info("检查投资详情页面元素完毕");
    }

    /**
     * @description 投资确认页面封装
     * @param seleniumUtil selenium api封装引用对象
     * @param amount 填写投资金额
     * */
    public static void typePcTenderConfirmInfo(SeleniumUtil seleniumUtil,String amount,String coupon){
        logger.info("开始填写投资确认页面");
        seleniumUtil.clear(TenderConfirmPage.TC_INPUT_AMOUNT);
        seleniumUtil.type(TenderConfirmPage.TC_INPUT_AMOUNT,amount);
        seleniumUtil.clear(TenderConfirmPage.TC_INPUT_COUPON);
        seleniumUtil.type(TenderConfirmPage.TC_INPUT_COUPON,coupon);
        logger.info("投资确认页面填写完毕");
        seleniumUtil.click(TenderConfirmPage.TC_BUTTON_TENDER);
    }


    /**
     * @description 验证投资确认页相关错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
    public static void checkPcTenderConfirmErrorInfo(SeleniumUtil seleniumUtil,String error){
        seleniumUtil.isTextCorrect(seleniumUtil.getText(TenderConfirmPage.TC_TEXT_ERROR), error);
    }
}
