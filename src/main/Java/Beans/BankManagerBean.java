package Beans;

import Interfaces.BankManagerInterface;
import Interfaces.DbconnectInterface;
import Pojo.*;
import Qualifiers.LoginQ;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;

@Local
@Stateless

public class BankManagerBean implements BankManagerInterface {
    @EJB
    DbconnectInterface dbconect;

    public boolean approveLoan(Loan loan) {
        String status = "Approved";

        String sql = "UPDATE loan set Status ='" + status + "' where AccNumber='" + loan.getAccNumber() + "' ";
        if (dbconect != null) {
            PreparedStatement pst = dbconect.createprepareStatement(sql);

            dbconect.dbWrite(pst);

            return true;

        }
        return false;
    }

    public boolean deleteCustomer(Customer customer) {
        String sql = "DELETE from customer where IDNumber=?";
        if (dbconect != null) {


            try {
                PreparedStatement pst = dbconect.createprepareStatement(sql);
                pst.setString(1, customer.getIdNumber());

                return dbconect.dbWrite(pst);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }


        }
        return false;
    }

    public boolean deleteTeller(Teller teller) {
        String sql = "DELETE from teller where WorkNumber=?";
        if (dbconect != null) {


            try {
                PreparedStatement pst = dbconect.createprepareStatement(sql);
                pst.setString(1, teller.getWorkNumber());

                return dbconect.dbWrite(pst);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }


        }
        return false;
    }
@Transactional(value = Transactional.TxType.NEVER)
    public ArrayList<Loan> viewLoan() {
        String status = "Not_Approved";
        ArrayList<Loan> loanList = new ArrayList<Loan>();
        if (dbconect != null) {

            String sql = "SELECT * from loan where Status='" + status + "' ";
            try {
                ResultSet rs = dbconect.dbRead(sql);
                while (rs != null && rs.next()) {
                    Loan loan = new Loan();
                    loan.setCutomerId(rs.getString("CustomerId"));
                    loan.setAccNumber(rs.getString("AccNumber"));
                    loan.setAmountRequest(rs.getDouble("AmountRequest"));
                    String type = rs.getString("LoanType");
                    if (type.equals("LongTerm")) {
                        loan.setLoanType(LoanType.LongTerm);
                    } else {
                        loan.setLoanType(LoanType.ShortTerm);
                    }
                    loan.setReasonForLoan(rs.getString("Reason"));
                    loanList.add(loan);


                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return loanList;
        }
        return null;
    }

}



