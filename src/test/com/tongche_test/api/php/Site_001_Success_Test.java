package tongche_test.api.php;

import net.sf.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.InterFaceUtil;
import utils.MySqlUtil;
import utils.TestUrl;

import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.InterFaceUtil.sendPostUrl;

public class Site_001_Success_Test extends MySqlUtil{

    //登录
    @Test
    @Parameters({"userName"})
    public void userLogin(String userName) {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=157935&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","登录成功");
    }

    //登录成功后APP端添加签名
    public static String AppSign(String user) {
        String userIdSql = "SELECT `id` from `user` where `tel`='" + user + "';";
        String userId = MySqlUtil.getInstance().mySqlCURD(userIdSql);
        String sign = "&urid="+userId+"&token=123";
        return sign;
    }

    //获取用户的userId
    public static String getUserId(String user){
        String userIdSql = "SELECT `id` from `user` where `tel`='" + user + "';";
        String userId = MySqlUtil.getInstance().mySqlCURD(userIdSql);
        return userId;
    }

    //获取工地工程列表
    /**
     * 四种角色和不存在的账号来刷接口
     * @param userName
     */
    @Test
    @Parameters({"userName"})
    public void siteList_Mix(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/site/list","urid=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","获取成功");
    }

    @Test
    @Parameters({"rentUserName"})
    public void siteList_Rent(String rentUserName){
        userLogin(rentUserName);
        String userId = getUserId(rentUserName);
        String sign = AppSign(rentUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/site/list","urid=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","获取成功");
    }

    @Test
    @Parameters({"noOrderDriver"})
    public void siteList_Driver(String noOrderDriver){
        userLogin(noOrderDriver);
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/site/list","urid=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"402","用户ID错误");
    }

    @Test
    @Parameters({"receiveUserName"})
    public void siteList_Delivery(String receiveUserName){
        userLogin(receiveUserName);
        String userId = getUserId(receiveUserName);
        String sign = AppSign(receiveUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/site/list","urid=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","获取成功");
    }

    @Test
    @Parameters({"NullUser"})
    public void siteList_NULL(String NullUser){
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=157935&tel=" + NullUser + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"500","用户未注册");
    }

    //更新工程
    @Test
    @Parameters({"userName"})
    public void siteProjectUpdate(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        String projectIdSql = "";
        String projectId = MySqlUtil.getInstance().mySqlCURD(projectIdSql);
        String projectNameSql = "";
        String projectName = MySqlUtil.getInstance().mySqlCURD(projectNameSql);
        String longitudeSql = "";
        String longitude = MySqlUtil.getInstance().mySqlCURD(longitudeSql);
        String latitudeSql = "";
        String latitude = MySqlUtil.getInstance().mySqlCURD(latitudeSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() +
                "/site/projectupdate","urid=" + userId + "&projectId=" + projectId + "&projectName=" + projectName + "&longitude=" + longitude + "&latitude=" + latitude + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //更新围栏
    @Test
    @Parameters({"userName"})
    public void siteUpdate(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        String siteIdSql = "";
        String siteId = MySqlUtil.getInstance().mySqlCURD(siteIdSql);
        String siteNameSql = "";
        String siteName = MySqlUtil.getInstance().mySqlCURD(siteNameSql);
        String typeSql = "";
        String type = MySqlUtil.getInstance().mySqlCURD(typeSql);
        String scopeSql = "";
        String scope = MySqlUtil.getInstance().mySqlCURD(scopeSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() +
                "/site/update","urid=" + userId + "&siteId=" + siteId + "&siteName=" + siteName + "&type=" + type + "&scope=" + scope + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //新建围栏
    /**
     * type=1 工地 type=2 搅拌站
     * @param userName
     */
    @Test
    @Parameters({"userName"})
    public void siteAdd(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String siteName = "南京西路_" + simpleDateFormat.format(date);
        String type = "1";
        String scopeSql = "SELECT `scope` from site where `id`='46';";
        String scope = MySqlUtil.getInstance().mySqlCURD(scopeSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() +
                "/site/add","urid=" + userId + "&siteName=" + siteName + "&type=" + type + "&scope=" + scope + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","新建成功");
    }

}




















