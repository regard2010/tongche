package ixijian_test.api.tender;

import ixijian_main.admin.flow.personage.NewBorrowApplyPersonMonth_001_AddBorrow_Test;
import ixijian_main.api.tender.CouponUtil;
import ixijian_main.api.tender.TenderAPIFlow;
import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.defaultdata.DefaultAccount;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.InterFaceUtil;
import utils.MySqlUtil;
import utils.TestUrl;

import java.io.IOException;

/**
 * 投资接口4参数相关
 * 用户情况：1、全有(四要素);2、全没有(未开户);3、未绑卡(可能是解绑)
 * 账号区分:13916072538_固定借款发标账号，17701748136_投资账号
 * 初始化时，在phone对应的账号每个种类卡券发10张
 * 收单下单独发标
 * 积分做积分检查
 * @author shuailiu
 *
 */
public class ADTender_001_TenderSuccessFunction_Test  extends BaseParpareTestAdmin {

	TenderAPIFlow tenderAPIFlow = new TenderAPIFlow();
	DefaultAccount defaultAccount = new DefaultAccount();
	private static Logger logger = Logger.getLogger(ADTender_001_TenderSuccessFunction_Test.class);

//	String phone = "17899999903";
	String client = "android";
	String JianZhiXiaoShouBat = "/sellbill/createSellBill.htm?code=21306a2b3c724245916aece785fe72c7";

//	String Coupon_Code_Sql = "SELECT coupon_code FROM coupon WHERE user_id=(SELECT user_id FROM `user` WHERE phone = " + phone + ")AND coupon_type = ";
	String Borrow_Id_Sql = "SELECT b.borrow_id FROM borrow b WHERE b.borrow_yes >= 0 AND b.borrow_yes <= b.borrow_amount AND b.borrow_status = 1 AND b.borrow_period = ";
	String JianZhiSql = "SELECT sb.unsettled_amount FROM sell_bill sb WHERE sb.username = '17999999914' ORDER BY sell_bill_id DESC LIMIT 1;";

//	String Coupon_Code_InterestSql = Coupon_Code_Sql + "\"interest\" AND coupon_status = 0 LIMIT 1;";
//	String Coupon_Code_PacketSql = Coupon_Code_Sql + "\"packet\" AND coupon_status = 0 LIMIT 1;";
//	String Coupon_Code_GiftSql = Coupon_Code_Sql + "\"gift\" AND coupon_status = 0 LIMIT 1;";

	String Borrow_Id_OneMonthSql = Borrow_Id_Sql + "1 ORDER BY borrow_id DESC LIMIT 1;";
	String Borrow_Id_TwoMonthSql = Borrow_Id_Sql + "2 ORDER BY borrow_id DESC LIMIT 1;";
	String Borrow_Id_ThreeMonthSql = Borrow_Id_Sql + "3 ORDER BY borrow_id DESC LIMIT 1;";
	String Borrow_Id_FourMonthSql = Borrow_Id_Sql + "4 ORDER BY borrow_id DESC LIMIT 1;";
	String Borrow_Id_FiveMonthSql = Borrow_Id_Sql + "5 ORDER BY borrow_id DESC LIMIT 1;";
	String Borrow_Id_SixMonthSql = Borrow_Id_Sql + "6 ORDER BY borrow_id DESC LIMIT 1;";

/***********************************************************************************************正常投资*************************************************************************************************/

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️
	 * 2.投资金额☑️
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone","amount","count"})
	@Test
	public void tenderSuccess(String phone,String source,String amount,int count) throws IOException {
			defaultAccount.addCoupon(seleniumUtil,source,phone,"278");
			String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
			String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		for(int i = 0 ; i < count ; i++){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
		}
	}

/***********************************************************************************************参数不全匹配*************************************************************************************************/

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额☑️️
	 * 3.卡券编号☑️(加息券)
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoBorrowIdInterest(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.5");
		tenderAPIFlow.AdTender(phone,"123456",client,"",amount,coupon_code,"123456","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额☑️️
	 * 3.卡券编号☑️(投资红包)
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoBorrowIdPacket(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		tenderAPIFlow.AdTender(phone,"123456",client,"",amount,coupon_code,"123456","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额☑️️
	 * 3.卡券编号☑️(礼品券)
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoBorrowIdGift(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		tenderAPIFlow.AdTender(phone,"123456",client,"",amount,coupon_code,"123456","0002");
	}
	
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额✖️️️
	 * 3.卡券编号☑️(加息券)
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Parameters({"phone"})
	@Test
	public void tenderNoBorrowIdNoAmountInterest(String phone) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.5");
		tenderAPIFlow.AdTender(phone,"123456",client,"","",coupon_code,"123456","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额✖️️️
	 * 3.卡券编号☑️(投资红包)
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Parameters({"phone"})
	@Test
	public void tenderNoBorrowIdNoAmountPacket(String phone) throws IOException {
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		tenderAPIFlow.AdTender(phone,"123456",client,"","",coupon_code,"123456","0002");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号✖️️
	 * 2.投资金额✖️️️
	 * 3.卡券编号☑️(礼品券)
	 * 4.支付密码☑️
	 * 错误信息️
	 */
	@Parameters({"phone"})
	@Test
	public void tenderNoBorrowIdNoAmountGift(String phone) throws IOException {
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		tenderAPIFlow.AdTender(phone,"123456",client,"","",coupon_code,"123456","0002");
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
	@Parameters({"phone"})
	@Test
	public void tenderNoBorrowIdNoAmountNoCouponCode(String phone) throws IOException {
		tenderAPIFlow.AdTender(phone,"123456",client,"","","","123456","0002");
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
	@Parameters({"phone"})
	@Test
	public void tenderNoBorrowIdNoAmountNoCouponCodeNoPayPassword(String phone) throws IOException {
		tenderAPIFlow.AdTender(phone,"123456",client,"","","","","0002");
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
	@Parameters({"phone"})
	@Test
	public void tenderNoAmountNoCouponCodeNoPayPassword(String phone) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"","","","0002");
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
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoCouponCodeNoPayPassword(String phone,String amount) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","","2001");
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
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoPayPassword(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.5");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"","2001");
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
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoCouponCode(String phone,String amount) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
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
	@Parameters({"phone"})
	@Test
	public void tenderNoAmountNoCouponCode(String phone) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"","","123456","0002");
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
	@Parameters({"phone"})
	@Test
	public void tenderErrorAmountNoCouponCode(String phone) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"100.88","","123456","2001");
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
	@Parameters({"phone"})
	@Test
	public void tenderUpAmountNoCouponCode(String phone) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"2000000","","123456","2001");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额:10000
	 *️️3.卡券编号:1.8%加息券对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderCoupon(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTenderCoupon(phone,"123456",client,borrow_id,amount,"123456","0000");
	}

/***********************************************************************************************投资卡券和投资月份交叉用例*************************************************************************************************/

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:1个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:使用[加息券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthInterest(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:2个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:使用[加息券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️

	@Test
	public void tenderTwoMonthInterest(String phone,String amount) throws IOException {
		String coupon_code = MySqlUtil.getInstance().mySqlCURD(Coupon_Code_InterestSql);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_TwoMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"100",coupon_code,"123456","0000");
	}
	 */
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:3个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:1.8%[加息券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthInterest(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:4个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:1.8%[加息券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthInterest(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:5个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:1.8%[加息券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功

	@Test
	public void tenderFiveMonthInterest(String phone,String amount) throws IOException {
		String coupon_code = MySqlUtil.getInstance().mySqlCURD(Coupon_Code_InterestSql);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FiveMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"100",coupon_code,"123456","0000");
	}
	 */
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:6个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:1.8%[加息券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthInterest(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:1个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[投资红包]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthPacket(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:2个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[投资红包]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️

	@Test
	public void tenderTwoMonthPacket(String phone,String amount) throws IOException {
		String coupon_code = MySqlUtil.getInstance().mySqlCURD(Coupon_Code_PacketSql);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_TwoMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"100",coupon_code,"123456","0000");
	}
	 */
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:3个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[投资红包]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthPacket(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:4个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[投资红包]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthPacket(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:5个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[投资红包]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功

	@Test
	public void tenderFiveMonthPacket(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FiveMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}
	 */
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:6个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[投资红包]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthPacket(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:1个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:使用[礼品券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthGift(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:2个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[礼品券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️

	@Test
	public void tenderTwoMonthGift(String phone,String amount) throws IOException {
		String coupon_code = MySqlUtil.getInstance().mySqlCURD(Coupon_Code_GiftSql);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_TwoMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}
	 */
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:3个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[礼品券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthGift(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:4个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[礼品券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthGift(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:5个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[礼品券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功

	@Test
	public void tenderFiveMonthGift(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FiveMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}
	 */
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:6个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:[礼品券]对应的编号️️️️️
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthGift(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:1个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:不使用任何卡券
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthNoCoupon(String phone,String amount) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:2个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:不使用任何卡券
	 * 4.支付密码☑️️️
	 * 投资成功️

	@Test
	public void tenderTwoMonthNoCoupon(String phone,String amount) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_TwoMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
	}
	 */
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:3个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:不使用任何卡券
	 * 4.支付密码☑️️️
	 * 投资成功
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthNoCoupon(String phone,String amount) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:4个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:不使用任何卡券
	 * 4.支付密码☑️️️
	 * 投资成功
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthNoCoupon(String phone,String amount) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:5个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:不使用任何卡券
	 * 4.支付密码☑️️️
	 * 投资成功

	@Test
	public void tenderFiveMonthNoCoupon(String phone,String amount) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FiveMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
	}
	 */
	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号:6个月️️️
	 * 2.投资金额:100
	 *️️3.卡券编号:不使用任何卡券
	 * 4.支付密码☑️️️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthNoCoupon(String phone,String amount) throws IOException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
	}
	
	/**
	 * 未登录
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额☑️️️️️️
	 * 3.卡券编号✖️
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoLoginNoCoupon(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTenderNoLogin(client,borrow_id,amount,"","123456","0002");
	}

	/**
	 * 未登录
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额☑️️️️️️
	 * 3.卡券编号:加息券
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoLoginInterest(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getInterest("","1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTenderNoLogin(client,borrow_id,amount,coupon_code,"123456","0002");
	}

	/**
	 * 未登录
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额☑️️️️️️
	 * 3.卡券编号:红包
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoLoginRedPacket(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getRedPacket("");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTenderNoLogin(client,borrow_id,amount,coupon_code,"123456","0002");
	}

	/**
	 * 未登录
	 * 投资提交
	 * 1.标的编号☑️️️
	 * 2.投资金额☑️️️️️️
	 * 3.卡券编号:礼品券
	 * 4.支付密码☑️️️
	 * 错误信息️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderNoLoginGift(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getGift("");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTenderNoLogin(client,borrow_id,amount,coupon_code,"123456","0002");
	}

	/**
	 * 登录成功(17999999901)未开户账号
	 * 投资提交
	 * 1.标的编号☑️
	 * 2.投资金额☑️
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️

	@Parameters({"phone","amount"})
	@Test
	public void tenderErrorUsernameNoAccount(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest("","1");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender("17999999901","123456",client,borrow_id,"100",coupon_code,"123456","0002");
	}
	 */
	/**
	 * 登录成功()未绑卡
	 * 投资提交
	 * 1.标的编号☑️
	 * 2.投资金额☑️
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️

	@Parameters({"phone","amount"})
	@Test
	public void tenderErrorUsernameNoCard(String phone,String amount) throws IOException {
		String coupon_code = CouponUtil.getInstance().getInterest("","1");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender("17999999901","123456",client,borrow_id,"100",coupon_code,"123456","0002");
	}
	 */
/***********************************************************************************************兼职销售*************************************************************************************************/

	//    {adminUrl}/sellbill/createSellBill.htm?code=21306a2b3c724245916aece785fe72c7 需要执行这个链接

	/**
	 * 登录成功()兼职销售账号是：17999999914;投资账号是：17999999917;使用投资账号投资
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 200
	 * 3.卡券编号☑️ 不用卡券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 查看[兼职销售]给的钱：1元
	 */
	@Test
	public void tenderJianZhiNoCoupon() throws IOException, InterruptedException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender("17999999917","123456",client,borrow_id,"200","","123456","0000");
		InterFaceUtil.getInstance().AssertGet(TestUrl.getAdminURL() + JianZhiXiaoShouBat,"兼职销售");
		Thread.sleep(1000);
		String JianZhiFanXian = MySqlUtil.getInstance().mySqlCURD(JianZhiSql);
		if(JianZhiFanXian.equals("1.00")){
			logger.info("兼职销售记录生成[" + JianZhiFanXian +"]元");
		}else{
			logger.error("兼职销售记录生成失败");
		}
	}

	/**
	 * 登录成功()兼职销售账号是：;投资账号是：;使用投资账号投资
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 200
	 * 3.卡券编号☑️ 红包
	 * 4.支付密码☑️
	 * 投资成功️
	 * 查看[兼职销售]给的钱:1元
	 */
	@Test
	public void tenderJianZhiRedPacket() throws IOException, InterruptedException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		String coupon_code = CouponUtil.getInstance().getRedPacket("17999999917");
		tenderAPIFlow.AdTender("17999999917","123456",client,borrow_id,"200",coupon_code,"123456","0000");
		InterFaceUtil.getInstance().AssertGet(TestUrl.getAdminURL() + JianZhiXiaoShouBat,"兼职销售");
		Thread.sleep(1000);
		String JianZhiFanXian = MySqlUtil.getInstance().mySqlCURD(JianZhiSql);
		if(JianZhiFanXian.equals("1.00")){
			logger.info("兼职销售记录生成[" + JianZhiFanXian +"]元");
		}else{
			logger.error("兼职销售记录生成失败");
		}
	}

	/**
	 * 登录成功()兼职销售账号是：;投资账号是：;使用投资账号投资
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️ 用0.5%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 查看[兼职销售]给的钱:0.38元
	 */
	@Test
	public void tenderJianZhiZeroPercentFiveInterest() throws IOException, InterruptedException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		String coupon_code = CouponUtil.getInstance().getInterest("17999999917","0.5");
		tenderAPIFlow.AdTender("17999999917","123456",client,borrow_id,"200",coupon_code,"123456","0000");
		InterFaceUtil.getInstance().AssertGet(TestUrl.getAdminURL() + JianZhiXiaoShouBat,"兼职销售");
		Thread.sleep(1000);
		String JianZhiFanXian = MySqlUtil.getInstance().mySqlCURD(JianZhiSql);
		if(JianZhiFanXian.equals("0.38")){
			logger.info("兼职销售记录生成[" + JianZhiFanXian +"]元");
		}else{
			logger.error("兼职销售记录生成失败");
		}
	}

	/**
	 * 登录成功()兼职销售账号是：;投资账号是：;使用投资账号投资
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️ 用1.5%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 查看[兼职销售]给的钱:0.13元
	 */
	@Test
	public void tenderJianZhiOnePercentFiveInterest() throws IOException, InterruptedException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		String coupon_code = CouponUtil.getInstance().getInterest("17999999917","1.5");
		tenderAPIFlow.AdTender("17999999917","123456",client,borrow_id,"200",coupon_code,"123456","0000");
		InterFaceUtil.getInstance().AssertGet(TestUrl.getAdminURL() + JianZhiXiaoShouBat,"兼职销售");
		Thread.sleep(1000);
		String JianZhiFanXian = MySqlUtil.getInstance().mySqlCURD(JianZhiSql);
		if(JianZhiFanXian.equals("0.13")){
			logger.info("兼职销售记录生成[" + JianZhiFanXian +"]元");
		}else{
			logger.error("兼职销售记录生成失败");
		}
	}

	/**
	 * 登录成功()兼职销售账号是：;投资账号是：;使用投资账号投资
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️ 用2%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 查看[兼职销售]给的钱:0元
	 */
	@Test
	public void tenderJianZhiTwoInterest() throws IOException, InterruptedException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		String coupon_code = CouponUtil.getInstance().getInterest("17999999917","2");
		tenderAPIFlow.AdTender("17999999917","123456",client,borrow_id,"200",coupon_code,"123456","0000");
		InterFaceUtil.getInstance().AssertGet(TestUrl.getAdminURL() + JianZhiXiaoShouBat,"兼职销售");
		Thread.sleep(1000);
		String JianZhiFanXian = MySqlUtil.getInstance().mySqlCURD(JianZhiSql);
		if(JianZhiFanXian.equals("0.00")){
			logger.info("兼职销售记录生成[" + JianZhiFanXian +"]元");
		}else{
			logger.error("兼职销售记录生成失败");
		}
	}

	/**
	 * 登录成功()兼职销售账号是：;投资账号是：;使用投资账号投资
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️ 用2.5%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 查看[兼职销售]给的钱:0元
	 */
	@Test
	public void tenderJianZhiTwoPercentFiveInterest() throws IOException, InterruptedException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		String coupon_code = CouponUtil.getInstance().getInterest("17999999917","2.5");
		tenderAPIFlow.AdTender("17999999917","123456",client,borrow_id,"200",coupon_code,"123456","0000");
		InterFaceUtil.getInstance().AssertGet(TestUrl.getAdminURL() + JianZhiXiaoShouBat,"兼职销售");
		Thread.sleep(1000);
		String JianZhiFanXian = MySqlUtil.getInstance().mySqlCURD(JianZhiSql);
		if(JianZhiFanXian.equals("0.00")){
			logger.info("兼职销售记录生成[" + JianZhiFanXian +"]元");
		}else{
			logger.error("兼职销售记录生成失败");
		}
	}

	/**
	 * 登录成功()兼职销售账号是：;投资账号是：;使用投资账号投资
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️ 用3.8%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 查看[兼职销售]给的钱:0元
	 */
	@Test
	public void tenderJianZhiThreePercentEightInterest() throws IOException, InterruptedException {
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		String coupon_code = CouponUtil.getInstance().getInterest("17999999917","3.8");
		tenderAPIFlow.AdTender("17999999917","123456",client,borrow_id,"200",coupon_code,"123456","0000");
		InterFaceUtil.getInstance().AssertGet(TestUrl.getAdminURL() + JianZhiXiaoShouBat,"兼职销售");
		Thread.sleep(1000);
		String JianZhiFanXian = MySqlUtil.getInstance().mySqlCURD(JianZhiSql);
		if(JianZhiFanXian.equals("0.00")){
			logger.info("兼职销售记录生成[" + JianZhiFanXian +"]元");
		}else{
			logger.error("兼职销售记录生成失败");
		}
	}

/***********************************************************************************************收单侠*************************************************************************************************/


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️标的总额200
	 * 2.投资金额☑️200
	 * 3.卡券编号☑️不用卡券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 待起息查看是否，收到2%加息券
	 */
	@Parameters({"phone"})
	@Test
	public void tenderSDNotCoupon(String phone) throws IOException{
		NewBorrowApplyPersonMonth_001_AddBorrow_Test newBorrowApplyPersonMonth_001_addBorrow_test = new NewBorrowApplyPersonMonth_001_AddBorrow_Test();
		String borrow_id = newBorrowApplyPersonMonth_001_addBorrow_test.newBorrowApplyPersonOneMonthDriver(seleniumUtil,"200","500","6.8","1","0.5");
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️标的总额200
	 * 2.投资金额☑️200
	 * 3.卡券编号☑️用红包
	 * 4.支付密码☑️
	 * 投资成功️
	 * 待起息查看是否，收到2%加息券
	 */
	@Parameters({"phone"})
	@Test
	public void tenderSDUseRedPacket(String phone) throws IOException{
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️标的总额200
	 * 2.投资金额☑️200
	 * 3.卡券编号☑️用1%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 待起息查看是否，收到2%加息券
	 */
	@Parameters({"phone"})
	@Test
	public void tenderSDUseOnePercentInterest(String phone) throws IOException{
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️标的总额200
	 * 2.投资金额☑️200
	 * 3.卡券编号☑️用2%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 待起息查看是否，收到2%加息券
	 */
	@Parameters({"phone"})
	@Test
	public void tenderSDUseTwoPercentInterest(String phone) throws IOException{
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"2");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️标的总额200
	 * 2.投资金额☑️200
	 * 3.卡券编号☑️用3%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 待起息查看是否，收到3%加息券
	 */
	@Parameters({"phone"})
	@Test
	public void tenderSDUseThreePercentInterest(String phone) throws IOException{
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"3");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️标的总额200
	 * 2.投资金额☑️200
	 * 3.卡券编号☑️用礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 待起息查看是否，收到3%加息券
	 */
	@Parameters({"phone"})
	@Test
	public void tenderSDUseGift(String phone) throws IOException{
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200",coupon_code,"123456","0000");
	}

/***********************************************************************************************积分*************************************************************************************************/

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给1积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthPoints(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"100","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给1积分
	 */


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给2积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthPoints(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"100","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给2积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthPoints(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"100","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给3积分
	 */

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ 100
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给3积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthPoints(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"100","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给90积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthPointsNinety(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"990","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给90积分
	 */

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给180积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthPointsNinety(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"990","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给180积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthPointsNinety(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"990","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给270积分
	 */

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给270积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthPointsNinety(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"990","","123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️ 使用红包
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给90积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthPointsNinetyUseRedPacket(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"990",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️ 使用红包
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给90积分
	 */


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️ 使用红包
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给180积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthPointsNinetyUseRedPacket(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"990",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️ 使用红包
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给180积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthPointsNinetyUseRedPacket(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"990",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️ 使用红包
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给270积分
	 */

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ 990
	 * 3.卡券编号☑️ 使用红包
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给270积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthPointsNinetyUseRedPacket(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getRedPacket(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"990",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用1.8%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给80积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthPointsEightyUseOnePercentEightInterest(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"890",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用1.8%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给80积分
	 */

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用1.8%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给160积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthPointsEightyUseOnePercentEightInterest(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"890",coupon_code,"123456","0000");
	}


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用1.8%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给160积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthPointsEightyUseOnePercentEightInterest(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"890",coupon_code,"123456","0000");
	}


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用1.8%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给240积分
	 */


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用1.8%加息券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给240积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthPointsEightyUseOnePercentEightInterest(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getInterest(phone,"1.8");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"890",coupon_code,"123456","0000");
	}


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给80积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonthPointsEightyUseGift(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"890",coupon_code,"123456","0000");
	}

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给80积分
	 */

	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给160积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonthPointsEightyUseGift(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"890",coupon_code,"123456","0000");
	}


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给160积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonthPointsEightyUseGift(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"890",coupon_code,"123456","0000");
	}


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给240积分
	 */


	/**
	 * 登录成功
	 * 投资提交
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ 890
	 * 3.卡券编号☑️ 使用礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 * 给240积分
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonthPointsEightyUseGift(String phone,String amount) throws IOException{
		String coupon_code = CouponUtil.getInstance().getGift(phone);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"890",coupon_code,"123456","0000");
	}

/***********************************************************************************************多人投资*************************************************************************************************/

	/* 3个账号，卡券分别用加息券、红包和不用 */
	/**
	 * 账号1: ;账号2: ;账号3: ;
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ 账号1:100;账号2:200;账号3:300
	 * 3.卡券编号☑️ 账号1:1%;账号2:红包;账号3:不用
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderOneMonthThreeAccountInterestPacketNoCoupon(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300","","123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:不用
	 * 4.支付密码☑️
	 * 投资成功️
	 */


	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:不用
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderThreeMonthThreeAccountInterestPacketNoCoupon(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300","","123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:不用
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderFourMonthThreeAccountInterestPacketNoCoupon(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300","","123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:不用
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:不用
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderSixMonthThreeAccountInterestPacketNoCoupon(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300","","123456","0000");
	}

	/* 3个账号，卡券分别用加息券、红包和礼品券 */

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderOneMonthThreeAccountInterestPacketGift(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String coupon_code_3 = CouponUtil.getInstance().getGift(phone3);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300",coupon_code_3,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderThreeMonthThreeAccountInterest(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String coupon_code_3 = CouponUtil.getInstance().getGift(phone3);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300",coupon_code_3,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderFourMonthThreeAccountInterest(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String coupon_code_3 = CouponUtil.getInstance().getGift(phone3);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300",coupon_code_3,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;phone3:礼品券
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderSixMonthThreeAccountInterest(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String coupon_code_3 = CouponUtil.getInstance().getGift(phone3);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300",coupon_code_3,"123456","0000");
	}

	/* 3个账号，均用加息券 */

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;phone3:3%
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderOneMonthThreeAccountThreeInterest(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String coupon_code_3 = CouponUtil.getInstance().getInterest(phone3,"3");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300",coupon_code_3,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;phone3:3%
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;phone3:3%
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderThreeMonthThreeAccountThreeInterest(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String coupon_code_3 = CouponUtil.getInstance().getInterest(phone3,"3");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300",coupon_code_3,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;phone3:3%
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderFourMonthThreeAccountThreeInterest(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String coupon_code_3 = CouponUtil.getInstance().getInterest(phone3,"3");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300",coupon_code_3,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;phone3:3%
	 * 4.支付密码☑️
	 * 投资成功️
	 */


	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;phone3:3%
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2","phone3"})
	@Test
	public void tenderSixMonthThreeAccountThreeInterest(String phone1,String phone2,String phone3) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String coupon_code_3 = CouponUtil.getInstance().getInterest(phone3,"3");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"200",coupon_code_2,"123456","0000");
		tenderAPIFlow.AdTender(phone3,"123456",client,borrow_id,"300",coupon_code_3,"123456","0000");
	}

	/* 2个账号，投资金额用最小值和最大值，卡券均用加息券 */

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderOneMonthTwoAccountTwoInterestAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;phone3:3%
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderThreeMonthTwoAccountTwoInterestAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderFourMonthTwoAccountTwoInterestAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;phone3: ;
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ phone1:100;phone2:200;phone3:300
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;phone3:3%
	 * 4.支付密码☑️
	 * 投资成功️
	 */


	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:2%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderSixMonthTwoAccountTwoInterestAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getInterest(phone2,"2");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/* 2个账号，投资金额用最小值和最大值，卡券用加息券和红包 */

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderOneMonthTwoAccountOneInterestOneRedPacketAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderThreeMonthTwoAccountOneInterestOneRedPacketAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderFourMonthTwoAccountOneInterestOneRedPacketAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;
	 * 4.支付密码☑️
	 * 投资成功️
	 */


	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:红包;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderSixMonthTwoAccountOneInterestOneRedPacketAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getRedPacket(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/* 2个账号，投资金额用最小值和最大值，卡券用加息券和礼品券 */

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:礼品券;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderOneMonthTwoAccountOneInterestOneGiftAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getGift(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:礼品券;
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:礼品券;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderThreeMonthTwoAccountOneInterestOneGiftAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getGift(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:礼品券;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderFourMonthTwoAccountOneInterestOneGiftAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getGift(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:礼品券;
	 * 4.支付密码☑️
	 * 投资成功️
	 */


	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;phone2:礼品券;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderSixMonthTwoAccountOneInterestOneGiftAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String coupon_code_2 = CouponUtil.getInstance().getGift(phone2);
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900",coupon_code_2,"123456","0000");
	}

	/* 2个账号，投资金额用最小值和最大值，卡券用加息券和不使用 */

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 1个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderOneMonthTwoAccountOneInterestOneNoCouponAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900","","123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 2个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 3个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderThreeMonthTwoAccountOneInterestOneNoCouponAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900","","123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 4个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderFourMonthTwoAccountOneInterestOneNoCouponAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900","","123456","0000");
	}

	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 5个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */


	/**
	 * phone1: ;phone2: ;
	 * 1.标的编号☑️ 6个月
	 * 2.投资金额☑️ phone1:100;phone2:199900;
	 * 3.卡券编号☑️ phone1:1%;
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone1","phone2"})
	@Test
	public void tenderSixMonthTwoAccountOneInterestOneNoCouponAmountMinMax(String phone1,String phone2) throws IOException{
		String coupon_code_1 = CouponUtil.getInstance().getInterest(phone1,"1");
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		tenderAPIFlow.AdTender(phone1,"123456",client,borrow_id,"100",coupon_code_1,"123456","0000");
		tenderAPIFlow.AdTender(phone2,"123456",client,borrow_id,"199900","","123456","0000");
	}


/***********************************************************************************************投资条数超过100条*************************************************************************************************/

	/**
	 * 登录成功
	 * 1.标的编号☑️ 1个月，1万
	 * 2.投资金额☑️ 账号投资100元一次，投资100次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderOneMonth100(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		for (int i = 0 ; i<=100 ; i++ ){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
		}
	}

	/**
	 * 登录成功
	 * 1.标的编号☑️ 2个月，1万
	 * 2.投资金额☑️ 账号投资100元一次，投资100次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * 登录成功
	 * 1.标的编号☑️ 3个月，1万
	 * 2.投资金额☑️ 账号投资100元一次，投资100次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderThreeMonth100(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		for (int i = 0 ; i<=100 ; i++ ){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
		}
	}

	/**
	 * 登录成功
	 * 1.标的编号☑️ 4个月，1万
	 * 2.投资金额☑️ 账号投资100元一次，投资100次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderFourMonth100(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		for (int i = 0 ; i<=100 ; i++ ){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
		}
	}

	/**
	 * 登录成功
	 * 1.标的编号☑️ 5个月，1万
	 * 2.投资金额☑️ 账号投资100元一次，投资100次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * 登录成功
	 * 1.标的编号☑️ 6个月，1万
	 * 2.投资金额☑️ 账号投资100元一次，投资100次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone","amount"})
	@Test
	public void tenderSixMonth100(String phone,String amount) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		for (int i = 0 ; i<=100 ; i++ ){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,amount,"","123456","0000");
		}
	}

	/**
	 * 登录成功
	 * 1.标的编号☑️ 1个月，2万
	 * 2.投资金额☑️ 账号投资100元一次，投资200次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone"})
	@Test
	public void tenderOneMonth200(String phone) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_OneMonthSql);
		for (int i = 0 ; i<=200 ; i++ ){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200","","123456","0000");
		}
	}

	/**
	 * 登录成功
	 * 1.标的编号☑️ 2个月，1万
	 * 2.投资金额☑️ 账号投资100元一次，投资100次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * 登录成功
	 * 1.标的编号☑️ 3个月，2万
	 * 2.投资金额☑️ 账号投资200元一次，投资100次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone"})
	@Test
	public void tenderThreeMonth200(String phone) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_ThreeMonthSql);
		for (int i = 0 ; i<=200 ; i++ ){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200","","123456","0000");
		}
	}

	/**
	 * 登录成功
	 * 1.标的编号☑️ 4个月，2万
	 * 2.投资金额☑️ 账号投资200元一次，投资200次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone"})
	@Test
	public void tenderFourMonth200(String phone) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_FourMonthSql);
		for (int i = 0 ; i<=200 ; i++ ){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200","","123456","0000");
		}
	}

	/**
	 * 登录成功
	 * 1.标的编号☑️ 5个月，2万
	 * 2.投资金额☑️ 账号投资200元一次，投资200次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */

	/**
	 * 登录成功
	 * 1.标的编号☑️ 6个月，2万
	 * 2.投资金额☑️ 账号投资200元一次，投资200次,投满
	 * 3.卡券编号✖️
	 * 4.支付密码☑️
	 * 投资成功️
	 */
	@Parameters({"phone"})
	@Test
	public void tenderSixMonth200(String phone) throws IOException{
		String borrow_id = MySqlUtil.getInstance().mySqlCURD(Borrow_Id_SixMonthSql);
		for (int i = 0 ; i<=200 ; i++ ){
			tenderAPIFlow.AdTender(phone,"123456",client,borrow_id,"200","","123456","0000");
		}
	}
}























