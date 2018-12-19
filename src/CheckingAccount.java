
public class CheckingAccount extends BankAccount{

	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	
	private int numTransactions;
	
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n,b);
		 OVER_DRAFT_FEE = odf;
		 TRANSACTION_FEE = tf;
		 FREE_TRANS = freeTrans;
		 
	}
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n,0);
		 OVER_DRAFT_FEE = odf;
		 TRANSACTION_FEE = tf;
		 FREE_TRANS = freeTrans;
	}
	public void deposit(double amt)
	{			
		if(amt> 0)
		{
		super.deposit(amt);
		numTransactions++;
		if(numTransactions > FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		}
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	public void withdraw(double amt)
	{
		if(this.getBalance() > 0 && amt > 0)
		{
			super.withdraw(amt);

		numTransactions++;
		if(numTransactions > FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		
		}
		if(this.getBalance() < 0)
		{
			super.withdraw(OVER_DRAFT_FEE);
		}
		}
			else
			{
				throw new IllegalArgumentException();
			}
	}
	public void transfer(BankAccount other, double amt)
	{
		if(other.getName().equals(this.getName()))
		{
			if(this.getBalance() >= amt && amt > 0)
			{
				this.transfer(other, amt);
				numTransactions++;
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
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
}
