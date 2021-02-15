package AAV_Project;

/*
 * Classe contenant le sac à dos
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SacADos {
	
	private String file;	//fichier txt
	private float poids;	//poids maximal du sac à dos
	//liste des items qui devraient etre dans le sac a dos
	private ArrayList<Item>listItems;
	
	public SacADos(){
		listItems=new ArrayList<Item>();
	}
	
	/*
	 * initialise le sac à dos
	 * @param f : String contenant l'adresse du fichier
	 * @param p : float poids maximal du sac à dos
	 */
	public SacADos(String f,float p) throws IOException{
		this();
		this.file=f;
		this.poids=p;
		Path filePath = Paths.get(this.file);
		Scanner sc = new Scanner(filePath);
		Scanner sca = new Scanner(filePath);
		sc.useDelimiter(";|\\n");
		
		while (sc.hasNext()) {
			
			String name = sc.next();
			float po=Float.parseFloat(sc.next()); 
			float pr=Float.parseFloat(sc.next()); 
			
			listItems.add(new Item(name,po,pr));
	    }
		sc.close();
		sca.close();
	}
	
	/*
	 * résoud le problème selon la méthode choisis
	 * @param methode_nom : String nom de méthodes
	 */
	public void resoudre(String methode_nom){
		IAlgo a = null;
		if(methode_nom.contentEquals("ApprocheeGloutonne"))
			a = new ApprocheeGloutonne(listItems,poids);
		if(methode_nom.contentEquals("ProgrammationDynamique"))
			a = new ProgrammationDynamique(listItems,poids);
		if(methode_nom.contentEquals("PSE"))
			a = new PSE(listItems,poids);
		a.resoudre();
		System.out.println(a.toString());
	}
	
}



