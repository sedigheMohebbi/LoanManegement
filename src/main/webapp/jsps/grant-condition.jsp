<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Loan Type Manager</title>
    <link href="base.css" rel="stylesheet"/>

</head>
<body>
<div class="firstDiv">
    <div class="headerDiv"><h1> Add Grant Condition </h1></div>
    <div class="contentDiv">
        <form method="get" name="form1" action="LoanTypeServlet">
            <ul class="form-style-1">
                <li>
                    <label> Name </label>
                    <input type="text" name="name" class="field-long"/>
                </li>
                <li>
                    <label> Min Contract Duration </label>
                    <input type="text" name="minContractDuration" class="field-long"/>
                </li>
                <li>
                    <label>Max Contract Duration </label>
                    <input type="text" name="maxContractDuration" class="field-long"/>
                </li>
                <li>
                    <label>Min Contract Amount </label>
                    <input type="text" name="minContractAmount" class="field-long"/>
                </li>
                <li>
                    <label>Max Contract Amount </label>
                    <input type="text" name="maxContractAmount" class="field-long" id="nationalCode"/>
                </li>
                <li>
                    <input type="submit" value="save"/>
                </li>
                <li>
                    <input type="submit" value="next"/>
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