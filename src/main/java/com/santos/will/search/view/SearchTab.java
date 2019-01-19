package com.santos.will.search.view;

import java.util.Collection;

import javax.swing.JPanel;

import com.santos.will.search.api.Search;

public abstract class SearchTab<T> extends JPanel {

	private static final long serialVersionUID = 1L;

	public abstract Search<T> getSearch();

	public abstract String getTitle();

	public abstract void setResult(Collection<T> result);
}
