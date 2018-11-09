package ixijian_test.api.tender;

import ixijian_main.api.tender.TenderAPIFlow;
import utils.MySqlUtil;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * 投资接口4参数相关
 * 用户情况：1、全有(四要素);2、全没有(未开户);3、未绑卡(可能是解绑)
 * @author shuailiu
 *
 */
public class IOSTender_001_TenderSuccessFunction_Test {

	TenderAPIFlow tenderAPIFlow = new TenderAPIFlow();
	String username = "17701748136";
	String client = "ios";
	String Coupon_codeSql = "SELECT coupon_code FROM coupon WHERE user_id=(SELECT user_id FROM `user` WHERE phone = " + username + ")AND coupon_type = \"interest\" AND coupon_status = 0 LIMIT 1;";

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️
	 * 2.投资金额☑️
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Test(priority = 1)
	public void tenderSuccess() throws IOException {
		String coupon_code = MySqlUtil.getInstance().mySqlCURD(Coupon_codeSql);
		tenderAPIFlow.AdTender(username,"123456",client,"2116","1000",coupon_code,"123456","投资");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额☑️️
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Test(priority = 2)
	public void tenderNoBorrowId() throws IOException {
		String coupon_code = MySqlUtil.getInstance().mySqlCURD(Coupon_codeSql);
		tenderAPIFlow.AdTender(username,"123456",client,"","1000",coupon_code,"123456","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额✖️️️
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Test(priority = 3)
	public void tenderNoBorrowIdNoAmount() throws IOException {
		String coupon_code = MySqlUtil.getInstance().mySqlCURD(Coupon_codeSql);
		tenderAPIFlow.AdTender(username,"123456",client,"","",coupon_code,"123456","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额✖️️️
	 * 3.卡券编号✖️️
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Test(priority = 4)
	public void tenderNoBorrowIdNoAmountNoCouponCode() throws IOException {
		tenderAPIFlow.AdTender(username,"123456",client,"","","","123456","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额✖️️️
	 * 3.卡券编号✖️️
	 * 4.支付密码✖️️
	 * 错误信息️
	 */
	@Test(priority = 5)
	public void tenderNoBorrowIdNoAmountNoCouponCodeNoPayPassword() throws IOException {
		tenderAPIFlow.AdTender(username,"123456",client,"","","","","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额✖️️️
	 * 3.卡券编号✖️️
	 * 4.支付密码✖️️
	 * 错误信息️
	 */
	@Test(priority = 6)
	public void tenderNoAmountNoCouponCodeNoPayPassword() throws IOException {
		tenderAPIFlow.AdTender(username,"123456",client,"2116","","","","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额☑️️️️
	 * 3.卡券编号✖️️
	 * 4.支付密码✖️️
	 * 错误信息️
	 */
	@Test(priority = 7)
	public void tenderNoCouponCodeNoPayPassword() throws IOException {
		tenderAPIFlow.AdTender(username,"123456",client,"2116","1000","","","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额☑️️️️
	 * 3.卡券编号☑️️️
	 * 4.支付密码✖️️️️
	 * 错误信息️
	 */
	@Test(priority = 8)
	public void tenderNoPayPassword() throws IOException {
		String coupon_code = MySqlUtil.getInstance().mySqlCURD(Coupon_codeSql);
		tenderAPIFlow.AdTender(username,"123456",client,"2116","1000",coupon_code,"","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额☑️️️️
	 * 3.卡券编号✖️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Test(priority = 9)
	public void tenderNoCouponCode() throws IOException {
		tenderAPIFlow.AdTender(username,"123456",client,"2116","1000","","123456","投资");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额✖️️️️️
	 * 3.卡券编号✖️️️️
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */
	@Test(priority = 10)
	public void tenderNoAmountNoCouponCode() throws IOException {
		tenderAPIFlow.AdTender(username,"123456",client,"2116","","","123456","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额（带小数点）：100.88
	 *️️3.卡券编号✖️️️️️
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */
	@Test(priority = 11)
	public void tenderErrorAmountNoCouponCode() throws IOException {
		tenderAPIFlow.AdTender(username,"123456",client,"2116","100.88","","123456","2001");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额，可投资金额(10000)，投资金额(20000)
	 *️️3.卡券编号✖️️️️️
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */
	@Test(priority = 12)
	public void tenderUpAmountNoCouponCode() throws IOException {
		tenderAPIFlow.AdTender(username,"123456",client,"2116","2000000","","123456","2001");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额:10000
	 *️️3.卡券编号:1.99%加息券对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */



	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:1个月️️️
	 * 2.投资金额:10000
	 *️️3.卡券编号:1.99%加息券对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:3个月️️️
	 * 2.投资金额:10000
	 *️️3.卡券编号:1.99%加息券对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:6个月️️️
	 * 2.投资金额:10000
	 *️️3.卡券编号:1.99%加息券对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */

	/**
	 * 未登录
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额☑️️️️️️
	 * 3.卡券编号☑️️️️️
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */
}
