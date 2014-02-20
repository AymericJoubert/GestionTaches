import java.io.BufferedReader;
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
	public static int nbTaches = 0;
	public static HashMap<Integer, Tache> taches;
	public static ArrayList<String> logins;

	public Serveur(){
		try {

			// HashMap contenant les taches
			taches = new HashMap();
			nbTaches = 0;
			logins = new ArrayList();

			// Logins acceptés pour la connections
			logins.add("jouberta");
			logins.add("test");

			// Socket de connection
			socketserver = new ServerSocket(8080);
			socketduserveur = socketserver.accept(); 

			// Pour lire et écrire
			out = new PrintWriter(socketduserveur.getOutputStream());
			in  = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));

			// Connection de l'utilisateur
			String log = in.readLine();
			while(!(logins.contains(log))){
				out.println("KO");
				out.flush();
				log = in.readLine();
			}

			// Login connu, utilisateur accepté
			out.println("Connection reussie.");
			out.flush();

			while(true){
				choix = in.readLine();
				String [] choixx = choix.split(" ");
				if(choixx[0].equals("create"))
					creerTache(out, choixx);
				else if(choixx[0].equals("list"))
					listerTaches(out, choixx);
				else if(choixx[0].equals("delete"))
					deleteTache( out, choixx);
				else if(choixx[0].equals("affect"))
					affectTache( out, choixx);
				else if(choixx[0].equals("change"))
					changeTache( out, choixx);
				else if(choixx[0].equals("exit"))
					socketduserveur.close();
				else{
					out.println("KO");
					out.flush();
				}


			}

			// Fermeture des sockets
			//socketduserveur.close();
			//socketserver.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Creation d'une tache
	public void creerTache(PrintWriter out, String [] tache){
		try{
			Tache t = new Tache(tache[1], tache[2], tache[3], tache[4], nbTaches);
			taches.put(nbTaches++, t);
			out.println("OK");
			out.flush();
		}
		catch(Exception e){
			Tache t = new Tache(tache[1], tache[2], nbTaches);
			taches.put(nbTaches++, t);
			out.println("OK");
			out.flush();
		}
	}

	// Fonction qui gere l'affichage de la liste des taches
	public void listerTaches(PrintWriter out, String [] choix){
		try{
			String ret = "";
			Set cles = taches.keySet();
			Iterator it = cles.iterator();
			if(choix[1].equals("all")){
				while (it.hasNext()){
					Object cle = it.next();
					Tache t = taches.get(cle);
					ret+="Num: "+t.getNum()+" Description: "+t.getDescription()+", Auteur : "+t.getAuteur()+", Affectation : "+t.getStatus()+"\n";
				}
			}
			else if(choix[1].equals("status")){
				while (it.hasNext()){
					Object cle = it.next();
					Tache t = taches.get(cle);
					if(t.getStatus().equals(choix[2]))
						ret+="Num: "+t.getNum()+" Description: "+t.getDescription()+", Auteur : "+t.getAuteur()+", Affectation : "+t.getStatus()+"\n";
				}
			}
			else if(choix[1].equals("affect")){
				while (it.hasNext()){
					Object cle = it.next();
					Tache t = taches.get(cle);
					if(t.getAffectation().equals(choix[2]))
						ret+="Num: "+t.getNum()+" Description: "+t.getDescription()+", Auteur : "+t.getAuteur()+", Affectation : "+t.getStatus()+"\n";
				}
			}
			out.println(ret);
			out.flush();
			out.println("OK");
			out.flush();
		}
		catch(Exception e){
			out.println(e.getMessage()+"\n");
			out.println(e.toString());
			out.println(e.getStackTrace());
			out.println("KO");
			out.flush();
		}
	}	

	// Suppression d'une tache
	public void deleteTache(PrintWriter out, String [] choix){
		if(taches.containsKey(Integer.parseInt(choix[1]))){
			taches.remove(Integer.parseInt(choix[1]));
			out.println("OK");
		}else
			out.println("KO");
		out.flush();
	}

	// Affectation d'une tache a une personne
	public void affectTache(PrintWriter out, String [] choix){
		try{
			if(choix[2] != null && taches.containsKey(Integer.parseInt(choix[1]))){
				taches.get(Integer.parseInt(choix[1])).setAffectation(choix[2]);
				taches.get(Integer.parseInt(choix[1])).setStatus("affectée");
				if(!logins.contains(choix[2]))
					logins.add(choix[2]);
				out.println("OK");
				out.flush();
			}else{
				out.println("KO");
				out.flush();
			}
		}catch(Exception e){
			out.println("KO");
			out.flush();
		}
	}

	// Changement du statut d'une tache
	public void changeTache(PrintWriter out, String [] choix){
		try{
			if((choix[2].equals("libre")||choix[2].equals("affectée")||choix[2].equals("réalisée")) && taches.containsKey(Integer.parseInt(choix[1]))){
				taches.get(Integer.parseInt(choix[1])).setStatus(choix[2]);
				out.println("OK");
				out.flush();
			}else{
				out.println("KO");
				out.flush();
			}
		}catch(Exception e){
			out.println(e.toString());
			out.flush();
		}
	}
}