<%@ page language="java" contentType="text/html;  charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<%@include file="../jsp/head.jsp"%>
<body>
	<!-- Always shows a header, even in smaller screens. -->
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<%@include file="../jsp/menu.jsp"%>
		
		<main class="md1-layout_content">
		<div class="page-content">
			<div class="mdl-grid center-items">
				<div class="mdl-cell mdl-cell--4-col">
					<div>
						<table
							class="center mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
							<thead>
								<tr>
									<th class="mdl-data-table_cell--non-numeric">NO</th>
									<th>Name</th>
									<th>Surname</th>
									<th>Birthdate</th>
									<th>Creation Time</th>
									<th>Age</th>
									<th>Id</th>
									<th>Type</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="0" scope="page" />
								<c:forEach var="user" items="${userList}">
									<c:set var="count" value="${count + 1}" scope="page" />
									<tr>
										<td class="mdl-data-table_cell--non-numeric"><c:out value="${count}" /></td>
										<td><c:out value="${user.name}" /></td>
										<td><c:out value="${user.surname}" /></td>
										<td><c:out value="${user.birthdate}" /></td>
										<td><c:out value="${user.creationtime}" /></td>
										<td><c:out value="${user.age}" /></td>
										<td><c:out value="${user.id}" /></td>
										<td><c:out value="${user.type}" /></td>
										<td><a href="UserController?op=edit&id=<c:out value='${user.id}' />">Edit</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="UserController?op=delete&id=<c:out value='${user.id}' />">Delete</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</main>
	</div>
</body>