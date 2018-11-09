package tongche_test.api.php;

import net.sf.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tools_test.SignUtils;
import utils.InterFaceUtil;
import utils.MySqlUtil;
import utils.TestUrl;

import static utils.InterFaceUtil.sendPostUrl;

public class CarManage_001_Success_Test {

    //给组分配车辆
    @Test
    @Parameters({"userName"})
    public void vehicleAssignVehicleGroup(String userName){
        SignUtils.getInstance().userLogin(userName);
        String userId = SignUtils.getInstance().getUserId(userName);
        String sign = SignUtils.getInstance().AppSign(userName);
        String gidSql = "SELECT `GID` from `groups` where `companyId`=(select `companyId` from `user` where `tel`='" + userName + "');";
        String gid = MySqlUtil.getInstance().mySqlCURD(gidSql);
        String vehicleIdsSql = "SELECT `id` from `vehicle` where `deliverCompanyId`=(select `companyId` from `user` where `tel`='" + userName + "') order by `id` desc limit 1;";
        String vehicleIds = MySqlUtil.getInstance().mySqlCURD(vehicleIdsSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/vehicle/assignVehicleGroup","userId=" + userId + "&gid=" + gid + "&vehicleIds=" + vehicleIds + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //根据组查车辆
    @Test
    @Parameters({"userName"})
    public void vehicleSelectByGroup(String userName){
        SignUtils.getInstance().userLogin(userName);
        String userId = SignUtils.getInstance().getUserId(userName);
        String sign = SignUtils.getInstance().AppSign(userName);
        String gidSql = "SELECT `GID` from `groups` where `companyId`=(select `companyId` from `user` where `tel`='" + userName + "');";
        String gid = MySqlUtil.getInstance().mySqlCURD(gidSql);
        String vehicleIdsSql = "SELECT `id` from `vehicle` where `deliverCompanyId`=(select `companyId` from `user` where `tel`='" + userName + "') order by `id` desc limit 1;";
        String vehicleIds = MySqlUtil.getInstance().mySqlCURD(vehicleIdsSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/vehicle/assignVehicleGroup","userId=" + userId + "&gid=" + gid + "&vehicleIds=" + vehicleIds + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }
}




















