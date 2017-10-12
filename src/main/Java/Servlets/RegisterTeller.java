package Servlets;

import Interfaces.TellerInterface;
import Pojo.Customer;
import Pojo.Teller;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "registerTeller")
public class RegisterTeller extends  Custom {
    @EJB
    TellerInterface tellerInterface;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("registerTeller.jsp");
        rd.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if (get(req, "pass").equals(get(req, "conpass"))) {
            Teller teller = new Teller();
            teller.setIdNumber(get(req, "idnum"));
            teller.setName(get(req, "name"));
            teller.setEmailaddress(get(req,"email"));
            teller.setPassword(get(req, "pass"));
            teller.setWorkNumber(get(req, "worknum"));
            if (tellerInterface.register(teller)) {
                resp.sendRedirect("tellerLogin");
            } else {
                out.println("Could not register");
                out.println("<html><body><p><a href=\"registerTeller.jsp\">  Try Again </a></p></body></html>");
            }


        } else {
            out.println("PassWord does not match");
            out.println("<html><body><p><a href=\"registerTeller.jsp\">  Try Again </a></p></body></html>");
        }
    }


}
