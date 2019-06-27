import java.io.FileOutputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class atmdetail {
	int count=0;
	public void withdraw() throws SQLException, ClassNotFoundException {
		
		
		int account_num;
		int pin;
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your account number: ");
		account_num=sc.nextInt();
		System.out.println("Enter the pin number:");
		pin=sc.nextInt();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
		Statement stat=cn.createStatement();
		
		String sq="Select * from user where account_no="+account_num;
		ResultSet rs=stat.executeQuery(sq);
		rs.next();
			if(account_num==rs.getInt("account_no")&&pin==rs.getInt("pin_no")) {
				System.out.println("Enter the amount you want to withdraw: ");
				int amount=sc.nextInt();
				Long balance=rs.getLong("balance");
					if(amount>balance) {
						System.out.println("InSufficient Balance: ");
						System.out.println("Please enter valid balance!!!");
						withdraw();
									}
					else {
						Long total;
						total=balance-amount;
						stat.executeUpdate("update user set balance="+total+" where account_no="+account_num);
						System.out.println("Withdrawn Sucessfully: ");
						Date thisdate=new Date();
						SimpleDateFormat form=new SimpleDateFormat("MMMM-dd-Y hh:mm a");
						String date =form.format(thisdate);
						
						String sql="insert into a"+account_num+" values("+account_num+",2,"+amount+",'"+date+"')";
						PreparedStatement st=cn.prepareStatement(sql);
						st.executeUpdate();
						
						}
												}
			else {
				System.out.println("Incorrect account number or password:");
				System.out.println("Please try again with correct information:\n");
				
				count++;
				if(count<3) {
					withdraw();	
				}
					else {
						System.out.println("You have attemped to login with wrong information many times:");
						System.exit(1);
					
					}
				}
				
		
		}
	
			
public void ministatement() throws Exception {
	
		Scanner sc=new Scanner(System.in);
	
	int acc_no = 0;
	String name1="";
	try {
	System.out.println("Enter account no.: ");
	acc_no=sc.nextInt();
	//Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
	}catch(Exception e) {
		System.out.println("Incorrect input: ");
		System.out.println("Re-enter account no.: ");
		acc_no=sc.nextInt();
		
	}	
	Connection cn=new databaseConnection().getConnection();	
	Statement std=cn.createStatement();
	
	ResultSet rs=std.executeQuery("select * from user where account_no="+acc_no);
	rs.next();
	//if(acc_no==rs.getInt("account_no")) {
	String accholder_name="";
	accholder_name=rs.getString("first_name");
	System.out.println("Is that you: "+accholder_name);
	System.out.println("If yes than press y or Y:");
	char choi;

	choi=sc.next().charAt(0);
	ResultSet rs1=std.executeQuery("select * from a"+acc_no+" where account_no="+acc_no);
	if(choi=='Y'||choi=='y') {
		int count=0;
		while(rs1.next()) {
				count++;
				}
			int b=count-4;
			//System.out.println(b);
			
			if(b>=4) {
	
			ResultSet rs2=std.executeQuery("select * from a"+acc_no+" where account_no="+acc_no);
			
				 int counter=0;
				while(rs2.next()){
					counter++;
                  //  System.out.println("Check");
					if( counter>b){
					//for(int i=b;i<=count;b++)
						
						{ 
			int check=rs2.getInt("about");
			{
				if(check==1) {
				int bal=rs2.getInt("hisamount");
				String sq="select * from a"+acc_no+" where sn=1";
				ResultSet rs5=std.executeQuery(sq);
				rs.next();
				String date1=rs5.getString("date");
				System.out.println(date1);

				System.out.println("You deposited : "+bal+" on "+date1);
					}
			else {
				int bal1=rs2.getInt("hisamount");
				String date=rs2.getString("time");
				System.out.println("You withdraw : "+bal1+" on "+date);
				
						}
			}
	
					}
			
	}
					
			}
				
				}
			else {
				ResultSet rs3=std.executeQuery("select * from a"+acc_no+" where account_no="+acc_no);
				while(rs3.next()) {
				
			int check=rs3.getInt("about");
			{
				
			if(check==1) {
				int bal=rs3.getInt("hisamount");
				System.out.println("You deposited : "+bal);
					}
			else {
				int bal1=rs3.getInt("hisamount");
				System.out.println("You withdraw : "+bal1);
				
			}
		}
	
	}
			
			}
	}
	System.out.println("Do you want to send email:");
System.out.println("Enter Y/y");
	choi=sc.next().charAt(0);
	
	if(choi=='Y'||choi=='y') {
		Connection cn1=new databaseConnection().getConnection();	
		Statement std1=cn.createStatement();
		
		ResultSet rs4=std.executeQuery("select * from user where account_no="+acc_no);
		rs4.next();
			
		String hell="a"+acc_no;
		String file_name="C:\\Users\\Rijal Aasish\\Desktop\\Java\\ATM\\pdf_sec\\hell.pdf";
		Document document=new Document();
		PdfWriter .getInstance(document, new FileOutputStream(file_name));
		document.open();
		
		document.add(Image.getInstance("C:\\Users\\Rijal Aasish\\Desktop\\Logo2.png"));	
		document.add(new Paragraph("  "));
		//document.add(new Paragraph("  "));
		Date thisdate=new Date();
		SimpleDateFormat form=new SimpleDateFormat("MMMM-dd-Y hh:mm a");
		String date =form.format(thisdate);
		Paragraph para = new Paragraph(date);
		para.setAlignment(Element.ALIGN_RIGHT);
		//para.setSpacingAfter(10f);
		document.add(para);
		Paragraph par=new Paragraph("   Account name:  "+rs4.getString("first_name")+" "+rs4.getString("last_name"));
		document.add(par);
		par.setAlignment(Element.ALIGN_LEFT);
		Paragraph pa=new Paragraph("Account number:  "+rs4.getString("account_no"));
		document.add(pa);
		pa.setAlignment(Element.ALIGN_LEFT);
		
		
		
		
		document.close();
	}	
	
	else {
	System.out.println("Wrong Input:");	
	}
	}
	
	
	
	
	
	
	
	
public void changepin() throws ClassNotFoundException, SQLException {

	int act_num=0,pin_num=0, pin_num1=0,pin_num2=0;
	Scanner sc=new Scanner(System.in);
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
	Statement st=con.createStatement();
	System.out.println("Enter the account number");
	
	act_num=sc.nextInt();
	System.out.println("Enter the pin number");
	pin_num=sc.nextInt();	
	String sq="Select * from user where account_no="+act_num;
	ResultSet rs=st.executeQuery(sq);
	rs.next();
	if(act_num==rs.getInt("account_no")&&pin_num==rs.getInt("pin_no")) {
		System.out.println("Enter 4 digit new pin number: ");
		pin_num1=sc.nextInt();
		//int result = pin_num1 / (int)Math.pow(10, (int)(Math.log10(pin_num1) - 1));
		System.out.println("Confirm pin number: ");
		pin_num2=sc.nextInt();
		//int result1 = pin_num2 / (int)Math.pow(10, (int)(Math.log10(pin_num2) - 1));
		if(pin_num1==pin_num2) {
			st.executeUpdate("update user set pin_no="+pin_num2+" where account_no="+act_num);
			System.out.println("Pin number Sucessfully changed: ");
			System.out.println("Your new pin number is: "+pin_num2);
		}
	}
		else {
			System.out.println("Incorrect account number or password:");
			System.out.println("Please try again with correct information:\n");
			
			count++;
			if(count<3) {
				changepin();	
			}
				else {
					System.out.println("You have attemped to login with wrong information many times:");
					System.exit(1);
				
				}
		}
	

	}
public void balanceinquiry() throws ClassNotFoundException, SQLException {

	int act_num;
		int pin_num;
	Scanner sc=new Scanner(System.in);
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
	Statement st=con.createStatement();
	System.out.println("Enter the account number");
	act_num=sc.nextInt();
	System.out.println("Enter the pin number");
	pin_num=sc.nextInt();	
	String sq="Select * from user where account_no="+act_num;
	ResultSet rs=st.executeQuery(sq);
	rs.next();
	if(act_num==rs.getInt("account_no")&&pin_num==rs.getInt("pin_no")) {
	System.out.println("Your Current balance is: "+rs.getLong("balance"));
		}	
	else {
		System.out.println("Incorrect account number or password:");
		System.out.println("Please try again with correct information:\n");
		
		count++;
		if(count<3) {
			changepin();	
		}
			else {
				System.out.println("You have attemped to login with wrong information many times:");
				System.exit(1);
			
			}
		}

		
}

	
void emailpdf() {
	String file_name="";
	
	
	
	}	
}	

	






