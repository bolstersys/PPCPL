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
	private static final Logger logger = Logger.getLogger(RoleServlet.class.getName());

	@Override
	public void init() {
		// Create a FileHandler
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler("D:/STS extract/STS-4.24/workspace/logs/mylog.log", true);

			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setLevel(Level.ALL);
		} catch (SecurityException | IOException e) {
			logger.severe("Error  in EmployeeServlet --> logger " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		switch (action) {
		case "insertEmployee":
			employeeService.insertEmployee(request, response);
			break;

		case "deleteEmployee":
			employeeService.deleteEmployee(request, response);
			break;

		case "getEmpoyeeById":
			employeeService.getEmployeeById(request, response);
			break;
			
		case "updateEmployee":
			employeeService.updateEmployee(request, response);
			break;

		default:
			employeeService.getAllEmployee(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
