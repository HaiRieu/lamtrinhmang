package server;

import java.awt.EventQueue;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class giaodienserve extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	
	    private static final String GROUP_ADDRESS = "224.0.0.1";
	    private static final int PORT = 8080;
	    private static final int BUFFER_SIZE = 256;
	    private static String groupPassword = null; // Mật khẩu của nhóm
	    private static Set<InetAddress> authorizedClients = new HashSet<>();
	  
	  
	public giaodienserve() {
		
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jbutton_start = new JButton("Start");
		jbutton_start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				//Thread thread = new Thread(() -> {
					try (DatagramSocket serverSocket = new DatagramSocket(PORT);
				             MulticastSocket multicastSocket = new MulticastSocket()) {

				            InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
				            System.out.println("Server đang chờ client đầu tiên tạo nhóm với mật khẩu...");

				            // Nhận mật khẩu từ client đầu tiên
				            DatagramPacket firstPacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				            serverSocket.receive(firstPacket);
				            groupPassword = new String(firstPacket.getData(), 0, firstPacket.getLength()).trim();
				            System.out.println("Mật khẩu nhóm đã được thiết lập.");

				            // Chạy vòng lặp chính
				            while (true) {
				                // Nhận tin nhắn từ client
				                byte[] buffer = new byte[BUFFER_SIZE];
				                DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
				                serverSocket.receive(receivedPacket);
				                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength()).trim();

				                // Nếu client yêu cầu tham gia và nhập mật khẩu
				                if (message.startsWith("JOIN:")) {
				                    String password = message.substring(5); // Trích xuất mật khẩu
				                    if (password.equals(groupPassword)) {
				                        authorizedClients.add(receivedPacket.getAddress());
				                        String joinSuccess = "Bạn đã tham gia nhóm thành công!";
				                        serverSocket.send(new DatagramPacket(joinSuccess.getBytes(), joinSuccess.length(),
				                                receivedPacket.getAddress(), receivedPacket.getPort()));
				                        System.out.println("Client được chấp nhận tham gia nhóm.");
				                    } else {
				                        String errorMsg = "Mật khẩu sai. Không thể tham gia nhóm.";
				                        serverSocket.send(new DatagramPacket(errorMsg.getBytes(), errorMsg.length(),
				                                receivedPacket.getAddress(), receivedPacket.getPort()));
				                    }
				                    continue;
				                }

				                // Phát lại tin nhắn cho các client đã được ủy quyền
				                if (authorizedClients.contains(receivedPacket.getAddress())) {
				                    DatagramPacket packetToSend = new DatagramPacket(message.getBytes(), message.length(), group, PORT);
				                    multicastSocket.send(packetToSend);
				                }
				            }

				        } catch (IOException e1) {
				            e1.printStackTrace();
				        }
			//	}) ;
	   //  thread.start();		
				
			}
			});
		jbutton_start.setFont(new Font("Tahoma", Font.BOLD, 14));
		jbutton_start.setBounds(136, 356, 98, 33);
		contentPane.add(jbutton_start);
		
		JButton btnNewButton_1 = new JButton("Stop");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(324, 356, 98, 33);
		contentPane.add(btnNewButton_1);
		
		 textArea = new JTextArea();
		textArea.setBounds(23, 49, 483, 281);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Server");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(35, 10, 98, 29);
		contentPane.add(lblNewLabel);
	
}

	
	
	
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				giaodienserve frame = new giaodienserve();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
		}
	

	
		
	
	

