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
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="dateOfBirth"><fmt:message key="person.dateOfBirth"/></form:label></th>
                <td><form:input path="dateOfBirth"/></td>
                <td><form:errors path="dateOfBirth" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="position"><fmt:message key="person.position"/></form:label></th>
                <td><form:input path="position"/></td>
                <td><form:errors path="position" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="nationality"><fmt:message key="person.nationality"/></form:label></th>
                <td><form:input path="nationality"/></td>
                <td><form:errors path="nationality" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="salary"><fmt:message key="person.salary"/></form:label></th>
                <td><form:input path="salary"/></td>
                <td><form:errors path="salary" cssClass="error"/></td>
            </tr>            
            <tr>
                <th><form:label path="employmentStatus"><fmt:message key="person.employmentStatus"/></form:label></th>
                <td><form:select path="employmentStatus"/>
                    <form:options items="${employmentStatus}" 
                                  itemLabel="employmentStatus"
                                  itemValue="employmentStatus" /></td>
                <td><form:errors path="employmentStatus" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="sex"><fmt:message key="person.sex"/></form:label></th>
                <td><form:select path="sex"/>
                    <form:options items="${sex}" 
                                  itemLabel="sex"
                                  itemValue="sex" /></td>
                <td><form:errors path="sex" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="2"><button class="button" type="button"
                                        value="Add person"
                                        <%-- onclick="javascript:validateAndSubmitAddLoanForm()"> --%>
                                        <spring:message code="lang.addPerson" text="Add person" />
                </button></td>
            </tr>
    </table>
