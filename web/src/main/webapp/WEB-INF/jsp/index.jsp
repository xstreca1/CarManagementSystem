<%-- 
    Document   : index
    Created on : Nov 23, 2014, 2:35:19 PM
    Author     : Petr Potucek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- title of the page -->
<fmt:message var="title" key="index.title"/>
<my:layout title="${title}">

    <jsp:attribute name="body">
        <section class="sekce">
            <h1><fmt:message key="layout.menu.sections"/></h1>
            <c:choose>
                <c:when test="${pageContext.request.isUserInRole('ROLE_USER')}">
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

                                <c:choose>
                                    <c:when test="${car.mileage >= 200000}">

                                       <td> <font color="red"><c:out value="${car.mileage}"/></font></td>

                                    </c:when>
                                    <c:otherwise>
                                        <td><c:out value="${car.mileage}"/></td>
                                    </c:otherwise>
                                </c:choose>
                                <td><fmt:message key="car.color.${car.color}"/></td>
                                <td><fmt:message key="car.bodystyle.${car.bodystyle}"/></td>
                                <td><fmt:message key="car.category.${car.category}"/></td>
                                <td><c:out value="${car.VIN}"/></td>
                                <td><c:out value="${car.emissionstandard}"/></td> 
                                <td><fmt:message key="car.isActive.${car.isActive}"/></td>
                                <td><fmt:message key="car.availibility.${car.availibility}"/></td>


                                <td style="background:white;padding:0;margin:0;">
                                    <c:choose>
                                        <c:when test="${car.availibility == false || car.mileage >= 200000}">
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
                                    <form method="get" action="${pageContext.request.contextPath}/car/showSC/${car.carID}">
                                        <input type="submit" class="btn black" value="<fmt:message key='car.list.showSC'/>">
                                    </form>
                                </td>
                            </tr>                           
                        </c:forEach>
                    </table>
                </c:when >
                <c:otherwise>
                    <ul>
                        <li>
                            <div id="wrapper">
                                <a href="lease/">
                                    <div class="slideContainer">
                                        <img class="slideIcon" src="<c:url value="/resources/img/spravaPujcekEng.png" />"/>
                                    </div>
                                </a>
                            </div>
                        </li>
                        <li>
                            <div id="wrapper">
                                <a href="car/">
                                    <div class="slideContainer">
                                        <img class="slideIcon" src="<c:url value="/resources/img/spravaVozuEng.png" />"/>
                                    </div>
                                </a>
                            </div>
                        </li>
                        <li>
                            <div id="wrapper">
                                <a href="person/">
                                    <div class="slideContainer">
                                        <img class="slideIcon" src="<c:url value="/resources/img/zamestnanciEng.png" />"/>
                                    </div>
                                </a>
                            </div>
                        </li>
                        <li>
                            <div id="wrapper">
                                <a href="serviceCheck/">
                                    <div class="slideContainer">
                                        <img class="slideIcon" src="<c:url value="/resources/img/servis.png" />"/>
                                    </div>
                                </a>
                            </div>
                        </li>  
                    </ul>
                </c:otherwise>
            </c:choose>
        </section>
    </jsp:attribute>
</my:layout>


