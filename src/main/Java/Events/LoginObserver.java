package Events;

import Interfaces.ActivityInterface;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

public class LoginObserver {
     @EJB
     ActivityInterface activityInterface;
     public  void observe(@Observes LoginEvent loginEvent){
     System.out.println(loginEvent.getMessage());
     activityInterface.recordActivity(loginEvent.getActivity());

     }
}
