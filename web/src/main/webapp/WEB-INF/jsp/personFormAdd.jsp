<%-- 
    Document   : formAdd
    Created on : Nov 25, 2014, 4:04:06 PM
    Author     : Mato
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="add">
    <tr>
        <th><form:label path="isAdmin"><fmt:message key="person.isAdmin"/></form:label></th>
        <td><form:checkbox path="isAdmin"/></td>
        <td><form:errors path="isAdmin" cssClass="error"/></td>
    </tr>
    <tr>        
        <th><form:label path="username"><fmt:message key="person.username"/></form:label></th>
        <td><form:input path="username" maxlength="10"/></td>
        <td><font color="red"> <form:errors path="username"></form:errors></font></td>
        </tr>
        <tr>
            <th><form:label path="password"><fmt:message key="person.password"/></form:label></th>
        <td><form:input path="password"/></td>
        <td><font color="red"> <form:errors path="password"></form:errors></font></td>
        </tr>
        <tr>
        <tr>        
            <th><form:label path="name"><fmt:message key="person.name"/></form:label></th>
        <td><form:input path="name"/></td>
        <td><font color="red"> <form:errors path="name"></form:errors></font></td>
        </tr>
        <tr>
            <th><form:label path="IdentificationNumber"><fmt:message key="person.identificationNumber"/></form:label></th>
        <td><form:input path="IdentificationNumber"/></td>
        <td><font color="red"> <form:errors path="IdentificationNumber"></form:errors></font></td>
        </tr>
        <tr>
            <th><form:label path="sex"><fmt:message key="person.sex"/></form:label></th>
        <td><form:select path="sex">
                <form:options items="${enumValues}" />
            </form:select></td>
        <td><form:errors path="sex" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="nationality"><fmt:message key="person.nationality"/></form:label></th>
        <td><form:input path="nationality" maxlength="2"/></td>
        <td><font color="red"> <form:errors path="nationality"></form:errors></font></td>
        </tr>
        <tr>
            <th><form:label path="position"><fmt:message key="person.position"/></form:label></th>
        <td><form:input path="position"/></td>
        <td><font color="red"> <form:errors path="position"></form:errors></font></td>
        </tr>
        <tr>
            <th><form:label path="employmentStatus"><fmt:message key="person.employmentStatus"/></form:label></th>
        <td><form:select path="employmentStatus">               
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

</table>
