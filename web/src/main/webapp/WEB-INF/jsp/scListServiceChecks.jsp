<%-- 
    Document   : listServiceChecks
    Created on : Nov 23, 2014, 12:25:02 PM
    Author     : jrumanov
--%>

<%@ page contentType="text/html" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="serviceCheck.list.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="serviceCheck.name"/></th>
                <th><fmt:message key="serviceCheck.serviceInterval"/></th>
                <th><fmt:message key="serviceCheck.lastCheck"/></th>
                <th><fmt:message key="serviceCheck.nextCheck"/></th>
                <th><fmt:message key="serviceCheck.description"/></th>
                <th><fmt:message key="serviceCheck.car"/></th>
       
            </tr>
            <c:forEach items="${checks}" var="serviceCheck">
                <tr>
                    <td>${serviceCheck.scID}</td>
                    <td><fmt:message key="serviceCheck.name.${serviceCheck.name}"/></td>
                    <td><c:out value="${serviceCheck.serviceInterval}"/></td>                    
                    <td><c:out value="${serviceCheck.lastCheck}"/></td> 
                    <td><c:out value="${serviceCheck.nextCheck}"/></td> 
                    <td><c:out value="${serviceCheck.description}"/></td> 
                    <td><c:out value="${serviceCheck.car}"/></td>
                    <td style="background:white;">
                        <form method="get" action="${pageContext.request.contextPath}/serviceCheck/perform/${serviceCheck.scID}">
                            <input class="btn black" type="submit" value="<fmt:message key='serviceCheck.list.edit'/>">
                        </form>
                    </td>                  

                </tr>
            </c:forEach>
        </table>
<%-- 
        <form:form method="post" action="${pageContext.request.contextPath}/serviceCheck/update" modelAttribute="serviceCheck">
            <fieldset><legend><fmt:message key="serviceCheck.list.newServiceCheck"/></legend>
                <%@include file="scForm.jsp"%>
                <input type="submit" value="<fmt:message key='serviceCheck.list.createServiceCheck'/>">
            </fieldset>
        </form:form>--%>
    </jsp:attribute>
</my:layout>
