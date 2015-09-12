package presentation;

import business.LegalCustomerBiz;
import exception.SqlException;
import exception.ValidationException;
import model.LegalCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LegalCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String nextJsp;
        System.out.println("request.getSession().getAttribute(\"a\") = " + request.getSession().getAttribute("a"));
        if ("add".equals(operation)) {
            nextJsp = "/jsps/legalcustomer/add-legal-customer.jsp";
        } else if ("save".equals(operation)) {
            try {
                LegalCustomer res = LegalCustomerBiz.getInstance().createAndSaveLegalCustomer(request.getParameter("companyName"),
                        request.getParameter("registrationDate"), request.getParameter("economicCode"));
                request.setAttribute("result", res);
                nextJsp = "/jsps/legalcustomer/show-legal-customer-result.jsp";

            } catch (SqlException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            }
        } else if ("search".equals(operation)) {
            nextJsp = "/jsps/legalcustomer/legal-customer-search-page.jsp";
        } else if ("searchResult".equals(operation)) {
            List<LegalCustomer> legalCustomers = LegalCustomerBiz.getInstance().searchLegalCustomer(request.getParameter("companyName"), request.getParameter("economicCode"), request.getParameter("customerNumber"));
            request.setAttribute("legalCustomers", legalCustomers);
            nextJsp = "/jsps/legalcustomer/show-search-legal-customer.jsp";
        } else if ("update".equals(operation)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                LegalCustomer legalCustomer;

                legalCustomer = LegalCustomerBiz.getInstance().findLegalCustomer(id);
                request.setAttribute("legalCustomer", legalCustomer);
                nextJsp = "/jsps/legalcustomer/legal-customer-update-page.jsp";

            } catch (SqlException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            }
        } else if ("updateSave".equals(operation)) {
            try {
                LegalCustomer legalCustomer = LegalCustomerBiz.getInstance().updateLegal(request.getParameter("companyName"), request.getParameter("economicCode"),
                        request.getParameter("registrationDate"), Integer.parseInt(request.getParameter("id")));
                request.setAttribute("result", legalCustomer);
                nextJsp = "/jsps/legalcustomer/show-legal-customer-result.jsp";
            } catch (SqlException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            }
        } else if ("delete".equals(operation)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                LegalCustomerBiz.getInstance().deleteLegalCustomer(id);
                nextJsp = "/jsps/legalcustomer/legal-customer-delete-page.jsp";

            } catch (SqlException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            }
        } else {
            request.getSession().setAttribute("a", "salam");
            nextJsp = "/jsps/legalcustomer/manage-legal-customer.jsp";
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(nextJsp);
        requestDispatcher.forward(request, response);
    }
}
