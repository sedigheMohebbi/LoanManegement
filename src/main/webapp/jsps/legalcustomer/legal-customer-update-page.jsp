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
    <div class=headerDiv><h1> Legal Customer Update</h1></div>
    <div class="contentDiv">
        <form method="get" action="LegalCustomerServlet">
            <ul class="form-style-1">
                <li>
                    <label>Company Name </label>
                    <input type="text" name="companyName"
                           value="<%=((LegalCustomer)request.getAttribute("legalCustomer")).getCompanyName()%>"
                           class="field-long"/>

                </li>
                <li>
                    <label>Registration Date </label>
                    <input type="text" name="registrationDate"
                           value="<%=((LegalCustomer)request.getAttribute("legalCustomer")).getRegistrationDate()%>"
                           class="field-long"/>
                </li>
                <li>
                    <label>Economic Code </label>
                    <input type="text" name="economicCode"
                           value="<%=((LegalCustomer)request.getAttribute("legalCustomer")).getEconomicCode()%>"
                           class="field-long"/>
                </li>
                <li>
                    <input type="submit" value="update"/>
                </li>
                <input type="hidden" name="operation" value="updateSave"/>
                <input type="hidden" name="id"
                       value="<%=((LegalCustomer)request.getAttribute("legalCustomer")).getId()%>"/>
            </ul>
        </form>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="LegalCustomerServlet">back</a>
    </div>
</div>
</body>
</html>