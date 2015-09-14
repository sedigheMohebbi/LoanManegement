<%@ page import="model.LoanType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Add loan </title>
    <link href="base.css" rel="stylesheet"/>
    <script src="/js/loan.js"></script>
</head>
<body>
<div class="firstDiv">
    <div class="headerDiv"><h1> Add Loan</h1></div>
    <div class="contentDiv">


        <ul class="form-style-1">
            <li>
                <form method="get" action="LoanServlet" target="my">
                    <label>Customer Number </label>
                    <input type="text" id="customerNumber" name="customerNumber" class="field-divided"/>
                    <input type="submit" value="retrieval"/>
                    <input type="hidden" name="operation" value="retrieval"/>
                </form>
            </li>
        </ul>
        <div class="iframe-div">
            <iframe name="my"></iframe>
        </div>
        <form method="get" name="form1" action="LoanServlet">

        <div class="select">
            <ul class="form-style-1">
                <li>
                    <label>Loan Type</label>
                    <select class="field-select" name="loanTypeId">
                        <% for (LoanType loanType : (List<LoanType>) request.getAttribute("loanTypes")) { %>
                        <option value="<%=loanType.getId()%>"><%= loanType.getName() %>
                        </option>
                        <%}%>
                    </select>
                <li>
                    <label>Duration of Contract </label>
                    <input type="text" name="durationOfContract" id="durationOfContract" class="field-long"/>
                </li>
                <li>
                    <label>Amount of Contract </label>
                    <input type="text" name="amountOfContract" id="amountOfContract" class="field-long"/>
                </li>
                <li>
                    <input type="button" value="save" onclick="myfunc()"/>

                </li>
                <input type="hidden"  name="operation" value="save" />
                <input type="hidden"  name="customerNumber" id="customerNumberHidden"/>


            </ul>

        </div>
      </form >
    </div>

    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="LoanServlet">back</a>
    </div>
</div>
</body>
</html>
