package br.com.fastchoice.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	
	public static void teste() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "SenhaSatanica!");
		System.out.println("Sistema conectado!");
		conexao.close();
	}
	
	/*
	public static void main(String[] args) {
		new JDBC();
		try {
			teste();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/

}
