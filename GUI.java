import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.File;
public class GUI extends JFrame{

	JButton confirm;
	JButton abort;
	JButton remove;
	JButton removeAll;
	JList list;
	DefaultListModel<String> model;
	JFileChooser fileChooser;
	ArrayList<ListData> fileList;
	
	public GUI() {
		fileList = new ArrayList<ListData>();
		setSize(750,500);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".kos", "kos");
		fileChooser.setApproveButtonText("Välj");
		//fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		centerPanel.add(fileChooser);

		//Button
		confirm = new JButton("Skapa excel");

		abort = new JButton("Avbryt");

		remove = new JButton("Ta bort från listan");

		removeAll = new JButton("Rensa listan");

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


	}

	public void addConfirmListener(ActionListener confirmListener) {
		confirm.addActionListener(confirmListener);
	}

	public void addAbortListener(ActionListener abortListener) {
		abort.addActionListener(abortListener);
	}
	
	public void addRemoveListener(ActionListener removeListener) {
		remove.addActionListener(removeListener);
	}
	
	public void addRemoveAllListener(ActionListener removeAllListener) {
		removeAll.addActionListener(removeAllListener);
	}

	public void addFileListener(ActionListener fileListener) {
		fileChooser.addActionListener(fileListener);
	}	

	public void addFrameListener(WindowListener winListener) {
		this.addWindowListener(winListener);
	}

	public boolean isModelEmpty() {
		return model.isEmpty();
	}

	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}

	public void addElement(String filename, File filepath) {
		model.addElement(filename);
		ListData ld = new ListData(filename, filepath);
		fileList.add(ld);
	}

	public void removeElement(int index) {
		System.out.println(fileList.get(index));	
		model.remove(index);
		fileList.remove(index);
	}

	public void removeAllElements() {
		model.removeAllElements();
		fileList.clear();
	}

	public boolean contains(String filename) {
		return model.contains(filename);
	}

	public String getFileName() {
		return fileChooser.getSelectedFile().getName();	
	}

	public void setVis() {
		setVisible(true);
	}

	public File getFilePath() {
		return fileChooser.getSelectedFile();	
	}
}

