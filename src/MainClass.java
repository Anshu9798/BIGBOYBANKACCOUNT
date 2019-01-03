import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		Scanner in = new Scanner(System.in);
		
		final int OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final int MIN_BAL = 300;
		final int MIN_BAL_FEE = 10;
		final int FINAL_TRANSACTIONS = 10;
		
		while(in.nextLine() != "P") {
		System.out.println("To create an account enter A, to perform a transaction type T, or P to terminate the program");
		String choice = in.nextLine();
		switch(choice)
		{
			case "A":
			{
				System.out.println("Would you like to add a Checking or Savings Account?");
				String accountChoice = in.nextLine();
				if(accountChoice.equals("Checking"))
				{
					System.out.println("Please enter the name of the owner");
					String str = in.next();
					String name = str;
					accounts.add(new CheckingAccount(name, TRANSACTION_FEE, TRANSACTION_FEE, FINAL_TRANSACTIONS));
				}
				else if(accountChoice.equals("Savings"))
				{
					System.out.println("Please enter the name of the owner");
					String str = in.next();
					String name = str;
					accounts.add(new SavingsAccount(name, TRANSACTION_FEE, TRANSACTION_FEE, TRANSACTION_FEE, TRANSACTION_FEE));
				}
				else
				{
					System.out.println("Please enter either 'Checking' or 'Savings'");
				}
				break;
			}
			case "T":
			{
				System.out.println("Enter D to deposit, W to withdraw, or T to Transfer");
				String accountChoice = in.nextLine();
				if(accountChoice.equals("D"))
				{
					
				}
				
				
				break;
			}
			case "P":
			{
				break;
			}
			default:
			{
				System.out.println("To create an account enter A, to perform a transaction type T, or P to terminate the program");
				choice = in.nextLine();
			}
		}	
		}
		
	}

}
