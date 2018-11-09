package tongche_test.admin;

import org.testng.annotations.*;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pageshelper.admin.login.AdminLoginPageHelper;
import tongche_main.pageshelper.admin.vertical.AddVerticalPageHelper;
import tools_test.SignUtils;
import utils.MySqlUtil;
import utils.TestUrl;

import java.util.Map;

public class AddVerticalPage_002_AddVerticalSuccessFunction_Test extends BaseParpareTestAdmin {

    @BeforeTest
    @Parameters({"userName"})
    public void login(String userName){
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil,timeOut);
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil,userName,"157935");
    }

    /**
     * excel的读入地址是：/Users/liuweiyi/Documents/tongche_workspace/data/admin.xls，sheet：002
     * 1、自动添加车辆
     * 2、自动指派到对应搅拌站
     * 更新至2018-11-06
     */
    @Test(dataProvider = "TongCheAdminAddVertical")
    public void addVertical(Map<String,String> addVerticalData){
        AddVerticalPageHelper.waitAddVerticalPageLoad(seleniumUtil,timeOut);
        AddVerticalPageHelper.typeAddVerticalPageInfo(seleniumUtil,addVerticalData.get("MAXCAPACITY"),addVerticalData.get("CARLINCESE"),addVerticalData.get("CARNUMBER"));
        seleniumUtil.pause(1000);
        String sign = SignUtils.getInstance().AppSign(addVerticalData.get("ADMINISTRATOR"));
        String userId = SignUtils.getInstance().getUserId(addVerticalData.get("ADMINISTRATOR"));
        String vehicleIdSql = "SELECT `id` from `vehicle` where `carNumber`='" + addVerticalData.get("CARNUMBER") + "';";
        String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
        seleniumUtil.get(TestUrl.getTongCheReleaseUrl() + "/vehicle/assigncompany?backend=1&userId=" + userId + "&vehicleId=" + vehicleId + "&companyId=" + addVerticalData.get("COMPANYID") + sign);
        seleniumUtil.pause(1000);
        seleniumUtil.get(TestUrl.getTongCheAdminUrl() + "/#/shipVertical");
    }
}
