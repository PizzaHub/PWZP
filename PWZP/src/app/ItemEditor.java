package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 * Edytor dla komponentu JComboBox
 * @author www.codejava.net
 *
 */
public class ItemEditor extends BasicComboBoxEditor {
	private JPanel panel = new JPanel();
	private JLabel labelItem = new JLabel();
	private String selectedValue;
	
	public ItemEditor() {
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(3, 5, 2, 2);
		
		labelItem.setOpaque(false);
		labelItem.setHorizontalAlignment(JLabel.LEFT);
		//labelItem.setForeground(Color.BLACK);
		labelItem.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(labelItem, constraints);
		panel.setBackground(new Color(0xeaeaeb));	
	}
	
	public Component getEditorComponent() {
		return this.panel;
	}
	
	public Object getItem() {
		return this.selectedValue;
	}
	
	public void setItem(Object item) {
		if (item == null) {
			return;
		}
		String items = (String) item;
		selectedValue = items;
		labelItem.setText(selectedValue);	
	}	
	
}