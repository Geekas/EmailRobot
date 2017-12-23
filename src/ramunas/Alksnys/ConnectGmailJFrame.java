package ramunas.Alksnys;

import javax.swing.JButton;
import java.nio.file.Files;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;

public class ConnectGmailJFrame extends JFrame {
	// Panel
	private JPanel mainPanel;
	// Labels
	private JLabel userL;
	private JLabel passL;
	// Fields
	private JTextField userF;
	private JPasswordField passF;
	// Button
	private JButton submit;	
	
	private WorkWithSelenium ws;

	public ConnectGmailJFrame(WorkWithSelenium ws) {
		this.ws = ws;
		configLabels();
		configFields();
		configButtons();
		configMainPanel();
		configJFrame();
		List<String> records = new ArrayList();
		records = Files.readAllLines(Paths.get(""));
	}

	private void configMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		addComp(mainPanel, userL, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(mainPanel, userF, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(mainPanel, passL, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(mainPanel, passF, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(mainPanel, submit, 0, 2, 2, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
	}

	private void configFields() {
		passF = new JPasswordField();
		userF = new JTextField();
		userF.setColumns(15);
		userF.setFocusable(false);
		userF.setFocusable(true);

		// userF.addFocusListener(new FocusListener() {
		// public void focusGained(FocusEvent e) {
		// userF.setText("");
		// }
		//
		// @Override
		// public void focusLost(FocusEvent e) {
		// }
		// });
		passF.setColumns(15);
	}

	private void configLabels() {
		userL = new JLabel(Const.userLabel);
		passL = new JLabel(Const.passLabel);
	}

	private void configButtons() {
		submit = new JButton(Const.bSubmit);
		submit.addActionListener(e -> {
			if (!userF.getText().isEmpty()&& passF.getPassword().length != 0){
				ws = new WorkWithSelenium();
				try {
					ws.logInWithData(userF.getText(), passF.getPassword());
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new MainJFrame(ws);
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, Const.errorText, "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	private void configJFrame() {
		this.add(mainPanel);		
		this.setTitle(Const.titleConGmail);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place,
			int stretch) {
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compHeight;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;
		thePanel.add(comp, gridConstraints);
	}
}
