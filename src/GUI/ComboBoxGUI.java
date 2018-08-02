package GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import java.awt.Font;

public class ComboBoxGUI extends JLabel implements ListCellRenderer {
	
	public ComboBoxGUI() {
		setOpaque(true);
		setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
	}
	 
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.toString());
		return this;
	}
 
}