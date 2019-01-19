package com.santos.will.search.engine;

import java.io.File;

import com.santos.will.search.api.Search;
import com.santos.will.search.api.SearchType;
import com.santos.will.search.engine.dto.Person;
import com.santos.will.search.engine.file.FileSearch;
import com.santos.will.search.engine.google.GoogleSearchApi;
import com.santos.will.search.engine.jdbc.JdbcConnection;
import com.santos.will.search.engine.jdbc.JdbcConnectionFactory;
import com.santos.will.search.engine.jdbc.PessoaDAO;

public class SearchBuilder {

	public Search<Person> searchInPostgresql() {
		final JdbcConnection connection = new JdbcConnectionFactory().get(SearchType.POSTGRESQL);
		return new PessoaDAO(connection);
	}

	public Search<Person> searchInMysql() {
		final JdbcConnection connection = new JdbcConnectionFactory().get(SearchType.MYSQL);
		return new PessoaDAO(connection);
	}

	public Search<String> searchInFile(final File root) {
		return new FileSearch(root, ThreadFactory.instance());
	}

	public Search<String> searchInGoogle() {
		return new GoogleSearchApi();
	}
}
