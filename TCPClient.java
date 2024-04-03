/**
	Description: This is a simple TCP client that connects to a server and sends a message to the server.
	Author: Lerynnx (GitHub)
	Date: 2024-04-03
	Version: 1.0
	Do not remove this Attribution if you use this code.
	Nottify the author if you want to use this code before using it.
*/

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPClient {

	private static Scanner scanner = new Scanner(System.in);

	public static void main (String args[]) {
		Socket s = null;

		try{
			String address = "localhost";
			int serverPort = 7896;
			
			// Create a socket and connect to the server
			s = new Socket(address, serverPort);
			// Input stream to receive data from the server
			DataInputStream in = new DataInputStream( s.getInputStream());
			// Output stream to send data to the server
			DataOutputStream out =new DataOutputStream( s.getOutputStream());

			// Send the message "12 + 4" to the server
			out.writeUTF("12 + 4"); 

			// Read the response from the server
			String data = in.readUTF();

			System.out.println("Received: "+ data); 
			
		}catch (UnknownHostException e){
			System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){
			System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){
			System.out.println("readline:"+e.getMessage());
		}finally {
			// Close the socket
			if(s!=null) try {s.close();}catch (IOException e){
				System.out.println("close:"+e.getMessage());
			}
		}
	}
}