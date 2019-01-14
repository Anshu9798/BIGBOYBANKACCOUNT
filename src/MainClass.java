/**
 * Main Class
 * @author Anshu Nunemunthala
 *Period 6
 */
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
		
		
	/**
	 * gives the user the choice of either creating an account, performing a transaction, or terminate the program
	 */
		while(true) 
		{
			System.out.println("To create an account enter A, to perform a transaction type T, or P to terminate the program\n");
			String choice = in.nextLine();
			switch(choice)
			{
				case "A":
					boolean choose = true;
					while(choose)
					{
					System.out.println("Would you like to add a Checking or Savings Account?\n");
					String accountChoice = in.nextLine();
					if(accountChoice.equals("Checking"))
					{
						System.out.println("Please enter the name of the owner\n");
						String str = in.nextLine();
						String nameC = str;
						BankAccount C = new CheckingAccount(nameC, MIN_BAL, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
						accounts.add(C);
						System.out.println("account created");
						System.out.println(C);
						choose = false;
					}
					else if(accountChoice.equals("Savings"))
					{
						System.out.println("Please enter the name of the owner\n");
						String str = in.nextLine();
						String nameS = str;
						BankAccount S = new SavingsAccount(nameS, MIN_BAL, RATE, MIN_BAL, MIN_BAL_FEE);
						accounts.add(S);
						System.out.println("account created");
						System.out.println(S);
						choose = false;
					}
					else
					{
						System.out.println("Please enter either 'Checking' or 'Savings'\n");
					}
					}
					break;
				
				case "T":

					boolean choosing = true;
					while(choosing)
					{
						/**
						 * gives the user a choice of either depositing, withdrawing, transferring, or getting a list of all account numbers in the array-list
						 */
						System.out.println("Enter D to deposit, W to withdraw, T to Transfer, or G to get a list of your account numbers\n");
						String transchoice = in.nextLine();
						switch(transchoice)
						{	
						case "D":
							int acctnumT = -1;
							
							boolean correct = true;
							while(correct)
							{
								acctnumT = (int)askNum("Please enter your account number\n"); 
								
								if(getAccByNum(acctnumT) != null)
									{
										correct = false;
									}
							}
							
							double amt = askNum("Please enter the amount you would like to deposit\n");
							try
							{
								getAccByNum(acctnumT).deposit(amt);
								System.out.println(getAccByNum(acctnumT));
							}
							catch(Exception e)
							{
								e.getMessage();
								System.out.println("transaction not authorized\n");
							}
							
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
								System.out.println(getAccByNum(acctnumW));
							}
							catch(Exception e)
							{
								e.getMessage();
								System.out.println("please try again\n");
							}
							
							break;
							
						case "T":
							int acctnumT1 = -1;
							int acctnumT2 = -1;
							boolean correct3 = true;
							while(correct3)
							{
								acctnumT1 = (int)askNum("Please enter your account number\n"); 
								acctnumT2 = (int)askNum("Please enter the number of the account you are transferring to. remember that the accounst must be under the same name\n");
								
								if(getAccByNum(acctnumT1) == null || getAccByNum(acctnumT2) == null)
									{
										System.out.println("Please enter an actual account number\n");
									}	
								correct3 = false;
							}
							double amtT = askNum("Please enter the amount you would like to transfer\n");
							try
							{
								getAccByNum(acctnumT1).transfer(getAccByNum(acctnumT2), amtT);
								System.out.println(getAccByNum(acctnumT1));
								System.out.println(getAccByNum(acctnumT2));
							}
							catch(Exception e)
							{
								e.getMessage();
								System.out.println("please try again\n");
							}
							
							break;
						
						case "G":
	
							boolean match = false;
							System.out.println("Please enter your name");
							String nameG = in.nextLine();
							for(BankAccount a : accounts)
							{
								if(a.getName().equals(nameG)) 
								{
									System.out.println(getType(a) + "\t" +a);
									match = true;
								}
							}
							if(!match) System.out.println("No matches were found");
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
	/**
	 * returns the type( Checking or Savings) of a specified BankAccount
	 * @param b the BankAccount entered
	 * @return String Checking or Savings to indicate the type of BankAccount
	 */
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
	/**
	 * prompts for the account number and converts it to a double type
	 * @param msg the amount entered by the user in String form
	 * @return double the double version of the deposit amount
	 */
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
	/**
	 * checks the array-list to see if the account number given by the user matches that of any in the array-list
	 * @param n the integer entered by the user
	 * @return BnankAccount if a BankAccount is found that matches the entered number, it returns the BankAccount
	 */
	private static BankAccount getAccByNum(int n)
	{
		for( BankAccount a : accounts)
		{
			if(a.getAccNum() == n) return a;
		}
		return null;
	}
	/**
	 * Checks to see if the provided number is actually numeric
	 * if not, the method returns false and ends the method
	 * @param n the String entered by the user which is being parsed
	 * @return boolean represents whether or not the number is numeric or not
	 */
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
