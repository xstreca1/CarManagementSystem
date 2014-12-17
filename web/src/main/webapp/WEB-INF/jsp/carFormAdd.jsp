<%-- 
    Document   : formAdd
    Created on : Nov 25, 2014, 3:59:42 PM
    Author     : Mato
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table>
    <tr>
        <th><form:label path="vehicleRegPlate"><fmt:message key="car.vehicleRegPlate"/></form:label></th>
        <td><form:input path="vehicleRegPlate"/></td>
        <td><font color="red"> <form:errors path="vehicleRegPlate"></form:errors></font></td>
        </tr>
        <tr>
            <th><form:label path="typeName"><fmt:message key="car.typeName"/></form:label></th>
        <td><form:input path="typeName"/></td>
        <td><font color="red"> <form:errors path="typeName"></form:errors></font></td>
    </tr>
    <tr>
        <th><form:label path="brand"><fmt:message key="car.brand"/></form:label></th>
        <td><form:input path="brand"/></td>
        <td><font color="red"> <form:errors path="brand"></form:errors></font></td>
    </tr>
    <tr>
        <th><form:label path="yearOfManufacture"><fmt:message key="car.yearOfManufacture"/></form:label></th>
        <td><form:input path="yearOfManufacture" maxlength="4"/></td>
        <td><font color="red"> <form:errors path="yearOfManufacture"></form:errors></font></td>
    </tr>

    <%-- <tr>
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
        <td><form:checkbox path="transmission"/></td>
        <td><form:errors path="transmission" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="VIN"><fmt:message key="car.VIN"/></form:label></th>
        <td><form:input path="VIN"/></td>
        <td><form:errors path="VIN" cssClass="error"/></td>
    </tr>--%>
    <tr>
        <th><form:label path="bodystyle"><fmt:message key="car.bodystyle"/></form:label></th>
        <td><form:select path="bodystyle">                
                <form:options items="${enumValues}" />
            </form:select>
        </td>
        <td><form:errors path="bodystyle" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="numberOfSeats"><fmt:message key="car.numberOfSeats"/></form:label></th>
        <td><form:input path="numberOfSeats"/></td>
        <td><font color="red"> <form:errors path="numberOfSeats"></form:errors></font></td>
    </tr>
    <tr>
        <th><form:label path="mileage"><fmt:message key="car.mileage"/></form:label></th>
        <td><form:input path="mileage"/></td>
        <td><font color="red"> <form:errors path="mileage"></form:errors></font></td>
    </tr>   
    <tr>
        <th><form:label path="color"><fmt:message key="car.color"/></form:label></th>
        <td><form:select path="color">                
                <form:options items="${enumValues}" />
            </form:select></td>
        <td><form:errors path="color" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="category"><fmt:message key="car.category"/></form:label></th>
        <td><form:select path="category">               
                <form:options items="${enumValues}" />
            </form:select>
        </td>
        <td><form:errors path="category" cssClass="error"/></td>
    </tr>

    <tr>
        <th><form:label path="emissionstandard"><fmt:message key="car.emissionstandard"/></form:label></th>
        <td><form:select path="emissionstandard">                
                <form:options items="${enumValues}" />
            </form:select></td>

        <td><form:errors path="emissionstandard" cssClass="error"/></td>
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
