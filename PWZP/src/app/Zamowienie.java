package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	
	/**
	 * Metoda sprawdzająca czy limit rozmiaru zamówienia nie został przekroczony
	 * @return boolean
	 */
	boolean sprawdzRozmiarZamowienia(int i){
		//Przypadek kiedy limit zamówienia nie został przekroczony
		if(Buffor.getRozmiarZamowienia()+i<=25){
			return true;
		}
		//Przypadek kiedy limit zamówienia został przekroczony
		else{
			return false;
		}
	}
	
	/**
	 * Metoda tworząca checkBox dla kreatora własnej pizzy
	 * @param checkBox
	 * @param s
	 * @return checkBox
	 */
	JCheckBox dodajCheckBox(JCheckBox checkBox, String s){
		checkBox = new JCheckBox();
		checkBox.setName(s);
		checkBox.setBorder(null);
		checkBox.setIcon(new ImageIcon("images/icon.png"));
		checkBox.setSelectedIcon(new ImageIcon("images/selectedIcon.png"));
		checkBox.setPressedIcon(new ImageIcon("images/icon.png"));
		checkBox.setRolloverIcon(new ImageIcon("images/icon.png"));
		checkBox.setRolloverSelectedIcon(new ImageIcon("images/selectedIcon.png"));
		checkBox.setMargin(new Insets(0,0,0,27));
		
		return checkBox;
	}
	
	/**
	 * Metoda tworząca label dla checkboxa
	 * @param lbl
	 * @param s
	 * @return lbl
	 */
	JLabel dodajLabel(JLabel lbl, String s){
		Font arial15 = new Font("Arial",Font.PLAIN, 15);
		
		lbl = new JLabel(s);
		lbl.setFont(arial15);
		lbl.setForeground(Color.BLACK);
		
		return lbl;
	}
	
	/**
	 * Metoda drukująca paragon
	 * @param textPane
	 */
	void drukujParagon(JTextPane textPane){
		  final PrinterJob printJob=PrinterJob.getPrinterJob();
          PageFormat pf = printJob.defaultPage();
		  final HashPrintRequestAttributeSet attrs=new HashPrintRequestAttributeSet();
		  attrs.add(OrientationRequested.PORTRAIT);
		  attrs.add(Chromaticity.MONOCHROME);
		  attrs.add(new MediaPrintableArea(0,0,200,150,MediaPrintableArea.MM));	
		  try {
			  textPane.print(null, null, true, null, attrs, false);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda przypisująca ceny pizzy własnej do rozmiaru
	 */
	float ustalCene(JComboBox comboBox, float f){
		if(comboBox.getSelectedItem()=="30cm"){
			f=17;
		}
		else if(comboBox.getSelectedItem()=="40cm"){
			f=25;
		}
		else if(comboBox.getSelectedItem()=="50cm"){
			f=33;
		}
		return f;
	}
}
