package app;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.jgoodies.forms.layout.CellConstraints.Alignment;
import com.jgoodies.forms.layout.Size;

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
	
	/**
	 * Metoda służąca do wyświetlania podglądu zamówienia w cenniku
	 * @param textArea
	 * @param string1
	 * @param string2
	 * @param string3
	 * @param integer
	 */
	void wyswietlPodgladZamowienia(JTextArea textArea, String string1, String string2, String string3, int integer){
		if(string1.equals("Brak")){
			textArea.append(string2+"\t\t       "+string3+"\t             "+"x"+integer+"\n");	
		}
		else{
			textArea.append(string2+"\t\t       "+string3+"\t             "+"x"+integer+"\n");	
			textArea.append("+ sos "+string1.toLowerCase()+"\n");
		}
	}
	
	/**
	 * Metoda służąca do wyświetlania podglądu zamówienia w cenniku wraz z pizzą własną
	 * @param textArea
	 * @param string1
	 * @param string2
	 * @param string3
	 * @param integer
	 */	
	void wyswietlPodgladZamowieniaWlasna(JTextArea textArea, String string1, String string2, String string3, int integer){
		if(string2.equals("Brak")){
			textArea.append(string1+"\t\t       "+string3+"\t             "+"x"+integer+"\n");	
		}
		else {
			textArea.append(string1+"\t\t       "+string3+"\t             "+"x"+integer+"\n");	
			textArea.append("+ sos "+string2.toLowerCase()+"\n");
		}
			
	}
	/**
	 * Metoda służąca do wyświetlania łącznego kosztu zamówienia
	 * @param lbl
	 * @param float1
	 * @param dec
	 */
	void wyswietlLacznyKoszt(JLabel lbl, double float1, DecimalFormat dec){
		lbl.setText((dec.format(float1)));
	}

	
	/**
	 * Metoda służąca do wyświetlania informacji: nazwa pizzy, rozmiar pizzy, liczba pizz na podglądzie zamówienia na ekranie dostawy
	 * @param s
	 * @param pane
	 */
	void wyswietlPizze(String s, JTextPane pane) {
		   try {
		      Document doc = pane.getDocument();
		      SimpleAttributeSet attributes = new SimpleAttributeSet();
		      attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
		      attributes.addAttribute(StyleConstants.CharacterConstants.Size, 17);
		      doc.insertString(doc.getLength(), s, attributes);
		   } catch(BadLocationException exc) {
		      exc.printStackTrace();
		   }
	}
	
	/**
	 * Metoda służąca do wyświetlania składników zamówionej pizzy na podglądzie zamówienia na ekranie dostawy
	 * @param s
	 * @param pane
	 */
	void wyswietlSkladniki(String s, JTextPane pane) {
		   try {
		      Document doc = pane.getDocument();
		      SimpleAttributeSet attributes = new SimpleAttributeSet();
		      attributes.addAttribute(StyleConstants.CharacterConstants.Size, 15);
		      doc.insertString(doc.getLength(), s, attributes);
		   } catch(BadLocationException exc) {
		      exc.printStackTrace();
		   }
	}
	/**
	 * Metoda służąca do wyświetlania wybranego sosu na podglądzie zamówienia na ekranie dostawy
	 * @param s
	 * @param pane
	 */
	void wyswietlSos(String s1, String s2, JTextPane pane) {
		if(s1.equals("Brak")){
		}
		else{
			try {
				Document doc = pane.getDocument();
				SimpleAttributeSet attributes = new SimpleAttributeSet();
				attributes.addAttribute(StyleConstants.CharacterConstants.Size, 16);
				doc.insertString(doc.getLength(), s2.toLowerCase(), attributes);
			} 
			catch(BadLocationException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda służąca do wyświetlania informacji: nazwa firmy, adres na podglądzie paragonu na ekranie zamówienia
	 * @param s
	 * @param pane
	 */
	void wyswietlNaglowekParagonu(String s, JTextPane pane) {
		try {   
			StyledDocument doc2 = pane.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc2.setParagraphAttributes(0, doc2.getLength(), center, false);
		      
			SimpleAttributeSet attributes2 = new SimpleAttributeSet();
			attributes2.addAttribute(StyleConstants.CharacterConstants.Size, 16);
		      
			doc2.insertString(doc2.getLength(), s, attributes2);
		} 
		catch(BadLocationException exc) {
			exc.printStackTrace();
		}
	}
	
	/**
	 * Metoda służąca do wyświetlania daty i godziny na podglądzie paragonu na ekranie zatwierdzania zamówienia
	 * @param pane
	 */
	void wyswietlDateNaParagonie(JTextPane pane){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		
		DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
		try {       
			StyledDocument doc3 = pane.getStyledDocument();
			SimpleAttributeSet center2 = new SimpleAttributeSet();
			StyleConstants.setAlignment(center2, StyleConstants.ALIGN_LEFT);
			doc3.setParagraphAttributes(doc3.getLength(), doc3.getLength(), center2, false);
		      
			SimpleAttributeSet attributes2 = new SimpleAttributeSet();
			attributes2.addAttribute(StyleConstants.CharacterConstants.Size, 16);
		      
			doc3.insertString(doc3.getLength(), dateFormat.format(cal.getTime())+"\t\t"+dateFormat2.format(cal.getTime())+"\n***********************"
					+ "*********************\n", attributes2);
		} 
		catch(BadLocationException exc) {
			exc.printStackTrace();
		}
		
	}
	
	/**
	 * Metoda służąca do wyświetlania informacji: nazwa pizzy, rozmiar pizzy, liczba pizz, cena jednostkowa na podglądzie paragonu na 
	 * ekranie zatwierdzania zamówienia
	 * @param s
	 * @param pane
	 */
	void wyswietlPizzeNaParagonie(String s, JTextPane pane){
		try {       
			StyledDocument doc3 = pane.getStyledDocument();
			SimpleAttributeSet center2 = new SimpleAttributeSet();
			StyleConstants.setAlignment(center2, StyleConstants.ALIGN_LEFT);
			doc3.setParagraphAttributes(doc3.getLength(), doc3.getLength(), center2, false);
		      
			SimpleAttributeSet attributes2 = new SimpleAttributeSet();
			attributes2.addAttribute(StyleConstants.CharacterConstants.Size, 16);
		      
			doc3.insertString(doc3.getLength(), s, attributes2);
		} 
		catch(BadLocationException exc) {
			exc.printStackTrace();
		}		
	}
	
	/**
	 * Metoda służąca do wyświetlania wybranego rodzaju sosu na podglądzie paragonu na ekranie zatwierdzania zamówienia
	 * @param s1
	 * @param s2
	 * @param pane
	 */
	void wyswietlSosNaParagonie(String s1, String s2, JTextPane pane) {
		if(s1.equals("Brak")){
		}
		else{
			try {
				Document doc = pane.getDocument();
				SimpleAttributeSet attributes = new SimpleAttributeSet();
				attributes.addAttribute(StyleConstants.CharacterConstants.Size, 16);
				doc.insertString(doc.getLength(), s2, attributes);
			} 
			catch(BadLocationException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	/**
	 * Metoda służąca do wyświetlania łącznego kosztu zamówienia na podglądzie paragonu na ekranie zatwierdzania zamówienia
	 * @param s1
	 * @param s2
	 * @param pane
	 */
	void wyswietlPodsumowanieParagonu(String s1, String s2, JTextPane pane) {
		try {
			Document doc = pane.getDocument();
			SimpleAttributeSet attributes = new SimpleAttributeSet();
			attributes.addAttribute(StyleConstants.CharacterConstants.Size, 16);
			doc.insertString(doc.getLength(),"********************************************\n"+s1+"\t\t"+s2, attributes);
		} 
		catch(BadLocationException exc) {
			exc.printStackTrace();
		}
	}
	

}
