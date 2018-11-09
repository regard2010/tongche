package ixijian_main.pageshelper.pc;

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pages.pc.TenderListPage;
import org.apache.log4j.Logger;
import utils.SeleniumUtil;

public class TenderListPageHelper  extends BaseParpareTestPc {

    public static Logger logger = Logger.getLogger(TenderListPageHelper.class);

    /**
     * 等待投资列表页面元素加载
     */
    public static void waitPcTenderListPageLoad(SeleniumUtil seleniumUtil, int timeOut){
        seleniumUtil.pause(1000);

        logger.info("开始检查投资列表页面元素");
        seleniumUtil.waitForElementToLoad(timeOut, TenderListPage.TL_BUTTON_XiangMuShouYi);
        seleniumUtil.waitForElementToLoad(timeOut, TenderListPage.TL_BUTTON_HuanKuanFangShi);
        seleniumUtil.waitForElementToLoad(timeOut, TenderListPage.TL_BUTTON_XiangMuQiXian);
        seleniumUtil.waitForElementToLoad(timeOut, TenderListPage.TL_BUTTON_XiangMuZhuangTai);
        seleniumUtil.waitForElementToLoad(timeOut, TenderListPage.TL_BUTTON_TENDER);
        logger.info("检查投资列表页面元素完毕");
    }

    /**
     * @description 投资列表页面封装
     * @param seleniumUtil selenium api封装引用对象
     * @param xiangmushouyi 项目收益
     * @param huankuanfangshi 还款方式
     * @param xiangmuqixian 项目期限
     * @param xiangmuzhuangtai 项目状态
     * */
    public static void typePcTenderListInfo(SeleniumUtil seleniumUtil,String xiangmushouyi,String huankuanfangshi,String xiangmuqixian,String xiangmuzhuangtai){
        logger.info("开始筛选投资列表页面");
        seleniumUtil.type(TenderListPage.TL_BUTTON_XiangMuShouYi,xiangmushouyi);
        seleniumUtil.type(TenderListPage.TL_BUTTON_HuanKuanFangShi,huankuanfangshi);
        seleniumUtil.type(TenderListPage.TL_BUTTON_XiangMuQiXian,xiangmuqixian);
        seleniumUtil.type(TenderListPage.TL_BUTTON_XiangMuZhuangTai,xiangmuzhuangtai);
        logger.info("筛选投资列表页面完毕");
        seleniumUtil.click(TenderListPage.TL_BUTTON_TENDER);
    }


    /**
     * @description 验证投资列表页相关错误信息
     * @param seleniumUtil selenium api封装引用对象
     * @param error 错误文本
     * */
    public static void checkPcTenderListErrorInfo(SeleniumUtil seleniumUtil,String error){
        seleniumUtil.isTextCorrect(seleniumUtil.getText(TenderListPage.TL_TEXT_ERROR), error);
    }
}
