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

        // Full Name
        JLabel SILName = new JLabel("Họ và tên:");
        SILName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        SILName.setBounds(150, 100, 150, 30); 
        this.add(SILName);

        JTextField SITName = new JTextField();
        SITName.setBounds(70, 135, 250, 35);
        SITName.setBorder(new RoundedBorder(10));
        this.add(SITName);        

        // Username
        JLabel SILUser = new JLabel("Tên đăng nhập:");
        SILUser.setFont(new Font("Segoe UI", Font.BOLD, 18));
        SILUser.setBounds(130, 180, 150, 30);
        this.add(SILUser);
        
        JTextField SITUser = new JTextField();
        SITUser.setBounds(70, 215, 250, 35);
        SITUser.setBorder(new RoundedBorder(10));
        this.add(SITUser);

        // Email
        JLabel SILEmail = new JLabel("Email:");
        SILEmail.setFont(new Font("Segoe UI", Font.BOLD, 18));
        SILEmail.setBounds(165, 260, 150, 30);
        this.add(SILEmail);
        
        JTextField SITEmail = new JTextField();
        SITEmail.setBounds(70, 295, 250, 35);
        SITEmail.setBorder(new RoundedBorder(10));
        this.add(SITEmail);

        // Password
        JLabel SILPass = new JLabel("Mật khẩu:");
        SILPass.setFont(new Font("Segoe UI", Font.BOLD, 18));
        SILPass.setBounds(150, 340, 150, 30);
        this.add(SILPass);
        
        JPasswordField SITPass = new JPasswordField();
        SITPass.setBounds(70, 375, 250, 35);
        SITPass.setBorder(new RoundedBorder(10));
        this.add(SITPass);

        

        // Nút Đăng ký
        JButton DangKyBtn = new JButton("Đăng Ký Ngay");
        DangKyBtn.setBackground(Color.WHITE);
        DangKyBtn.setForeground(Color.BLACK);
        DangKyBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        DangKyBtn.setBounds(70, 440, 250, 40);
        this.add(DangKyBtn);

        DangKyBtn.addActionListener(e -> {
            String user = SITName.getText().trim();
            String pass = new String(MatkhauField.getPassword()).trim();
        );

        // Nút Quay lại
        JButton btnBack = new JButton("<< Quay lại Đăng Nhập");
        btnBack.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setForeground(Color.BLUE);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setBounds(95, 490, 200, 30);
        this.add(btnBack);

        // --- Logic ---
        btnBack.addActionListener(e -> mainFrame.showLoginPanel());
        
        DangKyBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thực hiện lệnh INSERT vào DB tại đây");
        });
    }
}