package ixijian_main.mockdemo.test;

public class EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public EmployeeService(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao; 
	}
	/**
	* 获取所有员工的数量. 
	* @return
	*/
	public int getTotalEmployee() {
		return employeeDao.getTotal(); 
	}
	
	public void createEmployee(Employee employee) {
		employeeDao.addEmployee(employee);
	}
	
	public String getEmployeeName() {
		return employeeDao.getName();
	}
}
