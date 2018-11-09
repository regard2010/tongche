package ixijian_main.pageshelper.wx;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.WeiXinTenderConfirmPage;
import org.apache.log4j.Logger;
import utils.SeleniumUtil;

public class WeiXinTenderConfirmPageHelper extends BaseParpareTestWeiXin {

    public static Logger logger = Logger.getLogger(WeiXinTenderConfirmPageHelper.class);

    /**
     * 等待投资确认页面元素加载
     */
    public static void waitWeiXinTenderConfirmPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查投资确认页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderConfirmPage.TC_TEXT_COUPON_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderConfirmPage.TC_TEXT_COUPON_INCOME);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderConfirmPage.TC_TEXT_COUPON_COUPON);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderConfirmPage.TC_TEXT_COUPON_PAY);
        logger.info("检查投资确认页面元素完毕");
    }

    /**
     * 等待确认支付
     * @param seleniumUtil
     * @param timeOut
     */
    public static void waitWeiXinTenderConfirmPagePayLoad(SeleniumUtil seleniumUtil,int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查确认支付页面元素");
        seleniumUtil.waitForElementToLoad(timeOut,WeiXinTenderConfirmPage.TC_INPUT_COMFIRM_PAY);
        logger.info("检查确认支付页面完毕");
    }

    /**
     * @description 投资确认页面封装
     * @param seleniumUtil selenium api封装引用对象
     * */
    public static void typeWeiXinTenderConfirmInfo(SeleniumUtil seleniumUtil,String couponAmount,String couponIncome,String couponCoupon,String couponPay){
        logger.info(">>>>>>>>>>开始填写投资确认页面");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderConfirmPage.TC_TEXT_COUPON_AMOUNT),couponAmount);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderConfirmPage.TC_TEXT_COUPON_INCOME),couponIncome);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderConfirmPage.TC_TEXT_COUPON_COUPON),couponCoupon);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderConfirmPage.TC_TEXT_COUPON_PAY),couponPay);
        logger.info(">>>>>>>>>>投资确认页面填写完毕");
    }

    /**
     * 手动输入微信支付密码
     * @param seleniumUtil
     */
    public static void typeWeiXinTenderConfirmPagePayLoad(SeleniumUtil seleniumUtil){
        logger.info(">>>>>>>>>>开始提交确认支付页面");
        logger.info(">>>>>>>>>>>>>>>>>>>请勾选页面上的【我同意】，倒计时已开始<<<<<<<<<<<<<<<<<<");
        for(int i = 5 ; i >= 0 ; i--){
            if(i == 0){
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>倒计时结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                break;
            }else{
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>还有"+ i +"秒<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                seleniumUtil.pause(1000);
            }
        }
        seleniumUtil.click(WeiXinTenderConfirmPage.TC_INPUT_COMFIRM_PAY);
        logger.info(">>>>>>>>>>确认支付页面完毕");
        logger.info(">>>>>>>>>>开始手动输入支付密码页面");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>请手动输入支付密码,只有5秒钟,倒计时开始！<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        for(int i = 5 ; i >= 0 ; i--){
            if(i == 0){
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>倒计时结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                break;
            }else{
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>还有"+ i +"秒<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                seleniumUtil.pause(1000);
            }
        }
        logger.info(">>>>>>>>>>手动输入支付密码完成");
    }

    /**
     * @description 验证投资确认页相关错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
    public static void checkWeiXinTenderConfirmErrorInfo(SeleniumUtil seleniumUtil,String error){
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinTenderConfirmPage.TC_TEXT_ERROR), error);
    }
}
