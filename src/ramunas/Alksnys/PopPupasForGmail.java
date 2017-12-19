package ramunas.Alksnys;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PopPupasForGmail extends JFrame {
	private JPanel mainPanel;
	private JButton yes;
	private JButton noConectAuto;
	private JButton noConectMan;
	private JLabel quest;
	private WorkWithSelenium ws;
	private ConnectGmailJFrame conGmail;

	public PopPupasForGmail() {
		configLabel();
		configButtons();
		configPanel();
		configJFrame();
	}

	private void configLabel() {
		quest = new JLabel(Const.questText);

	}

	private void configPanel() {
		mainPanel = new JPanel(new GridBagLayout());
		addComp(mainPanel, quest, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		// addComp(mainPanel, yes, 0, 1, 1, 1, GridBagConstraints.CENTER,
		// GridBagConstraints.NONE);
		addComp(mainPanel, noConectAuto, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComp(mainPanel, noConectMan, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

	}

	private void configButtons() {
//		yes = new JButton();
//		yes.setPreferredSize(new Dimension(300, 20));
//		yes.setText(Const.yesText);
//		yes.addActionListener(e -> {
//			this.dispose();
//			new MainJFrame(ws);
//		});
		noConectAuto = new JButton();
		noConectAuto.setPreferredSize(new Dimension(300, 20));
		noConectAuto.setText(Const.noConnAutoText);
		noConectAuto.addActionListener(e -> {
			this.dispose();			
				conGmail = new ConnectGmailJFrame(ws);				
													
		});

		noConectMan = new JButton();
		noConectMan.setPreferredSize(new Dimension(300, 20));
		noConectMan.setText(Const.noConnManText);
		noConectMan.addActionListener(e -> {
			this.dispose();
			JOptionPane.showMessageDialog(null, Const.reminderText, "Friendly request", JOptionPane.INFORMATION_MESSAGE);
			ws = new WorkWithSelenium();
			ws.letUserConManual();
			new MainJFrame(ws);
		});
	}

	private void configJFrame() {
		this.add(mainPanel);
		this.setTitle(Const.popUpTitle);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
