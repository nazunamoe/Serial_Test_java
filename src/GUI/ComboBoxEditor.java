package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class ComboBoxEditor extends BasicComboBoxEditor {
	private JLabel label = new JLabel();
	private JPanel panel = new JPanel();
	private Object selectedItem;
    
	public ComboBoxEditor() {
        
		label.setOpaque(false);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label.setForeground(Color.BLACK);
        
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		panel.add(label);
		//panel.setBackground(Color.lightGray);
	}
    
	public Component getEditorComponent() {
		return this.panel;
	}
    
	public Object getItem() {
		return this.selectedItem.toString();
	}
    
	public void setItem(Object item) {
		this.selectedItem = item;
		label.setText(item.toString());
	}
    
}