<%-- 
    Document   : layout
    Created on : Nov 23, 2014, 2:35:19 PM
    Author     : jrumanov
--%>

<!DOCTYPE html>
<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/style.css"/>
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <h1><c:out value="${title}"/></h1>

        <header>
            <nav>  
                <ul>
                    <li>
                        <a href="index.jsp">
                            <fmt:message key="layout.menu.home"/>
                        </a>
                    </li>
                    <li>
                        <a href="lease/index.jsp">
                            <fmt:message key="layout.menu.lease"/>
                        </a>
                    </li>
                    <li>
                        <a href="car/index.jsp">
                            <fmt:message key="layout.menu.car"/>
                        </a>
                    </li>
                    <li>
                        <a href="person/index.jsp">
                            <fmt:message key="layout.menu.person"/>
                        </a>
                    </li>
                    <li>
                        <a href="serviceCheck/index.jsp">
                            <fmt:message key="layout.menu.serviceCheck"/>
                        </a>
                    </li>
                </ul>
            </nav> 
        </header>

        <div id="content">
            <c:if test="${not empty message}">
                <div class="message"><c:out value="${message}"/></div>
            </c:if>
            <jsp:invoke fragment="body"/>
        </div>
    </body>
</html>