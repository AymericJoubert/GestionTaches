import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Serveur{

	private ServerSocket socketserver;
	private Socket socketduserveur;
	private BufferedReader in;
	private PrintWriter out;
	private Scanner s;
	private String choix;
	private File fich;
	private FileWriter fichW;
	private FileReader fichR;
	public static int nbTaches = 0;
	public static HashMap<Integer, Tache> taches;
	public static ArrayList<String> logins;

	public Serveur(){

		try {			
			// HashMap contenant les taches
			taches = new HashMap();
			nbTaches = 0;

			// Socket de connection
			socketserver = new ServerSocket(8080);
			socketduserveur = socketserver.accept(); 

			// Pour lire et écrire
			out = new PrintWriter(socketduserveur.getOutputStream());
			in  = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));

			while(true){
				choix = in.readLine();
				String [] choixx = choix.split(" ");
				if(choixx[0].equals("create"))
					creerTache(out, choixx);
				else if(choixx[0].equals("list"))
					listerTaches(out, choixx);
				else if(choixx[0].equals("delete"))
					deleteTache(out,choixx);
					
			}

			// Fermeture des sockets
			//socketduserveur.close();
			//socketserver.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void creerTache(PrintWriter out, String [] tache){
		Tache t = new Tache(tache[1], tache[2]);
		taches.put(nbTaches++, t);
		out.println("OK");
		out.flush();
	}

	// Fonction qui gere l'affichage de la liste des taches
	public void listerTaches(PrintWriter out, String [] choix) throws IOException{
		String ret = "";
		Set cles = taches.keySet();
		Iterator it = cles.iterator();
		if(choix[1].equals("all")){
			while (it.hasNext()){
				Object cle = it.next();
				Tache t = taches.get(cle);
				ret+= "Description: "+t.getDescription()+", Auteur : "+t.getAuteur()+", Affectation : "+t.getStatus()+"\n";
			}
		}
		else if(choix[1].equals("status")){
			while (it.hasNext()){
				Object cle = it.next();
				Tache t = taches.get(cle);
				if(t.getStatus().equals("libre"))
					ret+= "Description: "+t.getDescription()+", Auteur : "+t.getAuteur()+", Affectation : "+t.getStatus()+"\n";
			}
			cles = taches.keySet();
			it = cles.iterator();
			while (it.hasNext()){
				Object cle = it.next();
				Tache t = taches.get(cle);
				if(t.getStatus().equals("affectée"))
					ret+= "Description: "+t.getDescription()+", Auteur : "+t.getAuteur()+", Affectation : "+t.getStatus()+"\n";
			}
			cles = taches.keySet();
			it = cles.iterator();
			while (it.hasNext()){
				Object cle = it.next();
				Tache t = taches.get(cle);
				if(t.getStatus().equals("realisée"))
					ret+= "Description: "+t.getDescription()+", Auteur : "+t.getAuteur()+", Affectation : "+t.getStatus()+"\n";
			}
		}
		else if(choix[1].equals("affect")){
			for(String s : logins){
				cles = taches.keySet();
				it = cles.iterator();
				while (it.hasNext()){
					Object cle = it.next();
					Tache t = taches.get(cle);
					if(t.getAffectation().equals(s))
						ret+= "Description: "+t.getDescription()+", Auteur : "+t.getAuteur()+", Affectation : "+t.getStatus()+"\n";
				}

			}
		}
		out.println(ret);
		out.println("OK");
		out.flush();
	}	
	
	public void deleteTache(PrintWriter out, String [] choix){
		if(taches.containsKey(Integer.parseInt(choix[1]))){
			taches.remove(Integer.parseInt(choix[1]));
			out.println("OK");
		}else
			out.println("KO");
		out.flush();
	}

}