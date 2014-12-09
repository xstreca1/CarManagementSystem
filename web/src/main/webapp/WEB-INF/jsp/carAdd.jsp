<%-- 
    Document   : add
    Created on : Nov 25, 2014, 3:55:52 PM
    Author     : Mato
--%>

<%@ page contentType="text/html" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="car.add.title"/>
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
                <th><fmt:message key="car.enginePower"/></th>
                <th><fmt:message key="car.gasConsumption"/></th>
                <th><fmt:message key="car.transmission"/></th>
                <th><fmt:message key="car.category"/></th>
                <th><fmt:message key="car.VIN"/></th>
                <th><fmt:message key="car.emissionstandard"/></th>
                <th><fmt:message key="car.isActive"/></th>
                <th><fmt:message key="car.availibility"/></th>
                <th></th>
                <th></th>
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
                    <td><c:out value="${car.enginePower}"/></td>                    
                    <td><c:out value="${car.gasConsumption}"/></td>   
                    <td><fmt:message key="car.transmission.${car.transmission}"/></td>
                    <td><fmt:message key="car.category.${car.category}"/></td>
                    <td><c:out value="${car.VIN}"/></td> 
                    <td><c:out value="${car.emissionstandard}"/></td> 
                    <td><fmt:message key="car.isActive.${car.isActive}"/></td>
                    <td><fmt:message key="car.availibility.${car.availibility}"/></td>

                    <td>
                        <form:form method="post" action="${pageContext.request.contextPath}/car/edit/${car.carID}" modelAttribute="car">
                            
                            <fieldset><legend><fmt:message key="car.add.add"/></legend>
                                <%@include file="carFormEdit.jsp"%>
                                <input type="submit" value="<fmt:message key='car.add.save'/>">
                            </fieldset>
                        </form:form>
                    </td>
                    <
                </tr>
            </c:forEach>
        </table>

    </jsp:attribute>
</my:layout>
