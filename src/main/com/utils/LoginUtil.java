package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LoginUtil {

    private static Logger logger = Logger.getLogger(LoginUtil.class);

    String source = "ixijian_dev_encrypt";
    String source_dev = "ixijian_dev";

    private static class Login{
        private static LoginUtil loginUtil = new LoginUtil();
    }

    public static LoginUtil getInstance(){
        return Login.loginUtil;
    }

    /**
     * 获取WeiXin登录验证码
     * @param phone 非加密手机号
     * @param seleniumUtil
     * @return
     */
    public String WXLoginCode(String phone,SeleniumUtil seleniumUtil){
        if(phone.length() != 11){
            String AesDecPhoneSql = "SELECT cast(aes_decrypt(from_base64('" + phone + "'),'8e11000c294ed406') as char );";
            phone = MySqlUtil.getInstance().mySqlCURD_URI(source,AesDecPhoneSql);
        }
        seleniumUtil.get(TestUrl.getWXOldURL() + "/user/sendLoginCode.htm?loginName=" + phone + "");
        seleniumUtil.pause(8000);
        String loginCodeSql = "SELECT `content` FROM `ms_send` WHERE `receive_addr`='" + phone + "' AND `template_id` = '84' ORDER BY `send_id` DESC LIMIT 1;";
        String loginCode = MySqlUtil.getInstance().mySqlCURD_URI(source_dev,loginCodeSql);
        seleniumUtil.pause(5000);
        String code = loginCode.substring(9,15);
        logger.info("验证码是：[" + code + "]");
        return code;
    }

    //获取后台Admin登录验证码
    public String AdminLoginCode(String phone){
        String code = "";
        return code;
    }

    //获取Pc登录验证码
    public String PcLoginCode(String phone){
        String code = "";
        return code;
    }

    public static void main(String[] args) {
        String a = "P/5u1Ta44tdQoTigquRP4w==";
        System.out.println(a.length());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String time = formatter.format(date);
        System.out.println(time);
    }

    /****************************************************************************砼车Admin****************************************************************************/

    /**
     * Java版本登录，弃用
     * @param username
     * @param password
     * @return
     * @throws InterruptedException
     */
    public WebDriver adminLoginJava(String username,String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/liuweiyi/Documents/tongche_workspace/res/driver/chrome/mac/chromedriver");
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().setSize(new Dimension(1600, 900));
        Thread.sleep(1000);
        driver.get(TestUrl.getTongCheAdminUrl() + "/login.jsp");
        driver.findElement(By.id("UPhone")).sendKeys(username);
        driver.findElement(By.id("UPwd")).sendKeys(password);
        Scanner yzm = new Scanner(System.in);
        System.out.println("请输入验证码：");
        String line = yzm.nextLine();
        System.out.println("验证码为：" + line);
        driver.findElement(By.id("code1")).sendKeys(line);
        Thread.sleep(1000);
        driver.findElement(By.id("btLogin")).click();
        Thread.sleep(1000);
        return driver;
    }

    /**
     * 临时使用，后面改用框架实现
     * @param username
     * @return
     * @throws InterruptedException
     */
    public WebDriver adminLogin(String username) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/liuweiyi/Documents/tongche_workspace/res/driver/chrome/mac/chromedriver");
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().setSize(new Dimension(1600, 900));
        Thread.sleep(1000);
        driver.get(TestUrl.getTongCheAdminUrl() + "/login.html");
        driver.findElement(By.id("mobile")).sendKeys(username);
        driver.findElement(By.id("secret")).sendKeys("157935");
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/button")).click();
        Thread.sleep(1000);
        return driver;
    }

}

















