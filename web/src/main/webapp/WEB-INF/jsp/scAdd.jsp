<%-- 
    Document   : add
    Created on : Nov 23, 2014, 12:00:07 PM
    Author     : martin strecansky
--%>

<%@ page contentType="text/html" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="serviceCheck.add.title"/>
<my:layout title="${title}">
 <jsp:attribute name="body">
<form:form method="post" action="${pageContext.request.contextPath}/serviceCheck/add" modelAttribute="serviceCheck">
    <form:hidden path="id"/>
    <fieldset><legend><fmt:message key="serviceCheck.add.add"/></legend>
        <%@include file="formAdd.jsp"%>
        <input class="btn black" type="submit" value="<fmt:message key='serviceCheck.add.save'/>">
    </fieldset>
</form:form>
</jsp:attribute>
</my:layout>