package dxc.myproject.firstservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dxc.myproject.employee.Employee;
import dxc.myproject.employee.EmployeeDAO;


public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
    public AddEmployee() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String salary = request.getParameter("salary");
		String designation = request.getParameter("designation");
		
		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setEmail(email);
		emp.setSalary(salary);
		emp.setDesignation(designation);
		
		EmployeeDAO empDAO = new EmployeeDAO();
		int status = 0;
		try {
			status = empDAO.addEmployee(emp);
		} catch (SQLException e) {
			pw.println(e);
		}
		if(status > 0) {
		pw.println("Record Saved Successfully");
		request.getRequestDispatcher("index.html").include(request, response);
		}
		else
			pw.println("Sorry Unable to Save Record Successfully");
	}

}
