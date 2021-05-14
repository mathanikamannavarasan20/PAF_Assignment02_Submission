package customerService;
import java.sql.*;

public class User {
		
	//A common method to connect to the DB
	private Connection connect() {
		 
			Connection con = null;
		 try {
		 
			 Class.forName("com.mysql.jdbc.Driver");
	
			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetgudget", "root", "AKAI@1997");
			 }
			catch (Exception e)
			 	{e.printStackTrace();}
			 return con;
			 }
	/*Insert user*/
	public String insertUser(String name, String email, String tel,String uname,String pwd) {
		 
			String output = "";
		 try
		 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for inserting."; }
				 // create a prepared statement
				 String query = " insert into user (`cid`,`name`,`email`,`tel`,`uname`,`pwd`)"
				 + " values (?, ?, ?, ?,?,?)";
				
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, name);
				 preparedStmt.setString(3, email);
				 preparedStmt.setString(4, tel);
				 preparedStmt.setString(5, uname);
				 preparedStmt.setString(6, pwd);
				
				
				 // execute the statement
				 
				 preparedStmt.execute();
				 con.close();
				 String newItems = readUser();
				 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
				 }
				 catch (Exception e)
				 {
				 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
				 System.err.println(e.getMessage());
				 } 
		 return output;
		 }
		
		/*read User*/
		public String readUser(){
		 
			String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Name</th><th>Email</th>" +
			 "<th>Telphone</th><th>UserName</th><th>Password</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
	
			 String query = "select * from user";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
		 while (rs.next()) {
		 
				 String cid = Integer.toString(rs.getInt("cid"));
				 String name = rs.getString("name");
				 String email = rs.getString("email");
				 String tel = rs.getString("tel");
				 String uname = rs.getString("uname");
				 String pwd = rs.getString("pwd");
				
				 // Add into the html table
				 output += "<tr><td>" +name + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + tel + "</td>";
				 output += "<td>" + uname + "</td>";
				 output += "<td>" + pwd + "</td>";
			
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-itemid='" + cid + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-itemid='" + cid + "'></td></tr>";
				 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
		 }
		 catch (Exception e) {
			 output = "Error while reading the users.";
			 System.err.println(e.getMessage());
			 }
		
		 return output;
		 }
		
		/*update User*/
		public String updateUser(String cid, String name, String email,String tel, String uname,String pwd)
		{
			 String output = "";
			 try {
			 
			 Connection con = connect();
			 if (con == null){
				 return "Error while connecting to the database for updating.";
				 }
				 // create a prepared statement
				 String query = "UPDATE `user` SET `name`=?,`email`=?,`tel`=?,`uname`=?,`pwd`=? WHERE `cid`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setString(1, name);
				 preparedStmt.setString(2, email);
				 preparedStmt.setString(3, tel);
				// preparedStmt.setDouble(3, Double.parseDouble(tel));
				 preparedStmt.setString(4, uname);
				 preparedStmt.setString(5, pwd);
				 preparedStmt.setInt(6, Integer.parseInt(cid));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 String newItems = readUser();
				 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
				 }
				 catch (Exception e)
				 {
				 output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
				 System.err.println(e.getMessage());
				 } 
		 return output;
		 }
		
		/*Delete User*/
		public String deleteUser(String cid) {
			 String output = "";
			try{
			  Connection con = connect();
			  if (con == null){
				  return "Error while connecting to the database for deleting."; 
				  }
					 // create a prepared statement
					 String query = "delete from user where cid=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(cid));
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newItems = readUser();
					 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
					 }
					 catch (Exception e)
					 {
					 output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
					 System.err.println(e.getMessage());
					 } 
			 return output;
			 } 
		
	
	
	
}
