
public abstract class BankAccount {

	private static int nextAccNum;
	
	private String name;
	private int acctNum;
	private double balance;
	
	public BankAccount(String n)
	{
		name  = n;
		balance = 0;
		acctNum = nextAccNum;
		nextAccNum++;
	}
	
	public BankAccount(String n, double b)
	{
		name  = n;
		balance = b;
		acctNum = nextAccNum;
		nextAccNum++;
	}
	/**
	 * allows user to deposit a specified amount into a BankAccount object
	 * @param amt
	 */
	public void deposit(double amt)
	{
		if(amt < 0) throw new IllegalArgumentException("You cannot depsit a negative amount");
		balance += amt;
	}
	/**
	 * allows user to withdraw a specified amount into a BankAccount object
	 * @param amt
	 */
	public void withdraw(double amt)
	{
		if(amt < 0) throw new IllegalArgumentException("You cannot depsit a negative amount");
		balance -= amt;
	}
	/**
	 * returns the name of a specified BankAccount object
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * returns the balance of a specified BankAccount object
	 * @return
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * returns the account number of a specified BankAccount object
	 * @return
	 */
	public int getAccNum()
	{
		return acctNum;
	}
	/**
	 * to be implemented in BankAccount subclasses
	 */
	public abstract void endOfMonthUpdate();
	/**
	 * allows user to transfer a specified amount from one account to another
	 * @param other
	 * @param amt
	 */
	public void transfer(BankAccount other, double amt)
	{
		this.balance -= amt;
		other.deposit(amt);
	}
	/**
	 * returns a string form of the BankAccount information
	 */
	public String toString()
	{
		return this.acctNum + "\t" + this.name + "\t" + this.balance;
	}
}
