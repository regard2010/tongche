package ixijian_test.test;

import ixijian_main.defaultdata.DefaultAccount;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class InitAccount_001_InitAccountSuccess_Test {

    DefaultAccount defaultAccount = new DefaultAccount();
    @Parameters({"source","phone"})
    @Test
    public void initAccount(String source,String phone){

        defaultAccount.initAccount(source, phone);
    }
}
