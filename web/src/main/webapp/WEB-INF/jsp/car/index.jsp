<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- title of the page -->
<fmt:message var="title" key="lease.list.title"/>
<my:layout title="${title}">

    <jsp:attribute name="body">
        <a href="/car/add">Add a new car</a>
        <a href="/car/listCars?isInactive=false">list all active cars</a>
        <a href="/car/listCars?isInactive=true">list all cars</a>
    </jsp:attribute>
</my:layout>

