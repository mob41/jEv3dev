package org.ev3dev.software.jEv3Dev.UI;

import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionsPane extends JPanel {

	private boolean dragging = false;
	private UI ui;
	
	/**
	 * Create the panel.
	 */
	public ActionsPane(UI frame) {
		this.ui = frame;
		
		JLabel lblTestItem = new JLabel("Test Item");
		lblTestItem.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if (!dragging){
					dragging = true;
					
					System.out.println("Dragging!");
					final MovingFrame movingFrame = new MovingFrame();
					ui.mainDesk.add(movingFrame);
					movingFrame.setLocation(ui.mainDesk.getMousePosition());
					movingFrame.setVisible(true);
					
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTestItem)
					.addContainerGap(971, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTestItem)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}

}
