package AAV_Project;

/**
 * AppcoheeGloutonne est la classe qui permet la
 * méthode approchee gloutonne des éléments du
 * sac à dos
 */

import java.util.ArrayList;

public class ApprocheeGloutonne implements IAlgo{
	/*tableau qui contiendra les valeurs*/
	private Valeur[] tab;
	/*poids maximal du sac à dos*/
	private float limite;
	
	/*
	 * classe qui va permettre de prendre en compte
	 * la valeur d'un item
	 */
	private  class Valeur{
		private Item item;
		private float val;
		/*
		 * @param i : Item
		 * @param v : float valeur de l'item
		 */
		private Valeur(Item i, float v) {
			this.item = i;
			this.val = v;
		}
	}
	
	/*
	 * initialise le tableau des valeurs
	 * @param list : ArrayList des objets du fichier txt
	 * @param p : float poids maximal du sac à dos
	 */
	public ApprocheeGloutonne(ArrayList<Item> list, float p) {
		this.limite = p;
		this.tab= new Valeur[list.size()];
		int n=0;
		for(Item i : list){
			float a =0;
			a=(i.getPrix()/i.getPoids());
			tab[n]= new Valeur(i,a);
			n++;
		}
		
	}
	
	/*
	 * trie les valeurs du tableau en fonction de val
	 * de manièredécroissante
	 */
	@Override
	public void resoudre() {
		for(int x= 0; x<=tab.length-1;x++) {
			int min=x;
			for(int y=x+1;y<tab.length;y++)
				if(tab[y].val > tab[min].val)
					min = y;
			Valeur pivot = null;
			pivot = new Valeur(tab[x].item,tab[x].val);
			tab[x] = tab[min];
			tab[min] = pivot;
		}
	}
	
	/*
	 * affiche les items qui peuvent entrer dans le
	 * sac à dos selon cette méthode
	 */
	@Override
	public String toString() {
		float n = 0;
		int i =0;
		StringBuilder sb = new StringBuilder();
		sb.append("Voici les produits qui peuvent entré dans le sac à dos: \n");
		while(n<this.limite) {
			i+=1;
			if(i>= tab.length)
				break;
			if(n+tab[i].item.getPoids()<=this.limite) {
				n += tab[i].item.getPoids();
				sb.append(tab[i].item.getNomItem());
				sb.append(", ");
			}
		}
		sb.deleteCharAt(sb.length() - 2);
		String singleString = sb.toString();
		return singleString;
	}
	
}