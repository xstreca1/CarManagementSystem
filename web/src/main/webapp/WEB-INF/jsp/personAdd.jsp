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

<fmt:message var="title" key="person.edit.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <p><fmt:message key="person.list.allPeople"/></p>

        <table class="basic" id="add">
            <tr>
                <th>id</th>
                <th><fmt:message key="person.name"/></th>
                <th><fmt:message key="person.identificationNumber"/></th>
                    <%--  <th><fmt:message key="person.dateOfBirth"/></th>--%>
                <th><fmt:message key="person.nationality"/></th>
                <th><fmt:message key="person.sex"/></th>
                    <%--<th><fmt:message key="person.address"/></th> <!-- pozret co so zavislou entitou-->--%>
                <th><fmt:message key="person.employmentStatus"/></th>
                <th><fmt:message key="person.position"/></th>
                <th><fmt:message key="person.salary"/></th>
                <th><fmt:message key="person.isActive"/></th>

                <th></th>
                <th></th>
            </tr>

            <c:forEach items="${people}" var="person">

                <td>${person.id}</td>
                <td><c:out value="${person.name}"/></td>
                <td><c:out value="${person.identificationNumber}"/></td>
                <%-- <td><c:out value="${person.dateOfBirth}"/></td>--%>
                <td><c:out value="${person.nationality}"/></td>
                <td><fmt:message key="person.sex.${person.sex}"/></td>
                <%-- <td><c:out value="${person.address}"/></td>--%>
                <td><fmt:message key="person.employmentStatus.${person.employmentStatus}"/></td>
                <td><c:out value="${person.position}"/></td>
                <td><c:out value="${person.salary}"/></td>
                <td><fmt:message key="person.isActive.${person.isActive}"/></td>
                <td>
                    <form:form method="post" action="${pageContext.request.contextPath}/person/edit/${person.id}" modelAttribute="person">
                        <fieldset><legend><fmt:message key="person.add.add"/></legend>
                            <%@include file="personEditForm.jsp"%>
                            <input type="submit" value="<fmt:message key='person.add.save'/>">
                        </fieldset>
                    </form:form>
                </td>
            </c:forEach>
        </table>


    </jsp:attribute>
</my:layout>
