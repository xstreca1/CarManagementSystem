<%-- 
    Document   : leaseCar
    Created on : Dec 10, 2014, 3:52:04 PM
    Author     : Mato
--%>

<%@ page contentType="text/html" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="lease.add.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="car.vehicleRegPlate"/></th>
                <th><fmt:message key="car.brand"/></th>
                <th><fmt:message key="car.typeName"/></th>
                <th><fmt:message key="car.yearOfManufacture"/></th>
                <th><fmt:message key="car.mileage"/></th>
                <th><fmt:message key="car.color"/></th>
                <th><fmt:message key="car.bodystyle"/></th>                           
                <th><fmt:message key="car.category"/></th>
                <th><fmt:message key="car.VIN"/></th>
                <th><fmt:message key="car.emissionstandard"/></th>
                <th><fmt:message key="car.isActive"/></th>
                <th><fmt:message key="car.availibility"/></th>
            </tr>
            <c:forEach items="${cars}" var="car">
                <tr>
                    <td>${car.carID}</td>
                    <td><c:out value="${car.vehicleRegPlate}"/></td>
                    <td><c:out value="${car.brand}"/></td>
                    <td><c:out value="${car.typeName}"/></td>
                    <td><c:out value="${car.yearOfManufacture}"/></td>
                    <td><c:out value="${car.mileage}"/></td>
                    <td><fmt:message key="car.color.${car.color}"/></td>
                    <td><fmt:message key="car.bodystyle.${car.bodystyle}"/></td>                    
                    <td><fmt:message key="car.category.${car.category}"/></td>
                    <td><c:out value="${car.VIN}"/></td> 
                    <td><c:out value="${car.emissionstandard}"/></td> 
                    <td><fmt:message key="car.isActive.${car.isActive}"/></td>
                    <td><fmt:message key="car.availibility.${car.availibility}"/></td>
                </tr>    
                <tr>
                    <form:form method="post" action="${pageContext.request.contextPath}/car/confirmLease/${car.carID}" modelAttribute="lease">

                    <fieldset><legend  align="center"><fmt:message key="lease.add.add"/></legend>
                        <%@include file="leaseFormAdd.jsp"%>
                        <input type="submit" align="center" class="btn main" value="<fmt:message key='lease.edit.save'/>">
                    </fieldset>
                </form:form>
            </tr>

        </c:forEach>
    </table>

</jsp:attribute>
</my:layout>