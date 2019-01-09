import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	static ArrayList<BankAccount> accounts;
	static Scanner in;
	
	public static void main(String[] args) {
		
		accounts = new ArrayList<BankAccount>();
		in = new Scanner(System.in);
		
		final int OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final int MIN_BAL = 300;
		final int MIN_BAL_FEE = 10;
		final int FINAL_TRANSACTIONS = 10;
		
	
		while(true) 
		{
			System.out.println("To create an account enter A, to perform a transaction type T, or P to terminate the program");
			String choice = in.nextLine();
			switch(choice)
			{
				case "A":
				
					System.out.println("Would you like to add a Checking or Savings Account?");
					String accountChoice = in.nextLine();
					if(accountChoice.equals("Checking"))
					{
						System.out.println("Please enter the name of the owner");
						String str = in.next();
						String nameC = str;
						accounts.add(new CheckingAccount(nameC, TRANSACTION_FEE, TRANSACTION_FEE, FINAL_TRANSACTIONS));
					}
					else if(accountChoice.equals("Savings"))
					{
						System.out.println("Please enter the name of the owner");
						String str = in.next();
						String nameS = str;
						accounts.add(new SavingsAccount(nameS, TRANSACTION_FEE, TRANSACTION_FEE, TRANSACTION_FEE, TRANSACTION_FEE));
					}
					else
					{
						System.out.println("Please enter either 'Checking' or 'Savings'");
					}
					break;
				
				case "T":
					
					
					
					boolean choosing = true;
					while(choosing)
					{
						System.out.println("Enter D to deposit, W to withdraw, T to Transfer, or G to get a list of account numbers");
						String transchoice = in.nextLine();
						switch(transchoice)
						{	
						case "D":
							
							int acctnum = (int)askNum("Please enter your account number");
							BankAccount b = getAccByNum(acctnum); 
							
							double amt = promptDouble("Please enter the amount you would like to deposit (it can be 0 dollars)");
							try
							{
								b.deposit(amt);
							}
							catch(Exception e)
							{
								System.out.println("Invalid amount, please try again");
							}
							break;
						}
					}
					
				
					break;
				
				case "P":
				
					System.exit(0);	
				
				default:
			}	
		}
		
	}
	private static double askNum(String msg)
	{	
		//there was a compiler error if a boolean parameter was used in the while
		while(true)
		{
			System.out.println(msg);
			String depatm = in.nextLine();
			if (isNumeric(depatm))
			{
				return Double.parseDouble(depatm);
			}
		}
	}
	private static BankAccount getAccByNum(int n)
	{
		for( BankAccount a : accounts)
		{
			if(a.getAccNum() == n) return a;
		}
		return null;
	}
	private static boolean isNumeric(String n)
    {
        try
        {
            Double.parseDouble(n);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
	
}
