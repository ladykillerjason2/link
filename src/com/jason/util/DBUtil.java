package com.jason.util;

import java.sql.*;
public class DBUtil {
	
	static Connection con=null;

	public static Connection getConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/link","root","1342");
			return con;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
