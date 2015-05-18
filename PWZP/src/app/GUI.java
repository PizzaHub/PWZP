package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MaskFormatter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;












import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.CellConstraints.Alignment;
import com.jgoodies.forms.layout.FormLayout;


/** 
* Program do wspomagania zarządzaniem pizzerią
* Klasa GUI definiująca okno aplikacji	 	
* @version 1.0	27/04/2015
*/
public class GUI extends JFrame implements ActionListener{
	/**
	 * Obiekty innych klas
	 */
	private Zamowienie zamowienie=new Zamowienie();
	private Dialog dialog;
	private Buffor buffor=new Buffor();
	private Pomoc pomoc;
	
	/**
	 * Deklaracja górnego i dolnego panelu
	 */

	JPanel panelGorny, panelDolny;
	
	/**
	 * Deklaracja kart dla menadżera rozkładu
	 */
	private JPanel ekranStartowy, cennik, ekranDostawy, ekranZatwierdzaniaZamowienia, ekranWlasnaPizza;

	
	/**
	 * Deklaracja przycisków
	 */
	private JButton btnZamknij, btnMinimalizuj, btnAnulujZamowienie, 
	btnAnulujZamowienie3, btnAnulujZamowienie4;
	
	/**
	 * Deklaracja komponentów ekranu startowego
	 */
	private int paragon; //Zmienna, której wartość wskazuje, czy w polu Podgląd paragonu wyświetlić informacje wstępne
	private JButton btnZamowzMenu, btnZamowzMenu2, btnZamowWlasna, btnZamowWlasna2, btnPomoc;
	private JLabel lblStartowyLogo, lblPasekStanuEkranStartowy;
	
	/**
	 * Deklaracja komponentów cennika
	 */
	private JLabel lblPasek, lblPasekMin, lblCennik, lbl30cm, lbl40cm, lbl50cm, lblSzukaj, lblPasekStanu, lblPodgladZamowienia, 
		lblLacznyKosztZamowienia, lblWyswietlLacznyKosztZamowienia;
	private JButton btnOK, btnAnuluj, btnDostawa3, btnStartowyZCennika;
	private JTextField txtSzukaj;
	private JTextArea txtrZamowienie;
	private String[] kolumny = {"Nazwa", "Rozmiar1","Rozmiar2", "Rozmiar3"};
	private JScrollPane scrollPane, scrollPane2;
	private TableRowSorter<TableModel> sorter;
	private JTable cennikTabela;
	private DecimalFormat dec = new DecimalFormat("##0.00");
	private JTextPane textPane2;
	private StyledDocument document2;
    
	/**
	 * Deklaracja komponenetów ekranu dostawy
	 */
	private JButton btnAnulujZamowienie2, btnDodajDoZamowienia, btnPotwierdzenie2, btnCennik;
	private JLabel lblDostawaLogo, lblDostawaText1, lblDostawaText2, lblDostawaText3, 
	lblDostawaText4, lblDostawaText5, lblDostawaText6,lblDostawaText7, lblDostawaText8, lblPasekStanuEkranDostawy, lblNumerTelefonu
	,lblMiejscowosc, lblUlica, lblNrBudynku, lblNrMieszkania, lblLacznyKoszt;
	private JTextField txtMiejscowosc, txtUlica, txtNrBudynku, txtNrMieszkania;
	private JFormattedTextField txtNumerTelefonu;
	private JTextPane textPane;
	private String[] listaDostawa = {"Na miejscu", "Na wynos", "Z dowozem"};
	private float[] kosztDostawy={0, 1, 5};
	private ComboBox comboBoxDostawa;
	private JScrollPane scrollPane3;
	private StyledDocument document;
	private boolean dostawa=false; //Zmienna wskazująca, z którego ekranu do ekranu dostawy przeszedł użytkownik
	
	/**
	 * Deklaracja komponentów ekranu zatwierdzania zamówienia
	 */
	private JLabel lblZatwierdzenieNaglowek, lblPodgladParagonu, lblDaneZamawiajacego, lblSposobDostawy,
		lblKosztDostawy, lblLacznyKosztZ, lblVAT, lblPasekStanuZatwierdzanie;
	private JTextField txtSposobDostawy, txtKosztDostawy, txtLacznyKosztZ, txtVAT;
	private JTextArea txtrDaneZamawiajacego;
	private JButton btnZatwierdz, btnAnulujZ, btnDostawa4, btnDrukujParagon;
	private JScrollPane scrollPane4;
	
	/**
	 * Deklaracja komponentów ekranu własnej pizzy
	 */
	private JLabel lblWlasnaPizzaText1,lblWlasnaPizzaText2, lblWlasnaPizzaText3, lblWlasnaPizzaText4, 
	lblWlasnaPizzaNaglowek, lblPasekStanuWlasnaPizza,
	lblCheckBox1, lblCheckBox2, lblCheckBox3,lblCheckBox4, lblCheckBox5, lblCheckBox6, lblCheckBox7, lblCheckBox8, lblCheckBox9, lblCheckBox10,
	lblCheckBox11, lblCheckBox12, lblCheckBox13, lblCheckBox14, lblCheckBox15, lblCheckBox16, lblCheckBox17, lblCheckBox18, lblCheckBox19, lblCheckBox20,
	lblCheckBox21, lblCheckBox22, lblCheckBox23, lblCheckBox24, lblCheckBox25, lblCheckBox26, lblCheckBox27, lblCheckBox28, lblCheckBox29, lblCheckBox30,
	lblCheckBox31, lblCheckBox32, lblCheckBox33, lblCheckBox34, lblCheckBox35, lblCheckBox36, lblCheckBox37, lblCheckBox38, lblCheckBox39, lblCheckBox40,
	lblPasekWlasnaPizza;
	private JButton btnStartowyZWlasnaPizza, btnDostawa5;
	private ComboBox customCombobox, customCombobox2;
	private JSpinner spinLiczbaPizz;
	private String[] listaRozmiarow = {"30cm", "40cm", "50cm"};
	private String[] listaSosow = {"Brak", "Czosnkowy", "Ostry"};
	private JCheckBox checkBox1,checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10,
	checkBox11, checkBox12, checkBox13, checkBox14, checkBox15, checkBox16, checkBox17, checkBox18, checkBox19, checkBox20,
	checkBox21, checkBox22, checkBox23, checkBox24, checkBox25, checkBox26, checkBox27, checkBox28, checkBox29, checkBox30,
	checkBox31, checkBox32, checkBox33, checkBox34, checkBox35, checkBox36, checkBox37, checkBox38, checkBox39, checkBox40;
	private int liczbaZaznaczonych = 0; //Zmienna potrzebna do zliczania liczby zaznaczonych składników
    
	/**
     * Deklaracja zmiennej ceny własnej pizzy
     */
    float cenaWlasnejPizzy;
    
    /**
     * Deklaracja zmiennej pomocniczej do wykrywania 3 wybranego składnika
     */
    int liczydlo=0;
    
    /**
     * Deklaracja zmiennej przechowującej nazwę ostatniego zaznaczonego check boxa
     */
    String ostatniSkladnik="";
    
    /**
     * Deklaracja zmiennych do przechowywania stanów zaznaconych check boxów
     */
	boolean sprawdzStan1, sprawdzStan2, sprawdzStan3, sprawdzStan4, sprawdzStan5, sprawdzStan6, sprawdzStan7, sprawdzStan8, sprawdzStan9, sprawdzStan10,
	sprawdzStan11, sprawdzStan12, sprawdzStan13, sprawdzStan14, sprawdzStan15, sprawdzStan16, sprawdzStan17, sprawdzStan18, sprawdzStan19, sprawdzStan20 
	,sprawdzStan21, sprawdzStan22, sprawdzStan23, sprawdzStan24, sprawdzStan25, sprawdzStan26, sprawdzStan27, sprawdzStan28, sprawdzStan29, sprawdzStan30 
	,sprawdzStan31, sprawdzStan32, sprawdzStan33, sprawdzStan34, sprawdzStan35, sprawdzStan36, sprawdzStan37, sprawdzStan38, sprawdzStan39, sprawdzStan40 ;
	
	/**
	 * Deklaracja zmiennych dla okna błędu wprowadzania znaków
	 */
	Pattern pattern, pattern2;
	private static final String POLSKIE_ZNAKI_SPACJA_MYSLNIK = "^[\\p{L}*\\s]+([ '-][\\p{L}*]+)";
	private static final String POLSKIE_ZNAKI = "^[\\p{L}*]+";
	static Pattern p1 = Pattern.compile(POLSKIE_ZNAKI_SPACJA_MYSLNIK, Pattern.CANON_EQ |Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	static Pattern p2 = Pattern.compile(POLSKIE_ZNAKI, Pattern.CANON_EQ |Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    Matcher matcherNrBudynku, matcherNrMieszkania, matcherMiejscowosc, matcherUlica, matcherLiczba, matcherWybor;
    private Blad blad1;
    private BladSkladniki blad6;
    private BladZaznaczoneSkladniki blad7;
    

//*************************************************************************************************************************************
	
	
	/**
	 * Konstruktor bezparametrowy klasy GUI
	 */
	public GUI(){
		this.getContentPane().add(utworzPanelDolny(), BorderLayout.CENTER);
		this.getContentPane().add(utworzPanelGorny(), BorderLayout.NORTH);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImageIcon icon = new ImageIcon("images/ico.png");
		this.setIconImage(icon.getImage());
		this.setUndecorated(true);
		this.setResizable(false);  
		this.setVisible(true);
	}
	
	/**
	 * Metoda tworząca górny panel.
	 * @return panelGorny
	 */
	private JPanel utworzPanelGorny(){
		FormLayout layout2 = new FormLayout(
				"1306px, 29px, 2px, 29px", "19px");
		panelGorny = new JPanel(layout2);
		CellConstraints cc = new CellConstraints();	
		panelGorny.setPreferredSize(new Dimension(1366,19));
		lblPasek=new JLabel(new ImageIcon("images/pasek_lewy.png"));
		lblPasek.setPreferredSize(new Dimension(1307, 19));
		lblPasekMin = new JLabel(new ImageIcon("images/pasek_prawy.png"));
		lblPasekMin.setPreferredSize(new Dimension(2,19));
		
		btnMinimalizuj = new JButton(new ImageIcon("images/minimalizuj.png"));
		btnMinimalizuj.setOpaque(false);
		btnMinimalizuj.setFocusable(false);
		btnMinimalizuj.setBorderPainted(false);
		
		btnMinimalizuj.setPreferredSize(new Dimension(29,19));
		btnMinimalizuj.addActionListener(this);
		
		btnZamknij=new JButton(new ImageIcon("images/zamknij.png"));
		btnZamknij.setOpaque(false);
		btnZamknij.setFocusable(false);
		btnZamknij.setBorderPainted(false);
		btnZamknij.setPreferredSize(new Dimension(29,19));
		btnZamknij.addActionListener(this);
		
		panelGorny.add(lblPasek, cc.xy(1, 1));
		panelGorny.add(btnMinimalizuj, cc.xy(2, 1));
		panelGorny.add(lblPasekMin, cc.xy(3, 1));
		panelGorny.add(btnZamknij, cc.xy(4, 1));
		
		return panelGorny;
	}
	
	/**
	 * Metoda tworząca panel dolny
	 * @return panelDolny
	 */
	private JPanel utworzPanelDolny(){
		panelDolny=new JPanel();
		panelDolny.setLayout(new CardLayout());
        panelDolny.add(utworzEkranStartowy(), "card1");
        panelDolny.add(utworzCennik(), "card2");
        panelDolny.add(utworzEkranDostawy(), "card3");
        panelDolny.add(utworzEkranZatwierdzania(), "card4");
        panelDolny.add(utworzEkranWlasnaPizza(), "card5");
        
        return panelDolny;
	}
	/**
	 * Metoda tworząca ekran startowy
	 * @return ekranStartowy
	 */
	private JPanel utworzEkranStartowy(){
		FormLayout layout3 = new FormLayout(
					"36px, 64px, 55px, 119px, 231px, 146px, 70px, 120px, 323px, 102px, 100px", 
					"115px, 110px, 55px, 22px, 304px, 91px, 14px, 32px, 6px");
		CellConstraints cc = new CellConstraints();
		
		ekranStartowy = new JPanel(layout3);
		//ekranStartowy = new FormDebugPanel(layout3);
		ekranStartowy.setBackground(new Color(0xf2f2f3));

		lblStartowyLogo= new JLabel(new ImageIcon("images/startowy_naglowek.png"));
		
		btnZamowzMenu = new JButton(new ImageIcon("images/zamow_z_menu.png"));
		btnZamowzMenu.setOpaque(false);
		btnZamowzMenu.setFocusable(false);
		btnZamowzMenu.setBorderPainted(false);
		btnZamowzMenu.setPreferredSize(new Dimension(551,65));
		btnZamowzMenu.addActionListener(this);
		
		btnZamowzMenu2 = new JButton(new ImageIcon("images/p1.png"));
		btnZamowzMenu2.setOpaque(false);
		btnZamowzMenu2.setFocusable(false);
		btnZamowzMenu2.setBorderPainted(false);
		btnZamowzMenu2.addActionListener(this);
		
		btnZamowWlasna = new JButton(new ImageIcon("images/zamow_wlasna.png"));
		btnZamowWlasna.setOpaque(false);
		btnZamowWlasna.setFocusable(false);
		btnZamowWlasna.setBorderPainted(false);
		btnZamowWlasna.setPreferredSize(new Dimension(545,65));
		btnZamowWlasna.addActionListener(this);
		
		btnZamowWlasna2 = new JButton(new ImageIcon("images/p2.png"));
		btnZamowWlasna2.setOpaque(false);
		btnZamowWlasna2.setFocusable(false);
		btnZamowWlasna2.setBorderPainted(false);
		btnZamowWlasna2.addActionListener(this);
		
		lblPasekStanuEkranStartowy = new JLabel(new ImageIcon("images/pasek_stanu_startowy.png"));
		lblPasekStanuEkranStartowy.setPreferredSize(new Dimension(1366,768));
		
		btnPomoc = new JButton(new ImageIcon("images/pomoc.png"));
		btnPomoc.setPreferredSize(new Dimension(119,32));
		btnPomoc.setBorder(null);
		btnPomoc.addActionListener(this);
		
		ekranStartowy.add(lblStartowyLogo, cc.xyw(1, 1, 11)); 
		ekranStartowy.add(btnZamowzMenu, cc.xyw(3, 3, 4));
		ekranStartowy.add(btnZamowWlasna, cc.xyw(8, 3, 3));
		ekranStartowy.add(btnZamowzMenu2, cc.xywh(5, 4, 1, 2, cc.FILL, cc.FILL));
		ekranStartowy.add(btnZamowWlasna2, cc.xywh(9, 4, 1, 2, cc.FILL, cc.FILL));
		ekranStartowy.add(btnPomoc, cc.xyw(2, 8, 2));
		ekranStartowy.add(lblPasekStanuEkranStartowy, cc.xywh(1, 7, 11, 3));
		
		return ekranStartowy;
	}
	
	/**
	 * Metoda tworząca cennik
	 * @return cennik
	 */
	private JPanel utworzCennik() {
		FormLayout layout = new FormLayout(
			"27px, 9px, 37px, 444px, 41px, 38px, 41px, 39px, 41px, 40px, 30px, 124px, 227px, 157px, 8px, 27px, 9px, 27px",
			"115px, 29px, 17px, 19px, 27px, 40px, 20px, 290px, 34px, 41px, 37px, 28px, 17px, 30px, 5px");
		CellConstraints cc = new CellConstraints();
		
		//cennik = new FormDebugPanel(layout);
		cennik = new JPanel(layout);
		cennik.setBackground(new Color(0xf2f2f3));
		
		//Nagłówek cennika
		lblCennik= new JLabel(new ImageIcon("images/cennik.png"));
		
		//Nazwy kolumn z rozmiarami pizz
		lbl30cm=new JLabel("<HTML><U>30cm</U></HTML>");
		lbl30cm.setFont(new Font("Arial", Font.BOLD, 17));
		lbl30cm.setForeground(Color.black);
		
		lbl40cm=new JLabel("<HTML><U>40cm</U></HTML>");
		lbl40cm.setFont(new Font("Arial", Font.BOLD, 17));
		lbl40cm.setForeground(Color.black);
		
		lbl50cm=new JLabel("<HTML><U>50cm</U></HTML>");
		lbl50cm.setFont(new Font("Arial", Font.BOLD, 17));
		lbl50cm.setForeground(Color.black);
		
		//Czcionka dla zawartości tabeli cennik
		FontUIResource font = new FontUIResource("Arial", Font.PLAIN, 17);
		UIManager.put("Table.font", font);
		
		//Zawartość tabeli
		Object[][] dane = {
				{"<html><strong>1. Margherita</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, oregano</font></html>","9,50", "17,80", "19,90"},	
				{"<html><strong>2. Capriciosa</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, szynka, pieczarki, oregano</font></html>","15,50", "22,80", "27,00"},
				{"<html><strong>3. Califfo</strong><br>"
					    + "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, szynka, kabanosy,</font><br>"
					    + "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>papryka konserwowa, oliwki zielone, oregano</font></html>","19,00", "27,90", "33,00"},
				{"<html><strong>4. Calzone</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, szynka, kabanosy,</font><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>boczek wędzony, salami, oregano</font></html>","19,00", "28,30", "33,00"},	
				{"<html><strong>5. Decoro</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, szynka, pieczarki,</font><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>papryka konserwowa, czosnek, oregano</font></html>","18,30", "26,70", "31,80"},
				{"<html><strong>6. Pepe Roso</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, salami, papryka konserwowa,</font><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>oregano</font></html>","15,40", "22,80", "26,80"},
				{"<html><strong>7. Napoletana</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, salami, oliwki zielone</font></html>","17,00", "24,60", "29,60"},	
				{"<html><strong>8. Piacere</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, salami, boczek wędzony,</font><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>cebula biała, kukurydza, oregano</html>","17,60", "26,90", "30,60"},
				{"<html><strong>9. Roma</strong><br>"
					    + "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, salami, kabanosy,</font><br>"
					    + "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>papryka konserwowa, oregano</font></html>","16,90", "24,80", "29,30"},
			    {"<html><strong>10. Polska</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, szynka, kiełbasa, kabanosy,</font><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>biała cebula, świeża papryka, oregano</font></html>","18,10", "25,60", "31,50"},	
				{"<html><strong>11. Farmerska</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, kurczak, szynka, świeża papryka</font></html>","15,50", "21,80", "29,90"},				
				{"<html><strong>12. Vega</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, pieczarki, fasola, kukurydza,</font><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>szparagi</font></html>","13,00", "20,40", "28,40"},
				{"<html><strong>13. Kaukaska</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, tuńczyk, czosnek,</font><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ogórek konserwowy, świeża papryka</font></html>","19,00", "25,80", "31,00"},
				{"<html><strong>14. Krewetkowa</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, krewetki, małże, czosnek</font></html>","19,40", "27,00", "39,90"},	    
				{"<html><strong>15. Spinachio</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, feta, szpinak, czosnek</font></html>","18,10", "25,60", "31,50"},
		};
		
		//Model tabeli
	    TableModel model = new DefaultTableModel(dane, kolumny) {

	    	private static final long serialVersionUID = 1L;

	    	public Class getColumnClass(int column) {
	    		Class returnValue;
	        		if ((column >= 0) && (column < getColumnCount())) {
	        			returnValue = getValueAt(0, column).getClass();
	        		} 
	        		else {
	        			returnValue = Object.class;
	        		}
	        		return returnValue;
	    	}
	    };
			
		cennikTabela = new JTable(model);
		cennikTabela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
	                cennikTabela = (JTable) e.getSource();
	                Point p = e.getPoint();
                    
	                //Pobranie i zapisanie wybranego numeru rzędu z tablicy
	                int row = cennikTabela.rowAtPoint(e.getPoint());
	                String mychar = String.valueOf(cennikTabela.getValueAt(row, 0).toString().charAt(14)+""+cennikTabela.getValueAt(row, 0).toString().charAt(15)).split("\\.")[0];
	                Buffor.setNumerRzedu(Integer.parseInt(mychar)-1);
	                
	                //Sprawdzenie, czy limit rozmiaru zamówienia został osiągnięty
	                if(Buffor.getRozmiarZamowienia()==25){
	                	BladOsiagnieciaLimitu blad=new BladOsiagnieciaLimitu();
	                }
	                else{
	                    //Utworzenie obiektu klasu Dialog
	                    dialog=new Dialog();
	                    
	                    //Sprawdzam, czy użytkownik zamknął dialog czy użył przycisku "Dodaj do zamówienia"
	                    if(Buffor.getDodaj()==1){
	                    	//Wyświetlenie podglądu zamówienia w cenniku
	                        zamowienie.wyswietlPodgladZamowienia(txtrZamowienie, Buffor.getSos(), Buffor.getNazwaPizzy(), Buffor.getRozmiarPizzy(), 
	                        Buffor.getLiczbaPizz());
	                        
	                        //Wyświetlenie łącznego kosztu zamówienia (z cennika i własnej pizzy)
	                        Buffor.setKosztLacznyWlasnej(Buffor.getKosztLacznyWlasnej());
	                        zamowienie.wyswietlLacznyKoszt(lblWyswietlLacznyKosztZamowienia, Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej(), dec);   
	                        
	                        //Wyświetlenie podglądu zamówienia na ekranie dostawy
	                        zamowienie.wyswietlPizze(Buffor.getNazwaPizzy()+"\t\t"+Buffor.getRozmiarPizzy()+"\t"+"x"+Buffor.getLiczbaPizz()+"\t"+
	                        		dec.format(Buffor.getKosztElementu())+"\n", textPane);
	                        zamowienie.wyswietlSkladniki(buffor.skladniki[Buffor.getNumerRzedu()]+"\n", textPane);
	                        zamowienie.wyswietlSos(Buffor.getSos(),"+ sos "+Buffor.getSos()+"\n", textPane);
	                        
	                        //Wyświetlenie podglądu paragonu na ekranie zatwierdzania zamówienia
	                        zamowienie.wyswietlPizzeNaParagonie(Buffor.getNazwaPizzy()+" "+Buffor.getRozmiarPizzy()+"\t"+Buffor.getLiczbaPizz()+"\t\tx\t"+
	                        		dec.format(Buffor.getCena())+"\n", textPane2);
	                        zamowienie.wyswietlSos(Buffor.getSos(), "Sos "+Buffor.getSos().toLowerCase()+"\t"+"0\t\tx\t0,00"+"\n", textPane2);     
	                    }
	                    else{
	                    	
	                    }	                	
	                }
				}
			}
		});
		
	    sorter = new TableRowSorter<TableModel>(model);
	    cennikTabela.setRowSorter(sorter);		
		
		//Wyrownanie zawartości tabeli do góry
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)cennikTabela.getDefaultRenderer(cennikTabela.getColumnClass(0));
		renderer.setVerticalAlignment(SwingConstants.NORTH);
		
		cennikTabela.setEnabled(false);
		cennikTabela.setShowGrid(false);
		cennikTabela.setIntercellSpacing(new Dimension(0, 0));
		
		//Ustawienie szerokości kolumn tabeli
		cennikTabela.getColumn("Nazwa").setMinWidth(481);
		cennikTabela.getColumn("Nazwa").setMaxWidth(481);
		cennikTabela.getColumn("Rozmiar1").setMinWidth(80);
		cennikTabela.getColumn("Rozmiar1").setMaxWidth(80);
		cennikTabela.getColumn("Rozmiar2").setMinWidth(80);
		cennikTabela.getColumn("Rozmiar2").setMaxWidth(80);
		cennikTabela.getColumn("Rozmiar3").setMinWidth(70);
		cennikTabela.getColumn("Rozmiar3").setMaxWidth(70);

		cennikTabela.setBackground(null);
		cennikTabela.setForeground(Color.black);
	    cennikTabela.setTableHeader(null);
	    
	    //Ustawienie wysokości wierszy tabeli
	    cennikTabela.setRowHeight(60);
        cennikTabela.setRowHeight(0, 40);
        cennikTabela.setRowHeight(1, 40);
        cennikTabela.setRowHeight(6, 40);
        cennikTabela.setRowHeight(10, 40);
        cennikTabela.setRowHeight(11, 60);
        cennikTabela.setRowHeight(12, 60);
        cennikTabela.setRowHeight(13, 40);
	    scrollPane2 = new JScrollPane(cennikTabela);
		scrollPane2.setBorder(BorderFactory.createEmptyBorder()); 
		scrollPane2.getViewport().setBackground(new Color(0xf2f2f3));
		
		//Obramowanie i marginesy wyszukiwarki
		Border line = BorderFactory.createLineBorder(new Color(0x939393));
		Border line2 = BorderFactory.createEmptyBorder();
		Border empty = new EmptyBorder(2, 9, 0, 0);
		CompoundBorder border = new CompoundBorder(line2, empty);
		
		//Wyszukiwarka
		txtSzukaj=new JTextField("Szukaj...");
		txtSzukaj.setOpaque(false);
		
		//Dzięki temu po kliknięciu w pole tekstowe jego zawartość jest czyszczona.
		txtSzukaj.addMouseListener(new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	            if(txtSzukaj.getText().equals("Szukaj...")){
	            	txtSzukaj.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });
	
		txtSzukaj.setBorder(border);
		txtSzukaj.setFont(new Font("Arial", Font.PLAIN, 17));
		
		//Wywołanie przycisku 'Szukaj' po wciśnięciu klawisza Enter
		txtSzukaj.addKeyListener(new KeyAdapter() {
		          public void keyPressed(KeyEvent e) {
		              if(e.getKeyCode()==KeyEvent.VK_ENTER){
		                  btnOK.doClick();
		              }
		          }		
		});
		
		lblSzukaj=new JLabel(new ImageIcon("images/tlo_szukaj.png"));
		lblSzukaj.setLayout(new BorderLayout());
		lblSzukaj.add(txtSzukaj);
		
		//Przycisk 'Szukaj'
		btnOK=new JButton(new ImageIcon("images/przycisk_szukaj.png"));
		btnOK.setPreferredSize(new Dimension(27,27));
		btnOK.setBorder(null);
		btnOK.addActionListener(this);
		
		//Napis "Podgląd zamówienia"
		lblPodgladZamowienia=new JLabel(new ImageIcon("images/podglad_zamowienia.png"));
	
		//Obramowanie dla podglądu zamówienia
		Border empty3 = new EmptyBorder(0, 0, 0, 0);
		CompoundBorder border3 = new CompoundBorder(line, empty3);
		
		//Podgląd zamówienia
		txtrZamowienie=new JTextArea();
		txtrZamowienie.setMargin(new Insets(6,9,0,0));
		txtrZamowienie.setBackground(new Color(0xeaeaeb));
		txtrZamowienie.setFont(new Font("Arial", Font.PLAIN, 16));
		txtrZamowienie.setEditable(false);
		txtrZamowienie.setForeground(Color.black);
		
		scrollPane=new JScrollPane(txtrZamowienie);
		scrollPane.setBorder(border3);
		
		//Napis "Łączny koszt zamówienia"
		lblLacznyKosztZamowienia=new JLabel(new ImageIcon("images/laczny_koszt_zamowienia.png"));
		
		//Wyświetlenie łącznego kosztu zamówienia
		lblWyswietlLacznyKosztZamowienia=new JLabel();
		lblWyswietlLacznyKosztZamowienia.setFont(new Font("Arial", Font.BOLD, 17));
		lblWyswietlLacznyKosztZamowienia.setForeground(Color.black);
		
		//Przycisk umożliwiający przejście do ekranu dostawy
		btnDostawa3=new JButton(new ImageIcon("images/dalej.png"));
		btnDostawa3.setPreferredSize(new Dimension(44,30));
		btnDostawa3.setBorder(null);
		btnDostawa3.addActionListener(this);
		
		//Przycisk umożliwiający przejście do ekranu startowego
		btnStartowyZCennika=new JButton(new ImageIcon("images/wstecz_cennik.png"));
		btnStartowyZCennika.setBorder(null);
		btnStartowyZCennika.addActionListener(this);
		
		//Pasek stanu cennika
		lblPasekStanu=new JLabel(new ImageIcon("images/pasek_stanu_cennik.png"));
		
		cennik.add(lblCennik, cc.xyw(1,1,18)); 
		cennik.add(lbl30cm, cc.xy(5,3));
		cennik.add(lbl40cm, cc.xy(7,3));
		cennik.add(lbl50cm, cc.xy(9,3));
		cennik.add(lblSzukaj, cc.xyw(13,5,3));
		cennik.add(btnOK, cc.xy(16,5));
		cennik.add(lblPodgladZamowienia, cc.xy(13,7, cc.LEFT, cc.FILL));
		cennik.add(lblLacznyKosztZamowienia, cc.xy(13,11, cc.LEFT, cc.BOTTOM));
		cennik.add(lblWyswietlLacznyKosztZamowienia, cc.xy(14,11, cc.LEFT, cc.BOTTOM));
		cennik.add(scrollPane, cc.xywh(13,8,4,3, cc.FILL, cc.FILL)); 
		cennik.add(scrollPane2,cc.xywh(3, 5, 8, 7)); 
		cennik.add(btnStartowyZCennika,cc.xyw(2, 14, 2, cc.FILL, cc.FILL));
		cennik.add(btnDostawa3,cc.xyw(15, 14, 3));
		cennik.add(lblPasekStanu, cc.xywh(1,13,18,3)); 
		
		return cennik;
	}
	
	/**
	 * Metoda tworząca ekran wyboru sposobu dostawy zamówienia
	 * @return ekranDostawy
	 */
	private JPanel utworzEkranDostawy(){
		FormLayout layout4 = new FormLayout(                                                              
				"27px, 46px, 135px, 136px, 9px, 199px, 135px, 182px, 49px, 97px, 14px, 35px, 22px, 40px, 29px, 140px, 44px, 27px", 
				"115px, 67px, 25px, 25px, 28px, 25px, 3px, 25px, 3px, 25px, 3px, 25px, 3px, 25px, "
				+ "60px, 25px, 30px, 27px, 41px, 27px, 27px, 63px, 17px, 30px, 5px");		
		CellConstraints cc = new CellConstraints();
		
		ekranDostawy = new JPanel(layout4);
		//ekranDostawy = new FormDebugPanel(layout4);
		ekranDostawy.setBackground(new Color(0xf2f2f3));

		lblDostawaLogo= new JLabel(new ImageIcon("images/dostawa_naglowek.png"));
		lblDostawaText1 = new JLabel(new ImageIcon("images/dostawa_text1.png"));
		lblDostawaText2 = new JLabel(new ImageIcon("images/dostawa_text2.png"));
		lblDostawaText3 = new JLabel(new ImageIcon("images/dostawa_text3.png"));
		lblDostawaText4 = new JLabel(new ImageIcon("images/dostawa_text4.png"));
		lblDostawaText5 = new JLabel(new ImageIcon("images/dostawa_text5.png"));
		lblDostawaText6 = new JLabel(new ImageIcon("images/dostawa_text6.png"));
		lblDostawaText7 = new JLabel(new ImageIcon("images/dostawa_text7.png"));
		lblDostawaText8 = new JLabel(new ImageIcon("images/dostawa_text8.png"));
		
		//Przycisk umożliwiający powiększenie zamówienia o dodatkowe pozycje
		btnDodajDoZamowienia=new JButton(new ImageIcon("images/powieksz_zamowienie.png"));
		btnDodajDoZamowienia.addActionListener(this);
		btnDodajDoZamowienia.setPreferredSize(new Dimension(211,27));
		btnDodajDoZamowienia.setBorder(null);
		btnDodajDoZamowienia.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt){
            	btnDodajDoZamowienia.setIcon(new ImageIcon("images/powieksz_zamowienie_over.png"));
            }
            public void mouseExited(MouseEvent evt){
            	btnDodajDoZamowienia.setIcon(new ImageIcon("images/powieksz_zamowienie.png"));
            }
            public void mousePressed(MouseEvent evt){
            	btnDodajDoZamowienia.setIcon(new ImageIcon("images/powieksz_zamowienie_over.png"));
            }
            public void mouseReleased(MouseEvent evt){
            	btnDodajDoZamowienia.setIcon(new ImageIcon("images/powieksz_zamowienie.png"));
            }
        });
		
		//Twoje zamówienie
		document = new DefaultStyledDocument();
		textPane = new JTextPane(document);
		
		textPane.setBackground(new Color(0xeaeaeb));
		textPane.setMargin(new Insets(8, 11, 0, 11));
		textPane.setEditable(false);
		textPane.setForeground(Color.black);
		
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		
		//Utworzenie tabulatorów dla textPane
		TabStop[] tabStops = new TabStop[5];
		tabStops[0] = new TabStop(150, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		tabStops[1] = new TabStop(150, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		tabStops[2] = new TabStop(275, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		tabStops[3] = new TabStop(340, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		tabStops[4] = new TabStop(385, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		
		//Dodanie tabulatorów do textPane
		TabSet tabSet = new TabSet(tabStops);
		StyleConstants.setTabSet(attributes, tabSet);
		document.setParagraphAttributes(0, 0, attributes, false);
	
		scrollPane3 = new JScrollPane(textPane);
		
		//Obramowanie i marginesy
		Border line = BorderFactory.createLineBorder(new Color(0x939393));
		Border empty2 = new EmptyBorder(2, 7, 0, 0);
		CompoundBorder border2 = new CompoundBorder(line, empty2);
		
		//Utworzenie maski dla pola tekstowego numer telefonu
		MaskFormatter mask = null;
        try {
            // # ten symbol oznacza miejsce na liczbe
            mask = new MaskFormatter("###-###-###");
            mask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		txtNumerTelefonu=new JFormattedTextField(mask);
		txtNumerTelefonu.setOpaque(false);
		txtNumerTelefonu.setBorder(border2);
		txtNumerTelefonu.setFont(new Font("Arial", Font.PLAIN, 17));
		
		lblNumerTelefonu=new JLabel(new ImageIcon("images/tlo_input.png"));
		lblNumerTelefonu.setLayout(new BorderLayout());
		lblNumerTelefonu.add(txtNumerTelefonu);

		txtMiejscowosc = new JTextField();
		txtMiejscowosc.setOpaque(false);
		txtMiejscowosc.setBorder(border2);
		txtMiejscowosc.setFont(new Font("Arial", Font.PLAIN, 17));
		txtMiejscowosc.setDocument(new TextFieldLimit(13));
		
		lblMiejscowosc=new JLabel(new ImageIcon("images/tlo_input.png"));
		lblMiejscowosc.setLayout(new BorderLayout());
		lblMiejscowosc.add(txtMiejscowosc);
		
		txtUlica = new JTextField();
		txtUlica.setOpaque(false);
		txtUlica.setBorder(border2);
		txtUlica.setFont(new Font("Arial", Font.PLAIN, 17));
		txtUlica.setDocument(new TextFieldLimit(13));
		
		lblUlica=new JLabel(new ImageIcon("images/tlo_input.png"));
		lblUlica.setLayout(new BorderLayout());
		lblUlica.add(txtUlica);
		
		txtNrBudynku = new JTextField();
		txtNrBudynku.setOpaque(false);
		txtNrBudynku.setBorder(border2);
		txtNrBudynku.setFont(new Font("Arial", Font.PLAIN, 17));
		txtNrBudynku.setDocument(new TextFieldLimit(2));
		
		lblNrBudynku=new JLabel(new ImageIcon("images/liczba.png"));
		lblNrBudynku.setLayout(new BorderLayout());
		lblNrBudynku.add(txtNrBudynku);
		
		txtNrMieszkania = new JTextField();
		txtNrMieszkania.setOpaque(false);
		txtNrMieszkania.setBorder(border2);
		txtNrMieszkania.setFont(new Font("Arial", Font.PLAIN, 17));
		txtNrMieszkania.setDocument(new TextFieldLimit(2));
		
		lblNrMieszkania=new JLabel(new ImageIcon("images/liczba.png"));
		lblNrMieszkania.setLayout(new BorderLayout());
		lblNrMieszkania.add(txtNrMieszkania);
		
		lblLacznyKoszt=new JLabel();
		lblLacznyKoszt.setFont(new Font("Arial", Font.BOLD, 17));
		lblLacznyKoszt.setForeground(Color.black);
		
		comboBoxDostawa = new ComboBox();
		comboBoxDostawa.setEditable(true);
		comboBoxDostawa.addItem(listaDostawa);
		comboBoxDostawa.setUI(ColorArrowUI.createUI(comboBoxDostawa));
		comboBoxDostawa.setBorder(line);
		comboBoxDostawa.addActionListener(this);
		comboBoxDostawa.setSelectedItem("Na miejscu");
		
		//Przycisk umożliwiający przejście do ekranu zatwierdzania zamówienia
		btnPotwierdzenie2 = new JButton(new ImageIcon("images/dalej.png"));
		btnPotwierdzenie2.setPreferredSize(new Dimension(44,30));
		btnPotwierdzenie2.setBorder(null);
		btnPotwierdzenie2.addActionListener(this);
		
		//Przycisk umożliwiający powrót do cennika
		btnCennik=new JButton(new ImageIcon("images/wstecz.png"));
		btnCennik.setBorder(null);
		btnCennik.addActionListener(this);
			
		lblPasekStanuEkranDostawy = new JLabel(new ImageIcon("images/pasek_stanu_dostawa.png"));

		ekranDostawy.add(lblDostawaLogo, cc.xyw(1, 1, 18));
		ekranDostawy.add(lblDostawaText1, cc.xywh(4, 3, 2, 1));
		ekranDostawy.add(lblDostawaText2, cc.xywh(9, 4, 2, 1));
		ekranDostawy.add(lblDostawaText3, cc.xywh(9, 6, 2, 1));
		ekranDostawy.add(lblDostawaText4, cc.xywh(9, 8, 2, 1));
		ekranDostawy.add(lblDostawaText5, cc.xywh(9, 10, 2, 1));
		ekranDostawy.add(lblDostawaText6, cc.xywh(9, 12, 2, 1));
		ekranDostawy.add(lblDostawaText7, cc.xywh(9, 14, 2, 1));
		ekranDostawy.add(lblDostawaText8, cc.xywh(9, 16, 5, 1));
		ekranDostawy.add(btnDodajDoZamowienia, cc.xywh(4, 21, 4, 1, cc.CENTER, cc.FILL));
		ekranDostawy.add(scrollPane3, cc.xywh(4, 4, 4, 16));
		ekranDostawy.add(lblNumerTelefonu, cc.xywh(12, 6, 4, 1));
		ekranDostawy.add(lblMiejscowosc, cc.xywh(12, 8, 4, 1));
		ekranDostawy.add(lblUlica, cc.xywh(12, 10, 4, 1));
		ekranDostawy.add(lblNrBudynku, cc.xywh(12, 12, 1, 1));
		ekranDostawy.add(lblNrMieszkania, cc.xywh(12, 14, 1, 1));
		ekranDostawy.add(lblLacznyKoszt, cc.xywh(14, 16, 2, 1, cc.LEFT, cc.DEFAULT));
		ekranDostawy.add(comboBoxDostawa, cc.xywh(12, 4, 4, 1));		
		ekranDostawy.add(btnPotwierdzenie2, cc.xy(17, 24));
		ekranDostawy.add(btnCennik, cc.xy(2, 24));
		ekranDostawy.add(lblPasekStanuEkranDostawy, cc.xywh(1, 23, 18, 3));
		
		return ekranDostawy;
	}
	
	/**
	 * Metoda tworząca ekran zatwierdzania zamówienia
	 * @return ekranZatwierdzaniaZamowienia
	 */
	private JPanel utworzEkranZatwierdzania(){
		FormLayout layout = new FormLayout(                                                              
				"27px, 46px, 166px, 147px, 153px, 143px, 32px, 14px, 12px, 11px, 65px, 32px, 14px, 39px, 20px, 38px, 29px, 140px, 238px", 
				"115px, 71px, 13px, 3px, 4px, 15px, 22px, 28px, 25px, 7px, 25px, 28px, 25px, 7px, 25px, 28px, 27px, 7px, 27px, 49px, 27px, 119px, 17px, 30px, 5px");																 
		CellConstraints cc = new CellConstraints();	
		
		ekranZatwierdzaniaZamowienia = new JPanel(layout);
		//ekranZatwierdzaniaZamowienia = new FormDebugPanel(layout);
		ekranZatwierdzaniaZamowienia.setBackground(new Color(0xf2f2f3));	
		
		//Nagłówek ekranu zatwierdzania zamówienia
		lblZatwierdzenieNaglowek=new JLabel(new ImageIcon("images/zatwierdzenie_naglowek.png"));
		
		//Podgląd paragonu 
		lblPodgladParagonu=new JLabel(new ImageIcon("images/podglad_paragonu.png"));

		document2 = new DefaultStyledDocument();
		textPane2 = new JTextPane(document2);
		
		textPane2.setBackground(new Color(0xeaeaeb));
		textPane2.setMargin(new Insets(8, 11, 0, 7));
		textPane2.setEditable(false);
		textPane2.setForeground(Color.black);
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		
		//Utworzenie tabulatorów dla textPane2
		TabStop[] tabStops = new TabStop[5];
		tabStops[0] = new TabStop(180, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		tabStops[1] = new TabStop(200, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		tabStops[2] = new TabStop(205, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		tabStops[3] = new TabStop(220, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		tabStops[4] = new TabStop(240, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
		
		//Dodanie tabulatorów do textPane2
		TabSet tabSet = new TabSet(tabStops);
		StyleConstants.setTabSet(attributes, tabSet);
		document2.setParagraphAttributes(0, 0, attributes, false);
	
		scrollPane4 = new JScrollPane(textPane2);

		//Obramowanie i marginesy dla komponentów
		Border line = BorderFactory.createLineBorder(new Color(0xf2f2f3));
		Border empty = new EmptyBorder(2, 0, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		
		//Dane zamawiającego
		lblDaneZamawiajacego=new JLabel(new ImageIcon("images/dane_zamawiajacego.png"));
		
		txtrDaneZamawiajacego=new JTextArea();
		txtrDaneZamawiajacego.setEditable(false);
		txtrDaneZamawiajacego.setBorder(border);
		txtrDaneZamawiajacego.setMargin(new Insets(6,2,0,0));
		txtrDaneZamawiajacego.setForeground(Color.black);
		txtrDaneZamawiajacego.setBackground(new Color(0xf2f2f3));	
		txtrDaneZamawiajacego.setFont(new Font("Arial", Font.PLAIN, 17));
		
		//Sposób dostawy
		lblSposobDostawy=new JLabel(new ImageIcon("images/sposob_dostawy.png"));
		
		txtSposobDostawy=new JTextField();
		txtSposobDostawy.setOpaque(false);
		txtSposobDostawy.setBorder(border);
		txtSposobDostawy.setBackground(new Color(0xf2f2f3));
		txtSposobDostawy.setForeground(Color.black);
		txtSposobDostawy.setFont(new Font("Arial", Font.PLAIN, 17));
		txtSposobDostawy.setEditable(false);
		
		//Koszt dostawy
		lblKosztDostawy=new JLabel(new ImageIcon("images/koszt_dostawy.png"));
		
		txtKosztDostawy=new JTextField();
		txtKosztDostawy.setOpaque(false);
		txtKosztDostawy.setBorder(border);
		txtKosztDostawy.setBackground(new Color(0xf2f2f3));
		txtKosztDostawy.setForeground(Color.black);
		txtKosztDostawy.setFont(new Font("Arial", Font.PLAIN, 17));
		txtKosztDostawy.setEditable(false);
		
		//Łączny koszt zamówienia
		lblLacznyKosztZ=new JLabel(new ImageIcon("images/laczny_koszt.png"));
		
		txtLacznyKosztZ=new JTextField();
		txtLacznyKosztZ.setOpaque(false);
		txtLacznyKosztZ.setBorder(border);
		txtLacznyKosztZ.setBackground(new Color(0xf2f2f3));
		txtLacznyKosztZ.setForeground(Color.black);
		txtLacznyKosztZ.setFont(new Font("Arial", Font.BOLD, 17));
		txtLacznyKosztZ.setEditable(false);
		
		//VAT
		lblVAT=new JLabel(new ImageIcon("images/vat.png"));
		
		txtVAT=new JTextField();
		txtVAT.setOpaque(false);
		txtVAT.setBorder(border);
		txtVAT.setBackground(new Color(0xf2f2f3));
		txtVAT.setForeground(Color.black);
		txtVAT.setFont(new Font("Arial", Font.BOLD, 17));
		txtVAT.setEditable(false);
		
		btnZatwierdz=new JButton(new ImageIcon("images/zatwierdz.png"));
		btnZatwierdz.addActionListener(this);
		btnZatwierdz.setPreferredSize(new Dimension(211,27));
		btnZatwierdz.setBorder(null);
		btnZatwierdz.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt){
            	btnZatwierdz.setIcon(new ImageIcon("images/zatwierdz_over.png"));
            }
            public void mouseExited(MouseEvent evt){
            	btnZatwierdz.setIcon(new ImageIcon("images/zatwierdz.png"));
            }
            public void mousePressed(MouseEvent evt){
            	btnZatwierdz.setIcon(new ImageIcon("images/zatwierdz_over.png"));
            }
            public void mouseReleased(MouseEvent evt){
            	btnZatwierdz.setIcon(new ImageIcon("images/zatwierdz.png"));
            }
        });
		
		//Przycisk "Drukuj paragon"
		btnDrukujParagon=new JButton(new ImageIcon("images/drukuj.png"));
		btnDrukujParagon.addActionListener(this);
		btnDrukujParagon.setPreferredSize(new Dimension(211,27));
		btnDrukujParagon.setBorder(null);
		btnDrukujParagon.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt){
            	btnDrukujParagon.setIcon(new ImageIcon("images/drukuj_over.png"));
            }
            public void mouseExited(MouseEvent evt){
            	btnDrukujParagon.setIcon(new ImageIcon("images/drukuj.png"));
            }
            public void mousePressed(MouseEvent evt){
            	btnDrukujParagon.setIcon(new ImageIcon("images/drukuj_over.png"));
            }
            public void mouseReleased(MouseEvent evt){
            	btnDrukujParagon.setIcon(new ImageIcon("images/drukuj.png"));
            }
        });
		
		//Przycisk "Anuluj zamówienie"
		btnAnulujZ=new JButton(new ImageIcon("images/anuluj.png"));
		btnAnulujZ.addActionListener(this);
		btnAnulujZ.setPreferredSize(new Dimension(211,27));
		btnAnulujZ.setBorder(null);
		btnAnulujZ.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt){
            	btnAnulujZ.setIcon(new ImageIcon("images/anuluj_over.png"));
            }
            public void mouseExited(MouseEvent evt){
            	btnAnulujZ.setIcon(new ImageIcon("images/anuluj.png"));
            }
            public void mousePressed(MouseEvent evt){
            	btnAnulujZ.setIcon(new ImageIcon("images/anuluj_over.png"));
            }
            public void mouseReleased(MouseEvent evt){
            	btnAnulujZ.setIcon(new ImageIcon("images/anuluj.png"));
            }
        });
			
		//Przycisk umożliwiający powrót do okna dostawy
		btnDostawa4=new JButton(new ImageIcon("images/wstecz.png"));
		btnDostawa4.addActionListener(this);
		btnDostawa4.setBorder(null);
		
		//Pasek stanu
		lblPasekStanuZatwierdzanie=new JLabel(new ImageIcon("images/pasek_stanu_zatwierdzanie.png"));
		
		ekranZatwierdzaniaZamowienia.add(lblZatwierdzenieNaglowek, cc.xywh(1,1,19,1));
		ekranZatwierdzaniaZamowienia.add(lblPodgladParagonu, cc.xywh(4,3,1,2));
		ekranZatwierdzaniaZamowienia.add(scrollPane4, cc.xywh(4,6,2,16));
		ekranZatwierdzaniaZamowienia.add(lblDaneZamawiajacego, cc.xywh(7,6,6,1));
		ekranZatwierdzaniaZamowienia.add(txtrDaneZamawiajacego, cc.xywh(14,4,6,4));
		ekranZatwierdzaniaZamowienia.add(lblSposobDostawy, cc.xywh(8,9,5,1));
		ekranZatwierdzaniaZamowienia.add(txtSposobDostawy, cc.xywh(14,9,4,1));
		ekranZatwierdzaniaZamowienia.add(lblKosztDostawy, cc.xywh(9,11,4,1));
		ekranZatwierdzaniaZamowienia.add(txtKosztDostawy, cc.xywh(14,11,2,1));		
		ekranZatwierdzaniaZamowienia.add(lblLacznyKosztZ, cc.xywh(10,13,3,1));
		ekranZatwierdzaniaZamowienia.add(txtLacznyKosztZ, cc.xywh(14,13,3,1, cc.FILL, cc.DEFAULT));	
		ekranZatwierdzaniaZamowienia.add(lblVAT, cc.xy(12,15));
		ekranZatwierdzaniaZamowienia.add(txtVAT, cc.xyw(14,15,3, cc.FILL, cc.DEFAULT));
		ekranZatwierdzaniaZamowienia.add(btnZatwierdz, cc.xyw(11,17,6));	
		ekranZatwierdzaniaZamowienia.add(btnDrukujParagon, cc.xyw(11,19,6));
		ekranZatwierdzaniaZamowienia.add(btnAnulujZ, cc.xyw(11,21,6));	
		ekranZatwierdzaniaZamowienia.add(btnDostawa4, cc.xy(2,24));
		ekranZatwierdzaniaZamowienia.add(lblPasekStanuZatwierdzanie, cc.xywh(1,23,19,3));
		
		return ekranZatwierdzaniaZamowienia;
	}
	
	/**
	 * Metoda tworząca ekran kreatora własnej pizzy
	 * @return ekranWlasnaPizza
	 */
	private JPanel utworzEkranWlasnaPizza(){
		FormLayout layout5 = new FormLayout(                                                              
				"27px,46px, 32px, 24px, 26px, 65px, 12px, 22px, 16px, 59px, 32px, 45px, 24px, 23px, 3px, 9px, 36px, 32px, 34px, 8px, 4px, 126px, 3px, 34px, 24px, 26px, 122px, 15px, 136px, 24px, 26px, 146px, 34px, 44px, 27px", 
				"115px, 77px, 25px, 13px, 2px, 25px, 15px, 8px, 24px, 11px, 24px, 11px, 24px, 11px, 24px, 11px, 24px, 11px, 24px, 11px, 24px, 11px, 24px, 11px, 24px, 11px, 24px, 78px, 17px, 30px, 5px ");																 
		CellConstraints cc = new CellConstraints();	
		
		ekranWlasnaPizza = new JPanel(layout5);
		//ekranWlasnaPizza = new FormDebugPanel(layout5);
		ekranWlasnaPizza.setBackground(new Color(0xf2f2f3));	
		
		lblWlasnaPizzaNaglowek = new JLabel(new ImageIcon("images/wlasna_pizza_naglowek.png"));
		lblWlasnaPizzaText1 = new JLabel(new ImageIcon("images/wlasna_pizza_text1.png"));
		lblWlasnaPizzaText2 = new JLabel(new ImageIcon("images/wlasna_pizza_text2.png"));
		lblWlasnaPizzaText3 = new JLabel(new ImageIcon("images/wlasna_pizza_text3.png"));
		lblWlasnaPizzaText4 = new JLabel(new ImageIcon("images/wlasna_pizza_text4.png"));
		
		//Pole wyboru rozmiaru pizzy
		customCombobox = new ComboBox();
		customCombobox.setEditable(true);
		customCombobox.addItem(listaRozmiarow);
		customCombobox.setUI(ColorArrowUI.createUI(customCombobox));
		customCombobox.setBorder(BorderFactory.createLineBorder(new Color(0x939393)));
		customCombobox.setForeground(Color.BLACK);
		
		//Wprowadzanie liczby pizz
		spinLiczbaPizz=new JSpinner();
		spinLiczbaPizz.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		JComponent editor = spinLiczbaPizz.getEditor();
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)editor;
		spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
		spinnerEditor.getTextField().setFont(new Font("Arial", Font.PLAIN, 17));
		spinnerEditor.getTextField().setBorder(new EmptyBorder(1, 0, 1, 0));
		spinnerEditor.getTextField().setForeground(Color.black);
		spinnerEditor.getTextField().setBackground(new Color(0xeaeaeb));
		spinLiczbaPizz.setPreferredSize(new Dimension(45,25));
		
		//Pole wyboru rodzaju sosu
		customCombobox2 = new ComboBox();
		customCombobox2.setEditable(true);
		customCombobox2.addItem(listaSosow);
		customCombobox2.setUI(ColorArrowUI.createUI(customCombobox));
		customCombobox2.setBorder(BorderFactory.createLineBorder(new Color(0x939393)));
		customCombobox2.setForeground(Color.BLACK);
				
		lblPasekWlasnaPizza = new JLabel(new ImageIcon("images/pasek_wlasna_pizza.png"));
				
		//Pierwsza kolumna składników (check boxy)
		checkBox1=zamowienie.dodajCheckBox(checkBox1, "checkBox1");
		checkBox2=zamowienie.dodajCheckBox(checkBox2, "checkBox2");
		checkBox3=zamowienie.dodajCheckBox(checkBox3, "checkBox3");
		checkBox4=zamowienie.dodajCheckBox(checkBox4, "checkBox4");
		checkBox5=zamowienie.dodajCheckBox(checkBox5, "checkBox5");
		checkBox6=zamowienie.dodajCheckBox(checkBox6, "checkBox6");
		checkBox7=zamowienie.dodajCheckBox(checkBox7, "checkBox7");
		checkBox8=zamowienie.dodajCheckBox(checkBox8, "checkBox8");
		checkBox9=zamowienie.dodajCheckBox(checkBox9, "checkBox9");
		checkBox10=zamowienie.dodajCheckBox(checkBox10, "checkBox10");
    
		//Etykiety dla pierwszej kolumny check boxów
		lblCheckBox1 = zamowienie.dodajLabel(lblCheckBox1, "ananas");
		lblCheckBox2 = zamowienie.dodajLabel(lblCheckBox2, "boczek wędzony");
		lblCheckBox3 = zamowienie.dodajLabel(lblCheckBox3, "brokuły");
		lblCheckBox4 = zamowienie.dodajLabel(lblCheckBox4, "camembert");
		lblCheckBox5 = zamowienie.dodajLabel(lblCheckBox5, "cebula biała");
		lblCheckBox6 = zamowienie.dodajLabel(lblCheckBox6, "cebula czerwona");
		lblCheckBox7 = zamowienie.dodajLabel(lblCheckBox7, "chili");
		lblCheckBox8 = zamowienie.dodajLabel(lblCheckBox8, "czosnek");
		lblCheckBox9 = zamowienie.dodajLabel(lblCheckBox9, "fasola");
		lblCheckBox10 = zamowienie.dodajLabel(lblCheckBox10, "feta");

			    
	    //Druga kolumna składników (check boxy)
		checkBox11=zamowienie.dodajCheckBox(checkBox11, "checkBox11");
		checkBox12=zamowienie.dodajCheckBox(checkBox12, "checkBox12");
		checkBox13=zamowienie.dodajCheckBox(checkBox13, "checkBox13");
		checkBox14=zamowienie.dodajCheckBox(checkBox14, "checkBox14");
		checkBox15=zamowienie.dodajCheckBox(checkBox15, "checkBox15");
		checkBox16=zamowienie.dodajCheckBox(checkBox16, "checkBox16");
		checkBox17=zamowienie.dodajCheckBox(checkBox17, "checkBox17");
		checkBox18=zamowienie.dodajCheckBox(checkBox18, "checkBox18");
		checkBox19=zamowienie.dodajCheckBox(checkBox19, "checkBox19");
		checkBox20=zamowienie.dodajCheckBox(checkBox20, "checkBox20");
	  	    
	    //Etykiety dla drugiej kolumny check boxów
		lblCheckBox11 = zamowienie.dodajLabel(lblCheckBox11, "kabanosy");
		lblCheckBox12 = zamowienie.dodajLabel(lblCheckBox12, "kapary");
		lblCheckBox13 = zamowienie.dodajLabel(lblCheckBox13, "kiełki sojowe");
		lblCheckBox14 = zamowienie.dodajLabel(lblCheckBox14, "krewetki");
		lblCheckBox15 = zamowienie.dodajLabel(lblCheckBox15, "kukurydza");
		lblCheckBox16 = zamowienie.dodajLabel(lblCheckBox16, "kurczak");
		lblCheckBox17 = zamowienie.dodajLabel(lblCheckBox17, "małże");
		lblCheckBox18 = zamowienie.dodajLabel(lblCheckBox18, "mozzarella");
		lblCheckBox19 = zamowienie.dodajLabel(lblCheckBox19, "ogórek kiszony");
		lblCheckBox20 = zamowienie.dodajLabel(lblCheckBox20, "ogórek konserwowy");
			  	    
		//Trzecia kolumna składników (check boxy)
		checkBox21=zamowienie.dodajCheckBox(checkBox21, "checkBox21");
		checkBox22=zamowienie.dodajCheckBox(checkBox22, "checkBox22");
		checkBox23=zamowienie.dodajCheckBox(checkBox23, "checkBox23");
		checkBox24=zamowienie.dodajCheckBox(checkBox24, "checkBox24");
		checkBox25=zamowienie.dodajCheckBox(checkBox25, "checkBox25");
		checkBox26=zamowienie.dodajCheckBox(checkBox26, "checkBox26");
		checkBox27=zamowienie.dodajCheckBox(checkBox27, "checkBox27");
		checkBox28=zamowienie.dodajCheckBox(checkBox28, "checkBox28");
		checkBox29=zamowienie.dodajCheckBox(checkBox29, "checkBox29");
		checkBox30=zamowienie.dodajCheckBox(checkBox30, "checkBox30");
				    
		//Etykiety dla trzeciej kolumny check boxów
		lblCheckBox21 = zamowienie.dodajLabel(lblCheckBox21, "oliwki czarne");
		lblCheckBox22 = zamowienie.dodajLabel(lblCheckBox22, "oliwki zielone");
		lblCheckBox23 = zamowienie.dodajLabel(lblCheckBox23, "oregano");
		lblCheckBox24 = zamowienie.dodajLabel(lblCheckBox24, "papryka konserwowa");
		lblCheckBox25 = zamowienie.dodajLabel(lblCheckBox25, "peperoni");
		lblCheckBox26 = zamowienie.dodajLabel(lblCheckBox26, "pieczarki");
		lblCheckBox27 = zamowienie.dodajLabel(lblCheckBox27, "pomidor");
		lblCheckBox28 = zamowienie.dodajLabel(lblCheckBox28, "por");
		lblCheckBox29 = zamowienie.dodajLabel(lblCheckBox29, "salami");
		lblCheckBox30 = zamowienie.dodajLabel(lblCheckBox30, "ser");
				    
		//Czwarta kolumna składników (check boxy)
		checkBox31=zamowienie.dodajCheckBox(checkBox31, "checkBox31");
		checkBox32=zamowienie.dodajCheckBox(checkBox32, "checkBox32");
		checkBox33=zamowienie.dodajCheckBox(checkBox33, "checkBox33");
		checkBox34=zamowienie.dodajCheckBox(checkBox34, "checkBox34");
		checkBox35=zamowienie.dodajCheckBox(checkBox35, "checkBox35");
		checkBox36=zamowienie.dodajCheckBox(checkBox36, "checkBox36");
		checkBox37=zamowienie.dodajCheckBox(checkBox37, "checkBox37");
		checkBox38=zamowienie.dodajCheckBox(checkBox38, "checkBox38");
		checkBox39=zamowienie.dodajCheckBox(checkBox39, "checkBox39");
		checkBox40=zamowienie.dodajCheckBox(checkBox40, "checkBox40");
			    
		//Etykiety dla czwartej kolumny check boxów
		lblCheckBox31 = zamowienie.dodajLabel(lblCheckBox31, "sos boloński");
		lblCheckBox32 = zamowienie.dodajLabel(lblCheckBox32, "sos pomidorowy");
		lblCheckBox33 = zamowienie.dodajLabel(lblCheckBox33, "suszone pomidory");
		lblCheckBox34 = zamowienie.dodajLabel(lblCheckBox34, "szparagi");
		lblCheckBox35 = zamowienie.dodajLabel(lblCheckBox35, "szpinak");
		lblCheckBox36 = zamowienie.dodajLabel(lblCheckBox36, "szynka");
		lblCheckBox37 = zamowienie.dodajLabel(lblCheckBox37, "świeża bazylia");
		lblCheckBox38 = zamowienie.dodajLabel(lblCheckBox38, "świeża papryka");
		lblCheckBox39 = zamowienie.dodajLabel(lblCheckBox39, "tabasco");
		lblCheckBox40 = zamowienie.dodajLabel(lblCheckBox40, "tuńczyk");

	    lblPasekStanuWlasnaPizza = new JLabel(new ImageIcon("images/pasek_stanu_wlasna_pizza.png"));
				
		//Przycisk umożliwiający powrót do ekranu startowego
		btnStartowyZWlasnaPizza=new JButton(new ImageIcon("images/wstecz_wlasna_pizza.png"));
		btnStartowyZWlasnaPizza.addActionListener(this);
		btnStartowyZWlasnaPizza.setBorder(null);
				
		//Przycisk umożliwiający przejście do ekranu dostawy
		btnDostawa5 = new JButton(new ImageIcon("images/dalej2.png"));
		btnDostawa5.addActionListener(this);
		btnDostawa5.setBorder(null);

		checkBox1.addActionListener(this);	
		checkBox2.addActionListener(this);	
		checkBox3.addActionListener(this);	
		checkBox4.addActionListener(this);	
		checkBox5.addActionListener(this);	
		checkBox6.addActionListener(this);	
		checkBox7.addActionListener(this);	
		checkBox8.addActionListener(this);	
		checkBox9.addActionListener(this);	
		checkBox10.addActionListener(this);
		checkBox11.addActionListener(this);	
		checkBox12.addActionListener(this);	
		checkBox13.addActionListener(this);	
		checkBox14.addActionListener(this);	
		checkBox15.addActionListener(this);	
		checkBox16.addActionListener(this);	
		checkBox17.addActionListener(this);	
		checkBox18.addActionListener(this);	
		checkBox19.addActionListener(this);	
		checkBox20.addActionListener(this);	
		checkBox21.addActionListener(this);	
		checkBox22.addActionListener(this);	
		checkBox23.addActionListener(this);	
		checkBox24.addActionListener(this);	
		checkBox25.addActionListener(this);	
		checkBox26.addActionListener(this);	
		checkBox27.addActionListener(this);	
		checkBox28.addActionListener(this);	
		checkBox29.addActionListener(this);	
		checkBox30.addActionListener(this);	
		checkBox31.addActionListener(this);	
		checkBox32.addActionListener(this);	
		checkBox33.addActionListener(this);	
		checkBox34.addActionListener(this);	
		checkBox35.addActionListener(this);	
		checkBox36.addActionListener(this);	
		checkBox37.addActionListener(this);	
		checkBox38.addActionListener(this);	
		checkBox39.addActionListener(this);	
		checkBox40.addActionListener(this);	

		ekranWlasnaPizza.add(lblWlasnaPizzaNaglowek, cc.xyw(1, 1, 35));
		ekranWlasnaPizza.add(lblWlasnaPizzaText1, cc.xyw(4,3, 3));
		ekranWlasnaPizza.add(lblWlasnaPizzaText2, cc.xyw(12, 3, 3));
		ekranWlasnaPizza.add(lblWlasnaPizzaText3, cc.xy(19, 3));
		ekranWlasnaPizza.add(lblWlasnaPizzaText4, cc.xyw(4, 7, 5));
		ekranWlasnaPizza.add(customCombobox, cc.xyw(8, 3, 3));
		ekranWlasnaPizza.add(spinLiczbaPizz, cc.xyw(17,3, 2, cc.LEFT, cc.FILL));
		ekranWlasnaPizza.add(customCombobox2, cc.xy(22, 3));
		ekranWlasnaPizza.add(lblPasekWlasnaPizza, cc.xyw(4, 5, 20));
		ekranWlasnaPizza.add(checkBox1, cc.xy(4, 9));
		ekranWlasnaPizza.add(checkBox2, cc.xy(4, 11));
		ekranWlasnaPizza.add(checkBox3, cc.xy(4, 13));
		ekranWlasnaPizza.add(checkBox4, cc.xy(4, 15));
		ekranWlasnaPizza.add(checkBox5, cc.xy(4, 17));
		ekranWlasnaPizza.add(checkBox6, cc.xy(4, 19));
		ekranWlasnaPizza.add(checkBox7, cc.xy(4, 21));
		ekranWlasnaPizza.add(checkBox8, cc.xy(4, 23));
		ekranWlasnaPizza.add(checkBox9, cc.xy(4, 25));
		ekranWlasnaPizza.add(checkBox10, cc.xy(4, 27));
		ekranWlasnaPizza.add(lblCheckBox1, cc.xy(6, 9));
		ekranWlasnaPizza.add(lblCheckBox2, cc.xyw(6, 11, 4));
		ekranWlasnaPizza.add(lblCheckBox3, cc.xy(6, 13));
		ekranWlasnaPizza.add(lblCheckBox4, cc.xyw(6, 15, 3));
		ekranWlasnaPizza.add(lblCheckBox5, cc.xyw(6, 17, 3));
		ekranWlasnaPizza.add(lblCheckBox6, cc.xyw(6, 19, 5));
		ekranWlasnaPizza.add(lblCheckBox7, cc.xy(6, 21));
		ekranWlasnaPizza.add(lblCheckBox8, cc.xyw(6, 23, 4));
		ekranWlasnaPizza.add(lblCheckBox9, cc.xy(6, 25));
		ekranWlasnaPizza.add(lblCheckBox10, cc.xy(6, 27));
		ekranWlasnaPizza.add(checkBox11, cc.xy(13, 9));
		ekranWlasnaPizza.add(checkBox12, cc.xy(13, 11));
		ekranWlasnaPizza.add(checkBox13, cc.xy(13, 13));
		ekranWlasnaPizza.add(checkBox14, cc.xy(13, 15));
		ekranWlasnaPizza.add(checkBox15, cc.xy(13, 17));
		ekranWlasnaPizza.add(checkBox16, cc.xy(13, 19));
		ekranWlasnaPizza.add(checkBox17, cc.xy(13, 21));
		ekranWlasnaPizza.add(checkBox18, cc.xy(13, 23));
		ekranWlasnaPizza.add(checkBox19, cc.xy(13, 25));
		ekranWlasnaPizza.add(checkBox20, cc.xy(13, 27));
		ekranWlasnaPizza.add(lblCheckBox11, cc.xyw(16, 9, 5));
		ekranWlasnaPizza.add(lblCheckBox12, cc.xyw(16, 11, 3));
		ekranWlasnaPizza.add(lblCheckBox13, cc.xyw(16, 13, 4));
		ekranWlasnaPizza.add(lblCheckBox14, cc.xyw(16, 15, 3));
		ekranWlasnaPizza.add(lblCheckBox15, cc.xyw(16, 17, 3));
		ekranWlasnaPizza.add(lblCheckBox16, cc.xyw(16, 19, 3));
		ekranWlasnaPizza.add(lblCheckBox17, cc.xyw(16, 21, 3));
		ekranWlasnaPizza.add(lblCheckBox18, cc.xyw(16, 23, 3));
		ekranWlasnaPizza.add(lblCheckBox19, cc.xyw(16, 25, 4));
		ekranWlasnaPizza.add(lblCheckBox20, cc.xyw(16, 27, 7));
		ekranWlasnaPizza.add(checkBox21, cc.xy(25, 9));
		ekranWlasnaPizza.add(checkBox22, cc.xy(25, 11));
		ekranWlasnaPizza.add(checkBox23, cc.xy(25, 13));
		ekranWlasnaPizza.add(checkBox24, cc.xy(25, 15));
		ekranWlasnaPizza.add(checkBox25, cc.xy(25, 17));
		ekranWlasnaPizza.add(checkBox26, cc.xy(25, 19));
		ekranWlasnaPizza.add(checkBox27, cc.xy(25, 21));
		ekranWlasnaPizza.add(checkBox28, cc.xy(25, 23));
		ekranWlasnaPizza.add(checkBox29, cc.xy(25, 25));
		ekranWlasnaPizza.add(checkBox30, cc.xy(25, 27));
		ekranWlasnaPizza.add(lblCheckBox21, cc.xy(27, 9));
		ekranWlasnaPizza.add(lblCheckBox22, cc.xy(27, 11));
		ekranWlasnaPizza.add(lblCheckBox23, cc.xy(27, 13));
		ekranWlasnaPizza.add(lblCheckBox24, cc.xyw(27, 15, 3));
		ekranWlasnaPizza.add(lblCheckBox25, cc.xyw(27, 17, 2));
		ekranWlasnaPizza.add(lblCheckBox26, cc.xy(27, 19));
		ekranWlasnaPizza.add(lblCheckBox27, cc.xy(27, 21));
		ekranWlasnaPizza.add(lblCheckBox28, cc.xy(27, 23));
		ekranWlasnaPizza.add(lblCheckBox29, cc.xy(27, 25));
		ekranWlasnaPizza.add(lblCheckBox30, cc.xy(27, 27));
		ekranWlasnaPizza.add(checkBox31, cc.xy(30, 9));
		ekranWlasnaPizza.add(checkBox32, cc.xy(30, 11));
		ekranWlasnaPizza.add(checkBox33, cc.xy(30, 13));
		ekranWlasnaPizza.add(checkBox34, cc.xy(30, 15));
		ekranWlasnaPizza.add(checkBox35, cc.xy(30, 17));
		ekranWlasnaPizza.add(checkBox36, cc.xy(30, 19));
		ekranWlasnaPizza.add(checkBox37, cc.xy(30, 21));
		ekranWlasnaPizza.add(checkBox38, cc.xy(30, 23));
		ekranWlasnaPizza.add(checkBox39, cc.xy(30, 25));
		ekranWlasnaPizza.add(checkBox40, cc.xy(30, 27));
		ekranWlasnaPizza.add(lblCheckBox31, cc.xy(32, 9));
		ekranWlasnaPizza.add(lblCheckBox32, cc.xy(32, 11));
		ekranWlasnaPizza.add(lblCheckBox33, cc.xy(32, 13));
		ekranWlasnaPizza.add(lblCheckBox34, cc.xy(32, 15));
		ekranWlasnaPizza.add(lblCheckBox35, cc.xy(32, 17));
		ekranWlasnaPizza.add(lblCheckBox36, cc.xy(32, 19));
		ekranWlasnaPizza.add(lblCheckBox37, cc.xy(32, 21));
		ekranWlasnaPizza.add(lblCheckBox38, cc.xy(32, 23));
		ekranWlasnaPizza.add(lblCheckBox39, cc.xy(32, 25));
		ekranWlasnaPizza.add(lblCheckBox40, cc.xy(32, 27));
		ekranWlasnaPizza.add(btnStartowyZWlasnaPizza, cc.xy(2, 30));
		ekranWlasnaPizza.add(btnDostawa5, cc.xy(34, 30));
		ekranWlasnaPizza.add(lblPasekStanuWlasnaPizza, cc.xywh(1,29,35, 3));
		
		return ekranWlasnaPizza;
	}
	/**
	 * Metoda odpowiedzialna za czyszczenie zawartości kart
	 */
	private void czysc(){
		//Czyszczenie paska wyszukiwarki
		txtSzukaj.setText("Szukaj...");
		txtSzukaj.repaint();
		
		//Przywrócenie domyślnej zawartości tabeli
		sorter.setRowFilter(null);
		
		//Czyszczenie pól dodawania zamówienia
		txtrZamowienie.setText("");
		txtrZamowienie.repaint();
		
		//Czyszczenie podglądu zamówienia
		textPane.setText("");
		textPane.repaint();
		
		//Czyszczenie pól wyboru sposobu dostawy i wprowadzania danych zamawiającego
		comboBoxDostawa.getModel().setSelectedItem("Na miejscu");
		comboBoxDostawa.repaint();
		txtNumerTelefonu.setText("");
		txtNumerTelefonu.setValue(null);
		txtNumerTelefonu.repaint();
		txtMiejscowosc.setText("");
		txtMiejscowosc.repaint();		
		txtUlica.setText("");
		txtUlica.repaint();
		txtNrBudynku.setText("");
		txtNrBudynku.repaint();
		txtNrMieszkania.setText("");
		txtNrMieszkania.repaint();
		
		//Czyszczenie podglądu paragonu
		textPane2.setText("");
		textPane2.repaint();	
		
		//Czyszczenie danych zamawiającego
		txtrDaneZamawiajacego.setText("");
		txtrDaneZamawiajacego.repaint();
		
		//Czyszczenie sposobu i kosztu dostawy
		txtSposobDostawy.setText("");
		txtSposobDostawy.repaint();
		txtKosztDostawy.setText("");
		txtKosztDostawy.repaint();
		
		//Czyszczenie łącznego kosztu zamówienia i VAT
		lblWyswietlLacznyKosztZamowienia.setText("");
		lblWyswietlLacznyKosztZamowienia.repaint();
		txtLacznyKosztZ.repaint();
		txtVAT.setText("");
		txtVAT.repaint();
		
		//Czyszczenie ekran własna pizza
		customCombobox.getModel().setSelectedItem("30cm");
		customCombobox.repaint();
		customCombobox2.getModel().setSelectedItem("Brak");
		customCombobox2.repaint();
		spinLiczbaPizz.setValue(1);
		
		//Wyłączenie zaznaczenia dla check boxów
		checkBox1.setSelected(false);
		checkBox2.setSelected(false);
		checkBox3.setSelected(false);
		checkBox4.setSelected(false);
		checkBox5.setSelected(false);
		checkBox6.setSelected(false);
		checkBox7.setSelected(false);
		checkBox8.setSelected(false);
		checkBox9.setSelected(false);
		checkBox10.setSelected(false);
		checkBox11.setSelected(false);
		checkBox12.setSelected(false);
		checkBox13.setSelected(false);
		checkBox14.setSelected(false);
		checkBox15.setSelected(false);
		checkBox16.setSelected(false);
		checkBox17.setSelected(false);
		checkBox18.setSelected(false);
		checkBox19.setSelected(false);
		checkBox20.setSelected(false);
		checkBox21.setSelected(false);
		checkBox22.setSelected(false);
		checkBox23.setSelected(false);
		checkBox24.setSelected(false);
		checkBox25.setSelected(false);
		checkBox26.setSelected(false);
		checkBox27.setSelected(false);
		checkBox28.setSelected(false);
		checkBox29.setSelected(false);
		checkBox30.setSelected(false);
		checkBox31.setSelected(false);
		checkBox32.setSelected(false);
		checkBox33.setSelected(false);
		checkBox34.setSelected(false);
		checkBox35.setSelected(false);
		checkBox36.setSelected(false);
		checkBox37.setSelected(false);
		checkBox38.setSelected(false);
		checkBox39.setSelected(false);
		checkBox40.setSelected(false);
	}
	/**
	 * Metoda odpowiedzialna za dwa wzory wpisywania nazwy miasta i ulicy w ekranie dostawa
	 * @param tekst
	 * @return
	 */
	public static boolean utworzWzor(String tekst){
		   return p1.matcher(tekst).matches() || p2.matcher(tekst).matches();
		}
	
	/**
	 * Obsługa błędu wprowadzania danych w ekranie dostawy
	 */
	private void utworzOknoBledu(){
		pattern = Pattern.compile(".*[^0-9].*");
		//pattern2 = Pattern.compile(POLSKIE_ZNAKI, Pattern.CANON_EQ |Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		//matcherMiejscowosc = pattern2.matcher(txtMiejscowosc.getText());
		//matcherUlica = pattern2.matcher(txtUlica.getText());
        matcherNrMieszkania = pattern.matcher(txtNrMieszkania.getText());
        matcherNrBudynku  = pattern.matcher(txtNrBudynku.getText());
		if (matcherNrMieszkania.matches()){
			blad1=new Blad();
	        txtNrMieszkania.setText("");
		}
		else if (!utworzWzor(txtMiejscowosc.getText())){
			blad1=new Blad();
			txtMiejscowosc.setText("");
		}
		else if (!utworzWzor(txtUlica.getText())){
			blad1=new Blad();
			txtUlica.setText("");
		}
		else {
		  /*Wyświetlenie informacji pobranych z ekranu wyboru stposobu dostawy: dane zamawiającego, sposób dostawy, koszt dostawy,
	      łączny koszt zamówienia, VAT na ekranie zatwierdzanie zamówienia*/
		  txtrDaneZamawiajacego.setText("");
          txtrDaneZamawiajacego.append(txtNumerTelefonu.getText()+"\n"+txtMiejscowosc.getText()+", ul. "+txtUlica.getText()+" "+
          txtNrBudynku.getText()+"/"+txtNrMieszkania.getText());
          txtSposobDostawy.setText(listaDostawa[comboBoxDostawa.getSelectedIndex()]);
          txtKosztDostawy.setText(dec.format(kosztDostawy[comboBoxDostawa.getSelectedIndex()]));
          txtLacznyKosztZ.setText(dec.format(Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej()));
          txtVAT.setText(dec.format((Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej())*0.23));
		  CardLayout c1 = (CardLayout)(panelDolny.getLayout());
		  c1.show(panelDolny,"card4");
		  dostawa=false;
		}
	}

	/**
	 * Obsługa błędu wprowadzania danych w ekranie dostawy
	 */
	private void utworzOknoBledu2(){
		pattern = Pattern.compile(".*[^0-9].*");
		//pattern2 = Pattern.compile(POLSKIE_ZNAKI, Pattern.CANON_EQ |Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		//matcherMiejscowosc = pattern2.matcher(txtMiejscowosc.getText());
		//matcherUlica = pattern2.matcher(txtUlica.getText());
        matcherNrMieszkania = pattern.matcher(txtNrMieszkania.getText());
        matcherNrBudynku  = pattern.matcher(txtNrBudynku.getText());
		if (matcherNrMieszkania.matches()){
			blad1=new Blad();
	        txtNrMieszkania.setText("");
		}
		else if (!utworzWzor(txtMiejscowosc.getText())){
			blad1=new Blad();
			txtMiejscowosc.setText("");
		}
		else if (!utworzWzor(txtUlica.getText())){
			blad1=new Blad();
			txtUlica.setText("");
		}
		else {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card1");
		}
	}

	/**
	 * Blokada pól tekstowych przy wyborze 'Na miejscu' i 'Na wynos'
	 */
	private void blokujWprowadzanieDanych(){
		if (comboBoxDostawa.getSelectedItem() == "Na miejscu" || comboBoxDostawa.getSelectedItem() == "Na wynos"){
			txtNrMieszkania.setEnabled(false);
			txtNrBudynku.setEnabled(false);
			txtUlica.setEnabled(false);
			txtMiejscowosc.setEnabled(false);
			txtNumerTelefonu.setEnabled(false);
			txtNumerTelefonu.setText("");
			txtNumerTelefonu.setValue(null);
			txtNumerTelefonu.repaint();
			txtMiejscowosc.setText("");
			txtMiejscowosc.repaint();		
			txtUlica.setText("");
			txtUlica.repaint();
			txtNrBudynku.setText("");
			txtNrBudynku.repaint();
			txtNrMieszkania.setText("");
			txtNrMieszkania.repaint();
			lblDostawaText3.setIcon(new ImageIcon("images/dostawa_text3-gray.png"));
			lblDostawaText4.setIcon(new ImageIcon("images/dostawa_text4-gray.png"));
			lblDostawaText5.setIcon(new ImageIcon("images/dostawa_text5-gray.png"));
			lblDostawaText6.setIcon(new ImageIcon("images/dostawa_text6-gray.png"));
	        lblDostawaText7.setIcon(new ImageIcon("images/dostawa_text7-gray.png"));
		}
		else {
			txtNrMieszkania.setEnabled(true);
			txtNrBudynku.setEnabled(true);
			txtUlica.setEnabled(true);
			txtMiejscowosc.setEnabled(true);
			txtNumerTelefonu.setEnabled(true);
			txtMiejscowosc.repaint();		
			txtNrMieszkania.repaint();
			txtNrBudynku.repaint();
			txtUlica.repaint();
			txtNumerTelefonu.repaint();
			lblDostawaText3.setIcon(new ImageIcon("images/dostawa_text3.png"));
			lblDostawaText4.setIcon(new ImageIcon("images/dostawa_text4.png"));
			lblDostawaText5.setIcon(new ImageIcon("images/dostawa_text5.png"));
			lblDostawaText6.setIcon(new ImageIcon("images/dostawa_text6.png"));
	        lblDostawaText7.setIcon(new ImageIcon("images/dostawa_text7.png"));
		}
	}
		
	/**
	 * Metoda ograniczająca liczbę składników do wyboru
	 * @param isSelected
	 * @param nazwa
	 */
	private void ograniczLiczbeSkladnikow(boolean isSelected, String nazwa){
		   if (isSelected){
			    liczbaZaznaczonych++;		    
			    }
		    else{
			    liczbaZaznaczonych--;
		    }  
		   if(liczbaZaznaczonych >= 7){
				//Wyłączenie zaznaczenia dla check boxów
			    blad6 = new BladSkladniki();
			    ostatniSkladnik = nazwa;
			  			   	//Sprawdzam czy nazwa check boxa zgadza się ze zmienną
						    if (ostatniSkladnik.equals("checkBox1")){
						    	checkBox1.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if ( ostatniSkladnik.equals("checkBox2")){
						    	checkBox2.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if ( ostatniSkladnik.equals("checkBox3")){
						    	checkBox3.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if ( ostatniSkladnik.equals("checkBox4")){
						    	checkBox4.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if ( ostatniSkladnik.equals("checkBox5")){
						    	checkBox5.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else   if (ostatniSkladnik.equals("checkBox6")){
						    	checkBox6.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if (ostatniSkladnik.equals("checkBox7")){
						    	checkBox7.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else   if (ostatniSkladnik.equals("checkBox8")){
						    	checkBox8.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox9")){
						    	checkBox9.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox10")){
						    	checkBox10.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox11")){
						    	checkBox11.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox12")){
						    	checkBox12.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if (ostatniSkladnik.equals("checkBox13")){
						    	checkBox13.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if (ostatniSkladnik.equals("checkBox14")){
						    	checkBox14.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox15")){
						    	checkBox15.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox16")){
						    	checkBox16.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox17")){
						    	checkBox17.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox18")){
						    	checkBox18.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox19")){
						    	checkBox19.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox20")){
						    	checkBox20.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox21")){
						    	checkBox21.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox22")){
						    	checkBox22.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox23")){
						    	checkBox23.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox24")){
						    	checkBox24.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox25")){
						    	checkBox25.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox26")){
						    	checkBox26.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox27")){
						    	checkBox27.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox28")){
						    	checkBox28.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox29")){
						    	checkBox29.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if (ostatniSkladnik.equals("checkBox30")){
						    	checkBox30.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if (ostatniSkladnik.equals("checkBox31")){
						    	checkBox31.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if (ostatniSkladnik.equals("checkBox32")){
						    	checkBox32.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else  if (ostatniSkladnik.equals("checkBox33")){
						    	checkBox33.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox34")){
						    	checkBox34.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox35")){
						    	checkBox35.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox36")){
						    	checkBox36.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox37")){
						    	checkBox37.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox38")){
						    	checkBox38.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox39")){
						    	checkBox39.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else if (ostatniSkladnik.equals("checkBox40")){
						    	checkBox40.setSelected(false);
						    	liczbaZaznaczonych--;
						    }
						    else {
						    	
						    }
		    }
	}
   

//*************************************************************************************************************************************
		
	
	/**
	 * Metoda uruchomieniowa
	 * @param args
	 */
	public static void main(String[] args) {
		UIManager.put("ScrollBar.trackHighlight", new ColorUIResource(new Color(0xf2f2f3)));		
		new GUI();
	}
	
	
//*************************************************************************************************************************************
		
	
	/**
	 * Obsługa zdarzeń 
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnCennik) {

			//Czyszczenie ekranu własna pizza
			customCombobox.getModel().setSelectedItem("30cm");
			customCombobox.repaint();
			customCombobox2.getModel().setSelectedItem("Brak");
			customCombobox2.repaint();
			spinLiczbaPizz.setValue(1);
			
			//Wyłączenie zaznaczenia dla check boxów
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			checkBox3.setSelected(false);
			checkBox4.setSelected(false);
			checkBox5.setSelected(false);
			checkBox6.setSelected(false);
			checkBox7.setSelected(false);
			checkBox8.setSelected(false);
			checkBox9.setSelected(false);
			checkBox10.setSelected(false);
			checkBox11.setSelected(false);
			checkBox12.setSelected(false);
			checkBox13.setSelected(false);
			checkBox14.setSelected(false);
			checkBox15.setSelected(false);
			checkBox16.setSelected(false);
			checkBox17.setSelected(false);
			checkBox18.setSelected(false);
			checkBox19.setSelected(false);
			checkBox20.setSelected(false);
			checkBox21.setSelected(false);
			checkBox22.setSelected(false);
			checkBox23.setSelected(false);
			checkBox24.setSelected(false);
			checkBox25.setSelected(false);
			checkBox26.setSelected(false);
			checkBox27.setSelected(false);
			checkBox28.setSelected(false);
			checkBox29.setSelected(false);
			checkBox30.setSelected(false);
			checkBox31.setSelected(false);
			checkBox32.setSelected(false);
			checkBox33.setSelected(false);
			checkBox34.setSelected(false);
			checkBox35.setSelected(false);
			checkBox36.setSelected(false);
			checkBox37.setSelected(false);
			checkBox38.setSelected(false);
			checkBox39.setSelected(false);
			checkBox40.setSelected(false);
			
			liczbaZaznaczonych=0;
			liczydlo = 0;
			if(dostawa==true){	
				CardLayout c1 = (CardLayout)(panelDolny.getLayout());
	            c1.show(panelDolny,"card5");
			}
			else if(dostawa==false){
				//Wyłączenie zaznaczenia dla check boxów
				checkBox1.setSelected(false);
				checkBox2.setSelected(false);
				checkBox3.setSelected(false);
				checkBox4.setSelected(false);
				checkBox5.setSelected(false);
				checkBox6.setSelected(false);
				checkBox7.setSelected(false);
				checkBox8.setSelected(false);
				checkBox9.setSelected(false);
				checkBox10.setSelected(false);
				checkBox11.setSelected(false);
				checkBox12.setSelected(false);
				checkBox13.setSelected(false);
				checkBox14.setSelected(false);
				checkBox15.setSelected(false);
				checkBox16.setSelected(false);
				checkBox17.setSelected(false);
				checkBox18.setSelected(false);
				checkBox19.setSelected(false);
				checkBox20.setSelected(false);
				checkBox21.setSelected(false);
				checkBox22.setSelected(false);
				checkBox23.setSelected(false);
				checkBox24.setSelected(false);
				checkBox25.setSelected(false);
				checkBox26.setSelected(false);
				checkBox27.setSelected(false);
				checkBox28.setSelected(false);
				checkBox29.setSelected(false);
				checkBox30.setSelected(false);
				checkBox31.setSelected(false);
				checkBox32.setSelected(false);
				checkBox33.setSelected(false);
				checkBox34.setSelected(false);
				checkBox35.setSelected(false);
				checkBox36.setSelected(false);
				checkBox37.setSelected(false);
				checkBox38.setSelected(false);
				checkBox39.setSelected(false);
				checkBox40.setSelected(false);
				
				liczbaZaznaczonych=0;
				liczydlo = 0;
				CardLayout c1 = (CardLayout)(panelDolny.getLayout());
	            c1.show(panelDolny,"card2");
			}
		}
		else if(arg0.getSource() == btnZamowzMenu || arg0.getSource() == btnZamowzMenu2) {
				if(paragon==0){
					paragon=1;
					
			        //Wyświetlenie wtępnych informacji na podglądzie paragonu: nazwa, adres firmy, data, godzina
			        zamowienie.wyswietlPizzeNaParagonie("                   PizzaHub sp.z.o.o.\n", textPane2);
			        zamowienie.wyswietlPizzeNaParagonie("                     75-453 Koszalin\n", textPane2);
			        zamowienie.wyswietlPizzeNaParagonie("                    ul. Śniadeckich 2\n\n", textPane2);
			        zamowienie.wyswietlDateNaParagonie(textPane2);
			        
					Buffor.setKosztLacznyWlasnej(Buffor.getKosztWlasnej()+Buffor.getKosztLacznyWlasnej());
					
			        //Wyświetlenie kosztu zamówienia w cenniku
			        zamowienie.wyswietlLacznyKoszt(lblWyswietlLacznyKosztZamowienia, Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej(), dec);
					
					CardLayout c1 = (CardLayout)(panelDolny.getLayout());
		            c1.show(panelDolny,"card2");
				}
				else{
					CardLayout c1 = (CardLayout)(panelDolny.getLayout());
		            c1.show(panelDolny,"card2");
				}
			}
		if(arg0.getSource() == btnZamowWlasna || arg0.getSource() == btnZamowWlasna2) {
			if(paragon==0){
				paragon=1;
				
		        //Wyświetlenie wtępnych informacji na podglądzie paragonu: nazwa, adres firmy, data, godzina		        
		        zamowienie.wyswietlPizzeNaParagonie("                   PizzaHub sp.z.o.o.\n", textPane2);
		        zamowienie.wyswietlPizzeNaParagonie("                     75-453 Koszalin\n", textPane2);
		        zamowienie.wyswietlPizzeNaParagonie("                    ul. Śniadeckich 2\n\n", textPane2);
		        
		        zamowienie.wyswietlDateNaParagonie(textPane2);
				Buffor.setKosztLacznyWlasnej((Buffor.getKosztWlasnej()+Buffor.getKosztLacznyWlasnej()));
				
		        //Wyświetlenie kosztu zamówienia w cenniku
		        zamowienie.wyswietlLacznyKoszt(lblWyswietlLacznyKosztZamowienia, Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej(), dec);
				
				CardLayout c1 = (CardLayout)(panelDolny.getLayout());
	            c1.show(panelDolny,"card5");
			}
			else{
				CardLayout c1 = (CardLayout)(panelDolny.getLayout());
	            c1.show(panelDolny,"card5");
			}
		}
		else if (arg0.getSource() == btnAnulujZamowienie || arg0.getSource()==btnAnuluj || arg0.getSource() == btnAnulujZamowienie2 || 
				arg0.getSource() == btnAnulujZamowienie3 || arg0.getSource() == btnAnulujZamowienie4 || arg0.getSource() == btnAnulujZ) {
			czysc();
			paragon=0;
			Buffor.setKosztLaczny(0);
			Buffor.setKosztLacznyWlasnej(0);
			Buffor.setKosztLacznyBezDostawy(0);
			Buffor.setKosztWlasnej(0);
			Buffor.setRozmiarZamowienia(0);
			lblLacznyKoszt.setText("");
			lblLacznyKoszt.repaint();
			liczydlo=0;
			liczbaZaznaczonych=0;
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card1");
	    }
		else if(arg0.getSource() == btnDostawa3) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card3");
			Buffor.setKosztLacznyWlasnej(Buffor.getKosztLacznyWlasnej()); //zmiana1
            lblLacznyKoszt.setText((dec.format(Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej())));
            
            dostawa=false;
		}
		else if (arg0.getSource()== comboBoxDostawa){
			blokujWprowadzanieDanych();
			
			//Zmiana łącznego kosztu zamówienia po wyborze sposobu zamówienia
			Buffor.setKosztLaczny(Buffor.getKosztLacznyBezDostawy()+kosztDostawy[comboBoxDostawa.getSelectedIndex()]);
			
			//Uaktualnienie wyświetlanego łącznego kosztu zamówienia po wyborze sposobu dostawy
			Buffor.setKosztLacznyWlasnej(Buffor.getKosztLacznyWlasnej());
			lblLacznyKoszt.setText((dec.format(Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej())));
			lblWyswietlLacznyKosztZamowienia.setText((dec.format(Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej())));
			}
		else if (arg0.getSource() == btnDodajDoZamowienia){
			liczydlo = 0;
			
			//Sprawdzenie, czy osiągnięto maksymalny rozmiar zamówienia
			if(Buffor.getRozmiarZamowienia()==25){
				BladOsiagnieciaLimitu blad=new BladOsiagnieciaLimitu();
			}
			else{
				if 	(comboBoxDostawa.getSelectedItem() == "Na miejscu" || comboBoxDostawa.getSelectedItem()=="Na wynos"){
					CardLayout c1 = (CardLayout)(panelDolny.getLayout());
		            c1.show(panelDolny,"card1");
				}
				else if(comboBoxDostawa.getSelectedItem() == "Z dowozem"){
				utworzOknoBledu2();
				}				
			}
		}
		else if(arg0.getSource() == btnDostawa4) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card3");
            
    		//Czyszczenie danych zamawiającego
    		txtrDaneZamawiajacego.setText("");
    		txtrDaneZamawiajacego.repaint();
    		
    		//Czyszczenie sposobu i kosztu dostawy
    		txtSposobDostawy.setText("");
    		txtSposobDostawy.repaint();
    		txtKosztDostawy.setText("");
    		txtKosztDostawy.repaint();
    		
    		//Czyszczenie łącznego kosztu zamówienia i VAT
    		txtLacznyKosztZ.setText("");
    		txtLacznyKosztZ.repaint();
    		txtVAT.setText("");
    		txtVAT.repaint();
		}
		else if(arg0.getSource() == btnPotwierdzenie2) {
			if 	(comboBoxDostawa.getSelectedItem() == "Na miejscu" || comboBoxDostawa.getSelectedItem()=="Na wynos"){
			  /*Wyświetlenie informacji pobranych z ekranu wyboru stposobu dostawy: dane zamawiającego, sposób dostawy, koszt dostawy,
	          łączny koszt zamówienia, VAT na ekranie zatwierdzanie zamówienia*/
	          txtrDaneZamawiajacego.setText("");
	          txtSposobDostawy.setText(listaDostawa[comboBoxDostawa.getSelectedIndex()]);
	          txtKosztDostawy.setText(dec.format(kosztDostawy[comboBoxDostawa.getSelectedIndex()]));
	          
	          //Łączny koszt i vat z cennika i własnej pizzy
  			  Buffor.setKosztLacznyWlasnej(Buffor.getKosztLacznyWlasnej()); //zmiana1
	          txtLacznyKosztZ.setText((dec.format(Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej())));
	          txtVAT.setText(dec.format((Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej())*0.23));
	          
	          CardLayout c1 = (CardLayout)(panelDolny.getLayout());
	          c1.show(panelDolny,"card4");
			}
			else if(comboBoxDostawa.getSelectedItem() == "Z dowozem") {
			utworzOknoBledu();
			}		
		}
		else if(arg0.getSource() == btnMinimalizuj) {
			this.setState(JFrame.ICONIFIED);
		}
		else if(arg0.getSource() == btnZamknij) {
			dispose();
		}
		else if(arg0.getSource() == btnOK) {
			zamowienie.szukajPizzy(sorter, txtSzukaj);
		}
		else if(arg0.getSource() == btnZatwierdz) {
			//Wyświetlenie łącznego kosztu zamówienia na podglądzie paragonu
			zamowienie.wyswietlPodsumowanieParagonu("SUMA:",dec.format(Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej()), textPane2);
			System.out.println(Buffor.getKosztLaczny()+" "+Buffor.getKosztLacznyWlasnej()+" "+Buffor.getKosztLacznyBezDostawy());
		}
		else if (arg0.getSource() == btnDrukujParagon){
			zamowienie.drukujParagon(textPane2);
		}
		else if(arg0.getSource()==btnStartowyZCennika || arg0.getSource()==btnStartowyZWlasnaPizza){
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card1");
		}
		else if (arg0.getSource()==btnPomoc){
			pomoc = new Pomoc();
			pomoc.setVisible(true);
		}
		//Zdarzenia kliknięcia w checkboxy
		if (arg0.getSource() == checkBox1){
			ograniczLiczbeSkladnikow(checkBox1.isSelected(), checkBox1.getName());
		}	
		if (arg0.getSource() == checkBox2){
			ograniczLiczbeSkladnikow(checkBox2.isSelected(), checkBox2.getName());
		}
		if (arg0.getSource() == checkBox3){
			ograniczLiczbeSkladnikow(checkBox3.isSelected(), checkBox3.getName());
		}
		if (arg0.getSource() == checkBox4){
			ograniczLiczbeSkladnikow(checkBox4.isSelected(), checkBox4.getName());
		}
		if (arg0.getSource() == checkBox5){
			ograniczLiczbeSkladnikow(checkBox5.isSelected(), checkBox5.getName());
		}
		if (arg0.getSource() == checkBox6){
			ograniczLiczbeSkladnikow(checkBox6.isSelected(), checkBox6.getName());
		}
		if (arg0.getSource() == checkBox7){
			ograniczLiczbeSkladnikow(checkBox7.isSelected(), checkBox7.getName());
		}
		if (arg0.getSource() == checkBox8){
			ograniczLiczbeSkladnikow(checkBox8.isSelected(), checkBox8.getName());
		}
		if (arg0.getSource() == checkBox9){
			ograniczLiczbeSkladnikow(checkBox9.isSelected(), checkBox9.getName());
		}
		if (arg0.getSource() == checkBox10){
			ograniczLiczbeSkladnikow(checkBox10.isSelected(), checkBox10.getName());
		}
		if (arg0.getSource() == checkBox11){
			ograniczLiczbeSkladnikow(checkBox11.isSelected(), checkBox11.getName());
		}
		if (arg0.getSource() == checkBox12){
			ograniczLiczbeSkladnikow(checkBox12.isSelected(), checkBox12.getName());
		}
		if (arg0.getSource() == checkBox13){
			ograniczLiczbeSkladnikow(checkBox13.isSelected(), checkBox13.getName());
		}
		if (arg0.getSource() == checkBox14){
			ograniczLiczbeSkladnikow(checkBox14.isSelected(), checkBox14.getName());
		}
		if (arg0.getSource() == checkBox15){
			ograniczLiczbeSkladnikow(checkBox15.isSelected(), checkBox15.getName());
		}
		if (arg0.getSource() == checkBox16){
			ograniczLiczbeSkladnikow(checkBox16.isSelected(), checkBox16.getName());
		}
		if (arg0.getSource() == checkBox17){
			ograniczLiczbeSkladnikow(checkBox17.isSelected(), checkBox17.getName());
		}
		if (arg0.getSource() == checkBox18){
			ograniczLiczbeSkladnikow(checkBox18.isSelected(), checkBox18.getName());
		}
		if (arg0.getSource() == checkBox19){
			ograniczLiczbeSkladnikow(checkBox19.isSelected(), checkBox19.getName());
		}
		if (arg0.getSource() == checkBox20){
			ograniczLiczbeSkladnikow(checkBox20.isSelected(), checkBox20.getName());
		}
		if (arg0.getSource() == checkBox21){
			ograniczLiczbeSkladnikow(checkBox21.isSelected(), checkBox21.getName());
		}
		if (arg0.getSource() == checkBox22){
			ograniczLiczbeSkladnikow(checkBox22.isSelected(), checkBox22.getName());
		}
		if (arg0.getSource() == checkBox23){
			ograniczLiczbeSkladnikow(checkBox23.isSelected(), checkBox23.getName());
		}
		if (arg0.getSource() == checkBox24){
			ograniczLiczbeSkladnikow(checkBox24.isSelected(), checkBox24.getName());
		}
		if (arg0.getSource() == checkBox25){
			ograniczLiczbeSkladnikow(checkBox25.isSelected(), checkBox25.getName());
		}
		if (arg0.getSource() == checkBox26){
			ograniczLiczbeSkladnikow(checkBox26.isSelected(), checkBox26.getName());
		}
		if (arg0.getSource() == checkBox27){
			ograniczLiczbeSkladnikow(checkBox27.isSelected(), checkBox27.getName());
		}
		if (arg0.getSource() == checkBox28){
			ograniczLiczbeSkladnikow(checkBox28.isSelected(), checkBox28.getName());
		}
		if (arg0.getSource() == checkBox29){
			ograniczLiczbeSkladnikow(checkBox29.isSelected(), checkBox29.getName());
		}
		if (arg0.getSource() == checkBox30){
			ograniczLiczbeSkladnikow(checkBox30.isSelected(), checkBox30.getName());
		}
		if (arg0.getSource() == checkBox31){
			ograniczLiczbeSkladnikow(checkBox31.isSelected(), checkBox31.getName());
		}
		if (arg0.getSource() == checkBox32){
			ograniczLiczbeSkladnikow(checkBox32.isSelected(), checkBox32.getName());
		}
		if (arg0.getSource() == checkBox33){
			ograniczLiczbeSkladnikow(checkBox33.isSelected(), checkBox33.getName());
		}
		if (arg0.getSource() == checkBox34){
			ograniczLiczbeSkladnikow(checkBox34.isSelected(), checkBox34.getName());
		}
		if (arg0.getSource() == checkBox35){
			ograniczLiczbeSkladnikow(checkBox35.isSelected(), checkBox35.getName());
		}
		if (arg0.getSource() == checkBox36){
			ograniczLiczbeSkladnikow(checkBox36.isSelected(), checkBox36.getName());
		}
		if (arg0.getSource() == checkBox37){
			ograniczLiczbeSkladnikow(checkBox37.isSelected(), checkBox37.getName());
		}
		if (arg0.getSource() == checkBox38){
			ograniczLiczbeSkladnikow(checkBox38.isSelected(), checkBox38.getName());
		}
		if (arg0.getSource() == checkBox39){
			ograniczLiczbeSkladnikow(checkBox39.isSelected(), checkBox39.getName());
		}
		if (arg0.getSource() == checkBox40){
			ograniczLiczbeSkladnikow(checkBox40.isSelected(), checkBox40.getName());			
		}
		
		else if(arg0.getSource()==btnDostawa5){
			
			//Przypisanie stanów check boxów do zmiennych
			sprawdzStan1=checkBox1.isSelected();
			sprawdzStan2=checkBox2.isSelected();
			sprawdzStan3=checkBox3.isSelected();
			sprawdzStan4=checkBox4.isSelected();
			sprawdzStan5=checkBox5.isSelected();
			sprawdzStan6=checkBox6.isSelected();
			sprawdzStan7=checkBox7.isSelected();
			sprawdzStan8=checkBox8.isSelected();
			sprawdzStan9=checkBox9.isSelected();
			sprawdzStan10=checkBox10.isSelected();
			sprawdzStan11=checkBox11.isSelected();
			sprawdzStan12=checkBox12.isSelected();
			sprawdzStan13=checkBox13.isSelected();
			sprawdzStan14=checkBox14.isSelected();
			sprawdzStan15=checkBox15.isSelected();
			sprawdzStan16=checkBox16.isSelected();
			sprawdzStan17=checkBox17.isSelected();
			sprawdzStan18=checkBox18.isSelected();
			sprawdzStan19=checkBox19.isSelected();
			sprawdzStan20=checkBox20.isSelected();
			sprawdzStan21=checkBox21.isSelected();
			sprawdzStan22=checkBox22.isSelected();
			sprawdzStan23=checkBox23.isSelected();
			sprawdzStan24=checkBox24.isSelected();
			sprawdzStan25=checkBox25.isSelected();
			sprawdzStan26=checkBox26.isSelected();
			sprawdzStan27=checkBox27.isSelected();
			sprawdzStan28=checkBox28.isSelected();
			sprawdzStan29=checkBox29.isSelected();
			sprawdzStan30=checkBox30.isSelected();
			sprawdzStan31=checkBox31.isSelected();
			sprawdzStan32=checkBox32.isSelected();
			sprawdzStan33=checkBox33.isSelected();
			sprawdzStan34=checkBox34.isSelected();
			sprawdzStan35=checkBox35.isSelected();
			sprawdzStan36=checkBox36.isSelected();
			sprawdzStan37=checkBox37.isSelected();
			sprawdzStan38=checkBox38.isSelected();
			sprawdzStan39=checkBox39.isSelected();
			sprawdzStan40=checkBox40.isSelected();	
			
			//Sprawdzenie, czy nie wybrano liczby pizz (=1) i żadnego składnika
			if ((int)spinLiczbaPizz.getValue()==1 && (sprawdzStan1==false && sprawdzStan2==false && sprawdzStan3==false && 
					sprawdzStan4==false && sprawdzStan5==false && sprawdzStan6==false && sprawdzStan7==false && sprawdzStan8==false &&
					sprawdzStan9==false && sprawdzStan10==false && sprawdzStan11==false && sprawdzStan12==false && sprawdzStan13==false && 
					sprawdzStan14==false && sprawdzStan15==false && sprawdzStan16==false && sprawdzStan17==false && sprawdzStan18==false && 
					sprawdzStan19==false && sprawdzStan20==false && sprawdzStan21==false && sprawdzStan22==false && sprawdzStan23==false && 
					sprawdzStan24==false && sprawdzStan25==false && sprawdzStan26==false && sprawdzStan27==false && sprawdzStan28==false &&
					sprawdzStan29==false && sprawdzStan30==false && sprawdzStan31==false && sprawdzStan32==false && sprawdzStan33==false && 
					sprawdzStan34==false && sprawdzStan35==false && sprawdzStan36==false && sprawdzStan37==false && sprawdzStan38==false && 
					sprawdzStan39==false && sprawdzStan40==false 
					)){
				CardLayout c1 = (CardLayout)(panelDolny.getLayout());
		        c1.show(panelDolny,"card3");
		        dostawa=true;				
			}
			else{
	            //Sprawdzenie, czy limit rozmiaru zamówienia został osiągnięty
	            if(Buffor.getRozmiarZamowienia()==25){
	            	BladOsiagnieciaLimitu blad=new BladOsiagnieciaLimitu();
	            	
					CardLayout c1 = (CardLayout)(panelDolny.getLayout());
			        c1.show(panelDolny,"card3");
			        dostawa=true;
	            }
	            else{
	            	//Sprawdzenie, czy nie przekroczono maksymalnego rozmiaru zamówienia
	            	if(zamowienie.sprawdzRozmiarZamowienia((int) spinLiczbaPizz.getValue())==true){
	            		
		    			//Sprawdzenie, czy wybrano liczbę pizz (>1), ale nie zaznaczono składników
		    			if ((int)spinLiczbaPizz.getValue()>1 && (sprawdzStan1==false && sprawdzStan2==false && sprawdzStan3==false && 
		    					sprawdzStan4==false && sprawdzStan5==false && sprawdzStan6==false && sprawdzStan7==false && sprawdzStan8==false &&
		    					sprawdzStan9==false && sprawdzStan10==false && sprawdzStan11==false && sprawdzStan12==false && sprawdzStan13==false && 
		    					sprawdzStan14==false && sprawdzStan15==false && sprawdzStan16==false && sprawdzStan17==false && sprawdzStan18==false && 
		    					sprawdzStan19==false && sprawdzStan20==false && sprawdzStan21==false && sprawdzStan22==false && sprawdzStan23==false && 
		    					sprawdzStan24==false && sprawdzStan25==false && sprawdzStan26==false && sprawdzStan27==false && sprawdzStan28==false &&
		    					sprawdzStan29==false && sprawdzStan30==false && sprawdzStan31==false && sprawdzStan32==false && sprawdzStan33==false && 
		    					sprawdzStan34==false && sprawdzStan35==false && sprawdzStan36==false && sprawdzStan37==false && sprawdzStan38==false && 
		    					sprawdzStan39==false && sprawdzStan40==false 
		    					)){
		    				blad7 = new BladZaznaczoneSkladniki();
		    			}
		    			
		    			//Sprawdzenie, czy wybrano liczbę pizz (>=1) i zaznaczono składniki
		    			else if ((int)spinLiczbaPizz.getValue()>=1 || (sprawdzStan1==true || sprawdzStan2==true || sprawdzStan3==true || 
		    					sprawdzStan4==true || sprawdzStan5==true || sprawdzStan6==true || sprawdzStan7==true || sprawdzStan8==true ||
		    					sprawdzStan9==true || sprawdzStan10==true || sprawdzStan11==true || sprawdzStan12==true || sprawdzStan13==true || 
		    					sprawdzStan14==true || sprawdzStan15==true || sprawdzStan16==true || sprawdzStan17==true || sprawdzStan18==true || 
		    					sprawdzStan19==true || sprawdzStan20==true || sprawdzStan21==true || sprawdzStan22==true || sprawdzStan23==true || 
		    					sprawdzStan24==true || sprawdzStan25==true || sprawdzStan26==true || sprawdzStan27==true || sprawdzStan28==true ||
		    					sprawdzStan29==true || sprawdzStan30==true || sprawdzStan31==true || sprawdzStan32==true || sprawdzStan33==true || 
		    					sprawdzStan34==true || sprawdzStan35==true || sprawdzStan36==true || sprawdzStan37==true || sprawdzStan38==true || 
		    					sprawdzStan39==true || sprawdzStan40==true 
		    					)){
		    				
		    				Buffor.setRozmiarPizzy(listaRozmiarow[customCombobox.getSelectedIndex()]);
		    				Buffor.setLiczbaPizz((int) spinLiczbaPizz.getValue());
		    				Buffor.setSos(listaSosow[customCombobox2.getSelectedIndex()]);
		    				Buffor.setKosztWlasnej((zamowienie.ustalCene(customCombobox, cenaWlasnejPizzy)*Buffor.getLiczbaPizz()));
		    				Buffor.setKosztLacznyWlasnej((Buffor.getKosztWlasnej()+Buffor.getKosztLacznyWlasnej()));
		    				Buffor.setRozmiarZamowienia(Buffor.getRozmiarZamowienia()+Buffor.getLiczbaPizz());
		    				
		    				//Wpisanie lacznego kosztu wlasnej pizzy i z cennika w ekranie dostawy
		    		        lblLacznyKoszt.setText((dec.format(Buffor.getKosztLaczny()+Buffor.getKosztLacznyWlasnej())));
		    		        //Wpisanie wlasnej pizzy na podgladzie paragonu w ekranie dostawy
		    		        zamowienie.wyswietlPizze("Pizza własna"+"\t\t"+Buffor.getRozmiarPizzy()+"\t"+"x"+Buffor.getLiczbaPizz()+"\t"+
		    		        		dec.format(zamowienie.ustalCene(customCombobox, cenaWlasnejPizzy)*Buffor.getLiczbaPizz())+"\n", textPane);
		    		        //txtrZamowienie Wpisanie wybranej pizzy własnej do podglądu zamówienia na ekranie cennika
		    		        zamowienie.wyswietlPodgladZamowieniaWlasna(txtrZamowienie,  "Pizza własna", Buffor.getSos(), Buffor.getRozmiarPizzy(), 
		    		        		Buffor.getLiczbaPizz());
		    		        
		    		        //Sprawdzanie który składnik został wybrany i wpisanie go na pogdląd paragonu w ekranie zatwierdzenia
		    		        if(checkBox1.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("ananas,\n", textPane);
		    						}
		    						else{
			    			        	zamowienie.wyswietlSkladniki("ananas, ", textPane);
		    						}
		    			        }
		    			        if(checkBox2.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("boczek wędzony, \n", textPane);
		    						}
		    						else{
			    			        	zamowienie.wyswietlSkladniki("boczek wędzony, ", textPane);
		    						}
		    			        }
		    			        if (checkBox3.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("brokuły, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("brokuły, ", textPane);
			    					}
		    					}
		    					if (checkBox4.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("camembert, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("camembert, ", textPane);
			    					}
		    					}
		    					if (checkBox5.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("cebula biała, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("cebula biała, ", textPane);
			    					}
		    					}
		    					if (checkBox6.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("cebula czerwona, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("cebula czerwona,  ", textPane);
			    					}
		    					}
		    					if (checkBox7.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("chili, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("chili, " , textPane);
			    					}
		    					}
		    					if (checkBox8.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("czosnek, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("czosnek, ", textPane);
			    					}
		    					}
		    					if (checkBox9.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("fasola, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("fasola, ", textPane);
			    					}
		    					}

		    					if (checkBox10.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("feta, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("feta, ", textPane);
			    					}
		    					}
								if(checkBox11.isSelected()){
									liczydlo++;
		    						if(liczydlo == 3 ){
		    							zamowienie.wyswietlSkladniki("kabanosy, \n", textPane);
		    						}
		    						else{
			    			        	zamowienie.wyswietlSkladniki("kabanosy, ", textPane);
		    					}
		    			        }
		    			        if(checkBox12.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("kapary, \n", textPane);
		    						}
		    						else{
			    			        	zamowienie.wyswietlSkladniki("kapary, ", textPane);
			    					}
		    			        }
		    			        if (checkBox13.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("kiełki sojowe, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("kiełki sojowe, ", textPane);
			    					}
		    					}
		    					if (checkBox14.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("krewetki, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("krewetki, ", textPane);
			    					}
		    					}
		    					if (checkBox15.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("kukurydza, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("kukurydza, ", textPane);
			    					}
		    					}
		    					if (checkBox16.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("kurczak, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("kurczak, ", textPane);
		    						}
		    					}
		    					if (checkBox17.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("małże, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("małże, ", textPane);
		    						}
		    					}
		    					if (checkBox18.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("mozzarella, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("mozzarella, ", textPane);
		    						}
		    					}
		    					if (checkBox19.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("ogórek kiszony, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("ogórek kiszony, ", textPane);
		    						}
		    					}
		    					if (checkBox20.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("ogórek konserwowy, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("ogórek konserwowy, ", textPane);
		    						}
		    					}
		    			        if(checkBox21.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("oliwki czarne, \n", textPane);
		    						}
		    						else{
			    			        	zamowienie.wyswietlSkladniki("oliwki czarne, ", textPane);
		    						}
		    			        }
		    			        if(checkBox22.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("oliwki zielone, \n", textPane);
		    						}
		    						else{
			    			        	zamowienie.wyswietlSkladniki("oliwki zielone, ", textPane);
		    						}
		    			        }
		    			        if (checkBox23.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("oregano, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("oregano, ", textPane);
		    						}
		    					}
		    					if (checkBox24.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("papryka konserwowa, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("papryka konserwowa, ", textPane);
		    						}
		    					}
		    					if (checkBox25.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("peperoni, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("peperoni, ", textPane);
		    						}
		    					}
		    					if (checkBox26.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("pieczarki, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("pieczarki, ", textPane);
		    						}
		    					}
		    					if (checkBox27.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("pomidor, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("pomidor, ", textPane);
		    						}
		    					}
		    					if (checkBox28.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("por, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("por, ", textPane);
		    						}
		    					}
		    					if (checkBox29.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("salami, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("salami, ", textPane);
		    						}
		    					}
		    					if (checkBox30.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("ser, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("ser, ", textPane);
		    						}
		    					}
		    			        if(checkBox31.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("sos boloński, \n", textPane);
		    						}
		    						else{
			    			        	zamowienie.wyswietlSkladniki("sos boloński, ", textPane);
		    						}
		    			        }
		    			        if(checkBox32.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("sos pomidorowy, \n", textPane);
		    						}
		    						else{
			    			        	zamowienie.wyswietlSkladniki("sos pomidorowy, ", textPane);
		    						}
		    			        }
		    			        if (checkBox33.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("suszone pomidory, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("suszone pomidory, ", textPane);
		    						}
		    					}
		    					if (checkBox34.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("szparagi, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("szparagi, ", textPane);
		    						}
		    					}
		    					if (checkBox35.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("szpinak, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("szpinak, ", textPane);
		    						}
		    					}
		    					if (checkBox36.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("szynka, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("szynka, ", textPane);
		    						}
		    					}
		    					if (checkBox37.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("świeża bazylia, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("świeża bazylia, ", textPane);
		    						}
		    					}
		    					if (checkBox38.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("świeża papryka, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("świeża papryka, ", textPane);
		    						}
		    					}
		    					if (checkBox39.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("tabasco, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("tabasco, ", textPane);
		    						}
		    					}
		    					if (checkBox40.isSelected()){
		    						liczydlo++;
		    						if(liczydlo == 3 ){
			    			        	zamowienie.wyswietlSkladniki("tuńczyk, \n", textPane);
		    						}
		    						else{
			    						zamowienie.wyswietlSkladniki("tuńczyk, ", textPane);
		    						}
		    					}
		    				//Wprowadzenie nowej po wpisaniu wszystkich składników własnej pizzy
		    				zamowienie.wyswietlSkladniki("\n", textPane);
		    		        zamowienie.wyswietlSos(Buffor.getSos(),"+ sos "+Buffor.getSos()+"\n", textPane);
		    		        
		    		        //Wyświetlenie podglądu paragonu na ekranie zatwierdzania zamówienia
		    		        zamowienie.wyswietlPizzeNaParagonie("Pizza własna"+" "+Buffor.getRozmiarPizzy()+"\t"+Buffor.getLiczbaPizz()+"\t\tx\t"+
		    		       		dec.format(zamowienie.ustalCene(customCombobox, cenaWlasnejPizzy)*Buffor.getLiczbaPizz())+"\n", textPane2);
		                    zamowienie.wyswietlSos(Buffor.getSos(), "Sos "+Buffor.getSos().toLowerCase()+"\t"+"0\t\tx\t0,00"+"\n", textPane2);
							
					        //Wyświetlenie kosztu zamówienia pizz z własnego przepisu w cenniku
		                    Buffor.setKosztLacznyWlasnej(Buffor.getKosztLacznyWlasnej());
					        zamowienie.wyswietlLacznyKoszt(lblWyswietlLacznyKosztZamowienia, Buffor.getKosztLacznyWlasnej(), dec);
					        txtKosztDostawy.setText(dec.format(kosztDostawy[comboBoxDostawa.getSelectedIndex()]));
		    				CardLayout c3 = (CardLayout)(panelDolny.getLayout());
		    		        c3.show(panelDolny,"card3");
		    		        dostawa=true;   
		    			}	            		
	            	}
	            	else if(zamowienie.sprawdzRozmiarZamowienia((int) spinLiczbaPizz.getValue())==false){
	    				int wynik=25-Buffor.getRozmiarZamowienia()-((int) spinLiczbaPizz.getValue());
	    				
	    				//Wyświetlenie komunikatu o błędzie
	    				BladPrzekroczeniaLimitu blad=new BladPrzekroczeniaLimitu();
	    				
	    				spinLiczbaPizz.setValue((int) spinLiczbaPizz.getValue()+wynik);
	            	}
	            }				
			}
		}
	}
}
