package account;

import org.junit.Test;
import static org.junit.Assert.*;

public class T_E_S_T_AccountBoundaryValues
{
    Account account = new Account();
    double balance, percentage;

    @Test
    public void testGetInterestNegativeRightBound()
    {
        balance = -0.01;
        percentage = 0.00;
        
        account.setBalance(balance);
        
        double expResult = balance * percentage;
        double result = account.getInterest();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetInterest3LeftBound()
    {
        balance = 0.00;
        percentage = 0.03;
        
        account.setBalance(balance);
        
        double expResult = balance * percentage;
        double result = account.getInterest();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetInterest3RightBound()
    {
        balance = 100.00;
        percentage = 0.03;
        
        account.setBalance(balance);
        
        double expResult = balance * percentage;
        double result = account.getInterest();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetInterest5LeftBound()
    {
        balance = 100.01;
        percentage = 0.05;
        
        account.setBalance(balance);
        
        double expResult = balance * percentage;
        double result = account.getInterest();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetInterest5RightBound()
    {
        balance = 999.99;
        percentage = 0.05;
        
        account.setBalance(balance);
        
        double expResult = balance * percentage;
        double result = account.getInterest();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetInterest7LeftBound()
    {
        balance = 1000.00;
        percentage = 0.07;
        
        account.setBalance(balance);
        
        double expResult = balance * percentage;
        double result = account.getInterest();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetInterest7RightBound()
    {
        balance = 999999.99;
        percentage = 0.07;
        
        account.setBalance(balance);
        
        double expResult = balance * percentage;
        double result = account.getInterest();
        assertEquals(expResult, result, 0.0);
    }
}