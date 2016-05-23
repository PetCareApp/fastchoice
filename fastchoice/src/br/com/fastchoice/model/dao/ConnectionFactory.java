package br.com.fastchoice.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
    	String path = "jdbc:mysql://localhost/fastchoice";
//    String path = "jdbc:mysql://mysql-env-6714128.jelasticlw.com.br/fastchoice/";
	String login = "root";
	String password = "SenhaSatanica!";
//	String password = "KAdedxBI79";
    
	public Connection getConnection() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(path, login, password);
		} catch (SQLException  e) {
			new RuntimeException(e);
			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "Can't connect with database. Please, check if you database is connected (with path, login and password) and try playing again.", "Connection error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return null;
	}
	
}
