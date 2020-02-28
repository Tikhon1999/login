package com.goncahrov.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService 
{
	String url = "jdbc:mysql://localhost:3306/login?serverTimezone=UTC";
	String username = "root";
	String password = "root";
	Connection conn;
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean check(String uname, String pass) {
		try {
			connect();
			PreparedStatement pst = conn.prepareStatement("select * from user where username=? and password=?");
			pst.setString(1, uname);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			System.out.print(e);
		}
		
		return false;
	}
	
	public String getUserFirstName(String uname) {
		try {
			connect();
			PreparedStatement pst = conn.prepareStatement("select firstname from user where username=?");
			pst.setString(1, uname);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}else {
				return "";
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return "";
	}
}
