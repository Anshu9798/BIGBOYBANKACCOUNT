/**
 * Bank Account
 * @author Anshu Nunemunthala
 *Period 6
 */
public abstract class BankAccount {

	private static int nextAccNum;
	
	private String name;
	private int acctNum;
	private double balance;
	
	/**
	 * creates a BankAccount with 0 balance
	 * @param n BankAccount name
	 */
	public BankAccount(String n)
	{
		name  = n;
		balance = 0;
		acctNum = nextAccNum;
		nextAccNum++;
	}
	
	/**
	 * creates a BankAccount, with a specified balance
	 * @param n BankAccount name
	 * @param b BankAccoutn balance
	 */
	public BankAccount(String n, double b)
	{
		name  = n;
		balance = b;
		acctNum = nextAccNum;
		nextAccNum++;
	}
	/**
	 * allows user to deposit a specified amount into a BankAccount object
	 * @param amt the amount being deposited
	 */
	public void deposit(double amt)
	{
		if(amt < 0) throw new IllegalArgumentException("You cannot depsit a negative amount");
		balance += amt;
	}
	/**
	 * allows user to withdraw a specified amount into a BankAccount object
	 * @param amt the amount being withdrawn
	 */
	public void withdraw(double amt)
	{
		if(amt < 0) throw new IllegalArgumentException("You cannot depsit a negative amount");
		balance -= amt;
	}
	/**
	 * returns the name of a specified BankAccount object
	 * @return String name of the account
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * returns the balance of a specified BankAccount object
	 * @return double balance of the account
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * returns the account number of a specified BankAccount object
	 * @return int the account number
	 */
	public int getAccNum()
	{
		return acctNum;
	}
	/**
	 * This method will run at the end of every month 
	 * will be specifically implemented in the subclasses of BankAccount
	 */
	public abstract void endOfMonthUpdate();
	/**
	 * allows user to transfer a specified amount from one account to another
	 * @param other the account being transferred to
	 * @param amt the amount being transferred
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
