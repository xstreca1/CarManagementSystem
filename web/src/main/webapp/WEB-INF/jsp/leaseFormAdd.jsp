<%-- 
    Document   : formAdd
    Created on : Nov 25, 2014, 4:07:43 PM
    Author     : Mato
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table>
    <tr>
        <th><form:label path="person"><fmt:message key="lease.person"/></form:label></th>
        <td><form:select path="person">                
                <form:options items="${people}" itemValue="id" itemLabel="id" />
            </form:select></td>
        <td><form:errors path="person" cssClass="error"/></td>
    </tr>
    <%--<tr>
        <th><form:label path="distance"><fmt:message key="lease.distance"/></form:label></th>
        <td><form:input path="distance"/></td>
        <td><form:errors path="distance" cssClass="error"/></td>
    </tr>--%>
    <tr>
        <th><form:label path="dateOfLease"><fmt:message key="lease.dateOfLease"/></form:label></th>
        <td><form:input path="dateOfLease"/></td>
        <td><font color="red"> <form:errors path="dateOfLease"></form:errors></font></td>
    </tr>
    <tr>
        <th><form:label path="dateOfReturn"><fmt:message key="lease.dateOfReturn"/></form:label></th>

            <td><form:input path="dateOfReturn"/></td>
        <td><font color="red"> <form:errors path="dateOfReturn"></form:errors></font></td>
    </tr>            
    <%-- <tr>
        <th><form:label path="returnedStatus"><fmt:message key="lease.returnedStatus"/></form:label></th>
        <td><form:select path="returnedStatus"/>
            <form:options items="${returnedStatus}" 
                          itemLabel="returnedStatus"
                          itemValue="returnedStatus" /></td>
        <td><form:errors path="returnedStatus" cssClass="error"/></td>
    </tr>--%>
    <tr>
        <th><form:label path="travelReason"><fmt:message key="lease.travelReason"/></form:label></th>
        <td><form:select path="travelReason">                
                <form:options items="${enumValues}" />
            </form:select></td>
        <td><font color="red"> <form:errors path="travelReason"></form:errors></font></td>
    </tr>
    <%-- <tr>
         <td><form:label path="person">
         <spring:message code="lang.person" text="Person" />
     </form:label></td>
 <td><form:select path="person">
         <form:options items="${person}" itemLabel="firstName"
                       itemValue="id" />
     </form:select></td>--%>

</tr>

</table>
