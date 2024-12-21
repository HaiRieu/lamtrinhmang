package server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ConferenceServer extends JFrame {

//    private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextArea textArea;

    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 1024;

    private static Map<String, String> userCredentials = new HashMap<>(); 
    private static Map<String, Set<InetAddress>> rooms = new HashMap<>(); 

    public ConferenceServer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnStart = new JButton("Start Server");
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnStart.setBounds(220, 400, 150, 40);
        contentPane.add(btnStart);

        textArea = new JTextArea();
        textArea.setBounds(20, 40, 550, 350);
        contentPane.add(textArea);

        JLabel lblServer = new JLabel("Conference Server");
        lblServer.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblServer.setBounds(200, 0, 200, 30);
        contentPane.add(lblServer);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	btnStart.setEnabled(false);
                new Thread(() -> startServer()).start();
            }
        });
    }

    private void startServer() {
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            textArea.append("Server started on port " + PORT + "\n");

            while (true) {
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(receivedPacket);
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength()).trim();
                
                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();

                if (message.startsWith("REGISTER:")) {
                    handleRegistration(message, clientAddress, clientPort, serverSocket);
                } else if (message.startsWith("LOGIN:")) {
                    handleLogin(message, clientAddress, clientPort, serverSocket);
                } else if (message.startsWith("CREATE_ROOM:")) {
                    handleCreateRoom(message, clientAddress, clientPort, serverSocket);
                } else if (message.startsWith("JOIN_ROOM:")) {
                    handleJoinRoom(message, clientAddress, clientPort, serverSocket);
                } else if (message.startsWith("MESSAGE:")) {
                    handleMessage(message, clientAddress, clientPort, serverSocket);
                } else {
                    sendResponse("Unknown command.", clientAddress, clientPort, serverSocket);
                }
            }
        } catch (IOException e) {
            textArea.append("\nError: " + e.getMessage());
        }
    }

    private void handleRegistration(String message, InetAddress clientAddress, int clientPort, DatagramSocket serverSocket) {
        String[] parts = message.split(":", 3);
        if (parts.length < 3) {
            sendResponse("Invalid registration format.", clientAddress, clientPort, serverSocket);
            return;
        }
        String username = parts[1];
        String password = parts[2];

        if (userCredentials.containsKey(username)) {
            sendResponse("Username already exists.", clientAddress, clientPort, serverSocket);
        } else {
            userCredentials.put(username, password);
            sendResponse("Registration successful.", clientAddress, clientPort, serverSocket);
        }
    }

    private void handleLogin(String message, InetAddress clientAddress, int clientPort, DatagramSocket serverSocket) {
        String[] parts = message.split(":", 3);
        if (parts.length < 3) {
            sendResponse("Invalid login format.", clientAddress, clientPort, serverSocket);
            return;
        }
        String username = parts[1];
        String password = parts[2];

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            sendResponse("Login successful.", clientAddress, clientPort, serverSocket);
        } else {
            sendResponse("Invalid username or password.", clientAddress, clientPort, serverSocket);
        }
    }

    private void handleCreateRoom(String message, InetAddress clientAddress, int clientPort, DatagramSocket serverSocket) {
        String[] parts = message.split(":", 2);
        if (parts.length < 2) {
            sendResponse("Invalid room creation format.", clientAddress, clientPort, serverSocket);
            return;
        }
        String roomName = parts[1];

        if (rooms.containsKey(roomName)) {
            sendResponse("Room already exists.", clientAddress, clientPort, serverSocket);
        } else {
            rooms.put(roomName, new HashSet<>());
            sendResponse("Room created successfully.", clientAddress, clientPort, serverSocket);
        }
    }

    private void handleJoinRoom(String message, InetAddress clientAddress, int clientPort, DatagramSocket serverSocket) {
        String[] parts = message.split(":", 2);
        if (parts.length < 2) {
            sendResponse("Invalid room join format.", clientAddress, clientPort, serverSocket);
            return;
        }
        String roomName = parts[1];

        if (rooms.containsKey(roomName)) {
            rooms.get(roomName).add(clientAddress);
            sendResponse("Joined room successfully.", clientAddress, clientPort, serverSocket);
        } else {
            sendResponse("Room does not exist.", clientAddress, clientPort, serverSocket);
        }
    }

    private void handleMessage(String message, InetAddress clientAddress, int clientPort, DatagramSocket serverSocket) {
        String[] parts = message.split(":", 3);
        if (parts.length < 3) {
            sendResponse("Invalid message format.", clientAddress, clientPort, serverSocket);
            return;
        }
        String roomName = parts[1];
        String chatMessage = parts[2];

        if (rooms.containsKey(roomName)) {
            for (InetAddress client : rooms.get(roomName)) {
                sendResponse(chatMessage, client, clientPort, serverSocket);
            }
        } else {
            sendResponse("Room does not exist.", clientAddress, clientPort, serverSocket);
        }
    }

    private void sendResponse(String message, InetAddress clientAddress, int clientPort, DatagramSocket serverSocket) {
        try {
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            serverSocket.send(packet);
        } catch (IOException e) {
            textArea.append("\nError sending response: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConferenceServer frame = new ConferenceServer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
