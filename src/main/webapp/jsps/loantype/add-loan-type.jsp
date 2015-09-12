<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>Add loan Type</title>
  <link href="base.css" rel="stylesheet"/>
  <script src="/js/loan-type.js"></script>
</head>
<body>
<div class="firstDiv">
  <div class="headerDiv"><h1> Add Loan Type</h1></div>
  <div class="contentDiv">
    <form method="get" action="LoanTypeServlet">

      <ul class="form-style-1">
        <li>
          <label>Loan Type Name </label>
          <input type="text" name="loanTypeName" id="loanTypeName"  class="field-long"/>

        </li>
        <li>
          <label>Interest Rate </label>
          <input type="text" name="interestRate" id="interestRate" class="field-long"/>
        </li>
        <li>
          <input type="button" value="next" onclick="addLoanType()"/>
        </li>
      </ul>
      <input type="hidden" name="operation" value="next"/>
    </form>
  </div>
  <div class="backDiv">
    <a href="index.jsp">home</a>
    <a href="LoanTypeServlet">back</a>
  </div>
</div>
</body>
</html>
