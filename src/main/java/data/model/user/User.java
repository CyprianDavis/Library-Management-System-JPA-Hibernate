package data.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	private String userName;
	private String passWord;
	
	public User(String name,String password) {
		this.userName =name;
		this.passWord =password;
	}
	public void setUserName(String name) {
		this.userName = name;
	}
	public void setPassWord(String password) {
		this.passWord = password;
	}
	public String getUserName() {
		return this.userName;
	}
	public String getPassword() {
		return this.passWord;
		
	}
	

}
