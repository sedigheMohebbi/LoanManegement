<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Customer Manager</title>
    <link href="base.css" rel="stylesheet"/>
</head>
<body>
<div class="firstDiv">
    <div class="headerDiv"><h1> Add Legal Customer</h1></div>
    <div class="contentDiv">
        <form method="get" action="LegalCustomerServlet">

            <ul class="form-style-1">
                <li>
                    <label>Company Name </label>
                    <input type="text" name="companyName" class="field-long"/>

                </li>
                <li>
                    <label>Registration Date </label>
                    <input type="text" name="registrationDate" class="field-long"/>
                </li>
                <li>
                    <label>Economic Code </label>
                    <input type="text" name="economicCode" class="field-long"/>

                </li>
                <li>
                    <input type="submit" value="save"/>
                </li>
            </ul>
            <input type="hidden" name="operation" value="save"/>
        </form>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="LegalCustomerServlet">back</a>
    </div>
</div>
</body>
</html>
