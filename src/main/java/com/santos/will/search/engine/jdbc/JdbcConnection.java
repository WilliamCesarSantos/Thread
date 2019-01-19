package com.santos.will.search.engine.jdbc;

import java.sql.Connection;

/**
 * @author William
 */
public interface JdbcConnection {

	Connection connect();
}
