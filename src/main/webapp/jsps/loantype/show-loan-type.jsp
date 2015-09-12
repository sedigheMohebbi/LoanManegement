<%@ page import="model.LegalCustomer" %>
<%@ page import="java.util.List" %>
<%@ page import="model.LoanType" %>
<%@ page import="model.GrantCondition" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Customer Manager</title>
    <link href="/base.css" rel="stylesheet"/>
</head>
<body>
<div class=firstDiv>
    <div class=headerDiv><h1> show information</h1></div>
    <div class="contentDiv">
        <ul class="form-style-1">
            <li>
                <label>Customer Number </label>
                <input type="text" class="field-long" disabled
                       value="<%= ((LoanType)request.getAttribute("loanType")).getName()%>"/>
            </li>
            <li>
                <label>Customer Number </label>
                <input type="text" class="field-long" disabled
                       value="<%= ((LoanType)request.getAttribute("loanType")).getInterestRate()%>"/>
            </li>
        </ul>

        <table class="resultTable" cellpadding="0" cellspacing="0">
            <tr>
                <td class="resultTableHeader"> &nbsp;&nbsp;Name&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Min Contract Duration&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Max Contract Duration&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Min Contract Amount&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Max Contract Amount&nbsp;&nbsp;</td>
            </tr>
            <% LoanType loanType;
                loanType = (LoanType) request.getAttribute("loanType");%>
            <% for (GrantCondition grantCondition : loanType.getGrantConditions()) {%>
            <tr>
                <td><%=grantCondition.getName()%>
                </td>
                <td><%=grantCondition.getMinContractDuration()%>
                </td>
                <td><%= grantCondition.getMaxContractDuration() %>
                </td>
                <td><%= grantCondition.getMaxContractDuration() %>
                </td>
                <td><%= grantCondition.getMaxContractAmount() %>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="LoanTypeServlet">back</a>
    </div>
</div>
</body>
</html>