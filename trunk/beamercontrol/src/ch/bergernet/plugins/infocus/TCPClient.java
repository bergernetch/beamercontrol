package ch.bergernet.plugins.infocus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
	private Socket socket;
	
	void connect(String host, int port) throws IOException {
        socket = new Socket(host,port); // verbindet sich mit Server
    }
	
	public String getInfo(){
		return "almost real beamer 0.0.1";
	}
    
    public String send(String msg) throws IOException {
    	rawSend(socket, msg);
        return(rawRead(socket));
    }
    
    private void rawSend(java.net.Socket socket, String msg) throws IOException {
         PrintWriter printWriter =
            new PrintWriter(
                new OutputStreamWriter(
                    socket.getOutputStream()));
        printWriter.print(msg);
        printWriter.flush();
   }
    private String rawRead(java.net.Socket socket) throws IOException {
        BufferedReader bufferedReader =
            new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        char[] buffer = new char[10000];
        int anzahlZeichen = bufferedReader.read(buffer, 0, 10000); // blockiert bis Nachricht empfangen
        String nachricht = new String(buffer, 0, anzahlZeichen);
        return nachricht;
   }
}