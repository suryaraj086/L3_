import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ZCoinExchange {

	Map<String, User> login = new HashMap<>();
	Map<String, Account> account = new HashMap<>();
	Map<String, LinkedList<Transaction>> transaction = new HashMap<>();
	Map<String, User> signup = new HashMap<>();
	Map<Integer, String> connect = new HashMap<>();
	private int zid = 0;
	public int conversionRate = 2;
	public double commision = 0.15;

	ZCoinExchange() {
		User user = ObjectSetter.adminSetter("surya", "s@123.com", "1234", 123, 12345, 100);
		User user1 = ObjectSetter.userSetter("surya", "s@12.com", "123", 123, 12345, 100);
		login.put("s@123.com", user);
		login.put(user1.getEmailid(), user1);
		Account acc = new Account();
		acc.setRcBalance(100);
		acc.setzCoinBalance(0);
		acc.setZid(0);
		account.put(user1.getEmailid(), acc);
	}

	public Role login(String email, String password) throws Exception {
		nullChecker(email, password);
		User user = login.get(email);
		if (user != null && user.passwordChecker(password)) {
			return user.getRole();
		}
		throw new Exception("Invalid username and password");
	}

	public String approveSignup(String email) {
		User user = signup.get(email);
		login.put(email, user);
		signup.remove(email);
		createAccount(user);
		return "Approved";
	}

	public String getInformation(String email) throws Exception {
		nullChecker(email);
		return login.get(email).toString();
	}

	public void createAccount(User user) {
		Account acc = new Account();
		acc.setRcBalance(user.getInitialRealCurrency());
		acc.setzCoinBalance(0);
		zid++;
		acc.setZid(zid);
		connect.put(zid, user.getEmailid());
		account.put(user.getEmailid(), acc);
	}

	public String submittedInfo(String email) throws Exception {
		nullChecker(email);
		return login.get(email).toString();
	}

	public String walletBalance(String email) throws Exception {
		nullChecker(email);
		return account.get(email).toString();
	}

	public void nullChecker(Object inp) throws Exception {
		if (inp == null) {
			throw new Exception("Error");
		}
	}

	public String signup(User user, String email) throws Exception {
		nullChecker(email);
		signup.put(email, user);
		return "Signup Successful";
	}

	public void nullChecker(Object inp, Object input) throws Exception {
		if (inp == null || input == null) {
			throw new Exception("Error");
		}
	}

	public boolean passwordChecker(String password, User user) {

		if (!password.contains(user.getName()) && !password.contains(user.getMobilenumber() + "")
				&& password.length() >= 8) {
			return true;
		}
		return false;
	}

	public String withdrawRc(String email, int amount) throws Exception {
		if (amount <= 0) {
			throw new Exception("Enter a valid amount");
		}
		Account acc = account.get(email);
		int bal = acc.getRcBalance();
		bal -= amount;
		acc.setRcBalance(bal);
		return "Amount Debited";
	}

	public String depositRc(String email, int amount) throws Exception {
		if (amount <= 0) {
			throw new Exception("Enter a valid amount");
		}
		Account acc = account.get(email);
		int bal = acc.getRcBalance();
		bal += amount;
		acc.setRcBalance(bal);
		return "Amount Credited";
	}

	public String rcToZcoinTransaction(String email, int amount, Transaction trans) throws Exception {
		Account acc = account.get(email);
		int rc = acc.getRcBalance();
		balanceChecker(rc, amount);
		rc -= amount;
		int zcoin = acc.getzCoinBalance();
		zcoin += (conversionRate * amount);
		acc.setRcBalance(rc);
		acc.setzCoinBalance(zcoin);
		addToTransaction(email, trans);
		return "Transaction Successful";
	}

	public String zCoinToRCTransaction(String email, int amount, Transaction trans) throws Exception {
		Account acc = account.get(email);
		int zcoin = acc.getzCoinBalance();
		balanceChecker(zcoin, amount);
		int rc = acc.getRcBalance();
		int value = (zcoin / conversionRate);
		rc += value;
		System.out.println("Rc is " + rc);
		double val = value * commision;
		rc -= val;
		System.out.println("Rc after commision is " + rc);
		acc.setRcBalance(rc);
		acc.setzCoinBalance(zcoin);
		addToTransaction(email, trans);
		return "Transaction Successful";
	}

	public void addToTransaction(String email, Transaction trans) {
		LinkedList<Transaction> arr = transaction.get(email);
		if (arr == null) {
			arr = new LinkedList<>();
		}
		arr.add(trans);
		transaction.put(email, arr);
	}

	public String changePassword(String email, String newPassword) throws Exception {
		User user = login.get(email);
		nullChecker(user, newPassword);
		user.setPassword(newPassword);
		return "Password changed successfully";
	}

	public String transferToAnotherUser(String email, int zid, int amount, Transaction transfer) throws Exception {
		Account acc = account.get(email);
		int zBalance = acc.getzCoinBalance();
		balanceChecker(zBalance, amount);
		String toEmail = connect.get(zid);
		Account toAcc = account.get(toEmail);
		int toZBalance = toAcc.getzCoinBalance();
		toZBalance += amount;
		zBalance -= amount;
		toAcc.setzCoinBalance(toZBalance);
		acc.setzCoinBalance(zBalance);
		addToTransaction(email, transfer);
		return "Transaction Successful";
	}

	public String listAllSignup() {
		StringBuilder str = new StringBuilder();
		for (User user : signup.values()) {
			str.append(user.toString());
		}
		return str.toString();
	}

	public String listAllTransaction() {
		StringBuilder str = new StringBuilder();
		for (List<Transaction> arr : transaction.values()) {
			str.append(arr);
		}
		return str.toString();
	}

	public void balanceChecker(int balance, int amount) throws Exception {
		if (amount > balance) {
			throw new Exception("insufficient balance");
		} else if (amount <= 0) {
			throw new Exception("Enter valid amount");
		}
	}

}
