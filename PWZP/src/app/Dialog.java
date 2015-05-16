package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

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
	 * Utworzenie obiektów innych klas
	 */
	private Buffor buffor=new Buffor();
	private Zamowienie zamowienie=new Zamowienie();
	
	/**
	 * Deklaracja komponentów 
	 */
	private JPanel panelCentralny;
	
	private JLabel lblNaglowek, lblStopka, lblLewo, lblPrawo, lblNazwaPizzy, lblRozmiarPizzy, lblLiczbaPizz, lblWprowadzLiczbePizz, lblSos, 
		lblKoszt, lblWyswietlKoszt;
	private JButton btnDodaj, btnZamknij;
	private JSpinner spinLiczbaPizz;
	private ComboBox customCombobox, customCombobox2;
	
	private String[] listaRozmiarow = {"30cm", "40cm", "50cm"};
	private String[] listaSosow = {"Brak", "Czosnkowy", "Ostry"};
	
	/**
	 * Deklaracja zmiennych dla okna błędu wprowadzania znaków 
	 */
	Pattern pattern;
    Matcher matcherNumerPizzy;
    private Blad blad1;
	
//*************************************************************************************************************************************
	
	/**
	 * Konstruktor bezparametrowy klasy Dialog
	 */
	public Dialog(){
		this.setUndecorated(true);
		this.setSize(322,235);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
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
		Buffor.setDodaj(0);
		
		FormLayout layout = new FormLayout(                                                              
				"9px, 30px, 21px, 53px, 32px, 14px, 36px, 38px, 51px, 30px, 1px, 7px", 
				"31px, 10px, 23px, 20px, 25px, 5px, 25px, 5px, 25px, 20px, 27px, 10px, 8px");	
		CellConstraints cc = new CellConstraints();
		
		panelCentralny=new JPanel(layout);
		//panelCentralny=new FormDebugPanel(layout);
		panelCentralny.setBackground(new Color(0xf2f2f3));
		
		btnZamknij=new JButton(new ImageIcon("images/dialog_zamknij_normal.png"));
		btnZamknij.addActionListener(this);
		btnZamknij.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt){
            	btnZamknij.setIcon(new ImageIcon("images/dialog_zamknij_enter.png"));
            }
            public void mouseExited(MouseEvent evt){
            	btnZamknij.setIcon(new ImageIcon("images/dialog_zamknij_normal.png"));
            }
            public void mousePressed(MouseEvent evt){
            	btnZamknij.setIcon(new ImageIcon("images/dialog_zamknij_pressed.png"));
            }
            public void mouseReleased(MouseEvent evt){
            	btnZamknij.setIcon(new ImageIcon("images/dialog_zamknij_normal.png"));
            }
        });
		btnZamknij.setOpaque(false);
		btnZamknij.setContentAreaFilled(false);
		btnZamknij.setBorderPainted(false);
		btnZamknij.setBorder(null);
		
		lblNaglowek=new JLabel(new ImageIcon("images/dialog_naglowek.png"));
		lblStopka=new JLabel(new ImageIcon("images/dialog_stopka.png"));
		lblLewo=new JLabel(new ImageIcon("images/dialog_ramka_lewa.png"));
		lblPrawo=new JLabel(new ImageIcon("images/dialog_ramka_prawa.png"));
			
		//Nazwa pizzy
		lblNazwaPizzy=new JLabel(buffor.dane[Buffor.getNumerRzedu()][0]);
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
		
		//Wprowadzanie liczby pizz
		spinLiczbaPizz=new JSpinner();
		spinLiczbaPizz.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		JComponent editor = spinLiczbaPizz.getEditor();
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)editor;
		spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
		spinnerEditor.getTextField().setFont(new Font("Arial", Font.PLAIN, 17));
		spinnerEditor.getTextField().setBorder(new EmptyBorder(0, 0, 1, 0));
		spinnerEditor.getTextField().setForeground(Color.black);
		spinnerEditor.getTextField().setBackground(new Color(0xeaeaeb));
		spinLiczbaPizz.setPreferredSize(new Dimension(45,25));
		
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
		btnDodaj.setPreferredSize(new Dimension(211,27));
		btnDodaj.setBorder(null);
		btnDodaj.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt){
            	btnDodaj.setIcon(new ImageIcon("images/dodaj_over.png"));
            }
            public void mouseExited(MouseEvent evt){
            	btnDodaj.setIcon(new ImageIcon("images/dodaj.png"));
            }
            public void mousePressed(MouseEvent evt){
            	btnDodaj.setIcon(new ImageIcon("images/dodaj_over.png"));
            }
            public void mouseReleased(MouseEvent evt){
            	btnDodaj.setIcon(new ImageIcon("images/dodaj.png"));
            }
        });
		
		panelCentralny.add(btnZamknij, cc.xywh(10, 1, 2, 1, cc.FILL, cc.FILL));
		panelCentralny.add(lblNaglowek, cc.xywh(1, 1, 12, 1, cc.FILL, cc.FILL));
		panelCentralny.add(lblStopka, cc.xywh(1, 13, 12, 1, cc.FILL, cc.FILL));
		panelCentralny.add(lblLewo, cc.xywh(1, 2, 1, 11, cc.FILL, cc.FILL));
		panelCentralny.add(lblPrawo, cc.xywh(11, 2, 2, 11, cc.FILL, cc.FILL));
		
		panelCentralny.add(lblNazwaPizzy, cc.xyw(2, 3, 9, cc.CENTER, cc.TOP));
		panelCentralny.add(lblRozmiarPizzy, cc.xywh(3, 5, 3, 1, cc.FILL, cc.FILL));
		panelCentralny.add(customCombobox, cc.xyw(7, 5, 2, cc.LEFT, cc.FILL));
		panelCentralny.add(lblLiczbaPizz, cc.xywh(4, 7, 2, 1, cc.LEFT, cc.CENTER));
		panelCentralny.add(spinLiczbaPizz, cc.xyw(7, 7, 2, cc.LEFT , cc.FILL));
		panelCentralny.add(lblSos, cc.xywh(5, 9, 2, 1, cc.LEFT, cc.CENTER));
		panelCentralny.add(customCombobox2, cc.xyw(7, 9, 3, cc.FILL, cc.FILL));
		panelCentralny.add(btnDodaj, cc.xyw(2, 11, 9, cc.CENTER, cc.CENTER));
		
		return panelCentralny;
	}

	
//*************************************************************************************************************************************

	
	public static void main(String[] args) {
		
		new Dialog();
	}
	
	
//*************************************************************************************************************************************

	//Obsługa zdarzeń
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnDodaj){
			//Jeśli rozmiar zamówienia mieści się w limicie:
			if(zamowienie.sprawdzRozmiarZamowienia((int) spinLiczbaPizz.getValue())==true){
				Buffor.setLiczbaPizz((int) spinLiczbaPizz.getValue());
				Buffor.setNazwaPizzy(buffor.dane[Buffor.getNumerRzedu()][0]);
				Buffor.setRozmiarPizzy(listaRozmiarow[customCombobox.getSelectedIndex()]);
				Buffor.setSos(listaSosow[customCombobox2.getSelectedIndex()]);
				Buffor.setKosztElementu((Float.parseFloat(buffor.dane[Buffor.getNumerRzedu()][customCombobox.getSelectedIndex()+1]))*Buffor.getLiczbaPizz());
				Buffor.setCena(Float.parseFloat(buffor.dane[Buffor.getNumerRzedu()][customCombobox.getSelectedIndex()+1]));
				Buffor.setKosztLaczny(Buffor.getKosztLaczny()+Buffor.getKosztElementu());
				Buffor.setKosztLacznyBezDostawy(Buffor.getKosztLaczny());
				Buffor.setDodaj(1);
				Buffor.setRozmiarZamowienia(Buffor.getRozmiarZamowienia()+Buffor.getLiczbaPizz());
				this.dispose();	
			}
			else if(zamowienie.sprawdzRozmiarZamowienia((int) spinLiczbaPizz.getValue())==false){
				int wynik=25-Buffor.getRozmiarZamowienia()-((int) spinLiczbaPizz.getValue());
				
				//Wyświetlenie komunikatu o błędzie
				BladPrzekroczeniaLimitu blad=new BladPrzekroczeniaLimitu();
				
				spinLiczbaPizz.setValue((int) spinLiczbaPizz.getValue()+wynik);
			}
		}
		else if(arg0.getSource()==btnZamknij){
			this.dispose();	
		}
		
	}

}
