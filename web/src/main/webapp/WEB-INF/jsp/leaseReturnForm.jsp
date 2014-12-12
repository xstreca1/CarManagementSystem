<%-- 
    Document   : leaseReturnForm
    Created on : Dec 12, 2014, 7:28:43 PM
    Author     : Mato
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
        <th><form:label path="returnedStatus"><fmt:message key="lease.returnedStatus"/></form:label></th>
        <td><form:select path="returnedStatus">
                <form:option value="-"> <fmt:message key="enum.select"/></form:option>
                <form:options items="${enumValues}" />
            </form:select></td>
        <td><form:errors path="returnedStatus" cssClass="error"/></td>
    </tr>    

</table>

