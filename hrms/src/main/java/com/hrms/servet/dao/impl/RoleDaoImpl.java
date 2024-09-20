package com.hrms.servet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hrms.servet.dao.RoleDao;
import com.hrms.servet.model.Role;
import com.hrms.servet.util.Utility;

public class RoleDaoImpl implements RoleDao {

	private static String GET_ALL_ROLE_QUERY = "SELECT * FROM role";
	private static String GET_ROLE_BY_ID_QUERY = "SELECT role.role_id, role.role_name, role.role_level, role.reporting_role_id, role.created_date, role.updated_date FROM role WHERE role.role_id = ?";
	private static String INSERT_ROLE_QUERY = "INSERT INTO role (role_name, role_level, reporting_role_id) VALUES (?, ?, ?)";
	private static String UPDATE_ROLE_QUERY = "UPDATE role SET role_name = ?, role_level = ?, reporting_role_id = ? WHERE role_id = ?";
	private static String DELETE_ROLE_QUERY = "DELETE FROM role WHERE role_id = ?";

	@Override
	public List<Role> getAllRoles() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Role> roleList = new ArrayList<>();
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ROLE_QUERY)) {

			System.out.println(preparedStatement);

			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Process the ResultSet object
			while (rs.next()) {;

				Role role = new Role();
				role.setRoleCode(rs.getString("role_id"));
				role.setRoleName(rs.getString("role_name"));
				role.setRoleLevel(rs.getString("role_level"));
				roleList.add(role);
			}

		} catch (Exception e) {
			e.printStackTrace();
//			throw e;
		}
		return roleList;
	}

	@Override
	public List<Role> getRoleById(String roleId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Role> roleList = new ArrayList<>();
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE_BY_ID_QUERY)) {
			preparedStatement.setString(1, roleId);
			System.out.println(preparedStatement);

			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Process the ResultSet object
			while (rs.next()) {
				String roleid = rs.getString("role_id");
				String roleName = rs.getString("role_name");
				String roleLevel = rs.getString("role_level");
				String roleReportingTo = rs.getString("reporting_role_id");

				Role role = new Role();
				role.setRoleCode(roleid);
				role.setRoleName(roleName);
				role.setRoleLevel(roleLevel);
				roleList.add(role);
			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		}
		return roleList;
	}

	@Override
	public void insertRole(Role role) throws SQLException, ClassNotFoundException {
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE_QUERY)) {

			preparedStatement.setString(1, role.getRoleName());
			preparedStatement.setString(2, role.getRoleLevel());

			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateRole(Role role) throws SQLException, ClassNotFoundException {
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_QUERY)) {

			preparedStatement.setString(1, role.getRoleName());
			preparedStatement.setString(2, role.getRoleLevel());
			preparedStatement.setString(4, role.getRoleCode());

			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public boolean deleteRole(String roleId) throws SQLException, ClassNotFoundException {
		boolean isDeleted = false;
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_QUERY)) {

			preparedStatement.setString(1, roleId);

			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();
			isDeleted = true;
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		}
		return isDeleted;
	}

}
