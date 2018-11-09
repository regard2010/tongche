package ixijian_main.api.user;import com.google.gson.JsonObject;import net.sf.json.JSONObject;import org.apache.commons.httpclient.HttpClient;import org.apache.commons.httpclient.methods.PostMethod;import org.apache.commons.httpclient.Header;import org.apache.http.HttpEntity;import org.apache.http.HttpResponse;import org.apache.http.NameValuePair;import org.apache.http.client.entity.UrlEncodedFormEntity;import org.apache.http.client.methods.HttpGet;import org.apache.http.client.methods.HttpPost;import org.apache.http.entity.StringEntity;import org.apache.http.impl.client.DefaultHttpClient;import org.apache.http.impl.client.HttpClientBuilder;import org.apache.http.message.BasicNameValuePair;import org.apache.http.protocol.HTTP;import org.apache.http.util.EntityUtils;import org.apache.log4j.Logger;import utils.AESCTools;import utils.InterFaceUtil;import utils.MySqlUtil;import utils.TestUrl;import java.io.IOException;import java.util.ArrayList;import java.util.List;/** * 用户登录注册相关模块 */public class LoginOauthToken {    private static Logger logger = Logger.getLogger(LoginOauthToken.class);//    String version = MySqlUtil.getInstance().mySqlCURD("SELECT version_code FROM app_aes_key ORDER BY key_id DESC LIMIT 1");    /**     * ios密码登录     * @param username     * @param passoword     * @return     * @throws Exception     */    public String IOSloginOauthTokenPassword(String username,String passoword) throws IOException {        String Url = TestUrl.getInterFaceNewURL() + "/oauth/token";        //传入URL        HttpPost request = new HttpPost(Url);        //传入请求头参数        request.addHeader("Content-Type", "application/x-www-form-urlencoded");        request.addHeader("Authorization", "Basic aW9zLWNsaWVudDppb3M=");        //传入请求体参数        List<NameValuePair> params = new ArrayList<NameValuePair>();        params.add(new BasicNameValuePair("client_id","ios-client"));        params.add(new BasicNameValuePair("client_secret","ios"));        params.add(new BasicNameValuePair("scope","read"));        params.add(new BasicNameValuePair("username",username));        params.add(new BasicNameValuePair("password","eeB+S02vcj+wS5hy+PXjug=="));        params.add(new BasicNameValuePair("grant_type","password"));        params.add(new BasicNameValuePair("version","1.3.3"));        request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));        // when        HttpResponse response = HttpClientBuilder.create().build().execute(request);        // then        HttpEntity entity = response.getEntity();        String jsonString = EntityUtils.toString(entity);        logger.info(jsonString);        //获得json        JSONObject jsonObject = JSONObject.fromObject(jsonString);        //得到access_token        String jo = jsonObject.getString("access_token");        logger.info("access_token:[" + jo + "]");        return jo;    }    /**     * 安卓密码登录     * @param username     * @param password     * @param message     * @return     * @throws Exception     */    public String ADloginOauthTokenPassword(String client,String username,String password,String message) throws IOException {        String version = TestUrl.getVersion();        String Url = TestUrl.getInterFaceNewURL() + "/oauth/token";        logger.info("加密前登录密码："+password);        password = AESCTools.getInstance().encryptAD(password);        logger.info("加密后登录密码："+password);        //传入URL        HttpPost request = new HttpPost(Url);        //传入请求头参数        request.addHeader("Content-Type", "application/x-www-form-urlencoded");        request.addHeader("Authorization", "Basic YW5kcm9pZC1jbGllbnQ6YW5kcm9pZA==");        //传入请求体参数        List<NameValuePair> params = new ArrayList<NameValuePair>();        params.add(new BasicNameValuePair("client_id", client + "-client"));        params.add(new BasicNameValuePair("client_secret", client));        params.add(new BasicNameValuePair("scope", "read"));        params.add(new BasicNameValuePair("username", username));        params.add(new BasicNameValuePair("password", password));        params.add(new BasicNameValuePair("grant_type", "password"));        params.add(new BasicNameValuePair("version", version));        request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));        // when        HttpResponse response = HttpClientBuilder.create().build().execute(request);        // then        HttpEntity entity = response.getEntity();        String jsonString = EntityUtils.toString(entity);        //获得json        JSONObject jsonObject = JSONObject.fromObject(jsonString);        int status = response.getStatusLine().getStatusCode();        if (status == 200) {            //得到access_token            String jo = jsonObject.getString("access_token");            logger.info("调用[" + message + "]接口成功,access_token:[" + jo + "]");            return jo;        }else if(jsonObject.get("error_description").toString().equals("请输入图形验证码") && status == 401){            logger.error("请使用接口工具手动调用登录验证码，并填写图形验证码后，重新登录");        }else{            logger.error("调用[" + message + "]接口失败，状态码为：" + status);            System.err.println("调用[" + message + "]接口失败，状态码为：" + status);        }        return null;    }    /**     * 安卓指纹登录     * @param username     * @param password     * @param message     * @return     * @throws Exception     */    public String ADloginOauthTokenConsumer(String username,String password,String message) throws IOException {        String Url = TestUrl.getInterFaceNewURL() + "/oauth/token";        logger.info("加密前登录密码："+password);        password = AESCTools.getInstance().encryptAD(password);        logger.info("加密后登录密码："+password);        boolean flag = false;        //传入URL        HttpPost request = new HttpPost(Url);        //传入请求头参数        request.addHeader("Content-Type", "application/x-www-form-urlencoded");        request.addHeader("Authorization", "Basic YW5kcm9pZC1jbGllbnQ6YW5kcm9pZA==");        //传入请求体参数        List<NameValuePair> params = new ArrayList<NameValuePair>();        params.add(new BasicNameValuePair("client_id", "android-client"));        params.add(new BasicNameValuePair("client_secret", "android"));        params.add(new BasicNameValuePair("username", username));        params.add(new BasicNameValuePair("password", password));        params.add(new BasicNameValuePair("grant_type", "custom_grant"));        params.add(new BasicNameValuePair("version", "1.3.3"));        request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));        // when        HttpResponse response = HttpClientBuilder.create().build().execute(request);        // then        HttpEntity entity = response.getEntity();        String jsonString = EntityUtils.toString(entity);        logger.info(jsonString);        int status = response.getStatusLine().getStatusCode();        if (status == 200) {            //获得json            JSONObject jsonObject = JSONObject.fromObject(jsonString);            //得到access_token            String jo = jsonObject.getString("access_token");            logger.info("调用[" + message + "]接口成功,access_token:[" + jo + "]");            return jo;        } else if (status == 401) {            flag = jsonString.contains(message);            if(flag == false){                System.err.println("错误信息[" + message + "]与预期不匹配！！！");            }else{                logger.info("错误信息[" + message + "]与预期匹配。");            }        }else{            System.err.println("调用[" + message + "]接口失败，状态码为：" + status);        }        return null;    }    /**     * 安卓输错三次密码产生图形验证码     * @param username     * @param message     * @throws Exception     */    public void ADloginErrorThreeSendKaptchaCode(String username,String message) throws IOException {        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/AdminLoginInterface/kaptchaCode/" + username;        InterFaceUtil.getInstance().AssertGet(Url,message);    }    /**     * 安卓注册     * @param username     * @param password     * @param msgCode     * @param invitUsername     */    public void ADregister(String username,String password,String msgCode,String invitUsername,String sign,String message) throws IOException{        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/register";        boolean flag = false;        DefaultHttpClient httpClient = new DefaultHttpClient();        //传入URL        HttpPost httpPost = new HttpPost(Url);        //传入请求头参数        httpPost.addHeader("Content-Type", "application/json");        httpPost.addHeader("client_id", "android-client");        //传入请求体json参数        JSONObject jsonObject = new JSONObject();        jsonObject.put("username",username);        jsonObject.put("code",msgCode);        jsonObject.put("password",password);        jsonObject.put("comefrom","ad");        jsonObject.put("invit_username",invitUsername);        jsonObject.put("open_id","");        jsonObject.put("sign",sign);        JSONObject.fromObject(jsonObject);        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题        entity.setContentEncoding("UTF-8");        entity.setContentType("application/json");        String jsonString = EntityUtils.toString(entity);        httpPost.setEntity(entity);        HttpResponse response = httpClient.execute(httpPost);        logger.info(jsonString);        InterFaceUtil.getInstance().AssertStatus(response,message);    }    /**     * 安卓注册获取注册短信验证码     */    public String ADregisterMsgCode(String username,String message) throws IOException {        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/register/msgCode/" + username;        InterFaceUtil.getInstance().AssertGet(Url,message);        String msgCodeSql = "SELECT content FROM ms_send ORDER BY send_id DESC LIMIT 1;";        String msgCode = MySqlUtil.getInstance().mySqlCURD_URI("ixijian_dev",msgCodeSql);        msgCode = msgCode.substring(9,13);        return msgCode;    }    /**     * 安卓重置登录密码验证码     */    public void ADresetLoginPasswordMsgCode(String username, String message) throws IOException{        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/AdminLoginInterface/password/msgCode/" + username;        InterFaceUtil.getInstance().AssertGet(Url,message);    }    /**     * 安卓重置登录密码     */    public void ADresetLoginPassword(String username,String msgCode,String password,String confirm_password,String sign,String message) throws IOException{        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/AdminLoginInterface/password/";        boolean flag = false;        DefaultHttpClient httpClient = new DefaultHttpClient();        //传入URL        HttpPost httpPost = new HttpPost(Url);        //传入请求头参数        httpPost.addHeader("Content-Type", "application/json");        httpPost.addHeader("client_id", "android-client");        //传入请求体json参数        JSONObject jsonObject = new JSONObject();        jsonObject.put("username",username);        jsonObject.put("code",msgCode);        jsonObject.put("password",password);        jsonObject.put("confirm_password",confirm_password);        jsonObject.put("sign",sign);        JSONObject.fromObject(jsonObject);        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题        entity.setContentEncoding("UTF-8");        entity.setContentType("application/json");        String jsonString = EntityUtils.toString(entity);        httpPost.setEntity(entity);        HttpResponse response = httpClient.execute(httpPost);        logger.info(jsonString);        InterFaceUtil.getInstance().AssertStatus(response,message);    }    /**     * 安卓重置支付密码验证码     */    public void ADresetTradingPasswordMsgCode(String username, String message) throws IOException{        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/trading/password/msgCode/" + username;        InterFaceUtil.getInstance().AssertGet(Url,message);    }    /**     * 安卓重置支付密码     */    public void ADresetTradingPassword(String username,String msgCode,String password,String confirm_password,String sign,String message) throws IOException{        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/trading/password/";        boolean flag = false;        DefaultHttpClient httpClient = new DefaultHttpClient();        //传入URL        HttpPost httpPost = new HttpPost(Url);        //传入请求头参数        httpPost.addHeader("Content-Type", "application/json");        httpPost.addHeader("client_id", "android-client");        //传入请求体json参数        JSONObject jsonObject = new JSONObject();        jsonObject.put("username",username);        jsonObject.put("code",msgCode);        jsonObject.put("password",password);        jsonObject.put("confirm_password",confirm_password);        jsonObject.put("sign",sign);        JSONObject.fromObject(jsonObject);        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题        entity.setContentEncoding("UTF-8");        entity.setContentType("application/json");        String jsonString = EntityUtils.toString(entity);        httpPost.setEntity(entity);        HttpResponse response = httpClient.execute(httpPost);        logger.info(jsonString);        InterFaceUtil.getInstance().AssertStatus(response,message);    }    /**     * 安卓用户信息Post     */    public void ADuser(String access_token,String message) throws IOException{        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/";        InterFaceUtil.getInstance().AssertPost(Url,access_token,"安卓用户信息");    }    /**     * 安卓用户设备绑定     */    public void ADuserDevice(String name, String access_token, String model, String system_version, String consumer_key,String consumer_secret,String type,String sign, String message) throws IOException{        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/device";        logger.info(Url);        DefaultHttpClient httpClient = new DefaultHttpClient();        //传入URL        HttpPost httpPost = new HttpPost(Url);        //传入请求头参数        httpPost.addHeader("Content-Type", "application/json");        httpPost.addHeader("client_id", "android-client");        httpPost.addHeader("Authorization", "bearer " + access_token);        //传入请求体json参数        JSONObject jsonObject = new JSONObject();        jsonObject.put("name",name);        jsonObject.put("model",model);        jsonObject.put("system_version",system_version);        jsonObject.put("consumer_key",consumer_key);        jsonObject.put("consumer_secret",consumer_secret);        jsonObject.put("type",type);        jsonObject.put("sign",sign);        JSONObject.fromObject(jsonObject);        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题        entity.setContentEncoding("UTF-8");        entity.setContentType("application/json");        String jsonString = EntityUtils.toString(entity);        httpPost.setEntity(entity);        HttpResponse response = httpClient.execute(httpPost);        logger.info("返回的JSON值是：" + jsonString);        InterFaceUtil.getInstance().AssertStatus(response,message);    }    /**     * 安卓解除用户设备绑定     */    public void ADuserDeviceDelete(String consumer_key, String type, String sign, String message) throws IOException{        String Url = TestUrl.getInterFaceNewURLVersion() + "/user/device/delete";        logger.info(Url);        boolean flag = false;        DefaultHttpClient httpClient = new DefaultHttpClient();        //传入URL        HttpPost httpPost = new HttpPost(Url);        //传入请求头参数        httpPost.addHeader("Content-Type", "application/json");        httpPost.addHeader("client_id", "android-client");        //传入请求体json参数        JSONObject jsonObject = new JSONObject();        jsonObject.put("consumer_key",consumer_key);        jsonObject.put("type",type);        jsonObject.put("sign",sign);        JSONObject.fromObject(jsonObject);        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题        entity.setContentEncoding("UTF-8");        entity.setContentType("application/json");        String jsonString = EntityUtils.toString(entity);        httpPost.setEntity(entity);        HttpResponse response = httpClient.execute(httpPost);        logger.info("返回的JSON值是：" + jsonString);        InterFaceUtil.getInstance().AssertStatus(response,message);    }}