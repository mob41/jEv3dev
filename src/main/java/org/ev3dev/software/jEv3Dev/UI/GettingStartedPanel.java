package org.ev3dev.software.jEv3Dev.UI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GettingStartedPanel extends JPanel {

	private UI ui;
	/**
	 * Create the panel.
	 */
	public GettingStartedPanel(UI uiframe) {
		this.ui = uiframe;
		setBackground(Color.CYAN);
		setBounds(0, 0, 1254, 650);
		
		JLabel lblGettingStartedWith = new JLabel("Getting Started with jEv3Dev");
		lblGettingStartedWith.setFont(new Font("Tahoma", Font.BOLD, 45));
		
		JLabel lblJevdevAlphadevelopment = new JLabel("jEv3Dev alpha (Development Version)");
		lblJevdevAlphadevelopment.setFont(new Font("PMingLiU", Font.PLAIN, 40));
		
		JButton btnNewProject = new JButton("New Project");
		btnNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTabbedPane tab = ui.getTabbedPane();
				ProjectPane projectPane = new ProjectPane(ui);
				
				tab.add("*New Project " + (tab.getTabCount() + 1), projectPane);
				tab.setSelectedIndex(tab.getTabCount() - 1);
				
				ui.getMainPanel().setVisible(true);
				ui.getContentPane().remove(GettingStartedPanel.this);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGettingStartedWith, GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE)
						.addComponent(lblJevdevAlphadevelopment)
						.addComponent(btnNewProject))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGettingStartedWith)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblJevdevAlphadevelopment)
					.addGap(62)
					.addComponent(btnNewProject)
					.addContainerGap(446, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
