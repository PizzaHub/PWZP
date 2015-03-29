package app;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Klasa pozwalająca wykonanie zindywidualizowanego komponentu JComboBox
 * @author www.codejava.net
 */
public class ComboBox extends JComboBox {
	private DefaultComboBoxModel model;
	
	public ComboBox() {
		model = new DefaultComboBoxModel();
		setModel(model);
		setRenderer(new ItemRenderer());
		setEditor(new ItemEditor());
	}
	
	/**
	 * Metoda dodająca elementy tablicy do komponentu
	 * Każdy element tablicy jest ciągiem znaków
	 * @param items
	 */
	public void addItem(String[] items) {
		for (String anItem : items) {
			model.addElement(anItem);
		}
	}
}