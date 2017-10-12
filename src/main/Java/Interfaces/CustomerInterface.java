package Interfaces;

import Pojo.*;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface CustomerInterface {


   public boolean makeDeposit( Account account,double amount);

   public boolean makeWithdrawal( Account account,double amount);

    public Account checkBalance(Account account);
    public ArrayList<Statement> viewBankStatement(Account account);

    boolean applyLoan(Loan loan);

    boolean payloan(Loan loan,double amount);

}
