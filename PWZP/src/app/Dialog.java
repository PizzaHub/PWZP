package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Klasa Dialog rozszerzona o klasę JDialog.
 * Służy do pobierania od użytkownika informacji o zamówieniu: rozmiaru, liczby pizz i rodzaju sosu.
 *
 */
class Dialog extends JDialog implements ActionListener {
	
	/**
	 * Utworzenie obiektu klasy Buffor
	 */
	private Buffor buffor=new Buffor();
	
	/**
	 * Deklaracja komponentów 
	 */
	private JPanel panelCentralny;
	
	private JLabel lblNazwaPizzy, lblRozmiarPizzy, lblLiczbaPizz, lblWprowadzLiczbePizz, lblSos, lblKoszt, lblWyswietlKoszt;
	private JButton btnDodaj;
	private JTextField txtWprowadzLiczbePizz;
	private ComboBox customCombobox, customCombobox2;
	
	private String[] listaRozmiarow = {"30cm", "40cm", "50cm"};
	private String[] listaSosow = {"Brak", "Czosnkowy", "Ostry"};
	
	/**
	 * Deklaracja zmiennych dla okna błędu wprowadzania znaków 
	 */
	Pattern pattern;
    Matcher matcherNumerPizzy;
    JOptionPane optionPane = new JOptionPane("Wprowadź poprawną wartość",0);
	JDialog dialogBlad = optionPane.createDialog("Błąd!");
	
//*************************************************************************************************************************************
	
	/**
	 * Konstruktor bezparametrowy klasy Dialog
	 */
	public Dialog(){
		this.setSize(322,235);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.getContentPane().add(utworzPanelCentralny(), BorderLayout.CENTER);
		setModal(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Pizza");
		
		this.setVisible(true);		
	}
	
	/**
	 * Metoda tworząca panelCentralny
	 * @return
	 */
	private JPanel utworzPanelCentralny() {
		buffor.setDodaj(0);
		
		FormLayout layout = new FormLayout(                                                              
				"30px, 21px, 53px, 32px, 14px, 36px, 38px, 51px, 30px", 
				"10px, 23px, 20px, 25px, 5px, 25px, 5px, 25px, 20px, 27px, 10px");	
		CellConstraints cc = new CellConstraints();
		
		panelCentralny=new JPanel(layout);
		//panelCentralny=new FormDebugPanel(layout);
		panelCentralny.setBackground(new Color(0xf2f2f3));
		
		//Obramowanie dla txtWprowadzLiczbePizz
		Border line = BorderFactory.createLineBorder(new Color(0x939393));
		Border empty2 = new EmptyBorder(2, 7, 0, 0);
		CompoundBorder border2 = new CompoundBorder(line, empty2);
		
		//Nazwa pizzy
		lblNazwaPizzy=new JLabel(buffor.dane[buffor.getNumerRzedu()][0]);
		lblNazwaPizzy.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNazwaPizzy.setForeground(Color.black);
		
		//Napis "Rozmiar pizzy"
		lblRozmiarPizzy=new JLabel(new ImageIcon("images/rozmiar_dialog.png"));
		
		//Pole wyboru rozmiaru pizzy
		customCombobox = new ComboBox();
		customCombobox.setEditable(true);
		customCombobox.addItem(listaRozmiarow);
		customCombobox.setUI(ColorArrowUI.createUI(customCombobox));
		customCombobox.setBorder(BorderFactory.createLineBorder(new Color(0x939393)));
		
		//Napis "Liczba pizz"
		lblLiczbaPizz=new JLabel(new ImageIcon("images/liczba_dialog.png"));
		
		//Pole do wprowadzania liczby pizz
		txtWprowadzLiczbePizz=new JTextField();
		txtWprowadzLiczbePizz.setOpaque(false);
		txtWprowadzLiczbePizz.setBorder(border2);
		txtWprowadzLiczbePizz.setFont(new Font("Arial", Font.PLAIN, 17));
		txtWprowadzLiczbePizz.setForeground(Color.BLACK);
		
		lblWprowadzLiczbePizz=new JLabel(new ImageIcon("images/liczba.png"));
		lblWprowadzLiczbePizz.setLayout(new BorderLayout());
		lblWprowadzLiczbePizz.add(txtWprowadzLiczbePizz);
		
		//Napis "Sos"
		lblSos=new JLabel(new ImageIcon("images/sos_dialog.png"));
		
		//Pole wyboru rodzaju sosu
		customCombobox2 = new ComboBox();
		customCombobox2.setEditable(true);
		customCombobox2.addItem(listaSosow);
		customCombobox2.setUI(ColorArrowUI.createUI(customCombobox));
		customCombobox2.setBorder(BorderFactory.createLineBorder(new Color(0x939393)));
		
		//Przycisk "Dodaj do zamówienia"
		btnDodaj=new JButton(new ImageIcon("images/dodaj.png"));
		btnDodaj.addActionListener(this);
		btnDodaj.setPreferredSize(new Dimension(208,27));
		
		panelCentralny.add(lblNazwaPizzy, cc.xyw(2, 2, 7, cc.CENTER, cc.TOP));
		panelCentralny.add(lblRozmiarPizzy, cc.xywh(2, 4, 3, 1, cc.FILL, cc.FILL));
		panelCentralny.add(customCombobox, cc.xyw(6, 4, 2, cc.LEFT, cc.FILL));
		panelCentralny.add(lblLiczbaPizz, cc.xywh(3, 6, 2, 1, cc.LEFT, cc.CENTER));
		panelCentralny.add(lblWprowadzLiczbePizz, cc.xyw(6, 6, 1, cc.FILL, cc.FILL));
		panelCentralny.add(lblSos, cc.xywh(4, 8, 2, 1, cc.LEFT, cc.CENTER));
		panelCentralny.add(customCombobox2, cc.xyw(6, 8, 3, cc.FILL, cc.FILL));
		panelCentralny.add(btnDodaj, cc.xyw(2, 10, 7, cc.CENTER, cc.CENTER));
		
		return panelCentralny;
	}

	/**
	 * Obsługa błędu w ekranie cennik	
	 */
	private void utworzOknoBledu(){
		pattern = Pattern.compile(".*[^0-9].*");
		matcherNumerPizzy = pattern.matcher(txtWprowadzLiczbePizz.getText());
        dialogBlad.setAlwaysOnTop(true);
		if (matcherNumerPizzy.matches()){
	        dialogBlad.setVisible(true);		
	        txtWprowadzLiczbePizz.setText("");    
        }
		else {
			buffor.setNazwaPizzy(buffor.dane[buffor.getNumerRzedu()][0]);
			buffor.setRozmiarPizzy(listaRozmiarow[customCombobox.getSelectedIndex()]);
			buffor.setLiczbaPizz(Integer.parseInt(txtWprowadzLiczbePizz.getText()));
			buffor.setSos(listaSosow[customCombobox2.getSelectedIndex()]);
			
			buffor.setKosztElementu((Float.parseFloat(buffor.dane[buffor.getNumerRzedu()][customCombobox.getSelectedIndex()+1]))*buffor.getLiczbaPizz());
			buffor.setCena(Float.parseFloat(buffor.dane[buffor.getNumerRzedu()][customCombobox.getSelectedIndex()+1]));
			
			buffor.setKosztLaczny(buffor.getKosztLaczny()+buffor.getKosztElementu());
			
			buffor.setDodaj(1);
			
			//System.out.println(buffor.getNazwaPizzy());
			//System.out.println(buffor.getRozmiarPizzy());
			//System.out.println(buffor.getLiczbaPizz());
			//System.out.println(buffor.getSos());
			System.out.println(buffor.getKosztElementu());
			//System.out.println(buffor.getKosztLaczny());
			
			this.dispose();	
		}
	}
	
//*************************************************************************************************************************************

	
	public static void main(String[] args) {
		new Dialog();
	}
	
	
//*************************************************************************************************************************************

	//Obsługa zdarzeń
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnDodaj){
			utworzOknoBledu();
		}
		
	}

}
