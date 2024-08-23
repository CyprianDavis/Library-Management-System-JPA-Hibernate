package data.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="Users.findAllUsers",query="SELECT u FROM Users u"),
	@NamedQuery(name="Users.login", query="SELECT u FROM Users u WHERE u.userName=:name AND u.passWord=:password"),
	@NamedQuery(name="Users.findUserByName",query="SELECT u FROM Users u WHERE u.userName=:name")
	
})
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
