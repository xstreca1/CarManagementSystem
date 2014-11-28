<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css" />
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <header>
            <nav>  
                <ul>
                    <li>
                        <a href="index.jsp">
                            HOME
                        </a>
                    </li>
                    <li>
                        <a href="lease/index.jsp">
                            SPRÁVA PŮJČEK
                        </a>
                    </li>
                    <li>
                        <a href="car/index.jsp">
                            SPRÁVA VOZŮ
                        </a>
                    </li>
                    <li>
                        <a href="person/index.jsp">
                            ZAMĚSTNANCI
                        </a>
                    </li>
                    <li>
                        <a href="serviceCheck/index.jsp">
                            SERVIS
                        </a>
                    </li>
                </ul>
            </nav> 
        </header>



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
    </body>
</html>


