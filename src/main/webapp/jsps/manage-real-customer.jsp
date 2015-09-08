<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Manager</title>
    <link href="/base.css" rel="stylesheet"/>
</head>
<body>
<div class="firstDiv">
    <div>
        <div class="headerDiv"><h1> customer manager</h1></div>
        <div class="selectFrame contentDiv">

            <form method="get" action="/RealCustomerServlet">
                <input type="submit" value="add" class="selectButton"/>
                <input type="hidden" name="operation" value="add"/>
            </form>
            <form method="get" action="/RealCustomerServlet">
                <input type="submit" value="search" class="selectButton"/>
                <input type="hidden" name="operation" value="search"/>
            </form>
        </div>
        <div class="backDiv">
            <a href="/index.jsp">home</a>
        </div>
    </div>
</div>
</body>
</html>


