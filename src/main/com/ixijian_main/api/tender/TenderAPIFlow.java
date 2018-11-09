package ixijian_main.api.tender;

import ixijian_main.api.user.LoginOauthToken;
import ixijian_main.base.BaseParpareTestAdmin;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.AESCTools;
import utils.InterFaceUtil;
import utils.SeleniumUtil;
import utils.TestUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TenderAPIFlow extends BaseParpareTestAdmin {

    LoginOauthToken loginOauthToken = new LoginOauthToken();
    private static Logger logger = Logger.getLogger(TenderAPIFlow.class);
//    String version = MySqlUtil.getInstance().mySqlCURD("SELECT version_code FROM app_aes_key ORDER BY key_id DESC LIMIT 1");

    /**
     *
     * @param username 手机号
     * @param loginPassword 登录密码
     * @param client   android ios wx pc
     * @param borrow_id 标的id
     * @param amount   投资金额
     * @param coupon_code 卡券code
     * @param payPassword 支付密码
     * @param message 正确或错误信息
     * @throws IOException
     */
    public void AdTender(String username,String loginPassword,String client,String borrow_id,String amount,String coupon_code,String payPassword,String message) throws IOException{
        String access_token = loginOauthToken.ADloginOauthTokenPassword(client,username,loginPassword,client+"登录");
        String Url = TestUrl.getInterFaceNewURLVersion() + "/tender";
        HttpPost httpPost = new HttpPost(Url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("borrow_id",borrow_id);
        jsonObject.put("amount",amount);
        jsonObject.put("coupon_code",coupon_code);
        logger.info("标的id是：" + borrow_id);
        logger.info("加密前支付密码："+payPassword);
        if(client.equals("ios")){
            payPassword = AESCTools.getInstance().encryptIOS(payPassword);
            logger.info("加密后支付密码："+payPassword);
        }else if(client.equals("android")) {
            payPassword = AESCTools.getInstance().encryptAD(payPassword);
            logger.info("加密后支付密码："+payPassword);
        }
        jsonObject.put("password",payPassword);
        InterFaceUtil.getInstance().setHeader(httpPost,client + "-client",access_token);
        StringEntity entity = InterFaceUtil.getInstance().setJSONBody(httpClient,httpPost,jsonObject);
        EntityUtils.toString(entity);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        InterFaceUtil.getInstance().AssertStatus(response,message);
    }

    /**
     *
     * @param client   android ios wx pc
     * @param borrow_id 标的id
     * @param amount   投资金额
     * @param coupon_code 卡券code
     * @param payPassword 支付密码
     * @param message 正确或错误信息
     * @throws IOException
     */
    public void AdTenderNoLogin(String client,String borrow_id,String amount,String coupon_code,String payPassword,String message) throws IOException{
        String Url = TestUrl.getInterFaceNewURLVersion() + "/tender";
        HttpPost httpPost = new HttpPost(Url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("borrow_id",borrow_id);
        jsonObject.put("amount",amount);
        jsonObject.put("coupon_code",coupon_code);
        logger.info("标的id是：" + borrow_id);
        logger.info("加密前支付密码："+payPassword);
        if(client.equals("ios")){
            payPassword = AESCTools.getInstance().encryptIOS(payPassword);
            logger.info("加密后支付密码："+payPassword);
        }else if(client.equals("android")) {
            payPassword = AESCTools.getInstance().encryptAD(payPassword);
            logger.info("加密后支付密码："+payPassword);
        }
        jsonObject.put("password",payPassword);
        InterFaceUtil.getInstance().setHeader(httpPost,client + "-client","");
        StringEntity entity = InterFaceUtil.getInstance().setJSONBody(httpClient,httpPost,jsonObject);
        EntityUtils.toString(entity);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        InterFaceUtil.getInstance().AssertStatus(response,message);
    }

    /**
     *
     * @param username 手机号
     * @param loginPassword 登录密码
     * @param client   android ios wx pc
     * @param borrow_id 标的id
     * @param amount   投资金额
     * @param payPassword 支付密码
     * @param message 正确或错误信息
     * @throws IOException
     */
    public void AdTenderCoupon(String username,String loginPassword,String client,String borrow_id,String amount,String payPassword,String message) throws IOException{
        String access_token = loginOauthToken.ADloginOauthTokenPassword(client,username,loginPassword,client+"登录");
        String Url = TestUrl.getInterFaceNewURLVersion() + "/tender";
        HttpPost httpPost = new HttpPost(Url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("borrow_id",borrow_id);
        jsonObject.put("amount",amount);
        logger.info("加密前支付密码："+payPassword);
        if(client.equals("ios")){
            payPassword = AESCTools.getInstance().encryptIOS(payPassword);
            logger.info("加密后支付密码："+payPassword);
        }else if(client.equals("android")) {
            payPassword = AESCTools.getInstance().encryptAD(payPassword);
            logger.info("加密后支付密码："+payPassword);
        }
        String couponCode = CouponChoose(access_token,client,borrow_id,amount);
        jsonObject.put("coupon_code",couponCode);
        jsonObject.put("password",payPassword);
        InterFaceUtil.getInstance().setHeader(httpPost,client + "-client",access_token);
        StringEntity entity = InterFaceUtil.getInstance().setJSONBody(httpClient,httpPost,jsonObject);
        EntityUtils.toString(entity);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        InterFaceUtil.getInstance().AssertStatus(response,message);
    }

    public String CouponChoose(String access_token,String client,String borrow_id,String amount) throws IOException{
//        String access_token = loginOauthToken.ADloginOauthTokenPassword(client,"17701748136","123456",client+"登录");
        String Url = TestUrl.getInterFaceNewURLVersion() + "/tender/couponList/1/1";
        String coupon_code = "";
        HttpPost httpPost = new HttpPost(Url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        InterFaceUtil.getInstance().setHeader(httpPost,client,access_token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("borrow_id",borrow_id);
        jsonObject.put("amount",amount);
        jsonObject.put("sign","");
        InterFaceUtil.getInstance().setJSONBody(httpClient,httpPost,jsonObject);
        StringEntity entity = InterFaceUtil.getInstance().setJSONBody(httpClient,httpPost,jsonObject);
        EntityUtils.toString(entity);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer sb = new StringBuffer("");
        String line = "";
        String NL = System.getProperty("line.separator");
        while ((line = in.readLine()) != null) {
            sb.append(line + NL);
        }
        in.close();
        String context = sb.toString();
        JSONObject objResponse = JSONObject.fromObject(context);
        coupon_code = objResponse.get("coupon_code").toString();
        return coupon_code;
    }

    public static void main(String[] args) throws IOException{
        TenderAPIFlow tenderAPIFlow = new TenderAPIFlow();
//        String coupon_code;
//        coupon_code = tenderAPIFlow.CouponChoose("bearer c0f9b1a2-b6f9-41ef-8141-8ada514c624a","android","2116","1000");
//        System.out.println(coupon_code);
    }

    /**
     * 起息
     */
    public void startInterest(SeleniumUtil seleniumUtil, String borrowId){
        seleniumUtil.get(TestUrl.getAdminURL() + "/portal/getRootMenu.htm?menuCode=BORROW_MANAGER_MENU");
        seleniumUtil.pause(1000);
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"nav\"]/li[1]/ul/li[2]/a")).click();
        seleniumUtil.pause(1000);
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"" + borrowId + "\"]/td[12]/a[2]")).click();
        seleniumUtil.pause(1000);
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"borrow\"]/div[1]/div[2]/div[2]/div[2]/input")).click();
        seleniumUtil.pause(1000);
        logger.info(">>>>>>>>>>>>>>>>>>>开始填写起息日期页面，倒计时已开始<<<<<<<<<<<<<<<<<<");
        for(int i = 5 ; i >= 0 ; i--){
            if(i == 0){
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>倒计时结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                break;
            }else{
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>还有"+ i +"秒<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                seleniumUtil.pause(1000);
            }
        }
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"fullVerifyDiv\"]/div[3]/div/textarea")).sendKeys("abc");
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"fullVerifyDiv\"]/div[4]/textarea")).sendKeys("efg");
        seleniumUtil.pause(1000);
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"mod-fullVerify\"]/div/div/div[3]/button[2]")).click();
//        seleniumUtil.get(TestUrl.getAdminURL() + "/borrow/verify/fullVerify.htm?borrowId=" + borrowId + "&verifyStatus=3&interestStartDate=" + interestStartDate + "&remark=qwer&contents=asdf");
    }

    /**
     * 还款
     */
    public void startRepay(SeleniumUtil seleniumUtil,String borrowId){
        seleniumUtil.get(TestUrl.getAdminURL() + "/portal/getRootMenu.htm?menuCode=BORROW_MANAGER_MENU");
        seleniumUtil.pause(1000);
        seleniumUtil.findElementBy(By.linkText("偿还借款")).click();
        seleniumUtil.pause(1000);
        seleniumUtil.findElementBy(By.linkText("还款中的借款")).click();
        seleniumUtil.pause(1000);
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"" + borrowId + "\"]/td[13]/a")).click();
        seleniumUtil.pause(1000);
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"1\"]/td[7]/a")).click();
        logger.info(">>>>>>>>>>>>>>>>>>>还款输入【验证码】倒计时已开始<<<<<<<<<<<<<<<<<<");
        for(int i = 15 ; i >= 0 ; i--){
            if(i == 0){
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>倒计时结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                break;
            }else{
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>还有"+ i +"秒<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                seleniumUtil.pause(1000);
            }
        }
        seleniumUtil.findElementBy(By.id("repayButton")).click();
        seleniumUtil.pause(2000);
        seleniumUtil.findElementBy(By.xpath("//*[@id=\"p2p_modal_dialog\"]/div/div/div[3]/button[1]")).click();
        logger.info(">>>>>>>>>>>>>>>>>>>还款【流程】倒计时开始，无需操作，等待倒计时完成即可<<<<<<<<<<<<<<<<<<");
        for(int i = 10 ; i >= 0 ; i--){
            if(i == 0){
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>倒计时结束，还款【流程】完成<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                break;
            }else{
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>还有"+ i +"秒<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                seleniumUtil.pause(1000);
            }
        }
//        seleniumUtil.get(TestUrl.getAdminURL() + "logout");
        seleniumUtil.pause(1000);
    }

    /**
     * 添加日
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addDay(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

}














