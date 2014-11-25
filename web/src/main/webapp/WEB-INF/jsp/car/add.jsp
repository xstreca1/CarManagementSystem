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
<form:form method="post" action="${pageContext.request.contextPath}/car/add" modelAttribute="car">
    <form:hidden path="id"/>
    <fieldset><legend><fmt:message key="car.add.add"/></legend>
        <%@include file="formAdd.jsp"%>
        <input type="submit" value="<fmt:message key='car.edit.save'/>">
    </fieldset>
</form:form>
</jsp:attribute>
</my:layout>
