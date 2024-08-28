package com.hrms.servet.dao;

import java.sql.SQLException;
import java.util.List;

import com.hrms.servet.model.Role;

public interface RoleDao {
	public List<Role> getAllRoles() throws SQLException, ClassNotFoundException;

	public List<Role> getRoleById(String roleId) throws SQLException, ClassNotFoundException;

	public boolean insertRole(Role role) throws SQLException, ClassNotFoundException;

	public boolean updateRole(Role role) throws SQLException, ClassNotFoundException;

	public boolean deleteRole(String roleId) throws SQLException, ClassNotFoundException;
}
