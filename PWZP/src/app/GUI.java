package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


/** 
* Program do wspomagania zarządzaniem pizzerią
* Klasa GUI definiująca okno aplikacji	 	
* @version 1.0	29/03/2015
*/
public class GUI extends JFrame implements ActionListener{
	JScrollPane scrollPane, scrollPane2;
	
	/**
	 * Zmienna definiująca tabele cennika
	 */
	JTable cennikTabela;
	JPanel panelGorny, panelDolny;
	
	/**
	 * Zmienne definiujące karty dla menadżera rozkładu
	 */
	private JPanel cennik;
	
	/**
	 * Zmienne definiujące przyciski w kartach
	 */
	private JButton btnZamknij, btnZamowzMenu, btnZamowWlasna, btnAnulujZamowienie, btnAnulujZamowienie2, 
	btnAnulujZamowienie3, btnAnulujZamowienie4, btnDostawa, btnDostawa2, btnDodajDoZamowienia, btnPotwierdzenie, btnDrukuj;
	
	/**
	 * Zmienne definiujące komponenty cennika
	 */
	private JLabel lblPasek, lblCennik, lbl30cm, lbl40cm, lbl50cm, lblSzukaj, lblNumerPizzy, lblRozmiarPizzy, lblLiczbaPizz,
			lblSos, lblWprowadzNumer, lblWprowadzLiczbe, lblPasekStanu;
	private JButton btnOK, btnDodaj, btnAnuluj;
	private JTextField txtSzukaj, txtWprowadzNumer, txtWprowadzLiczbe;
	private JTextArea txtaZamowienie;
	ComboBox customCombobox, customCombobox2;
	private String[] listaRozmiarow = {"30cm", "40cm", "50cm"};
	private String[] listaSosow = {"Brak", "Czosnkowy", "Ostry"};
	
	/**
	 * Konstruktor bezparametrowy klasy GUI
	 */
	public GUI(){
		this.getContentPane().add(utworzPanelDolny(), BorderLayout.CENTER);
		this.getContentPane().add(utworzPanelGorny(), BorderLayout.NORTH);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);  
		this.setVisible(true);
	}
	
	/**
	 * Metoda tworząca górny panel.
	 * @return panel
	 */
	private JPanel utworzPanelGorny(){
		panelGorny= new JPanel();
		panelGorny.setPreferredSize(new Dimension(1366,19));
		panelGorny.setLayout(new BorderLayout());
		
		lblPasek=new JLabel(new ImageIcon("images/pasek.png"));
		
		btnZamknij=new JButton(new ImageIcon("images/zamknij.png"));
		btnZamknij.setPreferredSize(new Dimension(29,19));
		btnZamknij.addActionListener(this);
		
		panelGorny.add(lblPasek, BorderLayout.WEST);
		panelGorny.add(btnZamknij, BorderLayout.EAST);
		
		return panelGorny;
	}
	
	/**
	 * Metoda tworząca karty dla menadżer rozkładu
	 */
	private JPanel utworzPanelDolny(){
		panelDolny=new JPanel();
		panelDolny.setLayout(new CardLayout());
		
		JPanel card1 = new JPanel();
        card1.setBackground(Color.blue);
        card1.setLayout(new FlowLayout());
        card1.add(new JLabel("Ekran startowy"));
        
        btnZamowzMenu = new JButton("Zamów pizzę z menu");
        btnZamowzMenu.addActionListener(this);
        
        btnZamowWlasna = new JButton("Zamów pizzę według własnego przepisu");
        btnZamowWlasna.addActionListener(this);
        
        card1.add(btnZamowzMenu);
        card1.add(btnZamowWlasna);
        
        JPanel card2 = new JPanel(); 
        card2.setBackground(Color.WHITE);
        card2.setLayout(new FlowLayout());
        card2.add(new JLabel("Cennik"));
        
        btnAnulujZamowienie = new JButton("Anuluj zamówienie");
        btnAnulujZamowienie.addActionListener(this);
        
        btnDostawa = new JButton("Wybór sposobu dostawy");
        btnDostawa.addActionListener(this);
        
        card2.add(btnAnulujZamowienie);
        card2.add(btnDostawa);
 

        JPanel card3 = new JPanel();
        card3.setBackground(Color.YELLOW);
        card3.setLayout(new FlowLayout());
        card3.add(new JLabel("Wybór sposobu dostawy"));
        
        btnDodajDoZamowienia = new JButton("Dodaj do zamówienia");
        btnDodajDoZamowienia.addActionListener(this);
        
        btnPotwierdzenie = new JButton("Potwierdź zamówienie");
        btnPotwierdzenie.addActionListener(this);
        
        btnAnulujZamowienie2 = new JButton("Anuluj zamówienie");
        btnAnulujZamowienie2.addActionListener(this);
        
        card3.add(btnDodajDoZamowienia);
        card3.add(btnAnulujZamowienie2);
        card3.add(btnPotwierdzenie);
        
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
        
        panelDolny.add(card1, "card1");
        panelDolny.add(utworzCennik(), "card2");
        panelDolny.add(card3, "card3");
        panelDolny.add(card4, "card4");
        panelDolny.add(card5, "card5");
        
        return panelDolny;
	}
	
	private JPanel utworzCennik() {
		FormLayout layout = new FormLayout(
			"36px, 481px, 41px, 38px, 41px, 39px, 41px, 30px, 30px, 134px, 27px, 33px, 46px, 12px, 10px, 13px, 125px, 48px, 2px, 40px,"
			+ "9px, 7px, 6px, 14px, 27px, 3px, 33px",
			"115px, 29px, 17px, 19px, 27px, 32px, 6px, 15px, 2px, 2px, 7px, 6px, 15px, 2px, 2px, 7px, 6px, 15px, 2px, 2px, 7px, "
			+ "6px, 12px, 7px, 29px, 27px, 20px, 100px, 85px, 21px, 27px, 28px, 17px, 30px, 5px");
		cennik = new JPanel(layout);
		//cennik = new FormDebugPanel(layout);
		cennik.setBackground(new Color(0xf2f2f3));
		CellConstraints cc = new CellConstraints();
		
		lblCennik= new JLabel(new ImageIcon("images/cennik.png"));
		
		lbl30cm=new JLabel("<HTML><U>30cm</U></HTML>");
		lbl30cm.setFont(new Font("Arial", Font.BOLD, 17));
		
		lbl40cm=new JLabel("<HTML><U>40cm</U></HTML>");
		lbl40cm.setFont(new Font("Arial", Font.BOLD, 17));
		
		lbl50cm=new JLabel("<HTML><U>50cm</U></HTML>");
		lbl50cm.setFont(new Font("Arial", Font.BOLD, 17));
		
		txtSzukaj=new JTextField("Szukaj...");
		txtSzukaj.setOpaque(false);
		
		//Dzięki temu po kliknięciu w pole tekstowe jego zawartość jest czyszczona.
		txtSzukaj.addMouseListener(new MouseAdapter()
	    {
	        public void mouseClicked(MouseEvent e)
	        {
	            if(txtSzukaj.getText().equals("Szukaj..."))
	            {
	            	txtSzukaj.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });

		//Tworzę obramowanie we właściwym kolorze i ustawiam marginesy.
		Border line = BorderFactory.createLineBorder(new Color(0x939393));
		Border empty = new EmptyBorder(0, 9, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		
		txtSzukaj.setBorder(border);
		
		lblSzukaj=new JLabel(new ImageIcon("images/szukaj.png"));
		lblSzukaj.setLayout(new BorderLayout());
		lblSzukaj.add(txtSzukaj);
		
		btnOK=new JButton(new ImageIcon("images/ok.png"));
		btnOK.setPreferredSize(new Dimension(27,27));
		btnOK.addActionListener(this);
		
		lblNumerPizzy=new JLabel("Numer pizzy:");
		lblNumerPizzy.setFont(new Font("Arial", Font.BOLD, 17));

		lblRozmiarPizzy=new JLabel("Rozmiar pizzy:");
		lblRozmiarPizzy.setFont(new Font("Arial", Font.BOLD, 17));
		
		customCombobox = new ComboBox();
		customCombobox.setEditable(true);
		customCombobox.addItem(listaRozmiarow);
		customCombobox.setUI(ColorArrowUI.createUI(customCombobox));
		customCombobox.setBorder(line);

		lblLiczbaPizz=new JLabel("Liczba pizz:");
		lblLiczbaPizz.setFont(new Font("Arial", Font.BOLD, 17));
		
		lblSos=new JLabel("Sos:");
		lblSos.setFont(new Font("Arial", Font.BOLD, 17));
	
		customCombobox2 = new ComboBox();
		customCombobox2.setEditable(true);
		customCombobox2.addItem(listaSosow);
		customCombobox2.setUI(ColorArrowUI.createUI(customCombobox2));
		customCombobox2.setBorder(line);

		Border empty2 = new EmptyBorder(0, 7, 0, 0);
		CompoundBorder border2 = new CompoundBorder(line, empty2);
		
		txtWprowadzNumer=new JTextField();
		txtWprowadzNumer.setOpaque(false);
		txtWprowadzNumer.setBorder(border2);
		txtWprowadzNumer.setFont(new Font("Arial", Font.PLAIN, 17));

		lblWprowadzNumer=new JLabel(new ImageIcon("images/liczba.png"));
		lblWprowadzNumer.setLayout(new BorderLayout());
		lblWprowadzNumer.add(txtWprowadzNumer);
		
		txtWprowadzLiczbe=new JTextField();
		txtWprowadzLiczbe.setOpaque(false);
		txtWprowadzLiczbe.setBorder(border2);
		txtWprowadzLiczbe.setFont(new Font("Arial", Font.PLAIN, 17));

		lblWprowadzLiczbe=new JLabel(new ImageIcon("images/liczba.png"));
		lblWprowadzLiczbe.setLayout(new BorderLayout());
		lblWprowadzLiczbe.add(txtWprowadzLiczbe);
		
		btnDodaj=new JButton(new ImageIcon("images/dodaj.png"));
		btnDodaj.setPreferredSize(new Dimension(208,27));
		btnDodaj.addActionListener(this);
		
		
		txtaZamowienie=new JTextArea();
		txtaZamowienie.setMargin(new Insets(0,9,0,0));
		txtaZamowienie.setBackground(new Color(0xeaeaeb));
		scrollPane=new JScrollPane(txtaZamowienie);
		
		Border empty3 = new EmptyBorder(0, 0, 0, 0);
		CompoundBorder border3 = new CompoundBorder(line, empty3);
		
		scrollPane.setBorder(border3);
		
		//Tabela Cennik
		String[] kolumny = {"Nazwa", "Rozmiar1","Rozmiar2", "Rozmiar3"};
		
		//Ustawienie czcionki dla zawartosci tabeli cennik
		FontUIResource font = new FontUIResource("Arial", Font.PLAIN, 17);
		UIManager.put("Table.font", font);
		
		//zawartosc tabeli
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
						+ "&nbsp;&nbsp;&nbsp;&nbsp<font size='16px'>ser, sospomidorowy, szynka, pieczarki,</font><br>"
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
			
		cennikTabela = new JTable(dane, kolumny);
		
		//wyrownanie zawartosci tabeli do gory
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)cennikTabela.getDefaultRenderer(cennikTabela.getColumnClass(0));
		renderer.setVerticalAlignment(SwingConstants.NORTH);
		
		cennikTabela.setEnabled(false);
		cennikTabela.setShowGrid(false);
		cennikTabela.setIntercellSpacing(new Dimension(0, 0));
		
		cennikTabela.getColumn("Nazwa").setMinWidth(481);
		cennikTabela.getColumn("Nazwa").setMaxWidth(481);
		cennikTabela.getColumn("Rozmiar1").setMinWidth(80);
		cennikTabela.getColumn("Rozmiar1").setMaxWidth(80);
		cennikTabela.getColumn("Rozmiar2").setMinWidth(80);
		cennikTabela.getColumn("Rozmiar2").setMaxWidth(80);
		cennikTabela.getColumn("Rozmiar3").setMinWidth(70);
		cennikTabela.getColumn("Rozmiar3").setMaxWidth(70);

		cennikTabela.setBackground(null);
	    cennikTabela.setTableHeader(null);
	    
	    //dodanie odpowiednich wysokosci wierszy
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
		
		btnAnuluj=new JButton(new ImageIcon("images/anuluj.png"));
		btnAnuluj.setPreferredSize(new Dimension(208,27));
		btnAnuluj.addActionListener(this);
		
		lblPasekStanu=new JLabel(new ImageIcon("images/pasekstanu.png"));
		
		cennik.add(lblCennik, cc.xyw(1,1,27)); 
		cennik.add(lbl30cm, cc.xy(3,3));
		cennik.add(lbl40cm, cc.xy(5,3));
		cennik.add(lbl50cm, cc.xy(7,3));
		cennik.add(lblSzukaj, cc.xyw(11,5,14));
		cennik.add(btnOK, cc.xy(25,5));
		cennik.add(lblNumerPizzy, cc.xywh(12,8,5,2));
		cennik.add(lblRozmiarPizzy, cc.xywh(12,13,6,2));
		cennik.add(customCombobox, cc.xywh(20,12,5,4));
		cennik.add(lblLiczbaPizz, cc.xywh(12,18,4,2));
		cennik.add(lblSos, cc.xyw(12,23,2));
		cennik.add(customCombobox2, cc.xywh(18,22,7,3));
		cennik.add(lblWprowadzNumer, cc.xywh(21,7,4,4));
		cennik.add(lblWprowadzLiczbe, cc.xywh(21,17,4,4));
		cennik.add(btnDodaj, cc.xyw(14,26,5));
		cennik.add(btnAnuluj, cc.xyw(14,31,5));
		cennik.add(scrollPane, cc.xywh(11,28,15,2)); 
		cennik.add(scrollPane2,cc.xywh(2, 5, 7, 27)); 
		cennik.add(lblPasekStanu, cc.xywh(1,33,27,3)); 
		
		return cennik;
	}
	
	/**
	 * Metoda uruchomieniowa
	 * @param args
	 */
	public static void main(String[] args) {
		UIManager.put("ScrollBar.trackHighlight", new ColorUIResource(new Color(0xf2f2f3)));		
		new GUI();
	}
	
	
	/**
	 * Obsługa zdarzeń 
	 */
	@Override
    

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnZamowzMenu) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card2");
		}
		else if(arg0.getSource() == btnZamowWlasna) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card5");
		}
		else if(arg0.getSource() == btnAnulujZamowienie || arg0.getSource() == btnDodajDoZamowienia || arg0.getSource() == btnDrukuj || arg0.getSource() == btnAnulujZamowienie2 || arg0.getSource() == btnAnulujZamowienie3 || arg0.getSource() == btnAnulujZamowienie4) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card1");
		}
		else if(arg0.getSource() == btnDostawa || arg0.getSource() == btnDostawa2) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card3");
		}
		else if(arg0.getSource() == btnPotwierdzenie) {
			CardLayout c1 = (CardLayout)(panelDolny.getLayout());
            c1.show(panelDolny,"card4");
		}	
		else if(arg0.getSource() == btnZamknij) {
			dispose();
		}
	}
}

