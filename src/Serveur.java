import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur{
	public static void main(String [] args){
		ServerSocket socketserver;
		Socket socketduserveur;
		BufferedReader in;
		PrintWriter out;
		Scanner s;
		String choix;
		File fich;
		FileWriter fichW;
		FileReader fichR;
		
		try {
			// Socket de connection
			socketserver = new ServerSocket(8080);
			socketduserveur = socketserver.accept(); 
			
			// Pour lire et écrire
			out = new PrintWriter(socketduserveur.getOutputStream());
			in  = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
			
			fich  = new File("bdd.csv");
			fichW = new FileWriter(fich);
			fichR = new FileReader(fich);
			
			sendMenu(out);
			
			choix = in.readLine();
			while(choix!=null && !choix.equals("0")){
				switch (Integer.parseInt(choix)){
					case 1 :
						out.println("Coucou tu as choisi le 1.");
						creerTache(out, in, fich, fichW);
						out.flush();
						break;
					case 2 :
						out.println("Coucou tu as choisi le 2.");
						out.flush();
						break;
					default :
						out.println("Ce choix n'est pas autorisé.");
				}
				choix = in.readLine();
			}
			socketduserveur.close();
			socketserver.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendMenu(PrintWriter out){
		out.println("        Menu :");
		out.println("        ------");
		out.println("1 : Créer une tâche.");
		out.println("2 : Voir la liste des tâches");
		out.println("3 : Affecter une tâche.");
		out.println("4 : Changer le statut d'une tâche.");
		out.println("5 : Supprimer une tache.");
		out.println("0 : Quitter.");
		out.println("endendend");
		out.flush();
	}
	
	public static void sendMenuTaches(PrintWriter out){
		out.println("1 : Voir toutes les tâches.");
		out.println("2 : Voir la liste par statut.");
		out.println("3 : Voir par affectation.");
		out.println("endendend");
		out.flush();
	}
	
	public static void creerTache(PrintWriter out, BufferedReader in, File fich, FileWriter fichW){
		out.println("Entrez le nom, le statut et l'affectation de la tache séparés par un espace : ");
		out.flush();
		//TODO Gérer le CSV en tant que base de donnée, insérer tâche et les lister
	}
}