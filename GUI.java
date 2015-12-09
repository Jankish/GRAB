import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JFrame{
	
	JButton button1;

	public GUI() {
		setSize(800,800);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);	
		setTitle("GRAB v1.0");

		//Panel
		JPanel panel = new JPanel();

		//Label
		JLabel label1 = new JLabel("Tell me something");
		
		//TextField
		JTextField field1 = new JTextField("", 15);
		field1.setToolTipText("Put information here");
		
		//Button
		button1 = new JButton("Send");
		button1.setToolTipText("Put information here");
		ButtonListener buttonL1 = new ButtonListener();
		button1.addActionListener(buttonL1);

		//Add to Panel
		panel.add(field1);
		panel.add(label1);
		panel.add(button1);
		
		//Add to frame
		add(panel);

		setVisible(true);

	}

	private class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) {
				//do Something
	//			field1.append("Button clicked");
				e.getSource().toString();
				System.out.println("Button Pressed");
			}
		}

	}
}

