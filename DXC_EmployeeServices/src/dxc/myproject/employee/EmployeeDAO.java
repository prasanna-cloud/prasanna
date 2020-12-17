package dxc.myproject.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// DAO: Data Access Object
public class EmployeeDAO {

	String JDBC_DRIVER, DB_URL, USER, PASS;
	static Connection conn;
	public EmployeeDAO() {
		super();
		// Step 1: Declaration of Driver Name
				JDBC_DRIVER = "com.mysql.jdbc.Driver";
				
				// Step 2: Loading of Driver with Connection String and Username and Password of the DB
				DB_URL = "jdbc:mysql://localhost:3306/employeeservicesdatabase";
				USER = "root";
				PASS = "prasanna";

				// Step 3: Creating Connection Class object to Actually Connect to the Database
				try {
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public Employee getEmployee(String empid) throws SQLException {
		// logic here for Getting Employee
		Employee emp = new Employee();
		PreparedStatement ps = conn.prepareStatement("select * from employeedata where id = ?");
		ps.setString(1, empid);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			emp.setId(rs.getString(1));
			emp.setName(rs.getString(2));
			emp.setEmail(rs.getString(3));
			emp.setSalary(rs.getString(4));
			emp.setDesignation(rs.getString(5));
		}
		return emp;
	}
	
	public List<Employee> getAllEmployees() throws SQLException {
		// logic here for Getting all Employees
		List<Employee> mList = new ArrayList<Employee>();
		PreparedStatement ps = conn.prepareStatement("select * from employeedata");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee emp = new Employee();
			emp.setId(rs.getString(1));
			emp.setName(rs.getString(2));
			emp.setEmail(rs.getString(3));
			emp.setSalary(rs.getString(4));
			emp.setDesignation(rs.getString(5));
			mList.add(emp);
		}
		return mList;
	}
	
	public int addEmployee(Employee employee) throws SQLException {
		// Logic here for Adding Employee
		PreparedStatement ps =
		conn.prepareStatement("insert into employeedata(id, name, email, salary, designation)  values(?,?,?,?,?)");
		ps.setString(1, employee.getId());
		ps.setString(2, employee.getName());
		ps.setString(3, employee.getEmail());
		ps.setString(4, employee.getSalary());
		ps.setString(5, employee.getDesignation());
		return ps.executeUpdate();
	}
	
	public int editUpdateEmployee(Employee employee) throws SQLException {
		// Logic here for Editing Employee
		PreparedStatement ps = 
		conn.prepareStatement("update employeedata set name=?, email=?, salary=?, designation=? where id=?");
		ps.setString(1, employee.getName());
		ps.setString(2, employee.getEmail());
		ps.setString(3, employee.getSalary());
		ps.setString(4, employee.getDesignation());
		ps.setString(5, employee.getId());
		return ps.executeUpdate();
	}
	
	public static int deleteEmployee(String empid) throws SQLException {
		// Logic here for Deleting Employee
			PreparedStatement ps = conn.prepareStatement("delete from employeedata where id = ?");
			ps.setString(1, empid);
			return ps.executeUpdate();
	}
}
