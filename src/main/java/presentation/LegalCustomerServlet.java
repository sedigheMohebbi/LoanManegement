package presentation;

import exception.SqlException;
import exception.ValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LegalCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String nextJsp;
        if ("add".equals(operation)) {
            nextJsp = "/jsps/add-legal-customer.jsp";
        } else if ("save".equals(operation)) {
            try {
                LegalCustomer res = LegalCustomerBiz.getInstance().createAndSaveLegalCustomer(request.getParameter("companyName"),
                        request.getParameter("registrationDate"), request.getParameter("economicCode"));
                request.setAttribute("result", res);
                nextJsp = "/jsps/show-legal-customer.jsp";

            } catch (SqlException e) {
                request.setAttribute("error",e);
                nextJsp="/jsps/error-page.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                nextJsp="/jsps/error-page.jsp";
            }


    } else {
            nextJsp = "/jsps/manage-legal-customer.jsp";
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(nextJsp);
        requestDispatcher.forward(request, response);


    }
}
