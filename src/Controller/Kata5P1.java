package Controller;

import View.MailListReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class Kata5P1 {

    public static void main(String[] args) {
        String query = "CREATE TABLE IF NOT EXISTS A(\n"
                     + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                     + " mail text NOT NULL);";
        createTable(query);
        String fileName = new String("email.txt");
        List<String> mailList = MailListReader.read(fileName);
        truncate();
        for (String mail : mailList) {
            insert(mail);
        }
    }
    
    public static void selectAll(){
        String sql="SELECT * FROM PEOPLE";
        try {
            Connection con = connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                System.out.println(rs.getInt("ID") + "\t" + 
                                   rs.getString("Name") + "\t" +
                                   rs.getString("Apellidos") + "\t" +
                                   rs.getString("Departamento") + "\t" );

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static Connection connect() {
        Connection con = null;
        try {
            String url = "jdbc:sqlite:kata5.db";
            con = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }

    private static void createTable(String query) {
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Tabla creada");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void insert(String email) {
        try {
            String sql = "INSERT INTO A(mail) VALUES(?)";
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void truncate() {
        try {
            String sql = "delete from A";
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
