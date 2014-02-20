import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	Socket socket;
	BufferedReader in;
	PrintWriter out;
	String line, choix;
	Scanner s;

	public Client() {
		try {
			// Socket de connection au port 8080
			socket = new Socket("localhost",8080);

			// Input, output et scanner
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			s = new Scanner(System.in);

			// Connection au serveur
			System.out.println("Entrez votre login :");
			out.println(s.nextLine());
			out.flush();

			// Attente d'une reponse favorable a la connection
			String connec = in.readLine();
			while(!(connec.contains("Connection reussie"))){
				System.out.println("Login incorrect :");
				out.println(s.nextLine());
				out.flush();
				connec = in.readLine();
			}
			System.out.println("Vous êtes connecté.");


			while(true){
				System.out.println("Quel choix ?");
				// Lecture de la requete
				choix = s.nextLine();

				// Envoi de la requete au serveur
				out.println(choix);
				out.flush();

				// Reception et affichage de la reponse du serveur
				line = in.readLine();
				while(!(line.contains("OK")) && !(line.contains("KO")) ){					
					System.out.println(line);
					line = in.readLine();
				}
				System.out.println(line);

			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
