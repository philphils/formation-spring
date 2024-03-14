package org.formation.spring.batch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.insee.sd44configuration.SD44Configuration;
import fr.insee.sd44configuration.exception.InseeDataSourceException;

/**
 * Hello world!
 *
 */
public class TestConnexionMainBatch {
	public static void main(String[] args) throws SQLException, InseeDataSourceException {
		System.out.println("Hello World!");

		Connection con = SD44Configuration.getInstance().getConnection("database_pool");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select 1");

		if (rs.next())
			System.out.println("Et le résultat est ... Tadammm : " + rs.getInt(1));
		else
			throw new RuntimeException("T'es trop nul ton code il est tout pourri !");

	}
}
