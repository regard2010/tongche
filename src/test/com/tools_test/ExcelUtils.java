package tools_test;

import jxl.Sheet;
import jxl.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LoginUtil;
import utils.TestUrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ExcelUtils {

    //使用静态内部类创建外部类对象
    private static class Excel{
        private static ExcelUtils excelUtils = new ExcelUtils();
    }

    //获取InterFaceUtil实例
    public static ExcelUtils getInstance(){
        return ExcelUtils.Excel.excelUtils;
    }

    public static String readExcel(String path,int column) throws InterruptedException {
        File file = new File(path);
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            String driverPhone = "";
            for (int index = 1; index < sheet_size; index++) {
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 1; i < sheet.getRows(); i++) {
                    String value = sheet.getCell(column,i).getContents();
//                    String value = sheet.getCell(1,i).getContents();
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
