package Servlets;

import Interfaces.TellerInterface;
import Pojo.Account;

import javax.ejb.AccessLocalException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static Pojo.AccountType.BusinessAccount;
import static Pojo.AccountType.CurrentAccount;
import static Pojo.AccountType.SavingsAccount;

@WebServlet(urlPatterns = "openAccount")
public class OpenAccount extends  Custom {
    @EJB
    TellerInterface tellerInterface;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("openAccount.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Account account = new Account();
        HttpSession session = req.getSession(false);
        String id = (String) session.getAttribute("custIDNumber");
        account.setCustomerId(id);
        account.setBalance(0.0);
        account.setAccNumber((get(req,"accnum")));
        String acctype = get(req,"type");
        if(acctype.equals("Current Account")){
            account.setAccountType(CurrentAccount);
        } else if (acctype.equals("Savings Account")){
            account.setAccountType(SavingsAccount);
        } else{
            account.setAccountType(BusinessAccount);
        }
        if(tellerInterface.openAccount(account)){
            out.println("Account open successfully");
            out.println("Customer can now Login");
            out.println("<html><body><p><a href=\"customerLogin.jsp\">  Customer Login </a></p></body></html>");
        }
        else{
            out.println("Could not open account");
            out.println("<html><body><p><a href=\"openAccount.jsp\">  Try Again </a></p></body></html>");
        }
    }
}
