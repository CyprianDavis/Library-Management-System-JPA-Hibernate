package database.memberOperations;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import data.model.member.Member;
import database.conn.DatabaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Handles database operations for Member (CRUD Operations)
 * @author CYPRIAN DAVIS
 *
 */

public class MembersOperations {
	//Returns database connection and stores it in conn
	static Connection conn = DatabaseConn.getConnection();
	
	/**
	 * 
	 * @returns the last id saved when a new member was added in the system
	 */
	public static int getLastMemberId() {
		String query = "SELECT * FROM MemberAutoId ORDER BY idNo DESC OFFSET 0 ROW FETCH FIRST 1 ROW ONLY";
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
				//increment id by 1
				 id++;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return id;
		}
	/**
	 * Saves the last id in the database
	 * @param id
	 */
	public static void saveLastId( int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO MemberAutoId (idNo) VALUES(?)");
			stmt.setInt(1, id);
			//execute
			stmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Inserts a member in the database
	 * @param member
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static boolean insertMember(Member member, File file) throws FileNotFoundException {
		boolean condition = true;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Member (memberNo,SurName,GivenName,OtherName,Contact,Email,Address,picture,gender,DateOfReg) VALUES (?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getSurName());
			stmt.setString(3, member.getGivenName());
			stmt.setString(4, member.getOtherName());
			stmt.setString(5, member.getContact());
			stmt.setString(6, member.getEmail());
			stmt.setString(7, member.getAddress());
			//stmt.setString(8, member.getDateOfReg());
			
			//FileInput Stream
			FileInputStream fis = new FileInputStream(file);
			stmt.setBinaryStream(8, fis, (int)file.length());
			stmt.setString(9,member.getGender());
			stmt.setString(10, member.getDateOfReg());
			//excute query
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
			return condition = false;
		}
		return condition;
	}
	/**
	 * 
	 * @returns a list of avaiable members in the system from the database
	 */
	public static ObservableList<Member> viewMembers(){
		ObservableList<Member> members = FXCollections.observableArrayList(); //List of members from the database
		members.clear();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Member");
			//Result set
			ResultSet rs = stmt.executeQuery();
			//extract resultSet
			while(rs.next()) {
				Member member = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				member.setContact(rs.getString(5));
				member.setEmail(rs.getString(6));
				member.setAddress(rs.getString(7));
				member.setDateOfReg(rs.getString(10));
				member.setGender(rs.getString(9));
				
				//add member to the list
				members.add(member);
			}
			
		}catch(SQLException e) {
			
		}
		return members;
	}
	public static ObservableList<Member> searchMemberByID(String memberID){
		ObservableList<Member> members = FXCollections.observableArrayList();
		members.clear();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Member WHERE memberNo LIKE ?");
			stmt.setString(1, "%" +memberID+ "%");
			//Result set
			ResultSet rs = stmt.executeQuery();
			//extract resultSet
			while(rs.next()) {
				Member member = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				member.setContact(rs.getString(5));
				member.setEmail(rs.getString(6));
				member.setAddress(rs.getString(7));
				member.setDateOfReg(rs.getString(10));
				member.setGender(rs.getString(9));
				
				//add member to the list
				members.add(member);
			}
			
		}catch(SQLException e) {
			
		}
		return members;
		
		}
	
	/**
	 * 
	 * @param memberId
	 * @returns member from the database if the member exists or null otherwise
	 */
	public static Member searchMember(String memberId) {
		Member member = null;
		//return null if book id deosnot exits
		if(!searhMember(memberId)) {
			return null;
		}
		else {
			try {
				PreparedStatement stmt = conn.prepareStatement("SELECT FROM * FROM Member WHERE memberNo=?");
				stmt.setString(1, memberId);
				//Results set
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					member = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	/**
	 * 
	 * @param id
	 * @returns true if the member id exists in the database or false otherwise
	 */
	public static boolean searhMember(String id) {

        try {
            String checkstmt = "SELECT COUNT(*) FROM Member WHERE memberNo=?";
            PreparedStatement stmt = conn.prepareStatement(checkstmt);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count > 0);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
	}
	/**
	 * 
	 * @returns the total number of library Members 
	 */
	public static int totalNumberOfMembers() {
		int count=0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM member");
			ResultSet rs = stmt.executeQuery();
			
            if (rs.next()) {
                 count = rs.getInt(1);
            }
		}catch(SQLException e) {
			e.printStackTrace();
	}
		return count;
		
	}
	
}


