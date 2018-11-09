package ixijian_main.mockdemo.test;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeServiceTest {

	@Test
	public void testGetTotalEmployee() {
		final EmployeeDao employeeDao = new EmployeeDao();
		final EmployeeService service = new EmployeeService(employeeDao);
		int total = service.getTotalEmployee();
		Assert.assertEquals(10, total);
	}

	@Test
	public void testGetTotalEmployeeWithMock() {
		EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
		PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
		EmployeeService service = new EmployeeService(employeeDao);
		int total = service.getTotalEmployee();
		Assert.assertEquals(10, total);
	}
	
	@Test
	public void testGetNameEmployeeWithMock() {
		//初始化，替代EmployeeDao的对象
		EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
		//mock修改EmployeeDao对应方法的值
		PowerMockito.when(employeeDao.getName()).thenReturn("HelloWorld");
		//new一个service
		EmployeeService service = new EmployeeService(employeeDao);
		//调用相关方法
		String name = service.getEmployeeName();
		//比较结果
		Assert.assertEquals("Hello World", name);
	}
	
	@Test
	public void testCreateEmloyee() {
		EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
		Employee employee = new Employee();
		PowerMockito.doNothing().when(employeeDao).addEmployee(employee);
		EmployeeService employeeService = new EmployeeService(employeeDao);
		employeeService.createEmployee(employee);
		Mockito.verify(employeeDao).addEmployee(employee);
	}
}








