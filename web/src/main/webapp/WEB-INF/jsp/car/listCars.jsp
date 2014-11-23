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

<fmt:message var="title" key="car.list.title"/>
<my:layout title="${title}">
 <jsp:attribute name="body">

        <p><fmt:message key="car.list.allCars"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="car.carVIN"/></th>
                <th><fmt:message key="car.carCategory"/></th>
                <th><fmt:message key="car.carColor"/></th>
                <th><fmt:message key="car.carActions"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${cars}" var="car">
                <tr>
                    <td>${book.id}</td>
                    <td><c:out value="${car.carVIN}"/></td>
                    <td><c:out value="${car.carCategory}"/></td>
                    <td><fmt:message key="car.carColor.${car.carColor}"/></td>
                    <td><fmt:message key="Car.carActions.${car.carActions}"/></td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/car/update/${book.id}">
                            <input type="submit" value="<fmt:message key='car.list.edit'/>">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/car/delete/${book.id}">
                            <input type="submit" value="<fmt:message key='car.list.delete'/>">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

                <!-- add?-->
<form:form method="post" action="${pageContext.request.contextPath}/car/update" modelAttribute="car">
    <fieldset><legend><fmt:message key="car.list.newCar"/></legend>
    <%@include file="form.jsp"%>
    <input type="submit" value="<fmt:message key='car.list.createCar'/>">
    </fieldset>
</form:form>
</jsp:attribute>
</my:layout>