package tongche_test.admin;

import com.sun.xml.internal.bind.v2.TODO;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pageshelper.admin.login.AdminLoginPageHelper;
import tongche_main.pageshelper.admin.task.AddItemOrderPageHelper;


public class AddItemOrderPage_001_ItemOrderSuccessFunction_Test extends BaseParpareTestAdmin {

    @BeforeTest
    @Parameters({"userName"})
    public void login(String userName){
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil,timeOut);
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil,userName,"157935");
    }

    //TODO 任务单和车号的获取
    @Test
    public void addItemOrder(){
        AddItemOrderPageHelper.waitItemOrderPageLoad(seleniumUtil,timeOut);
        AddItemOrderPageHelper.typeItemOrderPageInfo(seleniumUtil,"","20","车号不能为空！");
    }
}
