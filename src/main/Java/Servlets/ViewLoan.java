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
import java.util.ArrayList;

@WebServlet(urlPatterns = "viewLoan")
public class ViewLoan extends  Custom {
    @EJB
    BankManagerInterface managerInterface;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if(managerInterface.viewLoan()!=null){
            ArrayList<Loan> loans = managerInterface.viewLoan();
            req.setAttribute("loanlist",loans);
            RequestDispatcher rd  = req.getRequestDispatcher("viewLoan.jsp");
            rd.forward(req,resp);

        }
    }
}
