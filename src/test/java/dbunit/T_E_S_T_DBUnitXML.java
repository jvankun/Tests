package dbunitTest;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class T_E_S_T_DBUnitXML {
    MysqlDataSource datasource;
    Connection connection;

    IDatabaseConnection dbConnection;
    IDataSet xmlDataSet;
    QueryDataSet databaseDataSet;
    ITable xmlTable, databaseTable;
    
    public T_E_S_T_DBUnitXML()
    {
        datasource = new MysqlDataSource();
        datasource.setURL("jdbc:mysql://localhost:3306/cupcakeshoptest");
        datasource.setUser("tester");
        datasource.setPassword("tester");
        datasource.setDatabaseName("cupcakeshoptest");
    }

    @Before
    public void setUp() throws Exception
    {
        try
        {
            connection = datasource.getConnection();

            dbConnection = new DatabaseConnection(connection, "cupcakeshoptest");
            dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
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
    public void testDBUnit_createXMLDTD() throws Exception
    {
        System.out.println("TESTDBUnit_createXML+DTD");

        databaseDataSet = new QueryDataSet(dbConnection);
        databaseDataSet.addTable("cupcakebottom");
        databaseDataSet.addTable("cupcaketopping");
        databaseDataSet.addTable("cupcake");
        databaseDataSet.addTable("user");
        databaseDataSet.addTable("invoice");
        databaseDataSet.addTable("invoicedetails");
        
        FlatXmlDataSet.write(databaseDataSet, new FileOutputStream("DATASETS/dataset_Topping.xml"));
        FlatDtdDataSet.write(databaseDataSet, new FileOutputStream("DATASETS/dataset_Topping.dtd"));
    }
    
    @Test
    public void testDBUnit_compareDataSets() throws Exception
    {
        System.out.println("TESTDBUnit_compareDataSets");

        databaseDataSet = new QueryDataSet(dbConnection);
        databaseDataSet.addTable("cupcakebottom");
        databaseDataSet.addTable("cupcaketopping");
        databaseDataSet.addTable("cupcake");
        databaseDataSet.addTable("user");
        databaseDataSet.addTable("invoice");
        databaseDataSet.addTable("invoicedetails");
        databaseTable = databaseDataSet.getTable("cupcaketopping");

        xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("DATASETS/dataset_Topping.xml"));
        xmlTable = xmlDataSet.getTable("cupcaketopping");

        Assertion.assertEquals(xmlTable, databaseTable);
    }
}