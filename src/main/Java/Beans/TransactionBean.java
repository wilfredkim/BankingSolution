package Beans;

import Interfaces.DbconnectInterface;
import Interfaces.TransactionInterface;
import Pojo.Statement;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Stateless
@Local
public class TransactionBean implements TransactionInterface {
    @EJB
    DbconnectInterface dbConnect;
    public boolean recordTransaction(Statement statement) {
        String sql = "INSERT into transaction(UserId,AccNumber,Time,Detail, Amount,Balance) values(?,?,?,?,?,?)";
        if (dbConnect != null) {


            try {
                PreparedStatement pst = dbConnect.createprepareStatement(sql);
                pst.setString(1, statement.getUserId());
                pst.setString(2, statement.getAccNumber());
                pst.setString(3,statement.getTime());
                pst.setString(4, statement.getDetail());
                pst.setDouble(5, statement.getAmount());
                pst.setDouble(6,statement.getBal());




                return dbConnect.dbWrite(pst);


            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }


        }
        return false;

    }
}
