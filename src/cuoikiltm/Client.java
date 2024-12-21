package cuoikiltm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

public class Client extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;
    private JTextField textField;
    private JTextField textFieldPassword;
    private JTextField textFieldFullName;
    private JTextField textFieldUsername;
    private JTextField textFieldRegisterPassword;
    private JTextField textFieldRoomPassword;
    private JTextField textFieldMessage;
    private ArrayList<String> clients;
    private JPanel panelLabels;
    private JPanel panelClient;

    private static final int LABEL_WIDTH = 114;
    private static final int LABEL_HEIGHT = 114;

    public Client() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 978, 662);

        clients = new ArrayList<>();

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        setContentPane(contentPane);

        initializeLoginPanel();
        initializeRegisterPanel();
        initializeHomePanel();
        initializeRoomPanel();
    }

    private void initializeLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        contentPane.add(loginPanel, "login");

        JLabel lblLogin = new JLabel("\u0110ăng Nhập");
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblLogin.setBounds(415, 166, 152, 47);
        loginPanel.add(lblLogin);

        JLabel lblUsername = new JLabel("Tài Khoản");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(237, 250, 99, 20);
        loginPanel.add(lblUsername);

        JLabel lblPassword = new JLabel("Mật Khẩu");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setBounds(237, 342, 77, 20);
        loginPanel.add(lblPassword);

        textField = new JTextField();
        textField.setBounds(346, 250, 234, 26);
        loginPanel.add(textField);
        textField.setColumns(10);

        textFieldPassword = new JTextField();
        textFieldPassword.setBounds(346, 342, 234, 26);
        loginPanel.add(textFieldPassword);
        textFieldPassword.setColumns(10);

        JButton btnLogin = new JButton("\u0110ăng Nhập");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnLogin.setBounds(457, 424, 123, 32);
        btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			cardLayout.show(contentPane, "trangchu");	
			}
		});
        loginPanel.add(btnLogin);

        JButton btnRegister = new JButton("\u0110ăng Ký");
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnRegister.setBounds(290, 424, 112, 32);
        btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			cardLayout.show(contentPane, "dangky");	
			}
		});
        loginPanel.add(btnLogin);
        loginPanel.add(btnRegister);
    }

    private void initializeRegisterPanel() {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);
        contentPane.add(registerPanel, "dangky");

        JLabel lblRegister = new JLabel("\u0110ăng Ký Tài Khoản");
        lblRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRegister.setBounds(399, 136, 228, 52);
        registerPanel.add(lblRegister);

        JLabel lblFullName = new JLabel("Họ Và Tên");
        lblFullName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFullName.setBounds(325, 219, 90, 28);
        registerPanel.add(lblFullName);

        textFieldFullName = new JTextField();
        textFieldFullName.setBounds(431, 218, 214, 35);
        registerPanel.add(textFieldFullName);
        textFieldFullName.setColumns(10);

        JLabel lblUsername = new JLabel("Tài Khoản");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(331, 310, 90, 30);
        registerPanel.add(lblUsername);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(431, 310, 214, 35);
        registerPanel.add(textFieldUsername);
        textFieldUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Mật Khẩu");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setBounds(328, 401, 71, 26);
        registerPanel.add(lblPassword);

        textFieldRegisterPassword = new JTextField();
        textFieldRegisterPassword.setBounds(431, 397, 214, 35);
        registerPanel.add(textFieldRegisterPassword);
        textFieldRegisterPassword.setColumns(10);

        JButton btnRegister = new JButton("\u0110ăng Ký");
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRegister.setBounds(449, 486, 132, 35);
        btnRegister.addActionListener(e -> cardLayout.show(contentPane, "trangchu"));
        registerPanel.add(btnRegister);
    }

    private void initializeHomePanel() {
        panelClient = new JPanel();
        panelClient.setLayout(null);
        contentPane.add(panelClient, "trangchu");

        JLabel lblHome = new JLabel("Trang Chủ");
        lblHome.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblHome.setBounds(428, 172, 93, 58);
        panelClient.add(lblHome);

        JLabel lblRoomPassword = new JLabel("Mật Khẩu Phòng");
        lblRoomPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRoomPassword.setBounds(284, 296, 144, 37);
        panelClient.add(lblRoomPassword);

        textFieldRoomPassword = new JTextField();
        textFieldRoomPassword.setBounds(464, 302, 179, 29);
        panelClient.add(textFieldRoomPassword);
        textFieldRoomPassword.setColumns(10);

        JButton btnJoinRoom = new JButton("Vào Phòng");
        btnJoinRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnJoinRoom.setBounds(308, 392, 120, 34);
        btnJoinRoom.addActionListener(e -> cardLayout.show(contentPane, "room"));
        panelClient.add(btnJoinRoom);

        JButton btnCreateRoom = new JButton("Tạo Phòng");
        btnCreateRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCreateRoom.setBounds(515, 392, 128, 34);
        btnCreateRoom.addActionListener(e -> System.out.println(UUID.randomUUID().toString().split("-")[0]));
        panelClient.add(btnCreateRoom);
    }

    private void initializeRoomPanel() {
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(null);
        contentPane.add(roomPanel, "room");

        JLabel lblDiscussion = new JLabel("Thảo Luận");
        lblDiscussion.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDiscussion.setBounds(755, 45, 95, 30);
        roomPanel.add(lblDiscussion);

        JTextArea textAreaDiscussion = new JTextArea();
        textAreaDiscussion.setBounds(686, 97, 243, 248);
        roomPanel.add(textAreaDiscussion);

        textFieldMessage = new JTextField();
        textFieldMessage.setBounds(769, 387, 160, 23);
        roomPanel.add(textFieldMessage);
        textFieldMessage.setColumns(10);

        JButton btnSend = new JButton("Gửi");
        btnSend.setBounds(674, 388, 85, 21);
        btnSend.addActionListener(e -> {
            // LOGIC XỬ LÝ CHAT TRWCH TIẾP
        });
        roomPanel.add(btnSend);

        panelLabels = new JPanel();
        panelLabels.setBounds(25, 457, 919, 148);
        panelLabels.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        roomPanel.add(panelLabels);
    }
//
//    private void addClient(String clientName) {
//        JLabel clientLabel = new JLabel(clientName, SwingConstants.CENTER);
//        clientLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
//        clientLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        panelLabels.add(clientLabel);
//        panelLabels.revalidate();
//        panelLabels.repaint();
//    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Client frame = new Client();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
