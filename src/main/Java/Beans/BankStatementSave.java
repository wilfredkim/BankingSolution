package Beans;

import Interfaces.CustomerInterface;
import Pojo.Account;
import Pojo.Activity;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.enterprise.inject.New;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BankStatementSave  {

    public  void saveList(ArrayList list){

        //ArrayList<Activity> list = customerInterface.viewBankStatement(account);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Documents\\Bankstatement.txt");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new Gson().toJson(list));
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


}

