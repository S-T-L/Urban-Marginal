package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 * Dictionnaire de joueurs index� sur leur objet de connexion
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 * @param controle instance du contr�leur pour les �changes
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] infos = ((String)info).split(STRINGSEPARE);
		String ordre = infos[0];
		switch(ordre) {
		case PSEUDO :
			// arriv�e des informations d'un nouveau joueur
			controle.evenementJeuServeur(AJOUTPANELMURS, connection);
			String pseudo = infos[1];
			int numPerso = Integer.parseInt(infos[2]);
			this.lesJoueurs.get(connection).initPerso(pseudo, numPerso, this.lesJoueurs.values(), this.lesMurs);
			//avoir l'info qu'un joueur se connecte dans le chat
			String premierMessage = "***"+pseudo+" vient de se connecter ***";
			this.controle.evenementJeuServeur(AJOUTPHRASE, premierMessage);
			break;
		case TCHAT :
			String phrase = infos[1];
			//recuperer le pseudo du joueur dont la connexion à été recue en parametre 
			phrase = this.lesJoueurs.get(connection).getPseudo()+" > "+phrase;
			//demande au controleur d'ajouter la phrase dans l'arene en lui envoyant l'odre ajout phrase
			this.controle.evenementJeuServeur(AJOUTPHRASE, phrase);
			break;
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois � l'envoi de la classe Jeu
	 */
	public void envoi(Object info) {
		for(Connection connection : this.lesJoueurs.keySet()) {
			//envoi l'information a chaque joueur a l'aide de la méthode envoi de la classe mere
			super.envoi(connection, info);
		}
	}
	
	/**
	 * 
	 */
	public void envoiJeuATous() {
		for(Connection connection : this.lesJoueurs.keySet()) {
			this.controle.evenementJeuServeur(MODIFPANELJEU, connection);
		}
	}

	/**
	 * G�n�ration des murs
	 */
	public void constructionMurs() {
		for(int k=0 ; k < NBMURS ; k++) {
			this.lesMurs.add(new Mur());
			this.controle.evenementJeuServeur(AJOUTMUR, lesMurs.get(lesMurs.size()-1).getjLabel());
		}
	}
	/**
	 * 
	 * @param jLabel
	 */
	public void ajoutLabelJeuArene(JLabel jLabel) {
		this.controle.evenementJeuServeur(AJOUTJLABELJEU, jLabel);
	}
}
