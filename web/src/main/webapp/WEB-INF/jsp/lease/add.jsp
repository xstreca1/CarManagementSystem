<%-- 
    Document   : add
    Created on : Nov 23, 2014, 12:00:07 PM
    Author     : martin strecansky
--%>

<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%-- header not implemented yet
<jsp:include page="parts/header.jsp"></jsp:include> --%>
<div class="content">

    <h2 class="pageLabel">
        <spring:message code="lang.leaseParameters"
                        text="Lease parameters" />
    </h2>



    <form:form method="post" id="addLeaseForm" name="addLeaseForm"
               commandName="command" action="add">

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
                <td><form:input path="dateOfReturn"/></td>
                <td><form:errors path="dateOfReturn" cssClass="error"/></td>
            </tr>            
            <tr>
                <th><form:label path="returnedStatus"><fmt:message key="lease.returnedStatus"/></form:label></th>
                <td><form:select path="returnedStatus"/>
                    <form:options items="${returnedStatus}" 
                                  itemLabel="returnedStatus"
                                  itemValue="returnedStatus" /></td>
                <td><form:errors path="returnedStatus" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="travelReason"><fmt:message key="lease.travelReason"/></form:label></th>
                <td><form:select path="travelReason"/>
                    <form:options items="${travelReason}" 
                                  itemLabel="travelReason"
                                  itemValue="travelReason" /></td>
                <td><form:errors path="travelReason" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="2"><button class="button" type="button"
                                        value="Add lease"
                                        <%-- onclick="javascript:validateAndSubmitAddLoanForm()"> --%>
                                        <spring:message code="lang.addLease" text="Add lease" />
                </button></td>
            </tr>
            <tr>
                <td><form:label path="person">
                        <spring:message code="lang.person" text="Person" />
                    </form:label></td>
                <td><form:select path="person">
                    <form:options items="${person}" itemLabel="firstName"
                                  itemValue="id" />
                </form:select></td>

            </tr>
             <tr>
                <td><form:label path="car">
                        <spring:message code="lang.car" text="Car" />
                    </form:label></td>
                <td><form:select path="car">
                    <form:options items="${car}" itemLabel="firstName"
                                  itemValue="id" />
                </form:select></td>

            </tr>

    </table>

</form:form>
</div>
<jsp:include page="parts/footer.jsp"></jsp:include>
