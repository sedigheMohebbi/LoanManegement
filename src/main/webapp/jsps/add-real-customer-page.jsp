<%@ page import="model.RealCustomer" %>
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
    <div class=headerDiv><h1> Real Customer Show Information</h1></div>
    <div class="contentDiv">
        <ul class="form-style-1">
            <li>
                <label>Customer Number </label>
                <input type="text" class="field-long" disabled
                       value="<%= ((RealCustomer)request.getAttribute("realCustomer")).getCustomerNumber()%>"/>
            </li>
            <li>
                <label>First Name </label>
                <input type="text" class="field-long" disabled
                       value="<%=((RealCustomer)request.getAttribute("realCustomer")).getFirstName()%>"/>
            </li>
            <li>
                <label>Last Name </label>
                <input type="text" class="field-long" disabled
                       value="<%=((RealCustomer)request.getAttribute("realCustomer")).getLastName()%>"/>
            </li>
            <li>
                <label>Father Name </label>
                <input type="text" class="field-long" disabled
                       value="<%=((RealCustomer)request.getAttribute("realCustomer")).getFatherName()%>"/>
            </li>
            <li>
                <label>Birth Date </label>
                <input type="text" class="field-long" disabled
                       value="<%=((RealCustomer)request.getAttribute("realCustomer")).getBirthDate()%>"/>
            </li>
            <li>
                <label>national Code </label>
                <input type="text" class="field-long" disabled
                       value="<%=((RealCustomer)request.getAttribute("realCustomer")).getLastName()%>"/>
            </li>
        </ul>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="RealCustomerServlet">back</a>
    </div>
</div>
</body>
</html>