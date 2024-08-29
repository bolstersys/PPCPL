package com.hrms.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.hrms.servet.service.RoleService;
import com.hrms.servet.service.impl.RoleServiceImpl;

@WebServlet(name = "RoleServlet", urlPatterns = {"/role/*"})
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleServiceImpl();

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
			logger.severe("Error  in RoleServlet --> logger "+e.getMessage());
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
//		String action = "/role";
//		logger.info("i "+action);
//		System.out.print("fi "+action);
//		if(!action.contains(".jsp")) {
		switch (action) {
//		case "role":
//            	roleService.showRolePage(request, response);
//			break;
		case "insertRole", "updateRole":
			roleService.loadRoleForm(request, response);
			break;
		case "delete":
			roleService.deleteRole(request, response);
			break;
		default:
			roleService.getAllRoles(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action =  request.getParameter("action");
		switch (action) {
			case "insertRole":
				roleService.insertRole(request, response);
				break;
			case "updateRole":
				roleService.updateRole(request, response);
				break;
			case "delete":
				roleService.deleteRole(request, response);
				break;
			default:
				doGet(request, response);
				break;
		}
	}

}
