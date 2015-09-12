package presentation;


import business.LoanTypeBiz;
import exception.SqlException;
import exception.ValidationException;
import model.LoanType;

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
            nextJsp = "/jsps/loantype/add-loan-type.jsp"; //
        } else if ("next".equals(operation)) {
            nextJsp = "/jsps/loantype/grant-condition.jsp";
            String loanTypeName =  request.getParameter("loanTypeName");
            String interestRate =  request.getParameter("interestRate");
            request.getSession().setAttribute("loanTypeName", loanTypeName);
            request.getSession().setAttribute("interestRate", interestRate);
        } else if ("save".equals(operation)) {
            LoanType loanType = null;
            try {
                loanType = LoanTypeBiz.getInstance().createAndSaveLoanType(request.getSession().getAttribute("loanTypeName").toString()
                        , new BigDecimal(request.getSession().getAttribute("interestRate").toString())
                        , request.getParameterValues("name"), (request.getParameterValues("minContractDuration"))
                        ,(request.getParameterValues("maxContractDuration"))
                        ,(request.getParameterValues("minContractAmount"))
                        , (request.getParameterValues("maxContractAmount")));


                request.setAttribute("loanType", loanType);
                nextJsp = "/jsps/loantype/show-loan-type.jsp";

            } catch (SqlException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            } catch (ValidationException e) {
                request.setAttribute("error", e);
                nextJsp = "/jsps/error-page.jsp";
            }
        } else {
            nextJsp = "/jsps/loantype/manage-loan-type.jsp";
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(nextJsp);
        requestDispatcher.forward(request, response);

    }
}
