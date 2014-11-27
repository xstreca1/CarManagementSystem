<%-- 
    Document   : form
    Created on : Nov 23, 2014, 5:07:39 PM
    Author     : jrumanov
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table>
    <tr>
        <th><form:label path="distance"><fmt:message key="lease.distance"/></form:label></th>
        <td><form:input path="distance"/></td>
        <td><form:errors path="distance" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="dateOfLease"><fmt:message key="lease.dateOfLease"/></form:label></th>
        <td><form:input path="dateOfLease"/></td>
        <td><form:errors path="dateOfLease" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="dateOfReturn"><fmt:message key="lease.dateOfReturn"/></form:label></th>
        <td><form:checkbox path="dateOfReturn"/></td>
        <td><form:errors path="dateOfReturn" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="isClosed"><fmt:message key="lease.isClosed"/></form:label></th>
        <td><form:checkbox path="isClosed"/></td>
        <td><form:errors path="isClosed" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="returnedStatus"><fmt:message key="lease.returnedStatus"/></form:label></th>
        <td><form:select path="returnedStatus">
                <c:forEach items="${returnedStatuses}" var="r">
                    <form:option value="${r}"><fmt:message key="Lease.ReturnedStatus.${r}"/></form:option>
                </c:forEach>
            </form:select>
        </td>
        <td><form:errors path="returnedStatus" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="travelReason"><fmt:message key="lease.travelReason"/></form:label></th>
        <td><form:select path="travelReason">
                <c:forEach items="${travelReasons}" var="t">
                    <form:option value="${r}"><fmt:message key="Lease.TravelReason.${r}"/></form:option>
                </c:forEach>
            </form:select>
        </td>
        <td><form:errors path="travelReason" cssClass="error"/></td>
    </tr>
</table>

