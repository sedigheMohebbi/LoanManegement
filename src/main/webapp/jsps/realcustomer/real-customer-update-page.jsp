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
    <div class=headerDiv><h1> Real Customer Update</h1></div>
    <div class="contentDiv">
        <form method="get" action="/RealCustomerServlet">
            <ul class="form-style-1">
                <li>
                    <label>First Name </label>
                    <input type="text" name="firstName"
                           value="<%=((RealCustomer) request.getAttribute("realCustomer")).getFirstName() %>"
                           class="field-long"/>
                </li>
                <li>
                    <label>Last Name </label>
                    <input type="text" name="lastName"
                           value="<%= ((RealCustomer) request.getAttribute("realCustomer")).getLastName()%>"
                           class="field-long"/>
                </li>
                <li>
                    <label>Father Name </label>
                    <input type="text" name="fatherName"
                           value="<%=((RealCustomer)request.getAttribute("realCustomer")).getFatherName() %>"
                           class="field-long"/>
                </li>
                <li>
                    <label>Birth Date </label>
                    <input type="text" name="birthDate"
                           value="<%= ((RealCustomer)request.getAttribute("realCustomer")).getBirthDate() %>"
                           class="field-long"/>
                </li>
                <li>
                    <label>National Code </label>
                    <input type="text" name="nationalCode"
                           value="<%= ((RealCustomer)request.getAttribute("realCustomer")).getNationalCode()%>"
                           class="field-long"/>
                </li>
                <li>
                    <input type="submit" value="update"/>
                </li>
                <input type="hidden" name="operation" value="updateSave"/>
                <input type="hidden" name="id"
                       value="<%= ((RealCustomer) request.getAttribute("realCustomer")).getId()%>"/>
            </ul>
        </form>
    </div>
    <div class="backDiv">
        <a href="/index.jsp">home</a>
        <a href="/RealCustomerServlet">back</a>
    </div>
</div>
</body>
</html>
