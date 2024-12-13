public class BankAccount
{
    public BankAccount()
    {
        balance = 0;
    }

    public BankAccount(double initialBalance)
    {   
        deposit(initialBalance);
    }

    public boolean deposit(double amount)
    {
        if (amount <= 0) { return false; }
        balance = balance + amount;
        return true;
    }

    public boolean withdraw(double amount)
    {
        if (amount > balance || amount <= 0) { return false; }
        balance = balance - amount;
        return true;
    }

    public boolean transfer(double amount, BankAccount other)
    { 
        if (amount > balance || amount <= 0) { return false; }
        else if (other.deposit(amount)) { return withdraw(amount); }
        return false;        
    }


    // ------- metodi di accesso --------

    public double getBalance()
    {   
        return balance;
    }


    // ------ metodi di Object da sovrascrivere ---------
    // ......... toString e equals ......................



    // -------- campi di esemplare ---------

    private double balance;
}

