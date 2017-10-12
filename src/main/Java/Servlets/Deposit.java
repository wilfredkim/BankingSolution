package Servlets;

import Events.LoginEvent;
import Events.TransactionEvent;
import Interfaces.ActivityInterface;
import Interfaces.CustomerInterface;
import Interfaces.TransactionInterface;
import Pojo.Account;
import Pojo.Activity;
import Pojo.Statement;

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

@WebServlet(urlPatterns = "makeDeposit")
public class Deposit extends Custom {
    @EJB
    CustomerInterface customerInterface;
    @EJB
    TransactionInterface transactionInterface;
    @Inject
    private Event<TransactionEvent> event;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("makeDeposit.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Account account = new Account();
        account.setAccNumber((get(req, "accnum")));
        double amt = Double.parseDouble(get(req, "amount"));

        if (customerInterface.makeDeposit(account, amt)) {

            getActivity(req, account);
            out.print("Sucessfully deposited");

        } else {
            out.println("Could not deposit");
            out.println("<html><body><p><a href=\"makeDeposit.jsp\">  Try Again </a></p></body></html>");
        }

    }

    public void getActivity(HttpServletRequest req, Account account) {
        Statement statement = new Statement();
        if(customerInterface.checkBalance(account)!=null){
        statement.setDetail("Deposit");
        statement.setTime(String.valueOf(java.util.Calendar.getInstance().getTime()));
        HttpSession session = req.getSession(false);
        statement.setUserId(String.valueOf(session.getAttribute("CustomerEmail")));
        statement.setAccNumber(account.getAccNumber());
        statement.setAmount(Double.parseDouble(get(req, "amount")));
        statement.setBal(account.getBalance());
        TransactionEvent transactionEvent = new TransactionEvent();
        transactionEvent.setMessage("");
        transactionEvent.setStatement(statement);
        event.fire(transactionEvent);
    }
}}
