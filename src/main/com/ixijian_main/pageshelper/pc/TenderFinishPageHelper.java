package ixijian_main.pageshelper.pc;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.TenderFinishPage;
import org.apache.log4j.Logger;
import utils.SeleniumUtil;

public class TenderFinishPageHelper extends BaseParpareTestPc {

    public static Logger logger = Logger.getLogger(TenderFinishPageHelper.class);

    /**
     * 等待投资完成页面元素加载
     */
    public static void waitPcTenderFinishLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);

        logger.info("开始检查投资完成页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, TenderFinishPage.TF_TEXT_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, TenderFinishPage.TF_TEXT_COUPON);
        seleniumUtil.waitForElementToLoad(timeOut, TenderFinishPage.TF_TEXT_POINTS);
        seleniumUtil.waitForElementToLoad(timeOut, TenderFinishPage.TF_BUTTON_AMOUNT_LOG);
        seleniumUtil.waitForElementToLoad(timeOut, TenderFinishPage.TF_BUTTON_COUPON);
        logger.info("检查投资完成页面元素完毕");
    }

    /**
     * @description 投资完成页面封装
     * @param seleniumUtil selenium api封装引用对象
     * @param amount 填写[投资金额]
     * @param coupon 填写[卡券收益]
     * @param points 填写[获得积分]
     * */
    public static void typePcTenderFinishInfo(SeleniumUtil seleniumUtil,String amount,String coupon,String points){
        logger.info("开始检查投资完成页面");
        seleniumUtil.isTextCorrect(TenderFinishPage.TF_TEXT_AMOUNT.toString(),amount);
        seleniumUtil.isTextCorrect(TenderFinishPage.TF_TEXT_COUPON.toString(),coupon);
        seleniumUtil.isTextCorrect(TenderFinishPage.TF_TEXT_POINTS.toString(),points);
        logger.info("投资完成页面检查完毕");
        seleniumUtil.click(TenderFinishPage.TF_TEXT_AMOUNT);
    }


    /**
     * @description 验证投资确认页相关错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
    public static void checkPcTenderFinishErrorInfo(SeleniumUtil seleniumUtil,String error){
        seleniumUtil.isTextCorrect(seleniumUtil.getText(TenderFinishPage.TF_TEXT_ERROR), error);
    }
}
