package ressourcesCartes;
/**
 * 
 * Les données écrites sur les cartes batiments (nom, pierre, bois, savoir, tuile, ecus, points de victoires ), elle hérite de Ressources qui possède tous ces paramètres sauf points victoires
 *  @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *  
 */
public class RessourceBatiment extends Ressources{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8582956704398708840L;
	private int ptsVictoire;
	
	/**
	 * Permet d'initialiser toutes les ressources liée au batiment
	 * @param n : nom, pierre, bois, savoir, tuile, écus récompenses, points victoires
	 * @param p : pierre
	 * @param b : bois
	 * @param s : savoir
	 * @param t : tuile
	 * @param e : écus
	 * @param pv : points victoires
	 */
	public RessourceBatiment (String n, int p, int b, int s, int t,int pv,int e)
	{
		super(n,p,b,s,t, e);
		ptsVictoire=pv;
	}
	/**
	 * Compare les ressources de deux cartes
	 * @param obj : une instance de StatBatiment
	 * @return true : si 2 stats sont égales 
	 */
	public boolean equals(Object obj) {
       RessourceBatiment sb=(RessourceBatiment)obj;
       if(sb.getPierre()==this.getPierre() && sb.getBois()==this.getBois() && sb.getSavoir()==this.getSavoir() && sb.getTuile()==this.getTuile() && sb.getEcusRecompense()==this.getEcusRecompense() && sb.ptsVictoire==this.ptsVictoire) {
    	   return true;
       }
   	return false;  
	}
	public int getEcusRecompense() {
		return super.getEcus();
	}
	public int getPtsVictoire() {
		return ptsVictoire;
	}
	
	
	
}
