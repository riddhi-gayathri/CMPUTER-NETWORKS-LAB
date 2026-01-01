//DATAGRAM SERVER

import java.net.*;
import java.util.Scanner;


public class DatagramServer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            DatagramSocket serverSocket = new DatagramSocket(); // Create socket

            InetAddress clientAddress = InetAddress.getByName("localhost"); // Client address

            int clientPort = 9876; // Client port number

            System.out.println("Server started. Type messages to send to the client.");

            System.out.println("Type 'exit' to quit.\n");

            while (true) {

                System.out.print("Server: ");

                String message = sc.nextLine();

                byte[] sendData = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                serverSocket.send(sendPacket); // Send message to client

                if (message.equalsIgnoreCase("exit")) {

                    System.out.println("Server shutting down...");

                    break;

                }

            }

            serverSocket.close();

            sc.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}.
//DATAGRAM CLIENT
import java.net.*;

public class DatagramClient {

    public static void main(String[] args) {

        try {

            DatagramSocket clientSocket = new DatagramSocket(9876); // Bind to port

            byte[] receiveData = new byte[1024];

            System.out.println("Client is running... Waiting for messages from the server.\n");

            while (true) {

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                clientSocket.receive(receivePacket); // Receive message from server

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Message from Server: " + message);

                if (message.equalsIgnoreCase("exit")) {

                    System.out.println("Client shutting down...");

                    break;

                }

            }

            clientSocket.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
