
public class User {

	private String name;
	private String password;
	private String emailid;
	private long mobilenumber;
	private long hid;
	private int initialRealCurrency;
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}

	public int getInitialRealCurrency() {
		return initialRealCurrency;
	}

	public void setInitialRealCurrency(int initialRealCurrency) {
		this.initialRealCurrency = initialRealCurrency;
	}

	public boolean passwordChecker(String password) {
		if (password.equals(this.password)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", emailid=" + emailid + ", mobilenumber="
				+ mobilenumber + ", hid=" + hid + ", initialRealCurrency=" + initialRealCurrency + ", role=" + role
				+ "]";
	}

}
