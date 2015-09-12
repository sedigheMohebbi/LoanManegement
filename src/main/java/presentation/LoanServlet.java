package presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String nextJsp = null;
        if ("add".equals(operation)) {
            nextJsp = "/jsps/loan/add-loan.jsp";
        }
        else if("retrieval".equals(operation)){


        }
        else {
            nextJsp = "/jsps/loan/manage-loan.jsp";
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(nextJsp);
        requestDispatcher.forward(request, response);

    }
}
