package ixijian_test.test;

import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.defaultdata.DefaultAccount;
import ixijian_main.pageshelper.login.AdminLoginPageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.AESCTools;
import utils.MySqlUtil;

public class DefaultAccount_Old_001_DASuccess_Test{

    DefaultAccount defaultAccount = new DefaultAccount();
    @Parameters({"phone","source","totalMoney"})
    @Test
    public void defaultAccount(String phone,String source,String totalMoney){
        defaultAccount.defaultAccount(phone,source,totalMoney);
        defaultAccount.setRealName(source,phone);
        phone = AESCTools.AESAdd(phone);
        String userIdSql = "SELECT `user_id` FROM `user` WHERE `phone` = '" + phone + "'";
        String userId = MySqlUtil.getInstance().mySqlCURD_URI(source,userIdSql);
        defaultAccount.setBankCard(source,userId,phone);
    }
}
