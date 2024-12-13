package cuoikiltm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class testclient2 extends JFrame {
	   private CardLayout cardLayout;
	    private JPanel contentPane;
	    private JTextField txtRoomName, txtRoomPassword, txtJoinPassword;
	    private JTextArea chatArea;
	    private PrintWriter out;
	    private BufferedReader in;

	    public testclient2() {
	        connectToServer();

	        setTitle("Chat Application");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 500, 400);
	        contentPane = new JPanel();
	        cardLayout = new CardLayout();
	        contentPane.setLayout(cardLayout);
	        setContentPane(contentPane);

	        // Panel tạo phòng
	        JPanel createRoomPanel = new JPanel();
	        createRoomPanel.setLayout(new FlowLayout());
	        contentPane.add(createRoomPanel, "CreateRoom");

	        txtRoomName = new JTextField(10);
	        txtRoomPassword = new JTextField(10);
	        JButton btnCreateRoom = new JButton("Create Room");

	        btnCreateRoom.addActionListener(e -> {
	            String roomName = txtRoomName.getText();
	            String password = txtRoomPassword.getText();
	            out.println("CREATE_ROOM " + roomName + " " + password);
	            cardLayout.show(contentPane, "ChatRoom");
	        });

	        createRoomPanel.add(new JLabel("Room Name:"));
	        createRoomPanel.add(txtRoomName);
	        createRoomPanel.add(new JLabel("Password:"));
	        createRoomPanel.add(txtRoomPassword);
	        createRoomPanel.add(btnCreateRoom);

	        // Panel tham gia phòng
	        JPanel joinRoomPanel = new JPanel();
	        joinRoomPanel.setLayout(new FlowLayout());
	        contentPane.add(joinRoomPanel, "JoinRoom");

	        txtJoinPassword = new JTextField(10);
	        JButton btnJoinRoom = new JButton("Join Room");

	        btnJoinRoom.addActionListener(e -> {
	            String roomName = txtRoomName.getText();
	            String password = txtJoinPassword.getText();
	            out.println("JOIN_ROOM " + roomName + " " + password);
	            cardLayout.show(contentPane, "ChatRoom");
	        });

	        joinRoomPanel.add(new JLabel("Room Name:"));
	        joinRoomPanel.add(txtRoomName);
	        joinRoomPanel.add(new JLabel("Password:"));
	        joinRoomPanel.add(txtJoinPassword);
	        joinRoomPanel.add(btnJoinRoom);

	        // Panel ChatRoom
	        JPanel chatRoomPanel = new JPanel();
	        chatRoomPanel.setLayout(new BorderLayout());
	        contentPane.add(chatRoomPanel, "ChatRoom");

	        chatArea = new JTextArea();
	        chatArea.setEditable(false);
	        chatRoomPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);

	        JTextField txtMessage = new JTextField();
	        txtMessage.addActionListener(e -> {
	            String message = txtMessage.getText();
	            out.println("SEND_MSG " + message);
	            txtMessage.setText("");
	        });
	        chatRoomPanel.add(txtMessage, BorderLayout.SOUTH);
	    }

	    private void connectToServer() {
	        try {
	            Socket socket = new Socket("localhost", 12345);
	            out = new PrintWriter(socket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	            // Thread để nhận tin nhắn từ server
	            new Thread(() -> {
	                try {
	                    String serverMessage;
	                    while ((serverMessage = in.readLine()) != null) {
	                        chatArea.append(serverMessage + "\n");
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }).start();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	            	testclient2 frame = new testclient2();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }
}
