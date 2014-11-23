<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<h2>
		<spring:message code="lang.listCars" text="List of cars" />
	</h2>

	<c:if test="${empty carNew}">
		<p>
			<spring:message code="lang.noCars" text="Actions" />
		</p>
	</c:if>
		
	<c:if test="${not empty carNew}">
		<table id="standardTable">
			<thead>
				<tr>
					<th>ID</th>
					<th><spring:message code="lang.carVIN" text="VIN" /></th>
					<th><spring:message code="lang.carCategory" text="Category" /></th>
					<th><spring:message code="lang.carColor"
							text="Color" /></th>
					<th><spring:message code="lang.carActions" text="Actions" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${carNew}" var="car">
					<tr>
						<td>${machine.id}</td>
						<td>${machine.VIN}</td>
						<td>${machine.Category}</td>
						<td>${machine.color}</td>
						<td><input type="checkbox" name="carList" id="carList" value="${car.id}" /></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>ID</th>
					<th><spring:message code="lang.carVIN" text="VIN" /></th>
					<th><spring:message code="lang.carCategory" text="Category" /></th>
					<th><spring:message code="lang.carColor"
							text="Color" /></th>
					<th><spring:message code="lang.carActions" text="Actions" /></th>
				</tr>
			</tfoot>

		</table>
	</c:if>