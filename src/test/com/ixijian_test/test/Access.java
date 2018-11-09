package ixijian_test.test;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获得access_token
 */
public class Access {


    @Test
    public void testHttpCall() throws IOException {
        //传入URL
        HttpPost request = new HttpPost("http://192.168.16.199:8080/oauth/token");

        //传入请求头参数
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.addHeader("Authorization", "Basic aW9zLWNsaWVudDppb3M=");

        //传入请求体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("client_id","ios-client"));
        params.add(new BasicNameValuePair("client_secret","ios"));
        params.add(new BasicNameValuePair("scope","read"));
        params.add(new BasicNameValuePair("username","13916072538"));
        params.add(new BasicNameValuePair("password","eeB+S02vcj+wS5hy+PXjug=="));
        params.add(new BasicNameValuePair("grant_type","password"));
        params.add(new BasicNameValuePair("version","1.3.3"));

        request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

        // when
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // then
        HttpEntity entity = response.getEntity();
        String jsonString = EntityUtils.toString(entity);
        System.out.println(jsonString);
        //获得json
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        //得到access_token
        String jo = jsonObject.getString("access_token");
        System.out.println("access_token:[" + jo + "]");
    }
}
