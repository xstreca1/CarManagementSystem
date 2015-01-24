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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <header>
            <c:choose>
                <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                    <nav>  
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/main">
                                    <fmt:message key="layout.menu.home"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/lease/">
                                    <fmt:message key="layout.menu.lease"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/car/">
                                    <fmt:message key="layout.menu.car"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/person/">
                                    <fmt:message key="layout.menu.person"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/serviceCheck/">
                                    <fmt:message key="layout.menu.serviceCheck"/>
                                </a>
                            </li>                            
                            <li>
                                <a href="${pageContext.request.contextPath}/stats/">
                                    <fmt:message key="layout.menu.statistics"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/logout/">
                                    <fmt:message key="layout.menu.logout"/>
                                </a>
                            </li>                          
                        </ul>                       
                    </nav> 
                </c:when >
                <c:otherwise>
                    <nav>  
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/main">
                                    <fmt:message key="layout.menu.home"/>
                                </a>
                            </li>                   
                            <li>
                                <a href="${pageContext.request.contextPath}/stats/">
                                    <fmt:message key="layout.menu.statistics"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/logout/">
                                    <fmt:message key="layout.menu.logout"/>
                                </a>
                            </li>                                                        
                        </ul>                        
                    </nav> 
                </c:otherwise>
            </c:choose>
        </header>

        <h1><c:out value="${title}"/></h1>

        <div id="content">
            <c:if test="${not empty message}">
                <div class="message"><c:out value="${message}"/></div>
            </c:if>
            <jsp:invoke fragment="body"/>
        </div>
    </body>
</html>