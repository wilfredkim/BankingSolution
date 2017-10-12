package Servlets;

import Events.LoginEvent;
import Interfaces.ActivityInterface;
import Interfaces.DbconnectInterface;
import Pojo.Activity;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "customerLogout")
public class CustomerLogout extends  Custom{
    @EJB
    DbconnectInterface dbconnectInterface;
    @Inject
    private Event<LoginEvent> event;
    @EJB
    ActivityInterface activityInterface;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Activity activity = new Activity();
        activity.setUserId((String) session.getAttribute("CustomerEmail"));
        activity.setUserType("Customer");
        Date date=java.util.Calendar.getInstance().getTime();
        activity.setTime(String.valueOf(date));
        activity.setDetail("Logout");
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setMessage("Customer \t"+activity.getUserId()+"\tlogin" );
        loginEvent.setActivity(activity);
        event.fire(loginEvent);


         dbconnectInterface.dbClose();
         session.invalidate();
         resp.sendRedirect("customerLogin.jsp");
    }
}
