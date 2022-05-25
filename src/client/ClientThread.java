package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

	private Socket socket;
	private BufferedReader input;

	public ClientThread(Socket socket) throws IOException {
		this.socket = socket;
		this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {
		try (BufferedReader reader = input) {
			while (true) {
				String response = input.readLine();
				System.out.println(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
