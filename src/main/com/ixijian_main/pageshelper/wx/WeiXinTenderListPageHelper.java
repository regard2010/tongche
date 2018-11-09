package ixijian_main.pageshelper.wx;

import ixijian_main.base.BaseParpareTestWeiXin;
import ixijian_main.pages.wx.WeiXinTenderListPage;
import org.apache.log4j.Logger;
import utils.SeleniumUtil;

public class WeiXinTenderListPageHelper extends BaseParpareTestWeiXin {

    public static Logger logger = Logger.getLogger(WeiXinTenderListPageHelper.class);

    /**
     * 等待投资列表页面元素加载
     */
    public static void waitWeiXinTenderListPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);
        logger.info("开始检查投资列表页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, WeiXinTenderListPage.TL_BUTTON_TENDER);
        logger.info("检查投资列表页面元素完毕");
    }

    /**
     * @description 投资列表页面封装
     * @param seleniumUtil selenium api封装引用对象
     * */
    public static void typeWeiXinTenderListInfo(SeleniumUtil seleniumUtil){
        seleniumUtil.pause(1000);
        logger.info("开始操作投资列表页面");
        seleniumUtil.click(WeiXinTenderListPage.TL_BUTTON_TENDER);
        logger.info("投资列表页面操作完毕");
    }
}
