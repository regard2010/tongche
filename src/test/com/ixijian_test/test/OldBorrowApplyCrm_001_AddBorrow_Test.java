package ixijian_test.test;

import ixijian_main.base.BaseParpareTestCrm;
import ixijian_main.pageshelper.login.AdminLoginPageHelper;
import ixijian_main.pageshelper.login.CrmLoginPageHelper;
import utils.MySqlUtil;
import utils.TestUrl;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OldBorrowApplyCrm_001_AddBorrow_Test  extends BaseParpareTestCrm {

    private static Logger logger = Logger.getLogger(OldBorrowApplyCrm_001_AddBorrow_Test.class);

    //*借款方式:1、end(到期一次性还本付息) 2、endday(按天计息到期还本息) 3、month(等额本息)
    String borrowStyle = "end";
    //*借款人利率
    String approveApr = "24";
    //*计息方式：tjy:T+1;immediately:即时起息
    String interestType = "immediately";
    //*选择数据库源
    String source = "ixijian_dev_encrypt";
    String source_dev = "ixijian_dev";
    //列表页主图
    String imgUrl = "http://ixijian.oss-cn-shanghai.aliyuncs.com/test/exhibit/20170623/20170623111109tYR7.jpg";


    @Parameters({"phone","jieqianjine","valuation","tenderApr","borrowPeriod","borrowType","credit","insurance","appraisalFee","assurance","pledgeAndCustodyFee","postLoanManagementFee","afterLoanServiceCharge"})
    @Test
    public void newBorrowApply(String phone,String jieqianjine, String valuation, String tenderApr, String borrowPeriod, String borrowType,
                               String credit,String insurance,String appraisalFee,String assurance,String pledgeAndCustodyFee,String postLoanManagementFee,String afterLoanServiceCharge) {
        //等待登录页面加载
        CrmLoginPageHelper.waitLoginPageLoad(seleniumUtil, timeOut);
        //输入登录信息
        CrmLoginPageHelper.typeLoginInfo(seleniumUtil, "liuweiyi", "1qaz2wsx");
        //等待页面加载完成
        seleniumUtil.pause(1000);

        seleniumUtil.setBrowserSize(400,800);
        String apply_idSql = "SELECT `apply_id` FROM `borrow_apply` ORDER BY apply_id DESC LIMIT 1;";
        String apply_id = MySqlUtil.getInstance().mySqlCURD_URI(source,apply_idSql);
        //1、借款初审
        String FirstStepUrl = TestUrl.getCrmURL() + "/apply/firstVerify/firstVerify.htm?" +
                "applyId=" + apply_id + "&" +
                "verifyStatus=10&" +
                "verifyRemark=&" +
                "isShow=N";
        seleniumUtil.get(FirstStepUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("初审完成");

        //2、借款复审
        String SecondStepUrl = TestUrl.getCrmURL() + "/apply/firstVerify/secondVerify.htm?" +
                "applyId=" + apply_id + "&" +
                "verifyStatus=20&" +
                "valuation=&" +
                "verifyRemark=";
        seleniumUtil.get(SecondStepUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("复审完成");

        //3、电话联系
        String ThreeStepUrl = TestUrl.getCrmURL() + "/apply/firstVerify/phoneVerify.htm?" +
                "applyId=" + apply_id + "&" +
                "verifyStatus=30&" +
                "needInfo=1&" +
                "verifyRemark=";
        seleniumUtil.get(ThreeStepUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("电话预约完成");

        //4、专家复审
        //加密处理phone
        String AesPhoneSql = "SELECT to_base64(AES_ENCRYPT('" + phone + "', '8e11000c294ed406'));";
        phone = MySqlUtil.getInstance().mySqlCURD_URI(source,AesPhoneSql);
        //拿到userId
        String borrowUserIdSql = "SELECT `user_id` FROM `user` where `phone` = '" + phone + "'";
        String borrow_UserId = MySqlUtil.getInstance().mySqlCURD_URI(source, borrowUserIdSql);

        String FourStepEnterpriseUrl = TestUrl.getCrmURL() + "/borrowApply/add.htm?" +
                "applyId=" + apply_id + "&" +
                "userId=" + borrow_UserId + "&" +
                "borrowType=" + borrowType + "&" +
                "borrowAmount=" + jieqianjine + "&" +
                "borrowStyle=" + borrowStyle + "&" +
                "borrowPeriod=" + borrowPeriod + "&" +
                "borrowUseId=3";
        seleniumUtil.get(FourStepEnterpriseUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("增加借款请求成功");

        String DecodePhoneSql = "SELECT cast(aes_decrypt(from_base64('" + phone + "'),'8e11000c294ed406') as char );";
        String phone1 = MySqlUtil.getInstance().mySqlCURD_URI(source,DecodePhoneSql);
        String FourStepEditUrl = TestUrl.getCrmURL() + "/userInfo/editUserInfo.htm?"
                + "userId=" + borrow_UserId + "&"
                + "applyId=" + apply_id + "&"
                + "realname=刘超&"
                + "phone=" + phone1 + "&"
                + "cardId=120106198204074552&"
                + "infoProvinceId=1&"
                + "infoCityId=2&"
                + "nowAddress=%E4%B8%8A%E6%B5%B7&"
                + "sex=1&marry=1&"
                + "birthday=2016-07-05&"
                + "localLateNum=8&"
                + "otherBorrowNum=10&"
                + "liabilitiesInfo=Y&"
                + "useOfFunds=资金运用的还行&"
                + "financialSituation=财务状况还过得去&"
                + "repaymentAbility=还款能力非常强壮&"
                + "involvedInAppeal=从未设计过诉讼&"
                + "majorPenalties=有重大处罚我还敢借钱给你吗&"
                + "children=1&edu=0&"
                + "income=0&"
                + "companyName=&"
                + "companyType=0&"
                + "industry=&"
                + "office=&"
                + "workYear=0&"
                + "companyTel=&"
                + "address=&"
                + "tel=&"
                + "provinceId=40&"
                + "cityId=41&postId=&"
                + "name=%E5%88%98%E5%88%98&"
                + "comtactPhone=13916072538&"
                + "relation=0&"
                + "idcardId1=11427&"
                + "idcardId2=11428&"
                + "idcardId3=11429&"
                + "workId=&"
                + "incomeId=&"
                + "creditId=&"
                + "houseId=&"
                + "otherId=";
        seleniumUtil.get(FourStepEditUrl);
        seleniumUtil.pause(1000);

        String FourStepExpertVerifyUrl = TestUrl.getCrmURL() + "/apply/firstVerify/expertVerify.htm?" +
                "applyId=" + apply_id + "&" +
                "verifyStatus=40&" +
                "valuation=" + valuation + "&" +
                "verifyGist=abc&" +
                "expertId=25&" +
                "verifyRemark=efg";
        seleniumUtil.get(FourStepExpertVerifyUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("专家复审完成");

        //5、物品入库
        String goodsIdSql = "SELECT `goods_id` FROM `borrow_apply` where `apply_id` = '" + apply_id + "'";
        String goods_id = MySqlUtil.getInstance().mySqlCURD_URI(source, goodsIdSql);
        String FiveStepUrl = TestUrl.getCrmURL() + "/apply/firstVerify/storage.htm?" +
                "goodsId=" + goods_id + "&" +
                "applyId=" + apply_id + "&" +
                "verifyStatus=70&" +
                "delivery=abc&" +
                "custodian=efg&" +
                "shelfNumber=123&" +
                "storageAddress=拍卖行保管" +
                "verifyRemark=";
        seleniumUtil.get(FiveStepUrl);
        seleniumUtil.pause(1000);

        //6、借款审核
        String SixStepUrl = TestUrl.getCrmURL() + "/apply/firstVerify/borrowVerify.htm?" +
                "applyId=" + apply_id + "&" +
                "verifyStatus=50&" +
                "approveAmount=" + jieqianjine + "&" +
                "approveStyle=" + borrowStyle + "&" +
                "approvePeriod=" + borrowPeriod + "&" +
                "approveApr=" + approveApr + "&" +
                "tenderApr=" + tenderApr + "&" +
//                "advisoryFee=0&" +
                "credit=" + credit + "&" +
//                "manageFee=" + manageFee + "&" +
                "insurance=" + insurance + "&" +
//                "custodyFee=" + custodyFee + "&" +
                "appraisalFee=" + appraisalFee + "&" +
                "assurance=" + assurance + "&" +
                "pledgeAndCustodyFee=" + pledgeAndCustodyFee + "&" +
                "postLoanManagementFee=" + postLoanManagementFee + "&" +
                "afterLoanServiceCharge=" + afterLoanServiceCharge + "&" +
                "impawnRate=50&" +
                "verifyRemark=";
        seleniumUtil.get(SixStepUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("借款审核完成");

        //6.1电子签章
        String SixOneStepSignSql = "SELECT `sign` FROM `signature_account` WHERE `user_id` = '" + borrow_UserId + "';";
        String SixOneStepSign = MySqlUtil.getInstance().mySqlCURD_URI(source,SixOneStepSignSql);
        String AesDecPhoneSql = "SELECT cast(aes_decrypt(from_base64('" + phone + "'),'8e11000c294ed406') as char );";
        phone = MySqlUtil.getInstance().mySqlCURD_URI(source,AesDecPhoneSql);
        seleniumUtil.get(TestUrl.getWXOldURL() + "/user/sendLoginCode.htm?loginName=" + phone + "");
        seleniumUtil.pause(5000);
        String loginCodeSql = "SELECT `content` FROM `ms_send` ORDER BY `send_id` DESC LIMIT 1";
        String loginCode = MySqlUtil.getInstance().mySqlCURD_URI(source_dev,loginCodeSql);
        seleniumUtil.pause(1000);
        loginCode = loginCode.substring(9,15);
        logger.info("验证码是：[" + loginCode + "]");
        String SixOneStepUrl = "";
        if(phone.equals("17701748136")){
            SixOneStepUrl = TestUrl.getWXOldURL() + "/user/doLogin.htm?loginName=" + phone + "&loginPassword=MTIzNFF3ZXI=&captcha=&loginCode=" + loginCode + "";
        }else if(phone.equals("13817504921")){
            SixOneStepUrl = TestUrl.getWXOldURL() + "/user/doLogin.htm?loginName=" + phone + "&loginPassword=MTIzNDU2QWI=&captcha=&loginCode=" + loginCode + "";
        }
        String SixOneStepSignUrl = TestUrl.getWXOldURL() + "/appraisal/sign.htm?id=" + apply_id + "&sign=" + SixOneStepSign;
        seleniumUtil.get(SixOneStepUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("登录成功");
        seleniumUtil.get(SixOneStepSignUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("签名成功");
        String SixOneStepSignatureLogSql = "UPDATE `signature_log` SET `signature`='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' WHERE `pertain_id`='" + apply_id + "';";
        MySqlUtil.getInstance().mySqlCURD_URI(source,SixOneStepSignatureLogSql);


        // 7、风险审核
        String SevenStepUrl = TestUrl.getCrmURL() + "/apply/firstVerify/riskVerify.htm?" + "applyId=" + apply_id + "&" + "verifyStatus=60&"
                + "riskRating=8&" + "verifyRemark=";
        seleniumUtil.get(SevenStepUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("风控审核完成");

        //8、发布标的
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String borrowCode = sdf.format(date);
        String EightStepUrl = TestUrl.getCrmURL() + "/apply/firstVerify/publish.htm?" + "applyId=" + apply_id + "&" + "taskId=&"
                + "borrowCode=" + borrowCode + "&" + "borrowName=玺艺贷&borrowStyle=" + borrowStyle + "&" + "interestType=" + interestType + "&"
                + "borrowPeriod=" + borrowPeriod + "&" + "borrowAprInterest=" + approveApr + "&" + "borrowApr="
                + tenderApr + "&" + "borrowAmount=" + jieqianjine + "&" + "zijinliuxiang=zijinliuxiang&"
                + "showPlay=1&" + "showPlay=2&" + "showPlay=3&" + "allowUsePacket=Y&" + "allowUseInterest=Y&"
                + "renewFlag=Y&" + "isAuction=Y&" + "isRepurchase=Y&" + "borrowDays=31&"
                + "borrowType=youchi&" + "borrowFlag=4&" + "tenderMinAmount=100&" + "tenderMaxAmount=0&"
                + "riskLevel=8&" + "insuranceNo=&" + "description=description&" + "ah=duo_yun_xuan&" + "agree=2&"
                + "promotionPartners=%E6%97%A0%E5%90%88%E4%BD%9C%E6%96%B9";
        seleniumUtil.get(EightStepUrl);
        seleniumUtil.pause(1000);
        seleniumUtil.isCrmContains("发标成功");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CRM审核完成<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        seleniumUtil.setBrowserSize(1600,900);
        seleniumUtil.get(TestUrl.getAdminURL() + "/AdminLoginInterface.jsp");
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil,timeOut);
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil,"liuweiyi", "1qaz2wsx");
        seleniumUtil.pause(1000);
        String borrowIdSql = "SELECT `borrow_id` FROM `borrow_apply` WHERE `apply_id` = '" + apply_id + "'";
        String borrowId = MySqlUtil.getInstance().mySqlCURD_URI(source,borrowIdSql);
        seleniumUtil.get(TestUrl.getAdminURL() + "/borrow/verify/firstVerify.htm?" + "borrowId=" + borrowId + "&" + "verifyStatus=1&"
                + "remark=ddd&" + "contents=fff");
        seleniumUtil.get(TestUrl.getAdminURL() + "/borrow/verify/immediatelyOnline.htm?borrowId=" + borrowId + "");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ADMIN上标完成<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        seleniumUtil.pause(1000);
    }
}
