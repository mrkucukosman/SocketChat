package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 5000); Scanner scanner = new Scanner(System.in)) {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			String userInput;
			String response;
			String clientName = "empty";
			ClientThread clientThread = new ClientThread(socket);
			clientThread.start();

			do {
				if (clientName.equals("empty")) {
					System.out.println("Enter your name!");
					userInput = scanner.nextLine();
					clientName = userInput;
					output.println(userInput);
					if (userInput.equals("exit")) {
						break;
					}
				} else {
					String msg = ("(" + clientName + ")" + "message : ");
					System.out.println(msg);
					userInput = scanner.nextLine();
					output.println(msg + " " + userInput);
					if (userInput.equals("exit")) {
						break;
					}
				}
			} while (!userInput.equals("exit"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
