package Events;

import Interfaces.ActivityInterface;
import Interfaces.TransactionInterface;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

public class TransactionObserver {
    @EJB
    TransactionInterface transactionInterface;
    public  void observe(@Observes  TransactionEvent transactionEvent){
        System.out.println(transactionEvent);
        transactionInterface.recordTransaction(transactionEvent.getStatement());


    }
}
