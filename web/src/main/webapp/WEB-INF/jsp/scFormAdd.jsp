<%-- 
    Document   : formAdd
    Created on : Nov 25, 2014, 4:22:49 PM
    Author     : martin strecansky
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="add">
    <tr>
        <th id="markup"><form:label path="name"><fmt:message key="serviceCheck.name"/></form:label></th>
        <<td><form:select path="name">
                <form:option value="-"> <fmt:message key="enum.select"/></form:option>
                <form:options items="${enumValues}" />
            </form:select></td>
        <td><form:errors path="name" cssClass="error"/></td>
    </tr>    
    <tr>
        <th id="markup"><form:label path="serviceInterval"><fmt:message key="serviceCheck.serviceInterval"/></form:label></th>
        <td><form:input path="serviceInterval"/></td>
        <td><form:errors path="serviceInterval" cssClass="error"/></td>
    </tr>
    <tr>
        <th id="markup"><form:label path="lastCheck"><fmt:message key="serviceCheck.lastCheck"/></form:label></th>
        <td><form:input path="lastCheck"/></td>
        <td><form:errors path="lastCheck" cssClass="error"/></td>
    </tr>
    <tr>
        <th id="markup"><form:label path="description"><fmt:message key="serviceCheck.description"/></form:label></th>
        <td><form:textarea path="description"/></td>
        <td><form:errors path="description" cssClass="error"/></td>
    </tr>
    

</table>
