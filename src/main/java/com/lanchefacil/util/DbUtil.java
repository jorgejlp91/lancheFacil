package com.lanchefacil.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
	
	/*
	 * The method creates a Connection object. Loads the embedded driver, starts
	 * and connects to the database using the connection URL.
	 */
	public static Connection createDatabaseConnection() throws SQLException,
			ClassNotFoundException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver);
		String url = "jdbc:derby:sampleDB;create=true";
		Connection c = DriverManager.getConnection(url);
		return c;
	}
	
	
	
	public static void initializeDb() throws ClassNotFoundException, SQLException{
		 Connection conn = createDatabaseConnection();
		 DatabaseMetaData dbmd = conn.getMetaData();
		 ResultSet rs = dbmd.getTables(null, null, "TB_LANCHE", null);
		 if(!rs.next()) {
			 createDatabase();
		 }
	}
	
	public static void createDatabase() throws SQLException, ClassNotFoundException {
		Connection conn = createDatabaseConnection();
		Statement stmnt = conn.createStatement();
		stmnt.execute("CREATE TABLE TB_LANCHE ( "
				+ "ID INT NOT NULL AS IDENTITY, "
				+ "NOME VARCHAR(50) NOT NULL )");
		stmnt.close();	
		stmnt = conn.createStatement();
		stmnt.execute("CREATE TABLE TB_INGREDIENTE ( "
				+ "ID INT NOT NULL AS IDENTITY, "
				+ "ID_LANCHE INT NOT NULL, "
				+ "NOME VARCHAR(50) NOT NULL, "
				+ "VALOR FLOAT NOT NULL )");
		stmnt.close();		
		conn.close();
	}


}
