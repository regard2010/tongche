package tongche_test.login;

import jxl.Sheet;
import jxl.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.LoginUtil;
import utils.MySqlUtil;
import utils.TestUrl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminPHP_AllCreate {

    static String username = "17899999947";
    static String excelPath = "/Users/liuweiyi/Documents/tongche_workspace/data/";


    public static void main(String[] args) throws Exception {
        AdminPHP_AllCreate.createTaskNoRole("上海世博会博物馆","上海市黄浦区上海世博会博物馆");
//        AdminPHP_AllCreate.createVertical("","");
//        AdminPHP_AllCreate.createDriver("","");
//        AdminPHP_AllCreate.createVerticalForExcel();
//        AdminPHP_AllCreate.createDriverForExcel();
//        AdminPHP_AllCreate.createItemOrders();
//        App_Api_001_Success_Test.itemOrderUpdateItem();
    }

    //新订单
    public static void createTaskNoRole(String projectName,String projectAddress) throws Exception{
        //后台登录
        WebDriver driver = LoginUtil.getInstance().adminLogin(username);
        driver.get(TestUrl.getTongCheAdminUrl() + "/#/shipTask");

        for(int i = 1 ; i <= 2 ; i++){
            //单新建任务单
            Thread.sleep(1000);
            driver.findElement(By.id("taskAdds")).click();
            Thread.sleep(1000);
            System.out.println(">>>>>>开始录入任务单");
            Select select = new Select(driver.findElement(By.id("unit")));
            select.selectByValue("8467");
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");
            driver.findElement(By.id("_project")).click();
            driver.findElement(By.id("_project")).sendKeys(projectName + df.format(day));
            driver.findElement(By.name("transportDistance")).click();
            driver.findElement(By.name("transportDistance")).sendKeys("10");
            driver.findElement(By.name("pouringPosition")).click();
            driver.findElement(By.name("pouringPosition")).sendKeys("底部");
            Select select0 = new Select(driver.findElement(By.id("method")));
            select0.selectByValue("8741");
            Select select1 = new Select(driver.findElement(By.id("taskVariety")));
            select1.selectByValue("8805");
            Select select3 = new Select(driver.findElement(By.id("slump")));
            select3.selectByValue("8783");
            driver.findElement(By.name("planQuantity")).click();
            driver.findElement(By.name("planQuantity")).sendKeys("20");
            driver.findElement(By.name("planStartTime")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[7]/div[3]/table/tfoot/tr[1]/th")).click();
            Select select4 = new Select(driver.findElement(By.id("addSelect1")));
            select4.selectByValue("534");
            driver.findElement(By.name("projectAddress")).click();
            driver.findElement(By.name("projectAddress")).sendKeys(projectAddress);
            Thread.sleep(1000);
            driver.findElement(By.id("taskAdd")).click();
            System.out.println(">>>>>>录入任务单完毕:" + i);
            Thread.sleep(1000);
        }
        driver.quit();
    }

    //单独新建车辆
    public static void createVertical(String carLicense,String carNumber) throws Exception {
        //后台登录
        WebDriver driver = LoginUtil.getInstance().adminLogin(username);
        driver.findElement(By.xpath("//*[@id=\"item\"]/li[5]/span")).click();
        Thread.sleep(1000);
        //开始新建车辆
        System.out.println(">>>新建车辆开始");
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("carLicenseNo")).click();
        driver.findElement(By.name("carLicenseNo")).clear();
        driver.findElement(By.name("carLicenseNo")).sendKeys(carLicense);
        driver.findElement(By.name("carNumber")).click();
        driver.findElement(By.name("carNumber")).clear();
        driver.findElement(By.name("carNumber")).sendKeys(carNumber);
        driver.findElement(By.name("carType")).click();
        driver.findElement(By.xpath("//*[@id=\"addCarForm\"]/div[5]/div[2]/select/option[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("addCarOk")).click();
        System.out.println("车牌号："+carLicense+"，车号："+carNumber+"__新建车辆完毕<<<");
        Thread.sleep(2000);
        driver.quit();
    }

    //单独新建驾驶员
    public static void createDriver(String driverName,String driverPhone) throws Exception {
        //后台登录
        WebDriver driver = LoginUtil.getInstance().adminLogin(username);
        driver.findElement(By.xpath("//*[@id=\"item\"]/li[4]/span")).click();
        Thread.sleep(1000);
        //开始新建驾驶员
        System.out.println(">>>新建驾驶员开始");
        driver.findElement(By.id("driverModal")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys(driverName);
        driver.findElement(By.name("tel")).click();
        driver.findElement(By.name("tel")).clear();
        driver.findElement(By.name("tel")).sendKeys(driverPhone);
        Thread.sleep(1000);
        driver.findElement(By.id("addDriver")).click();
        System.out.println("驾驶员姓名："+driverName+"，驾驶员手机号：" + driverPhone + "__新建驾驶员完毕<<<");
        Thread.sleep(2000);
        driver.quit();
    }

    //批量添加车辆
    public static String createVerticalForExcel() throws InterruptedException {
        //后台登录
        WebDriver driver = LoginUtil.getInstance().adminLogin(username);
        driver.get(TestUrl.getTongCheAdminUrl() + "/#/shipVertical");
        Thread.sleep(1500);
        File file = new File(excelPath + "TC.xls");
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            String carLicense = "";
            String carNumber = "";
            for (int index = 0; index < sheet_size; index++) {
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 1; i < sheet.getRows(); i++) {
                    carLicense = sheet.getCell(0,i).getContents();
                    carNumber = sheet.getCell(1,i).getContents();
                    //开始新建车辆
                    System.out.println(">>>新建车辆开始");
                    driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[1]/button")).click();
                    Thread.sleep(1000);
                    driver.findElement(By.name("carLicenseNo")).click();
                    driver.findElement(By.name("carLicenseNo")).clear();
                    driver.findElement(By.name("carLicenseNo")).sendKeys(carLicense);
                    driver.findElement(By.name("carNumber")).click();
                    driver.findElement(By.name("carNumber")).clear();
                    driver.findElement(By.name("carNumber")).sendKeys(carNumber);
                    driver.findElement(By.name("carType")).click();
                    driver.findElement(By.xpath("//*[@id=\"addCarForm\"]/div[5]/div[2]/select/option[2]")).click();
                    Thread.sleep(1000);
                    driver.findElement(By.id("addCarOk")).click();
                    System.out.println("车牌号："+carLicense+"，车号："+carNumber+"__新建车辆完毕<<<");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
        return null;
    }

    //批量添加驾驶员
    public static String createDriverForExcel() throws InterruptedException {
        //后台登录
        WebDriver driver = LoginUtil.getInstance().adminLogin(username);
        driver.get(TestUrl.getTongCheAdminUrl() + "/#/shipDriver");
        Thread.sleep(1000);

        File file = new File(excelPath + "TC.xls");
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            String driverName = "";
            String driverPhone = "";
            for (int index = 1; index < sheet_size; index++) {
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 1; i < sheet.getRows(); i++) {
                    driverName = sheet.getCell(0,i).getContents();
                    driverPhone = sheet.getCell(1,i).getContents();
                    //开始新建车辆
                    System.out.println(">>>新建驾驶员开始");
                    driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/button[2]")).click();
                    Thread.sleep(1000);
                    driver.findElement(By.id("userName")).click();
                    driver.findElement(By.id("userName")).clear();
                    driver.findElement(By.id("userName")).sendKeys(driverName);
                    driver.findElement(By.name("tel")).click();
                    driver.findElement(By.name("tel")).clear();
                    driver.findElement(By.name("tel")).sendKeys(driverPhone);
                    Thread.sleep(1000);
                    driver.findElement(By.id("addDriver")).click();
                    System.out.println("驾驶员姓名："+driverName+"，驾驶员手机号："+driverPhone+"__新建驾驶员完毕<<<");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
        return null;
    }

    //自动发小票给驾驶员

    /**
     * 等新版本修改后，修改为自动发小票
     * 现在需要人工介入，半自动发小票
     * @throws Exception
     */
    public static void createItemOrders() throws Exception {
        //后台登录
        WebDriver driver = LoginUtil.getInstance().adminLogin(username);
        driver.get(TestUrl.getTongCheAdminUrl() + "/#/shipItemOrder");
        Thread.sleep(1000);

        File file = new File(excelPath + "TC.xls");
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            String carLicense = "";
            String carNumber = "";
            for (int index = 0; index < sheet_size; index++) {
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 1; i < sheet.getRows(); i++) {
                    /*
                    carLicense = sheet.getCell(0,i).getContents();
                    carNumber = sheet.getCell(1, i).getContents();
                    String driverIdSql = "SELECT `id` from `driver` where `accountId` = (select `id` from `user` where `userName`= '1" + i + "谊建');";
                    String driverId = MySqlUtil.getInstance().mySqlCURD(driverIdSql);
                    String vehicleIdSql = "SELECT `vehicleId` from `driver` where `accountId` = (select `id` from `user` where `userName`= '1" + i + "谊建');";
                    String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
                    Thread.sleep(1000);
                    driver.get(TestUrl.getTongCheAdminUrl() + "/itemOrder/insert?carNumber=" + carNumber + "&" +
                            "receiptRule=535&" +
                            "remarks=&" +
                            "limin=T2018070918969&" +
                            "taskId=18969&" +
                            "driverId=" + driverId + "&" +
                            "driverName=1" + i + "谊建&" +
                            "maxCapacity=--&" +
                            "chepai=" + carLicense + "&" +
                            "vehicleId=" + vehicleId + "&" +
                            "fhQuantity=17");
                            */
                    System.out.println(">>>新建小票开始" + i);
                    String driverIdSql = "SELECT `id` from `driver` where `accountId` = (select `id` from `user` where `userName`= '1" + i + "谊建');";
                    String driverId = MySqlUtil.getInstance().mySqlCURD(driverIdSql);
                    Thread.sleep(500);
                    driver.findElement(By.id("projectXpAdd")).click();
                    Thread.sleep(1000);
                    System.out.println(">>>>>>>>>>>>>>>>>>>选择【任务单】，倒计时已开始<<<<<<<<<<<<<<<<<<该死的2层弹窗还带双击操作，玩死人啊！！！");
                    for(int j = 8 ; j >= 0 ; j--){
                        if(j == 0){
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>倒计时结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            break;
                        }else{
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>还有"+ j +"秒<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            Thread.sleep(1000);
                        }
                    }
                    Select selectDriverId = new Select(driver.findElement(By.id("driverId")));
                    selectDriverId.selectByValue(driverId);
                    driver.findElement(By.id("fhQuantity")).sendKeys("18");
                    Select selectReceiptRule = new Select(driver.findElement(By.id("addSelect")));
                    selectReceiptRule.selectByValue("535");
                    Date day=new Date();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    driver.findElement(By.name("remarks")).sendKeys("驾驶员签收_发货方量18_时间点：" + df.format(day));
                    driver.findElement(By.id("noteAdd")).click();
                    Thread.sleep(1000);
                    System.out.println("小票新建: " + i);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
