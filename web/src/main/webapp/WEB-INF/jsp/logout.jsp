<%-- 
    Document   : logout
    Created on : Jan 24, 2015, 8:13:34 PM
    Author     : jozefpuchly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

'<%=request.getRemoteUser()%>' <fmt:message key="layout.menu.loggedOUT"/>

<% session.invalidate();%>

<a href="${pageContext.request.contextPath}/">
    <fmt:message key="layout.menu.login"/>
</a>