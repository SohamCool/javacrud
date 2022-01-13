
package crudopjava;
import java.util.*;
import java.sql.*;
public class DBConnection{
	public static void main(String[] args) {
		DBConnection conobj = new DBConnection();
	}
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
			System.out.println("Connection Established...");
		}catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}
}

