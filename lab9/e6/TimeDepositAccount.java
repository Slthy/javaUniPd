public class TimeDepositAccount extends SavingsAccount
{  
    public TimeDepositAccount(double rate, int months)
    {
        super(rate);               // costruttore della superclasse
        monthsLeft = months;
    }

    public TimeDepositAccount(double initialBalance, double rate, int months)
    {
        super(initialBalance, rate); // costruttore della superclasse
        monthsLeft = months;
    }

    //accredita gli interessi al termine del mese. Attenzione: usa il metodo
    //deposit della superclasse, altrimenti verrebbe addebitata la penale FEE
    public void addInterest()     //NUOVO METODO
    {
        super.addInterest();
        monthsLeft--;
    }

    public void withdraw(double amount)
    {
        if (monthsLeft > 0) super.withdraw(EARLY_WITHDRAWAL_PENALTY);
        super.withdraw(amount);
    }

    public int getMonthsLeft()
    {   
        return monthsLeft;
    }

    // ------ metodi di Object sovrascritti ---------
    // ........... toString, equals .................
    public String toString(){
        return super.toString() + String.format("\nMonths left: %d", monthsLeft);
    }

    public boolean equals(Object other){
        TimeDepositAccount o = (TimeDepositAccount)other;
        if (super.equals(other) && (monthsLeft == o.getMonthsLeft())) return true;

        return false;
    }

    //-------- nuovi campi di esemplare ----------------

    private double interestRate;
    private int monthsLeft;
    private static double EARLY_WITHDRAWAL_PENALTY = 20;
}