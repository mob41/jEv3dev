package org.ev3dev.software.jEv3Dev;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.ev3dev.software.jEv3Dev.UI.BlocksLoader;
import org.ev3dev.software.jEv3Dev.UI.UI;
import org.ev3dev.software.jEv3Dev.UI.blocks.TestBlock;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				UI window = new UI();
				window.setVisible(true);
			}
		});

	}

}
