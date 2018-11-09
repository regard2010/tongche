package ixijian_test.wx.check;

/**
 * 2018-02-23 更新等保版单用户单笔投资完整流程（无推荐人、无积分）
 * UI浏览器测试调用范例，可以直接用于参考复制UI测试
 */

import ixijian_main.api.tender.TenderAPIFlow;
import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.pageshelper.wx.checkdatahelper.WeiXinAccountMyTenderPageHelper;
import ixijian_main.pageshelper.wx.checkdatahelper.WeiXinAccountMyTradeDetailPageHelper;
import ixijian_main.pageshelper.wx.checkdatahelper.WeiXinAccountPreviewPageHelper;
import ixijian_test.wx.TenderFlowWeiXinPage_001_TenderSuccessFunction_Test;
import org.testng.annotations.Test;
import utils.MySqlUtil;
import utils.TestUrl;


public class OldCheckTenderDataWeiXinThreeMonth_001_TenderSuccessFunction_Test extends BaseParpareTestAdmin {

	String source = "ixijian_dev_encrypt";
	String source_dev = "ixijian_dev";
	TenderFlowWeiXinPage_001_TenderSuccessFunction_Test tenderFlowWeiXinPage_001_tenderSuccessFunction_test = new TenderFlowWeiXinPage_001_TenderSuccessFunction_Test();
	@Test
	public void tenderFlowWeiXinThreeMonth(){
		/**
		 * 初始化:投资流程
		 */
		//投资流程
		tenderFlowWeiXinPage_001_tenderSuccessFunction_test.tenderFlowWeiXinSuccessFunction
				(seleniumUtil,source,"100000.18","17999999944","111111",
						"223","1000","24.5","加息券3.1%","20",
						"1000","24.5","7.75","1032.25");
		//获得borrowId
		String borrowId = MySqlUtil.getInstance().mySqlCURD_URI(source,"SELECT `borrow_id` FROM `borrow` ORDER BY `borrow_id` DESC LIMIT 1;");
		//获得标的名称
		String title = MySqlUtil.getInstance().mySqlCURD_URI(source,"SELECT `borrow_name` FROM `borrow` WHERE `borrow_id` = " + borrowId + ";");

		/**
		 * 第一轮检查:投资成功后数据
		 */
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"99,000.18","100,000.18","0.00","20","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/investment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"1,000.00","0.00","未起息");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"1,000.00","0.00","0.00","0.00","1,000.00","暂未起息","1,000","0.00+0.00");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageInfo(seleniumUtil,"投资冻结","1,000.00");
		//我的--我的推荐
//		WeiXinAccountMyInvitePeoplePageHelper.waitWeiXinAccountMyInvitePeoplePageLoad(seleniumUtil,timeOut);
//		WeiXinAccountMyInvitePeoplePageHelper.typeWeiXinAccountMyInvitePeoplePageInfo(seleniumUtil,"","","","","","");




		/**
		 * 第二轮:起息
		 */
		seleniumUtil.setBrowserSize(1600,900);
		seleniumUtil.pause(1000);
		//开始起息
		TenderAPIFlow tenderAPIFlow = new TenderAPIFlow();
		tenderAPIFlow.startInterest(seleniumUtil,borrowId);
		seleniumUtil.setBrowserSize(400,800);



		/**
		 * 第二轮检查:起息成功后数据
		 */
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"99,000.18","100,032.70","1,032.52","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/investment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"1,000.00","32.52","到期时间：");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"1,000.00","24.50","7.75","0.27","1,032.52","1,032.52","1,000","24.50+8.02");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepTwoLoad(seleniumUtil,timeOut,"1");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepTwoLoad(seleniumUtil,timeOut,"2");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepTwoInfo(seleniumUtil,"投资成功","-1,000.00","1");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepTwoInfo(seleniumUtil,"投资冻结","1,000.00","2");
		//我的--我的推荐
//		WeiXinAccountMyInvitePeoplePageHelper.waitWeiXinAccountMyInvitePeoplePageLoad(seleniumUtil,timeOut);
//		WeiXinAccountMyInvitePeoplePageHelper.typeWeiXinAccountMyInvitePeoplePageInfo(seleniumUtil,"","","","","","");



		/**
		 * 第三轮:还款
		 */
		seleniumUtil.setBrowserSize(1600,900);
		seleniumUtil.pause(1000);
		tenderAPIFlow.startRepay(seleniumUtil,borrowId);
		seleniumUtil.setBrowserSize(400,800);


		/**
		 * 第三轮检查:还款成功后数据
		 */
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"100,032.70","100,032.70","0.00","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/ainvestment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"1,000.00","32.52","到期时间：");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"1,000.00","24.50","7.75","0.27","1,032.52","1,032.52","1,000","24.50+8.02");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"1");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"2");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"3");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"4");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"5");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"补息收益","＋0.27","1");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"加息券收益","＋7.75","2");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"收回本息","＋1,024.50","3");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"投资成功","-1,000.00","4");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"投资冻结","1,000.00","5");
		//我的--我的推荐
//		WeiXinAccountMyInvitePeoplePageHelper.waitWeiXinAccountMyInvitePeoplePageLoad(seleniumUtil,timeOut);
//		WeiXinAccountMyInvitePeoplePageHelper.typeWeiXinAccountMyInvitePeoplePageInfo(seleniumUtil,"","","","","","");
	}
}








































