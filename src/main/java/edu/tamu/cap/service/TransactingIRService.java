package edu.tamu.cap.service;

import edu.tamu.cap.model.ircontext.TransactionDetails;

public interface TransactingIRService<M> extends IRService<M> {
    
    public TransactionDetails startTransaction() throws Exception;
    
    public TransactionDetails refreshTransaction(String token) throws Exception;
    
    public TransactionDetails makeTransactionDetails(String transactionToken, String expirationString) throws Exception;

    public void commitTransaction(String contextUri) throws Exception;

    public void rollbackTransaction(String string) throws Exception;
    
//    public void setTransactionDetails(TransactionDetails transactionDetails);

}
