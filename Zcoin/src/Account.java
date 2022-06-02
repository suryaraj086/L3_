
public class Account {

	private int rcBalance;
	private int zCoinBalance;
	private int zid;

	public int getRcBalance() {
		return rcBalance;
	}

	public void setRcBalance(int rcBalance) {
		this.rcBalance = rcBalance;
	}

	public int getzCoinBalance() {
		return zCoinBalance;
	}

	public void setzCoinBalance(int zCoinBalance) {
		this.zCoinBalance = zCoinBalance;
	}

	public int getZid() {
		return zid;
	}

	public void setZid(int zid) {
		this.zid = zid;
	}

	@Override
	public String toString() {
		return "Account [rcBalance=" + rcBalance + ", zCoinBalance=" + zCoinBalance + ", zid=" + zid + "]";
	}

}
