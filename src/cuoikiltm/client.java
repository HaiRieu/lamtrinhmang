package cuoikiltm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class client extends JFrame {

	 JPanel contentPane;
	 CardLayout cardLayout;
	 JTextField textField;
	 JTextField textField_1;
	 JTextField textField_tenchinh;
	 JTextField textField_tendangnhap;
	 JTextField textField_matkhau;
	 JTextField textField_matkhauphong;
	 JTextField textField_2;
	 ArrayList<String> clients;
	 JPanel panel_lable;
	private JPanel panel_client; 
	  static final int LABEL_WIDTH = 114;
	  static final int LABEL_HEIGHT = 114;
	


	public client() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 662);
		
		clients = new ArrayList<>();
		
		    contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        cardLayout = new CardLayout(); // Khởi tạo CardLayout
	        contentPane.setLayout(cardLayout); // Đặt layout cho contentPane là CardLayout
	       setContentPane(contentPane);
	       
		JPanel panel_dangnhap = new JPanel();
		contentPane.add(panel_dangnhap, "dangnhap");
		panel_dangnhap.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(346, 250, 234, 26);
		panel_dangnhap.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(346, 342, 234, 26);
		panel_dangnhap.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lable_dangnhap = new JLabel("Đăng Nhập");
		lable_dangnhap.setFont(new Font("Tahoma", Font.BOLD, 16));
		lable_dangnhap.setBounds(415, 166, 152, 47);
		panel_dangnhap.add(lable_dangnhap);
		
		JLabel lable_taikhoan = new JLabel("Tài Khoản");
		lable_taikhoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lable_taikhoan.setBounds(237, 250, 99, 20);
		panel_dangnhap.add(lable_taikhoan);
		
		JLabel lable_matkhau = new JLabel("Mật Khẩu");
		lable_matkhau.setFont(new Font("Tahoma", Font.BOLD, 14));
		lable_matkhau.setBounds(237, 342, 77, 20);
		panel_dangnhap.add(lable_matkhau);
		
		JButton jbutton_dangnhap = new JButton("Đăng Nhập");
		jbutton_dangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// XỬ LÍ ĐĂNG NHẬP Ở ĐÂY
			 cardLayout.show(contentPane, "trangchu");
			
			}
		});
		jbutton_dangnhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		jbutton_dangnhap.setBounds(457, 424, 123, 32);
		panel_dangnhap.add(jbutton_dangnhap);
		
		JButton jbutton_dangky = new JButton("Đăng Ký");
		jbutton_dangky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "dangky");
			}
		});
		jbutton_dangky.setFont(new Font("Tahoma", Font.BOLD, 15));
		jbutton_dangky.setBounds(290, 424, 112, 32);
		panel_dangnhap.add(jbutton_dangky);
		
		JPanel panel_dangky = new JPanel();
		contentPane.add(panel_dangky, "dangky");
		
		panel_dangky.setLayout(null);
		
		JLabel lable_dangkytaikhoan = new JLabel("Đăng Ký Tài Khoản");
		lable_dangkytaikhoan.setFont(new Font("Tahoma", Font.BOLD, 16));
		lable_dangkytaikhoan.setBounds(399, 136, 228, 52);
		panel_dangky.add(lable_dangkytaikhoan);
		
		textField_tenchinh = new JTextField();
		textField_tenchinh.setBounds(431, 218, 214, 35);
		panel_dangky.add(textField_tenchinh);
		textField_tenchinh.setColumns(10);
		
		textField_tendangnhap = new JTextField();
		textField_tendangnhap.setBounds(431, 310, 214, 35);
		panel_dangky.add(textField_tendangnhap);
		textField_tendangnhap.setColumns(10);
		
		textField_matkhau = new JTextField();
		textField_matkhau.setBounds(431, 397, 214, 35);
		panel_dangky.add(textField_matkhau);
		textField_matkhau.setColumns(10);
		
		JLabel lable_tenchinh = new JLabel("Họ Và Tên");
		lable_tenchinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lable_tenchinh.setBounds(325, 219, 90, 28);
		panel_dangky.add(lable_tenchinh);
		
		JLabel lblNewLabel_1 = new JLabel("Tài Khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(331, 310, 90, 30);
		panel_dangky.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật Khẩu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(328, 401, 71, 26);
		panel_dangky.add(lblNewLabel_2);
		
		JButton jbutton_dangky1 = new JButton("Đăng Ký");
		jbutton_dangky1.setFont(new Font("Tahoma", Font.BOLD, 14));
		jbutton_dangky1.setBounds(449, 486, 132, 35);
		panel_dangky.add(jbutton_dangky1);
		jbutton_dangky1.addActionListener(new ActionListener() {
			// XỬ LÝ LOGIC ĐĂNG KÝ
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "trangchu");
			}
		});
		
		 panel_client = new JPanel();
		contentPane.add(panel_client, "trangchu");
		panel_client.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mật Khẩu Phòng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(284, 296, 144, 37);
		panel_client.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Trang Chủ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(428, 172, 93, 58);
		panel_client.add(lblNewLabel_3);
		
		textField_matkhauphong = new JTextField();
		textField_matkhauphong.setBounds(464, 302, 179, 29);
		panel_client.add(textField_matkhauphong);
		textField_matkhauphong.setColumns(10);
		
		JButton jbutton_vaophong = new JButton("Vào Phòng");
		jbutton_vaophong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// LOGIC XỬ LÝ VÀO PHÒNG
//				String clientName = "Client_" + (clients.size() + 1);
//	                addClient(clientName);
				
				cardLayout.show(contentPane, "client");
				
			}
		});
		jbutton_vaophong.setFont(new Font("Tahoma", Font.BOLD, 14));
		jbutton_vaophong.setBounds(308, 392, 120, 34);
		panel_client.add(jbutton_vaophong);
		
		JButton btnNewButton = new JButton("Tạo Phòng");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LOGIC XỬ LÝ TẠO PHÒNG
				String roomId = UUID.randomUUID().toString().split("-")[0];
				System.out.println(roomId);
			}
		});
		btnNewButton.setBounds(515, 392, 128, 34);
		panel_client.add(btnNewButton);
		
		JPanel panel_jonephong = new JPanel();
		contentPane.add(panel_jonephong, "client");
		panel_jonephong.setLayout(null);
		
		JTextArea textArea_thaoluan = new JTextArea();
		textArea_thaoluan.setBounds(686, 97, 243, 248);
		panel_jonephong.add(textArea_thaoluan);
		
		JLabel lblNewLabel_4 = new JLabel("Chia sẽ màn hình");
		lblNewLabel_4.setBounds(63, 43, 574, 367);
		panel_jonephong.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Thảo Luận");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(755, 45, 95, 30);
		panel_jonephong.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(769, 387, 160, 23);
		panel_jonephong.add(textField_2);
		textField_2.setColumns(10);
		
		JButton jbutton_gui = new JButton("Gửi");
		jbutton_gui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// LOGIC XỬ LÝ TIN NHẮN REALTIME - JAVA WEBSOCkET
//				-javax.websocket
//				-org.projectlombok
//				-jsonpp
				
				
				// Cai nay cua chatgpt
//				 String clientName = "Client_" + (clients.size() + 1);
//	                addClient(clientName);
				
				
			}
		});
		jbutton_gui.setBounds(674, 388, 85, 21);
		panel_jonephong.add(jbutton_gui);
		
		 panel_lable = new JPanel();
		panel_lable.setBounds(25, 457, 919, 148);
		panel_jonephong.add(panel_lable);
		panel_lable.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		

		
	}
	  private void addClient(String clientName) {
	        // Thêm tên client vào danh sách
	        clients.add(clientName);

	        // Tạo một JLabel cho client mới và thiết lập kích thước cố định
	        JLabel clientLabel = new JLabel(clientName, SwingConstants.CENTER);
	        clientLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT)); // Kích thước cố định 3cm x 3cm
	        clientLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Tạo viền để dễ nhìn thấy kích thước

	        panel_lable.add(clientLabel);
	        panel_lable.revalidate();
	        panel_lable.repaint();
	    }

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client frame = new client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
