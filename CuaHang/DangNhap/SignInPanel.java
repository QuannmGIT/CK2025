import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignInPanel extends JPanel {

    private ManageFrame frame; // Tham chiếu để quay lại

    public SignInPanel(ManageFrame frame) {
        this.frame = frame;
        this.setLayout(null);
        this.setBackground(Color.WHITE); // Nền trắng giống PersonPanel

        // --- 1. TIÊU ĐỀ ---
        JLabel lblTitle = new JLabel("Đăng ký thành viên");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setBounds(40, 20, 300, 40);
        this.add(lblTitle);

        // --- 2. KHUNG CHỨA (Container Box) ---
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(40, 80, 550, 420); // Kích thước khung
        formPanel.setBorder(new LineBorder(Color.BLACK, 1)); // Viền đen mỏng
        this.add(formPanel);

        // --- 3. ICON (Trang trí cho giống PersonPanel) ---
        JLabel lblIcon = new JLabel();
        lblIcon.setBounds(225, 20, 100, 100); // Căn giữa khung
        lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
        // Thử load ảnh, nếu không có thì bỏ qua
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/ImageFile/PersonIcon.png")); // Dùng lại icon person hoặc icon khác
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            lblIcon.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblIcon.setText("NEW USER");
            lblIcon.setBorder(new LineBorder(Color.GRAY));
        }
        formPanel.add(lblIcon);


        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Họ tên
        JLabel lblName = new JLabel("Họ và tên:");
        lblName.setFont(labelFont);
        lblName.setBounds(50, 140, 100, 30);
        formPanel.add(lblName);

        JTextField txtName = new JTextField();
        txtName.setFont(textFont);
        txtName.setBounds(160, 140, 300, 35);
        txtName.setBorder(new RoundedBorder(10)); // Giữ lại bo tròn của bạn
        formPanel.add(txtName);

        // Username
        JLabel lblUser = new JLabel("Tài khoản:");
        lblUser.setFont(labelFont);
        lblUser.setBounds(50, 190, 100, 30);
        formPanel.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setFont(textFont);
        txtUser.setBounds(160, 190, 300, 35);
        txtUser.setBorder(new RoundedBorder(10));
        formPanel.add(txtUser);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(labelFont);
        lblEmail.setBounds(50, 240, 100, 30);
        formPanel.add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setFont(textFont);
        txtEmail.setBounds(160, 240, 300, 35);
        txtEmail.setBorder(new RoundedBorder(10));
        formPanel.add(txtEmail);

        // Password
        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(labelFont);
        lblPass.setBounds(50, 290, 100, 30);
        formPanel.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setFont(textFont);
        txtPass.setBounds(160, 290, 300, 35);
        txtPass.setBorder(new RoundedBorder(10));
        formPanel.add(txtPass);

        
        // Nút Đăng ký 
        JButton btnRegister = new JButton("Xác nhận đăng ký");
        btnRegister.setBackground(new Color(32, 178, 170)); 
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRegister.setBounds(160, 350, 300, 45);
        btnRegister.setFocusable(false);
        formPanel.add(btnRegister);

        // Nút Quay lại 
        JButton btnBack = new JButton("Quay lại");
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(Color.BLACK);
        btnBack.setBounds(620, 80, 150, 50); 
        btnBack.setFocusable(false);
        this.add(btnBack);


        // Sự kiện Quay lại
        btnBack.addActionListener(e -> {
            frame.showPersonPanel(); 
        });

        // Sự kiện Đăng ký
        btnRegister.addActionListener(e -> {
            // Lấy dữ liệu
            String user = txtUser.getText().trim();
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();

            // Validate
            if (user.isEmpty() || email.isEmpty() || name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Database Logic
            dbConnect db = new dbConnect();
            try (Connection conn = db.getConnection()) {
                if (conn == null) {
                    JOptionPane.showMessageDialog(this, "Lỗi kết nối Database!");
                    return;
                }

                // Check trùng username
                String checkSql = "SELECT username FROM users WHERE username = ?";
                try (PreparedStatement psCheck = conn.prepareStatement(checkSql)) {
                    psCheck.setString(1, user);
                    if (psCheck.executeQuery().next()) {
                        JOptionPane.showMessageDialog(this, "Tài khoản '" + user + "' đã tồn tại!", "Trùng lặp", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                // Insert
                String insertSql = "INSERT INTO users (username, email, full_name, password) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, user);
                    ps.setString(2, email);
                    ps.setString(3, name);
                    ps.setString(4, pass);

                    int row = ps.executeUpdate();
                    if (row > 0) {
                        String newUserId = "";
                        try (ResultSet rs = ps.getGeneratedKeys()) {
                            if (rs.next()) {
                                newUserId = String.valueOf(rs.getInt(1));
                            }
                        }

                        // Insert vào bảng Average (Quan trọng)
                        String kpiSql = "INSERT INTO average (user_id) VALUES (?)";
                        try (PreparedStatement psKpi = conn.prepareStatement(kpiSql)) {
                            psKpi.setString(1, newUserId);
                            psKpi.executeUpdate();
                        }

                        JOptionPane.showMessageDialog(this, "Đăng ký thành công!\nQuay về màn hình thông tin.");
                        
                        // QUAN TRỌNG: Quay về PersonPanel ngay sau khi thành công
                        frame.showPersonPanel();
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + ex.getMessage());
            }
        });
    }
}