package ixijian_test.api.borrow;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import utils.HttpRequester;
import utils.TestUrl;

public class BorrowList_001_BorrowListSuccessFunction_Test {

    /**
     * 标的列表
     * /borrow/borrowList
     */
    public void borrowBorrowListTest(HttpClient httpClient){
        String Url = TestUrl.getPcURL() + "/borrow/borrowList";
        PostMethod postMethod1 = HttpRequester.goPostOne(Url,httpClient);
        postMethod1.setParameter("", "");
        postMethod1.setParameter("", "");
        HttpRequester.goPostTwo(httpClient, postMethod1, "");
    }

    /**
     * 标的详情个人
     * /borrow/borrowInfo/{borrowId}
     */
    public void borrowBorrowInfoTest(HttpClient httpClient){
        String Url = TestUrl.getPcURL() + "/borrow/borrowInfo/";
        PostMethod postMethod1 = HttpRequester.goPostOne(Url,httpClient);
        postMethod1.setParameter("", "");
        postMethod1.setParameter("", "");
        HttpRequester.goPostTwo(httpClient, postMethod1, "");
    }

    /**
     * 标的投资记录
     * /borrow/tenderList/{borrowId}/{page}/{size}
     */
    public void borrowTenderListDetailTest(HttpClient httpClient){
        String Url = TestUrl.getPcURL() + "/borrow/tenderList";
        PostMethod postMethod1 = HttpRequester.goPostOne(Url,httpClient);
        postMethod1.setParameter("", "");
        postMethod1.setParameter("", "");
        HttpRequester.goPostTwo(httpClient, postMethod1, "");
    }

    /**
     * 投资可用卡券
     * /borrow/couponList
     */
    public void borrowCouponListTest(HttpClient httpClient){
        String Url = TestUrl.getPcURL() + "/borrow/couponList";
        PostMethod postMethod1 = HttpRequester.goPostOne(Url,httpClient);
        postMethod1.setParameter("", "");
        postMethod1.setParameter("", "");
        HttpRequester.goPostTwo(httpClient, postMethod1, "");
    }

    /**
     * 预期收益、收益计算器
     * /borrow/income
     */
    public void borrowBorrowIncomeTest(HttpClient httpClient){
        String Url = TestUrl.getPcURL() + "/borrow/income";
        PostMethod postMethod1 = HttpRequester.goPostOne(Url,httpClient);
        postMethod1.setParameter("", "");
        postMethod1.setParameter("", "");
        HttpRequester.goPostTwo(httpClient, postMethod1, "");
    }




}
























