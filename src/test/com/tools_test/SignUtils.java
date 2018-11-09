package tools_test;

import net.sf.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.InterFaceUtil;
import utils.MySqlUtil;
import utils.TestUrl;

import static utils.InterFaceUtil.sendPostUrl;

public class SignUtils {

    //使用静态内部类创建外部类对象
    private static class Sign{
        private static SignUtils signUtils = new SignUtils();
    }

    //获取InterFaceUtil实例
    public static SignUtils getInstance(){
        return SignUtils.Sign.signUtils;
    }

    //登录
    @Test
    @Parameters({"userName"})
    public void userLogin(String userName) {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/backend/login","secret=157935&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
    }

    //登录成功后APP端添加签名
    public String AppSign(String user) {
        String userIdSql = "SELECT `id` from `user` where `tel`='" + user + "';";
        String userId = MySqlUtil.getInstance().mySqlCURD(userIdSql);
        String sign = "&urid="+userId+"&token=123";
        return sign;
    }

    //获取用户的userId
    public String getUserId(String user){
        String userIdSql = "SELECT `id` from `user` where `tel`='" + user + "';";
        String userId = MySqlUtil.getInstance().mySqlCURD(userIdSql);
        return userId;
    }
}
