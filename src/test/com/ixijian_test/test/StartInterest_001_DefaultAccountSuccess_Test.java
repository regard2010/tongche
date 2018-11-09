package ixijian_test.test;

import ixijian_main.api.tender.TenderAPIFlow;
import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.pageshelper.login.AdminLoginPageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class StartInterest_001_DefaultAccountSuccess_Test extends BaseParpareTestAdmin {

    @Parameters({"borrowId"})
    @Test
    public void defaultAccount(String borrowId){
        seleniumUtil.pause(2000);
        //等待登录页面加载
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil, timeOut);
        //输入登录信息
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil, "admin", "1234ad");
        //等待页面加载完成
        seleniumUtil.pause(2000);
        //刷入需要的卡券
        TenderAPIFlow tenderAPIFlow = new TenderAPIFlow();
        tenderAPIFlow.startInterest(seleniumUtil,borrowId);
    }
}
