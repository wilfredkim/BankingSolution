package Servlets;

import Events.LoginEvent;
import Interfaces.ActivityInterface;
import Interfaces.LoginInterface;
import Pojo.Activity;
import Qualifiers.LoginQ;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;


public class CustomerLogin implements Filter {
    @EJB
    ActivityInterface activityInterface;
    @Inject
    @LoginQ(value = LoginQ.LoginChoice.CUSTOMER)
    LoginInterface loginInterface;
    @Inject
    private Event<LoginEvent> event;
    public void init(FilterConfig arg0) throws ServletException {

    }
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");

        String mail = req.getParameter("email");
        String password = req.getParameter("pass");
        if(loginInterface.authenticate(mail,password)){
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session= request.getSession();
            session.setAttribute("CustomerEmail",mail);
            //req.setAttribute("CustomerEmail",mail);
            getActivity(req);
            chain.doFilter(req,resp);
        }
        else{
            out.println("Incorrect password");
            out.println("<html><body><p><a href=\"customerLogin.jsp\">  Try Again </a></p></body></html>");
        }
    }

    private void getActivity(ServletRequest req) {
        Activity activity = new Activity();
        activity.setUserId(req.getParameter("email"));
        activity.setUserType("Customer");
        Date date=java.util.Calendar.getInstance().getTime();
        activity.setTime(String.valueOf(date));
        activity.setDetail("Login");
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setMessage("Customer \t"+req.getParameter("email")+"\tlogin" );
        loginEvent.setActivity(activity);
        event.fire(loginEvent);
    }

    public void destroy() {
    }


}
