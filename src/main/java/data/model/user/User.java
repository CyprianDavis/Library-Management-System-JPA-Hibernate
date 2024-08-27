package data.model.user;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAllUsers",query="SELECT u FROM User u"),
	@NamedQuery(name="User.login", query="SELECT u FROM User u WHERE u.userName=:name AND u.passWord=:password"),
	@NamedQuery(name="User.UserPasswordExists",query="SELECT u FROM User u WHERE u.passWord=:password")
})
public class User {
	@Id
	private String userName;
	private String passWord;
	
	public User(String name,String password) {
		this.userName =name;
		this.passWord = PasswordUtils.hashPassword(password);
	}
	public User() {
		
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
