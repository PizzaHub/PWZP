package app;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Klasa umożliwiająca ograniczenie liczby wprowadzanych znaków dla komponentu JTextField
 *
 */
public class TextFieldLimit extends PlainDocument {
	
	/**
	 * Zmienna przechowująca limit wprowadzanych znaków
	 */
	private int limit;
	
	/**
	 * Konstruktor z parametrem klasy TextFieldLimit
	 * @param limit
	 */
	public TextFieldLimit(int limit){
	    this.setLimit(limit);
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getLimit() {
		return limit;
	}
	
	/**
	 * Metoda z rozszerzanej klasy PlainDocument
	 */
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			//Jeśli string jest pusty to nic nie zwraca
		    if (str == null)
		      return;
		    //Jeśli tekst wprowadzony plus jego długość jest mniejszy lub równy z limitem to zostanie wyświetlony
		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
	//offset - offset(przesunięcie) w dokumencie do wprowadzania zawartości wiekszej lub rownej 0
	//str - łańcuch znaków do wprowadzania
	//attr - atrybuty dla wprowadzanej zawartośći
}	
	
