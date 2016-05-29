package org.ev3dev.software.jEv3Dev.UI;

import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.ev3dev.software.jEv3Dev.UI.blocks.TestBlock;

public class BlocksPane extends JPanel {

	private boolean dragging = false;
	private UI ui;
	
	/**
	 * Create the panel.
	 */
	public BlocksPane(final UI frame) {
		this.ui = frame;
		
		JLabel lblTestItem = new JLabel();
		lblTestItem.setToolTipText("A block");
		lblTestItem.setIcon(new ImageIcon(BlocksPane.class.getResource("/org/ev3dev/software/jEv3Dev/UI/resources/block.fw.png")));
		lblTestItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Point nextPos = ui.getSelectedProjectPane().blocksLoader.getNextBlockPos();
				TestBlock testBlock = new TestBlock(Color.GREEN);
				
				if (arg0.getButton() == MouseEvent.BUTTON3){
					BlockInfo info = new BlockInfo(testBlock);
					frame.getSelectedProjectPane().add(info);
					info.setVisible(true);
					return;
				}
				
				ui.getSelectedProjectPane().blocksLoader.addBlock(testBlock);
				
				ui.getBlocksCanvas().repaint();
			}
		});
		
		JLabel lblTestItemRED = new JLabel();
		lblTestItemRED.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				TestBlock testBlock = new TestBlock(Color.RED);
				
				if (arg0.getButton() == MouseEvent.BUTTON3){
					BlockInfo info = new BlockInfo(testBlock);
					frame.getSelectedProjectPane().add(info);
					info.setVisible(true);
					return;
				}
				
				ui.getSelectedProjectPane().blocksLoader.addBlock(testBlock);
				
				ui.getBlocksCanvas().repaint();
				
			}
		});
		lblTestItemRED.setIcon(new ImageIcon(BlocksPane.class.getResource("/org/ev3dev/software/jEv3Dev/UI/resources/block.fw.png")));
		lblTestItemRED.setToolTipText("A RED block");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTestItem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTestItemRED, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(911, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTestItemRED, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTestItem))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}

}
