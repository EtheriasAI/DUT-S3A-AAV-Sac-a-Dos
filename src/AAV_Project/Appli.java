package AAV_Project;

/**
 * Appli est la classe contenant le main
 * 
 * @author DOGHRI FARAH
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Appli {

	/* main de l'application*/
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		/*l'adresse du fichier txt*/
		String file=sc.next();
		/*le poids maximal du sac à dos*/
		int poids = sc.nextInt();
		SacADos s = new SacADos(file,poids);
		/*le nom de la méthode choisis*/
		String methode_nom =sc.next();
		s.resoudre(methode_nom);
		sc.close();
	}
}