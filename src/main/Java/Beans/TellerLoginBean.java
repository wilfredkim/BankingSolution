package Beans;

import Interfaces.DbconnectInterface;
import Interfaces.LoginInterface;
import Qualifiers.LoginQ;

import javax.ejb.EJB;
import java.sql.ResultSet;
import java.sql.SQLException;
@LoginQ(LoginQ.LoginChoice.TELLER)
public class TellerLoginBean implements LoginInterface {
@EJB
    DbconnectInterface dbConnect;
   public boolean authenticate(String email, String password) {
        String sql = "SELECT * from teller  where Email='" + email + " ' and PassWord='" + password + "'";

        if(dbConnect!=null) {
            try {
                ResultSet rs = dbConnect.dbRead(sql);
                return (rs != null && rs.next());




            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;


    }
}
