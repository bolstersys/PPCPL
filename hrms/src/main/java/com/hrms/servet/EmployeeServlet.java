package com.hrms.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.hrms.servet.service.EmployeeService;
import com.hrms.servet.service.impl.EmployeeServiceImpl;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = { "/employee/*" })
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final EmployeeService employeeService = new EmployeeServiceImpl();
	private static final Logger logger = Logger.getLogger(EmployeeServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		switch (action) {
			case "insertEmployee", "updateEmployee":
				employeeService.loadEmployeeForm(request, response);
				break;
			case "deleteEmployee":
				employeeService.deleteEmployee(request, response);
				break;
			default:
				employeeService.getAllEmployee(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action =  request.getParameter("action");
		switch (action) {
			case "insertEmployee":
				employeeService.insertEmployee(request, response);
				break;
			case "updateEmployee":
				employeeService.updateEmployee(request, response);
				break;
			default:
				doGet(request, response);
				break;
		}
	}
}
