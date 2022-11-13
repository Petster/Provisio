package entity;

public class User {
	private String firstname = "";
	private String lastname = "";
	private String password = "";
	private String email = "";
	private String phone = "";
	private String joinDate = "";
	private Boolean isAdmin;
	private long id;
	private Integer loyaltyPoints;
	
	public User() {}
	
	public User(Integer id, String password, String email, String phone, String joindate, Boolean isAdmin, String firstname, String lastname, Integer loyaltyPoints) {
		this.password = password;
		this.email = email;
		this.id = id;
		this.phone = phone;
		this.joinDate = joindate;
		this.isAdmin = isAdmin;
		this.firstname = firstname;
		this.lastname = lastname;
		this.loyaltyPoints = loyaltyPoints;
	}
	
	/*creating user*/
	public User(String password, String email, String phone, String joindate, String firstname, String lastname, Integer loyaltyPoints, Boolean isAdmin) {
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.joinDate = joindate;
		this.firstname = firstname;
		this.lastname = lastname;
		this.loyaltyPoints = loyaltyPoints;
		this.isAdmin = isAdmin;
	}
	
	public void setPassword(String newPass) {
		this.password = newPass;
	}
	
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public void setId(long newId) {
		this.id = newId;
	}
	
	public void setPhone(String newPhone) {
		this.phone = newPhone;
	}
	
	public void setJoinDate(String newJD) {
		this.joinDate = newJD;
	}
	
	public void setAdmin(Boolean i) {
		this.isAdmin = i;
	}
	
	public void setFirstname(String i) {
		this.firstname = i;
	}
	
	public void setLastname(String i) {
		this.lastname = i;
	}
	
	public void setLoyaltyPoints(Integer i) {
		this.loyaltyPoints = i;
	}
	
	public Boolean getAdmin() {
		return isAdmin;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public Boolean isAdmin() {
		return isAdmin;
	}
	
	public String getJoinDate() {
		return joinDate;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public long getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Integer getLoyaltyPoints() {
		return loyaltyPoints;
	}
}
