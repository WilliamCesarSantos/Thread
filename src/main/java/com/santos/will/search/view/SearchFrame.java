package com.santos.will.search.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

import com.santos.will.search.api.Search;
import com.santos.will.search.view.file.FilePanel;
import com.santos.will.search.view.google.GooglePanel;
import com.santos.will.search.view.jdbc.mysql.MysqlPanel;
import com.santos.will.search.view.jdbc.postgresql.PostgresqlPanel;

public class SearchFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtQuery;
	private JTabbedPane tabbedPane;
	private final SearchTab<?>[] tabs = new SearchTab[] { new PostgresqlPanel(), new MysqlPanel(), new FilePanel(),
			new GooglePanel() };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		setTitle("Buscador de tudo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		txtQuery = new JTextField();
		txtQuery.setToolTipText("Criterio de busca...");
		PromptSupport.setPrompt("Criterio de busca...", txtQuery);
		GridBagConstraints gbc_txtQuery = new GridBagConstraints();
		gbc_txtQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuery.gridx = 0;
		gbc_txtQuery.gridy = 0;
		panel.add(txtQuery, gbc_txtQuery);
		txtQuery.setColumns(10);
		txtQuery.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					SearchFrame.this.search();
				}
			}
		});

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		for (SearchTab<?> tab : tabs) {
			tabbedPane.addTab(tab.getTitle(), null, tab, null);
		}
		setLocationRelativeTo(null);
	}

	protected void search() {
		for (final SearchTab<?> tab : tabs) {
			new Thread(new Runnable() {
				public void run() {
					try {
						final String query = txtQuery.getText();
						final Search<?> search = tab.getSearch();
						final Collection result = search.search(query);
						tab.setResult(result);
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

}
