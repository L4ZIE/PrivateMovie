package dal;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {
    private SQLServerDataSource dataSource;

    public DatabaseConnector(){
        dataSource= new SQLServerDataSource();
        dataSource.setDatabasename("MovieCollectionProject");
        dataSource.setUser("CSe22B_29");
        dataSource.setPassword("CSe22B_29");
        dataSource.setServerName("10.176.111.31");
        dataSource.setPortNumber(1433);
        dataSource.setTrustServerCertificate(true);
    }
    public Connection getConnection() throws SqlServerException{
        return dataSource.getConnection();
    }
    public static void main(String[] args) throws SQLException{
        DatabaseConnector databaseConnector = new DatabaseConnector();

        try(Connection connection = databaseConnector.getConnection()){
            System.out.println("Is it really open"+ !connection.isClosed());
        }
    }
}
