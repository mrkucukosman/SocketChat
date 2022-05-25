package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {

	private Socket socket;
	private ArrayList<ServerThread> threadList;
	private PrintWriter output;

	public ServerThread(Socket socket, ArrayList<ServerThread> threadList) {
		this.socket = socket;
		this.threadList = threadList;
	}

	@Override
	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				String outputString = input.readLine();

				if (outputString.equals("exit")) {
					break;
				}

				printToAllClients(outputString);
				System.out.println("Server received " + outputString);
			}
		} catch (Exception e) {
			System.err.println("Error : " + e);
		}
	}

	private void printToAllClients(String str) {
		for (ServerThread st : threadList) {
			st.output.println(str);
		}
	}
}
