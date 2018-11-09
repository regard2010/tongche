package ixijian_test.test;

/**
 * 多接口连续调用范例，可以直接用于参考复制接口测试
 */
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.testng.annotations.Test;

import utils.HttpRequester;
import utils.TestUrl;


public class Borrow_001_BorrowSuccessFunction_Test {
	
	String phone = "13916072538";
	
	@Test
	public void borrowInsert() throws Exception{
		//第一个登录接口
		String loginUrl = TestUrl.getPcURL() + "/user/doLogin.htm";
		//初始化HttpClient后就一直使用这个实例
		HttpClient httpClient = HttpRequester.getHttpClient();
		PostMethod postMethod1 = HttpRequester.goPostOne(loginUrl,httpClient);
		postMethod1.setParameter("loginName", phone);
		postMethod1.setParameter("loginPassword", "MTExMTEx");
		HttpRequester.goPostTwo(httpClient, postMethod1, "登录");
		
		//第二个借款返利申请接口
		String borrowUrl = TestUrl.getPcURL() + "/tender/tenderBorrow.htm";
		PostMethod postMethod2 = HttpRequester.goPostOne(borrowUrl,httpClient);
		postMethod2.setParameter("borrowId", "2092");
		postMethod2.setParameter("account", "100");
		postMethod2.setParameter("payPassword", "MTExMTEx");
		postMethod2.setParameter("couponId", "");
		postMethod2.setParameter("extraValue", "");
		postMethod2.setParameter("packetId", "");
		postMethod2.setParameter("experienceId", "");
		postMethod2.setParameter("interestId", "");
		HttpRequester.goPostTwo(httpClient, postMethod2, "投资");
		
	}
}
