package ramunas.Alksnys;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class MainJFrame extends JFrame {
	private FileManager manager;
	// Panels
	private JPanel mainPanel;
	private JPanel leftSideP;
	private JPanel righSideP;
	private JPanel buttonP;
	// JScrolls
	private JScrollPane fileChoserBar;
	private JScrollPane letterContBar;
	private JScrollPane proccessBar;
	// Labels
	private JLabel subjectL;
	private JLabel letterContL;
	private JLabel chosenFilesL;
	// Buttons
	private JButton selectAFile;
	private JButton clearFList;
	private JButton selContFile;
	private JButton startSend;
	// Text field
	private JTextField subjectField;
	// Text Area
	private JTextArea letterContArea;
	private JTextArea fileChooserLog;
	private JTextArea proccessLog;
	// Selenium object
	WorkWithSelenium ws;

	private String userName, password;

	public MainJFrame(WorkWithSelenium ws) {
		manager = new FileManager();
		this.ws = ws;
		configLabels();
		configFields();
		configTextAreas();
		configJScroll();
		configButtons();
		configMainPanel();
		configJFrame();
	}

	private void configTextAreas() {
		letterContArea = new JTextArea(10, 40);
		letterContArea.setLineWrap(true);
		letterContArea.setWrapStyleWord(true);
		fileChooserLog = new JTextArea(14, 20);
		// fileChooserLog.setLineWrap(true);
		// fileChooserLog.setWrapStyleWord(true);
		fileChooserLog.setEditable(false);
		proccessLog = new JTextArea(15, 15);
		proccessLog.setLineWrap(true);
		proccessLog.setWrapStyleWord(true);
		proccessLog.setEditable(false);

	}

	private void configJScroll() {
		fileChoserBar = new JScrollPane(fileChooserLog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		letterContBar = new JScrollPane(letterContArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		proccessBar = new JScrollPane(proccessLog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	}

	private void configFields() {
		subjectField = new JTextField();
		subjectField.setColumns(30);

	}

	private void configLabels() {
		subjectL = new JLabel(Const.letterSubText);
		letterContL = new JLabel(Const.letterContText);
		chosenFilesL = new JLabel(Const.chosenFileL);

	}

	private void configJFrame() {
		this.add(mainPanel);
		this.setTitle(Const.titleMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void configMainPanel() {
		leftSideP = new JPanel(new GridBagLayout());
		addComp(leftSideP, subjectL, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5);
		addComp(leftSideP, subjectField, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5);
		addComp(leftSideP, letterContL, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5);
		addComp(leftSideP, letterContBar, 0, 3, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, 5);

		righSideP = new JPanel(new GridBagLayout());
		addComp(righSideP, chosenFilesL, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5);
		addComp(righSideP, fileChoserBar, 0, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, 5);

		buttonP = new JPanel(new GridBagLayout());
		addComp(buttonP, selectAFile, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5);
		addComp(buttonP, clearFList, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5);
		addComp(buttonP, selContFile, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5);

		mainPanel = new JPanel(new GridBagLayout());
		addComp(mainPanel, leftSideP, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, 5);
		addComp(mainPanel, righSideP, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, 5);
		addComp(mainPanel, buttonP, 3, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, 40);

		addComp(mainPanel, proccessBar, 0, 4, 4, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
		addComp(mainPanel, startSend, 3, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, 5);
	}

	private void configButtons() {
		selectAFile = new JButton(Const.selectAFileText);
		selectAFile.setPreferredSize(new Dimension(150, 20));
		selectAFile.addActionListener(e -> choseFile());
		clearFList = new JButton(Const.clearFListText);
		clearFList.setPreferredSize(new Dimension(150, 20));
		clearFList.addActionListener(e -> clearList());
		selContFile = new JButton(Const.selectContFileText);
		selContFile.setPreferredSize(new Dimension(150, 20));
		selContFile.addActionListener(e -> selectFile());
		startSend = new JButton(Const.startSendText);
		startSend.setPreferredSize(new Dimension(150, 20));
		startSend.addActionListener(e->startSend());
	}

	private void startSend() {
		this.setState(this.ICONIFIED);
		ws.startSendEmails(subjectField.getText(),letterContArea.getText() , manager, proccessLog);
		this.setState(this.NORMAL);
	}

	private void selectFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle(Const.selectContText);

		int returnValue = jfc.showDialog(null, "Select file");
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			manager.setContFile(jfc.getSelectedFile().getPath());
			fileChooserLog.append(
					new StringBuilder().append("File selected:\n ").append(jfc.getSelectedFile().getPath()).toString());
			proccessLog.append("Contact file: " + manager.getContFile() + "\n");
		}
	}

	private void clearList() {
		manager.clearList();
		fileChooserLog.setText("");
		if (!manager.getContFile().isEmpty()) {
			fileChooserLog.append("Contact file:\n ");
			fileChooserLog.append(manager.getContFile() + "\n");
		}
	}

	private void choseFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle(Const.selectText);

		int returnValue = jfc.showDialog(null, "\nAtach file");
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			manager.addFile(jfc.getSelectedFile().getPath());
			fileChooserLog.append(new StringBuilder().append("File selected:\n ")
					.append(jfc.getSelectedFile().getPath()).append("\n").toString());
		}
	}

	private void openConnGmail() {
		ConnectGmailJFrame conGmail = new ConnectGmailJFrame(ws);
	}

	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place,
			int stretch, int topInset) {
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compHeight;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(topInset, 5, 5, 5);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;
		thePanel.add(comp, gridConstraints);

	}
}
