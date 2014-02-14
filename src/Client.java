import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String [] args) {

		Socket socket;
		BufferedReader in;
		PrintWriter out;
		String line;
		Scanner s;
		int choix;

		try {

			socket = new Socket("localhost",8080);

			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			s = new Scanner(System.in);

			line=in.readLine();
			while(line != null){
				if(line.contains("endendend"))
					line=null;
				else{
					System.out.println(line);
					line=in.readLine();	
				}
			}
			choix = s.nextInt();
			while(! (choix == 0)){
				out.println(choix);
				out.flush();

				System.out.println(in.readLine());
				choix = s.nextInt();
			}

			System.out.println("Fin de la connection.");
			socket.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
