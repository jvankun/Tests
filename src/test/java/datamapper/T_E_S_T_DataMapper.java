package datamapper;

import com.mysql.cj.jdbc.MysqlDataSource;
import database.datamapper.DataMapper;
import database.entity.User;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;

public class T_E_S_T_DataMapper
{
    DataMapper dm;

    public T_E_S_T_DataMapper()
    {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://localhost:3306/testdb");
        ds.setUser("root");
        ds.setPassword("admin");

        dm = new DataMapper(ds);
    }

    @Before
    public void setUp() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver") ;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?allowMultiQueries=true&useSSL=false", "root", "admin") ;
        Statement statement = connection.createStatement() ;
        
        BufferedReader sql = new BufferedReader(new InputStreamReader(new FileInputStream("testdb.sql"), "UTF-8"));
        String sqlStatements = "";
        String line;
        while ((line = sql.readLine()) != null)
        {
            sqlStatements += line;
        }
        
        statement.executeUpdate(sqlStatements);
    }

    @Test
    public void testDataMapperGet() throws Exception
    {
        System.out.println("testDataMapperGet");

        assertEquals(1, dm.getUser(1).getId());
        assertEquals(true, dm.getUser("Anders And").getUsername().equals("Anders And"));
        assertEquals(8, dm.getUsers().size());
        assertEquals(2, dm.getUsers("and").size());
    }

    @Test
    public void testDataMapperCreate() throws Exception
    {
        System.out.println("testDataMapperCreate");

        dm.createUser(new User("Chip", "Chap", false));

        assertEquals("Chap", dm.getUser("Chip").getPassword());
    }
}