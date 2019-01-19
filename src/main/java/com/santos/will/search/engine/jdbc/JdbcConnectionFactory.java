package com.santos.will.search.engine.jdbc;

import com.santos.will.search.api.SearchType;
import com.santos.will.search.engine.jdbc.mysql.MysqlConnection;
import com.santos.will.search.engine.jdbc.postgresql.PostgresqlConnection;

public class JdbcConnectionFactory {

	public JdbcConnection get(final SearchType type) {
		switch (type) {
		case POSTGRESQL:
			return new PostgresqlConnection();
		case MYSQL:
			return new MysqlConnection();
		default:
			throw new RuntimeException("JdbcType informado não suportado pelo app");
		}
	}
}
