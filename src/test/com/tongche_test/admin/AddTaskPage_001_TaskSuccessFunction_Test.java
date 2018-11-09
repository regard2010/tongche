package tongche_test.admin;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tongche_main.base.BaseParpareTestAdmin;
import tongche_main.pageshelper.admin.login.AdminLoginPageHelper;
import tongche_main.pageshelper.admin.task.AddTaskPageHelper;


public class AddTaskPage_001_TaskSuccessFunction_Test extends BaseParpareTestAdmin {

    @BeforeTest
    @Parameters({"userName"})
    public void login(String userName){
        AdminLoginPageHelper.waitAdminPageLoad(seleniumUtil,timeOut);
        AdminLoginPageHelper.typeAdminInfo(seleniumUtil,userName,"157935");
    }

    @Test
    public void addItemOrder(){
        AddTaskPageHelper.waitAddTaskPageLoad(seleniumUtil,timeOut);
        AddTaskPageHelper.typeAddTaskPageInfo(seleniumUtil,"","20","车号不能为空！");
    }
}
