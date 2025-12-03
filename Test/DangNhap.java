package Test; // Bạn có thể đổi tên package tùy ý

import javax.swing.*;
import java.awt.*;

public class DangNhap {

    // Hàm Main để chạy chương trình
    public static void main(String[] args) {
        // Chạy trên luồng giao diện để mượt mà hơn
        SwingUtilities.invokeLater(() -> new DangNhap());
    }

    public DangNhap() {
        // --- 1. THIẾT LẬP KHUNG CỬA SỔ CHÍNH ---
        JFrame DNFrame = new JFrame("Đăng Nhập Hệ Thống");
        DNFrame.setSize(600, 400); // Kích thước rộng 600, cao 400
        DNFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DNFrame.setResizable(false); // Không cho kéo giãn
        DNFrame.setLocationRelativeTo(null); // Căn giữa màn hình
        
        // Sử dụng BorderLayout để chia khung thành 2 phần: Trái và Giữa
        DNFrame.setLayout(new BorderLayout());

        // --- 2. TẠO PANEL BÊN TRÁI (LOGO & HÌNH ẢNH) ---
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(32, 178, 170)); // Màu xanh ngọc (Teal)
        leftPanel.setPreferredSize(new Dimension(250, 400)); // Chiều rộng cố định 250px
        
        // Dùng BoxLayout để xếp các thành phần theo chiều dọc (Y_AXIS)
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Tạo khoảng trống phía trên để đẩy nội dung xuống giữa
        leftPanel.add(Box.createVerticalGlue());

        // Thêm Icon (Giả lập bằng Label nếu chưa có file ảnh)
        JLabel iconLabel = new JLabel();
        // Bạn có thể bỏ comment dòng dưới nếu có file ảnh thật
        // iconLabel.setIcon(new ImageIcon("path/to/icon.png")); 
        iconLabel.setText("LOG IN"); // Dùng chữ thay icon tạm thời
        iconLabel.setFont(new Font("Arial", Font.BOLD, 40));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo trục ngang
        leftPanel.add(iconLabel);

        // Thêm dòng chữ slogan
        JLabel sloganLabel = new JLabel("Quản Lý ");
        sloganLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(sloganLabel);

        // Khoảng trống phía dưới
        leftPanel.add(Box.createVerticalGlue());

        // --- 3. TẠO PANEL BÊN PHẢI (FORM NHẬP LIỆU) ---
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null); // Layout null để dùng setBounds tùy ý

        // -- TẠO TIÊU ĐỀ --
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(32, 178, 170));
        titleLabel.setBounds(100, 30, 200, 40); // Canh giữa panel phải
        rightPanel.add(titleLabel);

        // -- TẠO TÀI KHOẢN --
        // 1. Label Tài Khoản
        JLabel userLabel = new JLabel("Tài Khoản:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setBounds(30, 90, 80, 30); // x=30, y=90
        rightPanel.add(userLabel);

        // 2. TextField Tài Khoản
        JTextField userField = new JTextField();
        userField.setBounds(30, 120, 280, 35); // Nằm ngay dưới Label, rộng 280
        rightPanel.add(userField);

        // -- TẠO MẬT KHẨU --
        // 1. Label Mật Khẩu
        JLabel passLabel = new JLabel("Mật Khẩu:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passLabel.setBounds(30, 170, 80, 30); // Cách phần trên 50px (120+35+15)
        rightPanel.add(passLabel);

        // 2. PasswordField Mật Khẩu (Dùng JPasswordField để che ký tự)
        JPasswordField passField = new JPasswordField();
        passField.setBounds(30, 200, 280, 35);
        rightPanel.add(passField);

        // -- CÁC NÚT BẤM (BUTTON) --
        // 1. Nút Đăng Nhập
        JButton loginBtn = new JButton("Đăng Nhập");
        loginBtn.setBackground(new Color(32, 178, 170));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false); // Bỏ viền chọn khi bấm
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setBounds(30, 260, 130, 40); // x=30, rộng 130
        rightPanel.add(loginBtn);

        // 2. Nút Đăng Ký
        JButton registerBtn = new JButton("Đăng Ký");
        registerBtn.setBackground(Color.WHITE);
        registerBtn.setForeground(new Color(32, 178, 170)); // Chữ màu xanh
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerBtn.setBounds(180, 260, 130, 40); // x=180 (30 + 130 + 20 khoảng cách)
        rightPanel.add(registerBtn);

        // --- 4. GẮN CÁC PANEL VÀO KHUNG CHÍNH ---
        DNFrame.add(leftPanel, BorderLayout.WEST);   // Trái
        DNFrame.add(rightPanel, BorderLayout.CENTER); // Phần còn lại (Phải)

        // --- 5. HIỂN THỊ ---
        DNFrame.setVisible(true);
    }
}