package ch.bergernet.beamerSimulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BeamerServer {
		   public static void main(String[] args) {
			   BeamerServer server = new BeamerServer();
		        try {
		            server.test();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } 
		   }
		   void test() throws IOException {
		        int port = 8888;
		        java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
		        java.net.Socket client = warteAufAnmeldung(serverSocket);
		        String msg = readRaw(client);
		        System.out.println(msg);
		        writeRaw(client, msg);
		   }
		   java.net.Socket warteAufAnmeldung(java.net.ServerSocket serverSocket) throws IOException {
		        java.net.Socket socket = serverSocket.accept(); // blockiert, bis sich ein Client angemeldet hat
		        return socket;
		   }
		   String readRaw(java.net.Socket socket) throws IOException {
		        BufferedReader bufferedReader = 
		            new BufferedReader(
		                new InputStreamReader(
		                    socket.getInputStream()));
		        char[] buffer = new char[10000];
		        int anzahlZeichen = bufferedReader.read(buffer, 0, 10000); // blockiert bis msg empfangen
		        String msg = new String(buffer, 0, anzahlZeichen);
		        return msg;
		   }
		   void writeRaw(java.net.Socket socket, String msg) throws IOException {
		        PrintWriter printWriter =
		            new PrintWriter(
		                new OutputStreamWriter(
		                    socket.getOutputStream()));
		        printWriter.print(msg);
		        printWriter.flush();
		   }
		

}
