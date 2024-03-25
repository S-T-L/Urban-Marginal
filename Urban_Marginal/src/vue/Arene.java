package vue;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controle;
import controleur.Global;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * frame de l'ar�ne du jeu
 * @author emds
 *
 */
public class Arene extends JFrame implements Global {

	/**
	 * Panel g�n�ral
	 */
	private JPanel contentPane;
	/**
	 * Panel contenant les murs
	 */
	private JPanel jpnMurs;
	/**
	 * Panel contenant les joueurs et les boules
	 */
	private JPanel jpnJeu;
	/**
	 * Zone de saisie du t'chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du t'chat
	 */
	private JTextArea txtChat ;
	
	private Controle controle;
	
	private Boolean client;
	/**
	 * @return the jpnMurs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}

	/**
	 * @param jpnMurs the jpnMurs to set
	 */
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}

	/**
	 * @return the jpnJeu
	 */
	public JPanel getJpnJeu() {
		return jpnJeu;
	}

	/**
	 * @param jpnJeu the jpnJeu to set
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}
	
	/**
	 * 
	 * @return txtchat
	 */
	public String getTxtChat() {
		return txtChat.getText();
	}
	
	/**
	 * 
	 * @param txtChat 
	 */
	public void setTextChat(String txtChat) {
		this.txtChat.setText(txtChat);
		//Pour que l'ascenseur reste en bas
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
		
		
	}

	/**
	 * Ajoute un mur dans le panel des murs
	 * @param unMur le mur � ajouter
	 */
	public void ajoutMurs(Object unMur) {
		jpnMurs.add((JLabel)unMur);
		jpnMurs.repaint();
	}
	
	/**
	 * 
	 * @param phrase ajoute la phrase recue la la zone de tchat sans supprimer son contenu
	 */
	public void ajoutTchat(String phrase) {
		this.txtChat.setText(this.txtChat.getText()+phrase+"\r\n");
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	
		
	}
	
	/**
	 * Ajout d'un joueur, son message ou sa boule, dans le panel de jeu
	 * @param unJLabel le label ajouter
	 */
	public void ajoutJLabelJeu(JLabel unJLabel) {
		this.jpnJeu.add(unJLabel);
		this.jpnJeu.repaint();
	}
	
	public void  txtSaisie_KeyPressed(KeyEvent e) {
		//tester si le code de la touche appuyée est égal au code de la touche entrée
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			if(!this.txtSaisie.getText().equals("")) {
				this.controle.evenementArene(this.txtSaisie.getText());
				this.txtSaisie.setText("");
				
			}
			
		}
	}
	/**
	 * Create the frame.
	 */
	public Arene(Controle controle, String typeJeu) {
		this.client = typeJeu.equals(CLIENT);
		
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(LARGEURARENE, HAUTEURARENE + 25 + 140));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);		
		contentPane.add(jpnJeu);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);		
		contentPane.add(jpnMurs);
		//n'affiche la zone de saisie de texte seulement si c'est un client
		if(this.client) {
		txtSaisie = new JTextField();
		txtSaisie.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtSaisie_KeyPressed(e);
			}
		});
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		}
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);
		
		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(FONDARENE);
		lblFond.setIcon(new ImageIcon(resource));		
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
		this.controle = controle;
		
	}

}

