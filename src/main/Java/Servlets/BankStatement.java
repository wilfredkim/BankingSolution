package Servlets;

import Beans.BankStatementSave;
import Interfaces.ActivityInterface;
import Interfaces.CustomerInterface;
import Pojo.Account;
import Pojo.Activity;
import Pojo.Statement;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "bankstatement")
public class BankStatement  extends  Custom{
    @EJB
    CustomerInterface customerInterface;
    Account account = new Account();
    BankStatementSave bankStatementSave = new BankStatementSave();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd  = req.getRequestDispatcher("bankStatement.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        account.setAccNumber((get(req,"accnum")));

        if(customerInterface.viewBankStatement(account)!=null) {
            ArrayList<Statement> list = customerInterface.viewBankStatement(account);
            req.setAttribute("viewstatement", list);
            bankStatementSave.saveList(list);
            RequestDispatcher dispatcher = req.getRequestDispatcher("displayStatement.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
