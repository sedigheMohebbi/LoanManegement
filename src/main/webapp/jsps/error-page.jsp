<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Customer Manager</title>
    <link href="base.css" rel="stylesheet"/>
</head>
<body>
<div class=firstDiv>
    <div class="headerDiv"><h1> Error Page</h1></div>
    <div class="contentDiv">
        <p id="massage"><%= ((Exception) request.getAttribute("error")).getMessage()%>   </p>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="LegalCustomerServlet">back</a>
    </div>
</div>
</body>
</html>
