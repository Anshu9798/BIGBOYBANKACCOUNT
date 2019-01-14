/**
 * Checking Account
 * @author Anshu Nunemunthala
 *Period 6
 */
public class CheckingAccount extends BankAccount{

	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	
	private int numTransactions;
	
	/**
	 * creates a checking account
	 * @param n bank account name
	 * @param b bank account balance
	 * @param odf over draft fee
	 * @param tf transaction fee
	 * @param freeTrans free transactions
	 */
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n,b);
		 OVER_DRAFT_FEE = odf;
		 TRANSACTION_FEE = tf;
		 FREE_TRANS = freeTrans;
		 
	}
	/**
	 * creates a checking account, initialized with no balance 
	 * @param n bank account name
	 * @param b bank account balance
	 * @param odf over draft fee
	 * @param tf transaction fee
	 * @param freeTrans free transactions
	 */
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n,0);
		 OVER_DRAFT_FEE = odf;
		 TRANSACTION_FEE = tf;
		 FREE_TRANS = freeTrans;
	}
	/**
	 * a deposit method that also allows the user to have an allotted number of free transactions
	 * if the number of free transactions is passed, a transaction fee is exacted
	 * @param amt amount being deposited
	 */
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
	/**
	 * allows user to withdraw a specified amount 
	 * original balance has to be greater than 0 and the amount withdrawn has to be greater than 0
	 * @param amt amount being withdrawn
	 */
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
	/**
	 * a withdraw method that requires the existing balance of the BankAccount to be greater than 0 after the transaction
	 * amount transfered  must be non-negative
	 * the BankAccounts must be under the same name
	 * @param other BankAccount being transferred
	 * @param amt amount being transferred
	 */
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
	/**
	 */
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
}
