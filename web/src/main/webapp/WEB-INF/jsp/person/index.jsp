<%-- 
    Document   : index for person
    Created on : Nov 28, 2014, 5:07:52 PM
    Author     : jrumanov
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- title of the page -->
<fmt:message var="title" key="person.index.title"/>
<my:layout title="${title}">

    <jsp:attribute name="body">

        <a href="/person/add">Add a new car</a><br />
        <a href="/person/listPeople?isInactive=false">list all active cars</a><br />
        <a href="/person/listPeople?isInactive=true">list all cars</a>
    </jsp:attribute>
</my:layout>

