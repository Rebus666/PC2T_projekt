import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databazeSQL {
	
	  private static volatile Connection dbConnection;

	  private databazeSQL() {}

	  public static Connection getDBConnection() {
	    if (dbConnection == null) {
	      synchronized (databazeSQL.class) {
	        if (dbConnection == null) {
	          try {
	            Class.forName("org.sqlite.JDBC");
	            dbConnection = DriverManager.getConnection("jdbc:sqlite:db/knihy.db");
	          } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	          }
	        }
	      }
	    }
	    return dbConnection;
	  }

	  public static void closeConnection() {
	    try {
	      dbConnection.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }

}