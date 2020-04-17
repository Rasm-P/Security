package dk.cphbusiness.soft.sqlinject;

import static dk.cphbusiness.soft.sqlinject.PlaceHolders.field;
import static dk.cphbusiness.soft.sqlinject.PlaceHolders.string;
import static dk.cphbusiness.soft.sqlinject.PlaceHolders.stringList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppMain {

    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        try {
          String id = "2 or 1=1"; 
          String name = "Jens' or ''='"; 
          injectSimpleStatement( id, name );
          injectPreparedStatement( id, name );
          }
        catch (Exception e) { e.printStackTrace(); }
        
    }
    
    private static void sortExample(String sortKey) {
      String sql = "select * from junk order by "+field("name", "id")+";";
      }
    
    private static void inExample(String... options) {
      String sql = "select * from junk where name in "+stringList(options)+";";
      }

    private static void injectSimpleStatement( String id, String name ) throws SQLException, ClassNotFoundException {
        System.out.println( "Simple inject" );
        try ( Connection con = getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM junk WHERE id=" + id + " and name ='" + string(name) + "'" ) ) {
            while ( rs.next() ) {
                System.out.println( "--> " + rs.getInt( "id" )
                        + " " + rs.getString( "name" )
                        + " " + rs.getString( "role" ) );
            }
        }
    }

    private static void injectPreparedStatement( String id, String name ) throws SQLException, ClassNotFoundException {
        System.out.println( "Prepared statement" );
        try ( Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement( "SELECT * FROM junk WHERE id=? and name =?" ) ) {
            stmt.setInt( 1, Integer.parseInt( id ) );
            stmt.setString( 2, name );
            ResultSet rs = stmt.executeQuery();
            while ( rs.next() ) {
                System.out.println( "--> " + rs.getInt( "id" )
                        + " " + rs.getString( "name" )
                        + " " + rs.getString( "role" ) );
            }
        }

    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName( "org.sqlite.JDBC" );
        String path = "/Users/AKA/DatSoftLyngby/SecurityFall2018/week2SQLinject/tmpinject.db";
        return DriverManager.getConnection( "jdbc:sqlite:" + path );
    }

}
