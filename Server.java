import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Server {

	public static void main(String argv[]) throws Exception, IOException {
		// TCP socket variables
		ServerSocket welcomeSocket;
		Socket connectionSocket;

		// client variables
		String clientSentence, serverSentence, putOrGer, path;
		HashMap<String, Date> map = new HashMap<>();
		ArrayList<String> putArray = new ArrayList<>();
		putArray.add("/index.html");
		Date indexDate = new Date();
		map.put("/index.html", indexDate);

		// command-line arguments
		int port;

		// process command-line arguments
		if (argv.length < 1) {
			System.out.println("Usage: java TCPServer port\n");
			System.exit(-1);
		}
		port = Integer.parseInt(argv[0]);

		// Create welcoming socket using the given port
		welcomeSocket = new ServerSocket(port);

		System.out.println("Listening on port " + port + "...");

		// While loop to handle arbitrary sequence of clients making requests
		while (true) {

			// Waits for some client to connect and creates new socket
			// for connection
			connectionSocket = welcomeSocket.accept();
			System.out.println("Client Made Connection");

			// Create (buffered) input stream attached to connection socket
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			// Create output stream attached to connection socket
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            PrintWriter writer = new PrintWriter(outToClient, true);

			// Read input line from socket
            clientSentence = inFromClient.readLine();
            while (!clientSentence.isEmpty()) {
				System.out.println(clientSentence);
				//handle get comannds
				if(clientSentence.contains("GET")){
					putOrGer = "GET";
					Pattern pattern = Pattern.compile("(\\/[a-z].*) ");
					Matcher matcher = pattern.matcher(clientSentence);
					if(matcher.find()){
						path = matcher.group(1);
						if(map.containsKey(path)){
							writer.println("HTTP/1.0 200 OK");
							Date today = new Date();
							writer.println("Time: " + today);
							writer.println("Last  Modified: "+ map.get(path) );
						}
					} 
					else {
						writer.println("404 Not Found");
						Date today = new Date();
						writer.println("Time: " + today);
					}
				}
				//handle put comannds
				if(clientSentence.contains("PUT")){
					putOrGer = "PUT";
					Pattern pattern = Pattern.compile("(\\/[a-z].*) ");
					Matcher matcher = pattern.matcher(clientSentence);
					// path = matcher.group(1);
					if(matcher.find()){
						path = matcher.group(1);
						if(!map.containsKey(path)){
							Date lastMode = new Date();
							writer.println("200 OK File Created");
							Date today = new Date();
							writer.println("Time: " + today);
							writer.println("PUT "+ path);
							map.put(path, lastMode);
							writer.println("Last  Modified: "+ map.get(path) );
						} else {
							Date today =new Date();
							writer.println("606 FAILED File NOT Created");
							writer.println("Time: " + today);

						}
					} 
					
				}
                clientSentence = inFromClient.readLine();
			}

			// Close the connection socket
			connectionSocket.close();

		} // end while; loop back to accept a new client connection

	} // end main
} // end class
