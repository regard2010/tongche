package ixijian_main.pageshelper.wx;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.WeiXinTenderConfirmPage;
import ixijian_main.pages.wx.WeiXinTenderDetailPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtil;

public class WeiXinTenderDetailPageHelper extends BaseParpareTestWeiXin {

    public static Logger logger = Logger.getLogger(WeiXinTenderDetailPageHelper.class);

    /**
     * 等待投资详情页面元素加载
     */
    public static void waitWeiXinTenderDetailPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);

        logger.info("开始检查投资详情页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderDetailPage.TD_INPUT_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderDetailPage.TD_BUTTON_TENDER);
        logger.info("检查投资详情页面元素完毕");
    }

    public static void waitWeiXinTenderChooseCoupon(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查【去支付】按钮");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderConfirmPage.TC_TEXT_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderConfirmPage.TC_TEXT_INCOME);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderConfirmPage.TC_TEXT_COUPON);
        seleniumUtil.waitForElementToLoad(timeOut,WeiXinTenderDetailPage.TD_BUTTON_TENDER_COUPON);
        logger.info("检查【去支付】完毕");
    }

    /**
     * @description 投资列表页面封装
     * @param seleniumUtil selenium api封装引用对象
     * @param amount 填写投资金额
     * */
    public static void typeWeiXinTenderDetailInfo(SeleniumUtil seleniumUtil,String amount){
        seleniumUtil.pause(1000);
        logger.info(">>>>>>>>>>开始填写投资详情页面");
        seleniumUtil.clear(WeiXinTenderDetailPage.TD_INPUT_AMOUNT);
        seleniumUtil.type(WeiXinTenderDetailPage.TD_INPUT_AMOUNT,amount);
        logger.info(">>>>>>>>>>投资详情页面填写完毕");
        seleniumUtil.click(WeiXinTenderDetailPage.TD_BUTTON_TENDER);
    }

    /**
     * 有卡券时，直接点击确认
     * @param seleniumUtil
     */
    public static void typeWeiXinTenderChooseCoupon(SeleniumUtil seleniumUtil,String income,String coupon){
        seleniumUtil.pause(1000);
        logger.info(">>>>>>>>>>选择默认卡券，检查预期收益和卡券收益");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderConfirmPage.TC_TEXT_INCOME),income);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderConfirmPage.TC_TEXT_COUPON),coupon);
        logger.info(">>>>>>>>>>检查预期收益和卡券收益完毕");
        seleniumUtil.click(WeiXinTenderDetailPage.TD_BUTTON_TENDER_COUPON);
        logger.info(">>>>>>>>>>点击【去支付】完成");
    }

    /**
     * @description 验证投资详情页相关错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
    public static void checkWeiXinTenderDetailErrorInfo(SeleniumUtil seleniumUtil,String error){
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderDetailPage.TD_TEXT_ERROR), error);
    }
}
