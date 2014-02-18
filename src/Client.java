import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	String line, choix;
	Scanner s;
	
	public Client() {
		try {
			// Socket de connection au port 800
			socket = new Socket("localhost",8080);

			// Input, output et scanner
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			s = new Scanner(System.in);

			while(true){
				// Lecture de la requete
				choix = s.nextLine();
				
				// Envoi de la requete au serveur
				out.println(choix);
				out.flush();
				
				// Reception de la reponse du serveur
				do
				{
					line = in.readLine();
					System.out.println(line);
				}
				while((! line.contains("OK")) || (! line.contains("KO")) );
				
				
			}
			// Attente de l'envoi du menu par le serveur
			/*line=in.readLine();
			while(line != null){
				if(line.contains("endendend"))
					line=null;
				else{
					System.out.println(line);
					line=in.readLine();	
				}
			}
			
			// Debut du traitement
			choix = s.nextLine();
			while(! choix.equals("0")){
				// Envoi du choix au serveur
				out.println(choix);
				out.flush();
				
				// Reponse du serveur
				line=in.readLine();
				//Traitement de la réponse
				if(line.contains("endFunction")){
					line = in.readLine();
					while(! line.contains("endendend")){
						System.out.println(line);
						line=in.readLine();
					}
				}else
					System.out.println(line);
				choix = s.nextLine();
			}*/

			//socket.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
