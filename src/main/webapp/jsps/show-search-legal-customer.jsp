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
    <div class=headerDiv><h1> show information</h1></div>
    <div class="contentDiv">
        <table class="resultTable" cellpadding="0" cellspacing="0">
            <tr>
                <td class="resultTableHeader"> &nbsp;&nbsp;Company Name&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Economic Code&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Customer Number&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Registration Date&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Update&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Delete&nbsp;&nbsp;</td>
            </tr>
           <% for (LegalCustomer legalCustomer : legalCustomers) {%>
          <%  out.println( %>
            <tr>
                <td> legalCustomer.getCompanyName()</td>
                <td>" + legalCustomer.getEconomicCode() + "</td>
                <td>" + legalCustomer.getCustomerNumber() + "</td>
                <td>" + legalCustomer.getRegistrationDate() + "</td>
                <td>
                    <form method="get" action="LegalCustomerServlet">
                        <input type="submit" class="inputSubmit" value="update"/>
                        <input type="hidden" name="operation" value="update"/>
                        <input type="hidden" name="id" value="<%= ((LegalCustomer)request.getAttribute("legalcustomer")).getId()%>" />
                    </form>
                </td>
                <td>
                    <form method="get" action="LegalCustomerServlet">
                        <input type="submit" class="inputSubmit" value="delete">
                        <input type="hidden" name="operation" value="delete"/>
                        <input type="hidden" name="id" value="<%= ((LegalCustomer)request.getAttribute("legalcustomer")).getId()%>" />
                    </form>
                </td>
            </tr>
            }

        </table>
    </div>
    <div class=\"backDiv\">
        <a href=\"/index.html\">home</a>
        <a href=\"/LegalCustomerServlet\">back</a>
    </div>
</div>
</body>
</html>