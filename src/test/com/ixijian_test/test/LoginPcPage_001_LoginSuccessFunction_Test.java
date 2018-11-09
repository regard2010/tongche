package ixijian_test.test;

/**
 * UI浏览器测试调用范例，可以直接用于参考复制UI测试
 */

import ixijian_main.base.BaseParpareTestPc;
import ixijian_main.pageshelper.login.IndexPcPageHelper;
import ixijian_main.pageshelper.login.LoginPageHelper;
import org.testng.annotations.Test;


public class LoginPcPage_001_LoginSuccessFunction_Test extends BaseParpareTestPc {
	
	@Test
	public void loginSuccessFunction(){
		//等待登录页面加载
		LoginPageHelper.waitLoginPageLoad(seleniumUtil, timeOut);
		//输入登录信息
		LoginPageHelper.typeLoginInfo(seleniumUtil, "17999999911", "111111");
		//等待页面加载完成
		seleniumUtil.pause(2000);
		//等待首页元素显示出来
		IndexPcPageHelper.waitIndexPcPageLoad(seleniumUtil, timeOut);
		//检查用户是否登录成功
		IndexPcPageHelper.checkIndexPcErrorInfo(seleniumUtil);
		//退出登录
		LoginPageHelper.logOutPc(seleniumUtil);
		seleniumUtil.refresh();
	}
	
	@Test
	public void loginYesUsernameNoPassword(){
		//等待登录页面加载
		LoginPageHelper.waitLoginPageLoad(seleniumUtil, timeOut);
		//输入登录信息
		LoginPageHelper.typeLoginInfo(seleniumUtil, "17999999912", "");
		//检查用户是否登录成功
		seleniumUtil.pause(1000);
		LoginPageHelper.checkLoginErrorInfo(seleniumUtil, "请输入登录密码");
	}
	
	@Test
	public void loginNoUsernameYesPassword(){
		//等待登录页面加载
		LoginPageHelper.waitLoginPageLoad(seleniumUtil, timeOut);
		//输入登录信息
		LoginPageHelper.typeLoginInfo(seleniumUtil, "", "111111");
		//检查用户是否登录成功
		seleniumUtil.pause(1000);
		LoginPageHelper.checkLoginErrorInfo(seleniumUtil, "请输入手机号");
		seleniumUtil.refresh();
	}
	
	@Test
	public void loginYesUsernameErrorPassword(){
		//等待登录页面加载
		LoginPageHelper.waitLoginPageLoad(seleniumUtil, timeOut);
		//输入登录信息
		LoginPageHelper.typeLoginInfo(seleniumUtil, "17999999913", "888888");
		//检查用户是否登录成功
		seleniumUtil.pause(1000);
		LoginPageHelper.checkLoginErrorInfo(seleniumUtil, "请输入验证码");
		seleniumUtil.refresh();
	}
}
