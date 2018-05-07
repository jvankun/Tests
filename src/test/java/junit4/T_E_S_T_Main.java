package junit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import main.Main;
import static org.junit.Assert.*;

public class T_E_S_T_Main
{
    
    public T_E_S_T_Main()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testSomeMethod()
    {
        System.out.println("testSomeMethod");
       
        assertEquals(true, new Main().someMethod("abc").equals("abcabc"));
    }
}