import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlKodiky {
	
static Connection conn = databazeSQL.getDBConnection();
	
	public static boolean createTableR()
	{
		
	     if (conn==null)
	           return false;
	     String sql = "CREATE TABLE IF NOT EXISTS romany (" + "nazev varchar(255) NOT NULL PRIMARY KEY," + "autor varchar(255) NOT NULL," + "rokvydani int, " + "dostupnost boolean, " + "zanr varchar(255)" + ")";
	     String createUniqueIndex = "CREATE UNIQUE INDEX IF NOT EXISTS unique_nazev ON romany (nazev);";
	     try{
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            stmt.execute(createUniqueIndex);
	            return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    return false;
	}
	
	public static boolean createTableU()
	{
		
	     if (conn==null)
	           return false;
	    String sql = "CREATE TABLE IF NOT EXISTS ucebnice (" + "nazev varchar(255) NOT NULL PRIMARY KEY," + "autor varchar(255) NOT NULL," + "rokvydani int, " + "dostupnost boolean, " + "vekovakategorie varchar(255)" + ")";
	    String createUniqueIndex = "CREATE UNIQUE INDEX IF NOT EXISTS unique_nazev ON ucebnice (nazev);";
	    try{
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            stmt.execute(createUniqueIndex);
	            return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    return false;
	}

	@SuppressWarnings({ "static-access" })
	public static void nactiRomanyQuery(DatabazeKnih ulozeneKnihy) {
		
					Connection conn = databazeSQL.getDBConnection();
					String loadQuery ="SELECT * FROM romany";
		           try  (PreparedStatement pstmt = conn.prepareStatement(loadQuery);
		        	   ResultSet rs = pstmt.executeQuery()) {
		        	        while (rs.next()) {
		        	            String nazev = rs.getString("nazev");
		        	            String autor = rs.getString("autor");
		        	            int rokVydani = rs.getInt("rokvydani");
		        	            boolean dostupnost = rs.getBoolean("dostupnost");
		        	            Romany.Zanry zanr = Romany.Zanry.valueOf(rs.getString("zanr"));
		        	            ulozeneKnihy.getUlozeneRomany().put(nazev, new Romany(nazev, autor, rokVydani, dostupnost, zanr));
		        	        }
		                   }
		               catch (SQLException e) {
	                       System.out.println(e.getMessage());
		               }
		           }
	
	@SuppressWarnings({ "static-access" })
	public static void nactiUcebniceQuery(DatabazeKnih ulozeneKnihy) {
		
		Connection conn = databazeSQL.getDBConnection();
		String loadQuery ="SELECT * FROM ucebnice";
       try  (PreparedStatement pstmt = conn.prepareStatement(loadQuery);
    	   ResultSet rs = pstmt.executeQuery()) {
    	        while (rs.next()) {
    	            String nazev = rs.getString("nazev");
    	            String autor = rs.getString("autor");
    	            int rokVydani = rs.getInt("rokvydani");
    	            boolean dostupnost = rs.getBoolean("dostupnost");
    	            Ucebnice.VekovaKategorie vekovakategorie = Ucebnice.VekovaKategorie.valueOf(rs.getString("vekovakategorie"));
    	            ulozeneKnihy.getUlozeneUcebnice().put(nazev, new Ucebnice(nazev, autor, rokVydani, dostupnost, vekovakategorie));
    	        }
               }
           catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }
					
			
	
	public static void ulozRomanyQuery(Romany kniha) {
		
			Connection conn = databazeSQL.getDBConnection();
			String saveQuery ="INSERT OR REPLACE INTO romany (nazev, autor, rokvydani, dostupnost, zanr) VALUES (?, ?, ?, ?, ?)";
           try (PreparedStatement prStmt  = conn.prepareStatement(saveQuery)) {
             	prStmt.setString(1, kniha.getNazev());
                prStmt.setString(2, kniha.getAutor());
                prStmt.setInt(3, kniha.getRokVydani());
                prStmt.setBoolean(4, kniha.getDostupnost());
                prStmt.setString (5, kniha.getZanry().toString());
                
                prStmt.executeUpdate();
             
        } 
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		}
	
	public static void ulozUcebniceQuery(Ucebnice kniha) {
		
		Connection conn = databazeSQL.getDBConnection();
		String saveQuery ="INSERT OR REPLACE INTO ucebnice (nazev, autor, rokvydani, dostupnost, vekovakategorie) VALUES (?, ?, ?, ?, ?)";
       try (PreparedStatement prStmt  = conn.prepareStatement(saveQuery)) {
         	prStmt.setString(1, kniha.getNazev());
            prStmt.setString(2, kniha.getAutor());
            prStmt.setInt(3, kniha.getRokVydani());
            prStmt.setBoolean(4, kniha.getDostupnost());
            prStmt.setString(5, kniha.getVekovaKategorie().toString());
            
            prStmt.executeUpdate();
         
    } 
	catch (SQLException e) {
        System.out.println(e.getMessage());
    }
	}
	
	public static void smazRomanyQuery() {
		Connection conn = databazeSQL.getDBConnection();
		String deleteQuery ="DELETE FROM romany";
		try (PreparedStatement prStmt = conn.prepareStatement(deleteQuery)) {
			prStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void smazUcebniceQuery() {
		Connection conn = databazeSQL.getDBConnection();
		String deleteQuery ="DELETE FROM ucebnice";
		try (PreparedStatement prStmt = conn.prepareStatement(deleteQuery)) {
			prStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
