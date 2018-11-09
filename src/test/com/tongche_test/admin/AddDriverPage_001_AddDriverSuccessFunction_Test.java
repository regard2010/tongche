package tongche_test.admin;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pageshelper.admin.driver.AddDriverPageHelper;
import tongche_main.pageshelper.admin.login.AdminLoginPageHelper;

import java.util.Map;

public class AddDriverPage_001_AddDriverSuccessFunction_Test extends BaseParpareTestAdmin {

    @BeforeTest
    @Parameters({"userName"})
    public void login(String userName){
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil,timeOut);
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil,userName,"157935");
    }

    @Test(dataProvider = "TongCheAdminAddDriver")
    public void addDriver(Map<String,String> addDriverData){
        AddDriverPageHelper.waitAddDriverPageLoad(seleniumUtil,timeOut);
        AddDriverPageHelper.typeAddDriverPageInfo(seleniumUtil,addDriverData.get("USERNAME"),addDriverData.get("TEL"),"该手机号已注册");
    }
}
