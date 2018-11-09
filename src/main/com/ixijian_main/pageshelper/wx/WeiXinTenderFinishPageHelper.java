package ixijian_main.pageshelper.wx;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.WeiXinTenderFinishPage;
import org.apache.log4j.Logger;
import utils.SeleniumUtil;

public class WeiXinTenderFinishPageHelper extends BaseParpareTestWeiXin {

    public static Logger logger = Logger.getLogger(WeiXinTenderFinishPageHelper.class);

    /**
     * 等待投资完成页面元素加载
     */
    public static void waitWeiXinTenderFinishLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);

        logger.info("开始检查投资完成页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderFinishPage.TF_TEXT_IS_SUCCESS);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderFinishPage.TF_TEXT_POINTS);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderFinishPage.TF_BUTTON_POINTS);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderFinishPage.TF_BUTTON_TENDER);
        logger.info("检查投资完成页面元素完毕");
    }

    /**
     * @description 投资完成页面封装
     * @param seleniumUtil selenium api封装引用对象
     * @param points 填写[获得积分]
     * */
    public static void typeWeiXinTenderFinishInfo(SeleniumUtil seleniumUtil,String isSuccess,String points){
        logger.info(">>>>>>>>>>开始检查投资完成页面，是否投资成功和积分");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderFinishPage.TF_TEXT_IS_SUCCESS),isSuccess);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderFinishPage.TF_TEXT_POINTS),points);
        logger.info(">>>>>>>>>>投资完成页面：是否投资成功和积分检查完毕");
        seleniumUtil.click(WeiXinTenderFinishPage.TF_BUTTON_TENDER);
        logger.info(">>>>>>>>>>点击【继续投资】完成");
    }


    /**
     * @description 验证投资确认页相关错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
    public static void checkWeiXinTenderFinishErrorInfo(SeleniumUtil seleniumUtil,String error){
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderFinishPage.TF_TEXT_ERROR), error);
    }
}
