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

        <section id="login-space">
            <form name='loginForm' action="<c:url value='j_spring_security_check' />"
                  method='POST'>

                <table>
                    <tr>
                        <td id="greyBack"><fmt:message key="index.login.username"/></td>
                    </tr>
                    <tr>
                        <td><input type='text' name='j_username' value=''>
                        </td>
                    </tr>
                    <tr>
                        <td id="greyBack"><fmt:message key="index.login.password"/></td>
                    </tr>
                    <tr>
                        <td><input type='password' name='j_password' />
                        </td>
                    </tr>
                    <tr>
                        <td id="greyBack"><input name="submit" type="submit" value=<fmt:message key="index.login.submit"/>
                        </td>
                    </tr>
                    <tr>
                        <td><input name="reset" type="reset" value=<fmt:message key="index.login.reset"/>
                        </td>
                    </tr>
                </table>

            </form>
        </section>
    </body>
</html>