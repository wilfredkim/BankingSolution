package Servlets;

import Events.LoginEvent;
import Interfaces.ActivityInterface;
import Interfaces.CustomerInterface;
import Pojo.Account;
import Pojo.Activity;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "checkBalance")
public class Balance extends Custom {
    @EJB
    CustomerInterface customerInterface;
    @EJB
    ActivityInterface activityInterface;
    @Inject
    private Event<LoginEvent> event;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd  = req.getRequestDispatcher("balance.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Account account = new Account();
        account.setAccNumber((get(req,"accnum")));
        if(customerInterface.checkBalance(account)!=null){
            HttpSession session = req.getSession();
            session.setAttribute("Balance",account.getBalance());

            resp.sendRedirect("diplayBalance.jsp");
        }
        else{
            out.println("Could not check balance.");
            out.println("<html><body><p><a href=\"balance.jsp\">  Try Again </a></p></body></html>");
        }
    }
}
