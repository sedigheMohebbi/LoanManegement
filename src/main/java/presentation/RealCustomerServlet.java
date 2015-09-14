package presentation;


import business.RealCustomerBiz;
import exception.SqlException;
import exception.ValidationException;
import model.RealCustomer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class RealCustomerServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(RealCustomer.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String next;
        if ("add".equals(operation)) {
            next = "/jsps/realcustomer/add-real-customer.jsp";
            logger.debug("Forward to " + next);

        } else if ("save".equals(operation)) {
            RealCustomer realCustomer = null;
            try {
                realCustomer = RealCustomerBiz.getInstance().createAndSaveRealCustomer(request.getParameter("firstName"),
                        request.getParameter("lastName"), request.getParameter("nationalCode"), request.getParameter("birthDate"),
                        request.getParameter("fatherName"));
                request.setAttribute("realCustomer", realCustomer);
                next = "/jsps/realcustomer/add-real-customer-page.jsp";
                logger.debug("Forward to " + next);


            } catch (SqlException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            }
        } else if ("search".equals(operation)) {
            next = "/jsps/realcustomer/real-customer-search-page.jsp";
            logger.debug("Forward to " + next);

        } else if ("searchResult".equals(operation)) {
            List<RealCustomer> realCustomers = RealCustomerBiz.getInstance().searchRealCustomer(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("nationalCode"), request.getParameter("customerNumber"));
            request.setAttribute("realCustomer", realCustomers);
            next = "/jsps/realcustomer/show-search-real-customer.jsp";
            logger.debug("Forward to " + next);

        } else if ("update".equals(operation)) {
            int id = Integer.parseInt(request.getParameter("id"));
            RealCustomer realCustomer = null;
            try {
                realCustomer = RealCustomerBiz.getInstance().findRealCustomer(id);
                request.setAttribute("realCustomer", realCustomer);
                next = "/jsps/realcustomer/real-customer-update-page.jsp";
                logger.debug("Forward to " + next);

            } catch (SqlException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            }
        } else if ("updateSave".equals(operation)) {
            try {
                RealCustomer realCustomer = RealCustomerBiz.getInstance().updateRealCustomer(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("fatherName"), request.getParameter("nationalCode"),
                        request.getParameter("birthDate"), Integer.parseInt(request.getParameter("id")));
                request.setAttribute("realCustomer", realCustomer);
                next = "/jsps/realcustomer/add-real-customer-page.jsp";
                logger.debug("Forward to " + next);
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
                next = "/jsps/realcustomer/real-customer-delete-page.jsp";
                logger.debug("Forward to " + next);
            } catch (SqlException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            }
        } else {
            next = "/jsps/realcustomer/manage-real-customer.jsp";
            logger.debug("Forward to " + next);

        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(next);
        requestDispatcher.forward(request, response);

    }
}
