import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Connect4View {
	private JFrame frame;
	private final List<JButton> list = new ArrayList<JButton>();
	private int height = 6;
	private int width = 7;

	public Connect4View() {
		frame = new JFrame("Connect4");

		JPanel p = new JPanel(new GridLayout(height, width));
		for (int i = 0; i < height * width; i++) {
			MyButton button = new MyButton();
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					button.isClicked = true;
					button.repaint();
				}
			});
			list.add(button);
			p.add(button);
		}

		frame.add(p);
		frame.pack();
		frame.setSize(550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	class MyButton extends JButton {
		private boolean isClicked = false;

		@Override
		public void paintComponent(Graphics g) {
			if (!isClicked) {
				super.paintComponent(g);
			} else {
				g.setColor(Color.RED);
				g.fillOval(10, 10, 60, 60);
			}

		}
	}

	public static void main(String[] args) {
		Connect4View c = new Connect4View();
	}
}
