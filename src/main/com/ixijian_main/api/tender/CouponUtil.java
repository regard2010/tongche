package ixijian_main.api.tender;

import org.apache.log4j.Logger;
import utils.MySqlUtil;

public class CouponUtil {
    private static Logger logger = Logger.getLogger(CouponUtil.class);

    //使用静态内部类创建外部类对象
    private static class Coupon{
        private static CouponUtil couponUtil = new CouponUtil();
    }

    //获取InterFaceUtil实例
    public static CouponUtil getInstance(){
        return Coupon.couponUtil;
    }

    /**
     * 满100返10元
     * @param username
     * @return 返回红包券码
     */
    public String getRedPacket(String username){
        String redPacketSql = "SELECT coupon_code " +
                "FROM coupon " +
                "WHERE user_id = ( SELECT user_id FROM `user` WHERE phone = " + username + ")" +
                "AND strategy_id = '283' " +
                "AND coupon_type = 'packet' " +
                "AND coupon_status = 0 LIMIT 1;";
        String redPacketCode = MySqlUtil.getInstance().mySqlCURD(redPacketSql);
        return redPacketCode;
    }

    /**
     * 0.5%加息券
     * @param username 手机号
     * @param face_value 加息券面值
     * @return 返回加息券券码
     */
    public String getInterest(String username,String face_value){
        String InterestSql = "SELECT coupon_code " +
                "FROM coupon " +
                "WHERE user_id = ( SELECT user_id FROM `user` WHERE phone = " + username + ")" +
                "AND face_value = " + face_value + "" +
                "AND coupon_type = 'interest' " +
                "AND coupon_status = 0 LIMIT 1;";
        String InterestCode = MySqlUtil.getInstance().mySqlCURD(InterestSql);
        return InterestCode;
    }

    /**
     * 满100送手机贴膜
     * @param username
     * @return 返回礼品券券码
     */
    public String getGift(String username){
        String giftSql = "SELECT coupon_code " +
                "FROM coupon " +
                "WHERE user_id = ( SELECT user_id FROM `user` WHERE phone = " + username + ")" +
                "AND strategy_id = '291' " +
                "AND coupon_type = 'gift' " +
                "AND coupon_status = 0 LIMIT 1;";
        String giftCode = MySqlUtil.getInstance().mySqlCURD(giftSql);
        return giftCode;
    }
}






















