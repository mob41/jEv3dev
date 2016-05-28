package org.ev3dev.software.jEv3Dev.UI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;

public class BlockInfo extends JInternalFrame {
	private JLabel lblName;
	private JLabel lblAuthor;
	private JLabel lblVersion;
	private JTextPane txtpnDescription;

	/**
	 * Create the frame.
	 */
	public BlockInfo(Block block) {
		setTitle(block.getName() + " - Block Information");
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 350, 337);
		
		lblName = new JLabel("Name: " + block.getName());
		
		lblAuthor = new JLabel("Author: " + block.getAuthor());
		
		lblVersion = new JLabel("Version: " + block.getVersion());
		
		txtpnDescription = new JTextPane();
		txtpnDescription.setEditable(false);
		txtpnDescription.setBackground(SystemColor.menu);
		txtpnDescription.setText("Description: " + block.getDescription());
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnDescription, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
						.addComponent(lblAuthor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
						.addComponent(lblName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
						.addComponent(lblVersion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAuthor, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblVersion, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnDescription, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
