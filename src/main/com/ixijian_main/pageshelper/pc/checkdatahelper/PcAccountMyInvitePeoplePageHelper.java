package ixijian_main.pageshelper.pc.checkdatahelper;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.checkdata.PcAccountMyInvitePeoplePage;
import org.testng.log4testng.Logger;
import utils.SeleniumUtil;

public class PcAccountMyInvitePeoplePageHelper extends BaseParpareTestPc {

    public static Logger logger = Logger.getLogger(PcAccountMyInvitePeoplePageHelper.class);

    /**
     * 等待[我的--我的推荐]页面元素加载
     */
    public static void waitPcAccountMyInvitePeoplePageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查[我的--我的推荐]页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyInvitePeoplePage.PAMIPP_TEXT_TOTAL_PEOPLE);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyInvitePeoplePage.PAMIPP_TEXT_TOTAL_AMOUNT);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyInvitePeoplePage.PAMIPP_TEXT_INVITE_USERNAME);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyInvitePeoplePage.PAMIPP_TEXT_INVITE_PHONE);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyInvitePeoplePage.PAMIPP_TEXT_REGISTER_TIME);
        seleniumUtil.waitForElementToLoad(timeOut, PcAccountMyInvitePeoplePage.PAMIPP_TEXT_STATUS);
        logger.info("检查[我的--我的推荐]页面元素完毕");
    }

    /**
     * 检查[我的--我的推荐]数据
     */
    public static void typePcAccountMyInvitePeoplePageInfo(SeleniumUtil seleniumUtil,String totalPeople,String amount,String inviteUsername,String invitePhone,String registerTime,String status){
        logger.info("开始校验[我的--我的推荐]");
        seleniumUtil.isTextCorrect(PcAccountMyInvitePeoplePage.PAMIPP_TEXT_TOTAL_PEOPLE.toString(),totalPeople);
        seleniumUtil.isTextCorrect(PcAccountMyInvitePeoplePage.PAMIPP_TEXT_TOTAL_AMOUNT.toString(),amount);
        seleniumUtil.isTextCorrect(PcAccountMyInvitePeoplePage.PAMIPP_TEXT_INVITE_USERNAME.toString(),inviteUsername);
        seleniumUtil.isTextCorrect(PcAccountMyInvitePeoplePage.PAMIPP_TEXT_INVITE_PHONE.toString(),invitePhone);
        seleniumUtil.isTextCorrect(PcAccountMyInvitePeoplePage.PAMIPP_TEXT_REGISTER_TIME.toString(),registerTime);
        seleniumUtil.isTextCorrect(PcAccountMyInvitePeoplePage.PAMIPP_TEXT_STATUS.toString(),status);
        logger.info("校验[我的--我的推荐]完毕");
    }
}
