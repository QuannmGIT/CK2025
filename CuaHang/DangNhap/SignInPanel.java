
import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {
    private MainFrame mainFrame;

    public SignInPanel(MainFrame frame) {
        this.mainFrame = frame;
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        // Tiêu đề
        JLabel title = new JLabel("ĐĂNG KÝ TÀI KHOẢN");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(80, 50, 300, 40); // Căn chỉnh cho đều
        this.add(title);

        // 1. Username
        JLabel lblUser = new JLabel("Tên đăng nhập:");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUser.setBounds(70, 100, 150, 30); // Giữ X=70 giống bên Login
        this.add(lblUser);
        
        JTextField txtUser = new JTextField();
        txtUser.setBounds(70, 130, 250, 35);
        txtUser.setBorder(new RoundedBorder(10));
        this.add(txtUser);

        // 2. Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEmail.setBounds(70, 170, 150, 30);
        this.add(lblEmail);
        
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(70, 200, 250, 35);
        txtEmail.setBorder(new RoundedBorder(10));
        this.add(txtEmail);

        // 3. Password
        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPass.setBounds(70, 240, 150, 30);
        this.add(lblPass);
        
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(70, 270, 250, 35);
        txtPass.setBorder(new RoundedBorder(10));
        this.add(txtPass);

        // Nút Đăng ký
        JButton btnRegister = new JButton("Đăng Ký Ngay");
        btnRegister.setBackground(new Color(32, 178, 170)); // Màu xanh chủ đạo
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setBounds(70, 340, 250, 40);
        this.add(btnRegister);

        // Nút Quay lại
        JButton btnBack = new JButton("<< Quay lại Đăng Nhập");
        btnBack.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setForeground(Color.BLUE);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setBounds(95, 390, 200, 30);
        this.add(btnBack);

        // --- Logic ---
        btnBack.addActionListener(e -> mainFrame.showLoginPanel());
        
        btnRegister.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thực hiện lệnh INSERT vào DB tại đây");
        });
    }
}