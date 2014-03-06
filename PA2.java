/**
 * Group members:
 * (1) 
 * (2) Nhu-Quynh Liu, A10937319
 */

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
      conn = DriverManager.getConnection("jdbc:sqlite:pa2.db");
      System.out.println("Opened database successfully");

      Statement stmt = conn.createStatement();

      stmt.executeUpdate("DROP TABLE IF EXISTS QuartersToGraduation;");
      stmt.executeUpdate("CREATE TABLE QuartersToGraduation( Student char(32), Quarters int DEFAULT 0);");
      stmt.executeUpdate("INSERT INTO QuartersToGraduation (Student) " +
                         "SELECT DISTINCT Student " +
                         "FROM Record;");

/*
      stmt.executeUpdate("CREATE TABLE AllCourses( Course char(32) );");
      stmt.executeUpdate("INSERT INTO AllCourse( Course ) " +
                         "SELECT * FROM Core UNION SELECT * FROM Elective " +
                         "FROM Record;");*/

      String coreQuery = "INSERT INTO Record (Student, Course) "  +
                         "SELECT DISTINCT r1.Student, c.Course " +
                         "FROM ( SELECT * FROM Core UNION ALL SELECT * FROM Elective ) c, Record r1 " +
                         "WHERE NOT EXISTS ( " +
                         "SELECT Course " +
                         "FROM Prerequisite AS p " +
                         "WHERE p.course = c.Course " +
                         "AND NOT EXISTS( " +
                         "SELECT Course " +
                         "FROM Record AS r2 " +
                         "WHERE r2.student = r1.student AND p.prereq = r2.course)) " +
                         "AND NOT EXISTS ( " +
                         "SELECT Course FROM Record AS r3 " +
                         "WHERE r1.Student = r3.Student " +
                         "AND c.course = r3.course)";

      int delta = 0;

      do {
        /*
        stmt.executeUpdate("UPDATE QuartersToGraduation " +
                           "SET Quarters = Quarters + 1 " +
                           "WHERE Student IN ( SELECT Student FROM (" + coreQuery + "));");*/

        stmt.executeUpdate("DELETE FROM Record " +
                           "WHERE Student IN ( " +
                           "SELECT Student FROM Record r1 " +
                           "WHERE NOT EXISTS( " +
                           "SELECT Course FROM Core " +
                           "WHERE NOT EXISTS( " +
                           "SELECT Course FROM Record r2 " +
                           "WHERE r2.Course = Core.course AND r2.Student = r1.Student)))" +
                           "AND Student IN ( " + 
                           "SELECT Student FROM (Elective LEFT JOIN Record) c " +
                           "GROUP BY Student " +
                           "HAVING COUNT(c.Course) >= 5);");                          

        delta = stmt.executeUpdate("UPDATE QuartersToGraduation " +
                           "SET Quarters = Quarters + 1 " +
                           "WHERE Student IN ( SELECT Student FROM Record);");
        
        stmt.executeUpdate( coreQuery );

        /*
        delta = stmt.executeUpdate("INSERT INTO Record (Student, Course) " +
                                                                    coreQuery);
        */

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
