package ixijian_main.pageshelper.pc;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.TenderDetailPage;
import org.apache.log4j.Logger;
import utils.SeleniumUtil;

public class TenderDetailPageHelper extends BaseParpareTestPc {

    public static Logger logger = Logger.getLogger(TenderDetailPageHelper.class);

    /**
     * 等待投资详情页面元素加载
     */
    public static void waitPcTenderDetailPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);

        logger.info("开始检查投资详情页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, TenderDetailPage.TD_INPUT_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, TenderDetailPage.TD_BUTTON_TENDER);
        logger.info("检查投资详情页面元素完毕");
    }

    /**
     * @description 投资列表页面封装
     * @param seleniumUtil selenium api封装引用对象
     * @param amount 填写投资金额
     * */
    public static void typePcTenderDetailInfo(SeleniumUtil seleniumUtil,String amount){
        logger.info("开始填写投资详情页面");
        seleniumUtil.clear(TenderDetailPage.TD_INPUT_AMOUNT);
        seleniumUtil.type(TenderDetailPage.TD_INPUT_AMOUNT,amount);
        logger.info("投资详情页面填写完毕");
        seleniumUtil.click(TenderDetailPage.TD_BUTTON_TENDER);
    }


    /**
     * @description 验证投资详情页相关错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
    public static void checkPcTenderDetailErrorInfo(SeleniumUtil seleniumUtil,String error){
        seleniumUtil.isTextCorrect(seleniumUtil.getText(TenderDetailPage.TD_TEXT_ERROR), error);
    }
}
