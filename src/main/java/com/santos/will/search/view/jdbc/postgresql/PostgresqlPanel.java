package com.santos.will.search.view.jdbc.postgresql;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.santos.will.search.api.Search;
import com.santos.will.search.engine.SearchBuilder;
import com.santos.will.search.engine.dto.Person;
import com.santos.will.search.view.SearchTab;
import com.santos.will.search.view.jdbc.PersonTableModel;

public class PostgresqlPanel extends SearchTab<Person> {

	private static final long serialVersionUID = 1L;
	private JTable table;

	public PostgresqlPanel() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scroll = new JScrollPane();
		table = new JTable();
		scroll.setViewportView(table);
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		table.setModel(new PersonTableModel(new ArrayList<>()));
	}

	@Override
	public String getTitle() {
		return "Postgresql";
	}

	@Override
	public Search<Person> getSearch() {
		return new SearchBuilder().searchInPostgresql();
	}

	@Override
	public void setResult(final Collection<Person> result) {
		final List<Person> peoples = new ArrayList<>(result);
		table.setModel(new PersonTableModel(peoples));
	}

}
