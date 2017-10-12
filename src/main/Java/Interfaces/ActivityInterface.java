package Interfaces;

import Pojo.Activity;
import Pojo.Statement;

import javax.ejb.Local;

@Local
public interface ActivityInterface {
    boolean recordActivity(Activity activity);


}
