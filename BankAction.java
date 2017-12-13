package oop;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BankAction {
	public static void main(String args[]) throws Exception {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int numberOfCustomer = 0;
		Bank bank = new Bank();
		Customer [] c = bank.getCustomer();
		while(true) {
			System.out.println("Please Enter your number");
			System.out.println("1: Add Customer");
			System.out.println("2: Cash Deposit");
			System.out.println("3: witdraw money");
			System.out.println("4: check balance");
			System.out.println("5: Interest Calculation");
			System.out.println("6: Exit");
			
			int choice = Integer.parseInt(bufferedReader.readLine());
			switch(choice) {
			case 1:
				System.out.println("Creating an Account for new customer");
				System.out.println("Please Enter initial Amount for create");
				double bal = Double.parseDouble(bufferedReader.readLine());
				System.out.println("Please Enter Your Account number");
				String acc = bufferedReader.readLine();
				Account account = new Account(bal,acc);
				System.out.println("Enter Customer name");
				String name = bufferedReader.readLine();
				Customer customer = new Customer(name, account);
				c[numberOfCustomer]= customer;
				numberOfCustomer++;
				System.out.println("Number of customers = "+ numberOfCustomer);
				for (int i = 0; i < numberOfCustomer; i++) {
					System.out.println(c[i].getName());
					
				}
				
				break;
			case 2:
				System.out.println("Please Enter Your Account Number");
				acc = bufferedReader.readLine();
				if(numberOfCustomer == 0) {
					System.err.println("No Customer Found");
				}else {
					boolean found = false;
					for (int i = 0; i < numberOfCustomer; i++) {
						Account temp = c[i].getAccount();
						String accountTemp = temp.getAccountNumber();
						if(accountTemp.equals(acc)) {
							System.out.println("Please Enter Your Deposit amount");
							double money = Double.parseDouble(bufferedReader.readLine());
							temp.deposit(money);
							found = true;
						}
					}
					if(found == false) {
						System.out.println("Account number not Exist");
					}
				}
				break;
			case 3:
				System.out.println("Please Enter Your Account Number");
				acc = bufferedReader.readLine();
				if(numberOfCustomer == 0) {
					System.err.println("No Customer Found");
				}else {
					boolean found = false;
					for (int i = 0; i < numberOfCustomer; i++) {
						Account temp = c[i].getAccount();
						String accountTemp = temp.getAccountNumber();
						if(accountTemp.equals(acc)) {
							System.out.println("Please Enter Your Withdraw amount");
							double money = Double.parseDouble(bufferedReader.readLine());
							temp.withdrawl(money);
							found = true;
						}
					}
					if(found == false) {
						System.out.println("Account number not Exist");
					}
				}
				break;
				
			case 4:
				System.out.println("Please Enter Your Account Number");
				acc = bufferedReader.readLine();
				if(numberOfCustomer == 0) {
					System.err.println("No Customer Found");
				}else {
					boolean found = false;
					for (int i = 0; i < numberOfCustomer; i++) {
						Account temp = c[i].getAccount();
						String accountTemp = temp.getAccountNumber();
						if(accountTemp.equals(acc)) {
							System.out.println("Account balance is "+ temp.getBalance());
							
						}
					}
					if(found == false) {
						System.out.println("Account number not Exist");
					}
				}
				break;
			case 5:
				System.out.println("Please Enter Your Account Number");
				acc = bufferedReader.readLine();
				if(numberOfCustomer == 0) {
					System.err.println("No Customer Found");
				}else {
					boolean found = false;
					for (int i = 0; i < numberOfCustomer; i++) {
						Account temp = c[i].getAccount();
						String accountTemp = temp.getAccountNumber();
						if(accountTemp.equals(acc)) {
							bank.calculateInterest(c[i]);
							found = true;
						}
					}
					if(found == false) {
						System.out.println("Account number not Exist");
					}
				}
				break;
			case 6:
				System.exit(0);
				break;
			 default:
				break;
			
			}
			
			
		}
	}
	

}


class Bank {
	private double interestRate = 8.5;
	private double transections = 10;
	
	
	Customer[] customers = new Customer[1000];
	
	public void calculateInterest(Customer customer) {
		Account a = customer.getAccount();
		double bal = a.getBalance();
		double interestAmount = bal * interestRate/100;
		double totalBalance = bal + interestAmount;
		System.out.println("Interest Amount is " +interestAmount + 
				"And total amount after Intersest "+ totalBalance);
		
	}
	public double getInterestRate() {
		return interestRate;
	}
	public double getTransectionsFees() {
		return transections;
	}
	public Customer[] getCustomer() {
		return customers;
	}
	
}

class Account {
	private double balance = 100;
	private String accountNumber;
	private boolean firstTime = true;
	
	public Account(String acc) {
		accountNumber = acc;
	}
	
	public Account (double bal, String acc) {
		if(bal >= 100) {
			balance = bal;
		} else {
			balance = 100;
		}
		accountNumber = acc;
	}
	
	public void deposit(double howMuch) {
		if(howMuch>0) {
			balance = balance + howMuch;
			System.out.println(howMuch + " is added with your Account"+ 
										" And Your total balance is now " + balance);
		}else {
			System.err.println("Negetive balance is not possible");
		}
		
	}
	
	public void withdrawl(double cashWithdraw) {
		if(cashWithdraw > 0) {
			if(firstTime == true) {
				double x = balance;
				double tempBalance = balance;
				tempBalance = tempBalance - cashWithdraw;
				if(tempBalance>=100) {
					balance = balance - cashWithdraw;
					System.out.println("Your total balance "+x+" You Withdraw amount is "
							+cashWithdraw+"Now Your Balance is "+ balance);
					
				}
				 else {
					System.err.println("Insufficient Balance to remove " + cashWithdraw);
				}
				firstTime = false;
				
			}
			 else {
				 double tempBalance = balance;
				 Bank bank = new Bank();
				 tempBalance = tempBalance - cashWithdraw - bank.getTransectionsFees();
				 if(tempBalance>=100) {
						balance = balance - cashWithdraw - bank.getTransectionsFees();
						System.out.println("You Withdraw "+cashWithdraw+"Transection fees "+ bank.getTransectionsFees()
								+"Now Your Balance is "+ balance);
						
					}
					 else {
						System.err.println("Insufficient Balance to remove " + cashWithdraw);
					}
			}
			
			
		}
		 else {
			System.err.println("You can't withdraw Negetive amount " + cashWithdraw);
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
}
class Customer {
	private String name;
	private Account account;
	Customer(String n, Account a){
		name = n;
		account = a;
	}
	
	public void display() {
		System.out.println("Customer name "+ name +" Account Number " + account.getAccountNumber()
						+ "Balance is "+ account.getBalance());
		
	}
	
	public String getName() {
		return name;
	}
	
	public Account getAccount() {
		return account;
	}
	
}