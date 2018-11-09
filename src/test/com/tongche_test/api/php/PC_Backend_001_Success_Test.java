package tongche_test.api.php;

import net.sf.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tools_test.SignUtils;
import utils.InterFaceUtil;
import utils.MySqlUtil;
import utils.TestUrl;

import static utils.InterFaceUtil.sendPostUrl;

public class PC_Backend_001_Success_Test{


    //获取本单位可以管理的人员总数
    @Test
    @Parameters({"userName"})
    public void pcapiGetusercount(String userName){
        SignUtils.getInstance().userLogin(userName);
        String userId = SignUtils.getInstance().getUserId(userName);
        String sign = SignUtils.getInstance().AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/pcapi/getusercount","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
    }

    //获取小票轨迹信息
    @Test
    @Parameters({"userName","carNumber"})
    public void pcapiItemtrack(String userName,String carNumber){
        SignUtils.getInstance().userLogin(userName);
        String userId = SignUtils.getInstance().getUserId(userName);
        String sign = SignUtils.getInstance().AppSign(userName);
        String itemOrderIdSql = "SELECT `id` from `itemorder` where `driverId`=(select `id` from `driver` where `accountId`= (select `currentDriver` from `vehicle` where `carNumber`='" + carNumber + "')) order by `id` desc limit 1;";
        String itemOrderId = MySqlUtil.getInstance().mySqlCURD(itemOrderIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/pcapi/itemtrack","userId=" + userId + "&itemOrderId=" + itemOrderId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
    }

    //获取司机详情
    @Test
    @Parameters({"userName","mixDriver"})
    public void pcapiGetdriver(String userName,String mixDriver){
        SignUtils.getInstance().userLogin(userName);
        String userId = SignUtils.getInstance().getUserId(userName);
        String sign = SignUtils.getInstance().AppSign(userName);
        String driver_userIdSql = "SELECT `id` from `user` where `tel`='" + mixDriver + "';";
        String driver_userId = MySqlUtil.getInstance().mySqlCURD(driver_userIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/pcapi/getdriver","userId=" + userId + "&driver_userId=" + driver_userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
    }
}




















