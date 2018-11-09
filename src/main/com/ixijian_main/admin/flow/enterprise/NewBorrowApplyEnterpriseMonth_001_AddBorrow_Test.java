package ixijian_main.admin.flow.enterprise;

import ixijian_main.api.user.LoginOauthToken;
import ixijian_main.base.BaseParpareTestAdmin;
import ixijian_main.pageshelper.login.AdminLoginPageHelper;
import ixijian_main.pageshelper.login.IndexPcPageHelper;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.InterFaceUtil;
import utils.MySqlUtil;
import utils.SeleniumUtil;
import utils.TestUrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewBorrowApplyEnterpriseMonth_001_AddBorrow_Test extends BaseParpareTestAdmin {

    //*借款方式:1、end(到期一次性还本付息) 2、endday(按天计息到期还本息) 3、month(等额本息)
    String borrowStyle = "end";
    //*借款人利率
    String approveApr = "20";
    //*投资人利率
    String tenderApr = "6.8";
    //*计息方式：tjy:T+1;immediately:即时起息
    String interestType = "immediately";
    //*标的类型可配置：1、per（预收购标）2、youchi（普通标）3、second（新手标）
    String borrowType = "youchi";

    @Parameters({"jieqianjine","valuation","tenderApr","borrowPeriod","borrowAprExtra"})
    @Test
    public void newBorrowApplyEnterpriseOneMonth(String jieqianjine, String valuation, String tenderApr, String borrowPeriod, String borrowAprExtra){
        //等待登录页面加载
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil, timeOut);
        //输入登录信息
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil, "admin", "1234ad");
        //等待页面加载完成
        seleniumUtil.pause(1000);
        //等待首页元素显示出来
        IndexPcPageHelper.waitIndexPcPageLoad(seleniumUtil, timeOut);
        //检查用户是否登录成功
        IndexPcPageHelper.checkIndexPcErrorInfo(seleniumUtil);

        //信息入库
        String EnterpriseUrlFirst = testAdminUrl + "/borrowApply/addInfo.htm?" +
                "applyId=" +
                "&applyType=enterprise" +
                "&phone=13916072538" +
                "&goodsName=企业借款(物品名称)" +
                "&categoryId=1" +
                "&years=tang" +
                "&valuation=" + valuation + "" +
                "&verifyGist=企业借款(评价依据)" +
                "&specific=企业借款(规格)" +
                "&getWay=auction" +
                "&author=企业借款(作者)" +
                "&description=企业借款(物品描述)" +
                "&imgUrls=http%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20171221%2F20171221124415ydYW.png%3Bhttp%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20171221%2F20171221124441Nnf4.png%3Bhttp%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20171221%2F20171221124453yC3D.png%3Bhttp%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20171221%2F20171221124502MijK.png%3B" +
                "&relations=%5B%7B%22name%22%3A%22%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE()%22%2C%22price%22%3A%22200000%22%2C%22auctionDate%22%3A%222018-01-01%22%2C%22specific%22%3A%22%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E8%A7%84%E6%A0%BC)%22%2C%22auctionCompany%22%3A%22%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E6%8B%8D%E5%8D%96%E5%85%AC%E5%8F%B8)%22%2C%22img%22%3A%22http%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20180111%2F20180111194045bQBG.jpg%22%7D%5D" +
                "&comefrom=2" +
                "&sellUserPhone=13116162780" +
                "&borrowAmount=" + jieqianjine + "" +
                "&borrowPeriod=" + borrowPeriod + "" +
                "&borrowPeriodUom=月" +
                "&borrowStyle=" + borrowStyle + "" +
                "&approveApr=" + approveApr + "" +
                "&tenderApr=" + tenderApr + "" +
                "&borrowUse=企业借款(借款用)" +
                "&advisoryFee=0.5" +
                "&credit=1" +
                "&manageFee=0.5" +
                "&insurance=0.5" +
                "&custodyFee=1" +
                "&assurance=1" +
                "&appraisalFee=1" +
                "&impawnRate=40" +
                "&remark=企业借款(备注)";

        seleniumUtil.get(EnterpriseUrlFirst);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("true");

        //物品入库
        String applyIdSql = "SELECT t.apply_id FROM borrow_apply t ORDER BY t.apply_id DESC LIMIT 1;";
        String applyId = MySqlUtil.getInstance().mySqlCURD(applyIdSql);
        String goodsIdSql = "SELECT t.goods_id FROM borrow_apply t WHERE t.apply_id = " + applyId + ";";
        String storageCodeSql = "SELECT t.apply_code FROM borrow_apply t WHERE t.apply_id = " + applyId + ";";
        String goodsId = MySqlUtil.getInstance().mySqlCURD(goodsIdSql);
        String storageCode = MySqlUtil.getInstance().mySqlCURD(storageCodeSql);
        String EnterpriseUrlSecond = testAdminUrl + "/borrowApply/storage.htm?" +
                "goodsId="+goodsId+"&" +
                "applyId="+applyId+"&" +
                "storageCode="+storageCode+"&" +
                "delivery=%E5%88%98%E6%9C%AA%E6%98%93&" +
                "custodian=%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E4%BF%9D%E7%AE%A1%E4%BA%BA)&" +
                "shelfNumber=%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E8%B4%A7%E6%9E%B6%E7%BC%96%E5%8F%B7)&" +
                "storageAddress=%E4%B8%8A%E6%B5%B7%E5%B8%82%E6%B5%A6%E4%B8%9C%E6%96%B0%E5%8C%BA%E9%BE%99%E4%B8%9C%E5%A4%A7%E9%81%934028%E5%BC%8444%E5%8F%B7+%E9%87%91%E7%88%B5%E5%88%AB%E5%A2%85&" +
                "remark=%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E5%A4%87%E6%B3%A8)";
        seleniumUtil.get(EnterpriseUrlSecond);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("true");

        //电子签章
        LoginOauthToken loginOauthToken = new LoginOauthToken();
        String signSql = "SELECT sa.sign FROM signature_account sa WHERE sa.user_id = \"10000309\";";
        String sign = MySqlUtil.getInstance().mySqlCURD(signSql);
        try {
            String access_token = loginOauthToken.ADloginOauthTokenPassword("android","13916072538","123456","登录");
            String Url = TestUrl.getInterFaceNewURLVersion() + "/apply/sign/" + applyId;
            HttpPost httpPost = new HttpPost(Url);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("signature",sign);
            jsonObject.put("ip","192.168.16.120");
            InterFaceUtil.getInstance().setHeader(httpPost,"android-client",access_token);
            StringEntity entity = InterFaceUtil.getInstance().setJSONBody(httpClient,httpPost,jsonObject);
            EntityUtils.toString(entity);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            InterFaceUtil.getInstance().AssertStatus(response,"0000");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //风控审核
        String EnterpriseUrlThree = testAdminUrl + "/borrowApply/risk.htm?" +
                "applyId=" + applyId + "&" +
                "jdbg=Y&" +
                "zywqsm=Y&" +
                "zyprk=Y&" +
                "ccbx=Y&" +
                "smrz=Y&" +
                "zxbg=Y&" +
                "gzzm=Y&" +
                "yhls=Y&" +
                "riskRating=AAA&" +
                "insurance=89&" +
                "insurance=90&" +
                "insurance=91&" +
                "insurance=92&" +
                "insurance=93&" +
                "insurance=94&" +
                "insurance=95";
        seleniumUtil.get(EnterpriseUrlThree);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("true");

        //发布标的
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String borrowCode = sdf.format(date);
        String EnterpriseUrlFour = testAdminUrl + "/borrowApply/publish.htm?" +
                "applyId=" + applyId + "&" +
                "borrowCode=" + borrowCode + "&" +
                "borrowName=玺艺贷-" + borrowCode + "&" +
                "borrowDays=31&" +
                "borrowStyle=%E5%88%B0%E6%9C%9F%E4%B8%80%E6%AC%A1%E6%80%A7%E8%BF%98%E6%9C%AC%E4%BB%98%E6%81%AF&" +
                "borrowType=" + borrowType + "&" +
                "interestType=" + interestType + "&" +
                "borrowFlag=2&" +
                "borrowPeriod=" + borrowPeriod + "&" +
                "tenderMinAmount=100&" +
                "borrowAprInterest=" + approveApr + "&" +
                "tenderMaxAmount=0&" +
                "borrowAprStand=" + tenderApr + "&" +
                "riskLevel=AAA&" +
                "borrowAprExtra="+borrowAprExtra+"&" +
                "insuranceNo=93501067&" +
                "borrowAmount=" + jieqianjine + "&" +
                "zijinliuxiang=%E5%80%9F%E6%9D%A5%E7%9A%84&" +
                "webEnable=Y&" +
                "wapEnable=Y&" +
                "appEnable=Y&" +
                "allowUsePacket=Y&" +
                "allowUseInterest=Y&" +
                "renewFlag=Y&" +
                "isAuction=Y&" +
                "ah=&" +
                "isRepurchase=Y&" +
                "agree=2&" +
                "promotionPartners=%E6%97%A0%E5%90%88%E4%BD%9C%E6%96%B9";
        seleniumUtil.get(EnterpriseUrlFour);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("true");

        //发布标的初审
        String borrowIdSql = "SELECT t.borrow_id FROM borrow_apply t WHERE t.apply_id = " + applyId + ";";
        String borrowId = MySqlUtil.getInstance().mySqlCURD(borrowIdSql);
        String EnterpriseUrlFive = testAdminUrl + "/borrow/verify/firstVerify.htm?" +
                "borrowId=" + borrowId + "&" +
                "verifyStatus=1&" +
                "remark=ready&" +
                "contents=go";
        seleniumUtil.get(EnterpriseUrlFive);
        seleniumUtil.pause(1000);
        String status = seleniumUtil.findElementBy(By.xpath("/html/body/pre")).toString();
        if(seleniumUtil.isAdminContains("{\"success\":false,\"message\":\"系统错误，请稍后再试\"}")){
            seleniumUtil.get(EnterpriseUrlFive);
            seleniumUtil.pause(1000);
            //上线
            String EnterpriseUrlSix = testAdminUrl + "/borrow/verify/immediatelyOnline.htm?borrowId=" + borrowId + "";
            seleniumUtil.get(EnterpriseUrlSix);
            seleniumUtil.pause(1000);
            seleniumUtil.isCrmContains("true");
        }else if(seleniumUtil.isAdminContains("{\"success\":true,\"message\":\"提交申请成功！\"}")){
            seleniumUtil.pause(1000);
            //上线
            String EnterpriseUrlSix = testAdminUrl + "/borrow/verify/immediatelyOnline.htm?borrowId=" + borrowId + "";
            seleniumUtil.get(EnterpriseUrlSix);
            seleniumUtil.pause(1000);
            seleniumUtil.isCrmContains("true");
        }
    }

    public String newBorrowApplyEnterpriseOneMonthDriver(SeleniumUtil seleniumUtil, String jieqianjine, String valuation, String tenderApr, String borrowPeriod, String borrowAprExtra){
        //等待登录页面加载
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil, timeOut);
        //输入登录信息
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil, "admin", "1234ad");
        //等待页面加载完成
        seleniumUtil.pause(1000);
        //等待首页元素显示出来
        IndexPcPageHelper.waitIndexPcPageLoad(seleniumUtil, timeOut);
        //检查用户是否登录成功
        IndexPcPageHelper.checkIndexPcErrorInfo(seleniumUtil);

        //信息入库
        String EnterpriseUrlFirst = testAdminUrl + "/borrowApply/addInfo.htm?" +
                "applyId=" +
                "&applyType=enterprise" +
                "&phone=13916072538" +
                "&goodsName=企业借款(物品名称)" +
                "&categoryId=1" +
                "&years=tang" +
                "&valuation=" + valuation + "" +
                "&verifyGist=企业借款(评价依据)" +
                "&specific=企业借款(规格)" +
                "&getWay=auction" +
                "&author=企业借款(作者)" +
                "&description=企业借款(物品描述)" +
                "&imgUrls=http%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20171221%2F20171221124415ydYW.png%3Bhttp%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20171221%2F20171221124441Nnf4.png%3Bhttp%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20171221%2F20171221124453yC3D.png%3Bhttp%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20171221%2F20171221124502MijK.png%3B" +
                "&relations=%5B%7B%22name%22%3A%22%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE()%22%2C%22price%22%3A%22200000%22%2C%22auctionDate%22%3A%222018-01-01%22%2C%22specific%22%3A%22%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E8%A7%84%E6%A0%BC)%22%2C%22auctionCompany%22%3A%22%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E6%8B%8D%E5%8D%96%E5%85%AC%E5%8F%B8)%22%2C%22img%22%3A%22http%3A%2F%2Fixijian.oss-cn-shanghai.aliyuncs.com%2Fexhibit%2F20180111%2F20180111194045bQBG.jpg%22%7D%5D" +
                "&comefrom=2" +
                "&sellUserPhone=13116162780" +
                "&borrowAmount=" + jieqianjine + "" +
                "&borrowPeriod=" + borrowPeriod + "" +
                "&borrowPeriodUom=月" +
                "&borrowStyle=" + borrowStyle + "" +
                "&approveApr=" + approveApr + "" +
                "&tenderApr=" + tenderApr + "" +
                "&borrowUse=企业借款(借款用)" +
                "&advisoryFee=0.5" +
                "&credit=1" +
                "&manageFee=0.5" +
                "&insurance=0.5" +
                "&custodyFee=1" +
                "&assurance=1" +
                "&appraisalFee=1" +
                "&impawnRate=40" +
                "&remark=企业借款(备注)";

        seleniumUtil.get(EnterpriseUrlFirst);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("true");

        //物品入库
        String applyIdSql = "SELECT t.apply_id FROM borrow_apply t ORDER BY t.apply_id DESC LIMIT 1;";
        String applyId = MySqlUtil.getInstance().mySqlCURD(applyIdSql);
        String goodsIdSql = "SELECT t.goods_id FROM borrow_apply t WHERE t.apply_id = " + applyId + ";";
        String storageCodeSql = "SELECT t.apply_code FROM borrow_apply t WHERE t.apply_id = " + applyId + ";";
        String goodsId = MySqlUtil.getInstance().mySqlCURD(goodsIdSql);
        String storageCode = MySqlUtil.getInstance().mySqlCURD(storageCodeSql);
        String EnterpriseUrlSecond = testAdminUrl + "/borrowApply/storage.htm?" +
                "goodsId="+goodsId+"&" +
                "applyId="+applyId+"&" +
                "storageCode="+storageCode+"&" +
                "delivery=%E5%88%98%E6%9C%AA%E6%98%93&" +
                "custodian=%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E4%BF%9D%E7%AE%A1%E4%BA%BA)&" +
                "shelfNumber=%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E8%B4%A7%E6%9E%B6%E7%BC%96%E5%8F%B7)&" +
                "storageAddress=%E4%B8%8A%E6%B5%B7%E5%B8%82%E6%B5%A6%E4%B8%9C%E6%96%B0%E5%8C%BA%E9%BE%99%E4%B8%9C%E5%A4%A7%E9%81%934028%E5%BC%8444%E5%8F%B7+%E9%87%91%E7%88%B5%E5%88%AB%E5%A2%85&" +
                "remark=%E4%BC%81%E4%B8%9A%E5%80%9F%E6%AC%BE(%E5%A4%87%E6%B3%A8)";
        seleniumUtil.get(EnterpriseUrlSecond);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("true");

        //电子签章
        LoginOauthToken loginOauthToken = new LoginOauthToken();
        String signSql = "SELECT sa.sign FROM signature_account sa WHERE sa.user_id = \"10000309\";";
        String sign = MySqlUtil.getInstance().mySqlCURD(signSql);
        try {
            String access_token = loginOauthToken.ADloginOauthTokenPassword("android","13916072538","123456","登录");
            String Url = TestUrl.getInterFaceNewURLVersion() + "/apply/sign/" + applyId;
            HttpPost httpPost = new HttpPost(Url);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("signature",sign);
            jsonObject.put("ip","192.168.16.120");
            InterFaceUtil.getInstance().setHeader(httpPost,"android-client",access_token);
            StringEntity entity = InterFaceUtil.getInstance().setJSONBody(httpClient,httpPost,jsonObject);
            EntityUtils.toString(entity);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            InterFaceUtil.getInstance().AssertStatus(response,"0000");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //风控审核
        String EnterpriseUrlThree = testAdminUrl + "/borrowApply/risk.htm?" +
                "applyId=" + applyId + "&" +
                "jdbg=Y&" +
                "zywqsm=Y&" +
                "zyprk=Y&" +
                "ccbx=Y&" +
                "smrz=Y&" +
                "zxbg=Y&" +
                "gzzm=Y&" +
                "yhls=Y&" +
                "riskRating=AAA&" +
                "insurance=89&" +
                "insurance=90&" +
                "insurance=91&" +
                "insurance=92&" +
                "insurance=93&" +
                "insurance=94&" +
                "insurance=95";
        seleniumUtil.get(EnterpriseUrlThree);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("true");

        //发布标的
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String borrowCode = sdf.format(date);
        String EnterpriseUrlFour = testAdminUrl + "/borrowApply/publish.htm?" +
                "applyId=" + applyId + "&" +
                "borrowCode=" + borrowCode + "&" +
                "borrowName=玺艺贷-" + borrowCode + "&" +
                "borrowDays=31&" +
                "borrowStyle=%E5%88%B0%E6%9C%9F%E4%B8%80%E6%AC%A1%E6%80%A7%E8%BF%98%E6%9C%AC%E4%BB%98%E6%81%AF&" +
                "borrowType=" + borrowType + "&" +
                "interestType=" + interestType + "&" +
                "borrowFlag=2&" +
                "borrowPeriod=" + borrowPeriod + "&" +
                "tenderMinAmount=100&" +
                "borrowAprInterest=" + approveApr + "&" +
                "tenderMaxAmount=0&" +
                "borrowAprStand=" + tenderApr + "&" +
                "riskLevel=AAA&" +
                "borrowAprExtra="+borrowAprExtra+"&" +
                "insuranceNo=93501067&" +
                "borrowAmount=" + jieqianjine + "&" +
                "zijinliuxiang=%E5%80%9F%E6%9D%A5%E7%9A%84&" +
                "webEnable=Y&" +
                "wapEnable=Y&" +
                "appEnable=Y&" +
                "allowUsePacket=Y&" +
                "allowUseInterest=Y&" +
                "renewFlag=Y&" +
                "isAuction=Y&" +
                "ah=&" +
                "isRepurchase=Y&" +
                "agree=2&" +
                "promotionPartners=%E6%97%A0%E5%90%88%E4%BD%9C%E6%96%B9";
        seleniumUtil.get(EnterpriseUrlFour);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("true");

        //发布标的初审
        String borrowIdSql = "SELECT t.borrow_id FROM borrow_apply t WHERE t.apply_id = " + applyId + ";";
        String borrowId = MySqlUtil.getInstance().mySqlCURD(borrowIdSql);
        String EnterpriseUrlFive = testAdminUrl + "/borrow/verify/firstVerify.htm?" +
                "borrowId=" + borrowId + "&" +
                "verifyStatus=1&" +
                "remark=ready&" +
                "contents=go";
        seleniumUtil.get(EnterpriseUrlFive);
        seleniumUtil.pause(1000);
        String status = seleniumUtil.findElementBy(By.xpath("/html/body/pre")).toString();
        if(seleniumUtil.isAdminContains("{\"success\":false,\"message\":\"系统错误，请稍后再试\"}")){
            seleniumUtil.get(EnterpriseUrlFive);
            seleniumUtil.pause(1000);
            //上线
            String EnterpriseUrlSix = testAdminUrl + "/borrow/verify/immediatelyOnline.htm?borrowId=" + borrowId + "";
            seleniumUtil.get(EnterpriseUrlSix);
            seleniumUtil.pause(1000);
            seleniumUtil.isCrmContains("true");
        }else if(seleniumUtil.isAdminContains("{\"success\":true,\"message\":\"提交申请成功！\"}")){
            seleniumUtil.pause(1000);
            //上线
            String EnterpriseUrlSix = testAdminUrl + "/borrow/verify/immediatelyOnline.htm?borrowId=" + borrowId + "";
            seleniumUtil.get(EnterpriseUrlSix);
            seleniumUtil.pause(1000);
            seleniumUtil.isCrmContains("true");
        }
        return borrowId;
    }
}
