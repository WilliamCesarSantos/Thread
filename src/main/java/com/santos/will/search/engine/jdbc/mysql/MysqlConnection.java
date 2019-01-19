package com.santos.will.search.engine.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.santos.will.search.engine.jdbc.JdbcConnection;

/**
 * Fornece acesso a uma conex�o com a base de dados postgresql
 * 
 * @author William
 * @since 2016-10-25
 * @version 1.0.0
 */
public class MysqlConnection implements JdbcConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/people";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "univel";

	public Connection connect() {
		try {
			final Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
			return connection;
		} catch (final SQLException e) {
			throw new RuntimeException("Erro na conex�o com o banco de dados", e);
		}
	}
}
