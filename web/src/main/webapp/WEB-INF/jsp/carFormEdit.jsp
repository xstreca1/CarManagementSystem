<%-- 
    Document   : carFormEdit
    Created on : Dec 9, 2014, 1:03:27 AM
    Author     : Mato
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table>
    <tr>
        <th><form:label path="mileage"><fmt:message key="car.mileage"/></form:label></th>
        <td><form:input path="mileage"/></td>
        <td><form:errors path="mileage" cssClass="error"/></td>
    </tr>
    
    <tr>
        <th><form:label path="availibility"><fmt:message key="car.availibility"/></form:label></th>
        <td><form:checkbox path="availibility"/></td>
        <td><form:errors path="availibility" cssClass="error"/></td>
    </tr>    
    <tr>
        <th><form:label path="isActive"><fmt:message key="car.isActive"/></form:label></th>
        <td><form:checkbox path="isActive"/></td>
        <td><form:errors path="isActive" cssClass="error"/></td>
    </tr>

</table>