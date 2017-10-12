package Beans;

import Interfaces.CustomerInterface;
import Interfaces.DbconnectInterface;
import Pojo.*;
import Pojo.Statement;
import Qualifiers.LoginQ;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;

@Stateless
@Local
@LoginQ(LoginQ.LoginChoice.CUSTOMER)
public class CustomerBean implements CustomerInterface {
    @EJB
    DbconnectInterface dbConnect;
@Transactional(value = Transactional.TxType.REQUIRED)
    public boolean makeDeposit(Account account, double amount) {
        if (dbConnect != null) {
            if (checkBalance(account)!=null) {
                double bal = amount + account.getBalance();


                String sql = "UPDATE account set Balance='" + bal + "' where AccNumber='" + account.getAccNumber() + "' ";
                System.out.println("balance" + bal);
                System.out.println("acc" + account.getAccNumber());
                PreparedStatement pst = dbConnect.createprepareStatement(sql);
                return dbConnect.dbWrite(pst);


            }
        }
        return false;
    }
    @Transactional(value = Transactional.TxType.REQUIRED)
    public boolean makeWithdrawal(Account account, double amount) {
        if (dbConnect != null) {
            if (checkBalance(account)!=null) {

                if (amount>account.getBalance()) {
                    return false;
                } else {
                    double remaining = account.getBalance() - amount;
       /*if(account.getAccountType().equals("SavingsAccount")){
           if(amount>20000)
               return false;
       }else if(account.getAccountType().equals("BusinessAccount")){
           if(amount>1000000)
               return  false;
       }
       else if(account.getAccountType().equals("CurrentAccount")){
           if(amount>100000)
               return  false;
       }*/

                    String sql = "UPDATE account set Balance='" + remaining + "' where AccNumber='" + account.getAccNumber() + "' ";

                    PreparedStatement pst = dbConnect.createprepareStatement(sql);

                    return dbConnect.dbWrite(pst);

                }

            }
        }
        return false;
    }

@Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Account checkBalance(Account account) {
        if (dbConnect != null) {
            String sql = "SELECT Balance from account where AccNumber='" + account.getAccNumber() + "'";
            try {
                ResultSet rs = dbConnect.dbRead(sql);
                if (rs != null && rs.next()) {
                    double bal = rs.getDouble("Balance");
                    account.setBalance(bal);

                }
                return account;
            } catch (SQLException ex) {
                ex.printStackTrace();

            }

        }
        return null;
    }
@Transactional(value = Transactional.TxType.NEVER)
    public ArrayList<Statement> viewBankStatement(Account account) {
        if(dbConnect!=null){
            ArrayList<Statement> statementList= new ArrayList<Statement>( );
            String sql = "SELECT * from transaction where AccNumber='"+account.getAccNumber()+"' ";
            try {
                ResultSet rs = dbConnect.dbRead(sql);
                while (rs!=null&& rs.next() ) {
                    Statement statement = new Statement();
                    statement.setUserId(rs.getString("UserId"));
                    statement.setAccNumber(rs.getString("AccNumber"));
                    statement.setTime(rs.getString("Time"));
                    statement.setDetail(rs.getString("Detail"));
                    statement.setAmount(rs.getDouble("Amount"));
                    statement.setBal(rs.getDouble("Balance"));
                    statementList.add(statement);


                }

                return statementList;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return null;
    }


    public boolean applyLoan(Loan loan) {
        String sql = "INSERT into loan(CustomerId, AccNumber,LoanType,AmountRequest,Reason) values(?,?,?,?,?)";
        if (dbConnect != null) {


            try {
                PreparedStatement pst = dbConnect.createprepareStatement(sql);
                pst.setString(1, loan.getCutomerId());
                pst.setString(2, loan.getAccNumber());
                pst.setString(3, String.valueOf(loan.getLoanType()));
                pst.setDouble(4, loan.getAmountRequest());
                pst.setString(5, loan.getReasonForLoan());


                return dbConnect.dbWrite(pst);


            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }


        }
        return false;
    }

    public boolean payloan(Loan loan,double amount) {
        return false;
    }
    public boolean authenticate(String email, String password) {
        String sql = "SELECT * from customer  where Email='" + email + " ' and PassWord='" + password + "'";

        if(dbConnect!=null) {
            try {
                ResultSet rs = dbConnect.dbRead(sql);
                return  rs != null && rs.next();




            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;




    }

}
