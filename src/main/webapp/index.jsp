<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="content-type" charset="UTF-8">
    <title>Customer Manager</title>
    <link href="base.css" rel="stylesheet"/>

</head>
<body>

<div class="firstDiv">
    <div class="headerDiv"><h1> select customer Type</h1></div>
    <div class="selectFrame">
        <form method="get" action="LegalCustomerServlet">

            <input type="submit" name="legalCustomer" value="LegalCustomer" class="selectButton"/>

        </form>
        <form method="get" action="RealCustomerServlet">
            <input type="submit" name="realCustomer" value="RealCustomer" class="selectButton"/>
        </form>
        <form method="get" action="LoanTypeServlet">
            <input type="submit" name="loanType" value="LoanType" class="selectButton"/>
        </form>
    </div>

</div>
</body>
</html>