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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Renderer dla komponentu JComboBox
 * @author www.codejava.net
 *
 */
public class ItemRenderer extends JPanel implements ListCellRenderer {
	private JLabel labelItem = new JLabel();
	
	public ItemRenderer() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 2;
		constraints.insets = new Insets(1, 0, 1, 0);
		
		labelItem.setOpaque(true);
		labelItem.setHorizontalAlignment(JLabel.LEFT);
		
		add(labelItem, constraints);
		setBackground(new Color(0xeaeaeb));
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String nazwa = (String) value;

		labelItem.setText(nazwa);
		labelItem.setFont(new Font("Arial", Font.PLAIN, 15));
		
		Border line = BorderFactory.createLineBorder(new Color(0xeaeaeb));
		Border empty = new EmptyBorder(0, 4, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		
		labelItem.setBorder(border);
		
		if (isSelected) {
			labelItem.setBackground(new Color(0x939393));
			labelItem.setForeground(Color.WHITE);
		} else {
			labelItem.setForeground(Color.BLACK);
			labelItem.setBackground(new Color(0xeaeaeb));
		}
		
		return this;
	}
}