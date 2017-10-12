package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class Custom extends HttpServlet {

    protected  String get(HttpServletRequest req, String key){
        return  req.getParameter(key);
    }
}
