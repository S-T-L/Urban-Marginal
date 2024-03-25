package modele;

import java.util.Collection;

import javax.swing.JLabel;

/**
 * Informations communes � tous les objets (joueurs, murs, boules)
 * permet de m�moriser la position de l'objet et de g�rer les  collisions
 *
 */
public abstract class Objet {

	/**
	 * position X de l'objet
	 */
	protected Integer posX ;
	/**
	 * position Y de l'objet
	 */
	protected Integer posY ;
	/**
	 * label contenant l'objet graphique (personnage, mur, boule)
	 */
	protected JLabel jLabel;
	/**
	 * 
	 * @return posX
	 */
	public Integer getPosX() {
		return posX;
	}
	/**
	 * 
	 * @return posY
	 */
	public Integer getPosY() {
		return posY;
	}
	
	/**
	 * @return the jLabel
	 */
	public JLabel getjLabel() {
		return jLabel;
	}

	/**
	 * contr�le si l'objet actuel touche l'objet pass� en param�tre
	 * @param objet contient l'objet � contr�ler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		if (objet.jLabel==null || objet.jLabel==null) {
			return false ;
		}else{
			return(this.posX+this.jLabel.getWidth()>objet.posX &&this.posX<objet.posX+objet.jLabel.getWidth() && 
				this.posY+this.jLabel.getHeight()>objet.posY &&
				this.posY<objet.posY+objet.jLabel.getHeight()) ;
		
		}
	}
	/**
	 * V�rifie si l'objet actuel touche un des objets de la collection
	 * @param lesObjets collection d'objets (murs, joueurs ou boules)
	 * @return l'objet touch� ou null
	 */
	public Objet toucheCollectionObjets (Collection<Objet> lesObjets) {
		for (Objet unObjet : lesObjets) {
			if (!unObjet.equals(this)) {
				if (toucheObjet(unObjet)) {
					return unObjet ;
				}
			}
		}
		return null ;
	}
	
}
