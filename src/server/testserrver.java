package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class testserrver {
	 private static final int PORT = 8080;
	    private static List<String> roomPasswords = new ArrayList<>(); // Map lưu trữ mật khẩu phòng 

	    public static void main(String[] args) {
	        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
	            System.out.println("Server đang chạy và chờ client kết nối...");

	            while (true) {
	                Socket clientSocket = serverSocket.accept();
	                System.out.println("Client đã kết nối từ: " + clientSocket.getInetAddress());

	                // Xử lý client trong luồng riêng
	                new Thread(() -> handleClient(clientSocket)).start();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void handleClient(Socket clientSocket) {
	        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

	            String request;
	            while ((request = in.readLine()) != null) {
	                String[] parts = request.split(":");
	                String command = parts[0];

	                if (command.equals("CREATE_ROOM")) {
	                    String password = parts[1];
	                    roomPasswords.add(password);
	              
	                } else if (command.equals("JOIN_ROOM")) {

	                    String password = parts[1];
	                    String storedPassword = roomPasswords.get(1);

	                    if (storedPassword != null && storedPassword.equals(password)) {
	                        out.println("Tham gia phòng thành công!");
	                    } else {
	                        out.println("Sai mật khẩu hoặc phòng không tồn tại.");
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
