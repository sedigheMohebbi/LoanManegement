<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Loan Type Manager</title>
    <link href="base.css" rel="stylesheet"/>
    <script src="/js/loan-type.js"></script>
</head>
<body>
<div class="firstDiv">
    <div class="headerDiv"><h1> Add Grant Condition </h1></div>
    <div class="contentDiv">
        <form method="get" name="form1" action="LoanTypeServlet">
            <ul class="form-style-1">
                <li>
                    <label> Name<span class="required">*</span> </label>
                    <input type="text" id="name" class="field-long"/>
                </li>
                <li><label>Contract Duration <span class="required">*</span></label>
                    <input type="text" id="minContractDuration" class="field-divided" placeholder="Min"/>
                    <input type="text" id="maxContractDuration" class="field-divided" placeholder="Max"/></li>
                <li><label>Contract Amount <span class="required">*</span></label>
                    <input type="text" id="minContractAmount" class="field-divided" placeholder="Min"/>
                    <input type="text" id="maxContractAmount" class="field-divided" placeholder="Max"/></li>
                <li>
                    <input type="button" value="add" onclick="addGrant()"/>
                </li>
            </ul>
            <table class="resultTable" id="myTable" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="resultTableHeader"> &nbsp;&nbsp; Name&nbsp;&nbsp;</td>
                    <td class="resultTableHeader"> &nbsp;&nbsp;Min Duration&nbsp;&nbsp;</td>
                    <td class="resultTableHeader"> &nbsp;&nbsp;Max Duration&nbsp;&nbsp;</td>
                    <td class="resultTableHeader"> &nbsp;&nbsp;Min Amount&nbsp;&nbsp;</td>
                    <td class="resultTableHeader"> &nbsp;&nbsp;Max Amount&nbsp;&nbsp;</td>
                </tr>

            </table>
            <ul class="form-style-1">
                <input type="submit" value="save"/>
            </ul>
            <input type="hidden" name="operation" value="save"/>
        </form>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="LoanTypeServlet">back</a>
    </div>
</div>


</body>
</html>