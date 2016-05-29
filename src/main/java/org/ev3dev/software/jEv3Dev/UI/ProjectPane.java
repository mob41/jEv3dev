package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JWindow;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.ev3dev.software.jEv3Dev.UI.blocks.Rail;

public class ProjectPane extends JDesktopPane {

	protected BlocksLoader blocksLoader;
	
	private JScrollPane blocksScroll;
	private BlocksVas blocksVas;
	private JWindow window;
	private JLabel label;
	private Block oldBlock;
	private Block oldDraggingBlock;
	
	private UI ui;
	private JMenuItem mntmRemove;

	/**
	 * Create the panel.
	 */
	public ProjectPane(UI uiframe) {
		setBounds(0, 0, 1254, 650);
		this.ui = uiframe;
		
		blocksLoader = new BlocksLoader(500);
		
		JSplitPane split1 = new JSplitPane();
		split1.setResizeWeight(0.9);
		
		JSplitPane split2 = new JSplitPane();
		split2.setResizeWeight(0.8);
		split2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split1.setLeftComponent(split2);
		
		blocksScroll = new JScrollPane();
		split2.setLeftComponent(blocksScroll);
		
		blocksVas = new BlocksVas(ui, blocksLoader);
		blocksScroll.setViewportView(blocksVas);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.addPopupMenuListener(new PopupMenuListener() {
			
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				
			}
			
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				
			}
			
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				Point pos = blocksVas.getMousePosition();
				
				if (pos == null){
					mntmRemove.setEnabled(false);
					return;
				}
				
				Block block = blocksLoader.getBlockAtPosition(pos);
				
				if (block == null){
					mntmRemove.setEnabled(false);
					mntmRemove.setToolTipText("No block is at this position");
				} else {
					mntmRemove.setEnabled(true);
					mntmRemove.setToolTipText(null);
				}
			}
		});
		addPopup(blocksVas, popupMenu);
		
		mntmRemove = new JMenuItem("Remove");
		mntmRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Point pos = blocksVas.getMousePosition(true);
				
				if (pos == null){
					System.err.println("Not in BlockVas");
					return;
				}
				
				Block block = blocksLoader.getBlockAtPosition(pos);
				
				if (block != null){
					blocksLoader.removeBlock(block);
				} else {
					System.err.println("No block is there!");
				}
				
				blocksVas.repaint();
			}
		});
		popupMenu.add(mntmRemove);
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Rectangle bounds = blocksScroll.getViewport().getViewRect();
				Dimension size = blocksScroll.getViewport().getViewSize();
				
				int x = (size.width - bounds.width) / 2;
				int y = (size.height - bounds.height) / 2;
				
				blocksScroll.getViewport().setViewPosition(new Point(x, y));
			}
		});
		
		blocksVas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				blocksLoader.onMouseClickCheckAll(getMousePosition());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Block block = blocksLoader.onMousePressCheckAll(getMousePosition());
				
				if (block != null && e.getButton() == -5){
					BlockInfo info = new BlockInfo(block);
					add(info);
					info.setVisible(true);
					return;
				}
			}
		});
		blocksVas.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent arg0) {
				ui.lblXY.setText("X: " + arg0.getPoint().x + " Y: " + arg0.getPoint().y);
				Block block = blocksLoader.onMouseTouchCheckAll(arg0.getPoint());
				
				
				if (block == null || (block != null && oldBlock != null && !block.equals(oldBlock))){
					oldBlock = null;
					if (label != null){
						blocksVas.remove(label);
						label = null;
					}
					blocksVas.repaint();
				} else {
					oldBlock = block;
					blocksVas.getGraphics().drawRect(block.getLeftX(), block.getUpY(), block.getWidth(), block.getHeight());
					
					if (label == null){
						label = new JLabel();
						blocksVas.add(label);
					}
					
					label.setText(block.getName());
					label.setBounds(arg0.getPoint().x, arg0.getPoint().y, blocksVas.getGraphics().getFontMetrics().stringWidth(label.getText()) + 10, 15);
					
					label.setHorizontalAlignment(JLabel.CENTER);
					label.setVisible(true);
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				Point pos = e.getPoint();
				Block block = blocksLoader.getBlockAtPosition(pos);
				
				if (block == null){
					oldDraggingBlock = null;
					return;
				}
				
				if (oldDraggingBlock != null){
					System.out.println("Not null");
					if (block.getShortName().equals("blocksRail")){
						if (block.getWidth() < block.getWidth() + block.getRightX() - pos.getX()){
							System.out.println("Inserting block");
							Rail rail = new Rail(false, true);
							blocksLoader.insertBlock(block, rail);
							oldDraggingBlock = null;
						} else if (block.getWidth() > pos.getX() - block.getRightX()){
							blocksLoader.removeBlock(block);
						}
						blocksVas.repaint();
						return;
					}
				}
				
				if (block.getShortName().equals("blocksRail")){
					oldDraggingBlock = block;
					System.out.println("Pulling blocks rail..." + pos);
				} else {
					oldDraggingBlock = null;
				}
				
				blocksVas.repaint();
			}
		});
		
				JScrollPane actionsScroll = new JScrollPane();
				actionsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				split2.setRightComponent(actionsScroll);
				
				BlocksPane actionsPane = new BlocksPane(ui);
				actionsScroll.setViewportView(actionsPane);
				GroupLayout gl_mainDesk = new GroupLayout(this);
				gl_mainDesk.setHorizontalGroup(
					gl_mainDesk.createParallelGroup(Alignment.LEADING)
						.addComponent(split1, GroupLayout.DEFAULT_SIZE, 1254, Short.MAX_VALUE)
				);
				gl_mainDesk.setVerticalGroup(
					gl_mainDesk.createParallelGroup(Alignment.LEADING)
						.addComponent(split1, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
				);
				
				setLayout(gl_mainDesk);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	protected JScrollPane getBlocksScrollPane(){
		return blocksScroll;
	}
	
	protected BlocksVas getBlocksCanvas(){
		return blocksVas;
	}

}
