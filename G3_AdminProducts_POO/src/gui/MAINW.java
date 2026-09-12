package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;

public class MAINW extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JList list;

	/**
	 * Create the panel.
	 */
	public MAINW() {
		setLayout(null);
		{
			btnNewButton = new JButton("New button");
			btnNewButton.addActionListener(this);
			btnNewButton.setBounds(216, 153, 89, 23);
			add(btnNewButton);
		}
		{
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(237, 128, 46, 14);
			add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(57, 69, 86, 20);
			add(textField);
			textField.setColumns(10);
		}
		{
			list = new JList();
			list.setBounds(26, 156, 142, 107);
			add(list);
		}

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		
	}
}
