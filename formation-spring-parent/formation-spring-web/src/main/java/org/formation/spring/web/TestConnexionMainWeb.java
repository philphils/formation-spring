package org.formation.spring.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.insee.config.InseeConfig;
import fr.insee.config.exception.PoolException;

/**
 * Hello world!
 *
 */
public class TestConnexionMainWeb {
	public static void main(String[] args) throws PoolException, SQLException {
		System.out.println("Hello World!");

		Connection con = InseeConfig.getPool().getConnection("database_pool");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select 1");

		if (rs.next())
			System.out.println("Et le résultat est ... Tadammm : " + rs.getInt(1));
		else
			throw new RuntimeException("T'es trop nul ton code il est tout pourri !");

	}
}
