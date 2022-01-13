package crudopjava;
import java.sql.*;
import java.util.Scanner;

public class Menu {
	Connection con = null;
	PreparedStatement pt = null;
	Statement st = null;
	ResultSet rs = null;
	
	public static void main(String[] args) throws SQLException {
		Menu menuobj = new Menu();
		Scanner sc = new Scanner(System.in);
		int ch = 0;
		int s1;
		String s2,s3,s4;
		do {
			System.out.println("Menu...");
			System.out.println("1. Insert Student data into the Student table.");
			System.out.println("2. Update Student data of the Student table.");
			System.out.println("3. Delete Student data from the Student table.");
			System.out.println("4. Get the list of all Students.");
			System.out.println("5. Search Student data by ID.");
			System.out.println("0. Exit");
			System.out.print("Choose any of the above...");
			ch = sc.nextInt();
			System.out.println("_________________________________________");
			System.out.println();
		
		switch(ch) {
		case 1:	System.out.print("Enter the Student ID number: ");
				s1=sc.nextInt();
				sc.nextLine();
				System.out.print("Enter the Name of Student: ");
				s2=sc.nextLine();
				System.out.print("Enter the Students's Date Of Birth: ");
				s3=sc.next();
				System.out.print("Enter the Student's Date Of Joining: ");
				s4=sc.next();
				menuobj.createStudent(s1, s2, s3, s4);
				break;
				
		case 2: System.out.print("Enter the Student ID number: ");
				s1=sc.nextInt();
				System.out.println("Enter the Updated* data of Student...");
				sc.nextLine();
				System.out.print("Name of Student: ");
				s2=sc.nextLine();
				System.out.print("Students's Date Of Birth: ");
				s3=sc.next();
				System.out.print("Student's Date Of Joining: ");
				s4=sc.next();
				menuobj.updateStudent(s1, s2, s3, s4);
				break;
		case 3:	System.out.println("Enter the Student ID to delete the record: ");
				s1 = sc.nextInt();
				menuobj.deleteStudent(s1);
			
		case 4:	menuobj.displayList();
				break;
		
		case 5:	menuobj.searchStudent();
				break;
		
		case 0: System.exit(0);
		default: System.out.println("Wrong Choice!");
		}
		}while(ch!=0);
		
		
	}
	public void createStudent(int sno, String sname, String sdob, String sdoj) {
		DBConnection conobj = new DBConnection();
		con = conobj.getConnection();
		
		try {
			pt = con.prepareStatement("insert into STUDENT (STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) values(?,?,?,?)");
			pt.setInt(1, sno);
			pt.setString(2, sname);
			pt.setString(3, sdob);
			pt.setString(4, sdoj);
			pt.executeUpdate();
			System.out.println(sname+"'s data is added successfully...");
			System.out.println("_________________________________________");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateStudent(int sno, String sname, String sdob, String sdoj) {
		DBConnection conobj = new DBConnection();
		con = conobj.getConnection();
		try {
			pt = con.prepareStatement("update STUDENT set STUDENT_NAME=?,STUDENT_DOB=?,STUDENT_DOJ=? where STUDENT_NO=?");
			pt.setString(1, sname);
			pt.setString(2, sdob);
			pt.setString(3, sdoj);
			pt.setInt(4, sno);
			pt.executeUpdate();
			System.out.println(sname+"'s data is Updated successfully...");
			System.out.println("_________________________________________");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteStudent(int id) throws SQLException {
		DBConnection conobj = new DBConnection();
		con = conobj.getConnection();
		pt = con.prepareStatement("delete from STUDENT where STUDENT_NO=?");
		pt.setInt(1, id);
		pt.executeUpdate();
		System.out.println("Student record is deleted successfully!");
		System.out.println("_________________________________________");
	}
	
	public void displayList() throws SQLException {
		DBConnection conobj = new DBConnection();
		con = conobj.getConnection();
		st = con.createStatement();
		rs = st.executeQuery("select * from STUDENT");
		ResultSetMetaData rsmd = rs.getMetaData();
		int colno = rsmd.getColumnCount();
		System.out.println("Student List...");
		while(rs.next()) {
			for(int i=1; i < colno; i++)
			System.out.println(rs.getString(i)+"  ");
			System.out.println();
		}
		System.out.println("_________________________________________");

	}
	
	public void searchStudent() throws SQLException {
		DBConnection conobj = new DBConnection();
		con = conobj.getConnection();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Student ID: ");
		int id = sc.nextInt();
		pt = con.prepareStatement("select * from STUDENT where STUDENT_NO=?");
		pt.setInt(1, id);
		
		rs = pt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colno = rsmd.getColumnCount();
		System.out.println("Search results...");
		while(rs.next()) {
			for(int i=1; i < colno; i++)
			System.out.println(rs.getString(i)+"  ");
			System.out.println();
		}
		System.out.println("_________________________________________");

	}
}
