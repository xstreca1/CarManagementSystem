<%-- 
    Document   : loginPage
    Created on : Jan 22, 2015, 1:49:23 PM
    Author     : jrumanov
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title><fmt:message key="index.login"/></title>
    </head>
    <body onload='document.loginForm.j_username.focus();'>
        <h3><fmt:message key="index.login"/></h3>

        <%

            String errorString = (String) request.getAttribute("error");
            if (errorString != null && errorString.trim().equals("true")) {
                out.println("Incorrect login name/password pair. Please retry using correct login name and password.");
            }
        %>

        <form name='loginForm' action="<c:url value='j_spring_security_check' />"
              method='POST'>

            <table>
                <tr>
                    <td><fmt:message key="index.login.username"/></td>
                    <td><input type='text' name='j_username' value=''>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="index.login.password"/></td>
                    <td><input type='password' name='j_password' />
                    </td>
                </tr>
                <tr>
                    <td><input name="submit" type="submit" value=<fmt:message key="index.login.submit"/>
                    </td>
                    <td><input name="reset" type="reset" value=<fmt:message key="index.login.reset"/>
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>