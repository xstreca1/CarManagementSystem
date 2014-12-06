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

        <p><fmt:message key="serviceCheck.list.title"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="serviceCheck.name"/></th>
                <th><fmt:message key="serviceCheck.serviceInterval"/></th>
                <th><fmt:message key="serviceCheck.lastCheck"/></th>
                <th><fmt:message key="serviceCheck.description"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${checks}" var="serviceCheck">
                <tr>
                    <td>${serviceCheck.id}</td>
                    <td><fmt:message key="serviceCheck.name.${serviceCheck.name}"/></td>
                    <td><c:out value="${serviceCheck.serviceInterval}"/></td>                    
                    <td><c:out value="${serviceCheck.lastCheck}"/></td>                    
                    <td><c:out value="${serviceCheck.description}"/></td>                    
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/serviceCheck/update/${serviceCheck.id}">
                            <input type="submit" value="<fmt:message key='serviceCheck.list.edit'/>">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/serviceCheck/delete/${serviceCheck.id}">
                            <input type="submit" value="<fmt:message key='serviceCheck.list.delete'/>">
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
