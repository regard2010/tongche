package kaipai_test.admin;

import ixijian_main.base.BaseParpareTestCrm;
import ixijian_main.pageshelper.login.AdminLoginPageHelper;
import ixijian_main.pageshelper.login.CrmLoginPageHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.MySqlUtil;
import utils.TestUrl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AcutionCreateAdmin_001_AddAuction_Test extends BaseParpareTestCrm {

    private static Logger logger = Logger.getLogger(AcutionCreateAdmin_001_AddAuction_Test.class);

    //*选择数据库源
    String source = "kaipai_dev";


    @Parameters({"beginTime","endTime","count"})
    @Test
    public void newAuctionCreate(String beginTime,String endTime,int count) {
        //等待登录页面加载
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil, timeOut);
        //输入登录信息
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil, "admin", "1234ad");
        seleniumUtil.pause(1000);
        seleniumUtil.setBrowserSize(400,800);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String timestamp = simpleDateFormat.format(date);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始【添加专场】<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.get(TestUrl.getKaiPaiAdminUrl() + "/auction/add.htm?" +
                "companyId=8&" +
                "title=测试专场" + timestamp + "&" +
                "beginTime=" + beginTime + "&" +
                "endTime=" + endTime + "&" +
                "margin=5000&" +
                "bidLadders=%5B%7Bup%3A0%2C+down%3A+1000%2C+val%3A100%7D%2C%7Bup%3A1000%2C+down%3A+5000%2C+val%3A200%7D%2C%7Bup%3A5000%2C+down%3A+10000%2C+val%3A500%7D%2C%7Bup%3A10000%2C+down%3A+50000%2C+val%3A1000%7D%2C%7Bup%3A50000%2C+down%3A+100000%2C+val%3A2000%7D%2C%7Bup%3A100000%2C+down%3A+200000%2C+val%3A5000%7D%2C%7Bup%3A200000%2C+down%3A+10000000%2C+val%3A10000%7D%5D&" +
                "cover=1535%3B&" +
                "infopic=&" +
                "infoId=127&" +
                "subtitle=%E5%90%84%E7%A7%8D%E4%B8%93%E5%9C%BA%E6%8F%8F%E8%BF%B0");
        seleniumUtil.pause(1000);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【添加专场】完成<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.pause(1000);
        String auctionIdSql = "SELECT `auction_id` FROM `auction` WHERE `title`='测试专场" + timestamp + "';";
        String auctionId = MySqlUtil.getInstance().mySqlCURD_URI(source,auctionIdSql);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始【添加单品】<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        for (int i = 1; i <= count; i++) {
            seleniumUtil.get(TestUrl.getKaiPaiAdminUrl() + "/item/add.htm?" +
                    "auctionId=" + auctionId + "&" +
                    "itemCode=" + timestamp + "0" + i + "&" +
                    "title=0" + i + "%E7%B2%89%E5%BD%A9%E5%9B%9B%E5%A6%83%E5%8D%81%E5%85%AD%E5%AD%90%E7%BC%A0%E6%9E%9D%E8%8E%B2%E8%B5%8F%E7%93%B6&" +
                    "subtitle=0" + i + "%E7%B2%89%E5%BD%A9%E5%9B%9B%E5%A6%83%E5%8D%81%E5%85%AD%E5%AD%90%E7%BC%A0%E6%9E%9D%E8%8E%B2%E8%B5%8F%E7%93%B6&" +
                    "quantity=1&" +
                    "sequence=1&" +
                    "startingPrice=1000&" +
                    "reservePrice=1000&" +
                    "evaluatePrice=1千-2千&" +
                    "fixedPrice=&" +
                    "premiumRate=0&" +
                    "commissionRate=0&" +
                    "premiumPrice=300&" +
                    "commissionPrice=500&" +
                    "upsetPrice=1000&" +
                    "orderDeadline=7&" +
                    "margin=100&" +
                    "artistId=36&" +
                    "coverId=1537&" +
                    "imgStr=1539%3B1540%3B&" +
                    "exsprops=%5B%5D&" +
                    "artworkId=&" +
                    "props=%5B%7Bprop%3A'%E4%BD%9C%E8%80%85'%2C+val%3A'%E5%BC%A0%E5%A4%A7%E5%8D%83'%7D%2C%7Bprop%3A'%E6%9D%90%E8%B4%A8'%2C+val%3A'%E7%93%B7%E5%99%A8'%7D%2C%7Bprop%3A'%E5%B0%BA%E5%AF%B8'%2C+val%3A'20*40*80'%7D%2C%7Bprop%3A'%E5%B9%B4%E4%BB%A3'%2C+val%3A'%E6%B8%85'%7D%5D");
            seleniumUtil.pause(1000);
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>添加单品:[" + i + "]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            String itemIdSql = "SELECT `item_id` FROM `item` WHERE `item_code` = '" + timestamp + "0" + i + "';";
            String itemId = MySqlUtil.getInstance().mySqlCURD_URI(source,itemIdSql);
            seleniumUtil.get(TestUrl.getKaiPaiAdminUrl() + "/item/verify.htm?auctionId=" + auctionId + "&itemId=" + itemId + "&status=2&reasons=");
            seleniumUtil.pause(1000);
        }
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【添加并审核单品】完成<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.get(TestUrl.getKaiPaiAdminUrl() + "/auction/verify.htm?auctionId=" + auctionId + "&status=2&reasons=");
        seleniumUtil.isCrmContains("提交数据成功");
        seleniumUtil.pause(1000);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【专场审核】完成并上线<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

}
