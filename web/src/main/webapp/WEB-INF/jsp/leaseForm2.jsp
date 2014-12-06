<%-- 
    Document   : leaseForm2
    Created on : Dec 6, 2014, 3:22:58 PM
    Author     : Mato
--%>


<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

    <head>

        <title>Registration Page</title>

    </head>

    <body>



        <form:form method="POST" commandName="user">

            <table>

                <tr>

                    <td><fmt:message key="lease.distance"/> :</td>
                    <td><form:input path="distance"/></td>

                    

                </tr>

                <tr>

                    <td><fmt:message key="lease.dateOfLease"/></td>

                    

                </tr>

                <tr>

                    <td><fmt:message key="lease.dateOfReturn"/></td>
                    


                </tr>

            </table>

        </form:form>


    </body>

</html>
