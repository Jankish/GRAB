import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class GUI extends JFrame{

	JButton confirm;
	JButton abort;
	JButton remove;
	JButton removeAll;
	JList list;
	DefaultListModel<String> model;
	JFileChooser fileChooser;

	public GUI() {
		setSize(750,500);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);	
		setTitle("GRAB v1.0");
		setDefaultLookAndFeelDecorated(true);
		WinListener winList = new WinListener();
		this.addWindowListener(winList);


		//GridBagLayout
		GridLayout gridLayout = new GridLayout();

		//Top Panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());

		//South Panel
		JPanel southPanel = new JPanel();

		//Center Panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(gridLayout);

		//File Chooser
		fileChooser = new JFileChooser();
		//fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter( "xml","kos");
		fileChooser.setApproveButtonText("Välj");
		//fileChooser.addChoosableFileFilter(filter);
		//fileChooser.setFileFilter(filter);
		//centerPanel.add(fileChooser, BorderLayout.WEST);
		FileListener fileList = new FileListener();
		fileChooser.addActionListener(fileList);
		centerPanel.add(fileChooser);

		//Button
		confirm = new JButton("Skapa excel");
		ButtonListener confirmList = new ButtonListener();
		confirm.addActionListener(confirmList);

		abort = new JButton("Avbryt");
		ButtonListener abortList = new ButtonListener();
		abort.addActionListener(abortList);

		remove = new JButton("Ta bort från listan");
		ButtonListener removeList = new ButtonListener();
		remove.addActionListener(removeList);

		removeAll = new JButton("Rensa listan");
		ButtonListener removeAllList = new ButtonListener();
		removeAll.addActionListener(removeAllList);

		//List model
		model = new DefaultListModel<String>();

		//List
		list = new JList(model); 
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		JScrollPane pane = new JScrollPane(list);
		centerPanel.add(pane);

		//Add to Panel

		southPanel.add(abort);
		southPanel.add(remove);
		southPanel.add(removeAll);
		southPanel.add(confirm);

		topPanel.add(centerPanel, BorderLayout.NORTH);	
		topPanel.add(southPanel, BorderLayout.SOUTH);

		//Add to frame
		add(topPanel);

		setVisible(true);

	}

	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == confirm) {
				e.getSource().toString();
				if (model.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Inga filer har valts!", "No file selected", JOptionPane.WARNING_MESSAGE);
				} else {
					System.out.println("Confirm Pressed");
				}
			} else if (e.getSource() == abort) {
				int n = JOptionPane.showConfirmDialog(null, "Vill du avsluta programmet?", "Caution", JOptionPane.YES_NO_OPTION);
				System.out.println("Abort Pressed");
				if (n == 0)
					System.exit(0);
			} else if (e.getSource() == remove) {
				if (model.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Listan är tom!", "Empty list", JOptionPane.WARNING_MESSAGE);
				} else {
					System.out.println(list.getSelectedIndex());
					System.out.println("Remove item");
				}
			} else if (e.getSource() == removeAll) {
				if (model.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Listan är tom!", "Empty list", JOptionPane.WARNING_MESSAGE);
				} else {
					System.out.println("Remove all");
				}
			}
		}

	}

	private class FileListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = (JFileChooser) e.getSource();
			if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
				if (!model.contains(fileChooser.getSelectedFile().getName())) {
					model.addElement(fileChooser.getSelectedFile().getName());
					System.out.println("File selected: " + fileChooser.getSelectedFile().getName());
				}
			}
		}
	}

	private class WinListener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			int n = JOptionPane.showConfirmDialog(null, "Vill du avsluta programmet?", "Caution", JOptionPane.YES_NO_OPTION);
			System.out.println("Abort Pressed");
			if (n == 0)
				System.exit(0);
			System.out.println("Trying to close the window");
		}
	}
}

