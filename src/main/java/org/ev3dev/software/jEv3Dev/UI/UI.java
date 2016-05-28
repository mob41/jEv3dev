package org.ev3dev.software.jEv3Dev.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JToolTip;
import javax.swing.JWindow;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import java.awt.Canvas;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

public class UI extends JFrame {

	private JPanel contentPane;
	protected JDesktopPane mainDesk;
	private BlocksVas blocksVas;
	private JScrollPane blocksScroll;
	private Block oldBlock;

	/**
	 * Create the frame.
	 */
	public UI() {
		setTitle("jEv3Dev - New Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLoadProject = new JMenuItem("Load Project");
		mnFile.add(mntmLoadProject);
		
		JMenuItem mntmDeployProject = new JMenuItem("Deploy Project");
		mnFile.add(mntmDeployProject);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mnEdit.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);
		
		JSeparator separator = new JSeparator();
		mnEdit.add(separator);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnEdit.add(mntmPreferences);
		contentPane = new JPanel();
		
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				contentPane.add(tabbedPane, BorderLayout.CENTER);
				
				mainDesk = new JDesktopPane();
				tabbedPane.addTab("*New Project", null, mainDesk, null);
				mainDesk.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
				
				JSplitPane split1 = new JSplitPane();
				split1.setResizeWeight(0.9);
				
				JSplitPane split2 = new JSplitPane();
				split2.setResizeWeight(0.8);
				split2.setOrientation(JSplitPane.VERTICAL_SPLIT);
				split1.setLeftComponent(split2);
				
				blocksScroll = new JScrollPane();
				split2.setLeftComponent(blocksScroll);
				
				blocksVas = new BlocksVas(this);
				blocksScroll.setViewportView(blocksVas);
				
				blocksVas.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						BlocksLoader.getBlocksLoader().onMouseClickCheckAll(blocksVas.getMousePosition());
					}
					@Override
					public void mousePressed(MouseEvent e) {
						Block block = BlocksLoader.getBlocksLoader().onMousePressCheckAll(blocksVas.getMousePosition());
						
						System.out.println("PRESSED. " + block);
						if (block != null && e.getButton() == MouseEvent.BUTTON3){
							System.out.println("Found block: " + block.getName());
							BlockInfo info = new BlockInfo(block);
							info.setVisible(true);
							getBlocksCanvas().add(info);
							return;
						}
					}
				});
				blocksVas.addMouseMotionListener(new MouseMotionAdapter() {
					private JWindow window;
					private JLabel label;

					@Override
					public void mouseMoved(MouseEvent arg0) {
						Block block = BlocksLoader.getBlocksLoader().onMouseTouchCheckAll(blocksVas.getMousePosition());
						
						System.out.println("checking");
						
						if (block == null || (block != null && oldBlock != null && !block.equals(oldBlock))){
							oldBlock = null;
							if (label != null){
								blocksVas.remove(label);
								label = null;
							}
							blocksVas.repaint();
						} else {
							oldBlock = block;
							System.out.println("Is not old and not null");
							blocksVas.getGraphics().drawRect(block.getLeftX(), block.getUpY(), block.getWidth(), block.getHeight());
							
							if (label == null){
								label = new JLabel();
								blocksVas.add(label);
							}
							
							label.setText(block.getName());
							label.setBounds(blocksVas.getMousePosition().x, blocksVas.getMousePosition().y, blocksVas.getGraphics().getFontMetrics().stringWidth(label.getText()) + 10, 15);
							
							label.setHorizontalAlignment(JLabel.CENTER);
							label.setVisible(true);
						}
					}
				});
				
						JScrollPane actionsScroll = new JScrollPane();
						actionsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						split2.setRightComponent(actionsScroll);
						
						BlocksPane actionsPane = new BlocksPane(this);
						actionsScroll.setViewportView(actionsPane);
						GroupLayout gl_mainDesk = new GroupLayout(mainDesk);
						gl_mainDesk.setHorizontalGroup(
							gl_mainDesk.createParallelGroup(Alignment.LEADING)
								.addComponent(split1, GroupLayout.DEFAULT_SIZE, 1254, Short.MAX_VALUE)
						);
						gl_mainDesk.setVerticalGroup(
							gl_mainDesk.createParallelGroup(Alignment.LEADING)
								.addComponent(split1, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
						);
						
						mainDesk.setLayout(gl_mainDesk);
	}
	
	public BlocksVas getBlocksCanvas(){
		return blocksVas;
	}
	
	public JScrollPane getBlocksScroll(){
		return blocksScroll;
	}
}
