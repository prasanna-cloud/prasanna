package dxc.myproject.firstservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dxc.myproject.employee.EmployeeDAO;


public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteEmployee() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		
		try {
			EmployeeDAO.deleteEmployee("id");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
