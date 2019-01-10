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
		final int FREE_TRANSACTIONS = 10;
		
		
	
		while(true) 
		{
			System.out.println("To create an account enter A, to perform a transaction type T, or P to terminate the program\n");
			String choice = in.nextLine();
			switch(choice)
			{
				case "A":
				
					System.out.println("Would you like to add a Checking or Savings Account?\n");
					String accountChoice = in.nextLine();
					if(accountChoice.equals("Checking"))
					{
						System.out.println("Please enter the name of the owner\n");
						String str = in.nextLine();
						String nameC = str;
						BankAccount C = new CheckingAccount(nameC, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
						accounts.add(C);
						System.out.println("account created");
						System.out.println(C);
					}
					else if(accountChoice.equals("Savings"))
					{
						System.out.println("Please enter the name of the owner\n");
						String str = in.nextLine();
						String nameS = str;
						BankAccount S = new SavingsAccount(nameS, TRANSACTION_FEE, RATE, MIN_BAL, MIN_BAL_FEE);
						accounts.add(S);
						System.out.println("account created");
						System.out.println(S);
					}
					else
					{
						System.out.println("Please enter either 'Checking' or 'Savings'\n");
					}
					break;
				
				case "T":

					boolean choosing = true;
					while(choosing)
					{
						System.out.println("Enter D to deposit, W to withdraw, T to Transfer, or G to get a list of account numbers\n");
						String transchoice = in.nextLine();
						switch(transchoice)
						{	
						case "D":
							int acctnumT = -1;
							
							boolean correct = true;
							while(correct)
							{
								acctnumT = (int)askNum("Please enter your account number\n"); 
								
								if(getAccByNum(acctnumT) == null)
									{
										System.out.println("Please enter an actual account number\n");
									}	
								correct = false;
							}
							
							double amt = askNum("Please enter the amount you would like to deposit (it can be 0 dollars)\n");
							try
							{
								getAccByNum(acctnumT).deposit(amt);
							}
							catch(Exception e)
							{
								System.out.println("Invalid amount, please try again\n");
							}
							System.out.println(getAccByNum(acctnumT));
							break;
							
						case "W":
							int acctnumW = -1;
							
							boolean correct2 = true;
							while(correct2)
							{
								acctnumW = (int)askNum("Please enter your account number\n"); 
								
								if(getAccByNum(acctnumW) == null)
									{
										System.out.println("Please enter an actual account number\n");
									}	
								correct2 = false;
							}
							
							double amtW = askNum("Please enter the amount you would like to withdraw\n");
							try
							{
								getAccByNum(acctnumW).withdraw(amtW);
							}
							catch(Exception e)
							{
								System.out.println("Invalid amount, please try again\n");
							}
							System.out.println(getAccByNum(acctnumW));
							break;
							
						case "T":
							int acctnumT1 = -1;
							int acctnumT2 = -1;
							boolean correct3 = true;
							while(correct3)
							{
								acctnumT1 = (int)askNum("Please enter your account number\n"); 
								acctnumT2 = (int)askNum("Please enter the number of the account you are transferring to\n");
								
								if(getAccByNum(acctnumT1) == null || getAccByNum(acctnumT2) == null)
									{
										System.out.println("Please enter an actual account number\n");
									}	
								correct3 = false;
							}
							double amtT = askNum("Please enter the amount you would like to withdraw\n");
							try
							{
								getAccByNum(acctnumT1).transfer(getAccByNum(acctnumT2), amtT);
							}
							catch(Exception e)
							{
								System.out.println("Invalid amount, please try again\n");
							}
							System.out.println(getAccByNum(acctnumT1));
							System.out.println(getAccByNum(acctnumT2));
							break;
						
						case "G":
							
							for(BankAccount a : accounts)
							{
								System.out.println(getType(a) + "\t" +a);
							}
						}
					}
					break;
				
				case "P":
				
					System.exit(0);	
				
				default:
					break;
			}	
		}
	}
	
	private static String getType(BankAccount b)
	{
		if(b instanceof CheckingAccount)
		{
			return "Checking";
		}
		else
		{
			return "Savings";
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
