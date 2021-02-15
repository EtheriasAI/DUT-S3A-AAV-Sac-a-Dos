package AAV_Project;

import java.util.ArrayList;

public class ProgrammationDynamique implements IAlgo{

	//tableau contenant tous les items du fichier txt
	private Item[] items;
	private int p;	//poids maximal du sac à dos
	//liste des items du résultat seulement
	private ArrayList<Item> solution;
	
	/*
	 * range les items dans le tableau items
	 */
	public ProgrammationDynamique(ArrayList<Item> listItem, float p) {
		items = new Item[listItem.size()];
		for(int i=0; i<=listItem.size()-1;i++) 
			items[i]=listItem.get(i);
		this.p = (int) p;
	}
	
	/*
	 * met les items qui peuvent entrés dans le sac à dos
	 * selon cette méthode dans la lsite solution
	 */
	@Override
	public void resoudre(){
		
		int NB_ITEMS = items.length;
		float[][] matrix = new float[NB_ITEMS+1][p+1];
		
		for (int i = 1; i <= p; i++) 
				matrix[0][i]=0;
		
		for(int i=1; i<= NB_ITEMS;i++) 
			for(int j=0; j<= p;j++) {
				if(items[i-1].getPoids()>j) 
					matrix[i][j]=matrix[i-1][j];
				else
					matrix[i][j]= (float) Math.max(matrix[i-1][j],matrix[i-1][ (int) (j-items[i-1].getPoids())] +items[i-1].getPrix());
			}
		
		for(int i=1; i<= NB_ITEMS;i++) {
			for(int j=0; j<= p;j++) 
				System.out.print(matrix[i][j]+" ,");System.out.println(" ");}
		
		double res = matrix[NB_ITEMS][p];
		int w = p;
		
		solution = new ArrayList<Item>();
		
		for(int i = NB_ITEMS;i>0 && res>0;i--)
			if(res != matrix[i-1][w]) {
				solution.add(items[i-1]);
				res -= items[i-1].getPrix();
				w -= items[i-1].getPoids();
			}
	}
	
	/*
	 * affiche les items qui peuvent entrer dans le
	 * sac à dos selon cette méthode
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Voici les produits qui peuvent entré dans le sac à dos: \n");
		for(Item x : this.solution) {
			sb.append(x.getNomItem());
			sb.append(", ");
		}
		sb.deleteCharAt(sb.length() - 2);
		String singleString = sb.toString();
		return singleString;
	}

}