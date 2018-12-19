
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
	}
	public BankAccount(String n, double b)
	{
		name  = n;
		balance = b;
		acctNum = nextAccNum;
	}
	public void deposit(double amt)
	{
		if(amt < 0) throw new IllegalArgumentException("You cannot depsit a negative amount");
		balance += amt;
	}
	public void withdraw(double amt)
	{
		if(amt < 0) throw new IllegalArgumentException("You cannot depsit a negative amount");
		balance -= amt;
	}
	public String getName()
	{
		return name;
	}
	public double getBalance()
	{
		return balance;
	}
	public abstract void endOfMonthUpdate();
	public void transfer(BankAccount other, double amt)
	{
		this.balance -= amt;
		other.deposit(amt);
	}
	public String toString()
	{
		return this.acctNum + "\t" + this.name + "\t" + this.balance;
	}
}
