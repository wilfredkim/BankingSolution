package Beans;

import Interfaces.ActivityInterface;
import Interfaces.DbconnectInterface;
import Pojo.Activity;
import Pojo.Statement;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Stateless
@Local
public class ActvityBean implements ActivityInterface {
    @EJB
    DbconnectInterface dbConnect;
    public boolean recordActivity(Activity activity) {
        String sql = "INSERT into activity(UserId,UserType,Time,Detail) values(?,?,?,?)";
        if (dbConnect != null) {


            try {
                PreparedStatement pst = dbConnect.createprepareStatement(sql);
                pst.setString(1, activity.getUserId());
                pst.setString(2, activity.getUserType());
                pst.setString(3, activity.getTime());
                pst.setString(4, activity.getDetail());




                return dbConnect.dbWrite(pst);


            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }


        }
        return false;
    }


}
