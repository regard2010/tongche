package tongche_main.base;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.ExcelDataProvider;
import utils.LogConfiguration;
import utils.SeleniumUtil;
import utils.TestUrl;

import java.util.Iterator;

public class BaseParpareTestAdmin {
    //输出本页面日志 初始化
    public static Logger logger = Logger.getLogger(BaseParpareTestAdmin.class.getName());
    protected SeleniumUtil seleniumUtil = null;
    //添加成员变量来获取beforeClass传入的context参数
    protected ITestContext testContext = null;
    protected static String testAdminUrl = "";
    protected String method = "";
    protected int timeOut = 0;

    /* 启动浏览器并打开测试页面*/
    @BeforeSuite
    public void startTestPcUrl(ITestContext context){
        LogConfiguration.initLog(this.getClass().getSimpleName());
        seleniumUtil = new SeleniumUtil();
        //这里得到context值
        this.testContext = context;
        //从testng.xml文件中获取浏览器的属性值
        String browserName = context.getCurrentXmlTest().getParameter("browserName");
        timeOut = Integer.valueOf(context.getCurrentXmlTest().getParameter("timeOut"));
//        testAdminUrl = context.getCurrentXmlTest().getParameter("testAdminUrl");
        testAdminUrl = TestUrl.getTongCheAdminUrl();
        method = "";

        try{
            //启动浏览器，打开浏览器，输入测试地址，并打开微信窗口
            seleniumUtil.launchBrowserPc(browserName, context, testAdminUrl,method, timeOut);
        }catch(Exception e){
            logger.error("浏览器不能正常工作，请检查是不是被手动关闭或者其他原因",e);
        }
        //设置一个testng上下文属性，将driver存起来，之后可以使用context随时取到，主要是提供arrow 获取driver对象使用的，因为arrow截图方法需要一个driver对象
        testContext.setAttribute("SELENIUM_DRIVER", seleniumUtil.driver);
    }

    @AfterSuite
    public void endTest(){
        if( seleniumUtil.driver != null ){
            //退出浏览器
            seleniumUtil.quit();
        }else{
            logger.error("浏览器driver没有获得对象,退出操作失败");
            Assert.fail("浏览器driver没有获得对象,退出操作失败");
        }
    }

    /**
     * 测试数据提供者 - 方法
     */
    @DataProvider(name = "TongCheAdminAddDriver")
    public Iterator<Object[]> dataForAddDriver(){
        String moduleName = null; //模块名字
        String caseNum = null; //用例编号
        String className = this.getClass().getName();
        int doIndexNum = className.indexOf("."); //取得第一个.的index
        int underlineIndexNum = className.indexOf("_"); // 取得第一个_的index

        if(doIndexNum > 0 ){
            moduleName = className.substring(13, className.lastIndexOf(".")); // 取到模块的名称
        }

        if (underlineIndexNum > 0) {
            caseNum = className.substring(underlineIndexNum + 26, underlineIndexNum + 29); // 取到用例编号
        }
        //将模块名称和用例的编号传给 ExcelDataProvider ，然后进行读取excel数据
        return new ExcelDataProvider(moduleName, caseNum);
    }

    /**
     * 测试数据提供者 - 方法
     */
    @DataProvider(name = "TongCheAdminAddVertical")
    public Iterator<Object[]> dataForAddVertical(){
        String moduleName = null; //模块名字
        String caseNum = null; //用例编号
        String className = this.getClass().getName();
        int doIndexNum = className.indexOf("."); //取得第一个.的index
        int underlineIndexNum = className.indexOf("_"); // 取得第一个_的index

        if(doIndexNum > 0 ){
            moduleName = className.substring(13, className.lastIndexOf(".")); // 取到模块的名称
        }

        if (underlineIndexNum > 0) {
            caseNum = className.substring(underlineIndexNum + 28, underlineIndexNum + 31); // 取到用例编号
        }
        //将模块名称和用例的编号传给 ExcelDataProvider ，然后进行读取excel数据
        return new ExcelDataProvider(moduleName, caseNum);
    }
}
