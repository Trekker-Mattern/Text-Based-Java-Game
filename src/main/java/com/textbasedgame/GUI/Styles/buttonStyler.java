package com.textbasedgame.GUI.Styles;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;


import java.awt.BasicStroke;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;

public class buttonStyler {

	public static void styleEnterButton(JButton inputButton){
		final int radius = 10;

		Color baseBackground = new Color(50, 50, 50);
		Color hoverBackground = baseBackground.darker();
		RoundedButtonUI ui = new RoundedButtonUI(radius);


		inputButton.setContentAreaFilled(false);
		inputButton.setOpaque(false);
		inputButton.setFocusPainted(false);
		inputButton.setBorder(new RoundedBorder(Color.BLACK, radius));
		inputButton.setUI(ui);
		inputButton.setBackground(baseBackground);
		inputButton.setPreferredSize(new Dimension(100, 30));
		
		inputButton.setForeground(Color.WHITE);
		inputButton.setFont(new Font("Times New Roman", Font.BOLD, 16));

		// Add mouse listener to change background color on hover
		inputButton.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				inputButton.setBackground(hoverBackground);
				inputButton.repaint();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				inputButton.setBackground(baseBackground);
				inputButton.repaint();
			}
		});
	}
	

	public static void styleTitleScreenButton(JButton inputButton, boolean resizeToDefault){

		final int radius = 20;

		Color baseBackground = new Color(50, 50, 50);
		Color hoverBackground = baseBackground.darker();
		RoundedButtonUI ui = new RoundedButtonUI(radius);


		inputButton.setContentAreaFilled(false);
		inputButton.setOpaque(false);
		inputButton.setFocusPainted(false);
		inputButton.setBorder(new RoundedBorder(Color.BLACK, radius));
		inputButton.setUI(ui);
		inputButton.setBackground(baseBackground);
		
		inputButton.setForeground(Color.WHITE);
		inputButton.setFont(new Font("Times New Roman", Font.BOLD, 32));

		// Add mouse listener to change background color on hover
		inputButton.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				inputButton.setBackground(hoverBackground);
				inputButton.repaint();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				inputButton.setBackground(baseBackground);
				inputButton.repaint();
			}
		});
	}

	public static void setDefaultButtonSize(JButton button) {
		button.setPreferredSize(new Dimension(200, 50));
		button.setMinimumSize(new Dimension(20, 5));
	}


	private static class RoundedBorder implements Border {

		private int radius;
		private Color color;

		RoundedBorder(int radius) {
			this.radius = radius;
			color = Color.BLACK;
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
			Graphics2D graphicsCopy = (Graphics2D) g.create();
			graphicsCopy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphicsCopy.setColor(color);
			graphicsCopy.setStroke(new BasicStroke(5f));
			graphicsCopy.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
			graphicsCopy.dispose();
		}
	}


	private static class RoundedButtonUI extends BasicButtonUI {

		private final int radius;

		RoundedButtonUI(int radius) {
			this.radius = radius;
		}

		@Override
		public void paint(Graphics g, JComponent c) {
			Graphics2D graphicsCopy = (Graphics2D) g.create();
			graphicsCopy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphicsCopy.setColor(c.getBackground());
			graphicsCopy.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, radius, radius);
			super.paint(graphicsCopy, c);
			graphicsCopy.dispose();
		}
	}
}	
