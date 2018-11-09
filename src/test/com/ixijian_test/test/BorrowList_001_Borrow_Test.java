package ixijian_test.test;

import ixijian_main.base.BaseParpareTestPc;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.MySqlUtil;
import utils.TestUrl;

public class BorrowList_001_Borrow_Test extends BaseParpareTestPc{


    @Test
    public void readList(){
        for(int i = 1 ; i <= 8 ; i ++){
            seleniumUtil.get(TestUrl.getReleasedPcUrl() + "/borrow/investList.htm?borrowApr=&borrowPeriod=&borrowStyle=&statusType=&page="+ i +"");
            seleniumUtil.pause(1000);
            for (int j = 1 ; j <= 8 ; j++){
                System.out.println("**************第[" + i + "]页的第["+ j +"]个标的*************");
                String borrowName = seleniumUtil.findElementBy(By.xpath("/html/body/div[4]/div/div[1]/dl[" + j + "]/dt/div/h3/a")).getText().toString();
                String borrowNianHua = seleniumUtil.findElementBy(By.xpath("/html/body/div[4]/div/div[1]/dl[" + j + "]/dt/div/div/p[1]/span[1]/strong")).getText().toString();
                String borrowMonth = seleniumUtil.findElementBy(By.xpath("/html/body/div[4]/div/div[1]/dl[" + j + "]/dt/div/div/p[2]/span[1]/strong")).getText().toString();
                String borrowMoney = seleniumUtil.findElementBy(By.xpath("/html/body/div[4]/div/div[1]/dl[" + j + "]/dt/div/div/p[3]/span[1]/strong")).getText().toString();
                System.out.println("标的名称:" + borrowName);
                System.out.println("标的年化:" + borrowNianHua);
                System.out.println("标的月份:" + borrowMonth);
                System.out.println("标的金额:" + borrowMoney);
                MySqlUtil.getInstance().mySqlCURD_URI_Localhost("INSERT INTO `borrowlist` (`borrowName`,`borrowNianHua`,`borrowMonth`,`borrowMoney`,`source`) " +
                        "VALUES ('"+ borrowName +"','" + borrowNianHua + "','" + borrowMonth + "','" + borrowMoney + "' , '玺鉴');");
            }
        }
    }
}
