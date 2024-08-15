package database.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import data.model.member.Member;
import data.model.member.transaction.Transaction;
import database.conn.DatabaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionsOperation {
	//Returns database connection and stores it in conn
	static Connection conn = DatabaseConn.getConnection();
		
		/**
		 * 
		 * @returns the last id saved when a new member transation was added in the system
		 */
	public static int getLastTransactionId() {
			String query = "SELECT * FROM TransactionIDs ORDER BY id DESC OFFSET 0 ROW FETCH FIRST 1 ROW ONLY";
			int id =0;
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				//Checks if the table is empty
				if(rs.next() == false) {
					id =1;
					
				}
				else {
					 id =rs.getInt(1);
					 id ++; //increment by 1
				}
				
		}catch(SQLException e) {
				e.printStackTrace();
		}
		return id;
		}
	/**
	 * Saves the last hold id for future use
	 * @param id
	 */
	public static void saveTransationIds(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO TransationIDs (id) VALUES(?)");
			stmt.setInt(1, id);
			//execute
			stmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Inserts a new transaction in the database
	 * @param trans
	 * @param memeber
	 */
	public static void insertTransaction(Transaction trans, Member member) {
		
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Transactions  (TransactionId,TransType,book,member,Date) VALUES(?,?,?,?,?)");
			stmt.setString(1, trans.getTransationId());
			stmt.setString(2, trans.getTransactionType());
			stmt.setString(3, trans.getBook());
			stmt.setString(4, member.getMemberId());
			stmt.setString(5, trans.getDate());
			//Execute
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param condition
	 * @returns List of transactions based on conditions such as Transactiion type, Date of transactions, or member who carried out the transaction
	 */
	
	public static ObservableList<Transaction> viewTransactions(String condition) {
		ObservableList<Transaction> transactions = FXCollections.observableArrayList(); //List of books from the database
		transactions.clear();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Transation_View WHERE TransType=? OR Date=? OR Book=? OR member=? ORDER BY Date DESC");
			stmt.setString(1,  "%"+condition+"%");
			stmt.setString(2, "%"+condition+"%");
			stmt.setString(3, "%"+condition+"%");
			stmt.setString(4, "%"+condition+"%");
			//ResultSet
			ResultSet rs = stmt.executeQuery();
			//Extract results
			while(rs.next()) {
				Transaction transaction = new Transaction(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				transactions.add(transaction);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return transactions;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

