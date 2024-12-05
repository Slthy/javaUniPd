public class Bonifico{
	public static void main (String[] args){
		BankAccount account1 = new BankAccount(1000);
		BankAccount account2 = new BankAccount(1000);
		account1.deposit(1000);
		account2.deposit(100);
		System.out.printf("Saldo account1: %.2f\n", account1.getBalance());
		System.out.printf("Saldo account2: %.2f\n", account2.getBalance());
		account1.withdraw(400);
		account2.deposit(400);
		System.out.printf("Nuovo saldo account1: %.2f\n", account1.getBalance());
		System.out.printf("Nuovo saldo account2: %.2f\n", account2.getBalance());
	}
}
