package org.ev3dev.software.jEv3Dev;

import java.awt.EventQueue;

import org.ev3dev.software.jEv3Dev.UI.UI;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable(){
			public void run(){
				UI window = new UI();
				window.setVisible(true);
			}
		});

	}

}
