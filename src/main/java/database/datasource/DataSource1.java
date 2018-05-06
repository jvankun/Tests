package database.datasource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataSource1
{
    private MysqlDataSource dataSource = new MysqlDataSource();
    
    public DataSource1()
    {
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("testdb");
        dataSource.setUser("root");
        dataSource.setPassword("admin");
    }
    
    public MysqlDataSource getDataSource()
    {
        return dataSource;
    }
}