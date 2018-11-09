package ixijian_test.wx;

/**
 * UI浏览器测试调用范例，可以直接用于参考复制UI测试
 * 1、动态配置参数:
 * 手机号phone 手机密码 password 卡券id couponId 投资金额 amount 预期收入 income 使用卡券的面值 useCoupon 积分 points
 */

import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.defaultdata.DefaultAccount;
import ixijian_main.pageshelper.wx.*;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.SeleniumUtil;
import utils.TestUrl;

public class TenderFlowWeiXinPage_001_TenderSuccessFunction_Test extends BaseParpareTestAdmin {
	private static Logger logger = Logger.getLogger(TenderFlowWeiXinPage_001_TenderSuccessFunction_Test.class);

	@Parameters({"phone","password","couponId","amount","income","useCoupon","points"})
	@Test
	public void tenderFlowWeiXinSuccessFunction(String phone, String source,String password, String couponId, String amount, String income, String useCoupon, String points){
		DefaultAccount defaultAccount = new DefaultAccount();
		defaultAccount.addCoupon(seleniumUtil,source,phone,couponId);
		seleniumUtil.pause(1000);
		//登录页面加载
		seleniumUtil.get(TestUrl.getWXOldURL() + "/user/AdminLoginInterface.htm");
		seleniumUtil.pause(1000);
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, phone, password);
		//等待页面加载完成
		seleniumUtil.pause(1000);
		//投资列表页加载
		seleniumUtil.get(TestUrl.getWXOldURL() + "/borrow/index.htm");
		WeiXinTenderListPageHelper.waitWeiXinTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinTenderListPageHelper.typeWeiXinTenderListInfo(seleniumUtil);
		seleniumUtil.pause(1000);
		//投资详情页加载
		WeiXinTenderDetailPageHelper.waitWeiXinTenderDetailPageLoad(seleniumUtil,timeOut);
		WeiXinTenderDetailPageHelper.typeWeiXinTenderDetailInfo(seleniumUtil,amount);
		seleniumUtil.pause(1000);
		WeiXinTenderDetailPageHelper.waitWeiXinTenderChooseCoupon(seleniumUtil,timeOut);
		WeiXinTenderDetailPageHelper.typeWeiXinTenderChooseCoupon(seleniumUtil,income,useCoupon);
		seleniumUtil.pause(1000);
		WeiXinTenderConfirmPageHelper.waitWeiXinTenderConfirmPagePayLoad(seleniumUtil,timeOut);
		WeiXinTenderConfirmPageHelper.typeWeiXinTenderConfirmPagePayLoad(seleniumUtil);
		seleniumUtil.pause(1000);
		//投资完成页加载
		WeiXinTenderFinishPageHelper.waitWeiXinTenderFinishLoad(seleniumUtil,timeOut);
		WeiXinTenderFinishPageHelper.typeWeiXinTenderFinishInfo(seleniumUtil,"恭喜您,投资成功!","获得" + points + "个积分");
		seleniumUtil.isTextCorrect(seleniumUtil.getTitle(),"标的列表");
		seleniumUtil.pause(1000);
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);
	}

	public void tenderFlowWeiXinSuccessFunction(SeleniumUtil seleniumUtil, String source, String totalMoney, String phone, String password, String couponId, String amount, String income, String useCoupon, String points,
												String couponAmount, String couponIncome, String couponCoupon, String couponPay){
		DefaultAccount defaultAccount = new DefaultAccount();
		defaultAccount.defaultAccount(phone,source,totalMoney);
		defaultAccount.addCoupon(seleniumUtil,source,phone,couponId);
		seleniumUtil.pause(1000);
		seleniumUtil.setBrowserSize(400,800);
		//登录页面加载
		seleniumUtil.get(TestUrl.getWXOldURL() + "/user/AdminLoginInterface.htm");
		seleniumUtil.pause(1000);
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, phone, password);
		//投资列表页加载
		seleniumUtil.pause(2000);
		seleniumUtil.get(TestUrl.getWXOldURL() + "/borrow/index.htm");
		seleniumUtil.pause(1000);
		WeiXinTenderListPageHelper.waitWeiXinTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinTenderListPageHelper.typeWeiXinTenderListInfo(seleniumUtil);
		//投资详情页加载
		WeiXinTenderDetailPageHelper.waitWeiXinTenderDetailPageLoad(seleniumUtil,timeOut);
		WeiXinTenderDetailPageHelper.typeWeiXinTenderDetailInfo(seleniumUtil,amount);
		WeiXinTenderDetailPageHelper.waitWeiXinTenderChooseCoupon(seleniumUtil,timeOut);
		WeiXinTenderDetailPageHelper.typeWeiXinTenderChooseCoupon(seleniumUtil,income,useCoupon);
		//投资确认页加载
		WeiXinTenderConfirmPageHelper.waitWeiXinTenderConfirmPageLoad(seleniumUtil,timeOut);
		WeiXinTenderConfirmPageHelper.typeWeiXinTenderConfirmInfo(seleniumUtil,couponAmount,couponIncome,couponCoupon,couponPay);
		WeiXinTenderConfirmPageHelper.waitWeiXinTenderConfirmPagePayLoad(seleniumUtil,timeOut);
		WeiXinTenderConfirmPageHelper.typeWeiXinTenderConfirmPagePayLoad(seleniumUtil);
		//投资完成页加载
		WeiXinTenderFinishPageHelper.waitWeiXinTenderFinishLoad(seleniumUtil,timeOut);
		WeiXinTenderFinishPageHelper.typeWeiXinTenderFinishInfo(seleniumUtil,"恭喜您,投资成功!","获得" + points + "个积分");
		seleniumUtil.isTextCorrect(seleniumUtil.getTitle(),"标的列表");
		//退出登录
//		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);
	}

}
