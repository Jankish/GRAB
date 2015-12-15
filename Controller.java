import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Controller {

	private Model model;
	private GUI gui;

	public Controller(Model model, GUI gui) {
		this.model = model;
		this.gui = gui;

		// Add listeners
		this.gui.addConfirmListener(new ConfirmListener());
		this.gui.addAbortListener(new AbortListener());
		this.gui.addRemoveListener(new RemoveListener());
		this.gui.addRemoveAllListener(new RemoveAllListener());
		this.gui.addFileListener(new FileListener());
		this.gui.addFrameListener(new WinListener());
	}

	//ButtonListeners
	public class ConfirmListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gui.isModelEmpty()) {
				JOptionPane.showMessageDialog(null, "Inga filer har valts!", "No file selected", JOptionPane.WARNING_MESSAGE);
			} else {
				model.process(gui.getFileList());
				System.out.println("Confirm Pressed");
			}
		}
	}

	public class AbortListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int n = JOptionPane.showConfirmDialog(null, "Vill du avsluta programmet?", "Caution", JOptionPane.YES_NO_OPTION);
			System.out.println("Abort Pressed");
			if (n == 0)
				System.exit(0);
		}
	}

	public class RemoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gui.isModelEmpty()) {
				JOptionPane.showMessageDialog(null, "Listan är tom!", "Empty list", JOptionPane.WARNING_MESSAGE);
			} else if (gui.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Markera vilken fil du vill ta bort", "No file selected", JOptionPane.WARNING_MESSAGE);
				System.out.println("Implement dialog here");
			} else {
				System.out.println(gui.getSelectedIndex());
				gui.removeElement(gui.getSelectedIndex());
				System.out.println("Remove item");
			}
		}
	}

	public class RemoveAllListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gui.isModelEmpty()) {
				JOptionPane.showMessageDialog(null, "Listan är tom!", "Empty list", JOptionPane.WARNING_MESSAGE);
			} else {
				gui.removeAllElements();
				System.out.println("Remove all");
			}
		}
	}

	// File Listener
	public class FileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = (JFileChooser) e.getSource();
			if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
				String fileName = gui.getFileName();
				if (!gui.contains(gui.getFileName()) && validate(gui.getFileName())) {
					gui.addElement(gui.getFileName(), gui.getFilePath());
					System.out.println("File selected: " + gui.getFileName() + " Path " + gui.getFilePath());
				}
			}
		}
	}

	//Window Listener
	public class WinListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			int n = JOptionPane.showConfirmDialog(null, "Vill du avsluta programmet?", "Caution", JOptionPane.YES_NO_OPTION);
			System.out.println("Abort Pressed");
			if (n == 0)
				System.exit(0);
		}
	}

	private boolean validate(String filename) {
		return filename.endsWith(".kos");	
	}
}
