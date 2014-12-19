<%-- 
    Document   : formAdd
    Created on : Nov 25, 2014, 4:07:43 PM
    Author     : Mato
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table id="add">
    <tr>
        <th id="markup"><form:label path="person"><fmt:message key="lease.person"/></form:label></th>
        <td><form:select path="person">
               
                <form:options items="${people}" itemValue="id" itemLabel="id" />
            </form:select></td>
        <td><form:errors path="person" cssClass="error"/></td>
    </tr>
   
    <tr>
        <th id="markup"><form:label path="dateOfLease"><fmt:message key="lease.dateOfLease"/></form:label></th>
        <td><form:input id="dateOfLease" path="dateOfLease"/></td>
        <td><form:errors path="dateOfLease" cssClass="error"/></td>
    </tr>
    <tr>
        <th id="markup"><form:label path="dateOfReturn"><fmt:message key="lease.dateOfReturn"/></form:label></th>

            <td><form:input id="dateOfReturn" path="dateOfReturn"/></td>
        <td><form:errors path="dateOfReturn" cssClass="error"/></td>
    </tr>            
    
    <tr>
        <th id="markup"><form:label path="travelReason"><fmt:message key="lease.travelReason"/></form:label></th>
        <td><form:select path="travelReason">               
                <form:options items="${enumValues}" />
            </form:select></td>
        <td><form:errors path="travelReason" cssClass="error"/></td>
    </tr>
    
</tr>

</table>
<script>
    $(function () {
        $("#dateOfLease").datepicker({dateFormat: 'mm-dd-yy'}).val();
        $("#dateOfReturn").datepicker({dateFormat: 'mm-dd-yy'}).val();
    });
</script>