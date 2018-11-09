package ixijian_test.api;

import ixijian_main.api.user.LoginOauthToken;
import ixijian_main.defaultdata.DefaultAccount;
import net.sf.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.AESCTools;

import java.io.IOException;

import static utils.SignUtil.generateSign;


/**
 * (priority = 1)从小到大，按顺序执行测试用例
 */
public class LoginInterFace_001_LoginSuccessFunction_Test{

    LoginOauthToken loginOauthToken = new LoginOauthToken();
    String client = "android";
    String source = "ixijian_new_test";

	/**
	 * 安卓密码登录成功
	 * 用户登录
	 * 用户名:13916072538
	 * 密码:111111
	 */
	@Test(priority = 1)
    void ADloginSuccessPassword() throws IOException {
        loginOauthToken.ADloginOauthTokenPassword(client,"13916072538","111111","登录安卓");
    }

    /**
     * 安卓指纹登录成功
     */
    @Test(priority = 2)
    public void ADloginSuccessConsumer() throws IOException{
	    loginOauthToken.ADloginOauthTokenConsumer("8e001c95bc7d01572e81b35a0450fed4","c33e48df6622b8c7ef4ae0db2c26b2e4","登录安卓");
    }

    /**
     * 安卓输错三次发送验证码
      */
    @Test(priority = 3)
    public void ADloginErrorThreeSendKaptchaCode() throws IOException{
        loginOauthToken.ADloginErrorThreeSendKaptchaCode("13116162780","登录验证码");
    }

    /**
     * 安卓注册获取注册验证码（在日志里查看）
     */
    @Test(priority = 4)
    public String ADregisterMsgCode() throws IOException{
        String msgCode = loginOauthToken.ADregisterMsgCode("17899999902","获取注册验证码");
        return msgCode;
    }

    /**
     * 安卓用户注册
     * 在日志里查看msgCode
     * 已知问题：传入的密码未AES加密
     */
    @Parameters({"phone"})
    @Test(priority = 5)
    public void ADregister(String phone) throws IOException{
        String password = "123456";
        System.out.println("加密前密码："+password);
        password = AESCTools.getInstance().encryptAD(password);
        System.out.println("加密后密码："+password);
        String msgCode = loginOauthToken.ADregisterMsgCode(phone,"获取注册验证码");
        JSONObject json = new JSONObject();
        json.put("username", phone);
        json.put("code", msgCode);
        json.put("password", password);
        json.put("comefrom", "ad");
        json.put("invit_username", "17899999901");
        json.put("open_id", "");
        String sign = generateSign(json, "android");
        System.out.println(generateSign(json, "android"));
        loginOauthToken.ADregister(phone,password,msgCode,"17899999901",sign,"0000");
    }

    /**
     * 安卓用户注册，自动实名，加入身份证号，银行卡号，账户100万，可以直接投资
     */
    @Parameters({"phone","invite_phone"})
    @Test(priority = 5)
    public void ADregisterAndDefaultAccount(String phone,String invite_phone) throws IOException{
        String password = "123456";
        System.out.println("加密前密码："+password);
        password = AESCTools.getInstance().encryptAD(password);
        System.out.println("加密后密码："+password);
        String msgCode = loginOauthToken.ADregisterMsgCode(phone,"获取注册验证码");
        JSONObject json = new JSONObject();
        json.put("username", phone);
        json.put("code", msgCode);
        json.put("password", password);
        json.put("comefrom", "ad");
        json.put("invit_username", invite_phone);
        json.put("open_id", "");
        String sign = generateSign(json, "android");
        System.out.println(generateSign(json, "android"));
        loginOauthToken.ADregister(phone,password,msgCode,invite_phone,sign,"0000");
        DefaultAccount defaultAccount = new DefaultAccount();
        defaultAccount.setRealName(source,phone);
        defaultAccount.defaultAccount(phone,source,"100000.18");
    }

    /**
     * 安卓重置登录密码验证码
     */
    @Test(priority = 6)
    public void ADresetLoginPasswordMsgCode() throws IOException{
        loginOauthToken.ADresetLoginPasswordMsgCode("17999999901","安卓获取重置登录密码验证码");
    }

    /**
     * 安卓重置登录密码
     */
    @Test(priority = 7)
    public void ADresetLoginPassword() throws IOException{
        String username = "17999999901";
        String msgCode = "8970";
        String password = "123456";
        String confirm_password = "123456";
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("code", msgCode);
        json.put("password", password);
        json.put("confirm_password", confirm_password);
        String sign = generateSign(json, "android");
        System.out.println(generateSign(json, "android"));
        loginOauthToken.ADresetLoginPassword(username,msgCode,password,confirm_password,sign,"安卓重置登录密码");
    }

    /**
     * 安卓重置支付密码验证码（在日志里查看）
     */
    @Test(priority = 8)
    public void ADresetTradingPasswordMsgCode() throws IOException{
        loginOauthToken.ADresetTradingPasswordMsgCode("17999999901","安卓获取重置支付密码验证码");
    }

    /**
     * 安卓重置支付密码
     * 在日志里查看msgCode，替换即可
     */
    @Test(priority = 9)
    public void ADresetTradingPassword() throws IOException{
        String username = "17999999901";
        String msgCode = "9572";
        String password = "123456";
        String confirm_password = "123456";
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("code", msgCode);
        json.put("password", password);
        json.put("confirm_password", confirm_password);
        String sign = generateSign(json, "android");
        System.out.println(generateSign(json, "android"));
        loginOauthToken.ADresetTradingPassword(username,msgCode,password,confirm_password,sign,"安卓重置支付密码");
    }

    /**
     * 安卓用户信息
     */
    @Test(priority = 9)
    public void ADuser() throws IOException{
        String access_token = loginOauthToken.ADloginOauthTokenPassword(client,"13916072538","111111","安卓登录");
        loginOauthToken.ADuser(access_token,"安卓获取用户");
    }

    /**
     * 安卓绑定设备
     */
    @Test(priority = 10)
    public void ADuserDevice() throws IOException{
        String access_token = loginOauthToken.ADloginOauthTokenPassword(client,"13916072538","111111","安卓用户密码登录");
        String name = "xiaomi";
        String model = "MIX";
        String system_version = "7.1.1";
        String consumer_key = "zdaa6d74ed91a14e2adbd68fb9223127";
        String consumer_secret = "ydaa6d74ed91a14e2adbd68fb9223127";
        String type = "AdminLoginInterface";
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("model", model);
        json.put("system_version", system_version);
        json.put("consumer_key", consumer_key);
        System.out.println("加密前密码："+consumer_secret);
        consumer_secret = AESCTools.getInstance().encryptAD(consumer_secret);
        System.out.println("加密后密码："+consumer_secret);
        json.put("consumer_secret", consumer_secret);
        json.put("type", type);
        String sign = generateSign(json, "android");
        System.out.println(generateSign(json, "android"));
        loginOauthToken.ADuserDevice(name,access_token,model,system_version,consumer_key,consumer_secret,type,sign,"安卓绑定设备");
    }

    /**
     * 安卓解除绑定设备
     */
    @Test(priority = 11)
    public void ADuserDeviceDelete() throws IOException{
        String consumer_key = "zdaa6d74ed91a14e2adbd68fb9223127";
        String type = "AdminLoginInterface";
        JSONObject json = new JSONObject();
        json.put("consumer_key", consumer_key);
        json.put("type", type);
        String sign = generateSign(json, "android");
        System.out.println(generateSign(json, "android"));
        loginOauthToken.ADuserDeviceDelete(consumer_key,type,sign,"安卓解除绑定设备");
    }
}
















