package ixijian_test.wx.check;

/**
 * 2018-02-24 更新等保版3用户组合投资完整流程（无推荐人、无积分）
 * 使用方法:
 * 1、执行OldBorrowStepOne_001_OldBorrow_Test.java，*需要输入的地方：验证码
 * 2、修改testng_create_old_borrow.xml中的相关参数来获得需要的标的
 * 3、执行testng_check_old_tender_account.xml，*需要输入的地方：1.同意2.密码输入3.起息时间4.还款验证码
 * UI浏览器测试调用范例，可以直接用于参考复制UI测试
 */

import ixijian_main.api.tender.TenderAPIFlow;
import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.pageshelper.wx.WeiXinLoginPageHelper;
import ixijian_main.pageshelper.wx.checkdatahelper.WeiXinAccountMyTenderPageHelper;
import ixijian_main.pageshelper.wx.checkdatahelper.WeiXinAccountMyTradeDetailPageHelper;
import ixijian_main.pageshelper.wx.checkdatahelper.WeiXinAccountPreviewPageHelper;
import ixijian_test.wx.TenderFlowWeiXinPage_001_TenderSuccessFunction_Test;
import org.testng.annotations.Test;
import utils.MySqlUtil;
import utils.TestUrl;


public class OCTD_WeiXinThreeMonThreeAccount_001_TSF_Test extends BaseParpareTestAdmin {

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
				(seleniumUtil,source,"100000.18","17999999943","111111",
						"223","900","22.05","加息券3.1%","18",
						"900","22.05","6.98","929.03");
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);
		seleniumUtil.get(TestUrl.getAdminURL() + "/portal/getRootMenu.htm");
		seleniumUtil.pause(1000);
		tenderFlowWeiXinPage_001_tenderSuccessFunction_test.tenderFlowWeiXinSuccessFunction
				(seleniumUtil,source,"200000.18","17999999944","111111",
						"432","100","2.45","红包10.18元","2",
						"100","2.45","10.18","112.63");
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);
		seleniumUtil.get(TestUrl.getAdminURL() + "/portal/getRootMenu.htm");
		seleniumUtil.pause(1000);
		tenderFlowWeiXinPage_001_tenderSuccessFunction_test.tenderFlowWeiXinSuccessFunction
				(seleniumUtil,source,"300000.18","17999999945","111111",
						"433","49000","1200.5","礼品券","980",
						"49000","1200.5","0","50200.5");
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);
		//获得borrowId
		String phone = MySqlUtil.getInstance().mySqlCURD_URI(source,"SELECT to_base64(AES_ENCRYPT('17999999943', '8e11000c294ed406'));");
		String borrowId = MySqlUtil.getInstance().mySqlCURD_URI(source,"SELECT `borrow_id` FROM `borrow_tender` WHERE " +
				"`user_id` = (SELECT `user_id` FROM `user` WHERE `phone` = '" + phone + "') ORDER BY `borrow_tender_id` DESC LIMIT 1;");
//		String borrowId = "2247";
		//获得标的名称
		String title = MySqlUtil.getInstance().mySqlCURD_URI(source,"SELECT `borrow_name` FROM `borrow` WHERE `borrow_id` = " + borrowId + ";");

		/**
		 * 第一轮检查:投资成功后数据:
		 * 17999999943
		 */
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999943", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"99,100.18","100,000.18","0.00","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/investment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"900.00","0.00","未起息");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"900.00","0.00","0.00","0.00","900.00","暂未起息","900","0.00+0.00");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageInfo(seleniumUtil,"投资冻结","900.00");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);

		/**
		 * 17999999944
		 */
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999944", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"199,900.18","200,000.18","0.00","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/investment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"100.00","0.00","未起息");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"100.00","0.00","0.00","0.00","100.00","暂未起息","100","0.00+0.00");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageInfo(seleniumUtil,"投资冻结","100.00");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);

		/**
		 * 17999999945
		 */
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999945", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"251,000.18","300,000.18","0.00","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/investment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"49,000.00","0.00","未起息");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"49,000.00","0.00","0.00","0.00","49,000.00","暂未起息","49,000","0.00+0.00");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageInfo(seleniumUtil,"投资冻结","49,000.00");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);



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
		 * 第二轮检查:起息成功后数据:
		 * 17999999943
		 */
		seleniumUtil.get(TestUrl.getWXOldURL() + "/user/AdminLoginInterface.htm");
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999943", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		//我的--账户总览
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"99,100.18","100,029.46","929.28","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/investment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"900.00","29.28","到期时间：");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"900.00","22.05","6.98","0.25","929.28","929.28","900","22.05+7.23");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepTwoLoad(seleniumUtil,timeOut,"1");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepTwoLoad(seleniumUtil,timeOut,"2");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepTwoInfo(seleniumUtil,"投资成功","-900.00","1");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepTwoInfo(seleniumUtil,"投资冻结","900.00","2");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);

		/**
		 * 17999999944
		 */
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999944", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		//我的--账户总览
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"199,900.18","200,012.84","112.66","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/investment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"100.00","12.66","到期时间：");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"100.00","2.45","10.18","0.03","112.66","112.66","100","2.45+10.21");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepTwoLoad(seleniumUtil,timeOut,"1");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepTwoLoad(seleniumUtil,timeOut,"2");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepTwoInfo(seleniumUtil,"投资成功","-100.00","1");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepTwoInfo(seleniumUtil,"投资冻结","100.00","2");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);


		/**
		 * 17999999945
		 */
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999945", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		//我的--账户总览
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"251,000.18","301,213.84","50,213.66","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/investment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"49,000.00","1,213.66","到期时间：");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"49,000.00","1,200.50","0.00","13.16","50,213.66","50,213.66","49,000","1,200.50+13.16");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepTwoLoad(seleniumUtil,timeOut,"1");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepTwoLoad(seleniumUtil,timeOut,"2");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepTwoInfo(seleniumUtil,"投资成功","-49,000.00","1");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepTwoInfo(seleniumUtil,"投资冻结","49,000.00","2");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);


		/**
		 * 第三轮:还款
		 */
		seleniumUtil.setBrowserSize(1600,900);
		seleniumUtil.pause(1000);
		tenderAPIFlow.startRepay(seleniumUtil,borrowId);
		seleniumUtil.setBrowserSize(400,800);


		/**
		 * 第三轮检查:还款成功后数据
		 * 17999999943
		 */
		seleniumUtil.get(TestUrl.getWXOldURL() + "/user/AdminLoginInterface.htm");
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999943", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"100,029.46","100,029.46","0.00","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/ainvestment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"900.00","29.28","到期时间：");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"900.00","22.05","6.98","0.25","929.28","929.28","900","22.05+7.23");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"1");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"2");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"3");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"4");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"5");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"补息收益","＋0.25","1");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"加息券收益","＋6.98","2");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"收回本息","＋922.05","3");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"投资成功","-900.00","4");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"投资冻结","900.00","5");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);


		/**
		 * 17999999944
		 */
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999944", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"200,012.84","200,012.84","0.00","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/ainvestment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"100.00","12.66","到期时间：");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"100.00","2.45","10.18","0.03","112.66","112.66","100","2.45+10.21");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"1");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"2");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"3");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"4");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"5");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"补息收益","＋0.03","1");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"红包收益","＋10.18","2");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"收回本息","＋102.45","3");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"投资成功","-100.00","4");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"投资冻结","100.00","5");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);


		/**
		 * 17999999945
		 */
		//登录页面加载
		WeiXinLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		WeiXinLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999945", "111111");
		//我的--账户总览
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/index.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountPreviewPageHelper.waitWeiXinAccountPreviewPageLoad(seleniumUtil,timeOut);
		WeiXinAccountPreviewPageHelper.typeWeiXinAccountPreviewPageInfo(seleniumUtil,"301,213.84","301,213.84","0.00","","0");
		//我的--我的投资
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/user/ainvestment.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderListPageLoad(seleniumUtil,timeOut);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderListPageInfo(seleniumUtil,title,"49,000.00","1,213.66","到期时间：");
		WeiXinAccountMyTenderPageHelper.waitWeiXinAccountMyTenderDetailPageLoad(seleniumUtil,timeOut,borrowId);
		WeiXinAccountMyTenderPageHelper.typeWeiXinAccountMyTenderDetailPageInfo
				(seleniumUtil,"49,000.00","1,200.50","0.00","13.16","50,213.66","50,213.66","49,000","1,200.50+13.16");
		//我的--交易记录(每条记录检查一次)
		seleniumUtil.get(TestUrl.getWXOldURL() + "/account/tradeDetail.htm");
		seleniumUtil.pause(1000);
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"1");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"2");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"3");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"4");
		WeiXinAccountMyTradeDetailPageHelper.waitWeiXinAccountMyTradeDetailPageStepThreeLoad(seleniumUtil,timeOut,"5");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"补息收益","＋13.16","1");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"红包收益","＋0.00","2");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"收回本息","＋50,200.50","3");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"投资成功","-49,000.00","4");
		WeiXinAccountMyTradeDetailPageHelper.typeWeiXinAccountMyTradeDetailPageStepThreeInfo(seleniumUtil,"投资冻结","49,000.00","5");
		//退出登录
		WeiXinLoginPageHelper.logOutWeiXin(seleniumUtil);

		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>3个月流程测试完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
}








































