<%-- 
    Document   : index for person
    Created on : Nov 28, 2014, 5:07:52 PM
    Author     : jrumanov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add or list all employees</title>
    </head>
    <body>
        <a href="/person/add">Add a new car</a>
        <a href="/person/listPeople?isInactive=false">list all active cars</a>
        <a href="/person/listPeople?isInactive=true">list all cars</a>
    </body>
</html>
