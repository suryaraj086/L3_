
public class ObjectSetter {

	public static User userSetter(String name, String email, String password, long hid, long mobilenumber, int rc) {
		User user = new User();
		user.setName(name);
		user.setEmailid(email);
		user.setPassword(password);
		user.setRole(Role.user);
		user.setInitialRealCurrency(rc);
		user.setHid(hid);
		return user;
	}

	public static User adminSetter(String name, String email, String password, long hid, long mobilenumber, int rc) {
		User user = new User();
		user.setName(name);
		user.setEmailid(email);
		user.setPassword(password);
		user.setRole(Role.agent);
		user.setInitialRealCurrency(rc);
		user.setHid(hid);
		return user;
	}

	public static Transaction transactionSetter(String transferDetails, int amount, long time) {
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setTime(time);
		transaction.setTransaction(transferDetails);
		return transaction;
	}

}
