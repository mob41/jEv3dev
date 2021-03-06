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
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class ProjectPane extends JDesktopPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4841366029094480006L;

	protected BlocksLoader blocksLoader;
	
	private JScrollPane blocksScroll;
	private BlocksVas blocksVas;
	private JLabel label;
	private Block oldBlock;
	private Block oldDraggingBlock;
	
	private UI ui;
	private JMenuItem mntmRemove;
	private JMenuItem mntmMakeRailShorter;
	private JMenuItem mntmBlockInformation;

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
					mntmMakeRailShorter.setVisible(false);
					mntmBlockInformation.setEnabled(false);
					mntmRemove.setVisible(true);
					mntmRemove.setEnabled(false);
					mntmRemove.setToolTipText("No block is at this position");
				} else if (block.getShortName().equals("blocksRail")){
					mntmRemove.setVisible(false);
					mntmBlockInformation.setEnabled(true);
					mntmMakeRailShorter.setVisible(true);
					mntmMakeRailShorter.setEnabled(true);
				} else {
					mntmMakeRailShorter.setVisible(false);
					mntmBlockInformation.setEnabled(true);
					mntmRemove.setVisible(true);
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
				
			}
		});
		popupMenu.add(mntmRemove);
		
		mntmMakeRailShorter = new JMenuItem("Make rail shorter");
		mntmMakeRailShorter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point pos = blocksVas.getMousePosition(true);
				
				if (pos == null){
					System.err.println("Not in BlockVas");
					return;
				}
				
				Block block = blocksLoader.getBlockAtPosition(pos);
				
				if (block == null){
					System.err.println("No block related");
					return;
				}
				
				if (!block.getShortName().equals("blocksRail")){
					System.err.println("Not a rail");
					return;
				} else {
					blocksLoader.blocks.remove(block);
				}
				
			}
		});
		popupMenu.add(mntmMakeRailShorter);
		
		mntmBlockInformation = new JMenuItem("Block Information");
		mntmBlockInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point pos = blocksVas.getMousePosition(true);
				
				if (pos == null){
					System.err.println("Not in BlockVas");
					return;
				}
				
				Block block = blocksLoader.getBlockAtPosition(pos);
				
				if (block == null){
					System.err.println("No block related to block info");
					return;
				}
				
				BlockInfo blockInfo = new BlockInfo(block);
				add(blockInfo);
				blockInfo.setVisible(true);
			}
		});
		popupMenu.add(mntmBlockInformation);
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
				Block notRldBlock = blocksLoader.getBlockOfNotReleasedMouse();
				
				if (notRldBlock == null){
					System.err.println("No block there");
					return;
				}
				System.out.println("Not released mouse: " + notRldBlock.getName());
				
				if (notRldBlock != null){
					Point pos = blocksVas.getMousePosition();
					Block blockAtPos = blocksLoader.getBlockAtPosition(pos);
					
					if (blockAtPos == null){
						System.err.println(">> Nullptr. No block there. Place it to workspace as well.");
						notRldBlock.setConnectedToRail(false);
						notRldBlock.setReleasedFromMouse(true);
						int cx = pos.x - notRldBlock.getWidth() / 2;
						int cy = pos.y - notRldBlock.getHeight() / 2;
						notRldBlock.setPos(new Point(cx, cy));
					} else {
						//TODO Process nullptr
						System.err.println("Not implemented");
					}
				}
				
				/*
				Block block = blocksLoader.onMousePressCheckAll(getMousePosition());
				
				if (block != null && e.getButton() == -5){
					BlockInfo info = new BlockInfo(block);
					add(info);
					info.setVisible(true);
					return;
				}
				*/
			}
		});
		blocksVas.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent arg0) {
				ui.lblXY.setText("X: " + arg0.getPoint().x + " Y: " + arg0.getPoint().y);
				Block block = blocksLoader.onMouseTouchCheckAll(arg0.getPoint());
				
				// Disable repaint via mouse motion, give this to thread
				//blocksVas.repaint();
				
				//TODO Do selected via MousePressed and render the rectangle bound via paintComponent in blocksVas
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
						
						System.out.println("--BlockWidth: " + block.getWidth());
						System.out.println("Width/3: " + block.getWidth() / 3);
						System.out.println("--PosX: " + pos.getX());
						System.out.println("--LeftX: " + block.getLeftX());
						System.out.println("--PosXSubLeftX: " + (pos.getX() - block.getLeftX()));
						System.out.println("Sub/2: " + (pos.getX() - block.getLeftX()) / 2);
						System.out.print("Sub/2BiggerThanWidth/3? ");
						
						if (block.getWidth() / 3 <= pos.getX() - block.getLeftX()){
							System.out.println("Yes!");
							System.out.println("Inserting block");
							//TODO Remove this insert block
							//Rail rail = new Rail(false, true);
							//blocksLoader.insertBlock(block, rail);
						} else {
							System.out.println("No, But BlockWidth is bigger than PosX-RightX");
							
							switch (blocksLoader.blocks.indexOf(block)){
							case 0:
							case 1:
								oldDraggingBlock = null;
								return;
							}
							
							if (blocksLoader.blocks.size() <= 2){
								return;
							
							}
							
							blocksLoader.blocks.remove(block);
							System.out.println("!!Removed block");
						}
					}
					oldDraggingBlock = null;
					return;
				}
				
				if (block.getShortName().equals("blocksRail")){
					oldDraggingBlock = block;
					System.out.println("Pulling blocks rail..." + pos);
				} else {
					oldDraggingBlock = null;
				}
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
