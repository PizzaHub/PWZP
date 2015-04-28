package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


/**
 * Klasa Blad 
 * Odpowiedzialna za budowę okna z komunikatem o błędzie.
 *
 */
class Blad extends JDialog implements ActionListener {
	
	private JPanel panelCentralny;
	private JButton btnOK;
	private JLabel lblNaglowek, lblStopka, lblLewo, lblPrawo, lblTresc;
	
	/**
	 * Bezparametrowy konstruktor klasy Blad
	 */
	public Blad(){
		this.setSize(278,125);
		this.getContentPane().add(utworzPanelCentralny(), BorderLayout.CENTER);
		setModal(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Pizza");
		this.setUndecorated(true);
		
		this.setVisible(true);		
	}
	
	/**
	 * Metoda tworząca panel centralny
	 * @return
	 */
	private JPanel utworzPanelCentralny(){
		FormLayout layout = new FormLayout(                                                              
				"8px, 262px, 8px", 
				"31px, 39px, 12px, 22px, 13px, 8px");	
		CellConstraints cc = new CellConstraints();
		
		panelCentralny=new JPanel(layout);
		//panelCentralny=new FormDebugPanel(layout);
		panelCentralny.setBackground(new Color(0xeeeeee));
		
		lblNaglowek=new JLabel(new ImageIcon("images/naglowek_blad.png"));
		
		lblTresc=new JLabel(new ImageIcon("images/tresc_blad.png"));
		
		lblLewo=new JLabel(new ImageIcon("images/blad_ramka_lewa.png"));
		
		lblPrawo=new JLabel(new ImageIcon("images/blad_ramka_prawa.png"));
		
		lblStopka=new JLabel(new ImageIcon("images/blad_ramka_dolna.png"));
		
		btnOK=new JButton("OK");
		btnOK.addActionListener(this);
		
		panelCentralny.add(lblNaglowek, cc.xyw(1, 1, 3, cc.CENTER, cc.FILL));
		panelCentralny.add(lblTresc, cc.xyw(1, 2, 3, cc.CENTER, cc.FILL));
		panelCentralny.add(lblLewo, cc.xywh(1, 3, 1, 3, cc.FILL, cc.FILL));
		panelCentralny.add(lblPrawo, cc.xywh(3, 3, 1, 3, cc.FILL, cc.FILL));
		panelCentralny.add(lblStopka, cc.xywh(1, 6, 3, 1, cc.FILL, cc.FILL));
		panelCentralny.add(btnOK, cc.xy(2, 4, cc.CENTER, cc.FILL));
		
		return panelCentralny;
	}
	
	public static void main(String[] args) {
		new Blad();
	}
	
	/**
	 * Obsługa zdarzeń
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnOK){
			this.dispose();
		}
		
	}

}
