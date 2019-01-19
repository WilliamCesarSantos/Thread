package com.santos.will.search.engine.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.santos.will.search.api.Search;
import com.santos.will.search.engine.RegexUtil;
import com.santos.will.search.engine.dto.Person;

public class PessoaDAO implements Search<Person> {

	private final JdbcConnection connection;

	public PessoaDAO(final JdbcConnection connection) {
		this.connection = connection;
	}

	public Collection<Person> search(final String query) {
		final String sql = "select * from person where idperson = ? or name like ? or age = ? or profession like ?";
		final String like = "%".concat(query).concat("%");
		final Long numericQuery = query.matches(RegexUtil.IS_NUMERIC) ? Long.valueOf(query) : 0L;
		try(final Connection connection = this.connection.connect()) {
			return new QueryRunner()
			.query(connection, sql, new ArrayListHandler(), numericQuery, like, numericQuery, like)
			.stream()
			.map(array -> {
				final Person person = new Person();
				person.setIdPerson((Long) array[0]);
				person.setName((String) array[1]);
				person.setAge((Integer) array[2]);
				person.setProfession((String) array[3]);
				return person;
			})
			.collect(Collectors.toList());
		} catch(final SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
