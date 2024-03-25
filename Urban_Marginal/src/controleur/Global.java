/**
 * 
 */
package controleur;

/**
 * Global contient les constantes du programme
 * @author emds
 *
 */
public interface Global {
	
	/**
	 * N� du port d'�coute du serveur
	 */
	int PORT = 6666;
	/**
	 * Nombre de personnages diff�rents
	 */
	int NBPERSOS = 3;
	/**
	 * Caract�re de s�paration dans un chemin de fichiers
	 */
	String CHEMINSEPARATOR = "/";
	/**
	 * Chemin du dossier des images de fonds
	 */
	String CHEMINFONDS = "fonds"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image de la boule
	 */
	String CHEMINBOULES = "boules"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image du mur
	 */
	String CHEMINMURS = "murs"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des images des personnages
	 */
	String CHEMINPERSONNAGES = "personnages"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des sons
	 */
	String CHEMINSONS = "sons"+CHEMINSEPARATOR;
	/**
	 * Chemin de l'image de fond de la vue ChoixJoueur
	 */
	String FONDCHOIX = CHEMINFONDS+"fondchoix.jpg";
	/**
	 * Chemin de l'image de fond de la vue Arene
	 */
	String FONDARENE = CHEMINFONDS+"fondarene.jpg";
	/**
	 * Extension des fichiers des images des personnages
	 */
	String EXTFICHIERPERSO = ".gif";
	/**
	 * D�but du nom des images des personnages
	 */
	String PERSO = "perso";
	/**
	 * Chemin de l'image de la boule
	 */
	String BOULE = CHEMINBOULES+"boule.gif";
	/**
	 * Chemin de l'image du mur
	 */
	String MUR = CHEMINMURS+"mur.gif";
	/**
	 * �tat marche du personnage
	 */
	String MARCHE = "marche";
	/**
	 * �tat touch� du personnage
	 */
	String TOUCHE = "touche";
	/**
	 * �tat mort du personnage
	 */
	String MORT = "mort";
	/**
	 * Caract�re de s�paration dans les chaines transf�r�es
	 */
	String STRINGSEPARE = "~";
	/**
	 * Message "connexion" envoy� par la classe Connection
	 */
	String CONNEXION = "connexion";
	/**
	 * Message "r�ception" envoy� par la classe Connection
	 */
	String RECEPTION = "r�ception";
	/**
	 * Message "d�connexion" envoy� par la classe Connection
	 */
	String DECONNEXION = "d�connexion";
	/**
	 * Message "pseudo" envoy� pour la cr�ation d'un joueur
	 */
	String PSEUDO = "pseudo";
	/**
	 * vie de d�part pour tous les joueurs
	 */
	int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	int PERTE = 2 ; 
	/**
	 * nombre de murs dans l'ar�ne
	 */
	int NBMURS = 20;
	/**
	 * hauteur de la zone de jeu de l'ar�ne
	 */
	int HAUTEURARENE = 600;
	/**
	 * largeur de la zone de heu de l'ar�ne
	 */
	int LARGEURARENE = 800;
	/**
	 * hauteur d'un mur
	 */
	int HAUTEURMUR = 35;
	/**
	 * largeur d'un mur
	 */
	int LARGEURMUR = 34;
	/**
	 * hauteur du personnage
	 */
	int HAUTEURPERSO = 44;
	/**
	 * largeur du personnage
	 */
	int LARGEURPERSO = 39;
	/**
	 * hauteur du message
	 */
	int HAUTEURMESSAGE = 8;
	/**
	 * largeur de la boule
	 */
	int LARGEURBOULE = 17;
	/**
	 * hauteur de la boule
	 */
	int HAUTEURBOULE = 17;
	/**
	 * orientation du personnage vers la gauche
	 */
	int GAUCHE = 0;
	/**
	 * orientation du personnage vers la droite
	 */
	int DROITE = 1;
	/**
	 * taille du pas quand le personnage avance (nombre de pixels)
	 */
	int PAS = 10;
	/**
	 * nombre d'�tapes (d'images) pour donner l'impresson de marche
	 */
	int NBETAPESMARCHE = 4;
	/**
	 * nombre d'�tapes (d'images) pour donner l'impression d'�tre touch�
	 */
	int NBETAPESTOUCHE = 2;
	/**
	 * nombre d'�tapes (d'images) pour donner l'impresson de mourir
	 */
	int NBETAPESMORT = 2;
	/**
	 * Message "serveur" pour la cr�ation d'un serveur
	 */
	String SERVEUR = "serveur";
	/**
	 * Message "client" pour la cr�ation d'un client
	 */
	String CLIENT = "client";
	/**
	 * ordre pour ajouter un mur dans l'ar�ne du serveur
	 */
	String AJOUTMUR = "ajout mur";
	/**
	 * ordre pour ajouter le panel des murs dans l'ar�ne du client
	 */
	String AJOUTPANELMURS = "ajout panel murs";
	/**
	 * ordre pour ajouter un jLabel dans l'ar�ne du serveur (joueur, message, boule)
	 */
	String AJOUTJLABELJEU = "ajout jLabel jeu";
	/**
	 * ordre pour modifier le panel du jeu dans l'ae�ne du client
	 */
	String MODIFPANELJEU = "modif panel jeu";
	/**
	 * ordre pour demander d'ajouter une phrase au tchat
	 */
	String TCHAT = "tchat";
	/**
	 * ordre pour ajouter une phrase dans l'ar�ne du serveur
	 */
	String AJOUTPHRASE = "ajout phrase";
	/**
	 * ordre pour modifier le contenu du tchat dans l'ar�ne du client
	 */
	String MODIFTCHAT = "modif tchat";
	/**
	 * ordre pour ex�cuter une action (d�placement, tire de boule)
	 */
	String ACTION = "action";
	

	
	/**
	 * Chemin du son welcome (entr�e dans la vue ChoixJoueur)
	 */
	String SONWELCOME = CHEMINSONS+"welcome.wav";
	/**
	 * Chemin du son precedent (clic sur le bouton pr�c�dent de la vue ChoixJoueur)
	 */
	String SONPRECEDENT = CHEMINSONS+"precedent.wav";
	/**
	 * Chemin du son suivant (clic sur le bouton suivant de la vue ChoixJoueur)
	 */
	String SONSUIVANT = CHEMINSONS+"suivant.wav";
	/**
	 * Chemin du son go (clic sur le bouton GO de la vue ChoixJoueur)
	 */
	String SONGO = CHEMINSONS+"go.wav";
	/**
	 * Chemin du son flight (tir de la boule)
	 */
	String SONFIGHT = CHEMINSONS+"fight.wav";
	/**
	 * Chemin du son hurt (joueur bless�)
	 */
	String SONHURT = CHEMINSONS+"hurt.wav";
	/**
	 * Chemin du son death (joueur tu�)
	 */
	String SONDEATH = CHEMINSONS+"death.wav";
	/**
	 * tableau des sons de la vue Arene
	 */

	String[] SON =  {CHEMINSONS + "fight.wav", CHEMINSONS + "hurt.wav", CHEMINSONS + "death.wav"} ;
	/**
	 * num�ro correspondant au son FIGHT
	 */
	int FIGHT = 0;
	/**
	 * num�ro correspondant au son HURT
	 */
	int HURT = 1;
	/**
	 * num�ro correspondant au son DEATH
	 */
	int DEATH = 2;
	/**
	 * jouer le son
	 */
	String JOUESON = "joue son";
}


