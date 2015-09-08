package presentation;


import business.RealCustomerBiz;
import exception.SqlException;
import exception.ValidationException;
import model.RealCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class RealCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String next;
        if ("add".equals(operation)) {
            next = "/jsps/add-real-customer.jsp";
        } else if ("save".equals(operation)) {
            RealCustomer realCustomer = null;
            try {
                realCustomer = RealCustomerBiz.getInstance().createAndSaveRealCustomer(request.getParameter("firstName"),
                        request.getParameter("lastName"), request.getParameter("nationalCode"), request.getParameter("birthDate"),
                        request.getParameter("fatherName"));
                request.setAttribute("realCustomer", realCustomer);
                next = "/jsps/add-real-customer-page.jsp";

            } catch (SqlException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            }
        } else if ("search".equals(operation)) {
            next = "/jsps/real-customer-search-page.jsp";
        } else if ("searchResult".equals(operation)) {
            List<RealCustomer> realCustomers = RealCustomerBiz.getInstance().searchRealCustomer(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("nationalCode"), request.getParameter("customerNumber"));
            request.setAttribute("realCustomer",realCustomers);
            next = "jsps/show-search-real-customer";
        } else {
            next = "/jsps/manage-real-customer.jsp";
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(next);
        requestDispatcher.forward(request, response);

    }
}
