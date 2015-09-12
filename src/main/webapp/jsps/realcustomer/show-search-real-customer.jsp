<%@ page import="model.RealCustomer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Real Customer Manager</title>
    <link href="base.css" rel="stylesheet"/>
</head>
<body>
<div class=firstDiv>
    <div class=headerDiv><h1> show information</h1></div>
    <div class="contentDiv">
        <table class="resultTable" cellpadding="0" cellspacing="0">
            <tr>
                <td class="resultTableHeader"> &nbsp;&nbsp;First Name&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Last Name&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Father Name&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp; Birth Date&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;National Code&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Customer Number&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Update&nbsp;&nbsp;</td>
                <td class="resultTableHeader"> &nbsp;&nbsp;Delete&nbsp;&nbsp;</td>
            </tr>
            <% List<RealCustomer> realCustomers;
                realCustomers = (List<RealCustomer>) request.getAttribute("realCustomer");%>
            <% for (RealCustomer realCustomer : realCustomers) {%>
            <tr>
                <td><%= realCustomer.getFirstName() %>
                </td>
                <td><%= realCustomer.getLastName() %>
                </td>
                <td><%= realCustomer.getFatherName() %>
                </td>
                <td><%= realCustomer.getBirthDate()  %>
                </td>
                <td><%= realCustomer.getNationalCode() %>
                </td>
                <td><%= realCustomer.getCustomerNumber() %>
                </td>
                <td>
                    <form method="get" action="RealCustomerServlet">
                        <input type="submit" class="inputSubmit" value="update"/>
                        <input type="hidden" name="operation" value="update"/>
                        <input type="hidden" name="id"
                               value="<%=realCustomer.getId()%>"/>
                    </form>
                </td>
                <td>
                    <form method="get" action="RealCustomerServlet">
                        <input type="submit" class="inputSubmit" value="delete"/>
                        <input type="hidden" name="operation" value="delete"/>
                        <input type="hidden" name="id"
                               value="<%=realCustomer.getId()%>"/>
                    </form>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
    <div class="backDiv">
        <a href="index.jsp">home</a>
        <a href="RealCustomerServlet">back</a>
    </div>
</div>
</body>
</html>