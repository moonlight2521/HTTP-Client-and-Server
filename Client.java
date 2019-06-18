import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.Severity;



class Client {

    public static void main(String argv[]) throws Exception, IOException
    {
	// TCP socket variables
	Socket clientSocket;
	DataOutputStream outToServer;
	BufferedReader inFromServer;
	
	// client variables
	BufferedReader inFromUser;
	String clientSentence, serverSentence;

	// command-line arguments
	String server="localhost",path = "index.html", url, line, putOrGet="GET";
	int port = 18000;

	// process command-line arguments check for
    if( argv.length == 1) {
        url = argv[0];
        putOrGet = "GET";

        if(url.contains("www")){
            Pattern pattern = Pattern.compile("(www.[a-zA-Z0-9]*.[a-z]{3})(\\/[a-z].*.html)");
            Matcher matcher = pattern.matcher(url);
            if(matcher.find()){
                server = matcher.group(1);
                port = 80;
                path = matcher.group(2);
            }
        }
        if(url.contains("172")){
            Pattern pattern = Pattern.compile("((?:[0-9]{1,3}\\.){3}[0-9]{1,3}):([0-9]{4,5})(\\/[a-z].*.[html]?)?");
            Matcher matcher = pattern.matcher(url);
            if(matcher.find()){
                server = matcher.group(1);
                // server="localhost";
                // System.out.println(matcher.group(1));
                port = Integer.parseInt(matcher.group(2));
                path = matcher.group(3);
            }
        }

        if(url.contains("localhost:")){
            Pattern pattern = Pattern.compile("localhost:([0-9]{4,5})(\\/[a-z].*.[html]?)?");
            Matcher matcher = pattern.matcher(url);
            if(matcher.find()){
                server = "localhost";
                port = Integer.parseInt(matcher.group(1));
                path = matcher.group(2);
            }
        } 

        //check for put argument 
    } else if(argv.length == 2){
        url = argv[1];
        putOrGet = "PUT";
        if(url.contains("www")){
            Pattern pattern = Pattern.compile("(www.[a-zA-Z0-9]*.[a-z]{3})(\\/[a-z].*.html)");
            Matcher matcher = pattern.matcher(url);
            if(matcher.find()){
                server = matcher.group(1);
                port = 80;
                path = matcher.group(2);
    
            }
        }
        if(url.contains("172")){
            Pattern pattern = Pattern.compile("((?:[0-9]{1,3}\\.){3}[0-9]{1,3}):([0-9]{4,5})(\\/[a-z].*.[html]?)?");
            Matcher matcher = pattern.matcher(url);
            if(matcher.find()){
                server = matcher.group(1);
                port = Integer.parseInt(matcher.group(2));
                path = matcher.group(3);
            }
        }

        if(url.contains("localhost:")){
            Pattern pattern = Pattern.compile("localhost:([0-9]{4,5})(\\/[a-z].*.[html]?)?");
            Matcher matcher = pattern.matcher(url);
            if(matcher.find()){
                server = "localhost";
                port = Integer.parseInt(matcher.group(1));
                path = matcher.group(2);
            }
        } 
    } else {
        System.out.println ("Usage: java TCPClient hostname port or java HTTPClient PUT URL path/<filename> \n");
        System.exit (-1);
    } 
    //get date
    Date today = new Date();
	// Create (buffered) input stream using standard input
    inFromUser = new BufferedReader(new InputStreamReader(System.in));  
    // Create client socket with connection to server at given port
    // System.out.println(server);
    // System.out.println(port);
    // System.out.println(path);

    clientSocket = new Socket(server, port);
	
	// Create output stream attached to socket
	outToServer = new DataOutputStream(clientSocket.getOutputStream());

	// Create (buffered) input stream attached to socket
	inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    // System.out.println(url);
    PrintWriter writer = new PrintWriter(outToServer, true);

    //sent to server
    writer.println( putOrGet + " " + path + " HTTP/1.0");
    writer.println("Host: " + server);
    writer.println("Time: " + today);
    writer.println("User-Agent: VCU-CMSC491");
    writer.println("User-name: Zun");
    writer.println();

    while((line = inFromServer.readLine()) != null){
        System.out.println(line);
    }

	// Close the socket
	clientSocket.close();

	} // end main

} // end class


