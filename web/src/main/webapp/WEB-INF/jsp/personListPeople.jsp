<%-- 
    Document   : listPeople
    Created on : Nov 23, 2014, 12:27:30 PM
    Author     : jrumanov
--%>

<%@ page contentType="text/html" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="person.list.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="person.name"/></th>
                <th><fmt:message key="person.username"/></th>
                <th><fmt:message key="person.isAdmin"/></th>
                <th><fmt:message key="person.identificationNumber"/></th>              
                <th><fmt:message key="person.nationality"/></th>
                <th><fmt:message key="person.sex"/></th>                
                <th><fmt:message key="person.employmentStatus"/></th>
                <th><fmt:message key="person.position"/></th>
                <th><fmt:message key="person.salary"/></th>
                <th><fmt:message key="person.isActive"/></th>

            </tr>
            <c:forEach items="${people}" var="person">
                <tr>
                    <td>${person.id}</td>
                    <td><c:out value="${person.name}"/></td>
                    <td><c:out value="${person.username}"/></td>
                    <td><fmt:message key="person.isAdmin.${person.isAdmin}"/></td>
                    <td><c:out value="${person.identificationNumber}"/></td>                    
                    <td><c:out value="${person.nationality}"/></td>
                    <td><fmt:message key="person.sex.${person.sex}"/></td>                 
                    <td><fmt:message key="person.employmentStatus.${person.employmentStatus}"/></td>
                    <td><c:out value="${person.position}"/></td>
                    <td><c:out value="${person.salary}"/></td>
                    <td><fmt:message key="person.isActive.${person.isActive}"/></td>
                    
                    <td style="background:white;">
                        <form method="get" action="${pageContext.request.contextPath}/person/update/${person.id}" modelAttribute="person">
                            <input class="btn black" type="submit" value="<fmt:message key='person.list.edit'/>">
                        </form>
                    </td>
                    <td style="background:white;">
                        <form method="post" action="${pageContext.request.contextPath}/person/delete/${person.id}"modelAttribute="person1">
                            <input class="btn black" type="submit" value="<fmt:message key='person.list.delete'/>">
                        </form>
                    </td>
                    <td style="background:white;">
                        <form method="get" action="${pageContext.request.contextPath}/person/statistics/${person.id}">
                            <input class="btn black" type="submit" value="<fmt:message key='person.list.getTravelStatsForPerson'/>">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <form:form method="post" action="${pageContext.request.contextPath}/person/add" modelAttribute="person">
            <fieldset><legend align="center"><fmt:message key="person.list.newperson"/></legend>
                <%@include file="personFormAdd.jsp"%>
                <input type="submit" class="btn main" value="<fmt:message key='person.list.addPerson'/>">
            </fieldset>
        </form:form>
    </jsp:attribute>
</my:layout>
