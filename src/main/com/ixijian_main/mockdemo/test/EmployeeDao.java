package ixijian_main.mockdemo.test;

public class EmployeeDao {
	
	public int getTotal() {
		System.err.println("专注测试显示错误");
		throw new UnsupportedOperationException();
	}

	public void addEmployee(Employee employee) {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new NullPointerException();
	}
}
