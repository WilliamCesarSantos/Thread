package com.santos.will.search.view.file;

import javax.swing.DefaultListModel;

public class FileListModel extends DefaultListModel<String> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getElementAt(int index) {
		final String element = super.getElementAt(index);
		return String.format("Encontrado correspondencia em %s", element);
	}
}
