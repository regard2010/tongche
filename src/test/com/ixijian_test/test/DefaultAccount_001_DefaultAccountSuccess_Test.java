package ixijian_test.test;

import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.defaultdata.DefaultAccount;
import ixijian_main.pageshelper.login.AdminLoginPageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.MySqlUtil;

public class DefaultAccount_001_DefaultAccountSuccess_Test extends BaseParpareTestAdmin {

    DefaultAccount defaultAccount = new DefaultAccount();
    @Parameters({"phone","strategy_code_id1","strategy_code_id2","strategy_code_id3"})
    @Test
    public void defaultAccount(String phone,String source,String strategy_code_id1,String strategy_code_id2,String strategy_code_id3){
        defaultAccount.setRealName(source,phone);
        defaultAccount.defaultAccount(phone,"ixijian_new_test","100000.18");
        seleniumUtil.pause(2000);
        //等待登录页面加载
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil, timeOut);
        //输入登录信息
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil, "admin", "1234ad");
        //等待页面加载完成
        String strategyCode1 = MySqlUtil.getInstance().mySqlCURD("SELECT cs.strategy_code FROM coupon_strategy cs WHERE cs.coupon_strategy_id='" + strategy_code_id1 + "';");
        String strategyCode2 = MySqlUtil.getInstance().mySqlCURD("SELECT cs.strategy_code FROM coupon_strategy cs WHERE cs.coupon_strategy_id='" + strategy_code_id2 + "';");
        String strategyCode3 = MySqlUtil.getInstance().mySqlCURD("SELECT cs.strategy_code FROM coupon_strategy cs WHERE cs.coupon_strategy_id='" + strategy_code_id3 + "';");
        seleniumUtil.pause(2000);
        //加入(加息券、红包和礼品券)需要的卡券
        seleniumUtil.get(testAdminUrl + "/coupon/addCoupon.htm?strategyCode=" + strategyCode1 + "&phone=" + phone + "&num=5");
        seleniumUtil.get(testAdminUrl + "/coupon/addCoupon.htm?strategyCode=" + strategyCode2 + "&phone=" + phone + "&num=5");
        seleniumUtil.get(testAdminUrl + "/coupon/addCoupon.htm?strategyCode=" + strategyCode3 + "&phone=" + phone + "&num=5");
    }
}
