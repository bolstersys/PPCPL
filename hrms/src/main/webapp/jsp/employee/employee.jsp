<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<jsp:include page="../../jsp/header/header.jsp" />

<c:if test="${action == 'getAllEmployeeData'}">
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Sr. No.</th>
				<th scope="col">Employee Id</th>
				<th scope="col">Employee Name</th>
				<th scope="col">Employee Role</th>
				<th scope="col">Employee Department</th>
				
				<th scope="col">Employee Age</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty employeeList}">
				<tr><td>No data found
				</td></tr>
			</c:if>
			<c:if test="${not empty employeeList}">
				<c:forEach items="${employeeList}" var="item" varStatus="loop">
					<tr>
						<td>${loop.index +1}</td>
						<td>${item.employeeId}</td>
						<td>${item.employeeFirstName} ${item.employeeMiddleName} ${item.employeeLastName }</td>
						<td>${item.role }</td>
						<td> </td>
						<td>${item.employeeAge }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</c:if>

<jsp:include page="../../jsp/footer/footer.jsp" />