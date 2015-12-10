import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JList;

public class GUI extends JFrame{
	
	JButton confirm;
	JButton abort;
	JList list;
	DefaultListModel model;
	JFileChooser fileChooser;
	
	public GUI() {
		setSize(750,500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);	
		setTitle("GRAB v1.0");
		setDefaultLookAndFeelDecorated(true);


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
		
		//List model
		model = new DefaultListModel();

		//List
		list = new JList(model); 
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		JScrollPane pane = new JScrollPane(list);
		centerPanel.add(pane);

		//Add to Panel
		southPanel.add(abort);
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
				System.out.println("Confirm Pressed");
			} else if(e.getSource() == abort) {
				System.out.println("Abort Pressed");
				System.exit(0);
			}
		}

	}
	
	private class FileListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = (JFileChooser) e.getSource();
			if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
				//model.add(fileChooser.getSelectedFile());
				System.out.println("File selected: " + fileChooser.getSelectedFile().getName());
			}
		}
	}
}

