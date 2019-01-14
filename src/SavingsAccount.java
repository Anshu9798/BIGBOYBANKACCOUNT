/**
 * Savings Account
 * @author Anshu Nunemunthala
 *Period 6
 */
public class SavingsAccount extends BankAccount
{
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	private double intRate;
	
	/**
	 * creates a savings account
	 * @param n bank account name
	 * @param b bank account balance 
	 * @param r interest rate
	 * @param mb minimum balance
	 * @param mbf minimum balance fee
	 */
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	/**
	 * creates a savings account, initialized with no balance
	 * @param n bank account name
	 * @param b bank account balance 
	 * @param r interest rate
	 * @param mb minimum balance
	 * @param mbf minimum balance fee
	 */
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n,0);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	/**
	 * a withdraw method that specifies that balance cannot go negative after transaction
	 * there is also a minimum balance that, if crossed, results in a fee withdrawn from the balance
	 * @param amt amount being withdrawn
	 */
	public void withdraw(double amt)
	{
		if(this.getBalance() >= amt && amt > 0)
		{
			super.withdraw(amt);
		}
		else
		{
			throw new IllegalArgumentException("Your balance cannot go negative and you cannot deposit a negative balance");
		}
		if(this.getBalance() < MIN_BAL)
		{
			super.withdraw(MIN_BAL_FEE);
		}
			
	}
	/**
	 * a standard transfer method 
	 * once again names must be the same for both BankAccounts and balance cannot go negative
	 * @param other the BankAccount being transferred to
	 * @param amt amount being transferred
	 */
	public void transfer(BankAccount other, double amt)
	{
		if(other.getName().equals(this.getName()))
		{
			if(this.getBalance() >= amt)
			{
				super.transfer(other, amt);
			}
			else
			{
				throw new IllegalArgumentException(" You cannot have a negative balance");
			}
		}
		else
		{
			throw new IllegalArgumentException("Names don't match");
		}
	}
	/**
	 * adds interest to the balance of the BankAccount
	 */
	public void addInterest()
	{
		this.deposit(((this.getBalance())*intRate));
	}
	/**
	 * adds the interest to the balance of the BankAccoutn at the end of the month
	 */
	public void endOfMonthUpdate()
	{
		addInterest();
	}
}

