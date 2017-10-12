package Servlets;

import Interfaces.CustomerInterface;
import Interfaces.TellerInterface;
import Pojo.Customer;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(urlPatterns = "CustomerRegister")
public class RegisterCustomer extends Custom {
    @EJB
    TellerInterface tellerInterface;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("registerCustomer.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if (get(req, "pass").equals(get(req, "conpass"))) {
            Customer customer = new Customer();
            customer.setName(get(req, "name"));
            customer.setIdNumber(get(req, "idnum"));
            customer.setEmailaddress(get(req, "email"));
            customer.setPassword(get(req, "pass"));
            customer.setAddress(get(req, "address"));
            customer.setPhoneNumber(get(req, "phone"));
            if (tellerInterface.register(customer)) {
                HttpSession session =req.getSession();
                session.setAttribute("custIDNumber",customer.getIdNumber());
                resp.sendRedirect("openAccount");
            } else {
                out.println("Could not register");
                out.println("<html><body><p><a href=\"registerCustomer.jsp\">  Try Again </a></p></body></html>");
            }


        } else {
            out.println("PassWord does not match");
            out.println("<html><body><p><a href=\"addCustomer.jsp\">  Try Again </a></p></body></html>");
        }
    }
}
