import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PA2 {
  public static void main( String[] args ) {
    Connection conn = null;
    
    try {

      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:pa2-2.db");
      System.out.println("Opened database successfully");

      Statement stmt = conn.createStatement();

      stmt.executeUpdate("DROP TABLE IF EXISTS QuartersToGraduation;");
      stmt.executeUpdate("CREATE TABLE QuartersToGraduation( Student char(32), Quarters int DEFAULT 0);");
      stmt.executeUpdate("INSERT INTO QuartersToGraduation (Student) " +
                         "SELECT Student " +
                         "FROM Record;");
      
      String coreQuery = "SELECT DISTINCT r1.Student, c.Course " +
                         "FROM Core c, Record r1 " +
                         "WHERE NOT EXISTS ( " +
                         "SELECT * " +
                         "FROM Prerequisite AS p " +
                         "WHERE p.course = c.Course " +
                         "AND NOT EXISTS( " +
                         "SELECT * " +
                         "FROM Record AS r2 " +
                         "WHERE r2.student = r1.student AND p.prereq = r2.course)) " +
                         "AND NOT EXISTS ( " +
                         "SELECT * FROM Record AS r3 " +
                         "WHERE r1.Student = r3.Student " +
                         "AND c.course = r3.course)";
      int delta = 0;

      do {
        /*
        stmt.executeUpdate("UPDATE QuartersToGraduation " +
                           "SET Quarters = Quarters + 1 " +
                           "WHERE Student IN (" + coreQuery + ");");*/
        
        delta = stmt.executeUpdate("INSERT INTO Record (Student, Course) " +
                                                                    coreQuery);

        System.out.println( delta );
      } while( delta != 0 );
          stmt.close();
    } catch ( Exception e ) {
      throw new RuntimeException("There was a problem!", e);
    } finally {
      try{
        if (conn != null) 
          conn.close();
      } catch (Exception e) {
        throw new RuntimeException( "Could not close the connection");
      }
    }

  }
}
