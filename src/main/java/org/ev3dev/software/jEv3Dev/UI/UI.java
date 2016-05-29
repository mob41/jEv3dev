package org.ev3dev.software.jEv3Dev.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JToolTip;
import javax.swing.JWindow;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Dimension;

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
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JToolBar;

public class UI extends JFrame {

	private JPanel contentPane;
	
	//For debug purpose, only
	protected JLabel lblXY;
	private JTabbedPane tab;

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
		
		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectPane projectPane = new ProjectPane(UI.this);
				
				tab.addTab("*New Project " + (tab.getTabCount() + 1), projectPane);
				tab.setSelectedIndex(tab.getTabCount() - 1);
			}
		});
		mnFile.add(mntmNewProject);
		
		JMenuItem mntmCloseProject = new JMenuItem("Close Project");
		mntmCloseProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tab.getSelectedIndex();
				
				if (index == -1){
					JOptionPane.showMessageDialog(UI.this, "No project is selected.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				tab.remove(index);
			}
		});
		mnFile.add(mntmCloseProject);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmSaveProject = new JMenuItem("Save Project");
		mnFile.add(mntmSaveProject);
		
		JMenuItem mntmLoadProject = new JMenuItem("Load Project");
		mnFile.add(mntmLoadProject);
		
		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);
		
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
		
		tab = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tab, BorderLayout.CENTER);
		
		ProjectPane projectPane = new ProjectPane(this);
		tab.addTab("*New Project 1", null, projectPane, null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		lblXY = new JLabel("X: --- Y: ---");
		toolBar.add(lblXY);
	}
	
	protected BlocksVas getBlocksCanvas(){
		ProjectPane projectPane = getSelectedProjectPane();
		
		if (projectPane == null){
			return null;
		}
		
		return projectPane.getBlocksCanvas();
	}
	
	protected JScrollPane getBlocksScroll(){
		ProjectPane projectPane = getSelectedProjectPane();
		
		if (projectPane == null){
			return null;
		}
		
		return projectPane.getBlocksScrollPane();
	}
	
	protected ProjectPane getSelectedProjectPane(){
		ProjectPane projectPane = (ProjectPane) tab.getSelectedComponent();
		
		if (projectPane == null){
			return null;
		}
		
		return projectPane;
	}
	
}
