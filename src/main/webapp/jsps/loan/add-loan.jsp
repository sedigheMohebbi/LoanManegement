<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Add loan </title>
    <link href="base.css" rel="stylesheet"/>
</head>
<body>
<div class="firstDiv">
    <div class="headerDiv"><h1> Add Loan</h1></div>
    <div class="contentDiv">
        <form method="get" action="LoanServlet" target="my">

            <ul class="form-style-1">
                <li>
                    <label>Customer Number </label>
                    <input type="text" name="customerNumber"    class="field-divided"/>
                    <input type="submit" value="retrieval"  />
                </li>
            </ul>
            <iframe name="my" src=""></iframe>
            <input type="hidden" name="operation" value="next"/>
        </form>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="LoanServlet">back</a>
    </div>
</div>
</body>
</html>
