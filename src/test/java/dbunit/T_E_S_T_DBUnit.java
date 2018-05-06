package dbunit;

import database.datamapper.DataMapper;
import database.entity.User;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.io.FileInputStream;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;

public class T_E_S_T_DBUnit
{
    DataMapper dm;

    MysqlDataSource datasource;
    Connection connection;

    IDatabaseConnection dbConnection;
    IDataSet xmlDataSet;
    QueryDataSet databaseDataSet;
    ITable xmlTable, databaseTable;

    public T_E_S_T_DBUnit()
    {
        datasource = new MysqlDataSource();
        datasource.setURL("jdbc:mysql://localhost:3306/testdb");
        datasource.setUser("root");
        datasource.setPassword("admin");
        datasource.setDatabaseName("testdb");

        dm = new DataMapper(datasource);
    }

    @Before
    public void setUp() throws Exception
    {
        try
        {
            connection = datasource.getConnection();

            dbConnection = new DatabaseConnection(connection, "testdb");
            dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
        
            xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("DATASETS/dataset_Init.xml"));
            
            DatabaseOperation.CLEAN_INSERT.execute(dbConnection, xmlDataSet);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    @After
    public void tearDown() throws Exception
    {
	dbConnection.close();
    }

    @Test
    public void testDBUnitGetUsers() throws Exception
    {
        System.out.println("testDBUnitGetUsers");
        
        List<User> users = dm.getUsers();
        
        assertEquals(2, users.size());        
        assertEquals("Anders And", users.get(0).getUsername());        
        assertEquals(true, users.get(1).isAdmin());        
    }

    
    @Test
    public void testDBUnitGetUser() throws Exception
    {
        System.out.println("testDBUnitGetUser");

        User u = new User("Anders And", "1234", true);
        
        databaseDataSet = new QueryDataSet(dbConnection);
        databaseDataSet.addTable("user");
        databaseTable = databaseDataSet.getTable("user");
        
        assertEquals(u.getPassword(), databaseTable.getValue(0, "password"));
    }
    
    @Test
    public void testDBUnitCreateUser() throws Exception
    {
        System.out.println("testDBUnitCreateUser");
        
        User u = new User("Chip", "Chap", false);
        dm.createUser(u);
        
        databaseDataSet = new QueryDataSet(dbConnection);
        databaseDataSet.addTable("user");
        databaseTable = databaseDataSet.getTable("user");
        
        xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("DATASETS/dataset_Created-User.xml"));
        xmlTable = xmlDataSet.getTable("user");
        
        for(int row = 0; row < xmlTable.getRowCount(); row++)
        { 
            assertEquals(xmlTable.getValue(row, "username").toString(), databaseTable.getValue(row, "username").toString());
            assertEquals(xmlTable.getValue(row, "password").toString(), databaseTable.getValue(row, "password").toString());
        }
    }
}