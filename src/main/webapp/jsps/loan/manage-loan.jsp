<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loan Manager</title>
    <link href="./base.css" rel="stylesheet"/>
</head>
<body>
<div class="firstDiv">
    <div>
        <div class="headerDiv"><h1> Loan manager</h1></div>
        <div class="selectFrame contentDiv">

            <form method="get" action="/LoanServlet">
                <input type="submit" value="Create File" class="selectButton"/>
                <input type="hidden" name="operation" value="add"/>
            </form>

        </div>
        <div class="backDiv">
            <a href="/index.jsp">home</a>
        </div>
    </div>
</div>
</body>
</html>

