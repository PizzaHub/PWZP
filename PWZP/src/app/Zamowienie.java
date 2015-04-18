package app;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Klasa implementująca funkcjonalności aplikacji
 */
public class Zamowienie {
	
	/**
	 * Metoda umożliwiajaca przeszukiwanie tabeli
	 * @param sorter
	 * @param txtField
	 */
	void szukajPizzy(TableRowSorter<TableModel> sorter, JTextField txtField){
        String text = txtField.getText();
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } else {
          sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
        }		
	}
}
