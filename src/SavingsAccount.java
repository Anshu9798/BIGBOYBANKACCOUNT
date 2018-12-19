
public class SavingsAccount extends BankAccount
{
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	private double intRate;
	
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n,0);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
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
	public void addInterest()
	{
		this.deposit(((this.getBalance())*intRate));
	}
	public void endOfMonthUpdate()
	{
		addInterest();
	}
}

