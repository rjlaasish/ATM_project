import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	
	public static void main(String[] args) throws Exception 
		{
		Bank bank=new Bank();	
		atmdetail atm=new atmdetail();
	
		System.out.println("    + **********************************+");
		System.out.println("    |    Welcome to Mero Bank Limited:  |   ");
		System.out.println("    + **********************************+");
		Date thisdate=new Date();
		SimpleDateFormat form=new SimpleDateFormat("MMMM-dd-Y hh:mm a");
		String date =form.format(thisdate);
		System.out.println("\t\t\t\t"+date);
		int choice = 0,loop;
		int choice1;
		int choice2;
		do {
			
		System.out.println("\t\tMenu");
		System.out.println("Select from menu ...press\n\t1.Go to Bank\n\t2.Go to ATM\n\t3.Exit");
		Scanner Sc=new Scanner(System.in);
		try {
		choice=Integer.parseInt(Sc.nextLine());
		}catch(Exception e) {
			System.out.println("!!! Incorrect input: !!!");
			System.out.println("Please select correct input from menu ...press\n\t1.Go to Bank\n\t2.Go to ATM\n\t3.Exit");
			choice=Integer.parseInt(Sc.nextLine());
				
		}	
		switch(choice) {
		case 1:
			
			System.out.println("\t\tWelcome to MeroBank.Ltd");
				do{
			System.out.println("Enter your choice:"); 
			System.out.println("\t1.Create Account\n\t2.Deposit\n\t3.Exit");
			try {
			choice1=Sc.nextInt();
			}catch(Exception e) {
				System.out.println("Incorrect input: ");
				System.out.println("Re-enter your choice:"); 
				System.out.println("\t1.Create Account\n\t2.Deposit\n\t3.Exit");
				choice1=Sc.nextInt();
						
			}
			
		
				switch(choice1) {
				
				case 1:
				bank.createaccount();
				System.out.println("\n\t!!!Thank you for choosing Mero Bank !!! ");
				System.out.println("\tPlease stay connected to get more update!!!\n");
				break;
				case 2:
					bank.deposit();
					break;
				case 3:
					break;
				default:
				System.out.println("Invalid Input: ");		
			break;
				
				}
				
			}while(choice!=3);
				break;
		case 2:
			//int cho = 0;
			do{
			
			
	//	label:
				System.out.println("\n\nSelect from menu ...press\n\t1.Withdraw cash\n\t2.Mini Statement\n\t3.Change pin number\n\t4.Balance Inquiry\n\t5.Exit");
			try {
				choice2=Sc.nextInt();
			}catch(Exception e) {
				choice2=0;
			
				System.out.println("!!! Incorrect input: !!!");
				//goto label;
				System.out.println("\n\nPlease select correct input from menu ...press\n\t1.Withdraw cash\n\t2.Mini Statement\n\t3.Change pin number\n\t4.Balance Inquiry\n\t5.Exit");
				choice2=Integer.parseInt(Sc.nextLine());
			}
				switch(choice2) {
			case 1:
				System.out.println("Withdraw Section: ");
				atm.withdraw();
				break;
			case 2:
				atm.ministatement();
				
				break;
			case 3:
			//	System.out.println("Change pin number section: ");
				atm.changepin();
				break;
			case 4:
				atm.balanceinquiry();
				break;
			case 5:
				break;
			default:
				
			System.out.println("Wrong Input:");	
			break;
				
				
			}
			
			}while(choice!=5);
		case 3:
			break;
		default:
			System.out.println("Invalid input: ");
			break;
		}
	}while(choice!=3);
		
		System.out.println("\tThank you for using our Service !!!");
		System.out.println("\tPlease Stay Connected.");
		
		
	
	
		

		}
}
		
