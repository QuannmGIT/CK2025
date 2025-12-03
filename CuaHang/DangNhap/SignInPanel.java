import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignInPanel extends JPanel {
    private MainFrame mainFrame;

    public SignInPanel(MainFrame frame) {
        this.mainFrame = frame;
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        // Tiêu đề
        JLabel title = new JLabel("ĐĂNG KÝ TÀI KHOẢN");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(80, 50, 300, 40);
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
            // Lấy dữ liệu
            String user = SITUser.getText().trim();
            String name = SITName.getText().trim();
            String email = SITEmail.getText().trim();
            String pass = new String(SITPass.getPassword()).trim();

            // Kiểm tra
            if (user.isEmpty() || email.isEmpty() || name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Gọi Database
            dbConnect db = new dbConnect();
            try (Connection conn = db.getConnection()) {
                if (conn == null) {
                    JOptionPane.showMessageDialog(this, "Lỗi kết nối Database!");
                    return;
                }

                // Kiểm tra xem username đã tồn tại chưa
                String checkSql = "SELECT username FROM user WHERE username = ?";
                try (PreparedStatement psCheck = conn.prepareStatement(checkSql)) {
                    psCheck.setString(1, user);
                    if (psCheck.executeQuery().next()) {
                        JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                // INSERT
                String insertSql = "INSERT INTO user (username, email, full_name, password) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                    ps.setString(1, user);
                    ps.setString(2, email);
                    ps.setString(3, name);
                    ps.setString(4, pass); 
                    // Thực thi lệnh nhập vào database
                    int row = ps.executeUpdate(); 
                    if (row > 0) {
                        JOptionPane.showMessageDialog(this, "Đăng ký thành công! Vui lòng đăng nhập.");
                        
                        // Xóa trắng các ô nhập liệu
                        SITUser.setText("");
                        SITEmail.setText("");
                        SITName.setText("");
                        SITPass.setText("");

                        // Chuyển về màn hình đăng nhập
                        mainFrame.showLoginPanel();
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi Database: " + ex.getMessage());
            }
        });
    
        

    }
}