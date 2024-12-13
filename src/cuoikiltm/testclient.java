package cuoikiltm;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;


	public class testclient {
	    private static final String GROUP_ADDRESS = "224.0.0.1";
	    private static final int PORT = 8081;
	    private static final int BUFFER_SIZE = 256;

	    public static void main(String[] args) {
	        try (MulticastSocket multicastSocket = new MulticastSocket(PORT);
	             DatagramSocket unicastSocket = new DatagramSocket()) {

	            InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
	            multicastSocket.joinGroup(group);

	            Scanner scanner = new Scanner(System.in);

	            // Gửi yêu cầu tham gia với mật khẩu
	            System.out.print("Nhập mật khẩu nhóm: ");
	            String password = scanner.nextLine();
	            String joinMessage = "JOIN:" + password;
	            unicastSocket.send(new DatagramPacket(joinMessage.getBytes(), joinMessage.length(), InetAddress.getLocalHost(), PORT));

	            // Nhận phản hồi từ server
	            byte[] buffer = new byte[BUFFER_SIZE];
	            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
	            unicastSocket.receive(responsePacket);
	            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
	            System.out.println(response);

	            if (response.contains("không thể")) {
	                System.out.println("Không thể tham gia nhóm. Đóng client...");
	                return;
	            }

	            System.out.println("Đã tham gia vào nhóm chat. Đang chờ tin nhắn...");

	            // Nhận tin nhắn từ nhóm multicast
	            new Thread(() -> {
	                try {
	                    while (true) {
	                        byte[] messageBuffer = new byte[BUFFER_SIZE];
	                        DatagramPacket packet = new DatagramPacket(messageBuffer, messageBuffer.length);
	                        multicastSocket.receive(packet);

	                        String receivedMessage = new String(packet.getData(), 0, packet.getLength());
	                        System.out.println("Nhận được: " + receivedMessage);
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }).start();

	            // Gửi tin nhắn tới server
	            while (true) {
	                String message = scanner.nextLine();
	                DatagramPacket packetToSend = new DatagramPacket(message.getBytes(), message.length(),
	                        InetAddress.getLocalHost(), PORT);
	                unicastSocket.send(packetToSend);
	                System.out.println("Đã gửi: " + message);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}