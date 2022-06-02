import java.util.Scanner;

public class ZCoinRunner {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ZCoinExchange obj = new ZCoinExchange();
		boolean bool = true;
		while (bool) {
			System.out.println("1.Signup\n2.Signin");
			int val = scan.nextInt();
			scan.nextLine();
			switch (val) {
			case 1:
				System.out.println("Enter the name");
				String name = scan.nextLine();
				System.out.println("Enter the email");
				String email = scan.nextLine();
				System.out.println("Enter the password");
				String password = scan.nextLine();
				System.out.println("Enter the mobilenumber");
				long mobile = scan.nextLong();
				System.out.println("Enter the H id");
				long hid = scan.nextLong();
				User user = ObjectSetter.userSetter(name, email, password, hid, mobile, 100);
				try {
					boolean temp = obj.passwordChecker(password, user);
					if (temp) {
						System.out.println(obj.signup(user, email));
					} else {
						System.out.println("Enter a valid password");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Enter user email");
				String mail = scan.nextLine();
				System.out.println("Enter the password");
				String password1 = scan.nextLine();
				Role role = null;
				try {
					role = obj.login(mail, password1);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				switch (role) {
				case agent:
					System.out.println("1.Approve signups\n2.List all transaction\n3.Change conversion rate");
					int num = scan.nextInt();
					scan.nextLine();
					switch (num) {
					case 1:
						System.out.println(obj.listAllSignup());
						String signup = scan.nextLine();
						System.out.println(obj.approveSignup(signup));
						break;
					case 2:
						System.out.println(obj.listAllTransaction());
						break;
					case 3:
						int rate = scan.nextInt();
						obj.conversionRate = rate;
						break;
					default:
						break;
					}
					break;
				case user:
					while (bool) {
						System.out.println(
								"1.Information\n2.Rc to Zcoin Transaction\n3.Zcoin to Rc Transaction\n4.Transfer to another user\n5.Wallet balance\n6.Change password");
						int num1 = scan.nextInt();
						switch (num1) {
						case 1:
							try {
								System.out.println(obj.getInformation(mail));
							} catch (Exception e1) {
								System.out.println(e1.getMessage());
							}
							break;
						case 2:
							System.out.println("Enter the amount");
							int amount = scan.nextInt();
							Transaction trans = ObjectSetter.transactionSetter("Rc to ZCoin transaction", amount,
									System.currentTimeMillis());
							try {
								System.out.println(obj.rcToZcoinTransaction(mail, amount, trans));
							} catch (Exception e) {
								System.out.println(e.getMessage());
								e.printStackTrace();
							}
							break;
						case 3:
							System.out.println("Enter the amount");
							int amount1 = scan.nextInt();
							Transaction trans1 = ObjectSetter.transactionSetter("Zcoin to RC transaction", amount1,
									System.currentTimeMillis());
							try {
								System.out.println(obj.zCoinToRCTransaction(mail, amount1, trans1));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 4:
							System.out.println("Enter the amount");
							int amount2 = scan.nextInt();
							System.out.println("Enter the to account");
							int acc = scan.nextInt();
							Transaction transfer = ObjectSetter.transactionSetter("Transferred to" + acc, amount2,
									System.currentTimeMillis());
							try {
								System.out.println(obj.transferToAnotherUser(mail, acc, amount2, transfer));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						case 5:
							try {
								System.out.println(obj.walletBalance(mail));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 6:
							System.out.println("Enter new password");
							String newPass = scan.nextLine();
							try {
								System.out.println(obj.changePassword(mail, newPass));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						default:
							bool = false;
							break;
						}
					}
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
		scan.close();
	}

}
