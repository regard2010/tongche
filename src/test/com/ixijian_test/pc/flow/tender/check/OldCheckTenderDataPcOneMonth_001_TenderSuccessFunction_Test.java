package ixijian_test.pc.flow.tender.check;

/**
 * UI浏览器测试调用范例，可以直接用于参考复制UI测试
 */

import ixijian_main.api.tender.TenderAPIFlow;
import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.defaultdata.DefaultAccount;
import ixijian_main.pageshelper.login.AdminLoginPageHelper;
import ixijian_main.pageshelper.pc.*;
import ixijian_main.pageshelper.pc.checkdatahelper.PcAccountMyInvitePeoplePageHelper;
import ixijian_main.pageshelper.pc.checkdatahelper.PcAccountMyTenderPageHelper;
import ixijian_main.pageshelper.pc.checkdatahelper.PcAccountMyTradeDetailPageHelper;
import ixijian_main.pageshelper.pc.checkdatahelper.PcAccountPreviewPageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class OldCheckTenderDataPcOneMonth_001_TenderSuccessFunction_Test extends BaseParpareTestPc {

	String source = "ixijian_dev";
	DefaultAccount defaultAccount = new DefaultAccount();
	@Parameters({"phone","strategy_code_id"})
	@Test
	public void tenderFlowPcOneMonth(String phone,String strategy_code_id){
		//初始化账户数据
		defaultAccount.defaultAccount(phone,source,"100000.18");
		/**
		 * 第一轮:投资流程
		 */
		//登录页面加载
		PcLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		PcLoginPageHelper.typeLoginInfo(seleniumUtil, phone, "123456");
		//等待页面加载完成
		seleniumUtil.pause(1000);
		//用户告知弹出框关闭
		PcLoginPageHelper.waitLoginLayerTitleLoad(seleniumUtil,timeOut);
		PcLoginPageHelper.typeLoginLayerTitleInfo(seleniumUtil);
		//投资列表页加载*******************标的id获取方式需要拿到*****************
		TenderListPageHelper.waitPcTenderListPageLoad(seleniumUtil,timeOut);
		TenderListPageHelper.typePcTenderListInfo(seleniumUtil,"","","","");
		String borrowId = "";
		//投资详情页加载
		TenderDetailPageHelper.waitPcTenderDetailPageLoad(seleniumUtil,timeOut);
		TenderDetailPageHelper.typePcTenderDetailInfo(seleniumUtil,"100");
		//投资确认页加载
		TenderConfirmPageHelper.waitPcTenderConfirmPageLoad(seleniumUtil,timeOut);
		TenderConfirmPageHelper.typePcTenderConfirmInfo(seleniumUtil,"","");
		//投资完成页加载
		TenderFinishPageHelper.waitPcTenderFinishLoad(seleniumUtil,timeOut);
		TenderFinishPageHelper.typePcTenderFinishInfo(seleniumUtil,"","","");



		/**
		 * 第一轮检查:投资成功后数据
		 */
		//我的--账户总览
		PcAccountPreviewPageHelper.waitPcAccountPreviewPageLoad(seleniumUtil,timeOut);
		PcAccountPreviewPageHelper.typePcAccountPreviewPageInfo(seleniumUtil,"","","","","");
		//我的--我的投资
		PcAccountMyTenderPageHelper.waitPcAccountMyTenderPageLoad(seleniumUtil,timeOut);
		PcAccountMyTenderPageHelper.typePcAccountMyTenderPageInfo(seleniumUtil,"","","","","");
		//我的--交易记录(每条记录检查一次)
		PcAccountMyTradeDetailPageHelper.waitPcAccountMyTradeDetailPageLoad(seleniumUtil,timeOut);
		PcAccountMyTradeDetailPageHelper.typePcAccountMyTradeDetailPageInfo(seleniumUtil,"","","","","");
		//我的--我的推荐
		PcAccountMyInvitePeoplePageHelper.waitPcAccountMyInvitePeoplePageLoad(seleniumUtil,timeOut);
		PcAccountMyInvitePeoplePageHelper.typePcAccountMyInvitePeoplePageInfo(seleniumUtil,"","","","","","");
		//退出登录
		PcLoginPageHelper.logOutPc(seleniumUtil);



		/**
		 * 第二轮:起息
		 */
		seleniumUtil.pause(2000);
		//等待登录页面加载
		AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil, timeOut);
		//输入登录信息
		AdminLoginPageHelper.typeAdminInfo(seleniumUtil, "admin", "1234ad");
		//等待页面加载完成
		seleniumUtil.pause(2000);
		//开始起息
		TenderAPIFlow tenderAPIFlow = new TenderAPIFlow();
		tenderAPIFlow.startInterest(seleniumUtil,borrowId);
		AdminLoginPageHelper.logOutAdmin(seleniumUtil);



		/**
		 * 第二轮检查:起息成功后数据
		 */
		//我的--账户总览
		PcAccountPreviewPageHelper.waitPcAccountPreviewPageLoad(seleniumUtil,timeOut);
		PcAccountPreviewPageHelper.typePcAccountPreviewPageInfo(seleniumUtil,"","","","","");
		//我的--我的投资
		PcAccountMyTenderPageHelper.waitPcAccountMyTenderPageLoad(seleniumUtil,timeOut);
		PcAccountMyTenderPageHelper.typePcAccountMyTenderPageInfo(seleniumUtil,"","","","","");
		//我的--交易记录(每条记录检查一次)
		PcAccountMyTradeDetailPageHelper.waitPcAccountMyTradeDetailPageLoad(seleniumUtil,timeOut);
		PcAccountMyTradeDetailPageHelper.typePcAccountMyTradeDetailPageInfo(seleniumUtil,"","","","","");
		//我的--我的推荐
		PcAccountMyInvitePeoplePageHelper.waitPcAccountMyInvitePeoplePageLoad(seleniumUtil,timeOut);
		PcAccountMyInvitePeoplePageHelper.typePcAccountMyInvitePeoplePageInfo(seleniumUtil,"","","","","","");
		//退出登录
		PcLoginPageHelper.logOutPc(seleniumUtil);



		/**
		 * 第三轮:还款
		 */
		seleniumUtil.pause(2000);
		//等待登录页面加载
		AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil, timeOut);
		//输入登录信息
		AdminLoginPageHelper.typeAdminInfo(seleniumUtil, "admin", "1234ad");
		//等待页面加载完成
		seleniumUtil.pause(2000);
		tenderAPIFlow.startRepay(seleniumUtil,borrowId);
		AdminLoginPageHelper.logOutAdmin(seleniumUtil);



		/**
		 * 第三轮检查:还款成功后数据
		 */
		//我的--账户总览
		PcAccountPreviewPageHelper.waitPcAccountPreviewPageLoad(seleniumUtil,timeOut);
		PcAccountPreviewPageHelper.typePcAccountPreviewPageInfo(seleniumUtil,"","","","","");
		//我的--我的投资
		PcAccountMyTenderPageHelper.waitPcAccountMyTenderPageLoad(seleniumUtil,timeOut);
		PcAccountMyTenderPageHelper.typePcAccountMyTenderPageInfo(seleniumUtil,"","","","","");
		//我的--交易记录(每条记录检查一次)
		PcAccountMyTradeDetailPageHelper.waitPcAccountMyTradeDetailPageLoad(seleniumUtil,timeOut);
		PcAccountMyTradeDetailPageHelper.typePcAccountMyTradeDetailPageInfo(seleniumUtil,"","","","","");
		//我的--我的推荐
		PcAccountMyInvitePeoplePageHelper.waitPcAccountMyInvitePeoplePageLoad(seleniumUtil,timeOut);
		PcAccountMyInvitePeoplePageHelper.typePcAccountMyInvitePeoplePageInfo(seleniumUtil,"","","","","","");
		//退出登录
		PcLoginPageHelper.logOutPc(seleniumUtil);
	}
}








































