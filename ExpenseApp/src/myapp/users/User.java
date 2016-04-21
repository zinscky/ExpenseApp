/**
 * 
 */
package myapp.users;

/**
 * @author Z.
 *
 */
public class User extends Person {
	
	private String userName;
	private String password;
	private String emailId;
	private int cost;
	
	
	public User() {
		
	}
	
	public User(String fName, 
				String lName, 
				String userName, 
				String password,
				String emailId,
				int cost) {
		
		super(fName, lName);
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
		this.cost = cost;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getpassword() {
		return password;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public int getCost() {
		return cost;
	}

}
