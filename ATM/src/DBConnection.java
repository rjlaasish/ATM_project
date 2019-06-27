import java.sql.*;

public class DBConnection {
	public Connection DBConnect() throws Exception {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("mysql:jdbc://localhost:3306/atm","root","");
	Statement st=con.createStatement();
	return con;
	
	}
	

}
