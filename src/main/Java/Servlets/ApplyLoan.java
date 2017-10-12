package Servlets;

import Interfaces.CustomerInterface;
import Pojo.Loan;
import Pojo.LoanType;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import static Pojo.LoanType.LongTerm;
import static Pojo.LoanType.ShortTerm;

@WebServlet(urlPatterns = "applyLoan")
public class ApplyLoan extends  Custom {
    @EJB
    CustomerInterface customerInterface;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("loan.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Loan loan = new Loan();
        loan.setCutomerId(get(req,"custId"));
        loan.setAccNumber((get(req,"accnum")));
        loan.setAmountRequest(Double.parseDouble(get(req,"amt")));
        loan.setReasonForLoan(get(req,"reason"));
        String type = get(req,"type");
        if(type.equals("Longterm")){
            loan.setLoanType(LongTerm);

        }else {
           loan.setLoanType(ShortTerm);
        }
        if(customerInterface.applyLoan(loan)){
           out.println("Loan Application sucessful.")  ;
           out.println("You will be notified as soon as application is approved . Thankyou");
            out.println("<html><body><p><a href=\"CustomerPage.jsp\">  Back </a></p></body></html>");
        } else{
           out.println("Application is unsucessful");
            out.println("<html><body><p><a href=\"loan.jsp\">  Try Again </a></p></body></html>");
        }
    }
}
