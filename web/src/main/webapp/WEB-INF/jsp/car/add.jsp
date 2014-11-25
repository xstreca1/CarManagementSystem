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
        <spring:message code="lang.carParameters"
                        text="Car parameters" />
    </h2>



    <form:form method="post" id="addCarForm" name="addCarForm"
               commandName="command" action="add">

        <table>
            <tr>
                <th><form:label path="mileage"><fmt:message key="car.mileage"/></form:label></th>
                <td><form:input path="mileage"/></td>
                <td><form:errors path="mileage" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="availibility"><fmt:message key="car.availibility"/></form:label></th>
                <td><form:input path="availibility"/></td>
                <td><form:errors path="availibility" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="vehicleRegPlate"><fmt:message key="car.vehicleRegPlate"/></form:label></th>
                <td><form:input path="vehicleRegPlate"/></td>
                <td><form:errors path="vehicleRegPlate" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="yearOfManufacture"><fmt:message key="car.yearOfManufacture"/></form:label></th>
                <td><form:input path="yearOfManufacture"/></td>
                <td><form:errors path="yearOfManufacture" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="brand"><fmt:message key="car.brand"/></form:label></th>
                <td><form:input path="brand"/></td>
                <td><form:errors path="brand" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="typeName"><fmt:message key="car.typeName"/></form:label></th>
                <td><form:input path="typeName"/></td>
                <td><form:errors path="typeName" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="enginePower"><fmt:message key="car.enginePower"/></form:label></th>
                <td><form:input path="enginePower"/></td>
                <td><form:errors path="enginePower" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="gasConsumption"><fmt:message key="car.gasConsumption"/></form:label></th>
                <td><form:input path="gasConsumption"/></td>
                <td><form:errors path="gasConsumption" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="transmission"><fmt:message key="car.transmission"/></form:label></th>
                <td><form:input path="transmission"/></td>
                <td><form:errors path="transmission" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="VIN"><fmt:message key="car.VIN"/></form:label></th>
                <td><form:input path="VIN"/></td>
                <td><form:errors path="VIN" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="numberOfSeats"><fmt:message key="car.numberOfSeats"/></form:label></th>
                <td><form:input path="numberOfSeats"/></td>
                <td><form:errors path="numberOfSeats" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="color"><fmt:message key="car.color"/></form:label></th>
                <td><form:select path="color"/>
                    <form:options items="${colors}" 
                                  itemLabel="colors"
                                  itemValue="color" /></td>
                <td><form:errors path="color" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="category"><fmt:message key="car.category"/></form:label></th>
                <td><form:select path="category"/>
                    <form:options items="${categorys}" 
                                  itemLabel="categorys"
                                  itemValue="category" /></td>
                <td><form:errors path="category" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="bodystyle"><fmt:message key="car.bodystyle"/></form:label></th>
                <td><form:select path="bodystyle"/>
                    <form:options items="${bodystyles}" 
                                  itemLabel="bodystyles"
                                  itemValue="bodystyle" /></td>
                <td><form:errors path="bodystyle" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="emissionstandard"><fmt:message key="car.emissionstandard"/></form:label></th>
                <td><form:select path="emissionstandard"/>
                    <form:options items="${emissionstandards}" 
                                  itemLabel="emissionstandards"
                                  itemValue="emissionstandard" /></td>
                <td><form:errors path="emissionstandard" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="2"><button class="button" type="button"
                                        value="Add car"
                                        <%-- onclick="javascript:validateAndSubmitAddLoanForm()"> --%>
                        <spring:message code="lang.addCar" text="Add car" />
                    </button></td>
            </tr>

        </table>

    </form:form>
   </div>
<jsp:include page="parts/footer.jsp"></jsp:include>
