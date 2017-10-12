package Interfaces;

import Pojo.Customer;
import Pojo.Loan;
import Pojo.Teller;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface BankManagerInterface {
    boolean approveLoan(Loan loan);
    boolean deleteCustomer(Customer customer);
    boolean deleteTeller(Teller teller);
    public ArrayList<Loan> viewLoan();
}
