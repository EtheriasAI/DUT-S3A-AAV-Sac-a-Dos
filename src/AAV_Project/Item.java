package AAV_Project;

/*
 * Item représente les élèments que l'on
 * a dans le fichier txt et qu'on voudrait
 * entrer dans le sac à dos
 */

public class Item {

	private String nomItem;	//le nom de l'item
	private float poids;	//le poids de l'item
	private float prix;		//le prix de l'item
	
	Item(String n, float po, float pr){
		this.nomItem=n;
		this.poids=po;
		this.prix=pr;
	}
	
	public String getNomItem(){
		return this.nomItem;
	}
	
	public float getPoids() {
		return this.poids;
	}

	public float getPrix() {
		return this.prix;
	}
	
}