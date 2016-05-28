package org.ev3dev.software.jEv3Dev.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
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

public class UI extends JFrame {

	private JPanel contentPane;
	protected JDesktopPane mainDesk;

	/**
	 * Create the frame.
	 */
	public UI() {
		setTitle("jEv3Dev - New Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		mainDesk = new JDesktopPane();
		mainDesk.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		contentPane.add(mainDesk, BorderLayout.CENTER);
		
		JSplitPane split1 = new JSplitPane();
		split1.setResizeWeight(0.9);
		
		JSplitPane split2 = new JSplitPane();
		split2.setResizeWeight(0.8);
		split2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split1.setLeftComponent(split2);
		
		JScrollPane blocksScroll = new JScrollPane();
		split2.setLeftComponent(blocksScroll);
		
		JPanel canvasHoldingPanel = new JPanel();
		blocksScroll.setViewportView(canvasHoldingPanel);
		canvasHoldingPanel.setLayout(new BorderLayout(0, 0));
		
		final BlocksVas blocksVas = new BlocksVas();
		canvasHoldingPanel.add(blocksVas);
		
		blocksVas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Checking click... " + Calendar.getInstance().getTime().toString());
				BlocksLoader.getBlocksLoader().onMouseClickCheckAll(contentPane.getMousePosition());
				System.out.println("!! Checked click!!" + Calendar.getInstance().getTime().toString());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Checking press... " + Calendar.getInstance().getTime().toString());
				BlocksLoader.getBlocksLoader().onMousePressCheckAll(contentPane.getMousePosition());
				System.out.println("!! Checked press !!" + Calendar.getInstance().getTime().toString());
			}
		});
		blocksVas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				System.out.println("Checking touch... " + Calendar.getInstance().getTime().toString());
				BlocksLoader.getBlocksLoader().onMouseTouchCheckAll(contentPane.getMousePosition());
				System.out.println("!!!Checked touch!!!" + Calendar.getInstance().getTime().toString());
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
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				System.out.println("Draw All");
				BlocksLoader.getBlocksLoader().drawAll(blocksVas.getGraphics());
				System.out.println("Repainting");
				blocksVas.repaint();
			}
		});
	}
}
