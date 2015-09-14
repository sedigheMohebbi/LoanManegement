package presentation;

import business.LoanBiz;
import exception.SqlException;
import exception.ValidationException;
import model.Loan;
import model.LoanType;
import model.RealCustomer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class LoanServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(Loan.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String nextJsp ;
        if ("add".equals(operation)) {
            List<LoanType> loanTypes = LoanBiz.getInstance().loadAllLoanTypes();
            request.setAttribute("loanTypes", loanTypes);
            nextJsp = "/jsps/loan/add-loan.jsp";
            logger.debug("Forward to "+ nextJsp);
        } else if ("retrieval".equals(operation)) {
            String customerNumber = request.getParameter("customerNumber");
            try {
                RealCustomer realCustomer = LoanBiz.getInstance().findCustomer(customerNumber);
                request.setAttribute("realCustomer", realCustomer);
                nextJsp = "/jsps/loan/show-customer-in-loan.jsp";
                logger.debug("Forward to "+ nextJsp);
            } catch (ValidationException e1) {
                request.setAttribute("error1", e1);
                nextJsp = "/jsps/error-ifram.jsp";
            }

        } else if ("save".equals(operation)) {


            try {
               Loan loan = LoanBiz.getInstance().saveLoanFile(request.getParameter("customerNumber"),
                        Integer.parseInt(request.getParameter("loanTypeId")),
                        Integer.parseInt(request.getParameter("durationOfContract")),
                        new BigDecimal(request.getParameter("amountOfContract")));
                request.setAttribute("loan", loan);
                nextJsp = "/jsps/loan/save-loan.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            } catch (SqlException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
                logger.error(" error "+e);
            }


        } else {
            nextJsp = "/jsps/loan/manage-loan.jsp";
            logger.debug("Forward to "+ nextJsp);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(nextJsp);
        requestDispatcher.forward(request, response);

    }
}
