package ixijian_main.pageshelper.wx.checkdatahelper;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.checkdata.WeiXinAccountMyInvitePeoplePage;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;

public class WeiXinAccountMyInvitePeoplePageHelper extends BaseParpareTestWeiXin {

    public static Logger logger = Logger.getLogger(WeiXinAccountMyInvitePeoplePageHelper.class);

    /**
     * 等待[我的--我的推荐]页面元素加载
     */
    public static void waitWeiXinAccountMyInvitePeoplePageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--我的推荐]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_TOTAL_PEOPLE);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_TOTAL_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_INVITE_USERNAME);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_INVITE_PHONE);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_REGISTER_TIME);
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_STATUS);
        logger.info("检查[我的--我的推荐]页面元素完毕");
    }

    /**
     * 检查[我的--我的推荐]数据
     */
    public static void typeWeiXinAccountMyInvitePeoplePageInfo(SeleniumUtil seleniumUtil,String totalPeople,String amount,String inviteUsername,String invitePhone,String registerTime,String status){
        logger.info("开始校验[我的--我的推荐]");
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_TOTAL_PEOPLE),totalPeople);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_TOTAL_AMOUNT),amount);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_INVITE_USERNAME),inviteUsername);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_INVITE_PHONE),invitePhone);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_REGISTER_TIME),registerTime);
        seleniumUtil.isTextCorrect(seleniumUtil.getText(WeiXinAccountMyInvitePeoplePage.PAMIPP_TEXT_STATUS),status);
        logger.info("校验[我的--我的推荐]完毕");
    }
}
