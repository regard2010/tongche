package ixijian_test.test;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.MySqlUtil;
import utils.TestUrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class OldBorrowStepOne_001_OldBorrow_Test {

    //*投资月份
    static String borrowPeriod = "1";
    //*个人标的:13817504921 企业标的:17701748136
    static String phone = "13817504921";
    //选择数据库源
    static String source = "ixijian_dev_encrypt";
    //列表页主图
    static String imgUrl = "http://ixijian.oss-cn-shanghai.aliyuncs.com/test/exhibit/20170623/20170623111109tYR7.jpg";
    //预估价格
    static String valuation = "10000000";
    static String borrowName = "";
    static String line = "";


    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/shuailiu/Documents/xijian_auto_workspace/mavenProject/chromedriver");
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.get(TestUrl.getPcURL() + "/appraisal/getAppraisalCaptcha.htm");
        driver.manage().window().setSize(new Dimension(100,200));
        Scanner yzm = new Scanner(System.in);
        System.out.println("请输入验证码：");
        line = yzm.nextLine();
        System.out.println("验证码为：" + line);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
        String str = sdf.format(date);
        //艺术品名称
        if (phone.equals("17701748136")) {
            borrowName = borrowPeriod + "个月【企业】" + str;
        } else if (phone.equals("13817504921")) {
            borrowName = borrowPeriod + "个月【个人】" + str;
//            borrowName = "  后算钱_老司机算逾期用" + str;
        } else {
            System.out.println("你既不是【企业】的也不是【个人】，你手机号肯定填错了！");
        }
        System.out.println("标的名称为：" + borrowName);

        driver.get(TestUrl.getPcURL() + "/appraisal/apply.htm?"
                + "productType=2&"
                + "productName=" + borrowName + "&"
                + "years=tangqian&"
                + "valuation=" + valuation + "&"
                + "getWayType=buy&"
                + "getWay=&"
                + "specific=123321&"
                + "author=abide&"
                + "condition=baocunwanhao&"
                + "description=zaqws&"
                + "fileIdList=16694%2C16695&"
                + "contactName=" + borrowName + "&"
                + "contactPhone=" + phone + "&"
                + "captcha=" + line + "&"
                + "shengming=");
        Thread.sleep(2000);
        String imgUrlSql = "UPDATE `goods` SET `image_url`='" + imgUrl + "' WHERE `goods_name` = '" + borrowName + "';";
        MySqlUtil.getInstance().mySqlCURD_URI(source, imgUrlSql);
        System.out.println("主图刷入完成");
        driver.quit();
    }
}
