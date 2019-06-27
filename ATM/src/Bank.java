import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Bank{
	long balance;
Scanner SC=new Scanner(System.in);
public void DBConnection() throws ClassNotFoundException, SQLException {
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
Statement st=con.createStatement();
}
	public void createaccount() throws Exception {

		String first_name="";
		String last_name="";
		String address="";
		String email="";
		String phone_no="";
		
		String query="insert into user values(?,?,?,?,?,?,?,?) ";
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
		Connection cn=new databaseConnection().getConnection();	
		PreparedStatement st=cn.prepareStatement(query);
		try {	
		System.out.println("Enter your first name:");
			first_name=SC.nextLine();
		}catch(Exception e) {	
			System.out.println("!!! Incorrect input: !!!");
			System.out.println("Re-enter your first name:");
			first_name=SC.nextLine();
		}
		try {
			System.out.println("Enter your last name:");
			last_name=SC.nextLine();
		}catch(Exception e) {	
			System.out.println("!!! Incorrect input: !!!");
			System.out.println("Re-enter your last name:");
			last_name=SC.nextLine();
		}
		try {
			System.out.println("Enter your Address:");
			address=SC.nextLine();
		}catch(Exception e) {
			System.out.println("!!! Incorrect input: !!!");
			System.out.println("Re-enter your Address:");
			address=SC.nextLine();
		}
		try {
			System.out.println("Enter your contact number:");
			phone_no=SC.nextLine();
		}catch(Exception e) {
			System.out.println("!!! Incorrect input: !!!");
			System.out.println("Re-enter your contact number:");
			phone_no=SC.nextLine();
		}	
		try {
			System.out.println("Enter your email:");
			email=SC.nextLine();
		}catch(Exception e) {
			System.out.println("!!! Incorrect input: !!!");
			System.out.println("Re-enter your email:");
			email=SC.nextLine();
		}
			balance = 0;
			Random rnd=new Random();
			int pin=1000+rnd.nextInt(10000-1000);
			st.setInt(1,0);
			st.setInt(2,pin);
			st.setString(3,first_name);
			st.setString(4,last_name);
			st.setString(5,address);
			st.setString(6,phone_no);
			st.setString(7,email);
			st.setLong(8,balance);
			st.executeUpdate();
			
			ResultSet rs= st.executeQuery("select account_no from user where email='"+email+"'");
			rs.next();
			int hist =rs.getInt("account_no");
			String sql="create table a"+hist+" (account_no int(3), about int(2), hisamount int(7),time varchar(30))";
            PreparedStatement create=cn.prepareStatement(sql);
            create.executeUpdate();
			System.out.println("Your account is sucessfully created:\n");
			System.out.println("\nAccount Details:");
			System.out.println("\nFirst Name: "+first_name+"\nLast_name: "+last_name+"\nAddress: "+address+"\nContact No."+phone_no+"");
			System.out.println("Email: "+email+"\nPin Number: "+pin+"\nBalance: "+balance+"\nAccoount No.: "+rs.getInt("account_no"));
		
	}
	
		
	

	
	public void deposit() throws Exception {

		long totalamt;
		int depositamt;
		char select;
		int acc_no;
		String name1="";
		System.out.println("Do you already have an account at Mero bank Ltd.:(Y/N)");
		select=SC.next().charAt(0);
		if(select=='N'||select=='n') {
			createaccount();
			}
		try {
		System.out.println("Enter account no.: ");
		acc_no=SC.nextInt();
		}catch(Exception e) {
			System.out.println("!!! Incorrect input: !!!");
			System.out.println("Re-enter account no.: ");
			acc_no=SC.nextInt();
		}
		Connection cn=new databaseConnection().getConnection();	
		Statement std=cn.createStatement();
		ResultSet rs=std.executeQuery("select * from user where account_no="+acc_no);
		rs.next();
		//if(acc_no==rs.getInt("account_no")) {
		
		String accholder_name=rs.getString("first_name");
		System.out.println("Is that you: "+accholder_name);
		System.out.println("If yes than press y or Y:");
		char choi;
		choi=SC.next().charAt(0);
		if(choi=='Y'||choi=='y') {
			try {
			System.out.println("Enter amount to deposit: ");
			depositamt=SC.nextInt();
			}catch(Exception e) {
				System.out.println("!!! Incorrect input: !!!");
				depositamt=SC.nextInt();
			}
			totalamt=rs.getLong("balance")+depositamt;
			std.executeUpdate("update user set balance="+totalamt+" where account_no="+acc_no);
			System.out.println("Your total balance is: "+totalamt+"\n");
			Date thisdate=new Date();
			SimpleDateFormat form=new SimpleDateFormat("MMMM-dd-Y hh:mm a");
			String date =form.format(thisdate);
			//System.out.println("\t\t\t\t"+date);
			String sql="insert into a"+acc_no+" values("+acc_no+",1,"+depositamt+",'"+date+"')";
			Statement st=cn.createStatement();
			st.executeUpdate(sql);
		
			
			}

	}
	}
		

		
	
