package com.santos.will.search.view.file;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import com.santos.will.search.api.Search;
import com.santos.will.search.engine.SearchBuilder;
import com.santos.will.search.view.SearchTab;
import com.santos.will.search.view.file.chooser.FileChooserCommand;
import com.santos.will.search.view.file.chooser.MouseFileChooser;

public class FilePanel extends SearchTab<String> implements FileChooserCommand {

	private static final long serialVersionUID = 1L;
	private JTextField txtRoot;
	private JList<String> lstFiles;

	/**
	 * Create the panel.
	 */
	public FilePanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblPath = new JLabel("Path:");
		GridBagConstraints gbc_lblPath = new GridBagConstraints();
		gbc_lblPath.weighty = 0.02;
		gbc_lblPath.weightx = 0.02;
		gbc_lblPath.insets = new Insets(0, 0, 0, 5);
		gbc_lblPath.anchor = GridBagConstraints.EAST;
		gbc_lblPath.gridx = 0;
		gbc_lblPath.gridy = 0;
		panel.add(lblPath, gbc_lblPath);

		txtRoot = new JTextField();
		txtRoot.setText("D:/");
		txtRoot.setEditable(false);
		GridBagConstraints gbc_txtD = new GridBagConstraints();
		gbc_txtD.insets = new Insets(1, 1, 1, 1);
		gbc_txtD.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtD.gridx = 1;
		gbc_txtD.gridy = 0;
		panel.add(txtRoot, gbc_txtD);
		txtRoot.setColumns(10);
		txtRoot.addMouseListener(new MouseFileChooser(this, this));

		JScrollPane scroll = new JScrollPane();
		lstFiles = new JList<String>();
		scroll.setViewportView(lstFiles);
		add(scroll, BorderLayout.CENTER);
		lstFiles.setModel(new FileListModel());
	}

	@Override
	public void execute(String path) {
		txtRoot.setText(path);
	}

	@Override
	public String getTitle() {
		return "File";
	}

	@Override
	public Search<String> getSearch() {
		final String root = txtRoot.getText();
		final File rootFile = new File(root);
		if (!rootFile.exists()) {
			final String msg = "Não foi configurado o diretorio raiz de busca na aba de File";
			JOptionPane.showMessageDialog(this, msg, "Warning", JOptionPane.WARNING_MESSAGE);
			throw new RuntimeException(msg);
		}
		return new SearchBuilder().searchInFile(rootFile);
	}

	@Override
	public void setResult(Collection<String> result) {
		final ListModel<String> model = this.lstFiles.getModel();
		if (model instanceof DefaultListModel) {
			final DefaultListModel<String> listModel = (DefaultListModel<String>) model;
			result.stream().forEach(link -> listModel.addElement(link));
		} else {
			throw new RuntimeException("Configurado model não esperado para JList, favor utilizar um DefaultListModel");
		}
	}
}
