/**
	Description: This is a simple TCP server that listens for incoming client connections and performs addition and subtraction operations.
	Author: Lerynnx (GitHub)
	Date: 2024-04-03
	Version: 1.0
	Do not remove this Attribution if you use this code.
	Nottify the author if you want to use this code before using it.
*/


import java.net.*;
import java.io.*;

public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896;

			// Create a server socket
			ServerSocket listenSocket = new ServerSocket(serverPort);

			while(true) {
				// Accept incoming client connections
				Socket clientSocket = listenSocket.accept();
				// Create a new Connection object for each client
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {
			System.out.println("Listen socket:"+e.getMessage());
		}
	}
}

//! This class should be in a separate file called Connection.java but for the sake of simplicity, I'm including it here
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;

			// Get input stream from client
			in = new DataInputStream( clientSocket.getInputStream());
			// Get output stream to client
			out =new DataOutputStream( clientSocket.getOutputStream());
			// Start the thread
			this.start();
		} catch(IOException e) {
			System.out.println("Connection:"+e.getMessage());
		}
	}

	public void run(){
		try {
			// Read data from client			                 
			String data = in.readUTF(); 
			// Split the data into parts
			String[] parts = data.split(" ");
			// Parse the first number
			int num1 = Integer.parseInt(parts[0]);
			// Get the operator
			String operator = parts[1];
			// Parse the second number
			int num2 = Integer.parseInt(parts[2]);

			if(operator.equals("+")){
				// Perform addition
				data = Integer.toString(num1 + num2); 
			}else if(operator.equals("-")){
				// Perform subtraction
				data = Integer.toString(num1 - num2);
			}

			// Send the result back to the client
			out.writeUTF(data); 

		}catch (EOFException e){
			System.out.println("EOF:"+e.getMessage());
		}catch(IOException e) {
			System.out.println("readline:"+e.getMessage());
		}finally{
			try {clientSocket.close();}catch (IOException e){/*close failed*/}
		}
	}
}