package account;

public class Account
{
    private double balance;

    public Account()
    {
        balance = 0;
    }

    public Account(double b)
    {
        balance = b;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    public double getInterest()
    {
        double interest = 0;
        
        if(balance >= 0 && balance <= 100)
        {
            interest = balance * 0.03;
        }
        else if(balance > 100 && balance < 1000)
        {
            interest = balance * 0.05;
        }
        else if(balance >= 1000)
        {
            interest = balance * 0.07;
        }
        
        return interest;
    }

    public void deposit(double amount)
    {
        balance += amount;
    }

    public void withdraw(double amount)
    {
        balance -= amount;
    }    
}
