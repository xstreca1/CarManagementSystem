<%-- 
    Document   : formAdd
    Created on : Nov 25, 2014, 4:04:06 PM
    Author     : Mato
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table>
    <tr>
        <th><form:label path="name"><fmt:message key="person.name"/></form:label></th>
        <td><form:input path="name"/></td>
        <td><font color="red"> <form:errors path="name"></form:errors></font></td>
    </tr>
    <tr>
        <th><form:label path="sex"><fmt:message key="person.sex"/></form:label></th>
        <td><form:select path="sex">
                <form:option value="-"> <fmt:message key="enum.select"/></form:option>
                <form:options items="${enumValues}" />
            </form:select></td>
        <td><form:errors path="sex" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="nationality"><fmt:message key="person.nationality"/></form:label></th>
        <td><form:input path="nationality"/></td>
        <td><form:errors path="nationality" cssClass="error"/></td>
    </tr>
    <%-- <tr>
        <th><form:label path="dateOfBirth"><fmt:message key="person.dateOfBirth"/></form:label></th>
        <td><form:input path="dateOfBirth"/></td>
        <td><form:errors path="dateOfBirth" cssClass="error"/></td>
    </tr>--%>
    <tr>
        <th><form:label path="position"><fmt:message key="person.position"/></form:label></th>
        <td><form:input path="position"/></td>
        <td><form:errors path="position" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="employmentStatus"><fmt:message key="person.employmentStatus"/></form:label></th>
        <td><form:select path="employmentStatus">
                <form:option value="-"> <fmt:message key="enum.select"/></form:option>
                <form:options items="${enumValues}" />
            </form:select></td>
        <td><form:errors path="employmentStatus" cssClass="error"/></td>
    </tr>
    
    <tr>
        <th><form:label path="salary"><fmt:message key="person.salary"/></form:label></th>
        <td><form:input path="salary" maxlength="10"/></td>        
        <td><font color="red"> <form:errors path="salary"></form:errors></font></td>
    </tr>            
    
    
    <tr>
        <th><form:label path="isActive"><fmt:message key="person.isActive"/></form:label></th>
        <td><form:checkbox path="isActive"/></td>
        <td><form:errors path="isActive" cssClass="error"/></td>
    </tr>
    <%-- <tr>
        <th><form:label path="identificationNumber"><fmt:message key="person.identificationNumber"/></form:label></th>
        <td><form:input path="identificationNumber"/></td>
        <td><form:errors path="identificationNumber" cssClass="error"/></td>
    </tr>--%>

</table>
