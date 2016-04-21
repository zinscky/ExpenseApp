/**
 * 
 */
package myapp.users;

/**
 * @author Z.
 *
 */
public class Person {
	
	private String fName;
	private String lName;
	
	public Person() {
		
	}
	
	public Person(String _fName, String _lName) {
		fName = _fName;
		lName = _lName;
	}
	
	public void setFName(String _fName) {
		fName = _fName;
	}
	
	public void setLName(String _lName) {
		lName = _lName;
	}
	
	public String getFName() {
		return fName;
	}
	
	public String getLName() {
		return lName;
	}
	
	

}
