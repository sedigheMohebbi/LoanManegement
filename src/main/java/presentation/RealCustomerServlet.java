package presentation;


import business.LegalCustomerBiz;
import business.RealCustomerBiz;
import exception.SqlException;
import exception.ValidationException;
import model.LegalCustomer;
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
            request.setAttribute("realCustomer", realCustomers);
            next = "/jsps/show-search-real-customer.jsp";
        } else if ("update".equals(operation)) {
            int id = Integer.parseInt(request.getParameter("id"));
            RealCustomer realCustomer = null;
            try {
                realCustomer = RealCustomerBiz.getInstance().findRealCustomer(id);
                request.setAttribute("realCustomer", realCustomer);
                next = "/jsps/real-customer-update-page.jsp";
            } catch (SqlException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            }
        } else if ("updateSave".equals(operation)) {
            try {
                RealCustomer realCustomer = RealCustomerBiz.getInstance().updateRealCustomer(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("fatherName"), request.getParameter("nationalCode"),
                        request.getParameter("birthDate"), Integer.parseInt(request.getParameter("id")));
                request.setAttribute("realCustomer", realCustomer);
                next = "/jsps/add-real-customer-page.jsp";
            } catch (SqlException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            }
        } else if ("delete".equals(operation)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                RealCustomerBiz.getInstance().deleteRealCustomer(id);
                next = "/jsps/real-customer-delete-page.jsp";
            } catch (SqlException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            }
        } else {
            next = "/jsps/manage-real-customer.jsp";
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(next);
        requestDispatcher.forward(request, response);

    }
}
