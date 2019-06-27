	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class databaseConnection {

		public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
		Statement st=cn.createStatement();
		return cn;
		
		}
		

	}


