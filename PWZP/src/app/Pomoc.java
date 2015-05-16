package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


/**
 * Klasa Pomoc
 * Odpowiedzialna za budowę okna z pomocą.
 */
public class Pomoc extends JDialog  implements ActionListener {
	
	private JEditorPane editorPane;
	private JPanel panelCentralny;
	private JLabel lblPrawo, lblLewo,  lblStopka, lblNaglowek;
	private JButton btnZamknij;
	
	public Pomoc(){
		 this.setTitle("Pomoc");
         this.setSize(775, 615);
         this.setLocationRelativeTo(null);
         this.setResizable(false);
         this.getContentPane().add(utworzPanelCentralny(), BorderLayout.CENTER);
         this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     	 this.setAlwaysOnTop(true);
     	 this.setModal(true);
     	 this.setUndecorated(true);
		 pack();
	}
	
	/**
	 * Prywatna metoda tworząca panel centralny
	 */
	private JPanel utworzPanelCentralny(){
		FormLayout layout = new FormLayout(                                                              
				"8px, 729px, 31px, 8px", 
				"31px,608, 8px");	
		CellConstraints cc = new CellConstraints();
		panelCentralny=new JPanel(layout);
		
		//tworzę obiekt editorPane oraz ścieżkę do pliku html
		try {
			editorPane = new JEditorPane();
			editorPane = new JEditorPane("file:pomoc/index.html");
			editorPane.setEditable(false);
			editorPane.setContentType("text/html");
		} catch (IOException e) {
            e.printStackTrace();
		}
        editorPane.addHyperlinkListener(new HyperlinkListener() {
    			public void hyperlinkUpdate(HyperlinkEvent event) {
    				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
    					try {
    						editorPane.setPage(event.getURL());
    					}
    					catch(IOException e) {
    						editorPane.setText("Exception: "+e);
    					}
    				}
    			}});
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
		btnZamknij.setBorder(null);
		
        lblNaglowek = new JLabel(new ImageIcon("images/pomoc_naglowek.png"));
        lblNaglowek.setBorder(null);
		lblStopka = new JLabel(new ImageIcon("images/pomoc_stopka.png"));
		lblStopka.setBorder(null);
		lblLewo = new JLabel(new ImageIcon("images/pomoc_ramka_lewa.png"));
		lblLewo.setBorder(null);
		lblPrawo = new JLabel(new ImageIcon("images/pomoc_ramka_prawa.png"));
		lblPrawo.setBorder(null);
		
		panelCentralny.add(btnZamknij, cc.xy(3, 1,  cc.FILL, cc.FILL ));
        panelCentralny.add(new JScrollPane(editorPane), cc.xyw(2,2, 2));
		panelCentralny.add(lblNaglowek, cc.xyw(1,1,4, cc.FILL, cc.FILL));
		panelCentralny.add(lblLewo, cc.xy(1, 2, cc.FILL, cc.FILL));
		panelCentralny.add(lblPrawo, cc.xy(4, 2, cc.FILL, cc.FILL));
		panelCentralny.add(lblStopka, cc.xyw(1,3, 4, cc.FILL, cc.FILL ));
        return panelCentralny;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnZamknij){
			this.dispose();	
		}
		
	}
	

}