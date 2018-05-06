package mockito;

import database.datamapper.DataMapper;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class T_E_S_T_Mockito
{
    MysqlDataSource ds;
    Connection c;
    PreparedStatement stmt;
    ResultSet rs;
    
    DataMapper dm;

    @Before
    public void setUp() throws Exception
    {
        ds = mock(MysqlDataSource.class);
        c = mock(Connection.class);
        stmt = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
        
        dm = new DataMapper(ds);
        
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("user_id")).thenReturn(1);
        when(rs.getString("username")).thenReturn("Anders And");
        when(rs.getString("password")).thenReturn("1234");
        when(rs.getBoolean("admin")).thenReturn(true);
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testMockitoGetUserTimes() throws Exception
    {
        System.out.println("testMockitoGetUserTimes");
        
        dm.getUser(1);

        verify(ds, times(1)).getConnection();
        verify(c, times(1)).close();
    }
    
    @Test
    public void testMockitoGetUserId() throws Exception
    {
        System.out.println("testMockitoGetUserId");
        
        assertEquals(1, dm.getUser(1).getId());
    }
    
    @Test
    public void testMockitoGetUserName() throws Exception
    {
        System.out.println("testMockitoGetUserName");
        
        assertEquals(true, dm.getUser("Anders And").getUsername().equals("Anders And"));
    }
}