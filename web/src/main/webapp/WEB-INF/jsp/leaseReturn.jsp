<%-- 
    Document   : leaseReturn
    Created on : Dec 12, 2014, 7:17:48 PM
    Author     : Mato
--%>

<<%@ page contentType="text/html" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
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
                <th><fmt:message key="lease.car"/></th>
                <th><fmt:message key="lease.person"/></th>
                <th><fmt:message key="lease.distance"/></th>
                <th><fmt:message key="lease.dateOfLease"/></th>
                 <th><fmt:message key="lease.dateOfReturn"/></th>  
                <th><fmt:message key="lease.travelReason"/></th>
                <th><fmt:message key="lease.isClosed"/></th>                             
                <th><fmt:message key="lease.returnedStatus"/></th>
                
                <th></th>
            </tr>
            <c:forEach items="${leases}" var="lease">
                <tr>
                    <td>${lease.leaseId}</td>  
                    <td><c:out value="${lease.car}"/></td>
                    <td><c:out value="${lease.person}"/></td>
                    <td><c:out value="${lease.distance}"/></td>
                    <td><c:out value="${lease.dateOfLease}"/></td>
                    <td><c:out value="${lease.dateOfReturn}"/></td> 
                    <td><fmt:message key="lease.travelReason.${lease.travelReason}"/></td>
                    
                    <td><c:out value="${lease.isClosed}"/></td>
                                       
                    <td><fmt:message key="lease.returnedStatus.${lease.returnedStatus}"/></td>                            

                    <<td>
                        <form:form method="post" action="${pageContext.request.contextPath}/lease/confirmReturn/${lease.leaseId}" modelAttribute="lease">
                            
                            <fieldset><legend><fmt:message key="car.add.add"/></legend>
                                <%@include file="leaseReturnForm.jsp"%>
                                <input type="submit" value="<fmt:message key='car.add.save'/>">
                            </fieldset>
                        </form:form>
                    </td>
                    
                </tr>
            </c:forEach>
        </table>    
    </jsp:attribute>
</my:layout>
