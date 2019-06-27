import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.*;
import javax.swing.text.Document;

import java.io.FileNotFoundException;

public class pdf {






	//public void pdf()throws  Exception {
	public static void main(String[] args) throws Exception {
		
	
	int acc_no;
		Connection cn=new databaseConnection().getConnection();	
		Statement std=cn.createStatement();
		Scanner sc=new Scanner(System.in);
		acc_no=sc.nextInt();
		ResultSet rs=std.executeQuery("select * from user where account_no="+acc_no);
		rs.next();
		System.out.println(rs.getString("first_name"));
	}
}



