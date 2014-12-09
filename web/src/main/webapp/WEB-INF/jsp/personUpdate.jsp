<%-- 
    Document   : update person
    Created on : Nov 23, 2014, 12:00:46 PM
    Author     : jrumanov
--%>

<%@ page contentType="text/html" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="person.edit.title"/>
<my:layout title="${title}">
 <jsp:attribute name="body">
<form:form method="post" action="${pageContext.request.contextPath}/person/update" modelAttribute="person">
    <form:hidden path="id"/>
    <fieldset><legend><fmt:message key="person.edit.edit"/></legend>
        <%@include file="form.jsp"%>
        <input type="submit" value="<fmt:message key='person.edit.save'/>">
    </fieldset>
</form:form>
</jsp:attribute>
</my:layout>