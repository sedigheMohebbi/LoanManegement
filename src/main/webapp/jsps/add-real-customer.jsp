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
    <div class="headerDiv"><h1> Add Real Customer</h1></div>
    <div class="contentDiv">
        <form method="get" name="form1" action="RealCustomerServlet">
            <ul class="form-style-1">
                <li>
                    <label>First Name </label>
                    <input type="text" name="firstName" class="field-long"/>
                </li>
                <li>
                    <label>Last Name </label>
                    <input type="text" name="lastName" class="field-long"/>
                </li>
                <li>
                    <label>Father Name </label>
                    <input type="text" name="fatherName" class="field-long"/>
                </li>
                <li>
                    <label>Birth Date </label>
                    <input type="text" name="birthDate" class="field-long"/>
                </li>
                <li>
                    <label>national Code </label>
                    <input type="text" name="nationalCode" class="field-long" id="nationalCode"/>
                </li>
                <li>
                    <input type="button" value="save" onclick="realCustomerValidation(document.getElementById('nationalCode').value)"/>
                </li>
            </ul>
            <input type="hidden" name="operation" value="save"/>
        </form>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="RealCustomerServlet">back</a>
    </div>
</div>
<script src="/js/real-customer.js"></script>

</body>
</html>