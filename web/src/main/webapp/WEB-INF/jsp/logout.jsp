<%-- 
    Document   : logout
    Created on : Jan 24, 2015, 8:13:34 PM
    Author     : jozefpuchly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

User '<%=request.getRemoteUser()%>' has been logged out.

<% session.invalidate(); %>