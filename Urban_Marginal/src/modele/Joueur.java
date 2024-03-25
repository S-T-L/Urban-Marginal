package modele;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {

	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n� correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * message qui s'affiche sous le personnage (contenant pseudo et vie)
	 */
	private JLabel message;
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * num�ro d'�tape dans l'animation (de la marche, touch� ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * tourn� vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	
	/**
	 * Constructeur : r�cup�raton de jeuServeur et initialisaton de certaines propri�t�s
	 * @param jeuServeur instance de JeuServeur pour lui envoyer des informations
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = DROITE;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Initialisation d'un joueur (pseudo et num�ro, calcul de la 1�re position, affichage, cr�ation de la boule)
	 * @param pseudo pseudo du joueur
	 * @param numPerso num�ro du personnage
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @param lesMurs collection contenant les murs
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur>lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		System.out.println("joueur "+pseudo+" - num perso "+numPerso+" cr��");
		// cr�ation du label du personnage
		super.jLabel = new JLabel();
		// cr�ation du label du message sous le personnage
		this.message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		// calcul de la premi�re position du personnage
		this.premierePosition(lesJoueurs, lesMurs);
		// demande d'ajout du label du personnage et du message dans l'ar�ne du serveur
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(message);
		// demande d'affichage du personnage
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre joueur ou un mur)
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @param lesMurs collection contenant les murs
	 */
	private void premierePosition(Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO)) ;
			posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE)) ;
		}while(this.toucheJoueur(lesJoueurs) || this.toucheMur(lesMurs));
	}
	
	/**
	 * Affiche le personnage et son message
	 * @param etape Etape dans le mouvement du personnage
	 * @param etat etat du personnage : "marche", "touche", "mort"
	 */
	public void affiche(String etat, int etape) {
		// positionnement du personnage et affectation de la bonne image
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = CHEMINPERSONNAGES+PERSO+this.numPerso+etat+etape+"d"+this.orientation+EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		// positionnement et remplissage du message sous le perosnnage
		this.message.setBounds(posX-10, posY+HAUTEURPERSO, LARGEURPERSO+10, HAUTEURMESSAGE);
		this.message.setText(pseudo+" : "+vie);
		// demande d'envoi � tous des modifications d'affichage
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * G�re une action re�ue et qu'il faut afficher (d�placement, tire de boule...)
	 * @param action action a ex�cut�e (d�placement ou tir de boule)
	 * @param lesMurs collection de murs
	 * @param lesJoueurs collection de joueurs
	 */
	public void action(Integer action, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		switch(action){
		case KeyEvent.VK_LEFT :
			orientation = GAUCHE; 
			posX = deplace(posX, action, -PAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_RIGHT :
			orientation = DROITE; 
			posX = deplace(posX, action, PAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_UP :
			posY = deplace(posY, action, -PAS, HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE, lesJoueurs, lesMurs) ;
			break;
		case KeyEvent.VK_DOWN :
			posY = deplace(posY,  action, PAS, HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE, lesJoueurs, lesMurs) ;
			break;			
		}
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * G�re le d�placement du personnage 
	 * @param position position de d�part
	 * @param action gauche, droite, haut ou bas
	 * @param lepas valeur de d�placement (positif ou n�gatif)
	 * @param max valeur � ne pas d�passer
	 * @param lesJoueurs collection de joueurs pour �viter les collisions
	 * @param lesMurs collection de murs pour �viter les collisions
	 * @return nouvelle position
	 */
	private int deplace(int position, // position de d�part
			int action, // gauche, droite, haut, bas
			int lepas, // valeur du d�placement (positif ou n�gatif)
			int max, // valeur � ne pas d�passer
			Collection<Joueur> lesJoueurs, // les autres joueurs (pour �viter les collisions)
			ArrayList<Mur> lesMurs) { // les murs (pour �viter les collisions)
		int ancpos = position ;
		position += lepas ;
		position = Math.max(position, 0) ;
		position = Math.min(position,  max) ;
		if (action==KeyEvent.VK_LEFT || action==KeyEvent.VK_RIGHT) {
			posX = position ;
		}else{
			posY = position ;
		}
		// controle s'il y a collision, dans ce cas, le personnage reste sur place
		if (toucheJoueur(lesJoueurs) || toucheMur(lesMurs)) {
			position = ancpos ;
		}
		// passe � l'�tape suivante de l'animation de la marche
		etape = (etape % NBETAPESMARCHE) + 1 ;
		return position ;
	}

	/**
	 * Contr�le si le joueur touche un des autres joueurs
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @return true si le joueur touche un autre joueur
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
		for(Joueur unJoueur : lesJoueurs) {
			if(!this.equals(unJoueur)) {
				if(super.toucheObjet(unJoueur)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	* Contr�le si le joueur touche un des murs
	 * @param lesMurs collection contenant tous les murs
	 * @return true si le joueur touche un mur
	 */
	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
		for(Mur unMur : lesMurs) {
			if(super.toucheObjet(unMur)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie() {
	}
	
	/**
	 * Perte de points de vie apr�s avoir �t� touch� 
	 */
	public void perteVie() {
	}
	
	/**
	 * vrai si la vie est � 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}
	
	/**
	 * Le joueur se d�connecte et disparait
	 */
	public void departJoueur() {
	}
	
}
