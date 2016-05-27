package org.ev3dev.software.jEv3Dev.UI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class MovingFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovingFrame frame = new MovingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MovingFrame() {
		setBounds(100, 100, 84, 70);
		
		JLabel lblYo = new JLabel("Yo");
		lblYo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblYo, BorderLayout.CENTER);

	}

}
