package com.santos.will.search.view.jdbc;

import java.util.List;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

import com.santos.will.search.engine.dto.Person;

public class PersonTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final List<Person> people;
	private final String[] columns = new String[] { "idPerson", "Name", "Age", "Profession" };

	public PersonTableModel(final List<Person> people) {
		this.people = people;
	}

	@Override
	public String getColumnName(int column) {
		if (column > this.columns.length) {
			throw new RuntimeException("Coluna não disponivel para a grid");
		}
		return this.columns[column];
	}

	@Override
	public int getRowCount() {
		return this.people.size();
	}

	@Override
	public int getColumnCount() {
		return this.columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final Person person = this.people.get(rowIndex);
		Objects.requireNonNull(person, "Person não disponível");
		switch (columnIndex) {
		case 0:
			return person.getIdPerson();
		case 1:
			return person.getName();
		case 2:
			return person.getAge();
		case 3:
			return person.getProfession();
		default:
			throw new RuntimeException("Coluna não disponível para a grid");
		}
	}

}
