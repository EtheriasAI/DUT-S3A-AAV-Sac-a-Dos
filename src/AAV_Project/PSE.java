package AAV_Project;

import java.util.ArrayList;

public class PSE implements IAlgo{
	
	//tableau contenant des listes pour crée les noeuds
	private ArrayList<Noeud> listNoeud[];
	//liste des items à mettre dans le sac
	private ArrayList<Item> listItem;
	private int listactual;		//index du tableau actuel
	private float poidsmax;		//poids maximal du sac
	private int resultat;		//solution
	
	public class Noeud{
		private Noeud p;		//Noeud precedent
		private Item i;			//item du Noeud
		private float poids;	//poids totales de la branche
		public Noeud (){ 
		      i = null;
		      p = null;
		      poids = 0;
			}

		   public Noeud ( Item i, Noeud p){
		      this.i = i;
		      this.p=p;
		      this.poids = (float) (this.p.poids + this.i.getPoids());
			}
	}

	/*
	 * on crée un tableau de liste pour l'abre
	 */
	public PSE(ArrayList<Item> listItem, float p) {
		this.listItem = listItem;
		this.poidsmax = p;
		this.listNoeud = new ArrayList[listItem.size()+1];
		for(int i = 0 ; i < listItem.size()+1 ; i++)
		    listNoeud[i] = new ArrayList<Noeud>();
		listactual=1;
		resultat = 0;
	}
	
	/*
	 * on crée l'arbre et on cherche la solution
	 */
	@Override
	public void resoudre() {
		Noeud root = new Noeud(new Item("null",0,0),new Noeud());
		listNoeud[0].add(root);
		while(listactual<=listItem.size()) {
			int i=0;
			int l = (int) (Math.pow(2, listactual)-1);
			while(i<=l) {
				listNoeud[listactual].add(f(i));
				i++;
				}
			listactual++;
			}
		/*on cherche la feuille "solution"*/
		resultat = listNoeud[listNoeud.length-1].size()-1;
		for(int i=listNoeud[listNoeud.length-1].size()-1; i >=0 ;i--) {
			if(listNoeud[listNoeud.length-1].get(i).poids<=poidsmax)
				resultat = i;
		}
	}
	
	/*
	 * méthode qui va créer les noeuds
	 */
	private Noeud f(int itemactual) {
		Noeud b = listNoeud[listactual-1].get(itemactual/2);
		Noeud a;
		/*un noeud sur deux doit etre vide*/
		if((itemactual % 2 )== 0)	
			a = new Noeud(listItem.get(listactual-1),b);
		else
			a = new Noeud(new Item("null",0,0),b);
		return a;
	}
	
	/*
	 * affiche les items qui peuvent entrer dans le
	 * sac à dos selon cette méthode
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Voici les produits qui peuvent entré dans le sac à dos: \n");
		int i = listNoeud.length;
		Noeud res = listNoeud[listNoeud.length-1].get(resultat);
		while(i!=1) {		//car root
			
			if(res.i.getNomItem()!="null") {
				sb.append(res.i.getNomItem());
				sb.append(", ");}
				
			res=res.p;
			i--;
		}
		sb.deleteCharAt(sb.length() - 2);
		String singleString = sb.toString();
		return singleString;
	}

}
