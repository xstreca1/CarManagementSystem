<%-- 
    Document   : index
    Created on : Nov 23, 2014, 2:35:19 PM
    Author     : Petr Potucek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/style.css"/>
<!-- title of the page -->
<fmt:message var="title" key="index.title"/>
<my:layout title="${title}">

    <jsp:attribute name="body">
        <section class="sekce">
            <h1>Sekce</h1>
            <ul>
                <li>
                    <div id="wrapper">
                        <a href="lease/index.jsp">
                            <div class="slideContainer">
                                <img class="slideIcon"  height="" src="img/spravaPujcek.png">
                            </div>
                        </a>
                    </div>
                </li><li>
                    <div id="wrapper">
                        <a href="car/index.jsp">
                            <div class="slideContainer">
                                <img class="slideIcon"  height="" src="img/spravaVozu.png">
                            </div>
                        </a>
                    </div>
                </li>

                <li>
                    <div id="wrapper">
                        <a href="person/index.jsp">
                            <div class="slideContainer">
                                <img class="slideIcon"  height="" src="img/zamestnanci.png">
                            </div>
                        </a>
                    </div>
                </li>
                <li>
                    <div id="wrapper">
                        <a href="serviceCheck/index.jsp">
                            <div class="slideContainer">
                                <img class="slideIcon"  height="" src="img/servis.png">
                            </div>
                        </a>
                    </div>
                </li>
            </ul>
        </section>
    </jsp:attribute>
</my:layout>


