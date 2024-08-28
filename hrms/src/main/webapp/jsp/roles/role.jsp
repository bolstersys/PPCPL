<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<jsp:include page="../../jsp/header/header.jsp" />

<c:if test="${action == 'getAllRoleData'}">
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Sr. No.</th>
				<th scope="col">Role</th>
				<th scope="col">Role Code</th>
				<th scope="col">Role Level</th>
				<th scope="col">Role Reporting to</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty roleList}">
				<tr>No data found
				</tr>
			</c:if>
			<c:if test="${not empty roleList}">
				<c:forEach items="${roleList}" var="item" varStatus="loop">
					<tr>
						<td>${loop.index +1}</td>
						<td>${item.roleName }</td>
						<td>${item.roleCode }</td>
						<td>${item.roleLevel }</td>
						<td>${item.roleReportingTo }</td>
						<td></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</c:if>

<jsp:include page="../../jsp/footer/footer.jsp" />