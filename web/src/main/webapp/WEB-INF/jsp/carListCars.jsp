<%-- 
    Document   : listCars
    Created on : Nov 23, 2014, 3:44:50 PM
    Author     : jrumanov
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!-- title of the page -->
<fmt:message var="title" key="car.list.title"/>
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

                    <td style="background:white;padding:0;margin:0;">
                        <form method="get" action="${pageContext.request.contextPath}/car/update/${car.carID}">
                            <input type="submit" class="btn black" value="<fmt:message key='car.list.edit'/>">
                        </form>
                    </td>
                    <td style="background:white;padding:0;margin:0;">
                        <form method="post" action="${pageContext.request.contextPath}/car/delete/${car.carID}"modelAttribute="car1">
                            <input type="submit" class="btn black" value="<fmt:message key='car.list.delete'/>">
                        </form>
                    </td>  
                    <td style="background:white;padding:0;margin:0;">
                        <c:choose>
                            <c:when test="${car.availibility == false || principal.username == admin || car.mileage >= 200000}">
                                <form method="get" action="${pageContext.request.contextPath}/car/lease/${car.carID}"modelAttribute="lease">

                                    <input type="submit" class="btn blackOff" disabled="disabled" value="<fmt:message key='car.list.lease'/>">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="get" action="${pageContext.request.contextPath}/car/lease/${car.carID}"modelAttribute="lease">
                                    <input type="submit"  class="btn black" value="<fmt:message key='car.list.lease'/>">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td style="background:white;padding:0;margin:0;">
                        <form method="get" action="${pageContext.request.contextPath}/car/check/${car.carID}"modelAttribute="check">
                            <input type="submit" class="btn black" value="<fmt:message key='car.list.check'/>">
                        </form>
                    </td>  
                    <td style="background:white;padding:0;margin:0;">
                        <form method="get" action="${pageContext.request.contextPath}/car/showSC/${car.carID}">
                            <input type="submit" class="btn black" value="<fmt:message key='car.list.showSC'/>">
                        </form>
                    </td>
                </tr>
              </tr>
        </c:forEach>
    </table>

   
    <form:form method="post" action="${pageContext.request.contextPath}/car/add" modelAttribute="car">
        <fieldset><legend align="center"><fmt:message key="car.list.newCar"/></legend>
            <%@include file="carFormAdd.jsp"%>
            <input align="center" class="btn main" style="margin-top:20px;" type="submit" value="<fmt:message key='car.list.createCar'/>">
        </fieldset>
    </form:form>
</jsp:attribute>
</my:layout>
