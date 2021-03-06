<%-- 
    Document   : listLeases
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
<fmt:message var="title" key="lease.list.title"/>
<my:layout title="${title}">

    <jsp:attribute name="body">

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="lease.car"/></th>
                <th><fmt:message key="lease.person"/></th>                
                <th><fmt:message key="lease.dateOfLease"/></th>
                <th><fmt:message key="lease.dateOfReturn"/></th>  
                <th><fmt:message key="lease.travelReason"/></th>
                <th><fmt:message key="lease.distance"/></th>
                <th><fmt:message key="lease.isClosed"/></th>                             
                <th><fmt:message key="lease.returnedStatus"/></th>


            </tr>
            <c:forEach items="${leases}" var="lease">
                <tr>
                    <td>${lease.leaseId}</td>  
                    <td><c:out value="${lease.car}"/></td>
                    <td><c:out value="${lease.person}"/></td>                    
                    <td><c:out value="${lease.dateOfLease}"/></td>
                    <td><c:out value="${lease.dateOfReturn}"/></td> 
                    <td><fmt:message key="lease.travelReason.${lease.travelReason}"/></td>
                    <td><c:out value="${lease.distance}"/></td>
                    <td><c:out value="${lease.isClosed}"/></td>                                       
                    <td><fmt:message key="lease.returnedStatus.${lease.returnedStatus}"/></td>              
                    <td style="background:white;">
                        <c:choose>
                            <c:when test="${lease.isClosed}">
                                <form method="get" action="${pageContext.request.contextPath}/lease/return/${lease.leaseId}">
                                    <input class="btn blackOff" type="submit" disabled="disabled" value="<fmt:message key='lease.list.edit'/>">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="get" action="${pageContext.request.contextPath}/lease/return/${lease.leaseId}">
                                    <input class="btn black" type="submit" value="<fmt:message key='lease.list.edit'/>">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>

                </tr>
            </c:forEach>
        </table>

        
    </jsp:attribute>
</my:layout>
