package ixijian_test.pc.flow.tender;

/**
 * UI浏览器测试调用范例，可以直接用于参考复制UI测试
 */

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pageshelper.pc.*;
import org.testng.annotations.Test;


public class TenderFlowPcPage_001_TenderSuccessFunction_Test extends BaseParpareTestPc {
	
	@Test
	public void tenderFlowPcSuccessFunction(){
		//登录页面加载
		PcLoginPageHelper.waitLoginPageLoad(seleniumUtil,timeOut);
		PcLoginPageHelper.typeLoginInfo(seleniumUtil, "17999999911", "111111");
		//等待页面加载完成
		seleniumUtil.pause(1000);
		//用户告知弹出框关闭
		PcLoginPageHelper.waitLoginLayerTitleLoad(seleniumUtil,timeOut);
		PcLoginPageHelper.typeLoginLayerTitleInfo(seleniumUtil);
		//投资列表页加载
		TenderListPageHelper.waitPcTenderListPageLoad(seleniumUtil,timeOut);
		TenderListPageHelper.typePcTenderListInfo(seleniumUtil,"","","","");
		//投资详情页加载
		TenderDetailPageHelper.waitPcTenderDetailPageLoad(seleniumUtil,timeOut);
		TenderDetailPageHelper.typePcTenderDetailInfo(seleniumUtil,"100");
		//投资确认页加载
		TenderConfirmPageHelper.waitPcTenderConfirmPageLoad(seleniumUtil,timeOut);
		TenderConfirmPageHelper.typePcTenderConfirmInfo(seleniumUtil,"","");
		//投资完成页加载
		TenderFinishPageHelper.waitPcTenderFinishLoad(seleniumUtil,timeOut);
		TenderFinishPageHelper.typePcTenderFinishInfo(seleniumUtil,"","","");
		//退出登录
		PcLoginPageHelper.logOutPc(seleniumUtil);
	}
}
