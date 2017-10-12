package Beans;

import Interfaces.DbconnectInterface;
import Interfaces.LoginInterface;
import Qualifiers.LoginQ;

import javax.ejb.EJB;
import java.sql.ResultSet;
import java.sql.SQLException;

@LoginQ(LoginQ.LoginChoice.MANAGER)
public class BankManagerLoginBean implements LoginInterface {
    @EJB
    DbconnectInterface dbconect;

    public boolean authenticate(String email, String password) {
        String sql = "SELECT * from manager  where Email='" + email + " ' and PassWord='" + password + "'";
        if (dbconect != null) {

            try {
                ResultSet rs = dbconect.dbRead(sql);
                return  rs != null && rs.next();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;

    }
}
