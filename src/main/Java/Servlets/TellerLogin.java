package Servlets;

import Events.LoginEvent;
import Interfaces.ActivityInterface;
import Interfaces.LoginInterface;
import Pojo.Activity;
import Qualifiers.LoginQ;

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

@WebServlet(urlPatterns = "tellerLogin")
public class TellerLogin extends  Custom {
    @EJB
    ActivityInterface activityInterface;
    @Inject
    @LoginQ(value = LoginQ.LoginChoice.TELLER)
    LoginInterface loginInterface;
    @Inject
    private Event<LoginEvent> event;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("tellerLogin.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        String mail = get(req,"email");
        String password = get(req,"pass");
        if(loginInterface.authenticate(mail,password)) {
            HttpSession session  = req.getSession();
            session.setAttribute("TellerEmail",mail);
            getActivity(req);
            resp.sendRedirect("tellerPage.jsp");
         }
        else{
            out.println("Incorrect password");
            out.println("<html><body><p><a href=\"tellerLogin.jsp\">  Try Again </a></p></body></html>");
        }
    }

    private void getActivity(HttpServletRequest req) {
        Activity activity = new Activity();
        activity.setUserId(get(req,"email"));
        activity.setUserType("Teller");
        Date date = java.util.Calendar.getInstance().getTime();
        activity.setTime(String.valueOf(date));
        activity.setDetail("Login");
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setMessage("Teller \t"+get(req,"email")+"\tlogin");
        loginEvent.setActivity(activity);
        event.fire(loginEvent);
    }
}
