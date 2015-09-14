<%@ page import="model.Loan" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Loan</title>
    <link href="base.css" rel="stylesheet"/>
</head>
<body>
<div class=firstDiv>
    <div class=headerDiv><h1> Loan Show Information</h1></div>
    <div class="contentDiv">
        <ul class="form-style-1">
            <li>
                <label>Customer Number </label>
                <input type="text" class="field-long" disabled
                       value="<%= ((Loan)(request.getAttribute("loan"))).getRealCustomer().getCustomerNumber()%>"/>
            </li>
            <li>
                <label>First Name </label>
                <input type="text" class="field-long" disabled
                       value="<%=((Loan)(request.getAttribute("loan"))).getRealCustomer().getFirstName()%>"/>
            </li>
            <li>
                <label>Last Name </label>
                <input type="text" class="field-long" disabled
                       value="<%=((Loan)request.getAttribute("loan")).getRealCustomer().getLastName()%>"/>
            </li>
            <li>
                <label>Loan Type Name </label>
                <input type="text" class="field-long" disabled
                       value="<%=((Loan)request.getAttribute("loan")).getLoanType().getName()%>"/>
            </li>

        </ul>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="LoanServlet">back</a>
    </div>
</div>
</body>
</html>