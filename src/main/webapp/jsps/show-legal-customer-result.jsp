<%@ page import="model.LegalCustomer" %>
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
    <div class=headerDiv><h1> Legal Customer Show Information</h1></div>
    <div class="contentDiv">
        <ul class="form-style-1">
            <li>
                <label>Customer Number </label>
                <input type="text" class="field-long" disabled
                       value=<%=((LegalCustomer) request.getAttribute("result")).getCustomerNumber()%>/>
            </li>
            <li>
                <label>Company Name </label>
                <input type="text" class="field-long" disabled
                       value=<%=( (LegalCustomer) request.getAttribute("result")).getCompanyName()%>/>
            </li>
            <li>
                <label>Registration Date </label>
                <input type="text" class="field-long" disabled
                       value=<%=((LegalCustomer) request.getAttribute("result")).getRegistrationDate()%>/>
            </li>
            <li>
                <label>Economic Code </label>
                <input type="text" class="field-long" disabled
                       value=<%=((LegalCustomer) request.getAttribute("result")).getEconomicCode()%>/>
            </li>
        </ul>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="/LegalCustomerServlet">back</a>
    </div>
</div>
</body>
</html>