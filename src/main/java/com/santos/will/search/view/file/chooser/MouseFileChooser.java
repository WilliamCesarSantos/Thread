package com.santos.will.search.view.file.chooser;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;

public class MouseFileChooser extends MouseAdapter {

	private final Component parent;
	private final FileChooserCommand command;

	public MouseFileChooser(final Component parent, final FileChooserCommand command) {
		this.parent = parent;
		this.command = command;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		final Integer returnedValue = chooser.showOpenDialog(this.parent);
		if (returnedValue == JFileChooser.APPROVE_OPTION) {
			final String path = chooser.getSelectedFile().getAbsolutePath();
			this.command.execute(path);
		}

	}
}
