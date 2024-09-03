package database.transactions;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import data.model.library.Transaction;
import enitiyFactory.EntityFactoryGen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionsOps {
	
	protected static EntityManager entityManager = EntityFactoryGen.getEntityManager();
	
	
	/**
	 * 
	 * @returns the next id number from the id generation table for the next Transaction
	 */
	 public static int getNextTableGeneratorValue() {
		 EntityTransaction transaction = null;
		 int nextValue =0;
		 try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			 // SQL query to get the next value for the sequence
			 String selectSql = "SELECT idValue FROM ID_Gen WHERE idName='TransactionId'";
		        Query selectQuery = entityManager.createNativeQuery(selectSql);
		        int currentValue = ((Number) selectQuery.getSingleResult()).intValue();

		        // Increment the value
		         nextValue = currentValue + 1;

		        // SQL query to update the sequence value
		        String updateSql = "UPDATE ID_GEN SET idValue = :nextValue WHERE idName = 'TransactionId'";
		        Query updateQuery = entityManager.createNativeQuery(updateSql);
		        updateQuery.setParameter("nextValue", nextValue);
		        int rowsUpdated = updateQuery.executeUpdate();
		        if(rowsUpdated ==0) {
		        	throw new RuntimeException("No rows updated. Check if the 'TransactionId' record exists.");
		        }
		        //commit transaction
		        transaction.commit();
		 }catch(Exception e) {
			 if(transaction !=null && transaction.isActive()) {
				 transaction.rollback();
			 }
			 e.printStackTrace();
			 throw new RuntimeException("Error updating sequence value", e);
		 }
	        
	        return nextValue;
	    }
	
	/**
	 * Inserts a new transaction in the database
	 * @param trans
	 * @param memeber
	 */
	public static void insertTransaction(Transaction trans) {
		
		
		
	}
	/**
	 * 
	 * @param condition
	 * @returns List of transactions based on conditions such as Transactiion type, Date of transactions, or member who carried out the transaction
	 */
	
	public static ObservableList<Transaction> viewTransactions(String condition) {
		ObservableList<Transaction> transactions = FXCollections.observableArrayList(); //List of books from the database
		
		return transactions;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

