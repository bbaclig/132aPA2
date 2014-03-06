/** 
 * Group members: 
 * (1) Benigno Baclig, A10108582 
 * (2) Mimi Liu, A
 */ 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
 
public class PA2 { 
 
	public static void main(String[] args) { 
 
		Connection conn = null; 
 
		try { 
			// Load JDBC class 
			Class.forName("org.sqlite.JDBC"); 
			// Get the connection to the database 
			conn = DriverManager.getConnection("jdbc:sqlite:pa2.db"); 
			System.out.println("Opened database successfully"); 
 
			// Case #1: Modification 
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate("DROP TABLE IF EXISTS DBpre;"); 
			stmt.executeUpdate("CREATE TABLE DBpre (course VARCHAR(20));"); 
			stmt.executeUpdate("INSERT INTO DBpre VALUES ('Java Prog');"); 
 
			// Case #2: Query the Prerequisite table 
			ResultSet rset= stmt.executeQuery("SELECT * from Prerequisite;"); 
			// Print the Course and Prereq columns 
			System.out.println ("\nStatement result:"); 
			while (rset.next()) { 
				System.out.print(rset.getString("Course")); 
				System.out.print("---"); 
				System.out.println(rset.getString("Prereq"));  
			} 
 
			// Case #3: Prepared statements 
			PreparedStatement pstmt= conn.prepareStatement("SELECT * FROM "
					+ "Prerequisite WHERE Course = ? OR Prereq = ?;"); 
			pstmt.setString (1, "Database"); 
			pstmt.setString (2, "Database"); 
			rset= pstmt.executeQuery (); 
			System.out.println ("\nPrepared statement result:"); 
			while (rset.next()) { 
				System.out.print(rset.getString("Course")); 
				System.out.print("---"); 
				System.out.println(rset.getString("Prereq")); 
			} 
 
			// close the result set, statement 
			rset.close(); 
			stmt.close(); 
		} catch (Exception e) { 
			throw new RuntimeException("There was a problem!", e); 
		} finally { 
			// I have to close the connection in the "finally" clause otherwise 
			// in case of an exception i would leave it open. 
			try { 
				if (conn != null) 
					conn.close(); 
			} catch (SQLException e) { 
				throw new RuntimeException("Help! I could not close the "
						+ "connection!!!", e); 
			} 
		} 
	}
}