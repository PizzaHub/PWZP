package app;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * Klasa umożliwiająca zaprojektowanie własnego przycisku dla komponentu JComboBox
 *
 */
class ColorArrowUI extends BasicComboBoxUI {

    public static ComboBoxUI createUI(JComponent c) {
        return new ColorArrowUI();
    }

    @Override protected JButton createArrowButton() {
    	JButton btn= new JButton(new ImageIcon("images/a2.png"));
    	btn.setBackground(new Color(0xeaeaeb));
    	btn.setBorder(null);
    	return btn;
    }
}
