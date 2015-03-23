import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/** 
* Program do wspomagania zarządzaniem pizzerią
* Klasa GUI definiująca okno aplikacji	 	
* @version 1.0	23/03/2015
*/
public class GUI extends JFrame implements ActionListener{
	final JPanel card = new JPanel();
	
	/**
	 * Zmienne definiujące przyciski w kartach
	 */
	private JButton btnZamowzMenu, btnZamowWlasna, btnAnulujZamowienie, btnAnulujZamowienie2, 
	btnAnulujZamowienie3, btnAnulujZamowienie4, btnDostawa, btnDostawa2, btnDodajDoZamowienia, btnPotwierdzenie, btnDrukuj;
	
	/**
	 * Konstruktor bezparametrowy klasy GUI
	 */
	public GUI(){
	this.getContentPane().add(card, BorderLayout.CENTER);
        this.setSize(650, 400);
        this.setVisible(true);
	utworzKarty();
	}
	
	/**
	 * Metoda tworząca karty dla menadżer rozkładu
	 */
	private void utworzKarty(){
	card.setLayout(new CardLayout());
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
        
        card.add(card1, "card1");
        card.add(card2, "card2");
        card.add(card3, "card3");
        card.add(card4, "card4");
        card.add(card5, "card5");
	}
	
	/**
	 * Metoda uruchomieniowa
	 * @param args
	 */
	public static void main(String[] args) {
		new GUI();
	}
	
	/**
	 * Obsługa zdarzeń 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnZamowzMenu) {
			CardLayout c1 = (CardLayout)(card.getLayout());
            c1.show(card,"card2");
		}
		else if(arg0.getSource() == btnZamowWlasna) {
			CardLayout c1 = (CardLayout)(card.getLayout());
            c1.show(card,"card5");
		}
		else if(arg0.getSource() == btnAnulujZamowienie || arg0.getSource() == btnDodajDoZamowienia || arg0.getSource() == btnDrukuj || arg0.getSource() == btnAnulujZamowienie2 || arg0.getSource() == btnAnulujZamowienie3 || arg0.getSource() == btnAnulujZamowienie4) {
			CardLayout c1 = (CardLayout)(card.getLayout());
            c1.show(card,"card1");
		}
		else if(arg0.getSource() == btnDostawa || arg0.getSource() == btnDostawa2) {
			CardLayout c1 = (CardLayout)(card.getLayout());
            c1.show(card,"card3");
		}
		else if(arg0.getSource() == btnPotwierdzenie) {
			CardLayout c1 = (CardLayout)(card.getLayout());
            c1.show(card,"card4");
		}	
	}
}
