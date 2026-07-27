package com.textbasedgame.GUI.Styles;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;


import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;

public class buttonStyler {
	public static void styleButton(JButton inputButton){
		RoundedBorder bdr = new RoundedBorder(Color.BLACK, 20);
		inputButton.setPreferredSize(new Dimension(200, 50));
		inputButton.setContentAreaFilled(false);
		inputButton.setOpaque(false);
		inputButton.setFocusPainted(false);
		inputButton.setBorder(bdr);
		inputButton.setBackground(new Color(50, 50, 50));
		inputButton.setForeground(Color.WHITE);
	}



	private static class RoundedBorder implements Border {

		private int radius;
		private Color color;

		RoundedBorder(int radius) {
			this.radius = radius;
		}
		RoundedBorder(Color color, int radius) {
			this.radius = radius;
			this.color = color;
		}


		public Insets getBorderInsets(Component c) {
			return new Insets(8, 16, 8, 16);
		}


		public boolean isBorderOpaque() {
			return false;
		}


		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(c.getBackground());
			g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
			g2.setColor(color);
			g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
			g2.dispose();
		}
	}
}	
