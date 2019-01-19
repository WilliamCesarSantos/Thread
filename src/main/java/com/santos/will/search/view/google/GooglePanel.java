package com.santos.will.search.view.google;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import com.santos.will.search.api.Search;
import com.santos.will.search.engine.SearchBuilder;
import com.santos.will.search.view.SearchTab;

public class GooglePanel extends SearchTab<String> {

	private static final long serialVersionUID = 1L;
	private JList<String> lstLinks;

	/**
	 * Create the panel.
	 */
	public GooglePanel() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scroll = new JScrollPane();
		lstLinks = new JList<String>();
		scroll.setViewportView(lstLinks);
		add(scroll, BorderLayout.CENTER);
		lstLinks.setModel(new DefaultListModel<String>());
	}

	@Override
	public String getTitle() {
		return "Google";
	}

	@Override
	public Search<String> getSearch() {
		return new SearchBuilder().searchInGoogle();
	}

	@Override
	public void setResult(final Collection<String> result) {
		final ListModel<String> model = this.lstLinks.getModel();
		if (model instanceof DefaultListModel) {
			final DefaultListModel<String> listModel = (DefaultListModel<String>) model;
			result.stream().forEach(link -> listModel.addElement(link));
		} else {
			throw new RuntimeException("Configurado model não esperado para JList, favor utilizar um DefaultListModel");
		}
	}

}
