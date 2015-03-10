package br.com.LuizMario.FarmaVet.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Connection{
	
	public class getInstance {

	}

	private static ResourceBundle config;
	private final String SENHA = "root";
	private final String USUARIO = "root";
	
	private static Connection connection;
	
	public Connection () {
		config = ResourceBundle.getBundle("config");
	}
	
	public static Connection getInstance(){
		if (connection == null){
			connection = new Connection();
		}
	return connection;
	}
	
	public java.sql.Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(config.getString("br.com.LuizMario.FarmaVet.driver"));
		return ( DriverManager.getConnection(config.getString("br.com.LuizMario.FarmaVet.conexao"), USUARIO, SENHA ));
	}	
		
}
