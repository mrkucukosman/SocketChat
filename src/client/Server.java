package client;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static void main(String[] args) {
		ArrayList<ServerThread> threadList = new ArrayList<ServerThread>();
		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			while (true) {
				Socket socket = serverSocket.accept();
				ServerThread serverThread = new ServerThread(socket, threadList);

				threadList.add(serverThread);
				serverThread.start();
			}
		} catch (Exception e) {
			System.err.println("Error " + e);
		}
	}
}
