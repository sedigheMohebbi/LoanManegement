package presentation;

import business.GrantConditionBiz;
import business.LoanTypeBiz;
import business.RealCustomerBiz;
import exception.SqlException;
import exception.ValidationException;
import model.GrantCondition;
import model.LoanType;
import model.RealCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class LoanTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String nextJsp = null;
        if ("add".equals(operation)) {
            nextJsp = "/jsps/add-loan-type.jsp"; //
        } else if ("next".equals(operation)) {
            nextJsp = "/jsps/grant-condition.jsp";
            String loanTypeName = (String) request.getAttribute("loanTypeName");
            String interestRate = (String) request.getAttribute("interestRate");
            request.getSession().setAttribute("loanTypeName", loanTypeName);
            request.getSession().setAttribute("interestRate", interestRate);
        } else if ("save".equals(operation)) {
            LoanType loanType = null;
            try {
                loanType = LoanTypeBiz.getInstance().createAndSaveLoanType(request.getSession().getAttribute("loanTypeName").toString()
                        ,new BigDecimal( request.getSession().getAttribute("interestRate").toString())
                        , request.getParameter("name"), Integer.parseInt(request.getParameter("minContractDuration"))
                        , Integer.parseInt("maxContractDuration")
                        , new BigDecimal("minContractAmount")
                        , new BigDecimal("maxContractAmount"));

                request.setAttribute("realCustomer", realCustomer);
                next = "/jsps/add-real-customer-page.jsp";

            } catch (SqlException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                next = "/jsps/error-page.jsp";
            }
        } else {
            nextJsp = "/jsps/manage-loan-type.jsp";
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(nextJsp);
        requestDispatcher.forward(request, response);

    }
}
