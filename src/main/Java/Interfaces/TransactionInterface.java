package Interfaces;

import Pojo.Statement;

import javax.ejb.Local;

@Local
public interface TransactionInterface {
    boolean recordTransaction(Statement statement);
}
