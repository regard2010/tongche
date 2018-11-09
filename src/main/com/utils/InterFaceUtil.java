package utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class InterFaceUtil {

    private static Logger logger = Logger.getLogger(InterFaceUtil.class);
    //使用静态内部类创建外部类对象
    private static class InterFace{
        private static InterFaceUtil interFaceUtil = new InterFaceUtil();
    }

    //获取InterFaceUtil实例
    public static InterFaceUtil getInstance(){
        return InterFace.interFaceUtil;
    }

    //没有GET请求体专用
    public void AssertGetOld(String Url,String message) throws IOException{
        HttpGet httpGet = new HttpGet(Url);
        httpGet.setHeader("client_id","android-client");
        HttpResponse response = HttpClientBuilder.create().build().execute(httpGet);
        int status = response.getStatusLine().getStatusCode();
        if (status == 200) {
            logger.info("调用[" + message + "]接口成功");
            System.out.println("调用[" + message + "]接口成功");
        } else {
            logger.error("调用[" + message + "]接口失败，状态码为：" + status);
            System.err.println("调用[" + message + "]接口失败，状态码为：" + status);
        }
    }

    //投资专用
    public void AssertStatus(HttpResponse response,String message)throws IOException{
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
        String codeValue = objResponse.get("code").toString();
        String messageValue = objResponse.get("message").toString();
        int status = response.getStatusLine().getStatusCode();
        if (status == 200) {
            if(codeValue.equals(message)){
                logger.info("返回的code:[" + codeValue + "]");
                logger.info("返回的message:[" + messageValue + "]");
                logger.info("返回的JSON为：" + objResponse);
                logger.info("调用[" + message + "]接口成功");
                logger.info("********************************************************************");
            }else {
                logger.error("返回的code:[" + codeValue + "]");
                logger.error("返回的message:[" + messageValue + "]");
                logger.error("返回的JSON为：" + objResponse);
                logger.error("调用[" + message + "]接口失败，实际返回Code是:" + codeValue);
                System.err.println("调用[" + message + "]接口失败，实际返回Code是:" + codeValue);
                logger.info("********************************************************************");
            }
        } else{
            logger.error("调用[" + message + "]接口失败，状态码为：" + status);
            System.err.println("调用[" + message + "]接口失败，状态码为：" + status);
            logger.info("********************************************************************");
        }
    }

    //没有POST请求体专用
    public void AssertPost(String Url,String access_token,String message) throws IOException{
        HttpPost httpPost = new HttpPost(Url);
        httpPost.setHeader("client_id","android-client");
        httpPost.setHeader("Authorization","bearer " + access_token);
        HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
        int status = response.getStatusLine().getStatusCode();
        if (status == 200) {
            logger.info("调用[" + message + "]接口成功");
            System.out.println("调用[" + message + "]接口成功");
        } else {
            logger.error("调用[" + message + "]接口失败，状态码为：" + status);
            System.err.println("调用[" + message + "]接口失败，状态码为：" + status);
        }
    }

    //固定请求头
    public void setHeader(HttpPost httpPost,String client,String access_token)throws IOException{
        httpPost.setHeader("client_id",client);
        httpPost.setHeader("Authorization","bearer " + access_token);
        httpPost.setHeader("Content-Type","application/json ");
    }

    //POST传入请求体
    public StringEntity setJSONBody(DefaultHttpClient httpClient,HttpPost httpPost, JSONObject jsonObject)throws IOException{
        JSONObject.fromObject(jsonObject);
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        return entity;
    }

    //POST状态码
    public void AssertStatusPost(HttpResponse response,String message)throws IOException{
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
        String codeValue = objResponse.get("code").toString();
        String messageValue = objResponse.get("message").toString();
        int status = response.getStatusLine().getStatusCode();
        if (status == 200) {
            if(messageValue.equals(message)){
                logger.info("返回的code:[" + codeValue + "]");
                logger.info("返回的message:[" + messageValue + "]");
                logger.info("返回的JSON为：" + objResponse);
                logger.info("调用[" + message + "]接口成功");
                System.out.println("调用[" + message + "]接口成功");
                logger.info("********************************************************************");
            }else {
                logger.error("返回的code:[" + codeValue + "]");
                logger.error("返回的message:[" + messageValue + "]");
                logger.error("返回的JSON为：" + objResponse);
                logger.error("调用[" + message + "]接口失败，实际返回Code是:" + codeValue);
                System.err.println("调用[" + message + "]接口失败，实际返回Code是:" + codeValue);
                logger.info("********************************************************************");
            }
        } else{
            logger.error("调用[" + message + "]接口失败，状态码为：" + status);
            System.err.println("调用[" + message + "]接口失败，状态码为：" + status);
            logger.info("********************************************************************");
        }
    }

    /****************************************************************************砼车****************************************************************************/

    /**
     *　　sendUrl（远程请求的URL）
     *　　param（远程请求参数）
     *　　JSONObject（远程请求返回的JSON）
     */
    public static JSONObject sendPostUrl(String url, String param){
        PrintWriter out = null;
        BufferedReader in = null;
        JSONObject jsonObject = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
            jsonObject = JSONObject.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return jsonObject;
    }

    //配合返回JSONObject使用，判断接口调用是否成功
    public void AssertJSONPost(JSONObject jsonObject,String code,String message){
        String codeValue = jsonObject.get("code").toString();
        String messageValue = jsonObject.get("message").toString();
        if(codeValue.equals(code)){
            logger.info("返回的code:[" + codeValue + "]");
            logger.info("返回的message:[" + messageValue + "]");
            logger.info("返回的JSON为：" + jsonObject);
            logger.info("调用接口返回成功");
            logger.info("********************************************************************");
            Assert.assertEquals(message,messageValue);
        }else {
            logger.error("返回的code:[" + codeValue + "]");
            logger.error("返回的message:[" + messageValue + "]");
            logger.error("返回的JSON为：" + jsonObject);
            logger.error("调用[" + message + "]接口失败，实际返回Code是:" + codeValue);
            logger.info("********************************************************************");
            Assert.assertEquals(message,messageValue);
        }
    }


    //另外一种接口刷入方法
    public static void readContentFromPost(String url) throws IOException {
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(url);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();

        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");

        // Post 请求不能使用缓存
        connection.setUseCaches(false);

        connection.setInstanceFollowRedirects(true);

        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection
                .getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        //carType=1&carLicenseNo=%E8%87%AAA10006&carNumber=%E8%87%AA006&unitId=8466&adder=1271&workState=3
//        String content = "tel=" + URLEncoder.encode("17999999901", "UTF-8");
//        content +="&securityCode="+URLEncoder.encode("157935", "UTF-8");
        String content = "carType=1";
        content +="&carLicenseNo=%E8%87%AAA18004";
        content +="&carNumber=8004";
        content +="&unitId=8466";
        content +="&adder=1271";
        content +="&workState=3";
        content +="&token=e3VuaXRJZDonODQ2NicsIGxvZ2luTmFtZTonMTc5OTk5OTk5MDInLCBsYXN0VmFsaWREYXRlOicyMDE4LTA3LTA1IDE1OjQwOjQ0JywgaWQ6JzEyNzEnLCBwaG9uZTonMTc5OTk5OTk5MDInLCB0aXRsZTon5LiK5rW35bu65bel5p2Q5paZ5bel56iL5pyJ6ZmQ5YWs5Y(46ZW)5qGl5pCF5ouM56uZJywgdXNlclBpYzonZGVmYXVsdC9kZWZhdWx0LnBuZycsIHVzZXJSb2xlOic0JywgdXNlck5hbWU6J(WPkei0p(euoScsIHJlZ1RpbWU6JzIwMTgtMDUtMjIgMTY6MTE6MjknLCB2ZXJzaW9uVHlwZTonbm9ybWFsJywgbGFzdE9ubGluZVRpbWU6JzIwMTgtMDYtMjggMTU6NDA6NDQnLCBhcHBJZDondG9uZ2NoZSd9";
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);

        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }

        reader.close();
        connection.disconnect();
    }

    //没有GET请求体专用
    public void AssertGet(String Url,String message) throws IOException{
        HttpGet httpGet = new HttpGet(Url);
        HttpResponse response = HttpClientBuilder.create().build().execute(httpGet);
        int status = response.getStatusLine().getStatusCode();
        if (status == 200) {
            logger.info("调用[" + message + "]接口成功");
            System.out.println("调用[" + message + "]接口成功");
        } else {
            logger.error("调用[" + message + "]接口失败，状态码为：" + status);
            System.err.println("调用[" + message + "]接口失败，状态码为：" + status);
        }
    }

}






















