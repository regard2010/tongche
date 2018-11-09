package tongche_test.api;

import net.sf.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.InterFaceUtil;
import utils.MySqlUtil;
import utils.TestUrl;

import static utils.InterFaceUtil.sendPostUrl;

public class Api_Login_001_Success_Test extends MySqlUtil{

    /****************************************************************************登录****************************************************************************/

    //登录：成功
    @Test
    @Parameters({"userName"})
    public void userLoginForSuccess(String userName) {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=157935&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","登录成功");
    }

    //登录：失败处理测试1，没有手机号，有验证码
    @Test
    public void userLoginNoTel() {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=157935&tel=");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"500","请输入手机号和验证码");
    }

    //登录：失败处理测试2，有手机号，无验证码
    @Test
    @Parameters({"userName"})
    public void userLoginNoSecurityCode(String userName) {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"10101","验证码不能为空");
    }

    //登录：失败处理测试3，错误手机号，错误验证码
    @Test
    public void userLoginErrorTel() {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=123456&tel=1");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"10103","验证码失效");
    }

    //登录：失败处理测试4，正确手机号，错误验证码
    @Test
    @Parameters({"userName"})
    public void userLoginErrorSecurityCode(String userName) {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=123&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"10102","验证码错误");
    }

    //登录：失败处理测试5，无手机号，无验证码
    @Test
    public void userLoginNoTelNoSecurityCode() {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=&tel=");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"10102","验证码不能为空");
    }

    /****************************************************************************登录获取验证码****************************************************************************/

    //登录获取验证码：成功
    @Test
    @Parameters({"userName"})
    public void userGetSecurityCode(String userName) {
        JSONObject jsonObject1 = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getSecurityCode","type=1&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject1,"200","发送成功");
        JSONObject jsonObject2 = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getSecurityCode","type=1&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject2,"-209","操作频繁，请稍后再试");
    }

    //登录获取验证码：失败1，没有手机号
    @Test
    @Parameters({"userName"})
    public void userGetSecurityCodeNoTel(String userName) {
        JSONObject jsonObject1 = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getSecurityCode","type=1&tel=");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject1,"105","请填写手机号");
    }

    //登录获取验证码：失败2，错误手机号
    //===================sms表有限制，但是接口返回是【发送成功】====================
    @Test
    public void userGetSecurityCodeErrorTel() {
        JSONObject jsonObject1 = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getSecurityCode","type=1&tel=123");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject1,"105","请填写手机号");
    }

}




















