package ixijian_main.defaultdata;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.pageshelper.login.AdminLoginPageHelper;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import utils.AESCTools;
import utils.MySqlUtil;
import utils.SeleniumUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultAccount extends BaseParpareTestAdmin {

    private Logger logger = Logger.getLogger(DefaultAccount.class);
//    String source = "ixijian_dev_encrypt";

    /**
     * 重置账号
     * @param phone
     * @param source
     * @param totalMoney
     */
    public void defaultAccount(String phone,String source,String totalMoney){
        logger.info("***************************账户重置开始，账号为:[" + phone + "]***************************");
        String zero = "0.00";
        String AesPhoneSql = "SELECT to_base64(AES_ENCRYPT('" + phone + "', '8e11000c294ed406'));";
        phone = MySqlUtil.getInstance().mySqlCURD_URI(source,AesPhoneSql);
        String AesTotalMoneySql = "SELECT to_base64(AES_ENCRYPT('" + totalMoney + "', '8e11000c294ed406'));";
        String AesTotalMoney = MySqlUtil.getInstance().mySqlCURD_URI(source,AesTotalMoneySql);
        String AesZeroSql = "SELECT to_base64(AES_ENCRYPT('" + zero + "', '8e11000c294ed406'));";
        zero = MySqlUtil.getInstance().mySqlCURD_URI(source,AesZeroSql);
        // 获取userid
        String useridSql = "SELECT `user_id` FROM `user` where `phone` = '" + phone + "'";
        String userid = MySqlUtil.getInstance().mySqlCURD_URI(source,useridSql);
        // 重置account账户
        String accountSql = "UPDATE `account` set `total`='" + AesTotalMoney + "',`income`='"+ AesTotalMoney +
                "',`expend`='" + zero + "',`frost`='" + zero + "', `await`='" + zero + "',`balance`='" + AesTotalMoney + "',`repay`='" + zero + "',`cash`='" + zero + "',`tender`='" + zero + "'  where `user_id`='" + userid + "';";
        // 重置account_log
        String accountlogSql = "DELETE FROM `account_log` where `user_id` = '" + userid + "';";
        // 重置borrow_tender
        String borrowtenderSql = "DELETE FROM `borrow_tender` where `user_id` = '" + userid + "';";
        // 重置coupon
        String couponSql = "DELETE FROM `coupon` where `user_id` = '" + userid + "';";
        // 重置coupon_use
        String couponuseSql = "DELETE FROM `coupon_use` where `user_id` = '" + userid + "';";
        // 重置borrow_repay
        String borrowrepaySql = "DELETE FROM `borrow_repay` where `user_id` = '" + userid + "';";
        // 重置borrow_recover
        String borrowrecoverSql = "DELETE FROM `borrow_recover` where `user_id` = '" + userid + "';";
        // 重置points
        String pointsSql = "UPDATE `points` set `income`='0.00', `expend`='0.00', `balance`='0.00' where `user_id`= '" + userid + "';";
        // 重置points_log
        String pointslogSql = "DELETE FROM `points_log` where `user_id` = '" + userid + "';";
        // 重置points_assignment
        String pointsassignmentSql = "DELETE FROM `points_assignment` where `user_id` = '" + userid + "';";
        // 重置收货地址points_shipping_address
        String pointsshippingaddress = "DELETE FROM `points_shipping_address` where `user_id` = '" + userid + "';";
        //重置activity_times
        String activitytimesSql = "DELETE FROM `activity_times` where `user_id` = '" + userid + "';";

        MySqlUtil.getInstance().mySqlCURD_URI(source,accountSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,accountlogSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,borrowtenderSql);
//        MySqlUtil.getInstance().mySqlCURD_URI(source,couponSql);
//        MySqlUtil.getInstance().mySqlCURD_URI(source,couponuseSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,borrowrepaySql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,borrowrecoverSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,pointsSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,pointslogSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,pointsassignmentSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,pointsshippingaddress);
        MySqlUtil.getInstance().mySqlCURD_URI(source,activitytimesSql);
        logger.info("***************************账户重置完成***************************");
    }


    /**
     * 账号初始化
     * @param phone
     * @param source
     */
    public void initAccount(String source,String phone){
        logger.info("***************************账户重置开始，账号为:[" + phone + "]***************************");
        System.out.println("***************************账户重置开始，账号为:[" + phone + "]***************************");
        String AesPhoneSql = "SELECT to_base64(AES_ENCRYPT('" + phone + "', '8e11000c294ed406'));";
        String phone1 = MySqlUtil.getInstance().mySqlCURD_URI(source,AesPhoneSql);
        // 获取userid
        String useridSql = "SELECT `user_id` FROM `user` where `phone` = '" + phone1 + "'";
        String userid = MySqlUtil.getInstance().mySqlCURD_URI(source,useridSql);
        // 重置account账户
        String accountSql = "DELETE FROM `account` where `user_id` = '" + userid + "';";
        // 重置account_log
        String accountlogSql = "DELETE FROM `account_log` where `user_id` = '" + userid + "';";
        // 重置borrow_tender
        String borrowtenderSql = "DELETE FROM `borrow_tender` where `user_id` = '" + userid + "';";
        // 重置coupon
        String couponSql = "DELETE FROM `coupon` where `user_id` = '" + userid + "';";
        // 重置coupon_use
        String couponuseSql = "DELETE FROM `coupon_use` where `user_id` = '" + userid + "';";
        // 重置borrow_repay
        String borrowrepaySql = "DELETE FROM `borrow_repay` where `user_id` = '" + userid + "';";
        // 重置borrow_recover
        String borrowrecoverSql = "DELETE FROM `borrow_recover` where `user_id` = '" + userid + "';";
        // 重置points
        String pointsSql = "DELETE FROM `points` where `user_id` = '" + userid + "';";
        // 重置points_log
        String pointslogSql = "DELETE FROM `points_log` where `user_id` = '" + userid + "';";
        // 重置points_assignment
        String pointsassignmentSql = "DELETE FROM `points_assignment` where `user_id` = '" + userid + "';";
        // 重置收货地址points_shipping_address
        String pointsshippingaddress = "DELETE FROM `points_shipping_address` where `user_id` = '" + userid + "';";
        // 重置activity_times
        String activitytimesSql = "DELETE FROM `activity_times` where `user_id` = '" + userid + "';";
        // 重置user_login
        String userLoginSql = "DELETE FROM `user_login` where `user_id` = '" + userid + "';";
        // 重置user_realname
        String userRealnameSql = "DELETE FROM `user_realname` where `user_id` = '" + userid + "';";
        // 重置account_bank
        String accountBankSql = "DELETE FROM `account_bank` where `user_id` = '" + userid + "';";
        // 重置activity_packet_receive
        String activityPacketReceiveSql = "DELETE FROM `activity_packet_receive` where `phone` = '" + phone + "';";
        // 重置user_invite
        String userInviteSql = "DELETE FROM `user_invite` where `invite_user_id` = '" + userid + "';";

        // 重置user
        String userSql = "DELETE FROM `user` where `user_id` = '" + userid + "';";

        MySqlUtil.getInstance().mySqlCURD_URI(source,accountSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,accountlogSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,borrowtenderSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,couponSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,couponuseSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,borrowrepaySql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,borrowrecoverSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,pointsSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,pointslogSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,pointsassignmentSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,pointsshippingaddress);
        MySqlUtil.getInstance().mySqlCURD_URI(source,activitytimesSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,userLoginSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,userRealnameSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,accountBankSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,activityPacketReceiveSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,userInviteSql);

        MySqlUtil.getInstance().mySqlCURD_URI(source,userSql);

        logger.info("***************************账户重置完成***************************");
        System.out.println("***************************账户重置完成***************************");
    }

    /**
     * 更新推荐人和被推荐人到当天
     * @param phoneInvite   上线手机号
     * @param phone1    被推荐人1
     * @param phone2    被推荐人2
     * @param phone3    被推荐人3
     */
    public void updateInviteTimeToday(String source,String phoneInvite,String phone1,String phone2,String phone3){
        Date date = new Date();
        // 修改被推荐人注册时间和绑定推荐时间修改到测试当天
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String userCreateTime = sdf2.format(date);
        String phoneInviteSql = "UPDATE `user_invite` SET `invite_time` = '" + userCreateTime + "',`create_time` = '" + userCreateTime + "' " + "WHERE `invite_user_id` = (select `user_id` from `user` WHERE `phone` = '" + phoneInvite + "')";
        String phone1Sql = "UPDATE `user` SET `create_time`='" + userCreateTime + "', `UPDATE_time`='" + userCreateTime + "' WHERE  `phone` ='" + phone1 + "'";
        String phone2Sql = "UPDATE `user` SET `create_time`='" + userCreateTime + "', `UPDATE_time`='" + userCreateTime + "' WHERE  `phone` ='" + phone2 + "'";
        String phone3Sql = "UPDATE `user` SET `create_time`='" + userCreateTime + "', `UPDATE_time`='" + userCreateTime + "' WHERE  `phone` ='" + phone3 + "'";
        MySqlUtil.getInstance().mySqlCURD_URI(source,phoneInviteSql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,phone1Sql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,phone2Sql);
        MySqlUtil.getInstance().mySqlCURD_URI(source,phone3Sql);
    }

    /**
     * 添加卡券
     * @param phone 手机号
     * @param strategyCodeId 卡券规则id
     */
    public void addCoupon(SeleniumUtil seleniumUtil, String source, String phone, String strategyCodeId){
        seleniumUtil.pause(2000);
        //等待登录页面加载
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil, timeOut);
        //输入登录信息
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil, "liuweiyi", "1qaz2wsx");
        //等待页面加载完成
        seleniumUtil.setBrowserSize(400,800);
        seleniumUtil.pause(1000);
        String strategyCode = MySqlUtil.getInstance().mySqlCURD_URI(source,"SELECT cs.strategy_code FROM coupon_strategy cs WHERE cs.coupon_strategy_id='" + strategyCodeId + "';");
        seleniumUtil.pause(1000);
        //刷入需要的卡券
        seleniumUtil.get(testAdminUrl + "/coupon/addCoupon.htm?strategyCode=" + strategyCode + "&phone=" + phone + "&num=1");
    }

    /**
     * 修改user表中的实名状态
     * @param phone
     * @return
     * @throws Exception
     */
    public String setUserRealNameStatus(String source,String phone){
        String realnameId = phone.substring(9, 11);
        System.out.println("修改User表中的实名状态(尾号2位):" + realnameId);
        String realname = "二级" + realnameId + "客户";
        System.out.println("修改User表中的实名状态(实名名称):" + realname);
        realname = AESCTools.AESAdd(realname);
        phone = AESCTools.AESAdd(phone);
        System.out.println("修改User表中的实名状态(手机号加密处理):" + phone);
        // 修改真实用户的用户名和状态
        String updateUserStatusSql = "UPDATE `user` SET `realname` = '" + realname + "', `realname_status`='1' WHERE `phone` = '" + phone + "';";
        MySqlUtil.getInstance().mySqlCURD_URI(source,updateUserStatusSql);
        return realnameId;
    }

    /**
     * 添加银行卡
     * @param source
     * @param userId
     * @param phone
     */
    public void setBankCard(String source,String userId,String phone){
        String BankCardCountSql = "SELECT count(*) FROM `account_bank` WHERE `user_id` = '" + userId + "';";
        String BankCardCount = MySqlUtil.getInstance().mySqlCURD_URI(source,BankCardCountSql);

        if(BankCardCount.equals("1")){
            logger.info(">>>>>>>>>>>>>>>>>>>>>银行卡已存在，不需要进行绑定<<<<<<<<<<<<<<<<<<<<");
        }else if(BankCardCount.equals("0")){
            System.out.println("添加银行卡:"+userId);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd");
            String timestamp = simpleDateFormat.format(date);
            String userIdSub = userId.substring(4,8);
            String account = "6226" + timestamp + "1166"+ userIdSub;
            account = AESCTools.AESAdd(account);
            System.out.println("添加银行卡:"+account);
            String bankCardSql = "INSERT INTO `account_bank` (`user_id`,`status`,`account`,`account_type`,`phone`,`bank_name`,`bank_code`) " +
                    "VALUES ('" + userId + "','1','" + account + "','11','" + phone + "','招商银行','CMB');";
            MySqlUtil.getInstance().mySqlCURD_URI(source,bankCardSql);
            logger.info(">>>>>>>>>>>>>>>>>>>>>银行卡绑定成功<<<<<<<<<<<<<<<<<<<<");
        }else{
            logger.error(">>>>>>>>>>>>>>>>>>>>>银行卡已存在[" + BankCardCount + "]条数据，请进行人工处理<<<<<<<<<<<<<<<<<<<<");
        }
    }

    /**
     * 添加投资签章
     * @param userId
     * @param phone
     */
    public void setSignature_account(String source,String userId,String phone){
        phone = phone.substring(8,11);
        String signatureSql = "INSERT INTO  `signature_account`  (`user_id`,`account_type`,`account_id`,`id_no`,`name`,`sign`) VALUES  ('" + userId + "','2','B7DA6CAAB168431D84416283B0C87EEA','310103198801024074'," +
                "'二级" + phone + "'," +
                "   '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');";
        MySqlUtil.getInstance().mySqlCURD_URI(source,signatureSql);
    }

    /**
     * 实名
     * 1、加入实名记录
     * 2、修改user表中的实名状态
     * @param source
     * @param phone
     */
    public void setRealName(String source,String phone){
        String realnameId = setUserRealNameStatus(source,phone);
        phone = AESCTools.AESAdd(phone);
        System.out.println("加入实名记录(手机号加密处理):"+phone);
        String userIdSql = "SELECT `user_id` FROM `user` WHERE `phone` = '" + phone + "'";
        String userId = MySqlUtil.getInstance().mySqlCURD_URI(source,userIdSql);
        System.out.println("加入实名记录(获取user_id):"+userId);
        String idCard = "310103198804" + realnameId + "34" + realnameId;
        idCard = AESCTools.AESAdd(idCard);
        System.out.println("加入实名记录(身份证号加密处理):"+idCard);
        String name = "二级" + realnameId + "客户";
        name = AESCTools.AESAdd(name);
        System.out.println("加入实名记录(用户实名加密处理):"+name);
        Date date = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String userCreateTime = sdf2.format(date);
        String setRealNameSql = "INSERT INTO `user_realname` ( `gender`, `id_card`, `update_time`, `user_id`, `create_time`, `version`, `name`) "
                + "VALUES ( 'man', '" + idCard + "', '" + userCreateTime + "', '" + userId + "', '" + userCreateTime + "', '0', '" + name + "');";
        try{
            MySqlUtil.getInstance().mySqlCURD_URI(source,setRealNameSql);
        }catch (Exception e){
            logger.info(">>>>>>>>>>>>>>>>>>>>>身份证已存在，不需要进行绑定<<<<<<<<<<<<<<<<<<<<");
        }

    }

    public static void main(String[] args) {
        DefaultAccount defaultAccount = new DefaultAccount();
//        defaultAccount.defaultAccount("17899999966","ixijian_dev_encrypt","800000.18");
//        defaultAccount.updateInviteTimeToday("17999999910","17999999911","17999999912","17999999913");
//        defaultAccount.setRealName("ixijian_dev_encrypt","17999999802");
//        defaultAccount.initAccount("ixijian_dev_encrypt","17999999991");
        defaultAccount.setRealName("ixijian_dev_encrypt","13817504921");
//        defaultAccount.setUserRealNameStatus("ixijian_dev_encrypt","17899999962");
    }
}













