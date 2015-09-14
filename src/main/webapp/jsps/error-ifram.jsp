<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link href="base.css" rel="stylesheet"/>
</head>
<body class="no-background-body">

    <div class="contentDiv">
        <p id="massage"><%= ((Exception) request.getAttribute("error1")).getMessage()%>   </p>
    </div>
</body>
</html>
