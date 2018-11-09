package ixijian_test.test;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.testng.annotations.Test;
import utils.SHAITools;

import java.util.HashMap;

public class AndroidLogin_001_AndroidSuccessFunction_Test {

    //获得签名的方法
    public static String getCaptchaImageParams(String udid) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("client_id", "android-client");
        map.put("version", "1.3.1");
        map.put("username", udid);
        String sign = SHAITools.getEccrypt(map);
        map.put("sign", sign);//加密
        JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.print(result);
        return result;
    }

    @Test
    public void androidLogin() throws Exception{
        //获得签名
//        String loginUrl = "http://testapi.ixijian.com";
        getCaptchaImageParams("13916072538");
    }
}
