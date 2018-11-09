package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.Logger;

public class HttpRequester {

	public static Logger logger = Logger.getLogger(HttpRequester.class);
	/**
	 * 向指定url发送POST方法的请求
	 * @param method 指定请求方法：get，post等
	 * @param url 发送请求等url
	 * @param param 请求参数，请求参数是name1=value1&name2=value2的形式
	 * @return result 返回结果
	 */
	public static Map<String, String> sendPost(String method, String url, String param) {
		PrintWriter out = null;
		BufferedReader br = null;
		String result = "";
		int responseCode = 0;
		Map<String, String> map = new HashMap<String, String>();
		try {
			HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
			// 发送POST请求必须设置如下两行
			// 设置可输入、输出
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);

			httpConn.setReadTimeout(150000);
			httpConn.setConnectTimeout(15000);

			// 连接后不自动跳转
			httpConn.setInstanceFollowRedirects(false);

			// 设置通用的请求属性
			httpConn.setRequestProperty("accept", "*/*");
			httpConn.setRequestProperty("connection", "Keep-Alive");
			httpConn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 设置提交方式
			httpConn.setRequestMethod(method);

			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());

			// 发送请求参数
			out.print(param);
			out.flush();
			responseCode = httpConn.getResponseCode();
			map.put("code", String.valueOf(responseCode));
			// 打印http状态码
			if (HttpsURLConnection.HTTP_OK == responseCode) {
				// 定义BufferedReader输入流来读取URL的响应
				br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"));
				String strLine;
				StringBuffer responseBuf = new StringBuffer();
				while ((strLine = br.readLine()) != null) {
					responseBuf.append(strLine);
				}
				result = responseBuf.toString();
				map.put("result", result);
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
//			Map<String, List<String>> map = connection.getHeaderFields();
			/*
			 * // 遍历所有的响应头字段 for (String key : map.keySet()) { System.out.println(key +
			 * "--->" + map.get(key)); }
			 */
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取HttpClient对象(默认编码utf-8)
	 * @return HttpClient对象
	 */
	public static HttpClient getHttpClient() {
		return getHttpClient("UTF-8");
	}

	/**
	 * 按encoding编码获取HttpClient对象
	 * @param encoding 字符编码
	 * @return HttpClient对象
	 */
	public static HttpClient getHttpClient(String encoding) {
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager());
		client.getParams().setContentCharset(encoding);
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getParams().setSoTimeout(10000);
		return client;
	}
	
	/**
	 * 获取Post对象
	 * @param url post请求方法url
	 * @param httpClient 得到httpClient实例
	 * @return
	 */
	public static PostMethod goPostOne(String url,HttpClient httpClient){
		 PostMethod postMethod = new PostMethod(url);
		 return postMethod;
	}
	
	/**
	 * 执行Post并判断状态码
	 * @param httpClient
	 * @param postMethod
	 * @param message 接口名称;验证错误信息时，写入错误信息
	 */
	public static void goPostTwo(HttpClient httpClient,PostMethod postMethod,String message) {
		boolean flag = false;
		try {
			int status = httpClient.executeMethod(postMethod);
			if(status == 200) {
				System.out.println("调用[" + message + "]接口成功");
			}else if(status == 401){
				flag = postMethod.getResponseBodyAsString().contains(message);
//				System.out.println(postMethod.getResponseBodyAsString());
				if(flag == false){
					System.err.println("错误信息[" + message + "]与预期不匹配！！！");
				}else{
					System.out.println("错误信息[" + message + "]与预期匹配。");
				}
			}else{
				System.err.println("调用[" + message + "]接口失败，状态码为：" + status);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
