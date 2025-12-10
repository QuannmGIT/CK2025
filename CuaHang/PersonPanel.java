import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonPanel extends JPanel {
    private JLabel lblFullName, lblEmail, lblUsername;
    private JLabel lblTotalOrder, lblPoints;
    private String currentUsername;

    public PersonPanel(String username) {
        this.currentUsername = username; // Lưu lại username được truyền từ ManageFrame

        this.setLayout(null);
        this.setBackground(Color.WHITE);

        // --- GIAO DIỆN (Giống bản vẽ) ---
        JLabel lblTitle = new JLabel("Thông tin tài khoản");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setBounds(40, 20, 300, 40);
        this.add(lblTitle);

        // Khung Profile
        JPanel profilePanel = new JPanel(null);
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setBounds(40, 80, 550, 350);
        profilePanel.setBorder(new LineBorder(Color.BLACK, 1));
        this.add(profilePanel);

        // Avatar
        JLabel lblAvatar = new JLabel();
        lblAvatar.setBorder(new LineBorder(Color.GRAY));
        lblAvatar.setBounds(30, 30, 100, 100);
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon(getClass().getResource("/ImageFile/PersonIcon.png"));
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        lblAvatar.setIcon(new ImageIcon(img));
        profilePanel.add(lblAvatar);

        // Thông tin text (Khởi tạo rỗng, sẽ điền sau khi load DB)
        lblFullName = new JLabel("...");
        lblFullName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblFullName.setBounds(150, 30, 350, 30);
        profilePanel.add(lblFullName);

        lblEmail = new JLabel("...");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(150, 70, 350, 25);
        profilePanel.add(lblEmail);

        lblUsername = new JLabel("...");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblUsername.setForeground(new Color(32, 178, 170));
        lblUsername.setBounds(150, 100, 350, 25);
        profilePanel.add(lblUsername);

        // Đường kẻ
        JSeparator sep = new JSeparator();
        sep.setBounds(0, 160, 550, 10);
        sep.setForeground(Color.BLACK);
        profilePanel.add(sep);

        // Thống kê: Tổng đơn hàng
        JPanel boxOrder = createStatsBox("Tổng đơn hàng", "0");
        boxOrder.setBounds(30, 190, 200, 100);
        lblTotalOrder = (JLabel) boxOrder.getComponent(1); // Lấy reference label số liệu
        profilePanel.add(boxOrder);

        // Thống kê: Điểm
        JPanel Point = createStatsBox("Điểm", "0");
        Point.setBounds(280, 190, 200, 100);
        lblPoints = (JLabel) Point.getComponent(1); // Lấy reference label số liệu
        profilePanel.add(Point);

        // Nút chức năng bên phải
        JButton btnChangePass = new JButton("Đổi mật khẩu");
        btnChangePass.setBackground(Color.WHITE);
        btnChangePass.setBounds(620, 80, 150, 50);
        btnChangePass.setFocusable(false);
        this.add(btnChangePass);

        JButton btnCreateAcc = new JButton("Tạo tài khoản");
        btnCreateAcc.setBackground(Color.WHITE);
        btnCreateAcc.setBounds(620, 150, 150, 50);
        btnCreateAcc.setFocusable(false);
        this.add(btnCreateAcc);


        loadDataFromDatabase();
    }

    private JPanel createStatsBox(String title, String value) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        
        JLabel t = new JLabel(title, SwingConstants.CENTER);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        JLabel v = new JLabel(value, SwingConstants.CENTER);
        v.setFont(new Font("Segoe UI", Font.BOLD, 28));
        
        panel.add(t); panel.add(v);
        return panel;
    }

    // --- HÀM TRUY XUẤT DATABASE ---
    private void loadDataFromDatabase() {
        dbConnect db = new dbConnect();
        try (Connection conn = db.getConnection()) {
            if (conn == null) return;

            // Câu lệnh SQL: Lấy thông tin User và JOIN với bảng average để lấy điểm
            // Sử dụng LEFT JOIN để nếu user chưa có điểm thì vẫn hiện thông tin cá nhân
            String sql = "SELECT u.full_name, u.email, u.username, " +
                         "IFNULL(a.Sum_order, 0) as Sum_order, " +
                         "IFNULL(a.Point, 0) as Point " +
                         "FROM users u " +
                         "LEFT JOIN average a ON u.user_id = a.user_id " +
                         "WHERE u.username = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, this.currentUsername);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Lấy dữ liệu từ DB
                        String name = rs.getString("full_name");
                        String email = rs.getString("email");
                        String user = rs.getString("username");
                        int orders = rs.getInt("Sum_order");
                        int points = rs.getInt("Point");

                        // Đưa lên giao diện
                        lblFullName.setText(name);
                        lblEmail.setText(email);
                        lblUsername.setText(user);
                        lblTotalOrder.setText(String.valueOf(orders));
                        lblPoints.setText(String.valueOf(points));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblFullName.setText("Lỗi kết nối CSDL");
        }

    
    }
}