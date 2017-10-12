package Beans;

import Interfaces.DbconnectInterface;
import Interfaces.TellerInterface;
import Pojo.Account;
import Pojo.Customer;
import Pojo.Teller;
import Qualifiers.LoginQ;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Local
@Stateless

public class TellerBean  implements TellerInterface{
@EJB
    DbconnectInterface dbConnect;


    public boolean issueATMCard()
    {
        return false;
    }

    public boolean register(Teller teller) {
        String sql = "INSERT into teller(IDNumber,Name,Email,PassWord,WorkNumber) values(?,?,?,?,?)";
        if (dbConnect != null) {


            try {
                PreparedStatement pst = dbConnect.createprepareStatement(sql);
                pst.setString(1, teller.getIdNumber());
                pst.setString(2, teller.getName());
                pst.setString(3, teller.getEmailaddress());
                pst.setString(4, teller.getPassword());
                pst.setString(5, teller.getWorkNumber());



                return dbConnect.dbWrite(pst);


            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }


        }
        return false;
    }
    public boolean register(Customer customer) {
        String sql = "INSERT into customer(IDNumber,Name,Email,PassWord,Address,PhoneNumber) values(?,?,?,?,?,?)";
        if (dbConnect != null) {


            try {
                PreparedStatement pst = dbConnect.createprepareStatement(sql);
                pst.setString(1, customer.getIdNumber());
                pst.setString(2, customer.getName());
                pst.setString(3, customer.getEmailaddress());
                pst.setString(4, customer.getPassword());
                pst.setString(5, customer.getAddress());
                pst.setString(6, customer.getPhoneNumber());



                return dbConnect.dbWrite(pst);


            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }


        }
        return false;
    }

    public boolean openAccount(Account account)
    {
        String sql = "INSERT into account(CustomerId,AccNumber, AccountType,Balance) values(?,?,?,?)";
        if (dbConnect != null) {


            try {
                PreparedStatement pst = dbConnect.createprepareStatement(sql);
                pst.setString(1, account.getCustomerId());
                pst.setString(2,account.getAccNumber());
                pst.setString(3, String.valueOf(account.getAccountType()));
                pst.setDouble(4, account.getBalance());

                return dbConnect.dbWrite(pst);


            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }


        }
        return false;
    }

    public boolean closeAccount(Account account)
    {
        String sql = "DELETE from account where AccNumber=?";
        if(dbConnect!=null) {


            try {
                PreparedStatement pst = dbConnect.createprepareStatement(sql);
                pst.setString(1, account.getAccNumber());

                return  dbConnect.dbWrite(pst);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }



        }
        return false;
    }

}
