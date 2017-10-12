package Servlets;

import Interfaces.BankManagerInterface;
import Pojo.Loan;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "approveLoan")
public class ApproveLoan extends  Custom {
    @EJB
    BankManagerInterface managerInterface;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd  = req.getRequestDispatcher("viewLoan.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        Loan loan  = new Loan();
        loan.setAccNumber((get(req,"accnumber")));
      if(managerInterface.approveLoan(loan)){
          out.println("Sucessfully Approved");
         resp.sendRedirect("viewLoan");
      }

    }
}
