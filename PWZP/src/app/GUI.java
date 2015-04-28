package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource.CompoundBorderUIResource;
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
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

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
	
	/**
	 * Deklaracja górnego i dolnego panelu
	 */

	JPanel panelGorny, panelDolny;
	
	/**
	 * Deklaracja kart dla menadżera rozkładu
	 */
	private JPanel ekranStartowy, cennik, ekranDostawy, ekranZatwierdzaniaZamowienia;

	
	/**
	 * Deklaracja przycisków
	 */
	private JButton btnZamknij, btnMinimalizuj, btnAnulujZamowienie, 
	btnAnulujZamowienie3, btnAnulujZamowienie4, btnDostawa, btnDostawa2, btnDrukuj;
	
	/**
	 * Deklaracja komponentów ekranu startowego
	 */
	private int paragon; //Zmienna, której wartość wskazuje, czy w polu Podgląd paragonu wyświetlić informacje wstępne
	private JButton btnZamowzMenu, btnZamowzMenu2, btnZamowWlasna, btnZamowWlasna2, btnPomoc;
	private JLabel lblStartowyLogo, lblMenuImage, lblWlasnaImage, lblPasekStanuEkranStartowy;
	
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
	private JButton btnAnulujZamowienie2, btnDodajDoZamowienia, btnPotwierdzenie, btnPotwierdzenie2, btnCennik;
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
	
	/**
	 * Deklaracja komponentów ekranu zatwierdzania zamówienia
	 */
	private JLabel lblZatwierdzenieNaglowek, lblPodgladParagonu, lblDaneZamawiajacego, lblSposobDostawy, lblWyswietlSposobDostawy,
		lblKosztDostawy, lblWyswietlKosztDostawy, lblLacznyKosztZ, lblWyswietlLacznyKosztZ, lblVAT, lblWyswietlVAT, lblPasekStanuZatwierdzanie;
	private JTextField txtDaneZamawiajacego, txtSposobDostawy, txtKosztDostawy, txtLacznyKosztZ, txtVAT;
	private JTextArea txtrPodgladParagonu, txtrDaneZamawiajacego;
	private JButton btnZatwierdz, btnAnulujZ, btnDostawa4, btnDrukujParagon;
	private JScrollPane scrollPane4;
	
	/**
	 * Deklaracja zmiennych dla okna błędu wprowadzania znaków
	 */
	Pattern pattern, pattern2;
	private static final String POLSKIE_ZNAKI = "^[\\p{L}*]+";
    Matcher matcherNrBudynku, matcherNrMieszkania, matcherMiejscowosc, matcherUlica;
    private Blad blad1, blad2, blad3;
	
//*************************************************************************************************************************************
	
	
	/**
	 * Konstruktor bezparametrowy klasy GUI
	 */
	public GUI(){
		/*
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		*/
		this.getContentPane().add(utworzPanelDolny(), BorderLayout.CENTER);
		this.getContentPane().add(utworzPanelGorny(), BorderLayout.NORTH);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImageIcon icon = new ImageIcon("images/ico.png");
		this.setIconImage(icon.getImage());
		this.setUndecorated(true);
		//this.setAlwaysOnTop(true);
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

        JPanel card4 = new JPanel();
        card4.setBackground(Color.ORANGE);
        card4.setLayout(new FlowLayout());
        card4.add(new Label("Potwierdzenie zamówienia"));
        btnDrukuj = new JButton("Drukuj paragon");
        btnDrukuj.addActionListener(this);
        btnAnulujZamowienie3 = new JButton("Anuluj zamówienie");
        btnAnulujZamowienie3.addActionListener(this);
        card4.add(btnDrukuj);
        card4.add(btnAnulujZamowienie3);
        
        JPanel card5 = new JPanel();
        card5.setBackground(Color.green);
        card5.setLayout(new FlowLayout());
        card5.add(new JLabel("Wybór składników"));
        
        btnAnulujZamowienie4 = new JButton("Anuluj zamówienie");
        btnAnulujZamowienie4.addActionListener(this);
        
        btnDostawa2 = new JButton("Wybór sposobu dostawy");
        btnDostawa2.addActionListener(this);
        
        card5.add(btnDostawa2);
        card5.add(btnAnulujZamowienie4);
        
        panelDolny.add(utworzEkranStartowy(), "card1");
        panelDolny.add(utworzCennik(), "card2");
        panelDolny.add(utworzEkranDostawy(), "card3");
        panelDolny.add(utworzEkranZatwierdzania(), "card4");
        panelDolny.add(card5, "card5");
        
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
				{"<html><strong>11. Margherita</strong><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, oregano</font></html>","9,50", "17,80", "19,90"},
				{"<html><strong>12. Margherita</strong><br>"
					    + "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, oregano</font></html>","9,50", "17,80", "19,90"},
			    {"<html><strong>12. Margherita</strong><br>"
					    + "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, oregano</font></html>","9,50", "17,80", "19,90"},
				{"<html><strong>12. Margherita</strong><br>"
					    + "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, oregano</font></html>","9,50", "17,80", "19,90"},
				{"<html><strong>12. Margherita</strong><br>"
					    + "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sos pomidorowy, oregano</font></html>","9,50", "17,80", "19,90"},
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
	            if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();                    
                    cennikTabela = (JTable) e.getSource();
                    Point p = e.getPoint();
                    
                    //Pobranie i zapisanie wybranego numeru rzędu z tablicy
                    int row = cennikTabela.rowAtPoint(e.getPoint());
                    String mychar = String.valueOf(cennikTabela.getValueAt(row, 0).toString().charAt(14)+""+cennikTabela.getValueAt(row, 0).toString().charAt(15)).split("\\.")[0];
                    buffor.setNumerRzedu(Integer.parseInt(mychar)-1);
                    System.out.println(buffor.getNumerRzedu());
                    
                    //Utworzenie obiektu klasu Dialog
                    dialog=new Dialog();
                    
                    //Sprawdzam, czy użytkownik zamknął dialog czy użył przycisku "Dodaj do zamówienia"
                    if(buffor.getDodaj()==1){
                    	
                        //Wyświetlenie podglądu zamówienia w cenniku
                        zamowienie.wyswietlPodgladZamowienia(txtrZamowienie, buffor.getSos(), buffor.getNazwaPizzy(), buffor.getRozmiarPizzy(), 
                        		buffor.getLiczbaPizz());
                        
                        //Wyświetlenie łącznego kosztu zamówienia
                        zamowienie.wyswietlLacznyKoszt(lblWyswietlLacznyKosztZamowienia, buffor.getKosztLaczny(), dec);   
                        
                        //Wyświetlenie podglądu zamówienia na ekranie dostawy
                        
                        zamowienie.wyswietlPizze(buffor.getNazwaPizzy()+"\t\t"+buffor.getRozmiarPizzy()+"\t"+"x"+buffor.getLiczbaPizz()+"\t"+
                        		dec.format(buffor.getKosztElementu())+"\n", textPane);
                        zamowienie.wyswietlSkladniki(buffor.skladniki[buffor.getNumerRzedu()]+"\n", textPane);
                        zamowienie.wyswietlSos(buffor.getSos(),"+ sos "+buffor.getSos()+"\n", textPane);
                        
                        //Wyświetlenie podglądu paragonu na ekranie zatwierdzania zamówienia
                        zamowienie.wyswietlPizzeNaParagonie(buffor.getNazwaPizzy()+" "+buffor.getRozmiarPizzy()+"\t"+buffor.getLiczbaPizz()+"\t\tx\t"+
                        		dec.format(buffor.getCena())+"\n", textPane2);
                        zamowienie.wyswietlSos(buffor.getSos(), "Sos "+buffor.getSos().toLowerCase()+"\t"+"0\t\tx\t0,00"+"\n", textPane2);
                        
                    }
                    else{
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
        cennikTabela.setRowHeight(11, 40);
        cennikTabela.setRowHeight(12, 40);
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
		
		/*
		//Przycisk 'Anuluj zamówienie'
		btnAnuluj=new JButton(new ImageIcon("images/anuluj.png"));
		btnAnuluj.setPreferredSize(new Dimension(208,27));
		btnAnuluj.addActionListener(this);
		*/
		
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
		//cennik.add(btnAnuluj, cc.xyw(13,11,4, cc.CENTER, cc.FILL));
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
		
		/*
		//Przycisk "Anuluj zamówienie"
		btnAnulujZamowienie2 = new JButton(new ImageIcon("images/anuluj.png"));
		btnAnulujZamowienie2.addActionListener(this);
		*/
		
		//Przycisk umożliwiający powiększenie zamówienia o dodatkowe pozycje
		btnDodajDoZamowienia = new JButton(new ImageIcon("images/powieksz_zamowienie.png"));
		btnDodajDoZamowienia.addActionListener(this);
		btnDodajDoZamowienia.setPreferredSize(new Dimension(209,27));
		
		//btnPotwierdzenie = new JButton(new ImageIcon("images/potwierdz.png"));
		//btnPotwierdzenie.addActionListener(this);
		
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
		
		lblMiejscowosc=new JLabel(new ImageIcon("images/tlo_input.png"));
		lblMiejscowosc.setLayout(new BorderLayout());
		lblMiejscowosc.add(txtMiejscowosc);
		
		txtUlica = new JTextField();
		txtUlica.setOpaque(false);
		txtUlica.setBorder(border2);
		txtUlica.setFont(new Font("Arial", Font.PLAIN, 17));
		
		lblUlica=new JLabel(new ImageIcon("images/tlo_input.png"));
		lblUlica.setLayout(new BorderLayout());
		lblUlica.add(txtUlica);
		
		txtNrBudynku = new JTextField();
		txtNrBudynku.setOpaque(false);
		txtNrBudynku.setBorder(border2);
		txtNrBudynku.setFont(new Font("Arial", Font.PLAIN, 17));
		
		lblNrBudynku=new JLabel(new ImageIcon("images/liczba.png"));
		lblNrBudynku.setLayout(new BorderLayout());
		lblNrBudynku.add(txtNrBudynku);
		
		txtNrMieszkania = new JTextField();
		txtNrMieszkania.setOpaque(false);
		txtNrMieszkania.setBorder(border2);
		txtNrMieszkania.setFont(new Font("Arial", Font.PLAIN, 17));
		
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
		//ekranDostawy.add(btnAnulujZamowienie2, cc.xywh(10, 21, 5, 1));
		ekranDostawy.add(btnDodajDoZamowienia, cc.xywh(4, 21, 4, 1, cc.CENTER, cc.FILL));
		//ekranDostawy.add(btnPotwierdzenie, cc.xywh(10, 18, 5, 1));
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
		
		//Przycisk "Zatwierdź zamówienie"
		btnZatwierdz=new JButton(new ImageIcon("images/zatwierdz.png"));
		btnZatwierdz.addActionListener(this);
		
		//Przycisk "Drukuj paragon"
		btnDrukujParagon=new JButton(new ImageIcon("images/drukuj.png"));
		btnDrukujParagon.addActionListener(this);
		
		//Przycisk "Anuluj zamówienie"
		btnAnulujZ=new JButton(new ImageIcon("images/anuluj.png"));
		btnAnulujZ.addActionListener(this);		
		
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
		ekranZatwierdzaniaZamowienia.add(txtrDaneZamawiajacego, cc.xywh(14,4,5,4));
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
		comboBoxDostawa.getModel().setSelectedItem("Na wynos");
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
		txtLacznyKosztZ.repaint();
		txtVAT.setText("");
		txtVAT.repaint();
	}
	
	/**
	 * Obsługa błędu wprowadzania danych w ekranie dostawy
	 */
	private void utworzOknoBledu(){
		pattern = Pattern.compile(".*[^0-9].*");
		pattern2 = Pattern.compile(POLSKIE_ZNAKI, Pattern.CANON_EQ |Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcherMiejscowosc = pattern2.matcher(txtMiejscowosc.getText());
		matcherUlica = pattern2.matcher(txtUlica.getText());
        matcherNrMieszkania = pattern.matcher(txtNrMieszkania.getText());
        matcherNrBudynku  = pattern.matcher(txtNrBudynku.getText());
		if (matcherNrMieszkania.matches()){
			blad1=new Blad();
	        txtNrMieszkania.setText("");
		}
		else if (!matcherMiejscowosc.matches()){
			blad1=new Blad();
			txtMiejscowosc.setText("");
		}
		else if (!matcherUlica.matches()){
			blad1=new Blad();
			txtUlica.setText("");
		}
		else {
			/*Wyświetlenie informacji pobranych z ekranu wyboru stposobu dostawy: dane zamawiającego, sposób dostawy, koszt dostawy,
            łączny koszt zamówienia, VAT na ekranie zatwierdzanie zamówienia*/
          buffor.setKosztLaczny(buffor.getKosztLaczny()+kosztDostawy[comboBoxDostawa.getSelectedIndex()]);
          txtrDaneZamawiajacego.setText("");
          txtrDaneZamawiajacego.append(txtNumerTelefonu.getText()+"\n"+txtMiejscowosc.getText()+", ul. "+txtUlica.getText()+" "+
          		txtNrBudynku.getText()+"/"+txtNrMieszkania.getText());
          txtSposobDostawy.setText(listaDostawa[comboBoxDostawa.getSelectedIndex()]);
          txtKosztDostawy.setText(dec.format(kosztDostawy[comboBoxDostawa.getSelectedIndex()]));
          txtLacznyKosztZ.setText(dec.format(buffor.getKosztLaczny()));
          txtVAT.setText(dec.format(buffor.getKosztLaczny()*0.23));
		  //dialogBlad.dispose();
		  CardLayout c1 = (CardLayout)(panelDolny.getLayout());
		  c1.show(panelDolny,"card4");
		}
	}
	/**
	 * Obsługa błędu wprowadzania danych w ekranie dostawy
	 */
	private void utworzOknoBledu2(){
		pattern = Pattern.compile(".*[^0-9].*");
		pattern2 = Pattern.compile(POLSKIE_ZNAKI, Pattern.CANON_EQ |Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcherMiejscowosc = pattern2.matcher(txtMiejscowosc.getText());
		matcherUlica = pattern2.matcher(txtUlica.getText());
        matcherNrMieszkania = pattern.matcher(txtNrMieszkania.getText());
        matcherNrBudynku  = pattern.matcher(txtNrBudynku.getText());
		if (matcherNrMieszkania.matches()){
			blad1=new Blad();
	        txtNrMieszkania.setText("");
		}
		else if (!matcherMiejscowosc.matches()){
			txtMiejscowosc.setText("");
		}
		else if (!matcherUlica.matches()){
			blad1=new Blad();
			txtUlica.setText("");
		}
		else {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card4");
            
            /*Wyświetlenie informacji pobranych z ekranu wyboru stposobu dostawy: dane zamawiającego, sposób dostawy, koszt dostawy,
              łączny koszt zamówienia, VAT na ekranie zatwierdzanie zamówienia*/
            buffor.setKosztLaczny(buffor.getKosztLaczny()+kosztDostawy[comboBoxDostawa.getSelectedIndex()]);
            txtrDaneZamawiajacego.setText("");
            txtrDaneZamawiajacego.append(txtNumerTelefonu.getText()+"\n"+txtMiejscowosc.getText()+", ul. "+txtUlica.getText()+" "+
            		txtNrBudynku.getText()+"/"+txtNrMieszkania.getText());
            txtSposobDostawy.setText(listaDostawa[comboBoxDostawa.getSelectedIndex()]);
            txtKosztDostawy.setText(dec.format(kosztDostawy[comboBoxDostawa.getSelectedIndex()]));
            txtLacznyKosztZ.setText(dec.format(buffor.getKosztLaczny()));
            txtVAT.setText(dec.format(buffor.getKosztLaczny()*0.23));
            
			//dialogBlad.dispose();
		}
	}
	/**
	 * Obsługa błędu wprowadzania danych w ekranie dostawy
	 */
	private void utworzOknoBledu3(){
		pattern = Pattern.compile(".*[^0-9].*");
		pattern2 = Pattern.compile(POLSKIE_ZNAKI, Pattern.CANON_EQ |Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcherMiejscowosc = pattern2.matcher(txtMiejscowosc.getText());
		matcherUlica = pattern2.matcher(txtUlica.getText());
        matcherNrMieszkania = pattern.matcher(txtNrMieszkania.getText());
        matcherNrBudynku  = pattern.matcher(txtNrBudynku.getText());
		if (matcherNrMieszkania.matches()){
			blad1=new Blad();
	        txtNrMieszkania.setText("");
		}
		else if (!matcherMiejscowosc.matches()){
			blad1=new Blad();
			txtMiejscowosc.setText("");
		}
		else if (!matcherUlica.matches()){
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
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card2");
		}
		else if(arg0.getSource() == btnZamowzMenu || arg0.getSource() == btnZamowzMenu2) {
				if(paragon==0){
					paragon=1;
					
			        //Wyświetlenie wtępnych informacji na podglądzie paragonu: nazwa, adres firmy, data, godzina
			        zamowienie.wyswietlNaglowekParagonu("PizzaHub sp.z.o.o."+"\n"+"75-453 Koszalin"+"\n"+"ul. Śniadeckich 2"+"\n\n", textPane2);
			        zamowienie.wyswietlDateNaParagonie(textPane2);
			        
			        //Wyświetlenie kosztu zamówienia w cenniku
			        zamowienie.wyswietlLacznyKoszt(lblWyswietlLacznyKosztZamowienia, buffor.getKosztLaczny(), dec);
					
					CardLayout c1 = (CardLayout)(panelDolny.getLayout());
		            c1.show(panelDolny,"card2");
				}
				else{
					CardLayout c1 = (CardLayout)(panelDolny.getLayout());
		            c1.show(panelDolny,"card2");
				}
			}
		if(arg0.getSource() == btnZamowWlasna || arg0.getSource() == btnZamowWlasna2) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card5");
		}
		else if(arg0.getSource() == btnDrukuj) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card1");
		}
		else if (arg0.getSource() == btnAnulujZamowienie || arg0.getSource()==btnAnuluj || arg0.getSource() == btnAnulujZamowienie2 || 
				arg0.getSource() == btnAnulujZamowienie3 || arg0.getSource() == btnAnulujZamowienie4 || arg0.getSource() == btnAnulujZ) {
			czysc();
			paragon=0;
			buffor.setKosztLaczny(0);
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card1");
	     }
		else if(arg0.getSource() == btnDostawa || arg0.getSource() == btnDostawa2) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card3");
		}
		else if(arg0.getSource() == btnDostawa3) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card3");
            lblLacznyKoszt.setText((dec.format(buffor.getKosztLaczny())));
		}
		else if (arg0.getSource()== comboBoxDostawa){
			blokujWprowadzanieDanych();
		}
		else if (arg0.getSource() == btnDodajDoZamowienia){
			if 	(comboBoxDostawa.getSelectedItem() == "Na miejscu" || comboBoxDostawa.getSelectedItem()=="Na wynos"){
				CardLayout c1 = (CardLayout)(panelDolny.getLayout());
	            c1.show(panelDolny,"card1");
			}
			else if(comboBoxDostawa.getSelectedItem() == "Z dowozem"){
			utworzOknoBledu3();
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
		/*
		else if(arg0.getSource() == btnPotwierdzenie) {
			if 	(comboBoxDostawa.getSelectedItem() == "Na miejscu" || comboBoxDostawa.getSelectedItem()=="Na wynos"){
				CardLayout c1 = (CardLayout)(panelDolny.getLayout());
	            c1.show(panelDolny,"card4");
			}
			else if(comboBoxDostawa.getSelectedItem() == "Z dowozem") {
			utworzOknoBledu();
			}
		}
		*/
		else if(arg0.getSource() == btnPotwierdzenie2) {
			if 	(comboBoxDostawa.getSelectedItem() == "Na miejscu" || comboBoxDostawa.getSelectedItem()=="Na wynos"){
				/*Wyświetlenie informacji pobranych z ekranu wyboru stposobu dostawy: dane zamawiającego, sposób dostawy, koszt dostawy,
	            łączny koszt zamówienia, VAT na ekranie zatwierdzanie zamówienia*/
	          buffor.setKosztLaczny(buffor.getKosztLaczny()+kosztDostawy[comboBoxDostawa.getSelectedIndex()]);
	          txtrDaneZamawiajacego.setText("");
	          //txtrDaneZamawiajacego.append(txtNumerTelefonu.getText()+"\n"+txtMiejscowosc.getText()+", ul. "+txtUlica.getText()+" "+
	          //		txtNrBudynku.getText()+"/"+txtNrMieszkania.getText());  
	          txtSposobDostawy.setText(listaDostawa[comboBoxDostawa.getSelectedIndex()]);
	          txtKosztDostawy.setText(dec.format(kosztDostawy[comboBoxDostawa.getSelectedIndex()]));
	          txtLacznyKosztZ.setText(dec.format(buffor.getKosztLaczny()));
	          txtVAT.setText(dec.format(buffor.getKosztLaczny()*0.23));
	          
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
			//dialogBlad.dispose();
		}
		else if(arg0.getSource() == btnOK) {
			zamowienie.szukajPizzy(sorter, txtSzukaj);
		}
		else if(arg0.getSource() == btnZatwierdz) {
			//Wyświetlenie łącznego kosztu zamówienia na podglądzie paragonu
			zamowienie.wyswietlPodsumowanieParagonu("SUMA:",dec.format(buffor.getKosztLaczny()), textPane2);
			/*
			paragon=0;
			czysc();
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card1");
            */
		}
		else if (arg0.getSource() == btnDrukujParagon){
			/*
			try {
                boolean done = textPane2.print();
                if (done) {
                    JOptionPane.showMessageDialog(null, "Drukowanie zakończyło się pomyślnie!");
                } else {
                    JOptionPane.showMessageDialog(null, "Błąd podczas drukowania.");
                }
            } catch (Exception pex) {
                JOptionPane.showMessageDialog(null, "Błąd podczas drukowania.");
                pex.printStackTrace();
            }*/
		}
		else if(arg0.getSource()==btnStartowyZCennika){
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card1");
		}
	}
}