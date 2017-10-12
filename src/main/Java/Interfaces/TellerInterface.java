package Interfaces;

import Pojo.Account;
import Pojo.Customer;
import Pojo.Teller;

import javax.ejb.Local;

@Local
public interface TellerInterface {

    boolean issueATMCard();
    boolean register(Teller teller);
    boolean  openAccount(Account account);
    boolean closeAccount(Account account);
    boolean register(Customer customer);

}
