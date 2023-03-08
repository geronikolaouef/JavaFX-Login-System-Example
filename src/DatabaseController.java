import java.sql.*;

public class DatabaseController 
{
    private Statement stmt;
    private ResultSet rs;
    private Connection conn;

    public DatabaseController()
    {
        try
        {
            // connection to the database
            final String db_url = "jdbc:mysql://localhost:3306/java_test";
            final String db_username = "root";
            final String db_password = "";
            conn = DriverManager.getConnection(db_url, db_username, db_password);
            
            // create a statement object
            stmt = conn.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet exec(String query) throws SQLException
    {       
        // execute query and get the results
        rs = stmt.executeQuery(query);

        return rs;
    }

    public void closeConn() throws SQLException
    {
        // close the connection, statement, and result set
        rs.close();
        stmt.close();
        conn.close();
    }
}
